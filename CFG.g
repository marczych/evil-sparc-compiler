tree grammar CFG;

options
{
   tokenVocab=Evil;
   ASTLabelType=CommonTree;
}

@header
{
   import java.util.Map;
   import java.util.HashMap;
   import java.util.Vector;
   import java.util.Iterator;
   import java.util.Hashtable;
   import java.io.*;
   import java.util.Collection;
}

@members {
   protected static final int TRUE_VAL = 1;
   protected static final int FALSE_VAL = 0;
   protected static final int NULL_VAL = 0;
   protected static final String GLOBAL_READ = "GLOBALHEREFORREAD";
   protected static final String PRINTF_GLOBAL = "PRINTF_GLOBAL";
   protected static final String PRINTF_ENDL_GLOBAL = "PRINTF_ENDL_GLOBAL";
   protected static final String SCANF_GLOBAL = "SCANF_GLOBAL";
   protected String mFileName;
	private static int nextGlobalLocation = 0;
   private RegisterTable regTable = new RegisterTable();
   private Hashtable<String, Block> funLabels = new Hashtable<String, Block>();
	private int largestNumArgs;
	private Integer numArgs;


	public static Integer nextLocation() {
		return new Integer(nextGlobalLocation++);
	}

   public void setFile(String fileName) {
      mFileName = fileName.substring(0, fileName.length() - 2);
   }

   public Register makeCompare(Compare type, Block b,
     Register r1, Register r2, boolean moveToReg) {
      Register r = null;

      if (moveToReg) {
         r = new Register();
         b.addInstruction(new LoadiInstr(r, FALSE_VAL));
      }

      b.addInstruction(new CompInstr(r1, r2));

      if (moveToReg) {
         b.addInstruction(new MovanyInstr(type, TRUE_VAL, r));
      }

      return r;
   }

   public void writeSparcFile(String contents) {
      try {
         FileOutputStream os = new FileOutputStream(mFileName + "s");

         os.write(contents.getBytes());

         os.close();
      }
      catch (IOException exc) {
         System.err.println("Error writing to file");
      }
   }

   public void writeGlobals(StringBuilder strBuild) {
      Collection<String> globs = regTable.globalTable.keySet();

      for (String name : globs)
         strBuild.append("\t.common " + name + ",4,4\n");
   }

   public void writeStrings(StringBuilder strBuild) {
      strBuild.append("\t.section\t\".rodata\"\n");
      strBuild.append("\t.align 8\n" + PRINTF_GLOBAL + ":\n\t.asciz\t\"\%d \"\n");
      strBuild.append("\t.align 8\n" + PRINTF_ENDL_GLOBAL + ":\n\t.asciz\t\"\%d\\n\"\n");
      strBuild.append("\t.align 8\n" + SCANF_GLOBAL + ":\n\t.asciz\t\"\%d\"\n");
   }

   public void writeIlocFile() {
      try {
         FileOutputStream os = new FileOutputStream(mFileName + "il");

         Collection funs = funLabels.values();

         String temp;
         for (Object fun : funs) {
            temp = "@function " + ((Block)fun).getFullLabel() + "\n";
            os.write(temp.getBytes());
         }

         os.write("\n".getBytes());

         Block.eliminateDeadBlocks();
         Block.checkTailCalls();
         Block.writeIloc(os);
         os.close();
      }
      catch (IOException exc) {
         System.err.println("Error writing to file");
      }
   }
	
	private Hashtable<String, Struct> structTable = new Hashtable<String, Struct>();

	private Block exitBlock, entryBlock;

   public Register getVariable(String id, Block blk) {
      Register reg;
      if ((reg = regTable.findLocalVariable(id)) == null) {
         int location = regTable.findGlobalVariable(id);
         reg = new Register();
         blk.addInstruction(new LoadGlobalInstr(id, reg));
      }

      return reg;
   }

   public void saveVariable(String id, Register val, Block blk) {
      Register reg;
      if ((reg = regTable.findLocalVariable(id)) == null) {
         blk.addInstruction(new StoreGlobalInstr(val, id));
         return;
      }

      blk.addInstruction(new MoveInstr(val, reg));
   }
}

