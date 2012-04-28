import java.util.ArrayList;
import java.util.Hashtable;

public class CallInstr extends IlocInstruction {
	private String mFun;
   public CallInstr(String fun) {
      mFun = fun;
   }

   public String toIloc() {
		return "call "+mFun;
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new CallSparc(mFun));
      list.add(new NopSparc());

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
   }

   public IlocInstruction copy() {
      return new CallInstr(mFun);
   }
}
