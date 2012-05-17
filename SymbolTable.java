import java.util.Hashtable;

public class SymbolTable {
   private Hashtable<String, EVILType> globalVars;
   private Hashtable<String, TypeFun> funTable;
   private String currentFunction;

   public SymbolTable() {
      globalVars = new Hashtable<String, EVILType>();
      funTable = new Hashtable<String, TypeFun>();
   }

   public void newFunction(String func) {
      currentFunction = func;
      funTable.put(currentFunction, new TypeFun());
   }

   public TypeFun findCurrentFunction() {
      return funTable.get(currentFunction);
   }

   public TypeFun findFunction(String fun) {
      return funTable.get(fun);
   }

   public EVILType findVariable(String varName) {
      TypeFun fun = funTable.get(currentFunction);

      EVILType ret = fun.getVar(varName);
      if(ret != null)
         return ret;

      ret = globalVars.get(varName);
      return ret;
   }

   public void addGlobalVar(String varName, EVILType type) {
      globalVars.put(varName, type);
   }

   public boolean addLocalVar(String varName, EVILType type) {
      TypeFun fun = funTable.get(currentFunction);
      return fun.addLocal(varName, type);
   }

   public boolean addParam(String varName, EVILType type) {
      TypeFun fun = funTable.get(currentFunction);
      return fun.addParam(varName, type);
   }

   public String toString() {
      return "Global Variables: "+globalVars.toString() +
       "\nCurrent Function: "+ currentFunction +
       " fun table "+ funTable.toString();
   }
}
