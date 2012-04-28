import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Hashtable;

public abstract class SparcInstruction {
   public TreeSet<SparcRegister> mLiveOut = new TreeSet<SparcRegister>();

   public abstract ArrayList<SparcRegister> getSources();
   public abstract ArrayList<SparcRegister> getDests();
   public abstract void replaceSpills(Hashtable<SparcRegister, SparcRegister> spills);

   public boolean equals(Object o) {
      return this == o;
   }

   public boolean canRemoveDead() {
      return true;
   }

   public ArrayList<SparcInstruction> getInstrs() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();
      ArrayList<SparcRegister> srcs = getSources(), dests = getDests();
      Hashtable<SparcRegister, SparcRegister> spills = new Hashtable<SparcRegister, SparcRegister>();
      SparcRegister spill, spillReplace;

      for (SparcRegister reg : srcs) {
         if ((spill = SparcRegister.spillMap.get(reg)) != null) {
            spillReplace = getSpillReplace(spills, reg);
            list.add(new LdswSparc(SparcRegister.framePointer, spillReplace,
              spill.mNum * 4));
         }
      }

      list.add(this);

      for (SparcRegister reg : dests) {
         if ((spill = SparcRegister.spillMap.get(reg)) != null) {
            spillReplace = getSpillReplace(spills, reg);
            list.add(new StSparc(spillReplace ,SparcRegister.framePointer, spill.mNum * 4));
         }
      }

      if (spills.size() > 0)
         replaceSpills(spills);

      return list;
   }

   private static SparcRegister getSpillReplace(Hashtable<SparcRegister, SparcRegister> hash, SparcRegister reg) {
      SparcRegister res;
      if ((res = hash.get(reg)) != null)
         return res;

      res = new Register().toSparc();
      hash.put(reg, res);

      return res;
   }
}
