import java.util.Hashtable;
import java.util.ArrayList;

public class MovSparc extends SparcInstruction {
   protected SparcRegister mSrc, mDest;

   public MovSparc(SparcRegister src, SparcRegister dest) {
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
      return "mov "+mSrc+", "+mDest;
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {
      SparcRegister rep;

      if ((rep = spills.get(mSrc)) != null)
         mSrc = rep;
      if ((rep = spills.get(mDest)) != null)
         mDest = rep;
   }
}
