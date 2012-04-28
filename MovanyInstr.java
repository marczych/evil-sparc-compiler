import java.util.ArrayList;
import java.util.Hashtable;

public class MovanyInstr extends IlocInstruction {
	private int mVal;
	private Register mDest;
	private String mType;

   public MovanyInstr(String type, int val, Register dest) {
		mType = type;
      mVal = val;
		mDest = dest;
   }

   public String toIloc() {
		return "mov"+mType+" "+mVal+", "+mDest.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

		String type = mType;
	
		if(mType.equals("gt"))
				  type = "g";
		else if(mType.equals("lt"))
				  type = "l";

		list.add(new CondMovSparc(type, mDest.toSparc(), mVal));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mDest = getRepReg(hash, mDest);
   }

   public IlocInstruction copy() {
      return new MovanyInstr(mType, mVal, mDest);
   }
}
