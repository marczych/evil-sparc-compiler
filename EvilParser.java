// $ANTLR 3.3 Nov 30, 2010 12:50:56 Evil.g 2012-04-28 14:31:35

   /* package declaration here */


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

public class EvilParser extends Parser {
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


        public EvilParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public EvilParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return EvilParser.tokenNames; }
    public String getGrammarFileName() { return "Evil.g"; }


    public static class program_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "program"
    // Evil.g:100:1: program : t= types d= declarations f= functions EOF -> ^( PROGRAM $t $d $f) ;
    public final EvilParser.program_return program() throws RecognitionException {
        EvilParser.program_return retval = new EvilParser.program_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF1=null;
        EvilParser.types_return t = null;

        EvilParser.declarations_return d = null;

        EvilParser.functions_return f = null;


        Object EOF1_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_functions=new RewriteRuleSubtreeStream(adaptor,"rule functions");
        RewriteRuleSubtreeStream stream_types=new RewriteRuleSubtreeStream(adaptor,"rule types");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // Evil.g:101:4: (t= types d= declarations f= functions EOF -> ^( PROGRAM $t $d $f) )
            // Evil.g:101:7: t= types d= declarations f= functions EOF
            {
            pushFollow(FOLLOW_types_in_program1043);
            t=types();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_types.add(t.getTree());
            pushFollow(FOLLOW_declarations_in_program1047);
            d=declarations();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declarations.add(d.getTree());
            pushFollow(FOLLOW_functions_in_program1051);
            f=functions();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_functions.add(f.getTree());
            EOF1=(Token)match(input,EOF,FOLLOW_EOF_in_program1053); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EOF.add(EOF1);



            // AST REWRITE
            // elements: t, d, f
            // token labels: 
            // rule labels: f, retval, d, t
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_f=new RewriteRuleSubtreeStream(adaptor,"rule f",f!=null?f.tree:null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.tree:null);
            RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.tree:null);

            root_0 = (Object)adaptor.nil();
            // 102:7: -> ^( PROGRAM $t $d $f)
            {
                // Evil.g:102:10: ^( PROGRAM $t $d $f)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROGRAM, "PROGRAM"), root_1);

                adaptor.addChild(root_1, stream_t.nextTree());
                adaptor.addChild(root_1, stream_d.nextTree());
                adaptor.addChild(root_1, stream_f.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "program"

    public static class types_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "types"
    // Evil.g:104:1: types : ( ( STRUCT ID LBRACE )=> types_sub -> ^( TYPES types_sub ) | -> TYPES );
    public final EvilParser.types_return types() throws RecognitionException {
        EvilParser.types_return retval = new EvilParser.types_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        EvilParser.types_sub_return types_sub2 = null;


        RewriteRuleSubtreeStream stream_types_sub=new RewriteRuleSubtreeStream(adaptor,"rule types_sub");
        try {
            // Evil.g:105:4: ( ( STRUCT ID LBRACE )=> types_sub -> ^( TYPES types_sub ) | -> TYPES )
            int alt1=2;
            switch ( input.LA(1) ) {
            case STRUCT:
                {
                int LA1_1 = input.LA(2);

                if ( (synpred1_Evil()) ) {
                    alt1=1;
                }
                else if ( (true) ) {
                    alt1=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
                }
                break;
            case INT:
                {
                int LA1_2 = input.LA(2);

                if ( (synpred1_Evil()) ) {
                    alt1=1;
                }
                else if ( (true) ) {
                    alt1=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 2, input);

                    throw nvae;
                }
                }
                break;
            case BOOL:
                {
                int LA1_3 = input.LA(2);

                if ( (synpred1_Evil()) ) {
                    alt1=1;
                }
                else if ( (true) ) {
                    alt1=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 3, input);

                    throw nvae;
                }
                }
                break;
            case FUN:
                {
                int LA1_4 = input.LA(2);

                if ( (synpred1_Evil()) ) {
                    alt1=1;
                }
                else if ( (true) ) {
                    alt1=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 4, input);

                    throw nvae;
                }
                }
                break;
            case EOF:
                {
                int LA1_5 = input.LA(2);

                if ( (synpred1_Evil()) ) {
                    alt1=1;
                }
                else if ( (true) ) {
                    alt1=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 5, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // Evil.g:105:7: ( STRUCT ID LBRACE )=> types_sub
                    {
                    pushFollow(FOLLOW_types_sub_in_types1099);
                    types_sub2=types_sub();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_types_sub.add(types_sub2.getTree());


                    // AST REWRITE
                    // elements: types_sub
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 105:39: -> ^( TYPES types_sub )
                    {
                        // Evil.g:105:42: ^( TYPES types_sub )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPES, "TYPES"), root_1);

                        adaptor.addChild(root_1, stream_types_sub.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // Evil.g:106:7: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 106:7: -> TYPES
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(TYPES, "TYPES"));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "types"

    public static class types_sub_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "types_sub"
    // Evil.g:108:1: types_sub : ( ( STRUCT ID LBRACE )=> type_declaration types_sub | );
    public final EvilParser.types_sub_return types_sub() throws RecognitionException {
        EvilParser.types_sub_return retval = new EvilParser.types_sub_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        EvilParser.type_declaration_return type_declaration3 = null;

        EvilParser.types_sub_return types_sub4 = null;



        try {
            // Evil.g:109:4: ( ( STRUCT ID LBRACE )=> type_declaration types_sub | )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==STRUCT) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==ID) ) {
                    int LA2_3 = input.LA(3);

                    if ( (LA2_3==LBRACE) && (synpred2_Evil())) {
                        alt2=1;
                    }
                    else if ( (LA2_3==ID) ) {
                        alt2=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA2_0==EOF||(LA2_0>=INT && LA2_0<=FUN)) ) {
                alt2=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // Evil.g:109:7: ( STRUCT ID LBRACE )=> type_declaration types_sub
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_type_declaration_in_types_sub1142);
                    type_declaration3=type_declaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type_declaration3.getTree());
                    pushFollow(FOLLOW_types_sub_in_types_sub1144);
                    types_sub4=types_sub();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, types_sub4.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:111:4: 
                    {
                    root_0 = (Object)adaptor.nil();

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "types_sub"

    public static class type_declaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "type_declaration"
    // Evil.g:112:1: type_declaration : STRUCT ID LBRACE nested_decl RBRACE SEMI ;
    public final EvilParser.type_declaration_return type_declaration() throws RecognitionException {
        EvilParser.type_declaration_return retval = new EvilParser.type_declaration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STRUCT5=null;
        Token ID6=null;
        Token LBRACE7=null;
        Token RBRACE9=null;
        Token SEMI10=null;
        EvilParser.nested_decl_return nested_decl8 = null;


        Object STRUCT5_tree=null;
        Object ID6_tree=null;
        Object LBRACE7_tree=null;
        Object RBRACE9_tree=null;
        Object SEMI10_tree=null;

        try {
            // Evil.g:113:4: ( STRUCT ID LBRACE nested_decl RBRACE SEMI )
            // Evil.g:113:7: STRUCT ID LBRACE nested_decl RBRACE SEMI
            {
            root_0 = (Object)adaptor.nil();

            STRUCT5=(Token)match(input,STRUCT,FOLLOW_STRUCT_in_type_declaration1164); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STRUCT5_tree = (Object)adaptor.create(STRUCT5);
            root_0 = (Object)adaptor.becomeRoot(STRUCT5_tree, root_0);
            }
            ID6=(Token)match(input,ID,FOLLOW_ID_in_type_declaration1167); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID6_tree = (Object)adaptor.create(ID6);
            adaptor.addChild(root_0, ID6_tree);
            }
            LBRACE7=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_type_declaration1169); if (state.failed) return retval;
            pushFollow(FOLLOW_nested_decl_in_type_declaration1172);
            nested_decl8=nested_decl();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, nested_decl8.getTree());
            RBRACE9=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_type_declaration1174); if (state.failed) return retval;
            SEMI10=(Token)match(input,SEMI,FOLLOW_SEMI_in_type_declaration1177); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "type_declaration"

    public static class nested_decl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "nested_decl"
    // Evil.g:115:1: nested_decl : ( decl SEMI )+ ;
    public final EvilParser.nested_decl_return nested_decl() throws RecognitionException {
        EvilParser.nested_decl_return retval = new EvilParser.nested_decl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMI12=null;
        EvilParser.decl_return decl11 = null;


        Object SEMI12_tree=null;

        try {
            // Evil.g:116:4: ( ( decl SEMI )+ )
            // Evil.g:116:7: ( decl SEMI )+
            {
            root_0 = (Object)adaptor.nil();

            // Evil.g:116:7: ( decl SEMI )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=STRUCT && LA3_0<=BOOL)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Evil.g:116:8: decl SEMI
            	    {
            	    pushFollow(FOLLOW_decl_in_nested_decl1194);
            	    decl11=decl();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, decl11.getTree());
            	    SEMI12=(Token)match(input,SEMI,FOLLOW_SEMI_in_nested_decl1196); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "nested_decl"

    public static class decl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "decl"
    // Evil.g:118:1: decl : t= type i= ID -> ^( DECL ^( TYPE $t) $i) ;
    public final EvilParser.decl_return decl() throws RecognitionException {
        EvilParser.decl_return retval = new EvilParser.decl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        EvilParser.type_return t = null;


        Object i_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // Evil.g:119:4: (t= type i= ID -> ^( DECL ^( TYPE $t) $i) )
            // Evil.g:119:7: t= type i= ID
            {
            pushFollow(FOLLOW_type_in_decl1216);
            t=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_type.add(t.getTree());
            i=(Token)match(input,ID,FOLLOW_ID_in_decl1220); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(i);



            // AST REWRITE
            // elements: i, t
            // token labels: i
            // rule labels: retval, t
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.tree:null);

            root_0 = (Object)adaptor.nil();
            // 120:7: -> ^( DECL ^( TYPE $t) $i)
            {
                // Evil.g:120:10: ^( DECL ^( TYPE $t) $i)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECL, "DECL"), root_1);

                // Evil.g:120:17: ^( TYPE $t)
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPE, "TYPE"), root_2);

                adaptor.addChild(root_2, stream_t.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                adaptor.addChild(root_1, stream_i.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "decl"

    public static class type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "type"
    // Evil.g:122:1: type : ( INT | BOOL | STRUCT ID );
    public final EvilParser.type_return type() throws RecognitionException {
        EvilParser.type_return retval = new EvilParser.type_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token INT13=null;
        Token BOOL14=null;
        Token STRUCT15=null;
        Token ID16=null;

        Object INT13_tree=null;
        Object BOOL14_tree=null;
        Object STRUCT15_tree=null;
        Object ID16_tree=null;

        try {
            // Evil.g:123:4: ( INT | BOOL | STRUCT ID )
            int alt4=3;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt4=1;
                }
                break;
            case BOOL:
                {
                alt4=2;
                }
                break;
            case STRUCT:
                {
                alt4=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Evil.g:123:7: INT
                    {
                    root_0 = (Object)adaptor.nil();

                    INT13=(Token)match(input,INT,FOLLOW_INT_in_type1257); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT13_tree = (Object)adaptor.create(INT13);
                    adaptor.addChild(root_0, INT13_tree);
                    }

                    }
                    break;
                case 2 :
                    // Evil.g:124:7: BOOL
                    {
                    root_0 = (Object)adaptor.nil();

                    BOOL14=(Token)match(input,BOOL,FOLLOW_BOOL_in_type1265); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOL14_tree = (Object)adaptor.create(BOOL14);
                    adaptor.addChild(root_0, BOOL14_tree);
                    }

                    }
                    break;
                case 3 :
                    // Evil.g:125:7: STRUCT ID
                    {
                    root_0 = (Object)adaptor.nil();

                    STRUCT15=(Token)match(input,STRUCT,FOLLOW_STRUCT_in_type1273); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STRUCT15_tree = (Object)adaptor.create(STRUCT15);
                    root_0 = (Object)adaptor.becomeRoot(STRUCT15_tree, root_0);
                    }
                    ID16=(Token)match(input,ID,FOLLOW_ID_in_type1276); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID16_tree = (Object)adaptor.create(ID16);
                    adaptor.addChild(root_0, ID16_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "type"

    public static class declarations_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declarations"
    // Evil.g:127:1: declarations : ( declaration )* -> ^( DECLS ( declaration )* ) ;
    public final EvilParser.declarations_return declarations() throws RecognitionException {
        EvilParser.declarations_return retval = new EvilParser.declarations_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        EvilParser.declaration_return declaration17 = null;


        RewriteRuleSubtreeStream stream_declaration=new RewriteRuleSubtreeStream(adaptor,"rule declaration");
        try {
            // Evil.g:128:4: ( ( declaration )* -> ^( DECLS ( declaration )* ) )
            // Evil.g:128:7: ( declaration )*
            {
            // Evil.g:128:7: ( declaration )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>=STRUCT && LA5_0<=BOOL)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Evil.g:128:8: declaration
            	    {
            	    pushFollow(FOLLOW_declaration_in_declarations1292);
            	    declaration17=declaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_declaration.add(declaration17.getTree());

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);



            // AST REWRITE
            // elements: declaration
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 128:22: -> ^( DECLS ( declaration )* )
            {
                // Evil.g:128:25: ^( DECLS ( declaration )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECLS, "DECLS"), root_1);

                // Evil.g:128:33: ( declaration )*
                while ( stream_declaration.hasNext() ) {
                    adaptor.addChild(root_1, stream_declaration.nextTree());

                }
                stream_declaration.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "declarations"

    public static class declaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declaration"
    // Evil.g:130:1: declaration : t= type ilist= id_list SEMI -> ^( DECLLIST ^( TYPE $t) $ilist) ;
    public final EvilParser.declaration_return declaration() throws RecognitionException {
        EvilParser.declaration_return retval = new EvilParser.declaration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMI18=null;
        EvilParser.type_return t = null;

        EvilParser.id_list_return ilist = null;


        Object SEMI18_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        RewriteRuleSubtreeStream stream_id_list=new RewriteRuleSubtreeStream(adaptor,"rule id_list");
        try {
            // Evil.g:131:4: (t= type ilist= id_list SEMI -> ^( DECLLIST ^( TYPE $t) $ilist) )
            // Evil.g:131:7: t= type ilist= id_list SEMI
            {
            pushFollow(FOLLOW_type_in_declaration1320);
            t=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_type.add(t.getTree());
            pushFollow(FOLLOW_id_list_in_declaration1324);
            ilist=id_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id_list.add(ilist.getTree());
            SEMI18=(Token)match(input,SEMI,FOLLOW_SEMI_in_declaration1326); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI18);



            // AST REWRITE
            // elements: t, ilist
            // token labels: 
            // rule labels: retval, t, ilist
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.tree:null);
            RewriteRuleSubtreeStream stream_ilist=new RewriteRuleSubtreeStream(adaptor,"rule ilist",ilist!=null?ilist.tree:null);

            root_0 = (Object)adaptor.nil();
            // 132:7: -> ^( DECLLIST ^( TYPE $t) $ilist)
            {
                // Evil.g:132:10: ^( DECLLIST ^( TYPE $t) $ilist)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECLLIST, "DECLLIST"), root_1);

                // Evil.g:132:21: ^( TYPE $t)
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPE, "TYPE"), root_2);

                adaptor.addChild(root_2, stream_t.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                adaptor.addChild(root_1, stream_ilist.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "declaration"

    public static class id_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "id_list"
    // Evil.g:134:1: id_list : ID ( COMMA ID )* ;
    public final EvilParser.id_list_return id_list() throws RecognitionException {
        EvilParser.id_list_return retval = new EvilParser.id_list_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID19=null;
        Token COMMA20=null;
        Token ID21=null;

        Object ID19_tree=null;
        Object COMMA20_tree=null;
        Object ID21_tree=null;

        try {
            // Evil.g:135:4: ( ID ( COMMA ID )* )
            // Evil.g:135:7: ID ( COMMA ID )*
            {
            root_0 = (Object)adaptor.nil();

            ID19=(Token)match(input,ID,FOLLOW_ID_in_id_list1363); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID19_tree = (Object)adaptor.create(ID19);
            adaptor.addChild(root_0, ID19_tree);
            }
            // Evil.g:135:10: ( COMMA ID )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==COMMA) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // Evil.g:135:11: COMMA ID
            	    {
            	    COMMA20=(Token)match(input,COMMA,FOLLOW_COMMA_in_id_list1366); if (state.failed) return retval;
            	    ID21=(Token)match(input,ID,FOLLOW_ID_in_id_list1369); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    ID21_tree = (Object)adaptor.create(ID21);
            	    adaptor.addChild(root_0, ID21_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "id_list"

    public static class functions_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "functions"
    // Evil.g:137:1: functions : ( function )* -> ^( FUNCS ( function )* ) ;
    public final EvilParser.functions_return functions() throws RecognitionException {
        EvilParser.functions_return retval = new EvilParser.functions_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        EvilParser.function_return function22 = null;


        RewriteRuleSubtreeStream stream_function=new RewriteRuleSubtreeStream(adaptor,"rule function");
        try {
            // Evil.g:138:4: ( ( function )* -> ^( FUNCS ( function )* ) )
            // Evil.g:138:7: ( function )*
            {
            // Evil.g:138:7: ( function )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==FUN) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // Evil.g:138:7: function
            	    {
            	    pushFollow(FOLLOW_function_in_functions1386);
            	    function22=function();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_function.add(function22.getTree());

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);



            // AST REWRITE
            // elements: function
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 138:17: -> ^( FUNCS ( function )* )
            {
                // Evil.g:138:20: ^( FUNCS ( function )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNCS, "FUNCS"), root_1);

                // Evil.g:138:28: ( function )*
                while ( stream_function.hasNext() ) {
                    adaptor.addChild(root_1, stream_function.nextTree());

                }
                stream_function.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "functions"

    public static class function_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "function"
    // Evil.g:140:1: function : FUN id= ID p= parameters r= return_type LBRACE d= declarations s= statement_list RBRACE -> ^( FUN $id $p ^( RETTYPE $r) $d $s) ;
    public final EvilParser.function_return function() throws RecognitionException {
        EvilParser.function_return retval = new EvilParser.function_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;
        Token FUN23=null;
        Token LBRACE24=null;
        Token RBRACE25=null;
        EvilParser.parameters_return p = null;

        EvilParser.return_type_return r = null;

        EvilParser.declarations_return d = null;

        EvilParser.statement_list_return s = null;


        Object id_tree=null;
        Object FUN23_tree=null;
        Object LBRACE24_tree=null;
        Object RBRACE25_tree=null;
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_FUN=new RewriteRuleTokenStream(adaptor,"token FUN");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_statement_list=new RewriteRuleSubtreeStream(adaptor,"rule statement_list");
        RewriteRuleSubtreeStream stream_parameters=new RewriteRuleSubtreeStream(adaptor,"rule parameters");
        RewriteRuleSubtreeStream stream_return_type=new RewriteRuleSubtreeStream(adaptor,"rule return_type");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // Evil.g:141:4: ( FUN id= ID p= parameters r= return_type LBRACE d= declarations s= statement_list RBRACE -> ^( FUN $id $p ^( RETTYPE $r) $d $s) )
            // Evil.g:141:7: FUN id= ID p= parameters r= return_type LBRACE d= declarations s= statement_list RBRACE
            {
            FUN23=(Token)match(input,FUN,FOLLOW_FUN_in_function1411); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_FUN.add(FUN23);

            id=(Token)match(input,ID,FOLLOW_ID_in_function1415); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            pushFollow(FOLLOW_parameters_in_function1419);
            p=parameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_parameters.add(p.getTree());
            pushFollow(FOLLOW_return_type_in_function1423);
            r=return_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_return_type.add(r.getTree());
            LBRACE24=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_function1425); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LBRACE.add(LBRACE24);

            pushFollow(FOLLOW_declarations_in_function1429);
            d=declarations();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declarations.add(d.getTree());
            pushFollow(FOLLOW_statement_list_in_function1433);
            s=statement_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_statement_list.add(s.getTree());
            RBRACE25=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_function1435); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RBRACE.add(RBRACE25);



            // AST REWRITE
            // elements: r, p, s, id, d, FUN
            // token labels: id
            // rule labels: retval, d, s, r, p
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.tree:null);
            RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);
            RewriteRuleSubtreeStream stream_r=new RewriteRuleSubtreeStream(adaptor,"rule r",r!=null?r.tree:null);
            RewriteRuleSubtreeStream stream_p=new RewriteRuleSubtreeStream(adaptor,"rule p",p!=null?p.tree:null);

            root_0 = (Object)adaptor.nil();
            // 142:7: -> ^( FUN $id $p ^( RETTYPE $r) $d $s)
            {
                // Evil.g:142:10: ^( FUN $id $p ^( RETTYPE $r) $d $s)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_FUN.nextNode(), root_1);

                adaptor.addChild(root_1, stream_id.nextNode());
                adaptor.addChild(root_1, stream_p.nextTree());
                // Evil.g:142:23: ^( RETTYPE $r)
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(RETTYPE, "RETTYPE"), root_2);

                adaptor.addChild(root_2, stream_r.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                adaptor.addChild(root_1, stream_d.nextTree());
                adaptor.addChild(root_1, stream_s.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "function"

    public static class parameters_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameters"
    // Evil.g:144:1: parameters : LPAREN ( decl ( COMMA decl )* )? RPAREN -> ^( PARAMS ( decl )* ) ;
    public final EvilParser.parameters_return parameters() throws RecognitionException {
        EvilParser.parameters_return retval = new EvilParser.parameters_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LPAREN26=null;
        Token COMMA28=null;
        Token RPAREN30=null;
        EvilParser.decl_return decl27 = null;

        EvilParser.decl_return decl29 = null;


        Object LPAREN26_tree=null;
        Object COMMA28_tree=null;
        Object RPAREN30_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_decl=new RewriteRuleSubtreeStream(adaptor,"rule decl");
        try {
            // Evil.g:145:4: ( LPAREN ( decl ( COMMA decl )* )? RPAREN -> ^( PARAMS ( decl )* ) )
            // Evil.g:145:7: LPAREN ( decl ( COMMA decl )* )? RPAREN
            {
            LPAREN26=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_parameters1481); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN26);

            // Evil.g:145:14: ( decl ( COMMA decl )* )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( ((LA9_0>=STRUCT && LA9_0<=BOOL)) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // Evil.g:145:15: decl ( COMMA decl )*
                    {
                    pushFollow(FOLLOW_decl_in_parameters1484);
                    decl27=decl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_decl.add(decl27.getTree());
                    // Evil.g:145:20: ( COMMA decl )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==COMMA) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // Evil.g:145:21: COMMA decl
                    	    {
                    	    COMMA28=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameters1487); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA28);

                    	    pushFollow(FOLLOW_decl_in_parameters1489);
                    	    decl29=decl();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_decl.add(decl29.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    }
                    break;

            }

            RPAREN30=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_parameters1495); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN30);



            // AST REWRITE
            // elements: decl
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 146:7: -> ^( PARAMS ( decl )* )
            {
                // Evil.g:146:10: ^( PARAMS ( decl )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARAMS, "PARAMS"), root_1);

                // Evil.g:146:19: ( decl )*
                while ( stream_decl.hasNext() ) {
                    adaptor.addChild(root_1, stream_decl.nextTree());

                }
                stream_decl.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "parameters"

    public static class return_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "return_type"
    // Evil.g:148:1: return_type : ( type | VOID );
    public final EvilParser.return_type_return return_type() throws RecognitionException {
        EvilParser.return_type_return retval = new EvilParser.return_type_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token VOID32=null;
        EvilParser.type_return type31 = null;


        Object VOID32_tree=null;

        try {
            // Evil.g:149:4: ( type | VOID )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>=STRUCT && LA10_0<=BOOL)) ) {
                alt10=1;
            }
            else if ( (LA10_0==VOID) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // Evil.g:149:7: type
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_type_in_return_type1525);
                    type31=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type31.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:150:7: VOID
                    {
                    root_0 = (Object)adaptor.nil();

                    VOID32=(Token)match(input,VOID,FOLLOW_VOID_in_return_type1533); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VOID32_tree = (Object)adaptor.create(VOID32);
                    adaptor.addChild(root_0, VOID32_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "return_type"

    public static class statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statement"
    // Evil.g:152:1: statement : ( block | ( lvalue ASSIGN )=> assignment | print | read | conditional | loop | delete | ret | ( ID LPAREN )=> invocation );
    public final EvilParser.statement_return statement() throws RecognitionException {
        EvilParser.statement_return retval = new EvilParser.statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        EvilParser.block_return block33 = null;

        EvilParser.assignment_return assignment34 = null;

        EvilParser.print_return print35 = null;

        EvilParser.read_return read36 = null;

        EvilParser.conditional_return conditional37 = null;

        EvilParser.loop_return loop38 = null;

        EvilParser.delete_return delete39 = null;

        EvilParser.ret_return ret40 = null;

        EvilParser.invocation_return invocation41 = null;



        try {
            // Evil.g:153:4: ( block | ( lvalue ASSIGN )=> assignment | print | read | conditional | loop | delete | ret | ( ID LPAREN )=> invocation )
            int alt11=9;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // Evil.g:153:7: block
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_block_in_statement1548);
                    block33=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, block33.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:154:7: ( lvalue ASSIGN )=> assignment
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_assignment_in_statement1564);
                    assignment34=assignment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignment34.getTree());

                    }
                    break;
                case 3 :
                    // Evil.g:155:7: print
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_print_in_statement1572);
                    print35=print();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, print35.getTree());

                    }
                    break;
                case 4 :
                    // Evil.g:156:7: read
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_read_in_statement1580);
                    read36=read();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, read36.getTree());

                    }
                    break;
                case 5 :
                    // Evil.g:157:7: conditional
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_conditional_in_statement1588);
                    conditional37=conditional();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, conditional37.getTree());

                    }
                    break;
                case 6 :
                    // Evil.g:158:7: loop
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_loop_in_statement1596);
                    loop38=loop();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, loop38.getTree());

                    }
                    break;
                case 7 :
                    // Evil.g:159:7: delete
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_delete_in_statement1604);
                    delete39=delete();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, delete39.getTree());

                    }
                    break;
                case 8 :
                    // Evil.g:160:7: ret
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ret_in_statement1612);
                    ret40=ret();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ret40.getTree());

                    }
                    break;
                case 9 :
                    // Evil.g:161:7: ( ID LPAREN )=> invocation
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_invocation_in_statement1628);
                    invocation41=invocation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, invocation41.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "statement"

    public static class block_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "block"
    // Evil.g:163:1: block : LBRACE s= statement_list RBRACE -> ^( BLOCK $s) ;
    public final EvilParser.block_return block() throws RecognitionException {
        EvilParser.block_return retval = new EvilParser.block_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LBRACE42=null;
        Token RBRACE43=null;
        EvilParser.statement_list_return s = null;


        Object LBRACE42_tree=null;
        Object RBRACE43_tree=null;
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_statement_list=new RewriteRuleSubtreeStream(adaptor,"rule statement_list");
        try {
            // Evil.g:164:4: ( LBRACE s= statement_list RBRACE -> ^( BLOCK $s) )
            // Evil.g:164:7: LBRACE s= statement_list RBRACE
            {
            LBRACE42=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_block1643); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LBRACE.add(LBRACE42);

            pushFollow(FOLLOW_statement_list_in_block1647);
            s=statement_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_statement_list.add(s.getTree());
            RBRACE43=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_block1649); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RBRACE.add(RBRACE43);



            // AST REWRITE
            // elements: s
            // token labels: 
            // rule labels: retval, s
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);

            root_0 = (Object)adaptor.nil();
            // 165:7: -> ^( BLOCK $s)
            {
                // Evil.g:165:10: ^( BLOCK $s)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BLOCK, "BLOCK"), root_1);

                adaptor.addChild(root_1, stream_s.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "block"

    public static class statement_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statement_list"
    // Evil.g:167:1: statement_list : ( statement )* -> ^( STMTS ( statement )* ) ;
    public final EvilParser.statement_list_return statement_list() throws RecognitionException {
        EvilParser.statement_list_return retval = new EvilParser.statement_list_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        EvilParser.statement_return statement44 = null;


        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        try {
            // Evil.g:168:4: ( ( statement )* -> ^( STMTS ( statement )* ) )
            // Evil.g:168:7: ( statement )*
            {
            // Evil.g:168:7: ( statement )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==PRINT||(LA12_0>=READ && LA12_0<=IF)||(LA12_0>=WHILE && LA12_0<=RETURN)||LA12_0==LBRACE||LA12_0==ID) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // Evil.g:168:8: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_statement_list1680);
            	    statement44=statement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_statement.add(statement44.getTree());

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);



            // AST REWRITE
            // elements: statement
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 168:20: -> ^( STMTS ( statement )* )
            {
                // Evil.g:168:23: ^( STMTS ( statement )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(STMTS, "STMTS"), root_1);

                // Evil.g:168:31: ( statement )*
                while ( stream_statement.hasNext() ) {
                    adaptor.addChild(root_1, stream_statement.nextTree());

                }
                stream_statement.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "statement_list"

    public static class assignment_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assignment"
    // Evil.g:170:1: assignment : l= lvalue ASSIGN e= expression SEMI -> ^( ASSIGN $e $l) ;
    public final EvilParser.assignment_return assignment() throws RecognitionException {
        EvilParser.assignment_return retval = new EvilParser.assignment_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ASSIGN45=null;
        Token SEMI46=null;
        EvilParser.lvalue_return l = null;

        EvilParser.expression_return e = null;


        Object ASSIGN45_tree=null;
        Object SEMI46_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_lvalue=new RewriteRuleSubtreeStream(adaptor,"rule lvalue");
        try {
            // Evil.g:171:4: (l= lvalue ASSIGN e= expression SEMI -> ^( ASSIGN $e $l) )
            // Evil.g:171:7: l= lvalue ASSIGN e= expression SEMI
            {
            pushFollow(FOLLOW_lvalue_in_assignment1708);
            l=lvalue();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_lvalue.add(l.getTree());
            ASSIGN45=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignment1710); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ASSIGN.add(ASSIGN45);

            pushFollow(FOLLOW_expression_in_assignment1714);
            e=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(e.getTree());
            SEMI46=(Token)match(input,SEMI,FOLLOW_SEMI_in_assignment1716); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI46);



            // AST REWRITE
            // elements: l, ASSIGN, e
            // token labels: 
            // rule labels: retval, e, l
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_e=new RewriteRuleSubtreeStream(adaptor,"rule e",e!=null?e.tree:null);
            RewriteRuleSubtreeStream stream_l=new RewriteRuleSubtreeStream(adaptor,"rule l",l!=null?l.tree:null);

            root_0 = (Object)adaptor.nil();
            // 172:7: -> ^( ASSIGN $e $l)
            {
                // Evil.g:172:10: ^( ASSIGN $e $l)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_ASSIGN.nextNode(), root_1);

                adaptor.addChild(root_1, stream_e.nextTree());
                adaptor.addChild(root_1, stream_l.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "assignment"

    public static class print_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "print"
    // Evil.g:174:1: print : PRINT expression ( ENDL )? SEMI ;
    public final EvilParser.print_return print() throws RecognitionException {
        EvilParser.print_return retval = new EvilParser.print_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PRINT47=null;
        Token ENDL49=null;
        Token SEMI50=null;
        EvilParser.expression_return expression48 = null;


        Object PRINT47_tree=null;
        Object ENDL49_tree=null;
        Object SEMI50_tree=null;

        try {
            // Evil.g:175:4: ( PRINT expression ( ENDL )? SEMI )
            // Evil.g:175:7: PRINT expression ( ENDL )? SEMI
            {
            root_0 = (Object)adaptor.nil();

            PRINT47=(Token)match(input,PRINT,FOLLOW_PRINT_in_print1749); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PRINT47_tree = (Object)adaptor.create(PRINT47);
            root_0 = (Object)adaptor.becomeRoot(PRINT47_tree, root_0);
            }
            pushFollow(FOLLOW_expression_in_print1752);
            expression48=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression48.getTree());
            // Evil.g:175:25: ( ENDL )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==ENDL) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // Evil.g:175:26: ENDL
                    {
                    ENDL49=(Token)match(input,ENDL,FOLLOW_ENDL_in_print1755); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ENDL49_tree = (Object)adaptor.create(ENDL49);
                    adaptor.addChild(root_0, ENDL49_tree);
                    }

                    }
                    break;

            }

            SEMI50=(Token)match(input,SEMI,FOLLOW_SEMI_in_print1759); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "print"

    public static class read_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "read"
    // Evil.g:177:1: read : READ lvalue SEMI ;
    public final EvilParser.read_return read() throws RecognitionException {
        EvilParser.read_return retval = new EvilParser.read_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token READ51=null;
        Token SEMI53=null;
        EvilParser.lvalue_return lvalue52 = null;


        Object READ51_tree=null;
        Object SEMI53_tree=null;

        try {
            // Evil.g:178:4: ( READ lvalue SEMI )
            // Evil.g:178:7: READ lvalue SEMI
            {
            root_0 = (Object)adaptor.nil();

            READ51=(Token)match(input,READ,FOLLOW_READ_in_read1775); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            READ51_tree = (Object)adaptor.create(READ51);
            root_0 = (Object)adaptor.becomeRoot(READ51_tree, root_0);
            }
            pushFollow(FOLLOW_lvalue_in_read1778);
            lvalue52=lvalue();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, lvalue52.getTree());
            SEMI53=(Token)match(input,SEMI,FOLLOW_SEMI_in_read1780); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "read"

    public static class conditional_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "conditional"
    // Evil.g:180:1: conditional : IF LPAREN expression RPAREN block ( ELSE block )? ;
    public final EvilParser.conditional_return conditional() throws RecognitionException {
        EvilParser.conditional_return retval = new EvilParser.conditional_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IF54=null;
        Token LPAREN55=null;
        Token RPAREN57=null;
        Token ELSE59=null;
        EvilParser.expression_return expression56 = null;

        EvilParser.block_return block58 = null;

        EvilParser.block_return block60 = null;


        Object IF54_tree=null;
        Object LPAREN55_tree=null;
        Object RPAREN57_tree=null;
        Object ELSE59_tree=null;

        try {
            // Evil.g:181:4: ( IF LPAREN expression RPAREN block ( ELSE block )? )
            // Evil.g:181:7: IF LPAREN expression RPAREN block ( ELSE block )?
            {
            root_0 = (Object)adaptor.nil();

            IF54=(Token)match(input,IF,FOLLOW_IF_in_conditional1796); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IF54_tree = (Object)adaptor.create(IF54);
            root_0 = (Object)adaptor.becomeRoot(IF54_tree, root_0);
            }
            LPAREN55=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_conditional1799); if (state.failed) return retval;
            pushFollow(FOLLOW_expression_in_conditional1802);
            expression56=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression56.getTree());
            RPAREN57=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_conditional1804); if (state.failed) return retval;
            pushFollow(FOLLOW_block_in_conditional1807);
            block58=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, block58.getTree());
            // Evil.g:181:44: ( ELSE block )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==ELSE) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // Evil.g:181:45: ELSE block
                    {
                    ELSE59=(Token)match(input,ELSE,FOLLOW_ELSE_in_conditional1810); if (state.failed) return retval;
                    pushFollow(FOLLOW_block_in_conditional1813);
                    block60=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, block60.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "conditional"

    public static class loop_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "loop"
    // Evil.g:183:1: loop : WHILE LPAREN e= expression RPAREN b= block -> ^( WHILE $e $b $e) ;
    public final EvilParser.loop_return loop() throws RecognitionException {
        EvilParser.loop_return retval = new EvilParser.loop_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WHILE61=null;
        Token LPAREN62=null;
        Token RPAREN63=null;
        EvilParser.expression_return e = null;

        EvilParser.block_return b = null;


        Object WHILE61_tree=null;
        Object LPAREN62_tree=null;
        Object RPAREN63_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_WHILE=new RewriteRuleTokenStream(adaptor,"token WHILE");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // Evil.g:184:4: ( WHILE LPAREN e= expression RPAREN b= block -> ^( WHILE $e $b $e) )
            // Evil.g:184:7: WHILE LPAREN e= expression RPAREN b= block
            {
            WHILE61=(Token)match(input,WHILE,FOLLOW_WHILE_in_loop1830); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_WHILE.add(WHILE61);

            LPAREN62=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_loop1832); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN62);

            pushFollow(FOLLOW_expression_in_loop1836);
            e=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(e.getTree());
            RPAREN63=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_loop1838); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN63);

            pushFollow(FOLLOW_block_in_loop1842);
            b=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(b.getTree());


            // AST REWRITE
            // elements: e, b, e, WHILE
            // token labels: 
            // rule labels: retval, e, b
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_e=new RewriteRuleSubtreeStream(adaptor,"rule e",e!=null?e.tree:null);
            RewriteRuleSubtreeStream stream_b=new RewriteRuleSubtreeStream(adaptor,"rule b",b!=null?b.tree:null);

            root_0 = (Object)adaptor.nil();
            // 185:7: -> ^( WHILE $e $b $e)
            {
                // Evil.g:185:10: ^( WHILE $e $b $e)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_WHILE.nextNode(), root_1);

                adaptor.addChild(root_1, stream_e.nextTree());
                adaptor.addChild(root_1, stream_b.nextTree());
                adaptor.addChild(root_1, stream_e.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "loop"

    public static class delete_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "delete"
    // Evil.g:187:1: delete : DELETE expression SEMI ;
    public final EvilParser.delete_return delete() throws RecognitionException {
        EvilParser.delete_return retval = new EvilParser.delete_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DELETE64=null;
        Token SEMI66=null;
        EvilParser.expression_return expression65 = null;


        Object DELETE64_tree=null;
        Object SEMI66_tree=null;

        try {
            // Evil.g:188:4: ( DELETE expression SEMI )
            // Evil.g:188:7: DELETE expression SEMI
            {
            root_0 = (Object)adaptor.nil();

            DELETE64=(Token)match(input,DELETE,FOLLOW_DELETE_in_delete1878); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DELETE64_tree = (Object)adaptor.create(DELETE64);
            root_0 = (Object)adaptor.becomeRoot(DELETE64_tree, root_0);
            }
            pushFollow(FOLLOW_expression_in_delete1881);
            expression65=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression65.getTree());
            SEMI66=(Token)match(input,SEMI,FOLLOW_SEMI_in_delete1883); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "delete"

    public static class ret_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ret"
    // Evil.g:190:1: ret : RETURN ( expression )? SEMI ;
    public final EvilParser.ret_return ret() throws RecognitionException {
        EvilParser.ret_return retval = new EvilParser.ret_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token RETURN67=null;
        Token SEMI69=null;
        EvilParser.expression_return expression68 = null;


        Object RETURN67_tree=null;
        Object SEMI69_tree=null;

        try {
            // Evil.g:191:4: ( RETURN ( expression )? SEMI )
            // Evil.g:191:7: RETURN ( expression )? SEMI
            {
            root_0 = (Object)adaptor.nil();

            RETURN67=(Token)match(input,RETURN,FOLLOW_RETURN_in_ret1899); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RETURN67_tree = (Object)adaptor.create(RETURN67);
            root_0 = (Object)adaptor.becomeRoot(RETURN67_tree, root_0);
            }
            // Evil.g:191:15: ( expression )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=TRUE && LA15_0<=NULL)||LA15_0==LPAREN||LA15_0==MINUS||(LA15_0>=NOT && LA15_0<=INTEGER)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // Evil.g:191:16: expression
                    {
                    pushFollow(FOLLOW_expression_in_ret1903);
                    expression68=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression68.getTree());

                    }
                    break;

            }

            SEMI69=(Token)match(input,SEMI,FOLLOW_SEMI_in_ret1907); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ret"

    public static class invocation_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "invocation"
    // Evil.g:193:1: invocation : id= ID a= arguments SEMI -> ^( INVOKE $id $a) ;
    public final EvilParser.invocation_return invocation() throws RecognitionException {
        EvilParser.invocation_return retval = new EvilParser.invocation_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;
        Token SEMI70=null;
        EvilParser.arguments_return a = null;


        Object id_tree=null;
        Object SEMI70_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_arguments=new RewriteRuleSubtreeStream(adaptor,"rule arguments");
        try {
            // Evil.g:194:4: (id= ID a= arguments SEMI -> ^( INVOKE $id $a) )
            // Evil.g:194:7: id= ID a= arguments SEMI
            {
            id=(Token)match(input,ID,FOLLOW_ID_in_invocation1925); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            pushFollow(FOLLOW_arguments_in_invocation1929);
            a=arguments();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_arguments.add(a.getTree());
            SEMI70=(Token)match(input,SEMI,FOLLOW_SEMI_in_invocation1931); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI70);



            // AST REWRITE
            // elements: id, a
            // token labels: id
            // rule labels: retval, a
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.tree:null);

            root_0 = (Object)adaptor.nil();
            // 195:7: -> ^( INVOKE $id $a)
            {
                // Evil.g:195:10: ^( INVOKE $id $a)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INVOKE, "INVOKE"), root_1);

                adaptor.addChild(root_1, stream_id.nextNode());
                adaptor.addChild(root_1, stream_a.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "invocation"

    public static class lvalue_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lvalue"
    // Evil.g:197:1: lvalue : ID ( DOT ID )* ;
    public final EvilParser.lvalue_return lvalue() throws RecognitionException {
        EvilParser.lvalue_return retval = new EvilParser.lvalue_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID71=null;
        Token DOT72=null;
        Token ID73=null;

        Object ID71_tree=null;
        Object DOT72_tree=null;
        Object ID73_tree=null;

        try {
            // Evil.g:198:4: ( ID ( DOT ID )* )
            // Evil.g:198:7: ID ( DOT ID )*
            {
            root_0 = (Object)adaptor.nil();

            ID71=(Token)match(input,ID,FOLLOW_ID_in_lvalue1964); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID71_tree = (Object)adaptor.create(ID71);
            adaptor.addChild(root_0, ID71_tree);
            }
            // Evil.g:198:10: ( DOT ID )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==DOT) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // Evil.g:198:11: DOT ID
            	    {
            	    DOT72=(Token)match(input,DOT,FOLLOW_DOT_in_lvalue1967); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    DOT72_tree = (Object)adaptor.create(DOT72);
            	    root_0 = (Object)adaptor.becomeRoot(DOT72_tree, root_0);
            	    }
            	    ID73=(Token)match(input,ID,FOLLOW_ID_in_lvalue1970); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    ID73_tree = (Object)adaptor.create(ID73);
            	    adaptor.addChild(root_0, ID73_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lvalue"

    public static class expression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression"
    // Evil.g:200:1: expression : boolterm ( ( AND | OR ) boolterm )* ;
    public final EvilParser.expression_return expression() throws RecognitionException {
        EvilParser.expression_return retval = new EvilParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AND75=null;
        Token OR76=null;
        EvilParser.boolterm_return boolterm74 = null;

        EvilParser.boolterm_return boolterm77 = null;


        Object AND75_tree=null;
        Object OR76_tree=null;

        try {
            // Evil.g:201:4: ( boolterm ( ( AND | OR ) boolterm )* )
            // Evil.g:201:7: boolterm ( ( AND | OR ) boolterm )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_boolterm_in_expression1987);
            boolterm74=boolterm();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, boolterm74.getTree());
            // Evil.g:201:16: ( ( AND | OR ) boolterm )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=AND && LA18_0<=OR)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // Evil.g:201:17: ( AND | OR ) boolterm
            	    {
            	    // Evil.g:201:17: ( AND | OR )
            	    int alt17=2;
            	    int LA17_0 = input.LA(1);

            	    if ( (LA17_0==AND) ) {
            	        alt17=1;
            	    }
            	    else if ( (LA17_0==OR) ) {
            	        alt17=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 17, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt17) {
            	        case 1 :
            	            // Evil.g:201:18: AND
            	            {
            	            AND75=(Token)match(input,AND,FOLLOW_AND_in_expression1991); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            AND75_tree = (Object)adaptor.create(AND75);
            	            root_0 = (Object)adaptor.becomeRoot(AND75_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Evil.g:201:25: OR
            	            {
            	            OR76=(Token)match(input,OR,FOLLOW_OR_in_expression1996); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            OR76_tree = (Object)adaptor.create(OR76);
            	            root_0 = (Object)adaptor.becomeRoot(OR76_tree, root_0);
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_boolterm_in_expression2000);
            	    boolterm77=boolterm();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, boolterm77.getTree());

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expression"

    public static class boolterm_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "boolterm"
    // Evil.g:203:1: boolterm : simple ( ( EQ | LT | GT | NE | LE | GE ) simple )? ;
    public final EvilParser.boolterm_return boolterm() throws RecognitionException {
        EvilParser.boolterm_return retval = new EvilParser.boolterm_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EQ79=null;
        Token LT80=null;
        Token GT81=null;
        Token NE82=null;
        Token LE83=null;
        Token GE84=null;
        EvilParser.simple_return simple78 = null;

        EvilParser.simple_return simple85 = null;


        Object EQ79_tree=null;
        Object LT80_tree=null;
        Object GT81_tree=null;
        Object NE82_tree=null;
        Object LE83_tree=null;
        Object GE84_tree=null;

        try {
            // Evil.g:204:4: ( simple ( ( EQ | LT | GT | NE | LE | GE ) simple )? )
            // Evil.g:204:7: simple ( ( EQ | LT | GT | NE | LE | GE ) simple )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_simple_in_boolterm2017);
            simple78=simple();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, simple78.getTree());
            // Evil.g:204:14: ( ( EQ | LT | GT | NE | LE | GE ) simple )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( ((LA20_0>=EQ && LA20_0<=GE)) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // Evil.g:204:15: ( EQ | LT | GT | NE | LE | GE ) simple
                    {
                    // Evil.g:204:15: ( EQ | LT | GT | NE | LE | GE )
                    int alt19=6;
                    switch ( input.LA(1) ) {
                    case EQ:
                        {
                        alt19=1;
                        }
                        break;
                    case LT:
                        {
                        alt19=2;
                        }
                        break;
                    case GT:
                        {
                        alt19=3;
                        }
                        break;
                    case NE:
                        {
                        alt19=4;
                        }
                        break;
                    case LE:
                        {
                        alt19=5;
                        }
                        break;
                    case GE:
                        {
                        alt19=6;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 19, 0, input);

                        throw nvae;
                    }

                    switch (alt19) {
                        case 1 :
                            // Evil.g:204:16: EQ
                            {
                            EQ79=(Token)match(input,EQ,FOLLOW_EQ_in_boolterm2021); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            EQ79_tree = (Object)adaptor.create(EQ79);
                            root_0 = (Object)adaptor.becomeRoot(EQ79_tree, root_0);
                            }

                            }
                            break;
                        case 2 :
                            // Evil.g:204:22: LT
                            {
                            LT80=(Token)match(input,LT,FOLLOW_LT_in_boolterm2026); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            LT80_tree = (Object)adaptor.create(LT80);
                            root_0 = (Object)adaptor.becomeRoot(LT80_tree, root_0);
                            }

                            }
                            break;
                        case 3 :
                            // Evil.g:204:28: GT
                            {
                            GT81=(Token)match(input,GT,FOLLOW_GT_in_boolterm2031); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            GT81_tree = (Object)adaptor.create(GT81);
                            root_0 = (Object)adaptor.becomeRoot(GT81_tree, root_0);
                            }

                            }
                            break;
                        case 4 :
                            // Evil.g:204:34: NE
                            {
                            NE82=(Token)match(input,NE,FOLLOW_NE_in_boolterm2036); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            NE82_tree = (Object)adaptor.create(NE82);
                            root_0 = (Object)adaptor.becomeRoot(NE82_tree, root_0);
                            }

                            }
                            break;
                        case 5 :
                            // Evil.g:204:40: LE
                            {
                            LE83=(Token)match(input,LE,FOLLOW_LE_in_boolterm2041); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            LE83_tree = (Object)adaptor.create(LE83);
                            root_0 = (Object)adaptor.becomeRoot(LE83_tree, root_0);
                            }

                            }
                            break;
                        case 6 :
                            // Evil.g:204:46: GE
                            {
                            GE84=(Token)match(input,GE,FOLLOW_GE_in_boolterm2046); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            GE84_tree = (Object)adaptor.create(GE84);
                            root_0 = (Object)adaptor.becomeRoot(GE84_tree, root_0);
                            }

                            }
                            break;

                    }

                    pushFollow(FOLLOW_simple_in_boolterm2050);
                    simple85=simple();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simple85.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "boolterm"

    public static class simple_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "simple"
    // Evil.g:206:1: simple : term ( ( PLUS | MINUS ) term )* ;
    public final EvilParser.simple_return simple() throws RecognitionException {
        EvilParser.simple_return retval = new EvilParser.simple_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PLUS87=null;
        Token MINUS88=null;
        EvilParser.term_return term86 = null;

        EvilParser.term_return term89 = null;


        Object PLUS87_tree=null;
        Object MINUS88_tree=null;

        try {
            // Evil.g:207:4: ( term ( ( PLUS | MINUS ) term )* )
            // Evil.g:207:7: term ( ( PLUS | MINUS ) term )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_term_in_simple2067);
            term86=term();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, term86.getTree());
            // Evil.g:207:12: ( ( PLUS | MINUS ) term )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=PLUS && LA22_0<=MINUS)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // Evil.g:207:13: ( PLUS | MINUS ) term
            	    {
            	    // Evil.g:207:13: ( PLUS | MINUS )
            	    int alt21=2;
            	    int LA21_0 = input.LA(1);

            	    if ( (LA21_0==PLUS) ) {
            	        alt21=1;
            	    }
            	    else if ( (LA21_0==MINUS) ) {
            	        alt21=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 21, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt21) {
            	        case 1 :
            	            // Evil.g:207:14: PLUS
            	            {
            	            PLUS87=(Token)match(input,PLUS,FOLLOW_PLUS_in_simple2071); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            PLUS87_tree = (Object)adaptor.create(PLUS87);
            	            root_0 = (Object)adaptor.becomeRoot(PLUS87_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Evil.g:207:22: MINUS
            	            {
            	            MINUS88=(Token)match(input,MINUS,FOLLOW_MINUS_in_simple2076); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            MINUS88_tree = (Object)adaptor.create(MINUS88);
            	            root_0 = (Object)adaptor.becomeRoot(MINUS88_tree, root_0);
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_term_in_simple2080);
            	    term89=term();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, term89.getTree());

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "simple"

    public static class term_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "term"
    // Evil.g:209:1: term : unary ( ( TIMES | DIVIDE ) unary )* ;
    public final EvilParser.term_return term() throws RecognitionException {
        EvilParser.term_return retval = new EvilParser.term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TIMES91=null;
        Token DIVIDE92=null;
        EvilParser.unary_return unary90 = null;

        EvilParser.unary_return unary93 = null;


        Object TIMES91_tree=null;
        Object DIVIDE92_tree=null;

        try {
            // Evil.g:210:4: ( unary ( ( TIMES | DIVIDE ) unary )* )
            // Evil.g:210:7: unary ( ( TIMES | DIVIDE ) unary )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_unary_in_term2097);
            unary90=unary();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unary90.getTree());
            // Evil.g:210:13: ( ( TIMES | DIVIDE ) unary )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=TIMES && LA24_0<=DIVIDE)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // Evil.g:210:14: ( TIMES | DIVIDE ) unary
            	    {
            	    // Evil.g:210:14: ( TIMES | DIVIDE )
            	    int alt23=2;
            	    int LA23_0 = input.LA(1);

            	    if ( (LA23_0==TIMES) ) {
            	        alt23=1;
            	    }
            	    else if ( (LA23_0==DIVIDE) ) {
            	        alt23=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 23, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt23) {
            	        case 1 :
            	            // Evil.g:210:15: TIMES
            	            {
            	            TIMES91=(Token)match(input,TIMES,FOLLOW_TIMES_in_term2101); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            TIMES91_tree = (Object)adaptor.create(TIMES91);
            	            root_0 = (Object)adaptor.becomeRoot(TIMES91_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Evil.g:210:24: DIVIDE
            	            {
            	            DIVIDE92=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_term2106); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            DIVIDE92_tree = (Object)adaptor.create(DIVIDE92);
            	            root_0 = (Object)adaptor.becomeRoot(DIVIDE92_tree, root_0);
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_unary_in_term2110);
            	    unary93=unary();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unary93.getTree());

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "term"

    public static class unary_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unary"
    // Evil.g:212:1: unary : ( NOT odd_not | MINUS odd_neg | selector );
    public final EvilParser.unary_return unary() throws RecognitionException {
        EvilParser.unary_return retval = new EvilParser.unary_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NOT94=null;
        Token MINUS96=null;
        EvilParser.odd_not_return odd_not95 = null;

        EvilParser.odd_neg_return odd_neg97 = null;

        EvilParser.selector_return selector98 = null;


        Object NOT94_tree=null;
        Object MINUS96_tree=null;

        try {
            // Evil.g:213:4: ( NOT odd_not | MINUS odd_neg | selector )
            int alt25=3;
            switch ( input.LA(1) ) {
            case NOT:
                {
                alt25=1;
                }
                break;
            case MINUS:
                {
                alt25=2;
                }
                break;
            case TRUE:
            case FALSE:
            case NEW:
            case NULL:
            case LPAREN:
            case ID:
            case INTEGER:
                {
                alt25=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // Evil.g:213:7: NOT odd_not
                    {
                    root_0 = (Object)adaptor.nil();

                    NOT94=(Token)match(input,NOT,FOLLOW_NOT_in_unary2127); if (state.failed) return retval;
                    pushFollow(FOLLOW_odd_not_in_unary2130);
                    odd_not95=odd_not();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, odd_not95.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:214:7: MINUS odd_neg
                    {
                    root_0 = (Object)adaptor.nil();

                    MINUS96=(Token)match(input,MINUS,FOLLOW_MINUS_in_unary2138); if (state.failed) return retval;
                    pushFollow(FOLLOW_odd_neg_in_unary2141);
                    odd_neg97=odd_neg();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, odd_neg97.getTree());

                    }
                    break;
                case 3 :
                    // Evil.g:215:7: selector
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_selector_in_unary2149);
                    selector98=selector();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, selector98.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "unary"

    public static class odd_not_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "odd_not"
    // Evil.g:217:1: odd_not : ( NOT even_not | s= selector -> ^( NOT $s) );
    public final EvilParser.odd_not_return odd_not() throws RecognitionException {
        EvilParser.odd_not_return retval = new EvilParser.odd_not_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NOT99=null;
        EvilParser.selector_return s = null;

        EvilParser.even_not_return even_not100 = null;


        Object NOT99_tree=null;
        RewriteRuleSubtreeStream stream_selector=new RewriteRuleSubtreeStream(adaptor,"rule selector");
        try {
            // Evil.g:218:4: ( NOT even_not | s= selector -> ^( NOT $s) )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==NOT) ) {
                alt26=1;
            }
            else if ( ((LA26_0>=TRUE && LA26_0<=NULL)||LA26_0==LPAREN||(LA26_0>=ID && LA26_0<=INTEGER)) ) {
                alt26=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // Evil.g:218:7: NOT even_not
                    {
                    root_0 = (Object)adaptor.nil();

                    NOT99=(Token)match(input,NOT,FOLLOW_NOT_in_odd_not2164); if (state.failed) return retval;
                    pushFollow(FOLLOW_even_not_in_odd_not2167);
                    even_not100=even_not();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, even_not100.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:219:7: s= selector
                    {
                    pushFollow(FOLLOW_selector_in_odd_not2177);
                    s=selector();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_selector.add(s.getTree());


                    // AST REWRITE
                    // elements: s
                    // token labels: 
                    // rule labels: retval, s
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 220:7: -> ^( NOT $s)
                    {
                        // Evil.g:220:10: ^( NOT $s)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NOT, "NOT"), root_1);

                        adaptor.addChild(root_1, stream_s.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "odd_not"

    public static class even_not_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "even_not"
    // Evil.g:222:1: even_not : ( NOT odd_not | selector );
    public final EvilParser.even_not_return even_not() throws RecognitionException {
        EvilParser.even_not_return retval = new EvilParser.even_not_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NOT101=null;
        EvilParser.odd_not_return odd_not102 = null;

        EvilParser.selector_return selector103 = null;


        Object NOT101_tree=null;

        try {
            // Evil.g:223:4: ( NOT odd_not | selector )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==NOT) ) {
                alt27=1;
            }
            else if ( ((LA27_0>=TRUE && LA27_0<=NULL)||LA27_0==LPAREN||(LA27_0>=ID && LA27_0<=INTEGER)) ) {
                alt27=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // Evil.g:223:7: NOT odd_not
                    {
                    root_0 = (Object)adaptor.nil();

                    NOT101=(Token)match(input,NOT,FOLLOW_NOT_in_even_not2207); if (state.failed) return retval;
                    pushFollow(FOLLOW_odd_not_in_even_not2210);
                    odd_not102=odd_not();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, odd_not102.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:224:7: selector
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_selector_in_even_not2218);
                    selector103=selector();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, selector103.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "even_not"

    public static class odd_neg_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "odd_neg"
    // Evil.g:226:1: odd_neg : ( MINUS even_neg | s= selector -> ^( NEG $s) );
    public final EvilParser.odd_neg_return odd_neg() throws RecognitionException {
        EvilParser.odd_neg_return retval = new EvilParser.odd_neg_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MINUS104=null;
        EvilParser.selector_return s = null;

        EvilParser.even_neg_return even_neg105 = null;


        Object MINUS104_tree=null;
        RewriteRuleSubtreeStream stream_selector=new RewriteRuleSubtreeStream(adaptor,"rule selector");
        try {
            // Evil.g:227:4: ( MINUS even_neg | s= selector -> ^( NEG $s) )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==MINUS) ) {
                alt28=1;
            }
            else if ( ((LA28_0>=TRUE && LA28_0<=NULL)||LA28_0==LPAREN||(LA28_0>=ID && LA28_0<=INTEGER)) ) {
                alt28=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // Evil.g:227:7: MINUS even_neg
                    {
                    root_0 = (Object)adaptor.nil();

                    MINUS104=(Token)match(input,MINUS,FOLLOW_MINUS_in_odd_neg2233); if (state.failed) return retval;
                    pushFollow(FOLLOW_even_neg_in_odd_neg2236);
                    even_neg105=even_neg();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, even_neg105.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:228:7: s= selector
                    {
                    pushFollow(FOLLOW_selector_in_odd_neg2246);
                    s=selector();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_selector.add(s.getTree());


                    // AST REWRITE
                    // elements: s
                    // token labels: 
                    // rule labels: retval, s
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 229:7: -> ^( NEG $s)
                    {
                        // Evil.g:229:10: ^( NEG $s)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NEG, "NEG"), root_1);

                        adaptor.addChild(root_1, stream_s.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "odd_neg"

    public static class even_neg_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "even_neg"
    // Evil.g:231:1: even_neg : ( MINUS odd_neg | selector );
    public final EvilParser.even_neg_return even_neg() throws RecognitionException {
        EvilParser.even_neg_return retval = new EvilParser.even_neg_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MINUS106=null;
        EvilParser.odd_neg_return odd_neg107 = null;

        EvilParser.selector_return selector108 = null;


        Object MINUS106_tree=null;

        try {
            // Evil.g:232:4: ( MINUS odd_neg | selector )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==MINUS) ) {
                alt29=1;
            }
            else if ( ((LA29_0>=TRUE && LA29_0<=NULL)||LA29_0==LPAREN||(LA29_0>=ID && LA29_0<=INTEGER)) ) {
                alt29=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // Evil.g:232:7: MINUS odd_neg
                    {
                    root_0 = (Object)adaptor.nil();

                    MINUS106=(Token)match(input,MINUS,FOLLOW_MINUS_in_even_neg2276); if (state.failed) return retval;
                    pushFollow(FOLLOW_odd_neg_in_even_neg2279);
                    odd_neg107=odd_neg();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, odd_neg107.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:233:7: selector
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_selector_in_even_neg2287);
                    selector108=selector();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, selector108.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "even_neg"

    public static class selector_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "selector"
    // Evil.g:235:1: selector : factor ( DOT ID )* ;
    public final EvilParser.selector_return selector() throws RecognitionException {
        EvilParser.selector_return retval = new EvilParser.selector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DOT110=null;
        Token ID111=null;
        EvilParser.factor_return factor109 = null;


        Object DOT110_tree=null;
        Object ID111_tree=null;

        try {
            // Evil.g:236:4: ( factor ( DOT ID )* )
            // Evil.g:236:7: factor ( DOT ID )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_factor_in_selector2302);
            factor109=factor();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, factor109.getTree());
            // Evil.g:236:14: ( DOT ID )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==DOT) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // Evil.g:236:15: DOT ID
            	    {
            	    DOT110=(Token)match(input,DOT,FOLLOW_DOT_in_selector2305); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    DOT110_tree = (Object)adaptor.create(DOT110);
            	    root_0 = (Object)adaptor.becomeRoot(DOT110_tree, root_0);
            	    }
            	    ID111=(Token)match(input,ID,FOLLOW_ID_in_selector2308); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    ID111_tree = (Object)adaptor.create(ID111);
            	    adaptor.addChild(root_0, ID111_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "selector"

    public static class factor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "factor"
    // Evil.g:238:1: factor : ( LPAREN expression RPAREN | id= ID a= arguments -> ^( INVOKE $id $a) | ID | INTEGER | TRUE | FALSE | NEW ID | NULL );
    public final EvilParser.factor_return factor() throws RecognitionException {
        EvilParser.factor_return retval = new EvilParser.factor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;
        Token LPAREN112=null;
        Token RPAREN114=null;
        Token ID115=null;
        Token INTEGER116=null;
        Token TRUE117=null;
        Token FALSE118=null;
        Token NEW119=null;
        Token ID120=null;
        Token NULL121=null;
        EvilParser.arguments_return a = null;

        EvilParser.expression_return expression113 = null;


        Object id_tree=null;
        Object LPAREN112_tree=null;
        Object RPAREN114_tree=null;
        Object ID115_tree=null;
        Object INTEGER116_tree=null;
        Object TRUE117_tree=null;
        Object FALSE118_tree=null;
        Object NEW119_tree=null;
        Object ID120_tree=null;
        Object NULL121_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_arguments=new RewriteRuleSubtreeStream(adaptor,"rule arguments");
        try {
            // Evil.g:239:4: ( LPAREN expression RPAREN | id= ID a= arguments -> ^( INVOKE $id $a) | ID | INTEGER | TRUE | FALSE | NEW ID | NULL )
            int alt31=8;
            alt31 = dfa31.predict(input);
            switch (alt31) {
                case 1 :
                    // Evil.g:239:7: LPAREN expression RPAREN
                    {
                    root_0 = (Object)adaptor.nil();

                    LPAREN112=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_factor2325); if (state.failed) return retval;
                    pushFollow(FOLLOW_expression_in_factor2328);
                    expression113=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression113.getTree());
                    RPAREN114=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_factor2330); if (state.failed) return retval;

                    }
                    break;
                case 2 :
                    // Evil.g:240:7: id= ID a= arguments
                    {
                    id=(Token)match(input,ID,FOLLOW_ID_in_factor2341); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(id);

                    pushFollow(FOLLOW_arguments_in_factor2345);
                    a=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_arguments.add(a.getTree());


                    // AST REWRITE
                    // elements: id, a
                    // token labels: id
                    // rule labels: retval, a
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 241:7: -> ^( INVOKE $id $a)
                    {
                        // Evil.g:241:10: ^( INVOKE $id $a)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INVOKE, "INVOKE"), root_1);

                        adaptor.addChild(root_1, stream_id.nextNode());
                        adaptor.addChild(root_1, stream_a.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // Evil.g:242:7: ID
                    {
                    root_0 = (Object)adaptor.nil();

                    ID115=(Token)match(input,ID,FOLLOW_ID_in_factor2371); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID115_tree = (Object)adaptor.create(ID115);
                    adaptor.addChild(root_0, ID115_tree);
                    }

                    }
                    break;
                case 4 :
                    // Evil.g:243:7: INTEGER
                    {
                    root_0 = (Object)adaptor.nil();

                    INTEGER116=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_factor2379); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INTEGER116_tree = (Object)adaptor.create(INTEGER116);
                    adaptor.addChild(root_0, INTEGER116_tree);
                    }

                    }
                    break;
                case 5 :
                    // Evil.g:244:7: TRUE
                    {
                    root_0 = (Object)adaptor.nil();

                    TRUE117=(Token)match(input,TRUE,FOLLOW_TRUE_in_factor2387); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TRUE117_tree = (Object)adaptor.create(TRUE117);
                    adaptor.addChild(root_0, TRUE117_tree);
                    }

                    }
                    break;
                case 6 :
                    // Evil.g:245:7: FALSE
                    {
                    root_0 = (Object)adaptor.nil();

                    FALSE118=(Token)match(input,FALSE,FOLLOW_FALSE_in_factor2395); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FALSE118_tree = (Object)adaptor.create(FALSE118);
                    adaptor.addChild(root_0, FALSE118_tree);
                    }

                    }
                    break;
                case 7 :
                    // Evil.g:246:7: NEW ID
                    {
                    root_0 = (Object)adaptor.nil();

                    NEW119=(Token)match(input,NEW,FOLLOW_NEW_in_factor2403); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NEW119_tree = (Object)adaptor.create(NEW119);
                    root_0 = (Object)adaptor.becomeRoot(NEW119_tree, root_0);
                    }
                    ID120=(Token)match(input,ID,FOLLOW_ID_in_factor2406); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID120_tree = (Object)adaptor.create(ID120);
                    adaptor.addChild(root_0, ID120_tree);
                    }

                    }
                    break;
                case 8 :
                    // Evil.g:247:7: NULL
                    {
                    root_0 = (Object)adaptor.nil();

                    NULL121=(Token)match(input,NULL,FOLLOW_NULL_in_factor2414); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NULL121_tree = (Object)adaptor.create(NULL121);
                    adaptor.addChild(root_0, NULL121_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "factor"

    public static class arguments_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arguments"
    // Evil.g:249:1: arguments : LPAREN arg_list RPAREN ;
    public final EvilParser.arguments_return arguments() throws RecognitionException {
        EvilParser.arguments_return retval = new EvilParser.arguments_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LPAREN122=null;
        Token RPAREN124=null;
        EvilParser.arg_list_return arg_list123 = null;


        Object LPAREN122_tree=null;
        Object RPAREN124_tree=null;

        try {
            // Evil.g:250:4: ( LPAREN arg_list RPAREN )
            // Evil.g:250:7: LPAREN arg_list RPAREN
            {
            root_0 = (Object)adaptor.nil();

            LPAREN122=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_arguments2429); if (state.failed) return retval;
            pushFollow(FOLLOW_arg_list_in_arguments2432);
            arg_list123=arg_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, arg_list123.getTree());
            RPAREN124=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_arguments2434); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "arguments"

    public static class arg_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arg_list"
    // Evil.g:252:1: arg_list : ( expression ( COMMA expression )* -> ^( ARGS ( expression )+ ) | -> ARGS );
    public final EvilParser.arg_list_return arg_list() throws RecognitionException {
        EvilParser.arg_list_return retval = new EvilParser.arg_list_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA126=null;
        EvilParser.expression_return expression125 = null;

        EvilParser.expression_return expression127 = null;


        Object COMMA126_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // Evil.g:253:4: ( expression ( COMMA expression )* -> ^( ARGS ( expression )+ ) | -> ARGS )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( ((LA33_0>=TRUE && LA33_0<=NULL)||LA33_0==LPAREN||LA33_0==MINUS||(LA33_0>=NOT && LA33_0<=INTEGER)) ) {
                alt33=1;
            }
            else if ( (LA33_0==RPAREN) ) {
                alt33=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // Evil.g:253:7: expression ( COMMA expression )*
                    {
                    pushFollow(FOLLOW_expression_in_arg_list2450);
                    expression125=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression125.getTree());
                    // Evil.g:253:18: ( COMMA expression )*
                    loop32:
                    do {
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( (LA32_0==COMMA) ) {
                            alt32=1;
                        }


                        switch (alt32) {
                    	case 1 :
                    	    // Evil.g:253:19: COMMA expression
                    	    {
                    	    COMMA126=(Token)match(input,COMMA,FOLLOW_COMMA_in_arg_list2453); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA126);

                    	    pushFollow(FOLLOW_expression_in_arg_list2455);
                    	    expression127=expression();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_expression.add(expression127.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop32;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 254:7: -> ^( ARGS ( expression )+ )
                    {
                        // Evil.g:254:10: ^( ARGS ( expression )+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGS, "ARGS"), root_1);

                        if ( !(stream_expression.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());

                        }
                        stream_expression.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // Evil.g:256:7: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 256:7: -> ARGS
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(ARGS, "ARGS"));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "arg_list"

    // $ANTLR start synpred1_Evil
    public final void synpred1_Evil_fragment() throws RecognitionException {   
        // Evil.g:105:7: ( STRUCT ID LBRACE )
        // Evil.g:105:8: STRUCT ID LBRACE
        {
        match(input,STRUCT,FOLLOW_STRUCT_in_synpred1_Evil1090); if (state.failed) return ;
        match(input,ID,FOLLOW_ID_in_synpred1_Evil1092); if (state.failed) return ;
        match(input,LBRACE,FOLLOW_LBRACE_in_synpred1_Evil1094); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_Evil

    // $ANTLR start synpred2_Evil
    public final void synpred2_Evil_fragment() throws RecognitionException {   
        // Evil.g:109:7: ( STRUCT ID LBRACE )
        // Evil.g:109:8: STRUCT ID LBRACE
        {
        match(input,STRUCT,FOLLOW_STRUCT_in_synpred2_Evil1133); if (state.failed) return ;
        match(input,ID,FOLLOW_ID_in_synpred2_Evil1135); if (state.failed) return ;
        match(input,LBRACE,FOLLOW_LBRACE_in_synpred2_Evil1137); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_Evil

    // $ANTLR start synpred3_Evil
    public final void synpred3_Evil_fragment() throws RecognitionException {   
        // Evil.g:154:7: ( lvalue ASSIGN )
        // Evil.g:154:8: lvalue ASSIGN
        {
        pushFollow(FOLLOW_lvalue_in_synpred3_Evil1557);
        lvalue();

        state._fsp--;
        if (state.failed) return ;
        match(input,ASSIGN,FOLLOW_ASSIGN_in_synpred3_Evil1559); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_Evil

    // $ANTLR start synpred4_Evil
    public final void synpred4_Evil_fragment() throws RecognitionException {   
        // Evil.g:161:7: ( ID LPAREN )
        // Evil.g:161:8: ID LPAREN
        {
        match(input,ID,FOLLOW_ID_in_synpred4_Evil1621); if (state.failed) return ;
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred4_Evil1623); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_Evil

    // Delegated rules

    public final boolean synpred4_Evil() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_Evil_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_Evil() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_Evil_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_Evil() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_Evil_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_Evil() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_Evil_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA11 dfa11 = new DFA11(this);
    protected DFA31 dfa31 = new DFA31(this);
    static final String DFA11_eotS =
        "\14\uffff";
    static final String DFA11_eofS =
        "\14\uffff";
    static final String DFA11_minS =
        "\1\11\1\uffff\1\47\11\uffff";
    static final String DFA11_maxS =
        "\1\70\1\uffff\1\52\11\uffff";
    static final String DFA11_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\4\1\5\1\6\1\7\1\10\2\2\1\11";
    static final String DFA11_specialS =
        "\2\uffff\1\0\11\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\3\1\uffff\1\4\1\5\1\uffff\1\6\1\7\1\10\22\uffff\1\1\24\uffff"+
            "\1\2",
            "",
            "\1\13\1\uffff\1\12\1\11",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "152:1: statement : ( block | ( lvalue ASSIGN )=> assignment | print | read | conditional | loop | delete | ret | ( ID LPAREN )=> invocation );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA11_2 = input.LA(1);

                         
                        int index11_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA11_2==DOT) && (synpred3_Evil())) {s = 9;}

                        else if ( (LA11_2==ASSIGN) && (synpred3_Evil())) {s = 10;}

                        else if ( (LA11_2==LPAREN) && (synpred4_Evil())) {s = 11;}

                         
                        input.seek(index11_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 11, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA31_eotS =
        "\12\uffff";
    static final String DFA31_eofS =
        "\12\uffff";
    static final String DFA31_minS =
        "\1\21\1\uffff\1\12\7\uffff";
    static final String DFA31_maxS =
        "\1\71\1\uffff\1\66\7\uffff";
    static final String DFA31_acceptS =
        "\1\uffff\1\1\1\uffff\1\4\1\5\1\6\1\7\1\10\1\2\1\3";
    static final String DFA31_specialS =
        "\12\uffff}>";
    static final String[] DFA31_transitionS = {
            "\1\4\1\5\1\6\1\7\22\uffff\1\1\20\uffff\1\2\1\3",
            "",
            "\1\11\32\uffff\2\11\1\10\1\11\1\uffff\15\11",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA31_eot = DFA.unpackEncodedString(DFA31_eotS);
    static final short[] DFA31_eof = DFA.unpackEncodedString(DFA31_eofS);
    static final char[] DFA31_min = DFA.unpackEncodedStringToUnsignedChars(DFA31_minS);
    static final char[] DFA31_max = DFA.unpackEncodedStringToUnsignedChars(DFA31_maxS);
    static final short[] DFA31_accept = DFA.unpackEncodedString(DFA31_acceptS);
    static final short[] DFA31_special = DFA.unpackEncodedString(DFA31_specialS);
    static final short[][] DFA31_transition;

    static {
        int numStates = DFA31_transitionS.length;
        DFA31_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA31_transition[i] = DFA.unpackEncodedString(DFA31_transitionS[i]);
        }
    }

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = DFA31_eot;
            this.eof = DFA31_eof;
            this.min = DFA31_min;
            this.max = DFA31_max;
            this.accept = DFA31_accept;
            this.special = DFA31_special;
            this.transition = DFA31_transition;
        }
        public String getDescription() {
            return "238:1: factor : ( LPAREN expression RPAREN | id= ID a= arguments -> ^( INVOKE $id $a) | ID | INTEGER | TRUE | FALSE | NEW ID | NULL );";
        }
    }
 

    public static final BitSet FOLLOW_types_in_program1043 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_declarations_in_program1047 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_functions_in_program1051 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_program1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_types_sub_in_types1099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_declaration_in_types_sub1142 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_types_sub_in_types_sub1144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCT_in_type_declaration1164 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_type_declaration1167 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACE_in_type_declaration1169 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_nested_decl_in_type_declaration1172 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RBRACE_in_type_declaration1174 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_type_declaration1177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_decl_in_nested_decl1194 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_nested_decl1196 = new BitSet(new long[]{0x0000000000000072L});
    public static final BitSet FOLLOW_type_in_decl1216 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_decl1220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_type1257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_type1265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCT_in_type1273 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_type1276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_declarations1292 = new BitSet(new long[]{0x0000000000000072L});
    public static final BitSet FOLLOW_type_in_declaration1320 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_id_list_in_declaration1324 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_declaration1326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_id_list1363 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_COMMA_in_id_list1366 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_id_list1369 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_function_in_functions1386 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_FUN_in_function1411 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_function1415 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_parameters_in_function1419 = new BitSet(new long[]{0x0000000000000170L});
    public static final BitSet FOLLOW_return_type_in_function1423 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACE_in_function1425 = new BitSet(new long[]{0x010000180001DA70L});
    public static final BitSet FOLLOW_declarations_in_function1429 = new BitSet(new long[]{0x010000180001DA00L});
    public static final BitSet FOLLOW_statement_list_in_function1433 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RBRACE_in_function1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parameters1481 = new BitSet(new long[]{0x0000010000000070L});
    public static final BitSet FOLLOW_decl_in_parameters1484 = new BitSet(new long[]{0x0000014000000000L});
    public static final BitSet FOLLOW_COMMA_in_parameters1487 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_decl_in_parameters1489 = new BitSet(new long[]{0x0000014000000000L});
    public static final BitSet FOLLOW_RPAREN_in_parameters1495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_return_type1525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_return_type1533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement1548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement1564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_print_in_statement1572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_read_in_statement1580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditional_in_statement1588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loop_in_statement1596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_delete_in_statement1604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ret_in_statement1612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_invocation_in_statement1628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_block1643 = new BitSet(new long[]{0x010000180001DA00L});
    public static final BitSet FOLLOW_statement_list_in_block1647 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RBRACE_in_block1649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statement_list1680 = new BitSet(new long[]{0x010000080001DA02L});
    public static final BitSet FOLLOW_lvalue_in_assignment1708 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ASSIGN_in_assignment1710 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_expression_in_assignment1714 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_assignment1716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRINT_in_print1749 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_expression_in_print1752 = new BitSet(new long[]{0x0000002000000400L});
    public static final BitSet FOLLOW_ENDL_in_print1755 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_print1759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_READ_in_read1775 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_lvalue_in_read1778 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_read1780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_conditional1796 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditional1799 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_expression_in_conditional1802 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditional1804 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_block_in_conditional1807 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_ELSE_in_conditional1810 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_block_in_conditional1813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_loop1830 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_LPAREN_in_loop1832 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_expression_in_loop1836 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_RPAREN_in_loop1838 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_block_in_loop1842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DELETE_in_delete1878 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_expression_in_delete1881 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_delete1883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_ret1899 = new BitSet(new long[]{0x039000A0001E0000L});
    public static final BitSet FOLLOW_expression_in_ret1903 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_ret1907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_invocation1925 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_arguments_in_invocation1929 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_invocation1931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_lvalue1964 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_DOT_in_lvalue1967 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_lvalue1970 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_boolterm_in_expression1987 = new BitSet(new long[]{0x0000180000000002L});
    public static final BitSet FOLLOW_AND_in_expression1991 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_OR_in_expression1996 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_boolterm_in_expression2000 = new BitSet(new long[]{0x0000180000000002L});
    public static final BitSet FOLLOW_simple_in_boolterm2017 = new BitSet(new long[]{0x0007E00000000002L});
    public static final BitSet FOLLOW_EQ_in_boolterm2021 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_LT_in_boolterm2026 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_GT_in_boolterm2031 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_NE_in_boolterm2036 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_LE_in_boolterm2041 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_GE_in_boolterm2046 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_simple_in_boolterm2050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_simple2067 = new BitSet(new long[]{0x0018000000000002L});
    public static final BitSet FOLLOW_PLUS_in_simple2071 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_MINUS_in_simple2076 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_term_in_simple2080 = new BitSet(new long[]{0x0018000000000002L});
    public static final BitSet FOLLOW_unary_in_term2097 = new BitSet(new long[]{0x0060000000000002L});
    public static final BitSet FOLLOW_TIMES_in_term2101 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_DIVIDE_in_term2106 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_unary_in_term2110 = new BitSet(new long[]{0x0060000000000002L});
    public static final BitSet FOLLOW_NOT_in_unary2127 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_odd_not_in_unary2130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_unary2138 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_odd_neg_in_unary2141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_unary2149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_odd_not2164 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_even_not_in_odd_not2167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_odd_not2177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_even_not2207 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_odd_not_in_even_not2210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_even_not2218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_odd_neg2233 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_even_neg_in_odd_neg2236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_odd_neg2246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_even_neg2276 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_odd_neg_in_even_neg2279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_even_neg2287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_factor_in_selector2302 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_DOT_in_selector2305 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_selector2308 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_LPAREN_in_factor2325 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_expression_in_factor2328 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_RPAREN_in_factor2330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_factor2341 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_arguments_in_factor2345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_factor2371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_factor2379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_factor2387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_factor2395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_factor2403 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_factor2406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_factor2414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_arguments2429 = new BitSet(new long[]{0x03900180001E0000L});
    public static final BitSet FOLLOW_arg_list_in_arguments2432 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_RPAREN_in_arguments2434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_arg_list2450 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_COMMA_in_arg_list2453 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_expression_in_arg_list2455 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_STRUCT_in_synpred1_Evil1090 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_synpred1_Evil1092 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACE_in_synpred1_Evil1094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCT_in_synpred2_Evil1133 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_synpred2_Evil1135 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACE_in_synpred2_Evil1137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lvalue_in_synpred3_Evil1557 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ASSIGN_in_synpred3_Evil1559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_synpred4_Evil1621 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred4_Evil1623 = new BitSet(new long[]{0x0000000000000002L});

}