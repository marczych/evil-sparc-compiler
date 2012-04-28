import java.util.ArrayList;
import java.util.Hashtable;

public class LoadGlobalInstr extends IlocInstruction {
   protected Register mDest;
   protected String mId;

   public LoadGlobalInstr(String id, Register dest) {
      mDest = dest;
      mId = id;
   }

   public String toIloc() {
      return "loadglobal " + mId + ", " + mDest.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();
      SparcRegister dest = mDest.toSparc();

      list.add(new SethiSparc(mId, dest));
      list.add(new OrLoSparc(mId, dest, dest));
      list.add(new LdswSparc(dest, dest, 0));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mDest = getRepReg(hash, mDest);
   }

   public IlocInstruction copy() {
      return new LoadGlobalInstr(mId, mDest);
   }
}
