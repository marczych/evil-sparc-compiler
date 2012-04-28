tree grammar TypeCheck;

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

}

@members {
	private enum Declaration { GLOBAL, LOCAL, PARAM }

	private boolean DEBUG = false;

	private boolean hasVerified = true;

	public void debug(Object msg) {
		if(DEBUG) {
			System.out.println(msg);
		}
	}

	public void error(String err) {
		System.err.println("ERROR ERROR: "+err);
		hasVerified = false;
	}

	private boolean hasMain = false;
}

verify [MyBoolean verified]
@init {
	StructTypes sTypes = new StructTypes();
	SymbolTable sTable = new SymbolTable();
}
 :

 ^(PROGRAM { debug("Matched program"); }
               ^(TYPES types_sub[sTypes, sTable]*) 
					{ 
						debug("After types"); 
					}
               ^(DECLS declarations[sTypes, sTable, Declaration.GLOBAL]*) 
					{ 
						debug("After decs");
					}
               ^(FUNCS functions[sTypes, sTable]+) 
					{ debug("After funcs"); }
					{ debug(sTable);})
					{ debug(sTypes);}
					{ 
						if(!hasMain) 
						{ error("No main function!"); } 
						else {
							TypeFun fun = sTable.findFunction("main");
							if(!(fun.getReturnType() instanceof TypeInt)) {
								error("Main needs to return an Int and you defined it to return "+fun.getReturnType());
							}
						}
					}
	{
		verified.change(hasVerified);
	}
;

types_sub [StructTypes sTypes, SymbolTable sTable] :
	{
		Hashtable<String, EVILType> fieldList = new Hashtable<String, EVILType>();
	}
   ^(STRUCT i=ID 
		{
			if(sTypes.findStruct($i.text) != null) {
				error($i.text + " already defined");
			}
			else {
				sTypes.addStruct($i.text, fieldList);
			}
		}  
		struct_decl[sTypes, sTable, fieldList]+)
;

struct_decl [StructTypes sTypes, SymbolTable sTable, Hashtable<String, EVILType> fieldList] :
   ^(DECL ^(TYPE t=type[sTypes]) i=ID)
	{
		fieldList.put($i.text, $t.t);
	}
;


type_decl [StructTypes sTypes, SymbolTable sTable, Declaration decl] :
   ^(DECL ^(TYPE t=type[sTypes]) i=ID)
	{
			switch(decl) {
				case GLOBAL:
					sTable.addGlobalVar($i.text, $t.t);
				break;
				
				case LOCAL:
					sTable.addLocalVar($i.text, $t.t);
				break;

				case PARAM:
					sTable.addParam($i.text, $t.t);
				break;

				default:
					error("LOLWUT?");
				break;
			}
	}
;

type [StructTypes sTypes] returns [EVILType t = null] :
   INT 
	{
		$t = new TypeInt();
		debug("INT");
	} 
	| BOOL {$t = new TypeBool();} 
	| ^(STRUCT i=ID)
	{
		if(sTypes.findStruct($i.text) == null) {
			error("Struct "+$i.text+" not defined");
		}
		else {
			$t = new TypeStruct($i.text);
		}
	}
	| VOID {$t = new TypeNull();}
;

declarations [StructTypes sTypes, SymbolTable sTable, Declaration decl] :
   ^(DECLLIST 
		^(TYPE t=type[sTypes]) 
		id_list[sTable, $t.t, decl]+)
;

id_list [SymbolTable sTable, EVILType t, Declaration decl] :
   id=ID
	{
		if($t != null) {
			switch(decl) {
				case GLOBAL:
					sTable.addGlobalVar($id.text, $t);
				break;
				
				case LOCAL:
					sTable.addLocalVar($id.text, $t);
					TypeFun fun = sTable.findCurrentFunction();
					if(fun.isParam($id.text)) {
						error($id.text +" is a parameter of the function");
					}
				break;

				case PARAM:
					sTable.addParam($id.text, $t);
				break;

				default:
					error("LOLWUT?");
				break;
			}
		}
	}
;

functions [StructTypes sTypes, SymbolTable sTable]:
{debug("functions");}
   ^(FUN 
		id=ID 
			{
				sTable.newFunction($id.text);
				if($id.text.equals("main")) {
					hasMain = true;
				}
			} 
		{ debug(sTable);}
		^(PARAMS type_decl[sTypes, sTable, Declaration.PARAM]*) 
		^(RETTYPE t=type[sTypes]) 
		{
			TypeFun fun = sTable.findCurrentFunction();
			fun.setReturnType($t.t);
			debug("Setting function "+$id.text+" to return type "+$t.t+" now has "+fun.getReturnType());
		} 
		^(DECLS declarations[sTypes, sTable, Declaration.LOCAL]*) 
		{ MyBoolean hasReturned = new MyBoolean(); }
		^(STMTS statements[sTypes, sTable, hasReturned]*))
		{debug($id);}
		{
			if(!(fun.getReturnType() instanceof TypeNull) && hasReturned.isFalse()) {
				error("Didn't return through all branches of the program");
			}
		}
