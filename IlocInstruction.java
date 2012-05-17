import java.util.ArrayList;
import java.util.Hashtable;

public abstract class IlocInstruction {
   public abstract String toIloc();

   public abstract ArrayList<SparcInstruction> toSparc();
   public abstract void regReplace(Hashtable<Register, Register> hash);
   public abstract IlocInstruction copy();

   public static Register getRepReg(Hashtable<Register, Register> hash, Register reg) {
      Register ret;

      if ((ret = hash.get(reg)) != null)
         return ret;

      hash.put(reg, ret = new Register());
      return ret;
   }
}
