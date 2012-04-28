import java.util.ArrayList;
import java.util.Hashtable;

public class StoreaiInstr extends IlocInstruction {
	private Register mVal, mVar;
	private String mOff;
   private Struct mStruct;

   public StoreaiInstr(Register val, Register var, String off, Struct str) {
      mVal = val;
		mVar = var;
		mOff = off;
      mStruct = str;
   }

   public String toIloc() {
      return "storeai "+mVal.toIloc()+", "+mVar.toIloc()+", "+mOff + 
        " (" + mStruct.getOffset(mOff) + " - " + mStruct + ")";
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new StSparc(mVal.toSparc(), mVar.toSparc(), mStruct.getOffset(mOff)));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mVal = getRepReg(hash, mVal);
      mVar = getRepReg(hash, mVar);
   }

   public IlocInstruction copy() {
      return new StoreaiInstr(mVal, mVar, mOff, mStruct);
   }
}
