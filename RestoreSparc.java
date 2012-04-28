import java.util.Hashtable;
import java.util.ArrayList;

public class RestoreSparc extends SparcInstruction {
   public RestoreSparc() {
   }

   public ArrayList<SparcRegister> getSources() {
      return new ArrayList<SparcRegister>();
   }

   public ArrayList<SparcRegister> getDests() {
      return new ArrayList<SparcRegister>();
   }

	public String toString() {
		return "restore";
	}

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {

   }

   public boolean canRemoveDead() {
      return false;
   }
}
