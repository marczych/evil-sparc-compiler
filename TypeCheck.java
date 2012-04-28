// $ANTLR 3.3 Nov 30, 2010 12:50:56 TypeCheck.g 2012-04-28 14:17:36

   import java.util.Map;
   import java.util.HashMap;
   import java.util.Vector;
   import java.util.Iterator;
	import java.util.Hashtable;



import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TypeCheck extends TreeParser {
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


        public TypeCheck(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public TypeCheck(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return TypeCheck.tokenNames; }
    public String getGrammarFileName() { return "TypeCheck.g"; }


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



    // $ANTLR start "verify"
    // TypeCheck.g:40:1: verify[MyBoolean verified] : ^( PROGRAM ^( TYPES ( types_sub[sTypes, sTable] )* ) ^( DECLS ( declarations[sTypes, sTable, Declaration.GLOBAL] )* ) ^( FUNCS ( functions[sTypes, sTable] )+ ) ) ;
    public final void verify(MyBoolean verified) throws RecognitionException {

        	StructTypes sTypes = new StructTypes();
        	SymbolTable sTable = new SymbolTable();

        try {
            // TypeCheck.g:45:2: ( ^( PROGRAM ^( TYPES ( types_sub[sTypes, sTable] )* ) ^( DECLS ( declarations[sTypes, sTable, Declaration.GLOBAL] )* ) ^( FUNCS ( functions[sTypes, sTable] )+ ) ) )
            // TypeCheck.g:47:2: ^( PROGRAM ^( TYPES ( types_sub[sTypes, sTable] )* ) ^( DECLS ( declarations[sTypes, sTable, Declaration.GLOBAL] )* ) ^( FUNCS ( functions[sTypes, sTable] )+ ) )
            {
            match(input,PROGRAM,FOLLOW_PROGRAM_in_verify56); 

             debug("Matched program"); 

            match(input, Token.DOWN, null); 
            match(input,TYPES,FOLLOW_TYPES_in_verify76); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // TypeCheck.g:48:24: ( types_sub[sTypes, sTable] )*
                loop1:
                do {
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==STRUCT) ) {
                        alt1=1;
                    }


                    switch (alt1) {
                	case 1 :
                	    // TypeCheck.g:48:24: types_sub[sTypes, sTable]
                	    {
                	    pushFollow(FOLLOW_types_sub_in_verify78);
                	    types_sub(sTypes, sTable);

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop1;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }
             
            						debug("After types"); 
            					
            match(input,DECLS,FOLLOW_DECLS_in_verify107); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // TypeCheck.g:52:24: ( declarations[sTypes, sTable, Declaration.GLOBAL] )*
                loop2:
                do {
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==DECLLIST) ) {
                        alt2=1;
                    }


                    switch (alt2) {
                	case 1 :
                	    // TypeCheck.g:52:24: declarations[sTypes, sTable, Declaration.GLOBAL]
                	    {
                	    pushFollow(FOLLOW_declarations_in_verify109);
                	    declarations(sTypes, sTable, Declaration.GLOBAL);

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop2;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }
             
            						debug("After decs");
            					
            match(input,FUNCS,FOLLOW_FUNCS_in_verify138); 

            match(input, Token.DOWN, null); 
            // TypeCheck.g:56:24: ( functions[sTypes, sTable] )+
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
            	    // TypeCheck.g:56:24: functions[sTypes, sTable]
            	    {
            	    pushFollow(FOLLOW_functions_in_verify140);
            	    functions(sTypes, sTable);

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
             debug("After funcs"); 
             debug(sTable);

            match(input, Token.UP, null); 
             debug(sTypes);
             
            						if(!hasMain) 
            						{ error("No main function!"); } 
            						else {
            							TypeFun fun = sTable.findFunction("main");
            							if(!(fun.getReturnType() instanceof TypeInt)) {
            								error("Main needs to return an Int and you defined it to return "+fun.getReturnType());
            							}
            						}
            					

            		verified.change(hasVerified);
            	

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
    // $ANTLR end "verify"


    // $ANTLR start "types_sub"
    // TypeCheck.g:75:1: types_sub[StructTypes sTypes, SymbolTable sTable] : ^( STRUCT i= ID ( struct_decl[sTypes, sTable, fieldList] )+ ) ;
    public final void types_sub(StructTypes sTypes, SymbolTable sTable) throws RecognitionException {
        CommonTree i=null;

        try {
            // TypeCheck.g:75:52: ( ^( STRUCT i= ID ( struct_decl[sTypes, sTable, fieldList] )+ ) )
            // TypeCheck.g:76:2: ^( STRUCT i= ID ( struct_decl[sTypes, sTable, fieldList] )+ )
            {

            		Hashtable<String, EVILType> fieldList = new Hashtable<String, EVILType>();
            	
            match(input,STRUCT,FOLLOW_STRUCT_in_types_sub194); 

            match(input, Token.DOWN, null); 
            i=(CommonTree)match(input,ID,FOLLOW_ID_in_types_sub198); 

            			if(sTypes.findStruct((i!=null?i.getText():null)) != null) {
            				error((i!=null?i.getText():null) + " already defined");
            			}
            			else {
            				sTypes.addStruct((i!=null?i.getText():null), fieldList);
            			}
            		
            // TypeCheck.g:88:3: ( struct_decl[sTypes, sTable, fieldList] )+
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
            	    // TypeCheck.g:88:3: struct_decl[sTypes, sTable, fieldList]
            	    {
            	    pushFollow(FOLLOW_struct_decl_in_types_sub209);
            	    struct_decl(sTypes, sTable, fieldList);

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
    // TypeCheck.g:91:1: struct_decl[StructTypes sTypes, SymbolTable sTable, Hashtable<String, EVILType> fieldList] : ^( DECL ^( TYPE t= type[sTypes] ) i= ID ) ;
    public final void struct_decl(StructTypes sTypes, SymbolTable sTable, Hashtable<String, EVILType> fieldList) throws RecognitionException {
        CommonTree i=null;
        EVILType t = null;


        try {
            // TypeCheck.g:91:93: ( ^( DECL ^( TYPE t= type[sTypes] ) i= ID ) )
            // TypeCheck.g:92:4: ^( DECL ^( TYPE t= type[sTypes] ) i= ID )
            {
            match(input,DECL,FOLLOW_DECL_in_struct_decl227); 

            match(input, Token.DOWN, null); 
            match(input,TYPE,FOLLOW_TYPE_in_struct_decl230); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_struct_decl234);
            t=type(sTypes);

            state._fsp--;


            match(input, Token.UP, null); 
            i=(CommonTree)match(input,ID,FOLLOW_ID_in_struct_decl240); 

            match(input, Token.UP, null); 

            		fieldList.put((i!=null?i.getText():null), t);
            	

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
    // TypeCheck.g:99:1: type_decl[StructTypes sTypes, SymbolTable sTable, Declaration decl] : ^( DECL ^( TYPE t= type[sTypes] ) i= ID ) ;
    public final void type_decl(StructTypes sTypes, SymbolTable sTable, Declaration decl) throws RecognitionException {
        CommonTree i=null;
        EVILType t = null;


        try {
            // TypeCheck.g:99:70: ( ^( DECL ^( TYPE t= type[sTypes] ) i= ID ) )
            // TypeCheck.g:100:4: ^( DECL ^( TYPE t= type[sTypes] ) i= ID )
            {
            match(input,DECL,FOLLOW_DECL_in_type_decl260); 

            match(input, Token.DOWN, null); 
            match(input,TYPE,FOLLOW_TYPE_in_type_decl263); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_type_decl267);
            t=type(sTypes);

            state._fsp--;


            match(input, Token.UP, null); 
            i=(CommonTree)match(input,ID,FOLLOW_ID_in_type_decl273); 

            match(input, Token.UP, null); 

            			switch(decl) {
            				case GLOBAL:
            					sTable.addGlobalVar((i!=null?i.getText():null), t);
            				break;
            				
            				case LOCAL:
            					sTable.addLocalVar((i!=null?i.getText():null), t);
            				break;

            				case PARAM:
            					sTable.addParam((i!=null?i.getText():null), t);
            				break;

            				default:
            					error("LOLWUT?");
            				break;
            			}
            	

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
    // TypeCheck.g:122:1: type[StructTypes sTypes] returns [EVILType t = null] : ( INT | BOOL | ^( STRUCT i= ID ) | VOID );
    public final EVILType type(StructTypes sTypes) throws RecognitionException {
        EVILType t =  null;

        CommonTree i=null;

        try {
            // TypeCheck.g:122:55: ( INT | BOOL | ^( STRUCT i= ID ) | VOID )
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
                    // TypeCheck.g:123:4: INT
                    {
                    match(input,INT,FOLLOW_INT_in_type295); 

                    		t = new TypeInt();
                    		debug("INT");
                    	

                    }
                    break;
                case 2 :
                    // TypeCheck.g:128:4: BOOL
                    {
                    match(input,BOOL,FOLLOW_BOOL_in_type305); 
                    t = new TypeBool();

                    }
                    break;
                case 3 :
                    // TypeCheck.g:129:4: ^( STRUCT i= ID )
                    {
                    match(input,STRUCT,FOLLOW_STRUCT_in_type314); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_type318); 

                    match(input, Token.UP, null); 

                    		if(sTypes.findStruct((i!=null?i.getText():null)) == null) {
                    			error("Struct "+(i!=null?i.getText():null)+" not defined");
                    		}
                    		else {
                    			t = new TypeStruct((i!=null?i.getText():null));
                    		}
                    	

                    }
                    break;
                case 4 :
                    // TypeCheck.g:138:4: VOID
                    {
                    match(input,VOID,FOLLOW_VOID_in_type327); 
                    t = new TypeNull();

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
        return t;
    }
    // $ANTLR end "type"


    // $ANTLR start "declarations"
    // TypeCheck.g:141:1: declarations[StructTypes sTypes, SymbolTable sTable, Declaration decl] : ^( DECLLIST ^( TYPE t= type[sTypes] ) ( id_list[sTable, $t.t, decl] )+ ) ;
    public final void declarations(StructTypes sTypes, SymbolTable sTable, Declaration decl) throws RecognitionException {
        EVILType t = null;


        try {
            // TypeCheck.g:141:73: ( ^( DECLLIST ^( TYPE t= type[sTypes] ) ( id_list[sTable, $t.t, decl] )+ ) )
            // TypeCheck.g:142:4: ^( DECLLIST ^( TYPE t= type[sTypes] ) ( id_list[sTable, $t.t, decl] )+ )
            {
            match(input,DECLLIST,FOLLOW_DECLLIST_in_declarations344); 

            match(input, Token.DOWN, null); 
            match(input,TYPE,FOLLOW_TYPE_in_declarations350); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_declarations354);
            t=type(sTypes);

            state._fsp--;


            match(input, Token.UP, null); 
            // TypeCheck.g:144:3: ( id_list[sTable, $t.t, decl] )+
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
            	    // TypeCheck.g:144:3: id_list[sTable, $t.t, decl]
            	    {
            	    pushFollow(FOLLOW_id_list_in_declarations361);
            	    id_list(sTable, t, decl);

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
    // TypeCheck.g:147:1: id_list[SymbolTable sTable, EVILType t, Declaration decl] : id= ID ;
    public final void id_list(SymbolTable sTable, EVILType t, Declaration decl) throws RecognitionException {
        CommonTree id=null;

        try {
            // TypeCheck.g:147:60: (id= ID )
            // TypeCheck.g:148:4: id= ID
            {
            id=(CommonTree)match(input,ID,FOLLOW_ID_in_id_list380); 

            		if(t != null) {
            			switch(decl) {
            				case GLOBAL:
            					sTable.addGlobalVar((id!=null?id.getText():null), t);
            				break;
            				
            				case LOCAL:
            					sTable.addLocalVar((id!=null?id.getText():null), t);
            					TypeFun fun = sTable.findCurrentFunction();
            					if(fun.isParam((id!=null?id.getText():null))) {
            						error((id!=null?id.getText():null) +" is a parameter of the function");
            					}
            				break;

            				case PARAM:
            					sTable.addParam((id!=null?id.getText():null), t);
            				break;

            				default:
            					error("LOLWUT?");
            				break;
            			}
            		}
            	

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
    // TypeCheck.g:176:1: functions[StructTypes sTypes, SymbolTable sTable] : ^( FUN id= ID ^( PARAMS ( type_decl[sTypes, sTable, Declaration.PARAM] )* ) ^( RETTYPE t= type[sTypes] ) ^( DECLS ( declarations[sTypes, sTable, Declaration.LOCAL] )* ) ^( STMTS ( statements[sTypes, sTable, hasReturned] )* ) ) ;
    public final void functions(StructTypes sTypes, SymbolTable sTable) throws RecognitionException {
        CommonTree id=null;
        EVILType t = null;


        try {
            // TypeCheck.g:176:51: ( ^( FUN id= ID ^( PARAMS ( type_decl[sTypes, sTable, Declaration.PARAM] )* ) ^( RETTYPE t= type[sTypes] ) ^( DECLS ( declarations[sTypes, sTable, Declaration.LOCAL] )* ) ^( STMTS ( statements[sTypes, sTable, hasReturned] )* ) ) )
            // TypeCheck.g:177:1: ^( FUN id= ID ^( PARAMS ( type_decl[sTypes, sTable, Declaration.PARAM] )* ) ^( RETTYPE t= type[sTypes] ) ^( DECLS ( declarations[sTypes, sTable, Declaration.LOCAL] )* ) ^( STMTS ( statements[sTypes, sTable, hasReturned] )* ) )
            {
            debug("functions");
            match(input,FUN,FOLLOW_FUN_in_functions399); 

            match(input, Token.DOWN, null); 
            id=(CommonTree)match(input,ID,FOLLOW_ID_in_functions406); 

            				sTable.newFunction((id!=null?id.getText():null));
            				if((id!=null?id.getText():null).equals("main")) {
            					hasMain = true;
            				}
            			
             debug(sTable);
            match(input,PARAMS,FOLLOW_PARAMS_in_functions422); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // TypeCheck.g:187:12: ( type_decl[sTypes, sTable, Declaration.PARAM] )*
                loop7:
                do {
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==DECL) ) {
                        alt7=1;
                    }


                    switch (alt7) {
                	case 1 :
                	    // TypeCheck.g:187:12: type_decl[sTypes, sTable, Declaration.PARAM]
                	    {
                	    pushFollow(FOLLOW_type_decl_in_functions424);
                	    type_decl(sTypes, sTable, Declaration.PARAM);

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop7;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }
            match(input,RETTYPE,FOLLOW_RETTYPE_in_functions433); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_functions437);
            t=type(sTypes);

            state._fsp--;


            match(input, Token.UP, null); 

            			TypeFun fun = sTable.findCurrentFunction();
            			fun.setReturnType(t);
            			debug("Setting function "+(id!=null?id.getText():null)+" to return type "+t+" now has "+fun.getReturnType());
            		
            match(input,DECLS,FOLLOW_DECLS_in_functions450); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // TypeCheck.g:194:11: ( declarations[sTypes, sTable, Declaration.LOCAL] )*
                loop8:
                do {
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==DECLLIST) ) {
                        alt8=1;
                    }


                    switch (alt8) {
                	case 1 :
                	    // TypeCheck.g:194:11: declarations[sTypes, sTable, Declaration.LOCAL]
                	    {
                	    pushFollow(FOLLOW_declarations_in_functions452);
                	    declarations(sTypes, sTable, Declaration.LOCAL);

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop8;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }
             MyBoolean hasReturned = new MyBoolean(); 
            match(input,STMTS,FOLLOW_STMTS_in_functions465); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // TypeCheck.g:196:11: ( statements[sTypes, sTable, hasReturned] )*
                loop9:
                do {
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==PRINT||(LA9_0>=READ && LA9_0<=IF)||(LA9_0>=WHILE && LA9_0<=RETURN)||LA9_0==BLOCK||LA9_0==INVOKE||LA9_0==ASSIGN) ) {
                        alt9=1;
                    }


                    switch (alt9) {
                	case 1 :
                	    // TypeCheck.g:196:11: statements[sTypes, sTable, hasReturned]
                	    {
                	    pushFollow(FOLLOW_statements_in_functions467);
                	    statements(sTypes, sTable, hasReturned);

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop9;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            match(input, Token.UP, null); 
            debug(id);

            			if(!(fun.getReturnType() instanceof TypeNull) && hasReturned.isFalse()) {
            				error("Didn't return through all branches of the program");
            			}
            		

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


    // $ANTLR start "statements"
    // TypeCheck.g:205:1: statements[StructTypes sTypes, SymbolTable sTable, MyBoolean returned] : ( block[sTypes, sTable, returned] | ^( ASSIGN t= expression[sTypes, sTable] l= expression[sTypes, sTable] ) | ^( PRINT t= expression[sTypes, sTable] ( ( ENDL )? ) ) | ^( READ l= expression[sTypes, sTable] ) | ^( IF t= expression[sTypes, sTable] block[sTypes, sTable, blk1] ( block[sTypes, sTable, blk2] )? ) | ^( WHILE t= expression[sTypes, sTable] block[sTypes, sTable, returned] expression[sTypes, sTable] ) | ^( DELETE t= expression[sTypes, sTable] ) | ^( RETURN (t= expression[sTypes, sTable] )? ) | ^( INVOKE i= ID a= arguments[sTypes, sTable] ) );
    public final void statements(StructTypes sTypes, SymbolTable sTable, MyBoolean returned) throws RecognitionException {
        CommonTree i=null;
        EVILType t = null;

        EVILType l = null;

        ArrayList<EVILType> a = null;


        try {
            // TypeCheck.g:205:73: ( block[sTypes, sTable, returned] | ^( ASSIGN t= expression[sTypes, sTable] l= expression[sTypes, sTable] ) | ^( PRINT t= expression[sTypes, sTable] ( ( ENDL )? ) ) | ^( READ l= expression[sTypes, sTable] ) | ^( IF t= expression[sTypes, sTable] block[sTypes, sTable, blk1] ( block[sTypes, sTable, blk2] )? ) | ^( WHILE t= expression[sTypes, sTable] block[sTypes, sTable, returned] expression[sTypes, sTable] ) | ^( DELETE t= expression[sTypes, sTable] ) | ^( RETURN (t= expression[sTypes, sTable] )? ) | ^( INVOKE i= ID a= arguments[sTypes, sTable] ) )
            int alt13=9;
            switch ( input.LA(1) ) {
            case BLOCK:
                {
                alt13=1;
                }
                break;
            case ASSIGN:
                {
                alt13=2;
                }
                break;
            case PRINT:
                {
                alt13=3;
                }
                break;
            case READ:
                {
                alt13=4;
                }
                break;
            case IF:
                {
                alt13=5;
                }
                break;
            case WHILE:
                {
                alt13=6;
                }
                break;
            case DELETE:
                {
                alt13=7;
                }
                break;
            case RETURN:
                {
                alt13=8;
                }
                break;
            case INVOKE:
                {
                alt13=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // TypeCheck.g:206:2: block[sTypes, sTable, returned]
                    {
                    pushFollow(FOLLOW_block_in_statements491);
                    block(sTypes, sTable, returned);

                    state._fsp--;

                    debug("Block");

                    }
                    break;
                case 2 :
                    // TypeCheck.g:208:4: ^( ASSIGN t= expression[sTypes, sTable] l= expression[sTypes, sTable] )
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_statements501); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statements505);
                    t=expression(sTypes, sTable);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_statements510);
                    l=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(!l.isSameType(t)) {
                    			error("In assignment types should be the same given "+l+" as l-value and "+t+" as r-value");
                    		}
                    	
                    debug("Assign");

                    }
                    break;
                case 3 :
                    // TypeCheck.g:215:4: ^( PRINT t= expression[sTypes, sTable] ( ( ENDL )? ) )
                    {
                    match(input,PRINT,FOLLOW_PRINT_in_statements524); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statements528);
                    t=expression(sTypes, sTable);

                    state._fsp--;

                    // TypeCheck.g:215:41: ( ( ENDL )? )
                    // TypeCheck.g:215:42: ( ENDL )?
                    {
                    // TypeCheck.g:215:42: ( ENDL )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==ENDL) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // TypeCheck.g:215:42: ENDL
                            {
                            match(input,ENDL,FOLLOW_ENDL_in_statements532); 

                            }
                            break;

                    }


                    }


                    match(input, Token.UP, null); 

                    		if(!(t instanceof TypeInt)) {
                    			error("PRINT needs an Int and given "+t);
                    		}
                    	
                    debug("Print");

                    }
                    break;
                case 4 :
                    // TypeCheck.g:222:4: ^( READ l= expression[sTypes, sTable] )
                    {
                    match(input,READ,FOLLOW_READ_in_statements547); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statements551);
                    l=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(!(l instanceof TypeInt)) {
                    			error("Read takes an Int given "+l);
                    		}
                    	
                    debug("Read");

                    }
                    break;
                case 5 :
                    // TypeCheck.g:229:4: ^( IF t= expression[sTypes, sTable] block[sTypes, sTable, blk1] ( block[sTypes, sTable, blk2] )? )
                    {
                    match(input,IF,FOLLOW_IF_in_statements565); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statements569);
                    t=expression(sTypes, sTable);

                    state._fsp--;

                    MyBoolean blk1 = new MyBoolean();
                    pushFollow(FOLLOW_block_in_statements574);
                    block(sTypes, sTable, blk1);

                    state._fsp--;

                    MyBoolean blk2 = new MyBoolean();
                    // TypeCheck.g:229:138: ( block[sTypes, sTable, blk2] )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==BLOCK) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // TypeCheck.g:229:139: block[sTypes, sTable, blk2]
                            {
                            pushFollow(FOLLOW_block_in_statements580);
                            block(sTypes, sTable, blk2);

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                    		if(!(t instanceof TypeBool)) {
                    			error("Guard on IF statement must be Bool you gave "+t);
                    		}
                    		if(blk2.isTrue() && blk1.isTrue()) {
                    			returned.setTrue();
                    		}
                    	
                    debug("If");

                    }
                    break;
                case 6 :
                    // TypeCheck.g:239:4: ^( WHILE t= expression[sTypes, sTable] block[sTypes, sTable, returned] expression[sTypes, sTable] )
                    {
                    match(input,WHILE,FOLLOW_WHILE_in_statements596); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statements600);
                    t=expression(sTypes, sTable);

                    state._fsp--;

                    pushFollow(FOLLOW_block_in_statements603);
                    block(sTypes, sTable, returned);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_statements606);
                    expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(!(t instanceof TypeBool)) {
                    			error("Guard on WHILE statement must be Bool you gave "+t);
                    		}
                    	
                    debug("While");

                    }
                    break;
                case 7 :
                    // TypeCheck.g:246:4: ^( DELETE t= expression[sTypes, sTable] )
                    {
                    match(input,DELETE,FOLLOW_DELETE_in_statements620); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statements624);
                    t=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(!(t instanceof TypeStruct)) {
                    			error("DELETE needs a Struct and given "+t);
                    		}
                    	
                    debug("Delete");

                    }
                    break;
                case 8 :
                    // TypeCheck.g:253:4: ^( RETURN (t= expression[sTypes, sTable] )? )
                    {
                    match(input,RETURN,FOLLOW_RETURN_in_statements638); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // TypeCheck.g:253:13: (t= expression[sTypes, sTable] )?
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>=TRUE && LA12_0<=NULL)||LA12_0==INVOKE||LA12_0==NEG||(LA12_0>=DOT && LA12_0<=INTEGER)) ) {
                            alt12=1;
                        }
                        switch (alt12) {
                            case 1 :
                                // TypeCheck.g:253:14: t= expression[sTypes, sTable]
                                {
                                pushFollow(FOLLOW_expression_in_statements643);
                                t=expression(sTypes, sTable);

                                state._fsp--;


                                }
                                break;

                        }


                        match(input, Token.UP, null); 
                    }

                    		TypeFun fun = sTable.findCurrentFunction();
                    		if(t != null) {
                    			if(!t.isSameType(fun.getReturnType())) {
                    				error("This function returns "+fun.getReturnType()+" you tried to return "+t);
                    			}
                    		}
                    		else if (!(fun.getReturnType() instanceof TypeNull)) {
                    			error("This function returns "+fun.getReturnType()+ 
                    				" and you didn't return anything.");
                    		}

                    		returned.setTrue();
                    	
                    debug("Return");

                    }
                    break;
                case 9 :
                    // TypeCheck.g:269:4: ^( INVOKE i= ID a= arguments[sTypes, sTable] )
                    {
                    match(input,INVOKE,FOLLOW_INVOKE_in_statements659); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_statements663); 
                    pushFollow(FOLLOW_arguments_in_statements667);
                    a=arguments(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		TypeFun fun = sTable.findFunction((i!=null?i.getText():null));
                    		if(!fun.checkArgs(a)) {
                    			error("Error with the arguments to function");
                    		}
                    	
                    debug("Invoke");

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


    // $ANTLR start "block"
    // TypeCheck.g:279:1: block[StructTypes sTypes, SymbolTable sTable, MyBoolean returned] : ^( BLOCK ^( STMTS ( statements[sTypes, sTable, returned] )* ) ) ;
    public final void block(StructTypes sTypes, SymbolTable sTable, MyBoolean returned) throws RecognitionException {
        try {
            // TypeCheck.g:279:68: ( ^( BLOCK ^( STMTS ( statements[sTypes, sTable, returned] )* ) ) )
            // TypeCheck.g:280:2: ^( BLOCK ^( STMTS ( statements[sTypes, sTable, returned] )* ) )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_block688); 

            match(input, Token.DOWN, null); 
            match(input,STMTS,FOLLOW_STMTS_in_block691); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // TypeCheck.g:280:18: ( statements[sTypes, sTable, returned] )*
                loop14:
                do {
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==PRINT||(LA14_0>=READ && LA14_0<=IF)||(LA14_0>=WHILE && LA14_0<=RETURN)||LA14_0==BLOCK||LA14_0==INVOKE||LA14_0==ASSIGN) ) {
                        alt14=1;
                    }


                    switch (alt14) {
                	case 1 :
                	    // TypeCheck.g:280:19: statements[sTypes, sTable, returned]
                	    {
                	    pushFollow(FOLLOW_statements_in_block694);
                	    statements(sTypes, sTable, returned);

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop14;
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
    // $ANTLR end "block"


    // $ANTLR start "expression"
    // TypeCheck.g:283:1: expression[StructTypes sTypes, SymbolTable sTable] returns [EVILType t = null] : ( ^( AND t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( OR t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( EQ t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( LT t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( GT t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( NE t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( LE t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( GE t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( PLUS t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( MINUS t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( TIMES t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( DIVIDE t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( DOT l= expression[sTypes, sTable] id= ID ) | ^( INVOKE i= ID a= arguments[sTypes, sTable] ) | ^( NEG t1= expression[sTypes, sTable] ) | INTEGER | TRUE | FALSE | ^( NOT t1= expression[sTypes, sTable] ) | ^( NEW i= ID ) | NULL | i= ID );
    public final EVILType expression(StructTypes sTypes, SymbolTable sTable) throws RecognitionException {
        EVILType t =  null;

        CommonTree id=null;
        CommonTree i=null;
        EVILType t1 = null;

        EVILType t2 = null;

        EVILType l = null;

        ArrayList<EVILType> a = null;


        try {
            // TypeCheck.g:283:81: ( ^( AND t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( OR t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( EQ t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( LT t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( GT t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( NE t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( LE t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( GE t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( PLUS t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( MINUS t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( TIMES t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( DIVIDE t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] ) | ^( DOT l= expression[sTypes, sTable] id= ID ) | ^( INVOKE i= ID a= arguments[sTypes, sTable] ) | ^( NEG t1= expression[sTypes, sTable] ) | INTEGER | TRUE | FALSE | ^( NOT t1= expression[sTypes, sTable] ) | ^( NEW i= ID ) | NULL | i= ID )
            int alt15=22;
            switch ( input.LA(1) ) {
            case AND:
                {
                alt15=1;
                }
                break;
            case OR:
                {
                alt15=2;
                }
                break;
            case EQ:
                {
                alt15=3;
                }
                break;
            case LT:
                {
                alt15=4;
                }
                break;
            case GT:
                {
                alt15=5;
                }
                break;
            case NE:
                {
                alt15=6;
                }
                break;
            case LE:
                {
                alt15=7;
                }
                break;
            case GE:
                {
                alt15=8;
                }
                break;
            case PLUS:
                {
                alt15=9;
                }
                break;
            case MINUS:
                {
                alt15=10;
                }
                break;
            case TIMES:
                {
                alt15=11;
                }
                break;
            case DIVIDE:
                {
                alt15=12;
                }
                break;
            case DOT:
                {
                alt15=13;
                }
                break;
            case INVOKE:
                {
                alt15=14;
                }
                break;
            case NEG:
                {
                alt15=15;
                }
                break;
            case INTEGER:
                {
                alt15=16;
                }
                break;
            case TRUE:
                {
                alt15=17;
                }
                break;
            case FALSE:
                {
                alt15=18;
                }
                break;
            case NOT:
                {
                alt15=19;
                }
                break;
            case NEW:
                {
                alt15=20;
                }
                break;
            case NULL:
                {
                alt15=21;
                }
                break;
            case ID:
                {
                alt15=22;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // TypeCheck.g:284:2: ^( AND t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] )
                    {
                    match(input,AND,FOLLOW_AND_in_expression716); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression720);
                    t1=expression(sTypes, sTable);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression725);
                    t2=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(t1 instanceof TypeBool && t2 instanceof TypeBool) {
                    			t = new TypeBool();
                    		}
                    		else {
                    			error("AND takes a Bool and a Bool and you gave: "+t1+ " and "+t2);
                    		}
                    	
                    debug("AND");

                    }
                    break;
                case 2 :
                    // TypeCheck.g:294:4: ^( OR t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] )
                    {
                    match(input,OR,FOLLOW_OR_in_expression739); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression743);
                    t1=expression(sTypes, sTable);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression748);
                    t2=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(t1 instanceof TypeBool && t2 instanceof TypeBool) {
                    			t = new TypeBool();
                    		}
                    		else {
                    			error("OR takes a Bool and a Bool and you gave: "+t1+ " and "+t2);
                    		}
                    	

                    }
                    break;
                case 3 :
                    // TypeCheck.g:303:4: ^( EQ t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] )
                    {
                    match(input,EQ,FOLLOW_EQ_in_expression759); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression763);
                    t1=expression(sTypes, sTable);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression768);
                    t2=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(t2.isSameType(t1)) {
                    			t = new TypeBool();
                    		}
                    		else {
                    			error("EQ takes an Int and an Int and you gave: "+t1+" and "+t2);
                    		}
                    	

                    }
                    break;
                case 4 :
                    // TypeCheck.g:312:4: ^( LT t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] )
                    {
                    match(input,LT,FOLLOW_LT_in_expression779); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression783);
                    t1=expression(sTypes, sTable);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression788);
                    t2=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(t1 instanceof TypeInt && t2 instanceof TypeInt) {
                    			t = new TypeBool();
                    		}
                    		else {
                    			error("LT takes an Int and an Int and you gave: "+t1+" and "+t2);
                    		}
                    	
                    debug("LT");

                    }
                    break;
                case 5 :
                    // TypeCheck.g:322:4: ^( GT t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] )
                    {
                    match(input,GT,FOLLOW_GT_in_expression802); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression806);
                    t1=expression(sTypes, sTable);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression811);
                    t2=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(t1 instanceof TypeInt && t2 instanceof TypeInt) {
                    			t = new TypeBool();
                    		}
                    		else {
                    			error("GT takes an Int and an Int and you gave: "+t1+" and "+t2);
                    		}
                    	
                    debug("GT");

                    }
                    break;
                case 6 :
                    // TypeCheck.g:332:4: ^( NE t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] )
                    {
                    match(input,NE,FOLLOW_NE_in_expression825); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression829);
                    t1=expression(sTypes, sTable);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression834);
                    t2=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(t2.isSameType(t1)) {
                    			t = new TypeBool();
                    		}
                    		else {
                    			error("NE takes an Int and an Int and you gave: "+t1+" and "+t2);
                    		}
                    	

                    }
                    break;
                case 7 :
                    // TypeCheck.g:341:4: ^( LE t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] )
                    {
                    match(input,LE,FOLLOW_LE_in_expression845); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression849);
                    t1=expression(sTypes, sTable);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression854);
                    t2=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(t1 instanceof TypeInt && t2 instanceof TypeInt) {
                    			t = new TypeBool();
                    		}
                    		else {
                    			error("LE takes an Int and an Int and you gave: "+t1+" and "+t2);
                    		}
                    	

                    }
                    break;
                case 8 :
                    // TypeCheck.g:350:4: ^( GE t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] )
                    {
                    match(input,GE,FOLLOW_GE_in_expression865); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression869);
                    t1=expression(sTypes, sTable);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression874);
                    t2=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(t1 instanceof TypeInt && t2 instanceof TypeInt) {
                    			t = new TypeBool();
                    		}
                    		else {
                    			error("GE takes an Int and an Int and you gave: "+t1+" and "+t2);
                    		}
                    	

                    }
                    break;
                case 9 :
                    // TypeCheck.g:359:4: ^( PLUS t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] )
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_expression885); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression889);
                    t1=expression(sTypes, sTable);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression894);
                    t2=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(t1 instanceof TypeInt && t2 instanceof TypeInt) {
                    			t = new TypeInt();
                    		}
                    		else {
                    			error("PLUS takes an Int and an Int and you gave: "+t1+" and "+t2);
                    		}
                    	

                    }
                    break;
                case 10 :
                    // TypeCheck.g:368:4: ^( MINUS t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] )
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_expression905); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression909);
                    t1=expression(sTypes, sTable);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression914);
                    t2=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(t1 instanceof TypeInt && t2 instanceof TypeInt) {
                    			t = new TypeInt();
                    		}
                    		else {
                    			error("MINUS takes an Int and an Int and you gave: "+t1+" and "+t2);
                    		}
                    	

                    }
                    break;
                case 11 :
                    // TypeCheck.g:377:4: ^( TIMES t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] )
                    {
                    match(input,TIMES,FOLLOW_TIMES_in_expression925); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression929);
                    t1=expression(sTypes, sTable);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression934);
                    t2=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(t1 instanceof TypeInt && t2 instanceof TypeInt) {
                    			t = new TypeInt();
                    		}
                    		else {
                    			error("TIMES takes an Int and an Int and you gave: "+t1+" and "+t2);
                    		}
                    	
                    debug("TIMES");

                    }
                    break;
                case 12 :
                    // TypeCheck.g:387:4: ^( DIVIDE t1= expression[sTypes, sTable] t2= expression[sTypes, sTable] )
                    {
                    match(input,DIVIDE,FOLLOW_DIVIDE_in_expression948); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression952);
                    t1=expression(sTypes, sTable);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression957);
                    t2=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(t1 instanceof TypeInt && t2 instanceof TypeInt) {
                    			t = new TypeInt();
                    		}
                    		else {
                    			error("DIVIDE takes an Int and an Int and you gave: "+t1+" and "+t2);
                    		}
                    	

                    }
                    break;
                case 13 :
                    // TypeCheck.g:397:2: ^( DOT l= expression[sTypes, sTable] id= ID )
                    {
                    match(input,DOT,FOLLOW_DOT_in_expression969); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression973);
                    l=expression(sTypes, sTable);

                    state._fsp--;

                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression978); 

                    match(input, Token.UP, null); 

                    		if(!(l instanceof TypeStruct)) {
                    			error("Should be a Struct but is a "+l);
                    		}
                    		else {
                    		TypeStruct struct = (TypeStruct) l;
                    		Hashtable<String, EVILType> table = sTypes.findStruct(struct.name);
                    		t = table.get((id!=null?id.getText():null));
                    		}
                    		debug("LVALUE DOT: "+(id!=null?id.getText():null));
                    	

                    }
                    break;
                case 14 :
                    // TypeCheck.g:410:4: ^( INVOKE i= ID a= arguments[sTypes, sTable] )
                    {
                    match(input,INVOKE,FOLLOW_INVOKE_in_expression990); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_expression994); 
                    pushFollow(FOLLOW_arguments_in_expression998);
                    a=arguments(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		debug("INVOKE IN EXPRESSIONS "+(i!=null?i.getText():null));
                    		TypeFun fun = sTable.findFunction((i!=null?i.getText():null));
                    		debug(a);
                    		if(!fun.checkArgs(a)) {
                    			error("Error with the arguments to function");
                    		}

                    		t = fun.getReturnType();
                    	

                    }
                    break;
                case 15 :
                    // TypeCheck.g:421:4: ^( NEG t1= expression[sTypes, sTable] )
                    {
                    match(input,NEG,FOLLOW_NEG_in_expression1009); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1013);
                    t1=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(t1 instanceof TypeInt) {
                    			t = new TypeInt();
                    		}
                    		else {
                    			error("NEG takes an Int and you gave: "+t1);
                    		}
                    	

                    }
                    break;
                case 16 :
                    // TypeCheck.g:430:4: INTEGER
                    {
                    match(input,INTEGER,FOLLOW_INTEGER_in_expression1023); 

                    		t = new TypeInt();
                    	
                    debug("INTEGER");

                    }
                    break;
                case 17 :
                    // TypeCheck.g:435:4: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_expression1034); 

                    		t = new TypeBool();
                    	

                    }
                    break;
                case 18 :
                    // TypeCheck.g:439:4: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_expression1042); 

                    		t = new TypeBool();
                    	

                    }
                    break;
                case 19 :
                    // TypeCheck.g:443:4: ^( NOT t1= expression[sTypes, sTable] )
                    {
                    match(input,NOT,FOLLOW_NOT_in_expression1051); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1055);
                    t1=expression(sTypes, sTable);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if(t1 instanceof TypeBool) {
                    			t = new TypeBool();
                    		}
                    		else {
                    			error("NOT takes a bool and you gave: "+t1);
                    		}
                    	

                    }
                    break;
                case 20 :
                    // TypeCheck.g:452:4: ^( NEW i= ID )
                    {
                    match(input,NEW,FOLLOW_NEW_in_expression1066); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1070); 

                    match(input, Token.UP, null); 

                    		if(sTypes.findStruct((i!=null?i.getText():null)) != null) {
                    			t = new TypeStruct((i!=null?i.getText():null));
                    		}
                    		else {
                    			error("Struct "+(i!=null?i.getText():null)+" not found!");
                    		}
                    	

                    }
                    break;
                case 21 :
                    // TypeCheck.g:461:4: NULL
                    {
                    match(input,NULL,FOLLOW_NULL_in_expression1079); 

                    		t = new TypeNull();
                    	

                    }
                    break;
                case 22 :
                    // TypeCheck.g:465:4: i= ID
                    {
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1089); 

                    		t = sTable.findVariable((i!=null?i.getText():null));
                    	
                    debug("ID");

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
        return t;
    }
    // $ANTLR end "expression"


    // $ANTLR start "ids_list"
    // TypeCheck.g:472:1: ids_list[ArrayList<String> list] : id= ID ;
    public final void ids_list(ArrayList<String> list) throws RecognitionException {
        CommonTree id=null;

        try {
            // TypeCheck.g:472:35: (id= ID )
            // TypeCheck.g:473:2: id= ID
            {
            id=(CommonTree)match(input,ID,FOLLOW_ID_in_ids_list1109); 

            	list.add((id!=null?id.getText():null));
            	

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
    // $ANTLR end "ids_list"


    // $ANTLR start "arguments"
    // TypeCheck.g:479:1: arguments[StructTypes sTypes, SymbolTable sTable] returns [ArrayList<EVILType> list = null] : arg_list[sTypes, sTable, list] ;
    public final ArrayList<EVILType> arguments(StructTypes sTypes, SymbolTable sTable) throws RecognitionException {
        ArrayList<EVILType> list =  null;

        try {
            // TypeCheck.g:479:94: ( arg_list[sTypes, sTable, list] )
            // TypeCheck.g:480:2: arg_list[sTypes, sTable, list]
            {
            list = new ArrayList<EVILType>();
            pushFollow(FOLLOW_arg_list_in_arguments1131);
            arg_list(sTypes, sTable, list);

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return list;
    }
    // $ANTLR end "arguments"


    // $ANTLR start "arg_list"
    // TypeCheck.g:484:1: arg_list[StructTypes sTypes, SymbolTable sTable, ArrayList<EVILType> list] : ^( ARGS ( arg_expression[sTypes, sTable, list] )* ) ;
    public final void arg_list(StructTypes sTypes, SymbolTable sTable, ArrayList<EVILType> list) throws RecognitionException {
        try {
            // TypeCheck.g:484:77: ( ^( ARGS ( arg_expression[sTypes, sTable, list] )* ) )
            // TypeCheck.g:485:2: ^( ARGS ( arg_expression[sTypes, sTable, list] )* )
            {
            match(input,ARGS,FOLLOW_ARGS_in_arg_list1145); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // TypeCheck.g:485:9: ( arg_expression[sTypes, sTable, list] )*
                loop16:
                do {
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( ((LA16_0>=TRUE && LA16_0<=NULL)||LA16_0==INVOKE||LA16_0==NEG||(LA16_0>=DOT && LA16_0<=INTEGER)) ) {
                        alt16=1;
                    }


                    switch (alt16) {
                	case 1 :
                	    // TypeCheck.g:485:9: arg_expression[sTypes, sTable, list]
                	    {
                	    pushFollow(FOLLOW_arg_expression_in_arg_list1147);
                	    arg_expression(sTypes, sTable, list);

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop16;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

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
    // $ANTLR end "arg_list"


    // $ANTLR start "arg_expression"
    // TypeCheck.g:488:1: arg_expression[StructTypes sTypes, SymbolTable sTable, ArrayList<EVILType> list] : t= expression[sTypes, sTable] ;
    public final void arg_expression(StructTypes sTypes, SymbolTable sTable, ArrayList<EVILType> list) throws RecognitionException {
        EVILType t = null;


        try {
            // TypeCheck.g:488:83: (t= expression[sTypes, sTable] )
            // TypeCheck.g:489:2: t= expression[sTypes, sTable]
            {
            pushFollow(FOLLOW_expression_in_arg_expression1164);
            t=expression(sTypes, sTable);

            state._fsp--;


            		list.add(t);
            	

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


 

    public static final BitSet FOLLOW_PROGRAM_in_verify56 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TYPES_in_verify76 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_types_sub_in_verify78 = new BitSet(new long[]{0x0000000000000018L});
    public static final BitSet FOLLOW_DECLS_in_verify107 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declarations_in_verify109 = new BitSet(new long[]{0x0000000008000008L});
    public static final BitSet FOLLOW_FUNCS_in_verify138 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_functions_in_verify140 = new BitSet(new long[]{0x0000000000000088L});
    public static final BitSet FOLLOW_STRUCT_in_types_sub194 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_types_sub198 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_struct_decl_in_types_sub209 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_DECL_in_struct_decl227 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TYPE_in_struct_decl230 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_struct_decl234 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_struct_decl240 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DECL_in_type_decl260 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TYPE_in_type_decl263 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_type_decl267 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_type_decl273 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INT_in_type295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_type305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCT_in_type314 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_type318 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VOID_in_type327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLLIST_in_declarations344 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TYPE_in_declarations350 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_declarations354 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_id_list_in_declarations361 = new BitSet(new long[]{0x0100000000000008L});
    public static final BitSet FOLLOW_ID_in_id_list380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUN_in_functions399 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_functions406 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_PARAMS_in_functions422 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_decl_in_functions424 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_RETTYPE_in_functions433 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_functions437 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DECLS_in_functions450 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declarations_in_functions452 = new BitSet(new long[]{0x0000000008000008L});
    public static final BitSet FOLLOW_STMTS_in_functions465 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statements_in_functions467 = new BitSet(new long[]{0x000002014001DA08L});
    public static final BitSet FOLLOW_block_in_statements491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_statements501 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statements505 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_statements510 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PRINT_in_statements524 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statements528 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_ENDL_in_statements532 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_READ_in_statements547 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statements551 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_statements565 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statements569 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_block_in_statements574 = new BitSet(new long[]{0x0000000040000008L});
    public static final BitSet FOLLOW_block_in_statements580 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_WHILE_in_statements596 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statements600 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_block_in_statements603 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_statements606 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DELETE_in_statements620 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statements624 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RETURN_in_statements638 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statements643 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INVOKE_in_statements659 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_statements663 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_arguments_in_statements667 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_in_block688 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STMTS_in_block691 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statements_in_block694 = new BitSet(new long[]{0x000002014001DA08L});
    public static final BitSet FOLLOW_AND_in_expression716 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression720 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression725 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OR_in_expression739 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression743 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression748 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQ_in_expression759 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression763 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression768 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expression779 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression783 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression788 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expression802 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression806 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression811 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NE_in_expression825 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression829 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression834 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LE_in_expression845 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression849 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression854 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GE_in_expression865 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression869 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression874 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PLUS_in_expression885 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression889 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression894 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MINUS_in_expression905 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression909 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression914 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TIMES_in_expression925 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression929 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression934 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIVIDE_in_expression948 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression952 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression957 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_expression969 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression973 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_expression978 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INVOKE_in_expression990 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expression994 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_arguments_in_expression998 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEG_in_expression1009 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1013 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INTEGER_in_expression1023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_expression1034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_expression1042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_expression1051 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1055 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEW_in_expression1066 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expression1070 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NULL_in_expression1079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_expression1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_ids_list1109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_list_in_arguments1131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ARGS_in_arg_list1145 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_arg_expression_in_arg_list1147 = new BitSet(new long[]{0x03FFFC05001E0008L});
    public static final BitSet FOLLOW_expression_in_arg_expression1164 = new BitSet(new long[]{0x0000000000000002L});

}