import java.util.ArrayList;
import java.util.Hashtable;

public class DelInstr extends IlocInstruction {
   protected Register mDest;

   public DelInstr(Register dest) {
      mDest = dest;
   }

   public String toIloc() {
      return "del " + mDest.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new MovSparc(mDest.toSparc(), new SparcRegister(SparcRegister.TYPE.OUT, 0)));
      list.add(new CallSparc("free"));
      list.add(new NopSparc());

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mDest = getRepReg(hash, mDest);
   }

   public IlocInstruction copy() {
      return new DelInstr(mDest);
   }
}
