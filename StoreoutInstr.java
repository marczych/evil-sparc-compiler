import java.util.ArrayList;
import java.util.Hashtable;

public class StoreoutInstr extends IlocInstruction {
   protected Register mSrc;
   protected int argNumber;

   public StoreoutInstr(Register src, int argNum) {
      mSrc = src;
      argNumber = argNum;
   }

   public String toIloc() {
      return "storeoutargument " + mSrc.toIloc() + ", " + argNumber;
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      if (argNumber < 6) {
         list.add(new MovSparc(mSrc.toSparc(), SparcRegister.getOut(argNumber)));
      } else {
         list.add(new StSparc(mSrc.toSparc(), SparcRegister.stackPointer,
          (argNumber - 6) * 4 + 68));
      }

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mSrc = getRepReg(hash, mSrc);
   }

   public IlocInstruction copy() {
      return new StoreoutInstr(mSrc, argNumber);
   }
}
