import java.util.ArrayList;
import java.util.Hashtable;

public class MoveInstr extends IlocInstruction {
   private Register mFrom, mTo;
   public MoveInstr(Register from, Register to) {
      mFrom = from;
      mTo = to;
   }

   public String toIloc() {
      return "mov "+mFrom.toIloc()+", "+mTo.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();
      list.add(new MovSparc(mFrom.toSparc(), mTo.toSparc()));
      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mFrom = getRepReg(hash, mFrom);
      mTo = getRepReg(hash, mTo);
   }

   public IlocInstruction copy() {
      return new MoveInstr(mFrom, mTo);
   }
}
