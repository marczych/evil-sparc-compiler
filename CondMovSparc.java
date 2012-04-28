import java.util.Hashtable;
import java.util.ArrayList;

public class CondMovSparc extends SparcInstruction {
	protected SparcRegister mDest;
	protected int mSrc;
	protected String mType;

   public CondMovSparc(String type, SparcRegister dest, int src) {
		mSrc = src;
		mDest = dest;
		mType = type;
   }

   public ArrayList<SparcRegister> getSources() {
		ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

		list.add(mDest);

		return list;
   }

   public ArrayList<SparcRegister> getDests() {
		ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();
      
		list.add(mDest);

		return list;
   }

   public String toString() {
		return "mov"+(mType.equals("eq") ? "e" : mType) +" %icc, "+mSrc+", "+mDest;
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {
      SparcRegister rep;

      if ((rep = spills.get(mDest)) != null)
         mDest = rep;
   }
}