;

statements [StructTypes sTypes, SymbolTable sTable, MyBoolean returned] :
	block[sTypes, sTable, returned]
	{debug("Block");}
	| ^(ASSIGN t=expression[sTypes, sTable] l=expression[sTypes, sTable])
	{
		if(!$l.t.isSameType($t.t)) {
			error("In assignment types should be the same given "+$l.t+" as l-value and "+$t.t+" as r-value");
		}
	}
	{debug("Assign");}
	| ^(PRINT t=expression[sTypes, sTable] (ENDL?))
	{
		if(!($t.t instanceof TypeInt)) {
			error("PRINT needs an Int and given "+$t.t);
		}
	}
	{debug("Print");}
	| ^(READ l=expression[sTypes, sTable])
	{
		if(!($l.t instanceof TypeInt)) {
			error("Read takes an Int given "+$l.t);
		}
	}
	{debug("Read");}
	| ^(IF t=expression[sTypes, sTable] {MyBoolean blk1 = new MyBoolean();} block[sTypes, sTable, blk1] {MyBoolean blk2 = new MyBoolean();} (block[sTypes, sTable, blk2])?)
	{
		if(!($t.t instanceof TypeBool)) {
			error("Guard on IF statement must be Bool you gave "+$t.t);
		}
		if(blk2.isTrue() && blk1.isTrue()) {
			$returned.setTrue();
		}
	}
	{debug("If");}
	| ^(WHILE t=expression[sTypes, sTable] block[sTypes, sTable, returned] expression[sTypes, sTable])
	{
		if(!($t.t instanceof TypeBool)) {
			error("Guard on WHILE statement must be Bool you gave "+$t.t);
		}
	}
	{debug("While");}
	| ^(DELETE t=expression[sTypes, sTable])
	{
		if(!($t.t instanceof TypeStruct)) {
			error("DELETE needs a Struct and given "+$t.t);
		}
	}
	{debug("Delete");}
	| ^(RETURN (t=expression[sTypes, sTable])?)
	{
		TypeFun fun = sTable.findCurrentFunction();
		if($t.t != null) {
			if(!$t.t.isSameType(fun.getReturnType())) {
				error("This function returns "+fun.getReturnType()+" you tried to return "+$t.t);
			}
		}
		else if (!(fun.getReturnType() instanceof TypeNull)) {
			error("This function returns "+fun.getReturnType()+ 
				" and you didn't return anything.");
		}

		returned.setTrue();
	}
	{debug("Return");}
	| ^(INVOKE i=ID a=arguments[sTypes, sTable])
	{
		TypeFun fun = sTable.findFunction($i.text);
		if(!fun.checkArgs($a.list)) {
			error("Error with the arguments to function");
		}
	}
	{debug("Invoke");}
;

block [StructTypes sTypes, SymbolTable sTable, MyBoolean returned] :
	^(BLOCK ^(STMTS (statements[sTypes, sTable, returned])*))
;

