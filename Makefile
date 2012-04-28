FILES=Evil.g CFG.g TypeCheck.g *.java

Evil.class : antlr.generated ${FILES}
	javac *.java

antlr.generated: antlr.generated.evil antlr.generated.type antlr.generated.cfg
	touch antlr.generated

antlr.generated.evil : Evil.g
	java org.antlr.Tool Evil.g
	touch antlr.generated.evil

antlr.generated.type : TypeCheck.g
	java org.antlr.Tool TypeCheck.g
	touch antlr.generated.type

antlr.generated.cfg : CFG.g
	java org.antlr.Tool CFG.g
	touch antlr.generated.cfg
