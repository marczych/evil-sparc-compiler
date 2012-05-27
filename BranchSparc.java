import java.util.Hashtable;
import java.util.ArrayList;

public class BranchSparc extends SparcInstruction {
   public enum TYPE {BA, BE, BNE};

   protected String mLabel;
   protected TYPE mType;

   public BranchSparc(TYPE type, String label) {
      mLabel = label;
      mType = type;
   }

   public ArrayList<SparcRegister> getSources() {
      return new ArrayList<SparcRegister>();
   }

   public ArrayList<SparcRegister> getDests() {
      return new ArrayList<SparcRegister>();
   }

   public String toString() {
      return getTypeName() + " " + mLabel;
   }

   private String getTypeName() {
      switch (mType) {
      case BA:
         return "ba";
      case BE:
         return "be";
      case BNE:
         return "bne";
      default:
         return "branch";
      }
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {

   }

   public boolean canRemoveDead() {
      return false;
   }
}
