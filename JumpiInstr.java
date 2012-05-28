import java.util.ArrayList;
import java.util.Hashtable;

public class JumpiInstr extends IlocInstruction {
   private String mLabel;

   public JumpiInstr(String label) {
      mLabel = label;
   }

   public String toIloc() {
      return "jumpi "+mLabel;
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new BranchSparc(null, mLabel));
      list.add(new NopSparc());

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
   }

   public IlocInstruction copy() {
      return new JumpiInstr(mLabel);
   }
}
