import java.util.Hashtable;
import java.util.ArrayList;

public class CmpSparc extends SparcInstruction {
	protected SparcRegister src1, src2;

   public CmpSparc(SparcRegister r1, SparcRegister r2) {
		src1 = r1;
		src2 = r2;
   }

   public ArrayList<SparcRegister> getSources() {
		ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

		list.add(src1);
		list.add(src2);

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
      if ((rep = spills.get(src2)) != null)
         src2 = rep;
   }

   public boolean canRemoveDead() {
      return false;
   }
}
