public class TypeId implements EVILType {
   private String name;

   public TypeId(String name_) {
      name = name_;
   }

   public boolean isSameType(EVILType type) {
      return type instanceof TypeId;
   }

   public String toString() {
      return name;
   }
}
