public class TypeInt implements EVILType {
   public boolean isSameType(EVILType other) {
      return other instanceof TypeInt;
   }

   public String toString() {
      return "TypeInt";
   }
}
