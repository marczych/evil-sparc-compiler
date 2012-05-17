import java.util.Hashtable;
import java.util.ArrayList;

public class AndSparc extends SparcInstruction {
   protected SparcRegister src1, src2, dest;

   public AndSparc(SparcRegister r1, SparcRegister r2, SparcRegister r3) {
      src1 = r1;
      src2 = r2;
      dest = r3;
   }

   public ArrayList<SparcRegister> getSources() {
      ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

      list.add(src1);
      list.add(src2);

      return list;
   }

   public ArrayList<SparcRegister> getDests() {
      ArrayList<SparcRegister> list = new ArrayList<SparcRegister>();

      list.add(dest);

      return list;
   }

   public String toString() {
      return "and "+src1+", "+src2+", "+dest;
   }

   public void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills) {
      SparcRegister rep;

      if ((rep = spills.get(src1)) != null)
         src1 = rep;
      if ((rep = spills.get(src2)) != null)
         src2 = rep;
      if ((rep = spills.get(dest)) != null)
         dest = rep;
   }
}
