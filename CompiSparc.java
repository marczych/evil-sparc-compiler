import java.util.Hashtable;
import java.util.ArrayList;

public class CompiSparc extends SparcInstruction {
	protected SparcRegister src1;
	protected int src2;

   public CompiSparc(SparcRegister r1, int r2) {
		src1 = r1;
		src2 = r2;
   }

   public ArrayList<SparcRegister> getSources() {
		ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

		list.add(src1);

		return list;
   }

   public ArrayList<SparcRegister> getDests() {
      return new ArrayList<SparcRegister>();
   }

   public String toString() {
		return "cmp "+src1+", "+src2;
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {
      SparcRegister rep;

      if ((rep = spills.get(src1)) != null)
         src1 = rep;
   }

   public boolean canRemoveDead() {
      return false;
   }
}
