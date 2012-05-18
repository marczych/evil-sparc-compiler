import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Collection;

public class SparcRegister implements Comparable<SparcRegister> {
   // in order of preference for register allocation
   public enum TYPE {LOCAL, IN, OUT, GLOBAL, VIRTUAL, FP, SP, SPILL};

   protected int mNum;
   protected TYPE mType;

   public static SparcRegister RETURN_REG = new SparcRegister(TYPE.IN, 0);
   public static SparcRegister ZERO = new SparcRegister(TYPE.GLOBAL, 0);
   public static ArrayList<SparcRegister> inRegs, outRegs, globalRegs, localRegs;
   public static int spillCount = 0;
   public static Hashtable<SparcRegister, SparcRegister> regMap;
   public static Hashtable<SparcRegister, SparcRegister> spillMap;
   public static SparcRegister framePointer;
   public static SparcRegister stackPointer;

   static {
      framePointer = new SparcRegister(TYPE.FP, 0);
      stackPointer = new SparcRegister(TYPE.SP, 0);

      inRegs = new ArrayList<SparcRegister>();
      for (int i = 0; i < 6; i ++)
         inRegs.add(new SparcRegister(TYPE.IN, i));

      outRegs = new ArrayList<SparcRegister>();
      for (int i = 0; i < 6; i ++)
         outRegs.add(new SparcRegister(TYPE.OUT, i));

      localRegs = new ArrayList<SparcRegister>();
      for (int i = 0; i < 8; i ++)
         localRegs.add(new SparcRegister(TYPE.LOCAL, i));

      globalRegs = new ArrayList<SparcRegister>();
      for (int i = 0; i < 8; i ++)
         globalRegs.add(new SparcRegister(TYPE.GLOBAL, i));

      regMap = new Hashtable<SparcRegister, SparcRegister>();
      spillMap = new Hashtable<SparcRegister, SparcRegister>();
   }

   public static void addToRegHash(Collection<RegGraph.Node> nodes) {
      for (RegGraph.Node node : nodes)
         regMap.put(node.mReg, node.mReal);
   }

   public static void addToSpillHash(RegGraph.Node node) {
      spillMap.put(node.mReg, node.mReal);
   }

   public static SparcRegister getZero() {
      return globalRegs.get(0);
   }

   public SparcRegister toSparc() {
      return this;
   }

   public int hashCode() {
      return print().hashCode();
   }

   public boolean isReal() {
      return mType != TYPE.VIRTUAL;
   }

   public int compareTo(SparcRegister o) {
      int cmp;
      if ((cmp = mType.compareTo(o.mType)) == 0 && mNum == o.mNum)
         return 0;
      else if (cmp != 0)
         return cmp;
      else
         return mNum - o.mNum;
   }

   public static SparcRegister getLocal(int num) {
      return localRegs.get(num);
   }

   public static SparcRegister getGlobal(int num) {
      return globalRegs.get(num);
   }

   public static SparcRegister getIn(int num) {
      return inRegs.get(num);
   }

   public static SparcRegister getOut(int num) {
      return outRegs.get(num);
   }

   public static SparcRegister makeNextSpill() {
      return new SparcRegister(TYPE.SPILL, getNextSpill());
   }

   public static int getNextSpill() {
      return spillCount++;
   }

   public SparcRegister(TYPE type, int num) {
      mNum = num;
      mType = type;
   }

   public void setNum(int num) {
      mNum = num;
   }

   public int getNum() {
      return mNum;
   }

   public String print() {
      switch (mType) {
         case IN:
            return "%i" + mNum;
         case OUT:
            return "%o" + mNum;
         case GLOBAL:
            return "%g" + mNum;
         case LOCAL:
            return "%l" + mNum;
         case VIRTUAL:
            return "%vir" + mNum;
         case FP:
            return "%fp";
         case SP:
            return "%sp";
         case SPILL:
            return "%spill" + mNum;
         default:
            return "%" + mNum;
      }
   }

   public String toString() {
      SparcRegister ret;

      if (mType == TYPE.VIRTUAL && (ret = regMap.get(this)) != null)
         return ret.toString();

      return print();
   }

   public boolean equals(Object o) {
      if (o instanceof SparcRegister) {
         SparcRegister other = (SparcRegister)o;
         return mType.equals(other.mType) && mNum == other.mNum;
      }

      return false;
   }
}
