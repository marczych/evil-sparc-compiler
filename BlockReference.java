public class BlockReference {
   protected Block mRef;

   public BlockReference() {
      this(null);
   }

   public BlockReference(Block block) {
      mRef = block;
   }

   public Block getRef() {
      return mRef;
   }

   public void setRef(Block ref) {
      mRef = ref;
   }
}
