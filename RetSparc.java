import java.util.Hashtable;
import java.util.ArrayList;

public class RetSparc extends SparcInstruction {
   public RetSparc() {
   }

   public ArrayList<SparcRegister> getSources() {
      ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

      list.add(SparcRegister.getIn(0));

      return list;
   }

   public ArrayList<SparcRegister> getDests() {
      return new ArrayList<SparcRegister>();
   }

   public String toString() {
      return "ret";
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {

   }

   public boolean canRemoveDead() {
      return false;
   }
}
