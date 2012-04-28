// $ANTLR 3.3 Nov 30, 2010 12:50:56 CFG.g 2012-04-28 14:31:20

   import java.util.Map;
   import java.util.HashMap;
   import java.util.Vector;
   import java.util.Iterator;
   import java.util.Hashtable;
   import java.io.*;
   import java.util.Collection;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CFG extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STRUCT", "INT", "BOOL", "FUN", "VOID", "PRINT", "ENDL", "READ", "IF", "ELSE", "WHILE", "DELETE", "RETURN", "TRUE", "FALSE", "NEW", "NULL", "PROGRAM", "TYPES", "TYPE", "DECLS", "FUNCS", "DECL", "DECLLIST", "PARAMS", "RETTYPE", "BLOCK", "STMTS", "INVOKE", "ARGS", "NEG", "LBRACE", "RBRACE", "SEMI", "COMMA", "LPAREN", "RPAREN", "ASSIGN", "DOT", "AND", "OR", "EQ", "LT", "GT", "NE", "LE", "GE", "PLUS", "MINUS", "TIMES", "DIVIDE", "NOT", "ID", "INTEGER", "WS", "COMMENT"
    };
    public static final int EOF=-1;
    public static final int STRUCT=4;
    public static final int INT=5;
    public static final int BOOL=6;
    public static final int FUN=7;
    public static final int VOID=8;
    public static final int PRINT=9;
    public static final int ENDL=10;
    public static final int READ=11;
    public static final int IF=12;
    public static final int ELSE=13;
    public static final int WHILE=14;
    public static final int DELETE=15;
    public static final int RETURN=16;
    public static final int TRUE=17;
    public static final int FALSE=18;
    public static final int NEW=19;
    public static final int NULL=20;
    public static final int PROGRAM=21;
    public static final int TYPES=22;
    public static final int TYPE=23;
    public static final int DECLS=24;
    public static final int FUNCS=25;
    public static final int DECL=26;
    public static final int DECLLIST=27;
    public static final int PARAMS=28;
    public static final int RETTYPE=29;
    public static final int BLOCK=30;
    public static final int STMTS=31;
    public static final int INVOKE=32;
    public static final int ARGS=33;
    public static final int NEG=34;
    public static final int LBRACE=35;
    public static final int RBRACE=36;
    public static final int SEMI=37;
    public static final int COMMA=38;
    public static final int LPAREN=39;
    public static final int RPAREN=40;
    public static final int ASSIGN=41;
    public static final int DOT=42;
    public static final int AND=43;
    public static final int OR=44;
    public static final int EQ=45;
    public static final int LT=46;
    public static final int GT=47;
    public static final int NE=48;
    public static final int LE=49;
    public static final int GE=50;
    public static final int PLUS=51;
    public static final int MINUS=52;
    public static final int TIMES=53;
    public static final int DIVIDE=54;
    public static final int NOT=55;
    public static final int ID=56;
    public static final int INTEGER=57;
    public static final int WS=58;
    public static final int COMMENT=59;

    // delegates
    // delegators


        public CFG(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public CFG(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return CFG.tokenNames; }
    public String getGrammarFileName() { return "CFG.g"; }


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

       public Register makeMoveAny(String type, Block b,
         Register r1, Register r2) {
          Register r = new Register();

          b.addInstruction(new LoadiInstr(r, FALSE_VAL));
          b.addInstruction(new CompInstr(r1, r2));
          b.addInstruction(new MovanyInstr(type, TRUE_VAL, r));

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
          strBuild.append("\t.align 8\n" + PRINTF_GLOBAL + ":\n\t.asciz\t\"%d \"\n");
          strBuild.append("\t.align 8\n" + PRINTF_ENDL_GLOBAL + ":\n\t.asciz\t\"%d\\n\"\n");
          strBuild.append("\t.align 8\n" + SCANF_GLOBAL + ":\n\t.asciz\t\"%d\"\n");
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



    // $ANTLR start "generate"
    // CFG.g:130:1: generate : ^( PROGRAM ^( TYPES ( types_sub )* ) ^( DECLS ( declarations[true] )* ) ^( FUNCS ( functions )+ ) ) ;
    public final void generate() throws RecognitionException {
        try {
            // CFG.g:130:10: ( ^( PROGRAM ^( TYPES ( types_sub )* ) ^( DECLS ( declarations[true] )* ) ^( FUNCS ( functions )+ ) ) )
            // CFG.g:130:12: ^( PROGRAM ^( TYPES ( types_sub )* ) ^( DECLS ( declarations[true] )* ) ^( FUNCS ( functions )+ ) )
            {
            match(input,PROGRAM,FOLLOW_PROGRAM_in_generate46); 

            match(input, Token.DOWN, null); 
            match(input,TYPES,FOLLOW_TYPES_in_generate65); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // CFG.g:131:24: ( types_sub )*
                loop1:
                do {
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==STRUCT) ) {
                        alt1=1;
                    }


                    switch (alt1) {
                	case 1 :
                	    // CFG.g:131:25: types_sub
                	    {
                	    pushFollow(FOLLOW_types_sub_in_generate68);
                	    types_sub();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop1;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }
            match(input,DECLS,FOLLOW_DECLS_in_generate89); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // CFG.g:132:24: ( declarations[true] )*
                loop2:
                do {
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==DECLLIST) ) {
                        alt2=1;
                    }


                    switch (alt2) {
                	case 1 :
                	    // CFG.g:132:25: declarations[true]
                	    {
                	    pushFollow(FOLLOW_declarations_in_generate92);
                	    declarations(true);

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop2;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }
            match(input,FUNCS,FOLLOW_FUNCS_in_generate114); 

            match(input, Token.DOWN, null); 
            // CFG.g:133:24: ( functions )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==FUN) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // CFG.g:133:24: functions
            	    {
            	    pushFollow(FOLLOW_functions_in_generate116);
            	    functions();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            match(input, Token.UP, null); 

            match(input, Token.UP, null); 

            	regTable.addGlobal(GLOBAL_READ, new Integer(nextLocation()), "string");
               writeIlocFile();

               Collection<Block> funs = funLabels.values();
               StringBuilder strBuild = new StringBuilder();
               String temp;
               ArrayList<Block> allBlocks;
               RegGraph regGraph;
               boolean liveDone = false, deadDone = true;

               for (Block fun : funs) {
                  if (!fun.mInline)
                     fun.makeSparc();
               }

               for (Block fun : funs) {
                  if (fun.mInline)
                     continue;

                  fun.getAllBlocks(allBlocks = new ArrayList<Block>());
                  SparcRegister.spillCount = 0;

                  for (;;) {

                     liveDone = false;

                     while (!liveDone) {
                        liveDone = true;

                        for (Block blk : allBlocks) {
                           liveDone &= !blk.makeLiveOut();
                        }
                     }

                     regGraph = new RegGraph();
                     for (Block blk : allBlocks) {
                        blk.makeInstrLiveOut(regGraph);
                     }

                     deadDone = true;
                     if (Block.DEAD_CODE)
                        for (Block blk : allBlocks)
                           deadDone &= !blk.checkDead();

                     if (deadDone && regGraph.colorGraph()) {
                        fun.setSpillCount(SparcRegister.spillCount);
                        break;
                     }

                     for (Block blk : allBlocks)
                        blk.clearMakeSparced();

                     fun.remakeSparc();
                  }
               }

               if (Block.deadCount > 0)
                  System.out.println(Block.deadCount + " dead instructions removed!");

               strBuild.append("\t.section\t\".text\"\n");

               for (Block fun : funs) {
                  if (fun.mInline)
                     continue;

                  strBuild.append("\t.align 4\n");
                  strBuild.append("\t.global " + fun.getFullLabel() + "\n");
                  strBuild.append("\t.type\t" + fun.getFullLabel() + ", #function\n");
                  strBuild.append(temp = fun.toSparc());
                  strBuild.append("\t.size\t" + fun.getFullLabel() + ", .-" + fun.getFullLabel() + "\n");
               }

               writeGlobals(strBuild);
               writeStrings(strBuild);

               writeSparcFile(strBuild.toString());


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "generate"


    // $ANTLR start "types_sub"
    // CFG.g:214:1: types_sub : ^( STRUCT i= ID ( struct_decl[fieldList, structList] )+ ) ;
    public final void types_sub() throws RecognitionException {
        CommonTree i=null;

        ArrayList<String> fieldList = new ArrayList<String>();
               ArrayList<String> structList = new ArrayList<String>();
        try {
            // CFG.g:217:1: ( ^( STRUCT i= ID ( struct_decl[fieldList, structList] )+ ) )
            // CFG.g:218:4: ^( STRUCT i= ID ( struct_decl[fieldList, structList] )+ )
            {
            match(input,STRUCT,FOLLOW_STRUCT_in_types_sub139); 

            match(input, Token.DOWN, null); 
            i=(CommonTree)match(input,ID,FOLLOW_ID_in_types_sub143); 
            // CFG.g:218:18: ( struct_decl[fieldList, structList] )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==DECL) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // CFG.g:218:18: struct_decl[fieldList, structList]
            	    {
            	    pushFollow(FOLLOW_struct_decl_in_types_sub145);
            	    struct_decl(fieldList, structList);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            match(input, Token.UP, null); 
            structTable.put((i!=null?i.getText():null), new Struct((i!=null?i.getText():null), fieldList, structList));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "types_sub"


    // $ANTLR start "struct_decl"
    // CFG.g:222:1: struct_decl[ArrayList<String> fieldList, ArrayList<String> types] : ^( DECL ^( TYPE t= type ) i= ID ) ;
    public final void struct_decl(ArrayList<String> fieldList, ArrayList<String> types) throws RecognitionException {
        CommonTree i=null;
        String t = null;


        try {
            // CFG.g:222:67: ( ^( DECL ^( TYPE t= type ) i= ID ) )
            // CFG.g:223:2: ^( DECL ^( TYPE t= type ) i= ID )
            {
            match(input,DECL,FOLLOW_DECL_in_struct_decl163); 

            match(input, Token.DOWN, null); 
            match(input,TYPE,FOLLOW_TYPE_in_struct_decl166); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_struct_decl170);
            t=type();

            state._fsp--;


            match(input, Token.UP, null); 
            i=(CommonTree)match(input,ID,FOLLOW_ID_in_struct_decl175); 

            match(input, Token.UP, null); 

            		fieldList.add((i!=null?i.getText():null));
                  types.add(t);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "struct_decl"


    // $ANTLR start "type_decl"
    // CFG.g:230:1: type_decl[ArrayList<IlocInstruction> list] : ^( DECL ^( TYPE t= type ) id= ID ) ;
    public final void type_decl(ArrayList<IlocInstruction> list) throws RecognitionException {
        CommonTree id=null;
        String t = null;


        try {
            // CFG.g:230:45: ( ^( DECL ^( TYPE t= type ) id= ID ) )
            // CFG.g:231:4: ^( DECL ^( TYPE t= type ) id= ID )
            {
            match(input,DECL,FOLLOW_DECL_in_type_decl194); 

            match(input, Token.DOWN, null); 
            match(input,TYPE,FOLLOW_TYPE_in_type_decl197); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_type_decl201);
            t=type();

            state._fsp--;


            match(input, Token.UP, null); 
            id=(CommonTree)match(input,ID,FOLLOW_ID_in_type_decl206); 

            match(input, Token.UP, null); 

                  Register reg = new Register();

                  regTable.addLocal((id!=null?id.getText():null), reg, t);
                  list.add(new LoadInArgInstr((id!=null?id.getText():null), list.size(), reg));
               

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "type_decl"


    // $ANTLR start "type"
    // CFG.g:240:1: type returns [String str = null] : ( INT | BOOL | ^( STRUCT id= ID ) | VOID );
    public final String type() throws RecognitionException {
        String str =  null;

        CommonTree id=null;

        try {
            // CFG.g:240:34: ( INT | BOOL | ^( STRUCT id= ID ) | VOID )
            int alt5=4;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt5=1;
                }
                break;
            case BOOL:
                {
                alt5=2;
                }
                break;
            case STRUCT:
                {
                alt5=3;
                }
                break;
            case VOID:
                {
                alt5=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // CFG.g:241:4: INT
                    {
                    match(input,INT,FOLLOW_INT_in_type226); 

                    }
                    break;
                case 2 :
                    // CFG.g:242:4: BOOL
                    {
                    match(input,BOOL,FOLLOW_BOOL_in_type232); 

                    }
                    break;
                case 3 :
                    // CFG.g:243:4: ^( STRUCT id= ID )
                    {
                    match(input,STRUCT,FOLLOW_STRUCT_in_type239); 

                    match(input, Token.DOWN, null); 
                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_type243); 

                    match(input, Token.UP, null); 

                          str = (id!=null?id.getText():null);
                       

                    }
                    break;
                case 4 :
                    // CFG.g:247:4: VOID
                    {
                    match(input,VOID,FOLLOW_VOID_in_type255); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return str;
    }
    // $ANTLR end "type"


    // $ANTLR start "declarations"
    // CFG.g:250:1: declarations[boolean isGlobal] : ^( DECLLIST ^( TYPE t= type ) ( id_list[isGlobal, $t.str] )+ ) ;
    public final void declarations(boolean isGlobal) throws RecognitionException {
        String t = null;


        try {
            // CFG.g:250:33: ( ^( DECLLIST ^( TYPE t= type ) ( id_list[isGlobal, $t.str] )+ ) )
            // CFG.g:251:4: ^( DECLLIST ^( TYPE t= type ) ( id_list[isGlobal, $t.str] )+ )
            {
            match(input,DECLLIST,FOLLOW_DECLLIST_in_declarations270); 

            match(input, Token.DOWN, null); 
            match(input,TYPE,FOLLOW_TYPE_in_declarations273); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_declarations277);
            t=type();

            state._fsp--;


            match(input, Token.UP, null); 
            // CFG.g:251:30: ( id_list[isGlobal, $t.str] )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==ID) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // CFG.g:251:30: id_list[isGlobal, $t.str]
            	    {
            	    pushFollow(FOLLOW_id_list_in_declarations280);
            	    id_list(isGlobal, t);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "declarations"


    // $ANTLR start "id_list"
    // CFG.g:254:1: id_list[boolean isGlobal, String type] : id= ID ;
    public final void id_list(boolean isGlobal, String type) throws RecognitionException {
        CommonTree id=null;

        try {
            // CFG.g:254:41: (id= ID )
            // CFG.g:255:4: id= ID
            {
            id=(CommonTree)match(input,ID,FOLLOW_ID_in_id_list299); 

            		if(isGlobal)
            			regTable.addGlobal((id!=null?id.getText():null), new Integer(nextLocation()), type);
            		else
            			regTable.addLocal((id!=null?id.getText():null), new Register(), type);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "id_list"


    // $ANTLR start "functions"
    // CFG.g:264:1: functions : ^( FUN id= ID ^( PARAMS ( type_decl[list] )* ) ^( RETTYPE type ) ^( DECLS ( declarations[false] )* ) ^( STMTS statements_list[entryBlock, null] ) ) ;
    public final void functions() throws RecognitionException {
        CommonTree id=null;


           ArrayList<IlocInstruction> list = new ArrayList<IlocInstruction>();  
        	largestNumArgs = 0;

        try {
            // CFG.g:268:2: ( ^( FUN id= ID ^( PARAMS ( type_decl[list] )* ) ^( RETTYPE type ) ^( DECLS ( declarations[false] )* ) ^( STMTS statements_list[entryBlock, null] ) ) )
            // CFG.g:269:4: ^( FUN id= ID ^( PARAMS ( type_decl[list] )* ) ^( RETTYPE type ) ^( DECLS ( declarations[false] )* ) ^( STMTS statements_list[entryBlock, null] ) )
            {
            match(input,FUN,FOLLOW_FUN_in_functions320); 

            match(input, Token.DOWN, null); 
            id=(CommonTree)match(input,ID,FOLLOW_ID_in_functions327); 

            			regTable.newFunction((id!=null?id.getText():null));
            		
            match(input,PARAMS,FOLLOW_PARAMS_in_functions337); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // CFG.g:274:12: ( type_decl[list] )*
                loop7:
                do {
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==DECL) ) {
                        alt7=1;
                    }


                    switch (alt7) {
                	case 1 :
                	    // CFG.g:274:12: type_decl[list]
                	    {
                	    pushFollow(FOLLOW_type_decl_in_functions339);
                	    type_decl(list);

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop7;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }
            match(input,RETTYPE,FOLLOW_RETTYPE_in_functions348); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_functions350);
            type();

            state._fsp--;


            match(input, Token.UP, null); 
            match(input,DECLS,FOLLOW_DECLS_in_functions357); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // CFG.g:276:11: ( declarations[false] )*
                loop8:
                do {
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==DECLLIST) ) {
                        alt8=1;
                    }


                    switch (alt8) {
                	case 1 :
                	    // CFG.g:276:11: declarations[false]
                	    {
                	    pushFollow(FOLLOW_declarations_in_functions359);
                	    declarations(false);

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop8;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

                     entryBlock = new Block((id!=null?id.getText():null));
                     entryBlock.setEntry();
                     entryBlock.appendInstruction(list);
            		   exitBlock = new Block("FUN"+(id!=null?id.getText():null)+"EXIT");
                     exitBlock.addInstruction(new RetInstr());
                     entryBlock.addThen(exitBlock);

                     funLabels.put((id!=null?id.getText():null), entryBlock);
                  
            match(input,STMTS,FOLLOW_STMTS_in_functions372); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_statements_list_in_functions374);
                statements_list(entryBlock, null);

                state._fsp--;


                match(input, Token.UP, null); 
            }

            match(input, Token.UP, null); 

            			entryBlock.setLargestNumArgs(largestNumArgs);
                     entryBlock.checkInline();
                     if (entryBlock.mInline)
                        System.err.println("inline: " + entryBlock.getLabel());
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "functions"


    // $ANTLR start "statements_list"
    // CFG.g:296:1: statements_list[Block b, BlockReference br] : ( statements[br.getRef(), br] )* ;
    public final void statements_list(Block b, BlockReference br) throws RecognitionException {
        try {
            // CFG.g:296:46: ( ( statements[br.getRef(), br] )* )
            // CFG.g:297:1: ( statements[br.getRef(), br] )*
            {

            	if(br == null)
            		br = new BlockReference(b);

            // CFG.g:301:4: ( statements[br.getRef(), br] )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==PRINT||(LA9_0>=READ && LA9_0<=IF)||(LA9_0>=WHILE && LA9_0<=RETURN)||LA9_0==BLOCK||LA9_0==INVOKE||LA9_0==ASSIGN) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // CFG.g:301:4: statements[br.getRef(), br]
            	    {
            	    pushFollow(FOLLOW_statements_in_statements_list397);
            	    statements(br.getRef(), br);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "statements_list"


    // $ANTLR start "freeblock"
    // CFG.g:304:1: freeblock[Block b, BlockReference br] : ^( BLOCK ^( STMTS ( statements[br.getRef(), br] )* ) ) ;
    public final void freeblock(Block b, BlockReference br) throws RecognitionException {
        try {
            // CFG.g:304:40: ( ^( BLOCK ^( STMTS ( statements[br.getRef(), br] )* ) ) )
            // CFG.g:305:4: ^( BLOCK ^( STMTS ( statements[br.getRef(), br] )* ) )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_freeblock414); 

            match(input, Token.DOWN, null); 
            match(input,STMTS,FOLLOW_STMTS_in_freeblock417); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // CFG.g:305:20: ( statements[br.getRef(), br] )*
                loop10:
                do {
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==PRINT||(LA10_0>=READ && LA10_0<=IF)||(LA10_0>=WHILE && LA10_0<=RETURN)||LA10_0==BLOCK||LA10_0==INVOKE||LA10_0==ASSIGN) ) {
                        alt10=1;
                    }


                    switch (alt10) {
                	case 1 :
                	    // CFG.g:305:20: statements[br.getRef(), br]
                	    {
                	    pushFollow(FOLLOW_statements_in_freeblock419);
                	    statements(br.getRef(), br);

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop10;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "freeblock"


    // $ANTLR start "statements"
    // CFG.g:308:1: statements[Block b, BlockReference br] : ( freeblock[b, br] | ^( ASSIGN r= expression[br] lvalue[br.getRef(), br, $r.r] ) | ^( PRINT r= expression[br] (endl= ENDL )? ) | ^( READ lvalue[br.getRef(), br, reg] ) | ^( IF r= expression[br] thenBlock= block[\"THEN\"] (elseBlock= block[\"ELSE\"] )? ) | ^( WHILE r= expression[br] body= block[\"WHILE\"] r2= expression[condBR = new BlockReference($body.end)] ) | ^( DELETE r= expression[br] ) | ^( RETURN (r= expression[br] )? ) | ^( INVOKE id= ID arguments[br.getRef(), br] ) );
    public final void statements(Block b, BlockReference br) throws RecognitionException {
        CommonTree endl=null;
        CommonTree id=null;
        CFG.expression_return r = null;

        CFG.block_return thenBlock = null;

        CFG.block_return elseBlock = null;

        CFG.block_return body = null;

        CFG.expression_return r2 = null;


        try {
            // CFG.g:308:41: ( freeblock[b, br] | ^( ASSIGN r= expression[br] lvalue[br.getRef(), br, $r.r] ) | ^( PRINT r= expression[br] (endl= ENDL )? ) | ^( READ lvalue[br.getRef(), br, reg] ) | ^( IF r= expression[br] thenBlock= block[\"THEN\"] (elseBlock= block[\"ELSE\"] )? ) | ^( WHILE r= expression[br] body= block[\"WHILE\"] r2= expression[condBR = new BlockReference($body.end)] ) | ^( DELETE r= expression[br] ) | ^( RETURN (r= expression[br] )? ) | ^( INVOKE id= ID arguments[br.getRef(), br] ) )
            int alt14=9;
            switch ( input.LA(1) ) {
            case BLOCK:
                {
                alt14=1;
                }
                break;
            case ASSIGN:
                {
                alt14=2;
                }
                break;
            case PRINT:
                {
                alt14=3;
                }
                break;
            case READ:
                {
                alt14=4;
                }
                break;
            case IF:
                {
                alt14=5;
                }
                break;
            case WHILE:
                {
                alt14=6;
                }
                break;
            case DELETE:
                {
                alt14=7;
                }
                break;
            case RETURN:
                {
                alt14=8;
                }
                break;
            case INVOKE:
                {
                alt14=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // CFG.g:309:4: freeblock[b, br]
                    {
                    pushFollow(FOLLOW_freeblock_in_statements437);
                    freeblock(b, br);

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // CFG.g:310:4: ^( ASSIGN r= expression[br] lvalue[br.getRef(), br, $r.r] )
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_statements444); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statements452);
                    r=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_lvalue_in_statements459);
                    lvalue(br.getRef(), br, (r!=null?r.r:null));

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // CFG.g:313:4: ^( PRINT r= expression[br] (endl= ENDL )? )
                    {
                    match(input,PRINT,FOLLOW_PRINT_in_statements467); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statements471);
                    r=expression(br);

                    state._fsp--;

                    // CFG.g:313:33: (endl= ENDL )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==ENDL) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // CFG.g:313:33: endl= ENDL
                            {
                            endl=(CommonTree)match(input,ENDL,FOLLOW_ENDL_in_statements476); 

                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                          br.getRef().addInstruction(new PrintInstr((r!=null?r.r:null), endl != null));
                       

                    }
                    break;
                case 4 :
                    // CFG.g:318:2: ^( READ lvalue[br.getRef(), br, reg] )
                    {
                     
                    		Register reg = new Register();
                          br.getRef().addInstruction(new ComputeGlobalInstr(GLOBAL_READ, reg));
                       	br.getRef().addInstruction(new ReadInstr(reg));
                          br.getRef().addInstruction(new LoadGlobalInstr(GLOBAL_READ, reg));
                    	
                    match(input,READ,FOLLOW_READ_in_statements494); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_lvalue_in_statements496);
                    lvalue(br.getRef(), br, reg);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 5 :
                    // CFG.g:326:4: ^( IF r= expression[br] thenBlock= block[\"THEN\"] (elseBlock= block[\"ELSE\"] )? )
                    {

                          String funName = br.getRef().getLabel();
                       
                    match(input,IF,FOLLOW_IF_in_statements512); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statements516);
                    r=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_block_in_statements521);
                    thenBlock=block("THEN");

                    state._fsp--;

                    // CFG.g:329:59: (elseBlock= block[\"ELSE\"] )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==BLOCK) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // CFG.g:329:59: elseBlock= block[\"ELSE\"]
                            {
                            pushFollow(FOLLOW_block_in_statements526);
                            elseBlock=block("ELSE");

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                     
                    		Block cont = new Block(funName + "CONT");
                          cont.addThen(exitBlock);

                    		//addThen adds the continuation for this. So if it's a then without an else
                    		//it "then" continues to the continuation block
                    		(thenBlock!=null?thenBlock.end:null).addThen(cont);
                          if ((elseBlock!=null?elseBlock.body:null) != null)
                             (elseBlock!=null?elseBlock.end:null).addThen(cont);

                    		br.getRef().addThen((thenBlock!=null?thenBlock.body:null));
                    		br.getRef().addElse((elseBlock!=null?elseBlock.body:null) != null ? (elseBlock!=null?elseBlock.body:null) : cont);
                    		br.getRef().appendCondition((r!=null?r.r:null));

                    		// if there is an else block, tell it to "then" go to the continuation
                          br.setRef(cont);
                    	

                    }
                    break;
                case 6 :
                    // CFG.g:348:2: ^( WHILE r= expression[br] body= block[\"WHILE\"] r2= expression[condBR = new BlockReference($body.end)] )
                    {

                          BlockReference condBR;
                       

                          String funName = br.getRef().getLabel();
                       
                    match(input,WHILE,FOLLOW_WHILE_in_statements548); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statements552);
                    r=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_block_in_statements557);
                    body=block("WHILE");

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_statements562);
                    r2=expression(condBR = new BlockReference((body!=null?body.end:null)));

                    state._fsp--;


                    match(input, Token.UP, null); 

                          entryBlock.mLoop = true;
                    		Block cont = new Block(funName + "CONT");
                          cont.addThen(exitBlock);
                    		br.getRef().addThen((body!=null?body.body:null));
                    		br.getRef().addElse(cont);
                    		br.getRef().appendCondition((r!=null?r.r:null));
                    		condBR.getRef().addThen((body!=null?body.body:null));
                    		condBR.getRef().addElse(cont);
                    		condBR.getRef().appendCondition((r2!=null?r2.r:null)); 
                          br.setRef(cont);
                    	

                    }
                    break;
                case 7 :
                    // CFG.g:367:4: ^( DELETE r= expression[br] )
                    {
                    match(input,DELETE,FOLLOW_DELETE_in_statements573); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statements577);
                    r=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          br.getRef().addInstruction(new DelInstr((r!=null?r.r:null)));
                       

                    }
                    break;
                case 8 :
                    // CFG.g:371:4: ^( RETURN (r= expression[br] )? )
                    {
                    match(input,RETURN,FOLLOW_RETURN_in_statements590); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // CFG.g:371:14: (r= expression[br] )?
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( ((LA13_0>=TRUE && LA13_0<=NULL)||LA13_0==INVOKE||LA13_0==NEG||(LA13_0>=DOT && LA13_0<=INTEGER)) ) {
                            alt13=1;
                        }
                        switch (alt13) {
                            case 1 :
                                // CFG.g:371:14: r= expression[br]
                                {
                                pushFollow(FOLLOW_expression_in_statements594);
                                r=expression(br);

                                state._fsp--;


                                }
                                break;

                        }


                        match(input, Token.UP, null); 
                    }

                          if ((r!=null?r.r:null) != null)
                             br.getRef().addInstruction(new StoreRetInstr((r!=null?r.r:null)));

                    		br.getRef().addThen(exitBlock);
                    		br.getRef().setReturn();
                       

                    }
                    break;
                case 9 :
                    // CFG.g:380:4: ^( INVOKE id= ID arguments[br.getRef(), br] )
                    {

                          String funName = br.getRef().getLabel();
                       
                    match(input,INVOKE,FOLLOW_INVOKE_in_statements615); 

                    match(input, Token.DOWN, null); 
                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_statements619); 
                    pushFollow(FOLLOW_arguments_in_statements621);
                    arguments(br.getRef(), br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          if ((id!=null?id.getText():null).equals(entryBlock.getLabel()))
                             entryBlock.mCallsSelf = true;

                          Block fun = funLabels.get((id!=null?id.getText():null));

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

                             cont.addThen(exitBlock);
                             br.getRef().addThen(inlineEntry);
                             inlineExit.addThen(cont);
                             inlineExit.mIsInExit = true;
                             br.setRef(cont);
                          }
                       

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "statements"

    public static class block_return extends TreeRuleReturnScope {
        public Block body = null;
        public Block end = null;
    };

    // $ANTLR start "block"
    // CFG.g:412:1: block[String label] returns [Block body = null, Block end = null] : ^( BLOCK ^( STMTS statements_list[newBlock, bRef] ) ) ;
    public final CFG.block_return block(String label) throws RecognitionException {
        CFG.block_return retval = new CFG.block_return();
        retval.start = input.LT(1);

        try {
            // CFG.g:412:67: ( ^( BLOCK ^( STMTS statements_list[newBlock, bRef] ) ) )
            // CFG.g:413:4: ^( BLOCK ^( STMTS statements_list[newBlock, bRef] ) )
            {

                  Block newBlock = new Block(label);
                  BlockReference bRef = new BlockReference(newBlock);
               
            match(input,BLOCK,FOLLOW_BLOCK_in_block649); 

            match(input, Token.DOWN, null); 
            match(input,STMTS,FOLLOW_STMTS_in_block652); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                pushFollow(FOLLOW_statements_list_in_block654);
                statements_list(newBlock, bRef);

                state._fsp--;


                match(input, Token.UP, null); 
            }

            match(input, Token.UP, null); 

                  retval.body = newBlock;
                  retval.end = bRef.getRef();
               

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "block"


    // $ANTLR start "lvalue"
    // CFG.g:424:1: lvalue[Block b, BlockReference br, Register val] : (id= ID | ^( DOT lval= lvalue_h[br.getRef()] id= ID ) );
    public final void lvalue(Block b, BlockReference br, Register val) throws RecognitionException {
        CommonTree id=null;
        CFG.lvalue_h_return lval = null;


        try {
            // CFG.g:424:51: (id= ID | ^( DOT lval= lvalue_h[br.getRef()] id= ID ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==ID) ) {
                alt15=1;
            }
            else if ( (LA15_0==DOT) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // CFG.g:425:2: id= ID
                    {
                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue676); 

                          //variable name
                          // "i"
                          saveVariable((id!=null?id.getText():null), val, br.getRef());
                       

                    }
                    break;
                case 2 :
                    // CFG.g:432:2: ^( DOT lval= lvalue_h[br.getRef()] id= ID )
                    {
                    match(input,DOT,FOLLOW_DOT_in_lvalue687); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_lvalue_h_in_lvalue691);
                    lval=lvalue_h(br.getRef());

                    state._fsp--;

                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue696); 

                    match(input, Token.UP, null); 

                          //struct (id = field name, lval = base addr)
                          // a.a.a.a."a.i"
                          br.getRef().addInstruction(new StoreaiInstr(val, (lval!=null?lval.r:null), (id!=null?id.getText():null), (lval!=null?lval.s:null)));
                       

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "lvalue"

    public static class lvalue_h_return extends TreeRuleReturnScope {
        public Register r = null;
        public Struct s = null;
    };

    // $ANTLR start "lvalue_h"
    // CFG.g:440:1: lvalue_h[Block blk] returns [Register r = null, Struct s = null] : (id= ID | ^( DOT lval= lvalue_h[blk] id= ID ) );
    public final CFG.lvalue_h_return lvalue_h(Block blk) throws RecognitionException {
        CFG.lvalue_h_return retval = new CFG.lvalue_h_return();
        retval.start = input.LT(1);

        CommonTree id=null;
        CFG.lvalue_h_return lval = null;


        try {
            // CFG.g:440:67: (id= ID | ^( DOT lval= lvalue_h[blk] id= ID ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==ID) ) {
                alt16=1;
            }
            else if ( (LA16_0==DOT) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // CFG.g:441:2: id= ID
                    {
                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue_h718); 

                          //variable name of struct
                          // "a".a.a.a.a.i
                          retval.r = getVariable((id!=null?id.getText():null), blk);
                          retval.s = structTable.get(regTable.getStruct((id!=null?id.getText():null)));
                       

                    }
                    break;
                case 2 :
                    // CFG.g:449:2: ^( DOT lval= lvalue_h[blk] id= ID )
                    {
                    match(input,DOT,FOLLOW_DOT_in_lvalue_h729); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_lvalue_h_in_lvalue_h733);
                    lval=lvalue_h(blk);

                    state._fsp--;

                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue_h738); 

                    match(input, Token.UP, null); 

                          //id = field name, lval = base addr of struct
                          // a."a.a.a".a.i
                          retval.r = new Register();
                          retval.s = structTable.get((lval!=null?lval.s:null).getStructType((id!=null?id.getText():null)));
                          blk.addInstruction(new LoadaiInstr((lval!=null?lval.r:null), (id!=null?id.getText():null), (lval!=null?lval.s:null), retval.r));
                       

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lvalue_h"

    public static class expression_return extends TreeRuleReturnScope {
        public Register r = null;
        public Struct struct = null;
    };

    // $ANTLR start "expression"
    // CFG.g:459:1: expression[BlockReference br] returns [Register r = null, Struct struct = null] : ( ^( AND r1= expression[br] r2= expression[br] ) | ^( OR r1= expression[br] r2= expression[br] ) | ^( EQ r1= expression[br] r2= expression[br] ) | ^( LT r1= expression[br] r2= expression[br] ) | ^( GT r1= expression[br] r2= expression[br] ) | ^( NE r1= expression[br] r2= expression[br] ) | ^( LE r1= expression[br] r2= expression[br] ) | ^( GE r1= expression[br] r2= expression[br] ) | ^( PLUS r1= expression[br] r2= expression[br] ) | ^( MINUS r1= expression[br] r2= expression[br] ) | ^( TIMES r1= expression[br] r2= expression[br] ) | ^( DIVIDE r1= expression[br] r2= expression[br] ) | ^( DOT r1= expression[br] id= ID ) | ^( INVOKE id= ID arguments[br.getRef(), br] ) | ^( NEG r1= expression[br] ) | num= INTEGER | TRUE | FALSE | ^( NOT r1= expression[br] ) | ^( NEW id= ID ) | NULL | id= ID );
    public final CFG.expression_return expression(BlockReference br) throws RecognitionException {
        CFG.expression_return retval = new CFG.expression_return();
        retval.start = input.LT(1);

        CommonTree id=null;
        CommonTree num=null;
        CFG.expression_return r1 = null;

        CFG.expression_return r2 = null;


        try {
            // CFG.g:460:53: ( ^( AND r1= expression[br] r2= expression[br] ) | ^( OR r1= expression[br] r2= expression[br] ) | ^( EQ r1= expression[br] r2= expression[br] ) | ^( LT r1= expression[br] r2= expression[br] ) | ^( GT r1= expression[br] r2= expression[br] ) | ^( NE r1= expression[br] r2= expression[br] ) | ^( LE r1= expression[br] r2= expression[br] ) | ^( GE r1= expression[br] r2= expression[br] ) | ^( PLUS r1= expression[br] r2= expression[br] ) | ^( MINUS r1= expression[br] r2= expression[br] ) | ^( TIMES r1= expression[br] r2= expression[br] ) | ^( DIVIDE r1= expression[br] r2= expression[br] ) | ^( DOT r1= expression[br] id= ID ) | ^( INVOKE id= ID arguments[br.getRef(), br] ) | ^( NEG r1= expression[br] ) | num= INTEGER | TRUE | FALSE | ^( NOT r1= expression[br] ) | ^( NEW id= ID ) | NULL | id= ID )
            int alt17=22;
            switch ( input.LA(1) ) {
            case AND:
                {
                alt17=1;
                }
                break;
            case OR:
                {
                alt17=2;
                }
                break;
            case EQ:
                {
                alt17=3;
                }
                break;
            case LT:
                {
                alt17=4;
                }
                break;
            case GT:
                {
                alt17=5;
                }
                break;
            case NE:
                {
                alt17=6;
                }
                break;
            case LE:
                {
                alt17=7;
                }
                break;
            case GE:
                {
                alt17=8;
                }
                break;
            case PLUS:
                {
                alt17=9;
                }
                break;
            case MINUS:
                {
                alt17=10;
                }
                break;
            case TIMES:
                {
                alt17=11;
                }
                break;
            case DIVIDE:
                {
                alt17=12;
                }
                break;
            case DOT:
                {
                alt17=13;
                }
                break;
            case INVOKE:
                {
                alt17=14;
                }
                break;
            case NEG:
                {
                alt17=15;
                }
                break;
            case INTEGER:
                {
                alt17=16;
                }
                break;
            case TRUE:
                {
                alt17=17;
                }
                break;
            case FALSE:
                {
                alt17=18;
                }
                break;
            case NOT:
                {
                alt17=19;
                }
                break;
            case NEW:
                {
                alt17=20;
                }
                break;
            case NULL:
                {
                alt17=21;
                }
                break;
            case ID:
                {
                alt17=22;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // CFG.g:461:2: ^( AND r1= expression[br] r2= expression[br] )
                    {
                    match(input,AND,FOLLOW_AND_in_expression761); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression765);
                    r1=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression770);
                    r2=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          retval.r = new Register();
                          br.getRef().addInstruction(new AndInstr((r1!=null?r1.r:null), (r2!=null?r2.r:null), retval.r));
                       

                    }
                    break;
                case 2 :
                    // CFG.g:466:4: ^( OR r1= expression[br] r2= expression[br] )
                    {
                    match(input,OR,FOLLOW_OR_in_expression783); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression787);
                    r1=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression792);
                    r2=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          retval.r = new Register();
                          br.getRef().addInstruction(new OrInstr((r1!=null?r1.r:null), (r2!=null?r2.r:null), retval.r));
                       

                    }
                    break;
                case 3 :
                    // CFG.g:471:4: ^( EQ r1= expression[br] r2= expression[br] )
                    {
                    match(input,EQ,FOLLOW_EQ_in_expression805); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression809);
                    r1=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression814);
                    r2=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          retval.r = makeMoveAny("eq", br.getRef(), (r1!=null?r1.r:null), (r2!=null?r2.r:null));
                       

                    }
                    break;
                case 4 :
                    // CFG.g:475:4: ^( LT r1= expression[br] r2= expression[br] )
                    {
                    match(input,LT,FOLLOW_LT_in_expression827); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression831);
                    r1=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression836);
                    r2=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          retval.r = makeMoveAny("lt", br.getRef(), (r1!=null?r1.r:null), (r2!=null?r2.r:null));
                       

                    }
                    break;
                case 5 :
                    // CFG.g:479:4: ^( GT r1= expression[br] r2= expression[br] )
                    {
                    match(input,GT,FOLLOW_GT_in_expression849); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression853);
                    r1=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression858);
                    r2=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          retval.r = makeMoveAny("gt", br.getRef(), (r1!=null?r1.r:null), (r2!=null?r2.r:null));
                       

                    }
                    break;
                case 6 :
                    // CFG.g:483:4: ^( NE r1= expression[br] r2= expression[br] )
                    {
                    match(input,NE,FOLLOW_NE_in_expression871); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression875);
                    r1=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression880);
                    r2=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          retval.r = makeMoveAny("ne", br.getRef(), (r1!=null?r1.r:null), (r2!=null?r2.r:null));
                       

                    }
                    break;
                case 7 :
                    // CFG.g:487:4: ^( LE r1= expression[br] r2= expression[br] )
                    {
                    match(input,LE,FOLLOW_LE_in_expression893); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression897);
                    r1=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression902);
                    r2=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          retval.r = makeMoveAny("le", br.getRef(), (r1!=null?r1.r:null), (r2!=null?r2.r:null));
                       

                    }
                    break;
                case 8 :
                    // CFG.g:491:4: ^( GE r1= expression[br] r2= expression[br] )
                    {
                    match(input,GE,FOLLOW_GE_in_expression915); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression919);
                    r1=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression924);
                    r2=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          retval.r = makeMoveAny("ge", br.getRef(), (r1!=null?r1.r:null), (r2!=null?r2.r:null));
                       

                    }
                    break;
                case 9 :
                    // CFG.g:495:4: ^( PLUS r1= expression[br] r2= expression[br] )
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_expression937); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression941);
                    r1=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression946);
                    r2=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          retval.r = new Register();
                          br.getRef().addInstruction(new AddInstr((r1!=null?r1.r:null), (r2!=null?r2.r:null), retval.r));
                       

                    }
                    break;
                case 10 :
                    // CFG.g:500:4: ^( MINUS r1= expression[br] r2= expression[br] )
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_expression959); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression963);
                    r1=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression968);
                    r2=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          retval.r = new Register();
                          br.getRef().addInstruction(new SubInstr((r1!=null?r1.r:null), (r2!=null?r2.r:null), retval.r));
                       

                    }
                    break;
                case 11 :
                    // CFG.g:505:4: ^( TIMES r1= expression[br] r2= expression[br] )
                    {
                    match(input,TIMES,FOLLOW_TIMES_in_expression981); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression985);
                    r1=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression990);
                    r2=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          retval.r = new Register();
                          br.getRef().addInstruction(new MultInstr((r1!=null?r1.r:null), (r2!=null?r2.r:null), retval.r));
                       

                    }
                    break;
                case 12 :
                    // CFG.g:510:4: ^( DIVIDE r1= expression[br] r2= expression[br] )
                    {
                    match(input,DIVIDE,FOLLOW_DIVIDE_in_expression1003); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1007);
                    r1=expression(br);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1012);
                    r2=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          retval.r = new Register();
                          br.getRef().addInstruction(new DivInstr((r1!=null?r1.r:null), (r2!=null?r2.r:null), retval.r));
                       

                    }
                    break;
                case 13 :
                    // CFG.g:515:4: ^( DOT r1= expression[br] id= ID )
                    {
                    match(input,DOT,FOLLOW_DOT_in_expression1025); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1029);
                    r1=expression(br);

                    state._fsp--;

                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1034); 

                    match(input, Token.UP, null); 

                          retval.r = new Register();
                          String type = (r1!=null?r1.struct:null).getStructType((id!=null?id.getText():null));
                          if (type != null)
                             retval.struct = structTable.get(type);
                          br.getRef().addInstruction(new LoadaiInstr((r1!=null?r1.r:null), (id!=null?id.getText():null), (r1!=null?r1.struct:null), retval.r));
                       

                    }
                    break;
                case 14 :
                    // CFG.g:524:4: ^( INVOKE id= ID arguments[br.getRef(), br] )
                    {

                          String funName = br.getRef().getLabel();
                       
                    match(input,INVOKE,FOLLOW_INVOKE_in_expression1053); 

                    match(input, Token.DOWN, null); 
                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1057); 
                    pushFollow(FOLLOW_arguments_in_expression1059);
                    arguments(br.getRef(), br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          retval.r = new Register();
                          if ((id!=null?id.getText():null).equals(entryBlock.getLabel()))
                             entryBlock.mCallsSelf = true;

                          Block fun = funLabels.get((id!=null?id.getText():null));

                          if (!fun.mInline) {
                             br.getRef().addInstruction(new CallInstr(fun.getFullLabel()));
                             br.getRef().addInstruction(new LoadRetInstr(retval.r));
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

                             cont.addThen(exitBlock);
                             br.getRef().addThen(inlineEntry);
                             inlineExit.addThen(cont);
                             inlineExit.mIsInExit = true;
                             cont.addInstruction(new LoadRetInstr(retval.r));
                             br.setRef(cont);
                          }
                       

                    }
                    break;
                case 15 :
                    // CFG.g:557:4: ^( NEG r1= expression[br] )
                    {
                    match(input,NEG,FOLLOW_NEG_in_expression1072); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1076);
                    r1=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          Register neg = new Register();
                          br.getRef().addInstruction(new LoadiInstr(neg, -1));
                          retval.r = new Register();
                          br.getRef().addInstruction(new MultInstr(neg, (r1!=null?r1.r:null), retval.r));
                       

                    }
                    break;
                case 16 :
                    // CFG.g:564:4: num= INTEGER
                    {
                    num=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_expression1090); 

                          retval.r = new Register();
                          br.getRef().addInstruction(new LoadiInstr(retval.r, (num!=null?Integer.valueOf(num.getText()):0)));
                       

                    }
                    break;
                case 17 :
                    // CFG.g:569:4: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_expression1100); 

                          retval.r = new Register();
                          br.getRef().addInstruction(new LoadiInstr(retval.r, TRUE_VAL));
                       

                    }
                    break;
                case 18 :
                    // CFG.g:574:4: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_expression1110); 

                          retval.r = new Register();
                          br.getRef().addInstruction(new LoadiInstr(retval.r, FALSE_VAL));
                       

                    }
                    break;
                case 19 :
                    // CFG.g:579:4: ^( NOT r1= expression[br] )
                    {
                    match(input,NOT,FOLLOW_NOT_in_expression1121); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1125);
                    r1=expression(br);

                    state._fsp--;


                    match(input, Token.UP, null); 

                          retval.r = new Register();
                          br.getRef().addInstruction(new LoadiInstr(retval.r, TRUE_VAL));
                          br.getRef().addInstruction(new CompiInstr((r1!=null?r1.r:null), TRUE_VAL));
                          br.getRef().addInstruction(new MovanyInstr("eq", FALSE_VAL, retval.r));
                       

                    }
                    break;
                case 20 :
                    // CFG.g:586:4: ^( NEW id= ID )
                    {
                    match(input,NEW,FOLLOW_NEW_in_expression1138); 

                    match(input, Token.DOWN, null); 
                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1142); 

                    match(input, Token.UP, null); 

                          retval.r = new Register();
                          br.getRef().addInstruction(new NewInstr(structTable.get((id!=null?id.getText():null)), retval.r));
                       

                    }
                    break;
                case 21 :
                    // CFG.g:591:4: NULL
                    {
                    match(input,NULL,FOLLOW_NULL_in_expression1153); 

                          retval.r = new Register();
                          br.getRef().addInstruction(new LoadiInstr(retval.r, NULL_VAL));
                       

                    }
                    break;
                case 22 :
                    // CFG.g:596:4: id= ID
                    {
                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1165); 

                          retval.r = getVariable((id!=null?id.getText():null), br.getRef());
                          String struct;
                          if ((struct = regTable.getStruct((id!=null?id.getText():null))) != null)
                             retval.struct = structTable.get(regTable.getStruct((id!=null?id.getText():null)));
                       

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expression"


    // $ANTLR start "arguments"
    // CFG.g:605:1: arguments[Block b, BlockReference br] : ^( ARGS ( arg_expression[regs, br] )* ) ;
    public final void arguments(Block b, BlockReference br) throws RecognitionException {
        try {
            // CFG.g:605:40: ( ^( ARGS ( arg_expression[regs, br] )* ) )
            // CFG.g:606:4: ^( ARGS ( arg_expression[regs, br] )* )
            {

                  ArrayList<Register> regs = new ArrayList<Register>();
               
            match(input,ARGS,FOLLOW_ARGS_in_arguments1188); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // CFG.g:609:9: ( arg_expression[regs, br] )*
                loop18:
                do {
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( ((LA18_0>=TRUE && LA18_0<=NULL)||LA18_0==INVOKE||LA18_0==NEG||(LA18_0>=DOT && LA18_0<=INTEGER)) ) {
                        alt18=1;
                    }


                    switch (alt18) {
                	case 1 :
                	    // CFG.g:609:9: arg_expression[regs, br]
                	    {
                	    pushFollow(FOLLOW_arg_expression_in_arguments1190);
                	    arg_expression(regs, br);

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop18;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

                  for (int i = 0; i < regs.size(); i ++) {
                     br.getRef().addInstruction(new StoreoutInstr(regs.get(i), i));
                  }

            		numArgs = new Integer(regs.size());
               

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "arguments"


    // $ANTLR start "arg_expression"
    // CFG.g:619:1: arg_expression[ArrayList<Register> regs, BlockReference br] : reg= expression[br] ;
    public final void arg_expression(ArrayList<Register> regs, BlockReference br) throws RecognitionException {
        CFG.expression_return reg = null;


        try {
            // CFG.g:619:62: (reg= expression[br] )
            // CFG.g:620:2: reg= expression[br]
            {
            pushFollow(FOLLOW_expression_in_arg_expression1212);
            reg=expression(br);

            state._fsp--;


                  regs.add((reg!=null?reg.r:null));
               

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "arg_expression"

    // Delegated rules


 

    public static final BitSet FOLLOW_PROGRAM_in_generate46 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TYPES_in_generate65 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_types_sub_in_generate68 = new BitSet(new long[]{0x0000000000000018L});
    public static final BitSet FOLLOW_DECLS_in_generate89 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declarations_in_generate92 = new BitSet(new long[]{0x0000000008000008L});
    public static final BitSet FOLLOW_FUNCS_in_generate114 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_functions_in_generate116 = new BitSet(new long[]{0x0000000000000088L});
    public static final BitSet FOLLOW_STRUCT_in_types_sub139 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_types_sub143 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_struct_decl_in_types_sub145 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_DECL_in_struct_decl163 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TYPE_in_struct_decl166 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_struct_decl170 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_struct_decl175 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DECL_in_type_decl194 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TYPE_in_type_decl197 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_type_decl201 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_type_decl206 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INT_in_type226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_type232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCT_in_type239 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_type243 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VOID_in_type255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLLIST_in_declarations270 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TYPE_in_declarations273 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_declarations277 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_id_list_in_declarations280 = new BitSet(new long[]{0x0100000000000008L});
    public static final BitSet FOLLOW_ID_in_id_list299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUN_in_functions320 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_functions327 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_PARAMS_in_functions337 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_decl_in_functions339 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_RETTYPE_in_functions348 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_functions350 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DECLS_in_functions357 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declarations_in_functions359 = new BitSet(new long[]{0x0000000008000008L});
    public static final BitSet FOLLOW_STMTS_in_functions372 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statements_list_in_functions374 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_statements_in_statements_list397 = new BitSet(new long[]{0x000002014001DA02L});
    public static final BitSet FOLLOW_BLOCK_in_freeblock414 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STMTS_in_freeblock417 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statements_in_freeblock419 = new BitSet(new long[]{0x000002014001DA08L});
    public static final BitSet FOLLOW_freeblock_in_statements437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_statements444 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statements452 = new BitSet(new long[]{0x0100040000000000L});
    public static final BitSet FOLLOW_lvalue_in_statements459 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PRINT_in_statements467 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statements471 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_ENDL_in_statements476 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_READ_in_statements494 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_lvalue_in_statements496 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_statements512 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statements516 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_block_in_statements521 = new BitSet(new long[]{0x0000000040000008L});
    public static final BitSet FOLLOW_block_in_statements526 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_WHILE_in_statements548 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statements552 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_block_in_statements557 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_statements562 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DELETE_in_statements573 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statements577 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RETURN_in_statements590 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statements594 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INVOKE_in_statements615 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_statements619 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_arguments_in_statements621 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_in_block649 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STMTS_in_block652 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statements_list_in_block654 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_lvalue676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_lvalue687 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_lvalue_h_in_lvalue691 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_lvalue696 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_lvalue_h718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_lvalue_h729 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_lvalue_h_in_lvalue_h733 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_lvalue_h738 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AND_in_expression761 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression765 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression770 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OR_in_expression783 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression787 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression792 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQ_in_expression805 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression809 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression814 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expression827 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression831 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression836 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expression849 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression853 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression858 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NE_in_expression871 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression875 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression880 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LE_in_expression893 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression897 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression902 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GE_in_expression915 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression919 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression924 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PLUS_in_expression937 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression941 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression946 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MINUS_in_expression959 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression963 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression968 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TIMES_in_expression981 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression985 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression990 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIVIDE_in_expression1003 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1007 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression1012 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_expression1025 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1029 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_expression1034 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INVOKE_in_expression1053 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expression1057 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_arguments_in_expression1059 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEG_in_expression1072 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1076 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INTEGER_in_expression1090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_expression1100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_expression1110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_expression1121 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1125 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEW_in_expression1138 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expression1142 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NULL_in_expression1153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_expression1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ARGS_in_arguments1188 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_arg_expression_in_arguments1190 = new BitSet(new long[]{0x03FFFC05001E0008L});
    public static final BitSet FOLLOW_expression_in_arg_expression1212 = new BitSet(new long[]{0x0000000000000002L});

}