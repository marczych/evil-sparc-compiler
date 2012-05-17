import java.util.ArrayList;
import java.util.Hashtable;

public class StoreRetInstr extends IlocInstruction {
   protected Register mReg;
   public StoreRetInstr(Register reg) {
      mReg = reg;
   }

   public String toIloc() {
      return "storeret "+mReg.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new MovSparc(mReg.toSparc(), SparcRegister.RETURN_REG));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mReg = getRepReg(hash, mReg);
   }

   public IlocInstruction copy() {
      return new StoreRetInstr(mReg);
   }
}
