import java.util.ArrayList;
import java.util.Hashtable;

public class CompInstr extends IlocInstruction {
   protected Register mReg, mReg2;

   public CompInstr(Register reg, Register reg2) {
			  mReg = reg;
			  mReg2 = reg2;
   }

   public String toIloc() {
      return "comp " + mReg.toIloc() + ", " + mReg2.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new CmpSparc(mReg.toSparc(), mReg2.toSparc()));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mReg = getRepReg(hash, mReg);
      mReg2 = getRepReg(hash, mReg2);
   }

   public IlocInstruction copy() {
      return new CompInstr(mReg, mReg2);
   }
}
