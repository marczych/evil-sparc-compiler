import java.util.ArrayList;
import java.util.Hashtable;

public class CompiInstr extends IlocInstruction {
   protected Register mReg;
   protected int mImmed;

   public CompiInstr(Register reg, int immed) {
      mReg = reg;
      mImmed = immed;
   }

   public String toIloc() {
      return "compi " + mReg.toIloc() + ", " + mImmed;
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new CompiSparc(mReg.toSparc(), mImmed));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mReg = getRepReg(hash, mReg);
   }

   public IlocInstruction copy() {
      return new CompiInstr(mReg, mImmed);
   }
}
