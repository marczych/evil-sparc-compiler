import java.util.Hashtable;
import java.util.ArrayList;

public class SubSparc extends SparcInstruction {
   SparcRegister mSrc1, mSrc2, mDest;

   public SubSparc(SparcRegister src1, SparcRegister src2, SparcRegister dest) {
      mSrc1 = src1;
      mSrc2 = src2;
      mDest = dest;
   }

   public ArrayList<SparcRegister> getSources() {
      ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

      list.add(mSrc1);
      list.add(mSrc2);
      
      return list;
   }

   public ArrayList<SparcRegister> getDests() {
      ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

      list.add(mDest);
      
      return list;
   }

   public String toString() {
      return "sub " + mSrc1 + ", " + mSrc2 + ", " + mDest;
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {
      SparcRegister rep;

      if ((rep = spills.get(mSrc1)) != null)
         mSrc1 = rep;
      if ((rep = spills.get(mSrc2)) != null)
         mSrc2 = rep;
      if ((rep = spills.get(mDest)) != null)
         mDest = rep;
   }
}
