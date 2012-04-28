import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

public class LoadInArgInstr extends IlocInstruction {
	protected String mName;
	protected int mIdx;
	protected Register mReg;
   public LoadInArgInstr(String varName, int idx, Register reg) {
      mName = varName;
		mIdx = idx;
		mReg = reg;
   }

   public String toIloc() {
		return "loadinargument "+mName+", "+mIdx+", "+mReg.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      TreeSet<SparcRegister> resSet = new TreeSet<SparcRegister>();
      resSet.addAll(SparcRegister.outRegs);
      RegGraph.addRestricted(mReg.toSparc(), resSet);
      list.add(new MovSparc(SparcRegister.getIn(mIdx), mReg.toSparc()));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mReg = getRepReg(hash, mReg);
   }

   public IlocInstruction copy() {
      return new LoadInArgInstr(mName, mIdx, mReg);
   }
}
