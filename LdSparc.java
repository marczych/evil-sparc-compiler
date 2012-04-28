import java.util.Hashtable;
import java.util.ArrayList;

public class LdSparc extends SparcInstruction {
	protected SparcRegister mDest;
	protected int mAddy;

   public LdSparc(int addy, SparcRegister dest) {
		mAddy = addy;
		mDest = dest;
   }

   public ArrayList<SparcRegister> getSources() {
      return new ArrayList<SparcRegister>();
   }

   public ArrayList<SparcRegister> getDests() {
		ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

		list.add(mDest);

		return list;
   }

   public String toString() {
		return "ld ["+mAddy+"], "+mDest;
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {
      SparcRegister rep;

      if ((rep = spills.get(mDest)) != null)
         mDest = rep;
   }
}
