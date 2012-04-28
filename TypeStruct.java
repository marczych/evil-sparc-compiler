
public class TypeStruct implements EVILType {
   protected String name;

   public TypeStruct(String name_) {
      name = name_;
   }

   public boolean isSameType(EVILType other) {
		if(other instanceof TypeNull)
				  return true;

      if (other instanceof TypeStruct)
         return name.equals(((TypeStruct)other).name);

      return false;
   }

	public String toString() { return "TypeStruct"; }
}
