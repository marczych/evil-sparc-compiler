import java.util.ArrayList;
import java.util.Hashtable;

public class LoadRetInstr extends IlocInstruction {
   private Register mReg;
   public LoadRetInstr(Register ret) {
      mReg = ret;
   }

   public String toIloc() {
      return "loadret "+mReg.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new MovSparc(SparcRegister.getOut(0), mReg.toSparc()));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mReg = getRepReg(hash, mReg);
   }

   public IlocInstruction copy() {
      return new LoadRetInstr(mReg);
   }
}
