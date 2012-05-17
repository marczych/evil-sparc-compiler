import java.util.ArrayList;
import java.util.Hashtable;

public class ComputeGlobalInstr extends IlocInstruction {
   private String mName;
   private Register mReg;
   public ComputeGlobalInstr(String name, Register reg) {
      mName = name;
      mReg = reg;
   }

   public String toIloc() {
      return "computeglobaladdress "+mName+", "+mReg.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new SethiSparc(mName, mReg.toSparc()));
      list.add(new OrLoSparc(mName, mReg.toSparc(), mReg.toSparc()));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mReg = getRepReg(hash, mReg);
   }

   public IlocInstruction copy() {
      return new ComputeGlobalInstr(mName, mReg);
   }
}
