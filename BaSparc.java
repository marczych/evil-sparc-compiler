import java.util.Hashtable;
import java.util.ArrayList;

public class BaSparc extends SparcInstruction {
	protected String mLabel;

   public BaSparc(String label) {
		mLabel = label;
   }

   public ArrayList<SparcRegister> getSources() {
      return new ArrayList<SparcRegister>();
   }

   public ArrayList<SparcRegister> getDests() {
      return new ArrayList<SparcRegister>();
   }

   public String toString() {
		return "ba "+mLabel;
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {

   }

   public boolean canRemoveDead() {
      return false;
   }
}
