import java.util.ArrayList;
import java.util.Hashtable;

public class MultInstr extends IlocInstruction {
   protected Register mSrc1, mSrc2, mDest;

   public MultInstr(Register src1, Register src2, Register dest) {
      mSrc1 = src1;
      mSrc2 = src2;
      mDest = dest;
   }

   public String toIloc() {
      return "mult " + mSrc1.toIloc() + ", " + mSrc2.toIloc() + ", "
         + mDest.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
		ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();
		list.add(new SraSparc(mSrc1.toSparc(), SparcRegister.getZero(), mSrc1.toSparc()));
		list.add(new SraSparc(mSrc2.toSparc(), SparcRegister.getZero(), mSrc2.toSparc()));
		list.add(new MulxSparc(mSrc1.toSparc(), mSrc2.toSparc(), mDest.toSparc()));
		return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mDest = getRepReg(hash, mDest);
      mSrc1 = getRepReg(hash, mSrc1);
      mSrc2 = getRepReg(hash, mSrc2);
   }

   public IlocInstruction copy() {
      return new MultInstr(mSrc1, mSrc2, mDest);
   }
}