generate : ^(PROGRAM 
               ^(TYPES (types_sub)*)
               ^(DECLS (declarations[true])*)
               ^(FUNCS functions+))
{
	regTable.addGlobal(GLOBAL_READ, new Integer(nextLocation()), "string");
   writeIlocFile();

   Collection<Block> funs = funLabels.values();
   StringBuilder strBuild = new StringBuilder();
   ArrayList<Block> allBlocks;
   RegGraph regGraph;
   boolean liveDone = false, deadDone = true;

   // Convert ILOC instructions into Sparc instructions for each function.
   for (Block fun : funs) {
      if (!fun.mInline) {
         fun.makeSparc();
      }
   }

   // Perform live range analysis and graph coloring for each function
   for (Block fun : funs) {
      if (fun.mInline)
         continue;

      // Get every block in the function.
      fun.getAllBlocks(allBlocks = new ArrayList<Block>());
      SparcRegister.spillCount = 0;

      for (;;) {
         liveDone = false;

         // Make the live out set until there aren't any changes.
         while (!liveDone) {
            liveDone = true;

            // Make the live out set for each block keeping track of whether
            // or not any of them have changed.
            for (Block blk : allBlocks) {
               liveDone &= !blk.makeLiveOut();
            }
         }

         regGraph = new RegGraph();
		 
		 //regGraph.buildGraph(allBlocks);
		 regGraph.initializeIRC();
		 
         // This makes the interference graph that is used in graph coloring.
        for (Block blk : allBlocks) {
           blk.makeInstrLiveOut(regGraph);
        }

         // Perform dead code removal and start over if we have removed
         // any dead instructions because live range analysis will change.
         deadDone = true;
         if (Block.DEAD_CODE)
            for (Block blk : allBlocks)
               deadDone &= !blk.checkDead();

		//if (deadDone)
		//	regGraph.iteratedRegisterCoalescing(allBlocks);
			   
         // If graph coloring was successful then continue on to the next function.
         if (deadDone /*&& regGraph.colorGraph()*/ && regGraph.iteratedRegisterCoalescing(allBlocks)) {
            fun.setSpillCount(SparcRegister.spillCount);
            break;
         }

         // We need to perform live range analysis again so we need to
         // remake the sparc instructions.
         for (Block blk : allBlocks)
            blk.clearMakeSparced();

         fun.remakeSparc();
      }
   }

   if (Block.deadCount > 0)
      System.out.println(Block.deadCount + " dead instructions removed!");
	  
	if (RegGraph.coalescedCount > 0)
		System.out.println(RegGraph.coalescedCount + " mov instructions coalesced.");

   strBuild.append("\t.section\t\".text\"\n");

   for (Block fun : funs) {
      if (fun.mInline)
         continue;

      strBuild.append("\t.align 4\n");
      strBuild.append("\t.global " + fun.getFullLabel() + "\n");
      strBuild.append("\t.type\t" + fun.getFullLabel() + ", #function\n");
      fun.toSparc(strBuild);
      strBuild.append("\t.size\t" + fun.getFullLabel() + ", .-" + fun.getFullLabel() + "\n");
   }

   writeGlobals(strBuild);
   writeStrings(strBuild);

   writeSparcFile(strBuild.toString());
}
;

types_sub
@init {ArrayList<String> fieldList = new ArrayList<String>();
       ArrayList<String> structList = new ArrayList<String>();}
:
   ^(STRUCT i=ID struct_decl[fieldList, structList]+)
	{structTable.put($i.text, new Struct($i.text, fieldList, structList));}
;

struct_decl [ArrayList<String> fieldList, ArrayList<String> types]:
	^(DECL ^(TYPE t=type) i=ID)
	{
		fieldList.add($i.text);
      types.add($t.str);
	}
