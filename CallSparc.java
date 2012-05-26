import java.util.Hashtable;
import java.util.ArrayList;

public class CallSparc extends SparcInstruction {
   protected String mLabel;
   protected boolean mTail;

   public CallSparc(String label) {
      this(label, false);
   }

   public CallSparc(String label, boolean tail) {
      mLabel = label;
      mTail = tail;
   }

   public ArrayList<SparcRegister> getSources() {
      ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

      if (mTail) {
         list.addAll(SparcRegister.inRegs);
      } else {
         list.addAll(SparcRegister.outRegs);
      }

      return list;
   }

   // TODO: Might need to rejigger this for tail calls...
   public ArrayList<SparcRegister> getDests() {
      ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

      list.addAll(SparcRegister.outRegs);
      list.add(SparcRegister.getGlobal(1));
      list.add(SparcRegister.getGlobal(2));
      list.add(SparcRegister.getGlobal(3));
      list.add(SparcRegister.getGlobal(4));
      list.add(SparcRegister.getGlobal(5));

      return list;
   }

   public String toString() {
      if (mTail) {
         return "ba " + mLabel + "BODY";
      } else {
         return "call " + mLabel;
      }
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {

   }

   public boolean canRemoveDead() {
      return false;
   }
}
