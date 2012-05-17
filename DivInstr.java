import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

public class DivInstr extends IlocInstruction {
   protected Register mSrc1, mSrc2, mDest;

   public DivInstr(Register src1, Register src2, Register dest) {
      mSrc1 = src1;
      mSrc2 = src2;
      mDest = dest;
   }

   public String toIloc() {
      return "div " + mSrc1.toIloc() + ", " + mSrc2.toIloc() + ", " +
       mDest.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();
      Register src1 = new Register(), src2 = new Register();

      list.add(new SraSparc(mSrc1.toSparc(), SparcRegister.getZero(), src1.toSparc()));
      list.add(new SraSparc(mSrc2.toSparc(), SparcRegister.getZero(), src2.toSparc()));
      list.add(new SDivxSparc(mSrc1.toSparc(), mSrc2.toSparc(), mDest.toSparc()));

      TreeSet<SparcRegister> resSet = new TreeSet<SparcRegister>();
      resSet.addAll(SparcRegister.localRegs);
      resSet.addAll(SparcRegister.inRegs);

      RegGraph.addRestricted(src1.toSparc(), resSet);
      RegGraph.addRestricted(src2.toSparc(), resSet);
      RegGraph.addRestricted(mDest.toSparc(), resSet);

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mDest = getRepReg(hash, mDest);
      mSrc1 = getRepReg(hash, mSrc1);
      mSrc2 = getRepReg(hash, mSrc2);
   }

   public IlocInstruction copy() {
      return new DivInstr(mSrc1, mSrc2, mDest);
   }
}
