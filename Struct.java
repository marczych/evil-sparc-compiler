import java.util.ArrayList;

public class Struct {
   protected ArrayList<String> mFieldNames, mStructs;
   protected String mName;

   public Struct(String name, ArrayList<String> fields, ArrayList<String> structs) {
      mName = name;
      mFieldNames = fields;
      mStructs = structs;
   }

   public ArrayList<String> getFieldNames() {
      return mFieldNames;
   }

   public int getOffset(String field) {
      return mFieldNames.indexOf(field) * 4;
   }

   public int getSize() {
      return mFieldNames.size() * 4;
   }

   public String getStructType(String field) {
      return mStructs.get(mFieldNames.indexOf(field));
   }

   public String getType() {
      return mName;
   }

   public String toString() {
      return mName;
   }
}
