import java.util.Hashtable;

public class RegisterTable {
   protected Hashtable<String, Integer> globalTable;
   protected Hashtable<String, String> globalStruct;
   protected Hashtable<String, Hashtable<String, Register>> funTable;
   protected Hashtable<String, String> funStruct;
   protected String currentFunction;

   public RegisterTable() {
      globalTable = new Hashtable<String, Integer>();
      globalStruct = new Hashtable<String, String>();
      funTable = new Hashtable<String, Hashtable<String, Register>>();
      funStruct = new Hashtable<String, String>();
   }

   public void newFunction(String id) {
      currentFunction = id;
      funTable.put(id, new Hashtable<String, Register>());
      funStruct = new Hashtable<String, String>();
   }

   public void addLocal(String id, Register reg, String type) {
      funTable.get(currentFunction).put(id, reg);
      if (type != null)
         funStruct.put(id, type);
   }

   public void addGlobal(String id, Integer loc, String type) {
      globalTable.put(id, loc);
      if (type != null)
         globalStruct.put(id, type);
   }

   public String getStruct(String name) {
      String res;

      if ((res = funStruct.get(name)) != null)
         return res;

      return globalStruct.get(name);
   }

   public Register findLocalVariable(String id) {
      return funTable.get(currentFunction).get(id);
   }

   public Integer findGlobalVariable(String id) {
      return globalTable.get(id);
   }

   public String toString() {
      return globalTable.toString();
   }
}
