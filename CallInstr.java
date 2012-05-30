import java.util.ArrayList;
import java.util.Hashtable;

public class CallInstr extends IlocInstruction {
   private String mFun;
   private boolean mTail;

   public CallInstr(String fun) {
      this(fun, false);
   }

   public CallInstr(String fun, boolean tail) {
      mFun = fun;
      mTail = tail;
   }

   public String getFunName() {
      return mFun.substring(3, mFun.length());
   }

   public void setTail(boolean tail) {
      mTail = tail;
   }

   public String toIloc() {
      return "call "+mFun;
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new CallSparc(mFun, mTail));
      list.add(new NopSparc());

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
   }

   public IlocInstruction copy() {
      return new CallInstr(mFun);
   }
}
