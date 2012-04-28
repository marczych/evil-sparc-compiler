import java.util.ArrayList;
import java.util.Hashtable;

public class RetInstr extends IlocInstruction {
   public RetInstr() {
   }

   public String toIloc() {
			  return "ret";
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new RetSparc());
      list.add(new RestoreSparc());

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
   }

   public IlocInstruction copy() {
      return new RetInstr();
   }
}
