import java.util.ArrayList;
import java.util.Hashtable;

public class PrintInstr extends IlocInstruction {
   protected Register mSrc;
   protected boolean mEndl;

   public PrintInstr(Register src, boolean endl) {
      mSrc = src;
      mEndl = endl;
   }

   public String toIloc() {
      return (mEndl ? "println " : "print ") + mSrc.toIloc();
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new SethiSparc(getPrintStr(), SparcRegister.getOut(0)));
      list.add(new OrLoSparc(getPrintStr(), SparcRegister.getOut(0), SparcRegister.getOut(0)));
      list.add(new MovSparc(mSrc.toSparc(), SparcRegister.getOut(1)));
      list.add(new CallSparc("printf"));
      list.add(new NopSparc());

      return list;
   }

   private String getPrintStr() {
      return mEndl ? CFG.PRINTF_ENDL_GLOBAL : CFG.PRINTF_GLOBAL;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mSrc = getRepReg(hash, mSrc);
   }

   public IlocInstruction copy() {
      return new PrintInstr(mSrc, mEndl);
   }
}
