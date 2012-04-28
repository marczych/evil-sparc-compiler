public class Register {
	private static int count = 0;

   protected int mRegNum;

   public Register(int num) {
      mRegNum = num;
   }

   public Register() {
      mRegNum = Register.nextRegister();
   }

	public static int nextRegister() {
      return count++;
   }

   public String toIloc() {
      return "r" + mRegNum;
   }

   public SparcRegister toSparc() {
      return new SparcRegister(SparcRegister.TYPE.VIRTUAL, mRegNum);
   }

   public String toString() {
      return mRegNum + "";
   }
}