;

type_decl [ArrayList<IlocInstruction> list] :
   ^(DECL ^(TYPE t=type) id=ID)
	{
      Register reg = new Register();

      regTable.addLocal($id.text, reg, $t.str);
      list.add(new LoadInArgInstr($id.text, list.size(), reg));
   }
;

type returns [String str = null] :
   INT 
	| BOOL 
	| ^(STRUCT id=ID) 
   {
      $str = $id.text;
   }
	| VOID
;

declarations [boolean isGlobal] :
   ^(DECLLIST ^(TYPE t=type) id_list[isGlobal, $t.str]+)
;

id_list [boolean isGlobal, String type] :
   id=ID
	{
		if(isGlobal)
			regTable.addGlobal($id.text, new Integer(nextLocation()), type);
		else
			regTable.addLocal($id.text, new Register(), type);
	}
;

functions @init 
{
   ArrayList<IlocInstruction> list = new ArrayList<IlocInstruction>();  
	largestNumArgs = 0;
}:
   ^(FUN 
		id=ID 
		{
			regTable.newFunction($id.text);
		}
		^(PARAMS type_decl[list]*) 
		^(RETTYPE type) 
		^(DECLS declarations[false]*) 
		{
         entryBlock = new Block($id.text);
         entryBlock.setEntry();
         entryBlock.setNumArgs(list.size());
         entryBlock.appendInstruction(list);
		   exitBlock = new Block("FUN"+$id.text+"EXIT");
         exitBlock.addInstruction(new RetInstr());
         exitBlock.setExit();
         entryBlock.setThen(exitBlock);

         funLabels.put($id.text, entryBlock);
      }
		^(STMTS statements_list[entryBlock, null]))
		{
			entryBlock.setLargestNumArgs(largestNumArgs);
         entryBlock.checkInline();
         if (entryBlock.mInline)
            System.err.println("inline: " + entryBlock.getLabel());
		}
;

statements_list [Block b, BlockReference br] :
{
	if(br == null)
		br = new BlockReference(b);
}
   statements[br.getRef(), br]*
;

freeblock [Block b, BlockReference br] :
   ^(BLOCK ^(STMTS statements[br.getRef(), br]*))
;

