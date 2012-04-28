import java.util.ArrayList;
import java.util.Hashtable;

public class SubInstr extends IlocInstruction {
   protected Register mSrc1, mSrc2, mDest;

   public SubInstr(Register src1, Register src2, Register dest) {
      mSrc1 = src1;
      mSrc2 = src2;
      mDest = dest;
   }

   public String toIloc() {
      return "sub " + mSrc1.toIloc() + ", " + mSrc2.toIloc() + ", "
         + mDest.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new SubSparc(mSrc1.toSparc(), mSrc2.toSparc(), mDest.toSparc()));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mDest = getRepReg(hash, mDest);
      mSrc1 = getRepReg(hash, mSrc1);
      mSrc2 = getRepReg(hash, mSrc2);
   }

   public IlocInstruction copy() {
      return new SubInstr(mSrc1, mSrc2, mDest);
   }
}
