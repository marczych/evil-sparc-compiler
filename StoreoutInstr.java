import java.util.ArrayList;
import java.util.Hashtable;

public class StoreoutInstr extends IlocInstruction {
   protected Register mSrc;
   protected int mImmed;

   public StoreoutInstr(Register src, int immed) {
      mSrc = src;
      mImmed = immed;
   }

   public String toIloc() {
      return "storeoutargument " + mSrc.toIloc() + ", " + mImmed;
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new MovSparc(mSrc.toSparc(), SparcRegister.getOut(mImmed)));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mSrc = getRepReg(hash, mSrc);
   }

   public IlocInstruction copy() {
      return new StoreoutInstr(mSrc, mImmed);
   }
}
