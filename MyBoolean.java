public class MyBoolean {
   protected boolean val;

   public MyBoolean() {
      val = false;
   }

   public void setTrue() {
      val = true;
   }

   public void change(boolean newVal) {
      val = newVal;
   }

   public boolean isFalse() {
      return !val;
   }

   public boolean isTrue() {
      return val;
   }
}
