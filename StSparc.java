import java.util.Hashtable;
import java.util.ArrayList;

public class StSparc extends SparcInstruction {
   protected SparcRegister mSrc, mDest;
   protected int mOffset;

   public StSparc(SparcRegister src, SparcRegister dest, int off) {
      mOffset = off;
      mSrc = src;
      mDest = dest;
   }

   public ArrayList<SparcRegister> getSources() {
      ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

      list.add(mSrc);
      list.add(mDest); // not being overwritten, read from

      return list;
   }

   public ArrayList<SparcRegister> getDests() {
      ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

      return list;
   }

   public String toString() {
      return "st " + mSrc + ", [" + mDest + (mOffset > 0 ? "+" + mOffset : "") + "]";
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {
      SparcRegister rep;

      if ((rep = spills.get(mSrc)) != null)
         mSrc = rep;
      if ((rep = spills.get(mDest)) != null)
         mDest = rep;
   }

   public boolean canRemoveDead() {
      return false;
   }
}
