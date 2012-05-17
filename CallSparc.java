import java.util.Hashtable;
import java.util.ArrayList;

public class CallSparc extends SparcInstruction {
   protected String mLabel;

   public CallSparc(String label) {
      mLabel = label;
   }

   public ArrayList<SparcRegister> getSources() {
      ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

      list.addAll(SparcRegister.outRegs);

      return list;
   }

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
      return "call " + mLabel;
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {

   }

   public boolean canRemoveDead() {
      return false;
   }
}
