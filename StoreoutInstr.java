import java.util.ArrayList;
import java.util.Hashtable;

public class StoreoutInstr extends IlocInstruction {
   protected Register mSrc;
   protected int mArgNumber;
   protected boolean mTail;

   public StoreoutInstr(Register src, int argNum) {
      mSrc = src;
      mArgNumber = argNum;
      mTail = false;
   }

   public void setTail(boolean tail) {
      mTail = tail;
   }

   public String toIloc() {
      return "storeoutargument " + mSrc.toIloc() + ", " + mArgNumber;
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      if (mArgNumber < 6) {
         list.add(new MovSparc(mSrc.toSparc(), getArgReg()));
      } else {
         list.add(new StSparc(mSrc.toSparc(), SparcRegister.stackPointer,
          (mArgNumber - 6) * 4 + 68));
      }

      return list;
   }

   private SparcRegister getArgReg() {
      if (mTail) {
         return SparcRegister.getIn(mArgNumber);
      } else {
         return SparcRegister.getOut(mArgNumber);
      }
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mSrc = getRepReg(hash, mSrc);
   }

   public IlocInstruction copy() {
      return new StoreoutInstr(mSrc, mArgNumber);
   }
}
