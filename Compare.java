public enum Compare {
   EQ, LT, GT, NE, LE, GE;

   public Compare reverse() {
      switch (this) {
      case EQ:
         return NE;
      case LT:
         return GE;
      case GT:
         return LE;
      case NE:
         return EQ;
      case LE:
         return GT;
      case GE:
         return LT;
      default:
         return EQ;
      }
   }
};
