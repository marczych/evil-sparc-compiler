import java.util.ArrayList;
import java.util.Hashtable;

public class BranchInstr extends IlocInstruction {
   private Compare mType;
   private String mLabel;

   public BranchInstr(Compare type, String label) {
      mType = type;
      mLabel = label;
   }

   public String toIloc() {
      return "";
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new BranchSparc(mType, mLabel));
      list.add(new NopSparc());

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
   }

   public IlocInstruction copy() {
      return new BranchInstr(mType, mLabel);
   }
}
