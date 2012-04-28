import java.util.Hashtable;
import java.util.ArrayList;

public class OrLoSparc extends SparcInstruction {
   protected SparcRegister mSrc, mDest;
   protected String mId;

   public OrLoSparc(String id, SparcRegister src, SparcRegister dest) {
      mId = id;
      mSrc = src;
      mDest = dest;
   }

   public ArrayList<SparcRegister> getSources() {
      ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

      list.add(mSrc);

      return list;
   }

   public ArrayList<SparcRegister> getDests() {
      ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

      list.add(mDest);

      return list;
   }

   public String toString() {
      return "or " + mSrc + ", %lo(" + mId + "), " + mDest;
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {
      SparcRegister rep;

      if ((rep = spills.get(mSrc)) != null)
         mSrc = rep;
      if ((rep = spills.get(mDest)) != null)
         mDest = rep;
   }
}