statements [Block b, BlockReference br] :
   freeblock[b, br]
	| ^(ASSIGN 
			r=expression[br, new Boolean(false), new Boolean(false)] 
			lvalue[br.getRef(), br, $r.r])
	| ^(PRINT r=expression[br, new Boolean(false), new Boolean(false)] endl=ENDL?)
   {
      br.getRef().addInstruction(new PrintInstr($r.r, $endl != null));
   }
	| 
	{ 
		Register reg = new Register();
      br.getRef().addInstruction(new ComputeGlobalInstr(GLOBAL_READ, reg));
   	br.getRef().addInstruction(new ReadInstr(reg));
      br.getRef().addInstruction(new LoadGlobalInstr(GLOBAL_READ, reg));
	}
	^(READ lvalue[br.getRef(), br, reg])
	|
   {
      String funName = br.getRef().getLabel();
   }
   ^(IF r=expression[br, new Boolean(false), new Boolean(true)]
        thenBlock=block["THEN"] elseBlock=block["ELSE"]?)
	{ 
		Block cont = new Block(funName + "CONT");
      cont.setThen(exitBlock);

		//setThen adds the continuation for this. So if it's a then without an else
		//it "then" continues to the continuation block
		$thenBlock.end.setThen(cont);
      if ($elseBlock.body != null)
         $elseBlock.end.setThen(cont);

		br.getRef().setThen($thenBlock.body);
		br.getRef().setElse($elseBlock.body != null ? $elseBlock.body : cont);
		br.getRef().appendCondition($r.r, $r.compareType);

		// if there is an else block, tell it to "then" go to the continuation
      br.setRef(cont);
	}
	| 
	{
      BlockReference condBR;
   }
   {
      String funName = br.getRef().getLabel();
   }
	^(WHILE r=expression[br, new Boolean(false), new Boolean(true)] body=block["WHILE"]
           r2=expression[condBR = new BlockReference($body.end), new Boolean(false), new Boolean(true)])
	{
      entryBlock.mLoop = true;
		Block cont = new Block(funName + "CONT");
      cont.setThen(exitBlock);
		br.getRef().setThen($body.body);
		br.getRef().setElse(cont);
		br.getRef().appendCondition($r.r, $r.compareType);
		condBR.getRef().setThen($body.body);
		condBR.getRef().setElse(cont);
		condBR.getRef().appendBackwardsCondition($r2.r,
       $r2.compareType);
      br.setRef(cont);
	}
	| ^(DELETE r=expression[br, new Boolean(false), new Boolean(false)])
   {
      br.getRef().addInstruction(new DelInstr($r.r));
   }
	| ^(RETURN r=expression[br, new Boolean(true), new Boolean(false)]?)
   {
      if (!$r.tail) {
         if ($r.r != null)
            br.getRef().addInstruction(new StoreRetInstr($r.r));

         br.getRef().setThen(exitBlock);
         br.getRef().setReturn();
      }
   }
	| 
   {
      String funName = br.getRef().getLabel();
   }
	^(INVOKE id=ID arguments[br.getRef(), br])
   {
      if ($id.text.equals(entryBlock.getLabel()))
         entryBlock.mCallsSelf = true;

      Block fun = funLabels.get($id.text);

      if (!fun.mInline) {
         br.getRef().addInstruction(new CallInstr(fun.getFullLabel()));
         if(largestNumArgs < numArgs) {
            largestNumArgs = numArgs.intValue();
         }
      }
      else {
         Block cont = new Block(funName + "CONT");
         Hashtable<Block, Block> blocks = new Hashtable<Block, Block>();
         Hashtable<Register, Register> regs = new Hashtable<Register, Register>();
         Block inlineEntry = fun.mInlineEntry.inlineMakeClone(blocks, regs);
         Block inlineExit = inlineEntry.mInlineExit;

         cont.setThen(exitBlock);
         br.getRef().setThen(inlineEntry);
         inlineExit.setThen(cont);
         br.setRef(cont);
      }
   }
;

block [String label] returns [Block body = null, Block end = null]:
   {
      Block newBlock = new Block(label);
      BlockReference bRef = new BlockReference(newBlock);
   }
	^(BLOCK ^(STMTS statements_list[newBlock, bRef]))
   {
      $body = newBlock;
      $end = bRef.getRef();
   }
;

lvalue [Block b, BlockReference br, Register val] :
	id=ID 
	{
      //variable name
      // "i"
      saveVariable($id.text, val, br.getRef());
   }
	|
	^(DOT lval=lvalue_h[br.getRef()] id=ID)
	{
      //struct (id = field name, lval = base addr)
      // a.a.a.a."a.i"
      br.getRef().addInstruction(new StoreaiInstr(val, $lval.r, $id.text, $lval.s));
   }
;

lvalue_h [Block blk] returns [Register r = null, Struct s = null] :
	id=ID 
	{
      //variable name of struct
      // "a".a.a.a.a.i
      $r = getVariable($id.text, blk);
      $s = structTable.get(regTable.getStruct($id.text));
   }
	|
	^(DOT lval=lvalue_h[blk] id=ID)
	{
      //id = field name, lval = base addr of struct
      // a."a.a.a".a.i
      $r = new Register();
      $s = structTable.get($lval.s.getStructType($id.text));
      blk.addInstruction(new LoadaiInstr($lval.r, $id.text, $lval.s, $r));
   }
;

