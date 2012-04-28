import java.util.Hashtable;
import java.util.ArrayList;

public class LdswSparc extends SparcInstruction {
   protected SparcRegister mSrc, mDest;
   protected int mOffset;

   public LdswSparc(SparcRegister src, SparcRegister dest, int off) {
      mOffset = off;
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
      return "ldsw [" + mSrc + (mOffset > 0 ? "+" + mOffset : "") + "], " + mDest;
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {
      SparcRegister rep;

      if ((rep = spills.get(mSrc)) != null)
         mSrc = rep;
      if ((rep = spills.get(mDest)) != null)
         mDest = rep;
   }
}
