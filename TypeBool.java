
public class TypeBool implements EVILType {
   public boolean isSameType(EVILType other) {
      return other instanceof TypeBool;
   }

	public String toString() { return "TypeBool"; }
}
