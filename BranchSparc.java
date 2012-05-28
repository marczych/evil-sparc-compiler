import java.util.Hashtable;
import java.util.ArrayList;

public class BranchSparc extends SparcInstruction {
   protected String mLabel;
   protected Compare mType;

   public BranchSparc(Compare type, String label) {
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
      if (mType == null) {
         return "ba";
      }

      switch (mType) {
      case EQ:
         return "be";
      case LT:
         return "bl";
      case GT:
         return "bg";
      case NE:
         return "bne";
      case LE:
         return "ble";
      case GE:
         return "bge";
      default:
         return "ba";
      }
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {

   }

   public boolean canRemoveDead() {
      return false;
   }
}
