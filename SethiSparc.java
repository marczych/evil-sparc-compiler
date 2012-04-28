import java.util.Hashtable;
import java.util.ArrayList;

public class SethiSparc extends SparcInstruction {
	protected SparcRegister dest;
	protected String mLabel;

   public SethiSparc(String label, SparcRegister r2) {
		dest = r2;
		mLabel = label;
   }

   public ArrayList<SparcRegister> getSources() {
      return new ArrayList<SparcRegister>();
   }

   public ArrayList<SparcRegister> getDests() {
		ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

		list.add(dest);

		return list;
   }

   public String toString() {
		return "sethi %hi("+mLabel+"), "+dest;
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {
      SparcRegister rep;

      if ((rep = spills.get(dest)) != null)
         dest = rep;
   }
}
