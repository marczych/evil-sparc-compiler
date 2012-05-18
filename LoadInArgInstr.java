import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

public class LoadInArgInstr extends IlocInstruction {
   protected String mName;
   protected int argNumber;
   protected Register mReg;
   public LoadInArgInstr(String varName, int idx, Register reg) {
      mName = varName;
      argNumber = idx;
      mReg = reg;
   }

   public String toIloc() {
      return "loadinargument "+mName+", "+argNumber+", "+mReg.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      TreeSet<SparcRegister> resSet = new TreeSet<SparcRegister>();
      resSet.addAll(SparcRegister.outRegs);
      RegGraph.addRestricted(mReg.toSparc(), resSet);

      if (argNumber < 6) {
         list.add(new MovSparc(SparcRegister.getIn(argNumber), mReg.toSparc()));
      } else {
         list.add(new LdswSparc(SparcRegister.framePointer,
          mReg.toSparc(), (argNumber - 6) * 4 + 68));
      }

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mReg = getRepReg(hash, mReg);
   }

   public IlocInstruction copy() {
      return new LoadInArgInstr(mName, argNumber, mReg);
   }
}
