import java.util.ArrayList;
import java.util.Hashtable;

public class ReadInstr extends IlocInstruction {
   protected Register mAddr;

   public ReadInstr(Register dest) {
      mAddr = dest;
   }

   public String toIloc() {
      return "read " + mAddr.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new SethiSparc(CFG.SCANF_GLOBAL, SparcRegister.getOut(0)));
      list.add(new OrLoSparc(CFG.SCANF_GLOBAL, SparcRegister.getOut(0), SparcRegister.getOut(0)));
      list.add(new MovSparc(mAddr.toSparc(), SparcRegister.getOut(1)));
      list.add(new CallSparc("scanf"));
      list.add(new NopSparc());

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mAddr = getRepReg(hash, mAddr);
   }

   public IlocInstruction copy() {
      return new ReadInstr(mAddr);
   }
}
