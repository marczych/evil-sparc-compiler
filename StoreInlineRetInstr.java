import java.util.ArrayList;
import java.util.Hashtable;

public class StoreInlineRetInstr extends IlocInstruction {
   protected Register mReg;

   public StoreInlineRetInstr(Register reg) {
      mReg = reg;
   }

   public String toIloc() {
      return "storeret "+mReg.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new MovSparc(mReg.toSparc(), SparcRegister.getOut(0)));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mReg = getRepReg(hash, mReg);
   }

   public IlocInstruction copy() {
      return new StoreInlineRetInstr(mReg);
   }
}
