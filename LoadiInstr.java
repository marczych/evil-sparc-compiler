import java.util.ArrayList;
import java.util.Hashtable;

public class LoadiInstr extends IlocInstruction {
   protected static int mask = 0xFFF;
   private Register mDest;
   private int mImmed;

   public LoadiInstr(Register dest, int immed) {
      mDest = dest;
      mImmed = immed;
   }

   public String toIloc() {
      return "loadi " + mImmed + ", " + mDest.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      if (mImmed != 0) {
         if ((mImmed & mask) == mImmed)
            list.add(new MoviSparc(mImmed, mDest.toSparc()));
         else {
            list.add(new SethiSparc(mImmed + "", mDest.toSparc()));
            list.add(new OrLoSparc(mImmed + "", mDest.toSparc(), mDest.toSparc()));
         }
      }
      else
         list.add(new MovSparc(SparcRegister.getGlobal(0), mDest.toSparc()));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mDest = getRepReg(hash, mDest);
   }

   public IlocInstruction copy() {
      return new LoadiInstr(mDest, mImmed);
   }
}
