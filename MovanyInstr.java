import java.util.ArrayList;
import java.util.Hashtable;

public class MovanyInstr extends IlocInstruction {
   private Compare mType;
   private int mVal;
   private Register mDest;

   public MovanyInstr(Compare type, int val, Register dest) {
      mType = type;
      mVal = val;
      mDest = dest;
   }

   public String toIloc() {
      return "mov" + getConditionName() + " " + mVal + ", " + mDest.toIloc();
   }

   public String getConditionName() {
      switch (mType) {
      case EQ:
         return "eq";
      case LT:
         return "lt";
      case GT:
         return "gt";
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

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new CondMovSparc(mType, mDest.toSparc(), mVal));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mDest = getRepReg(hash, mDest);
   }

   public IlocInstruction copy() {
      return new MovanyInstr(mType, mVal, mDest);
   }
}
