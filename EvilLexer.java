// $ANTLR 3.3 Nov 30, 2010 12:50:56 Evil.g 2012-04-28 14:31:36

   /* package declaration here */


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class EvilLexer extends Lexer {
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

    public EvilLexer() {;} 
    public EvilLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public EvilLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "Evil.g"; }

    // $ANTLR start "STRUCT"
    public final void mSTRUCT() throws RecognitionException {
        try {
            int _type = STRUCT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:7:8: ( 'struct' )
            // Evil.g:7:10: 'struct'
            {
            match("struct"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRUCT"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:8:5: ( 'int' )
            // Evil.g:8:7: 'int'
            {
            match("int"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "BOOL"
    public final void mBOOL() throws RecognitionException {
        try {
            int _type = BOOL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:9:6: ( 'bool' )
            // Evil.g:9:8: 'bool'
            {
            match("bool"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BOOL"

    // $ANTLR start "FUN"
    public final void mFUN() throws RecognitionException {
        try {
            int _type = FUN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:10:5: ( 'fun' )
            // Evil.g:10:7: 'fun'
            {
            match("fun"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FUN"

    // $ANTLR start "VOID"
    public final void mVOID() throws RecognitionException {
        try {
            int _type = VOID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:11:6: ( 'void' )
            // Evil.g:11:8: 'void'
            {
            match("void"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VOID"

    // $ANTLR start "PRINT"
    public final void mPRINT() throws RecognitionException {
        try {
            int _type = PRINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:12:7: ( 'print' )
            // Evil.g:12:9: 'print'
            {
            match("print"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRINT"

    // $ANTLR start "ENDL"
    public final void mENDL() throws RecognitionException {
        try {
            int _type = ENDL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:13:6: ( 'endl' )
            // Evil.g:13:8: 'endl'
            {
            match("endl"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ENDL"

    // $ANTLR start "READ"
    public final void mREAD() throws RecognitionException {
        try {
            int _type = READ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:14:6: ( 'read' )
            // Evil.g:14:8: 'read'
            {
            match("read"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "READ"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:15:4: ( 'if' )
            // Evil.g:15:6: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:16:6: ( 'else' )
            // Evil.g:16:8: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:17:7: ( 'while' )
            // Evil.g:17:9: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "DELETE"
    public final void mDELETE() throws RecognitionException {
        try {
            int _type = DELETE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:18:8: ( 'delete' )
            // Evil.g:18:10: 'delete'
            {
            match("delete"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DELETE"

    // $ANTLR start "RETURN"
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:19:8: ( 'return' )
            // Evil.g:19:10: 'return'
            {
            match("return"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RETURN"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:20:6: ( 'true' )
            // Evil.g:20:8: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:21:7: ( 'false' )
            // Evil.g:21:9: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "NEW"
    public final void mNEW() throws RecognitionException {
        try {
            int _type = NEW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:22:5: ( 'new' )
            // Evil.g:22:7: 'new'
            {
            match("new"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEW"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:23:6: ( 'null' )
            // Evil.g:23:8: 'null'
            {
            match("null"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "LBRACE"
    public final void mLBRACE() throws RecognitionException {
        try {
            int _type = LBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:56:10: ( '{' )
            // Evil.g:56:13: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACE"

    // $ANTLR start "RBRACE"
    public final void mRBRACE() throws RecognitionException {
        try {
            int _type = RBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:57:10: ( '}' )
            // Evil.g:57:13: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACE"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:58:10: ( ';' )
            // Evil.g:58:13: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:59:10: ( ',' )
            // Evil.g:59:13: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:60:10: ( '(' )
            // Evil.g:60:13: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:61:10: ( ')' )
            // Evil.g:61:13: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:62:10: ( '=' )
            // Evil.g:62:13: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:63:10: ( '.' )
            // Evil.g:63:13: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:64:10: ( '&&' )
            // Evil.g:64:13: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:65:10: ( '||' )
            // Evil.g:65:13: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:66:10: ( '==' )
            // Evil.g:66:13: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:67:10: ( '<' )
            // Evil.g:67:13: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:68:10: ( '>' )
            // Evil.g:68:13: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "NE"
    public final void mNE() throws RecognitionException {
        try {
            int _type = NE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:69:10: ( '!=' )
            // Evil.g:69:13: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NE"

    // $ANTLR start "LE"
    public final void mLE() throws RecognitionException {
        try {
            int _type = LE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:70:10: ( '<=' )
            // Evil.g:70:13: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LE"

    // $ANTLR start "GE"
    public final void mGE() throws RecognitionException {
        try {
            int _type = GE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:71:10: ( '>=' )
            // Evil.g:71:13: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GE"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:72:10: ( '+' )
            // Evil.g:72:13: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:73:10: ( '-' )
            // Evil.g:73:13: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "TIMES"
    public final void mTIMES() throws RecognitionException {
        try {
            int _type = TIMES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:74:10: ( '*' )
            // Evil.g:74:13: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TIMES"

    // $ANTLR start "DIVIDE"
    public final void mDIVIDE() throws RecognitionException {
        try {
            int _type = DIVIDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:75:10: ( '/' )
            // Evil.g:75:13: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIVIDE"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:76:10: ( '!' )
            // Evil.g:76:13: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:78:10: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
            // Evil.g:78:13: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // Evil.g:78:34: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Evil.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:80:10: ( '0' | ( '1' .. '9' ) ( '0' .. '9' )* )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='0') ) {
                alt3=1;
            }
            else if ( ((LA3_0>='1' && LA3_0<='9')) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // Evil.g:80:13: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // Evil.g:80:19: ( '1' .. '9' ) ( '0' .. '9' )*
                    {
                    // Evil.g:80:19: ( '1' .. '9' )
                    // Evil.g:80:20: '1' .. '9'
                    {
                    matchRange('1','9'); 

                    }

                    // Evil.g:80:30: ( '0' .. '9' )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // Evil.g:80:31: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTEGER"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:82:10: ( ( ' ' | '\\t' | '\\f' | '\\r' | '\\n' )+ )
            // Evil.g:82:13: ( ' ' | '\\t' | '\\f' | '\\r' | '\\n' )+
            {
            // Evil.g:82:13: ( ' ' | '\\t' | '\\f' | '\\r' | '\\n' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='\t' && LA4_0<='\n')||(LA4_0>='\f' && LA4_0<='\r')||LA4_0==' ') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Evil.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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

             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Evil.g:91:10: ( '#' (~ '\\n' )* '\\n' )
            // Evil.g:91:13: '#' (~ '\\n' )* '\\n'
            {
            match('#'); 
            // Evil.g:91:17: (~ '\\n' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\u0000' && LA5_0<='\t')||(LA5_0>='\u000B' && LA5_0<='\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Evil.g:91:18: ~ '\\n'
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match('\n'); 
             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    public void mTokens() throws RecognitionException {
        // Evil.g:1:8: ( STRUCT | INT | BOOL | FUN | VOID | PRINT | ENDL | READ | IF | ELSE | WHILE | DELETE | RETURN | TRUE | FALSE | NEW | NULL | LBRACE | RBRACE | SEMI | COMMA | LPAREN | RPAREN | ASSIGN | DOT | AND | OR | EQ | LT | GT | NE | LE | GE | PLUS | MINUS | TIMES | DIVIDE | NOT | ID | INTEGER | WS | COMMENT )
        int alt6=42;
        alt6 = dfa6.predict(input);
        switch (alt6) {
            case 1 :
                // Evil.g:1:10: STRUCT
                {
                mSTRUCT(); 

                }
                break;
            case 2 :
                // Evil.g:1:17: INT
                {
                mINT(); 

                }
                break;
            case 3 :
                // Evil.g:1:21: BOOL
                {
                mBOOL(); 

                }
                break;
            case 4 :
                // Evil.g:1:26: FUN
                {
                mFUN(); 

                }
                break;
            case 5 :
                // Evil.g:1:30: VOID
                {
                mVOID(); 

                }
                break;
            case 6 :
                // Evil.g:1:35: PRINT
                {
                mPRINT(); 

                }
                break;
            case 7 :
                // Evil.g:1:41: ENDL
                {
                mENDL(); 

                }
                break;
            case 8 :
                // Evil.g:1:46: READ
                {
                mREAD(); 

                }
                break;
            case 9 :
                // Evil.g:1:51: IF
                {
                mIF(); 

                }
                break;
            case 10 :
                // Evil.g:1:54: ELSE
                {
                mELSE(); 

                }
                break;
            case 11 :
                // Evil.g:1:59: WHILE
                {
                mWHILE(); 

                }
                break;
            case 12 :
                // Evil.g:1:65: DELETE
                {
                mDELETE(); 

                }
                break;
            case 13 :
                // Evil.g:1:72: RETURN
                {
                mRETURN(); 

                }
                break;
            case 14 :
                // Evil.g:1:79: TRUE
                {
                mTRUE(); 

                }
                break;
            case 15 :
                // Evil.g:1:84: FALSE
                {
                mFALSE(); 

                }
                break;
            case 16 :
                // Evil.g:1:90: NEW
                {
                mNEW(); 

                }
                break;
            case 17 :
                // Evil.g:1:94: NULL
                {
                mNULL(); 

                }
                break;
            case 18 :
                // Evil.g:1:99: LBRACE
                {
                mLBRACE(); 

                }
                break;
            case 19 :
                // Evil.g:1:106: RBRACE
                {
                mRBRACE(); 

                }
                break;
            case 20 :
                // Evil.g:1:113: SEMI
                {
                mSEMI(); 

                }
                break;
            case 21 :
                // Evil.g:1:118: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 22 :
                // Evil.g:1:124: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 23 :
                // Evil.g:1:131: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 24 :
                // Evil.g:1:138: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 25 :
                // Evil.g:1:145: DOT
                {
                mDOT(); 

                }
                break;
            case 26 :
                // Evil.g:1:149: AND
                {
                mAND(); 

                }
                break;
            case 27 :
                // Evil.g:1:153: OR
                {
                mOR(); 

                }
                break;
            case 28 :
                // Evil.g:1:156: EQ
                {
                mEQ(); 

                }
                break;
            case 29 :
                // Evil.g:1:159: LT
                {
                mLT(); 

                }
                break;
            case 30 :
                // Evil.g:1:162: GT
                {
                mGT(); 

                }
                break;
            case 31 :
                // Evil.g:1:165: NE
                {
                mNE(); 

                }
                break;
            case 32 :
                // Evil.g:1:168: LE
                {
                mLE(); 

                }
                break;
            case 33 :
                // Evil.g:1:171: GE
                {
                mGE(); 

                }
                break;
            case 34 :
                // Evil.g:1:174: PLUS
                {
                mPLUS(); 

                }
                break;
            case 35 :
                // Evil.g:1:179: MINUS
                {
                mMINUS(); 

                }
                break;
            case 36 :
                // Evil.g:1:185: TIMES
                {
                mTIMES(); 

                }
                break;
            case 37 :
                // Evil.g:1:191: DIVIDE
                {
                mDIVIDE(); 

                }
                break;
            case 38 :
                // Evil.g:1:198: NOT
                {
                mNOT(); 

                }
                break;
            case 39 :
                // Evil.g:1:202: ID
                {
                mID(); 

                }
                break;
            case 40 :
                // Evil.g:1:205: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 41 :
                // Evil.g:1:213: WS
                {
                mWS(); 

                }
                break;
            case 42 :
                // Evil.g:1:216: COMMENT
                {
                mCOMMENT(); 

                }
                break;

        }

    }


    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA6_eotS =
        "\1\uffff\14\36\6\uffff\1\63\3\uffff\1\65\1\67\1\71\10\uffff\2\36"+
        "\1\74\15\36\10\uffff\1\36\1\114\1\uffff\1\36\1\116\12\36\1\131\2"+
        "\36\1\uffff\1\134\1\uffff\1\36\1\136\1\36\1\140\1\141\1\142\3\36"+
        "\1\146\1\uffff\1\147\1\36\1\uffff\1\151\1\uffff\1\152\3\uffff\1"+
        "\36\1\154\1\36\2\uffff\1\156\2\uffff\1\157\1\uffff\1\160\3\uffff";
    static final String DFA6_eofS =
        "\161\uffff";
    static final String DFA6_minS =
        "\1\11\1\164\1\146\1\157\1\141\1\157\1\162\1\154\1\145\1\150\1\145"+
        "\1\162\1\145\6\uffff\1\75\3\uffff\3\75\10\uffff\1\162\1\164\1\60"+
        "\1\157\1\156\1\154\2\151\1\144\1\163\1\141\1\151\1\154\1\165\1\167"+
        "\1\154\10\uffff\1\165\1\60\1\uffff\1\154\1\60\1\163\1\144\1\156"+
        "\1\154\1\145\1\144\1\165\1\154\2\145\1\60\1\154\1\143\1\uffff\1"+
        "\60\1\uffff\1\145\1\60\1\164\3\60\1\162\1\145\1\164\1\60\1\uffff"+
        "\1\60\1\164\1\uffff\1\60\1\uffff\1\60\3\uffff\1\156\1\60\1\145\2"+
        "\uffff\1\60\2\uffff\1\60\1\uffff\1\60\3\uffff";
    static final String DFA6_maxS =
        "\1\175\1\164\1\156\1\157\1\165\1\157\1\162\1\156\1\145\1\150\1\145"+
        "\1\162\1\165\6\uffff\1\75\3\uffff\3\75\10\uffff\1\162\1\164\1\172"+
        "\1\157\1\156\1\154\2\151\1\144\1\163\1\164\1\151\1\154\1\165\1\167"+
        "\1\154\10\uffff\1\165\1\172\1\uffff\1\154\1\172\1\163\1\144\1\156"+
        "\1\154\1\145\1\144\1\165\1\154\2\145\1\172\1\154\1\143\1\uffff\1"+
        "\172\1\uffff\1\145\1\172\1\164\3\172\1\162\1\145\1\164\1\172\1\uffff"+
        "\1\172\1\164\1\uffff\1\172\1\uffff\1\172\3\uffff\1\156\1\172\1\145"+
        "\2\uffff\1\172\2\uffff\1\172\1\uffff\1\172\3\uffff";
    static final String DFA6_acceptS =
        "\15\uffff\1\22\1\23\1\24\1\25\1\26\1\27\1\uffff\1\31\1\32\1\33\3"+
        "\uffff\1\42\1\43\1\44\1\45\1\47\1\50\1\51\1\52\20\uffff\1\34\1\30"+
        "\1\40\1\35\1\41\1\36\1\37\1\46\2\uffff\1\11\17\uffff\1\2\1\uffff"+
        "\1\4\12\uffff\1\20\2\uffff\1\3\1\uffff\1\5\1\uffff\1\7\1\12\1\10"+
        "\3\uffff\1\16\1\21\1\uffff\1\17\1\6\1\uffff\1\13\1\uffff\1\1\1\15"+
        "\1\14";
    static final String DFA6_specialS =
        "\161\uffff}>";
    static final String[] DFA6_transitionS = {
            "\2\40\1\uffff\2\40\22\uffff\1\40\1\31\1\uffff\1\41\2\uffff\1"+
            "\25\1\uffff\1\21\1\22\1\34\1\32\1\20\1\33\1\24\1\35\12\37\1"+
            "\uffff\1\17\1\27\1\23\1\30\2\uffff\32\36\6\uffff\1\36\1\3\1"+
            "\36\1\12\1\7\1\4\2\36\1\2\4\36\1\14\1\36\1\6\1\36\1\10\1\1\1"+
            "\13\1\36\1\5\1\11\3\36\1\15\1\26\1\16",
            "\1\42",
            "\1\44\7\uffff\1\43",
            "\1\45",
            "\1\47\23\uffff\1\46",
            "\1\50",
            "\1\51",
            "\1\53\1\uffff\1\52",
            "\1\54",
            "\1\55",
            "\1\56",
            "\1\57",
            "\1\60\17\uffff\1\61",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\62",
            "",
            "",
            "",
            "\1\64",
            "\1\66",
            "\1\70",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\72",
            "\1\73",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104\22\uffff\1\105",
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\113",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "",
            "\1\115",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "\1\132",
            "\1\133",
            "",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "",
            "\1\135",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "\1\137",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "\1\143",
            "\1\144",
            "\1\145",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "\1\150",
            "",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "",
            "",
            "",
            "\1\153",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "\1\155",
            "",
            "",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "",
            "",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "",
            "\12\36\7\uffff\32\36\6\uffff\32\36",
            "",
            "",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( STRUCT | INT | BOOL | FUN | VOID | PRINT | ENDL | READ | IF | ELSE | WHILE | DELETE | RETURN | TRUE | FALSE | NEW | NULL | LBRACE | RBRACE | SEMI | COMMA | LPAREN | RPAREN | ASSIGN | DOT | AND | OR | EQ | LT | GT | NE | LE | GE | PLUS | MINUS | TIMES | DIVIDE | NOT | ID | INTEGER | WS | COMMENT );";
        }
    }
 

}