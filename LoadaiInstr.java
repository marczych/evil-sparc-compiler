import java.util.ArrayList;
import java.util.Hashtable;

public class LoadaiInstr extends IlocInstruction {
   private Register mReg, mDest;
   private String mOff;
   private Struct mStruct;

   public LoadaiInstr(Register reg, String off, Struct struct, Register dest) {
      mReg = reg;
      mOff = off;
      mDest = dest;
      mStruct = struct;
   }

   public String toIloc() {
      return "loadai " + mReg.toIloc() + ", "+mOff+", "+mDest.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new LdswSparc(mReg.toSparc(), mDest.toSparc(), mStruct.getOffset(mOff)));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mDest = getRepReg(hash, mDest);
      mReg = getRepReg(hash, mReg);
   }

   public IlocInstruction copy() {
      return new LoadaiInstr(mReg, mOff, mStruct, mDest);
   }
}
