import java.util.ArrayList;
import java.util.Hashtable;

public class CBREQInstr extends IlocInstruction {

   private String mTrueLabel, mFalseLabel;

   public CBREQInstr(String trueLabel, String falseLabel) {
      mTrueLabel = trueLabel;
      mFalseLabel = falseLabel;
   }

   public String toIloc() {
      return "cbreq "+mTrueLabel+ ", "+mFalseLabel;
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new BranchSparc(BranchSparc.TYPE.BE, mTrueLabel));
      list.add(new NopSparc());
      list.add(new BranchSparc(BranchSparc.TYPE.BA, mFalseLabel));
      list.add(new NopSparc());

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
   }

   public IlocInstruction copy() {
      return new CBREQInstr(mTrueLabel, mFalseLabel);
   }
}