expression [StructTypes sTypes, SymbolTable sTable] returns [EVILType t = null] :
	^(AND t1=expression[sTypes, sTable] t2=expression[sTypes, sTable])
	{
		if($t1.t instanceof TypeBool && $t2.t instanceof TypeBool) {
			$t = new TypeBool();
		}
		else {
			error("AND takes a Bool and a Bool and you gave: "+$t1.t+ " and "+$t2.t);
		}
	}
	{debug("AND");}
	| ^(OR t1=expression[sTypes, sTable] t2=expression[sTypes, sTable])
	{
		if($t1.t instanceof TypeBool && $t2.t instanceof TypeBool) {
			$t = new TypeBool();
		}
		else {
			error("OR takes a Bool and a Bool and you gave: "+$t1.t+ " and "+$t2.t);
		}
	}
	| ^(EQ t1=expression[sTypes, sTable] t2=expression[sTypes, sTable])
	{
		if($t2.t.isSameType($t1.t)) {
			$t = new TypeBool();
		}
		else {
			error("EQ takes an Int and an Int and you gave: "+$t1.t+" and "+$t2.t);
		}
	}
	| ^(LT t1=expression[sTypes, sTable] t2=expression[sTypes, sTable])
	{
		if($t1.t instanceof TypeInt && $t2.t instanceof TypeInt) {
			$t = new TypeBool();
		}
		else {
			error("LT takes an Int and an Int and you gave: "+$t1.t+" and "+$t2.t);
		}
	}
	{debug("LT");}
	| ^(GT t1=expression[sTypes, sTable] t2=expression[sTypes, sTable])
	{
		if($t1.t instanceof TypeInt && $t2.t instanceof TypeInt) {
			$t = new TypeBool();
		}
		else {
			error("GT takes an Int and an Int and you gave: "+$t1.t+" and "+$t2.t);
		}
	}
	{debug("GT");}
	| ^(NE t1=expression[sTypes, sTable] t2=expression[sTypes, sTable])
	{
		if($t2.t.isSameType($t1.t)) {
			$t = new TypeBool();
		}
		else {
			error("NE takes an Int and an Int and you gave: "+$t1.t+" and "+$t2.t);
		}
	}
	| ^(LE t1=expression[sTypes, sTable] t2=expression[sTypes, sTable])
	{
		if($t1.t instanceof TypeInt && $t2.t instanceof TypeInt) {
			$t = new TypeBool();
		}
		else {
			error("LE takes an Int and an Int and you gave: "+$t1.t+" and "+$t2.t);
		}
	}
	| ^(GE t1=expression[sTypes, sTable] t2=expression[sTypes, sTable])
	{
		if($t1.t instanceof TypeInt && $t2.t instanceof TypeInt) {
			$t = new TypeBool();
		}
		else {
			error("GE takes an Int and an Int and you gave: "+$t1.t+" and "+$t2.t);
		}
	}
	| ^(PLUS t1=expression[sTypes, sTable] t2=expression[sTypes, sTable])
	{
		if($t1.t instanceof TypeInt && $t2.t instanceof TypeInt) {
			$t = new TypeInt();
		}
		else {
			error("PLUS takes an Int and an Int and you gave: "+$t1.t+" and "+$t2.t);
		}
	}
	| ^(MINUS t1=expression[sTypes, sTable] t2=expression[sTypes, sTable])
	{
		if($t1.t instanceof TypeInt && $t2.t instanceof TypeInt) {
			$t = new TypeInt();
		}
		else {
			error("MINUS takes an Int and an Int and you gave: "+$t1.t+" and "+$t2.t);
		}
	}
	| ^(TIMES t1=expression[sTypes, sTable] t2=expression[sTypes, sTable])
	{
		if($t1.t instanceof TypeInt && $t2.t instanceof TypeInt) {
			$t = new TypeInt();
		}
		else {
			error("TIMES takes an Int and an Int and you gave: "+$t1.t+" and "+$t2.t);
		}
	}
	{debug("TIMES");}
	| ^(DIVIDE t1=expression[sTypes, sTable] t2=expression[sTypes, sTable])
	{
		if($t1.t instanceof TypeInt && $t2.t instanceof TypeInt) {
			$t = new TypeInt();
		}
		else {
			error("DIVIDE takes an Int and an Int and you gave: "+$t1.t+" and "+$t2.t);
		}
	}
	|
	^(DOT l=expression[sTypes, sTable] id=ID)
	{
		if(!($l.t instanceof TypeStruct)) {
			error("Should be a Struct but is a "+$l.t);
		}
		else {
		TypeStruct struct = (TypeStruct) $l.t;
		Hashtable<String, EVILType> table = sTypes.findStruct(struct.name);
		$t = table.get($id.text);
		}
		debug("LVALUE DOT: "+$id.text);
	}
	
	| ^(INVOKE i=ID a=arguments[sTypes, sTable])
	{
		debug("INVOKE IN EXPRESSIONS "+$i.text);
		TypeFun fun = sTable.findFunction($i.text);
		debug($a.list);
		if(!fun.checkArgs($a.list)) {
			error("Error with the arguments to function");
		}

		$t = fun.getReturnType();
	}
	| ^(NEG t1=expression[sTypes, sTable])
	{
		if($t1.t instanceof TypeInt) {
			$t = new TypeInt();
		}
		else {
			error("NEG takes an Int and you gave: "+$t1.t);
		}
	}
	| INTEGER
	{
		$t = new TypeInt();
	}
	{debug("INTEGER");}
	| TRUE
	{
		$t = new TypeBool();
	}
	| FALSE
	{
		$t = new TypeBool();
	}
	| ^(NOT t1=expression[sTypes, sTable])
	{
		if($t1.t instanceof TypeBool) {
			$t = new TypeBool();
		}
		else {
			error("NOT takes a bool and you gave: "+$t1.t);
		}
	}
	| ^(NEW i=ID)
	{
		if(sTypes.findStruct($i.text) != null) {
			$t = new TypeStruct($i.text);
		}
		else {
			error("Struct "+$i.text+" not found!");
		}
	}
	| NULL
	{
		$t = new TypeNull();
	}
	| i=ID
	{
		$t = sTable.findVariable($i.text);
	}
	{debug("ID");}
;

ids_list [ArrayList<String> list] :
	id=ID
	{
	$list.add($id.text);
	}
;

arguments [StructTypes sTypes, SymbolTable sTable] returns [ArrayList<EVILType> list = null] :
	{list = new ArrayList<EVILType>();}
	arg_list[sTypes, sTable, list]
;

arg_list [StructTypes sTypes, SymbolTable sTable, ArrayList<EVILType> list] :
	^(ARGS arg_expression[sTypes, sTable, list]*)
;

arg_expression [StructTypes sTypes, SymbolTable sTable, ArrayList<EVILType> list] :
	t=expression[sTypes, sTable]
	{
		list.add($t.t);
	}
;
