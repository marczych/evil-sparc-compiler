import java.util.ArrayList;
import java.util.Hashtable;

public class AndInstr extends IlocInstruction {
	private Register mR1,mR2;
	private Register mDest;

   public AndInstr(Register r1, Register r2, Register dest) {
      mR1 = r1;
		mR2 = r2;
		mDest = dest;
   }

   public String toIloc() {
		return "and " + mR1.toIloc() + ", " + mR2.toIloc() + ", " + mDest.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
		ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();
		list.add(new AndSparc(mR1.toSparc(), mR2.toSparc(), mDest.toSparc()));
		return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mDest = getRepReg(hash, mDest);
      mR1 = getRepReg(hash, mR1);
      mR2 = getRepReg(hash, mR2);
   }

   public IlocInstruction copy() {
      return new AndInstr(mR1, mR2, mDest);
   }
}