expression [BlockReference br, Boolean isReturning, Boolean isBranching]
  returns [Register r = null, Struct struct = null, boolean tail = false, Compare compareType = null] :
	^(AND r1=expression[br, new Boolean(false), new Boolean(false)]
         r2=expression[br, new Boolean(false), new Boolean(false)])
   {
      $r = new Register();
      br.getRef().addInstruction(new AndInstr($r1.r, $r2.r, $r));
   }
	| ^(OR r1=expression[br, new Boolean(false), new Boolean(false)]
          r2=expression[br, new Boolean(false), new Boolean(false)])
   {
      $r = new Register();
      br.getRef().addInstruction(new OrInstr($r1.r, $r2.r, $r));
   }
	| ^(EQ r1=expression[br, new Boolean(false), new Boolean(false)]
          r2=expression[br, new Boolean(false), new Boolean(false)])
   {
      $compareType = Compare.EQ;
      $r = makeCompare(Compare.EQ, br.getRef(), $r1.r, $r2.r, !isBranching);
   }
	| ^(LT r1=expression[br, new Boolean(false), new Boolean(false)]
          r2=expression[br, new Boolean(false), new Boolean(false)])
   {
      $compareType = Compare.LT;
      $r = makeCompare(Compare.LT, br.getRef(), $r1.r, $r2.r, !isBranching);
   }
	| ^(GT r1=expression[br, new Boolean(false), new Boolean(false)]
          r2=expression[br, new Boolean(false), new Boolean(false)])
   {
      $compareType = Compare.GT;
      $r = makeCompare(Compare.GT, br.getRef(), $r1.r, $r2.r, !isBranching);
   }
	| ^(NE r1=expression[br, new Boolean(false), new Boolean(false)]
          r2=expression[br, new Boolean(false), new Boolean(false)])
   {
      $compareType = Compare.NE;
      $r = makeCompare(Compare.NE, br.getRef(), $r1.r, $r2.r, !isBranching);
   }
	| ^(LE r1=expression[br, new Boolean(false), new Boolean(false)]
          r2=expression[br, new Boolean(false), new Boolean(false)])
   {
      $compareType = Compare.LE;
      $r = makeCompare(Compare.LE, br.getRef(), $r1.r, $r2.r, !isBranching);
   }
	| ^(GE r1=expression[br, new Boolean(false), new Boolean(false)]
          r2=expression[br, new Boolean(false), new Boolean(false)])
   {
      $compareType = Compare.GE;
      $r = makeCompare(Compare.GE, br.getRef(), $r1.r, $r2.r, !isBranching);
   }
	| ^(PLUS r1=expression[br, new Boolean(false), new Boolean(false)]
            r2=expression[br, new Boolean(false), new Boolean(false)])
   {
      $r = new Register();
      br.getRef().addInstruction(new AddInstr($r1.r, $r2.r, $r));
   }
	| ^(MINUS r1=expression[br, new Boolean(false), new Boolean(false)]
             r2=expression[br, new Boolean(false), new Boolean(false)])
   {
      $r = new Register();
      br.getRef().addInstruction(new SubInstr($r1.r, $r2.r, $r));
   }
	| ^(TIMES r1=expression[br, new Boolean(false), new Boolean(false)]
             r2=expression[br, new Boolean(false), new Boolean(false)])
   {
      $r = new Register();
      br.getRef().addInstruction(new MultInstr($r1.r, $r2.r, $r));
   }
	| ^(DIVIDE r1=expression[br, new Boolean(false), new Boolean(false)]
              r2=expression[br, new Boolean(false), new Boolean(false)])
   {
      $r = new Register();
      br.getRef().addInstruction(new DivInstr($r1.r, $r2.r, $r));
   }
	| ^(DOT r1=expression[br, new Boolean(false), new Boolean(false)] id=ID)
   {
      $r = new Register();
      String type = $r1.struct.getStructType($id.text);
      if (type != null)
         $struct = structTable.get(type);
      br.getRef().addInstruction(new LoadaiInstr($r1.r, $id.text, $r1.struct, $r));
   }
	| 
   {
      String funName = br.getRef().getLabel();
   }
	^(INVOKE id=ID list=arguments[br.getRef(), br])
   {
      $r = new Register();
      if ($id.text.equals(entryBlock.getLabel()))
         entryBlock.mCallsSelf = true;

      Block fun = funLabels.get($id.text);

      if (fun.mInline) {
         Block cont = new Block(funName + "CONT");
         Hashtable<Block, Block> blocks = new Hashtable<Block, Block>();
         Hashtable<Register, Register> regs = new Hashtable<Register, Register>();
         Block inlineEntry = fun.mInlineEntry.inlineMakeClone(blocks, regs);
         Block inlineExit = inlineEntry.mInlineExit;

         cont.setThen(exitBlock);
         br.getRef().setThen(inlineEntry);
         inlineExit.setThen(cont);
         cont.addInstruction(new LoadRetInstr($r));
         br.setRef(cont);
      } else if (isReturning && Block.TAIL_CALL) {
         // Tail call time.
         $tail = true;

         System.out.println("Tail: " + $id.text);

         // But first we need to put the arguments in the in registers.
         for (StoreoutInstr storeInstr : $list.storeOutList) {
            storeInstr.setTail(true);
         }

         br.getRef().addInstruction(new CallInstr(fun.getFullLabel(), true));
         br.getRef().setThen(null);
         br.getRef().setElse(null);
      } else {
         br.getRef().addInstruction(new CallInstr(fun.getFullLabel()));
         br.getRef().addInstruction(new LoadRetInstr($r));
         if(largestNumArgs < numArgs) {
            largestNumArgs = numArgs.intValue();
         }
      }
   }
	| ^(NEG r1=expression[br, new Boolean(false), new Boolean(false)])
   {
      $r = new Register();
      br.getRef().addInstruction(new NegInstr($r1.r, $r));
   }
	| num=INTEGER
   {
      $r = new Register();
      br.getRef().addInstruction(new LoadiInstr($r, $num.int));
   }
	| TRUE
   {
      $r = new Register();
      br.getRef().addInstruction(new LoadiInstr($r, TRUE_VAL));
   }
	| FALSE
   {
      $r = new Register();
      br.getRef().addInstruction(new LoadiInstr($r, FALSE_VAL));
   }
	| ^(NOT r1=expression[br, new Boolean(false), new Boolean(false)])
   {
      $r = new Register();
      br.getRef().addInstruction(new LoadiInstr($r, TRUE_VAL));
      br.getRef().addInstruction(new CompiInstr($r1.r, TRUE_VAL));
      br.getRef().addInstruction(new MovanyInstr(Compare.EQ, FALSE_VAL, $r));
   }
	| ^(NEW id=ID)
   {
      $r = new Register();
      br.getRef().addInstruction(new NewInstr(structTable.get($id.text), $r));
   }
	| NULL
   {
      $r = new Register();
      br.getRef().addInstruction(new LoadiInstr($r, NULL_VAL));
   }
	| id=ID
   {
      $r = getVariable($id.text, br.getRef());
      String struct;
      if ((struct = regTable.getStruct($id.text)) != null)
         $struct = structTable.get(regTable.getStruct($id.text));
   }
;

arguments [Block b, BlockReference br]
  returns [ArrayList<StoreoutInstr> storeOutList = null] :
   {
      ArrayList<Register> regs = new ArrayList<Register>();
   }
	^(ARGS arg_expression[regs, br]*)
   {
      storeOutList = new ArrayList<StoreoutInstr>();
      StoreoutInstr instr;
      for (int i = 0; i < regs.size(); i ++) {
         instr = new StoreoutInstr(regs.get(i), i);
         storeOutList.add(instr);
         br.getRef().addInstruction(instr);
      }

		numArgs = new Integer(regs.size());
   }
;

arg_expression [ArrayList<Register> regs, BlockReference br] :
	reg=expression[br, new Boolean(false), new Boolean(false)]
   {
      regs.add($reg.r);
   }
;
