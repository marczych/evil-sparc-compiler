import java.util.ArrayList;
import java.util.Hashtable;

public class NegInstr extends IlocInstruction {
   protected Register mSrc, mDest;

   public NegInstr(Register src, Register dest) {
      mSrc = src;
      mDest = dest;
   }

   public String toIloc() {
      return "sub 0, " + mSrc.toIloc() + ", " + mDest.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new NegSparc(mSrc.toSparc(), mDest.toSparc()));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mDest = getRepReg(hash, mDest);
      mSrc = getRepReg(hash, mSrc);
   }

   public IlocInstruction copy() {
      return new NegInstr(mSrc, mDest);
   }
}
