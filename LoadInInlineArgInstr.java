import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

public class LoadInInlineArgInstr extends IlocInstruction {
	protected int mIdx;
	protected Register mReg;

   public LoadInInlineArgInstr(int idx, Register reg) {
		mIdx = idx;
		mReg = reg;
   }

   public String toIloc() {
		return "loadininlineargument "+mIdx+", "+mReg.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new MovSparc(SparcRegister.getOut(mIdx), mReg.toSparc()));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mReg = getRepReg(hash, mReg);
   }

   public IlocInstruction copy() {
      return new LoadInInlineArgInstr(mIdx, mReg);
   }
}
