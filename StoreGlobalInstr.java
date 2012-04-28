import java.util.ArrayList;
import java.util.Hashtable;

public class StoreGlobalInstr extends IlocInstruction {
	private Register mReg;
	private String mName;

   public StoreGlobalInstr(Register reg, String name) {
      mReg = reg;
		mName = name;
   }

   public String toIloc() {
		return "storeglobal "+mReg.toIloc()+", "+mName;
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();
      SparcRegister dest = mReg.toSparc(), addr = new Register().toSparc();

      list.add(new SethiSparc(mName, addr));
      list.add(new OrLoSparc(mName, addr, addr));
      list.add(new StSparc(dest, addr, 0));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mReg = getRepReg(hash, mReg);
   }

   public IlocInstruction copy() {
      return new StoreGlobalInstr(mReg, mName);
   }
}
