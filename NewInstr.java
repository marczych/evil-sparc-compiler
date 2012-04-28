import java.util.ArrayList;
import java.util.Hashtable;

public class NewInstr extends IlocInstruction {
   protected ArrayList<String> mFields;
   protected Struct mStruct;
   protected Register mDest;

   public NewInstr(Struct str, Register reg) {
      mStruct = str;
      mFields = mStruct.getFieldNames();
      mDest = reg;
   }

   public String toIloc() {
      String temp = "new " + mStruct.getType() + ", [";

      for (String field : mFields) {
         temp += field + ", ";
      }

      temp = temp.substring(0, temp.length() - 2) + "], " + mDest.toIloc();

      return temp;
   }

   public ArrayList<SparcInstruction> toSparc() {
      ArrayList<SparcInstruction> list = new ArrayList<SparcInstruction>();

      list.add(new MoviSparc(mStruct.getSize(), SparcRegister.getOut(0)));
      list.add(new CallSparc("malloc"));
      list.add(new NopSparc());
      list.add(new MovSparc(SparcRegister.getOut(0), mDest.toSparc()));

      return list;
   }

   public void regReplace(Hashtable<Register, Register> hash) {
      mDest = getRepReg(hash, mDest);
   }

   public IlocInstruction copy() {
      return new NewInstr(mStruct, mDest);
   }
}
