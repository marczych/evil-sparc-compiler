import java.util.Hashtable;
import java.util.ArrayList;

public class BeSparc extends SparcInstruction {
   protected String mLabel;

   public BeSparc(String label) {
      mLabel = label;
   }

   public ArrayList<SparcRegister> getSources() {
      return new ArrayList<SparcRegister>();
   }

   public ArrayList<SparcRegister> getDests() {
      return new ArrayList<SparcRegister>();
   }

   public String toString() {
      return "be "+mLabel;
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {

   }

   public boolean canRemoveDead() {
      return false;
   }
}
