import java.util.Hashtable;
import java.util.ArrayList;

public class CondMovSparc extends SparcInstruction {
   protected Compare mType;
   protected SparcRegister mDest;
   protected int mSrc;

   public CondMovSparc(Compare type, SparcRegister dest, int src) {
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

   public String getConditionName() {
      switch (mType) {
      case EQ:
         return "e";
      case LT:
         return "l";
      case GT:
         return "g";
      case NE:
         return "ne";
      case LE:
         return "le";
      case GE:
         return "ge";
      default:
         return "cond";
      }
   }

   public String toString() {
      return "mov" + getConditionName() + " %icc, " + mSrc + ", " + mDest;
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {
      SparcRegister rep;

      if ((rep = spills.get(mDest)) != null)
         mDest = rep;
   }
}
