public class TypeNull implements EVILType {
   public boolean isSameType(EVILType other) {
      return other instanceof TypeNull || other instanceof TypeStruct;
   }

	public String toString() { return "TypeNull"; }
}
