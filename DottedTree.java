import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

public class DottedTree
   extends CommonTree
{
   public DottedTree()
   {
      super();
   }

   public DottedTree(CommonTree node)
   {
      super(node);
   }

   public DottedTree(Token t)
   {
      super(t);
   }

   public DottedTree(DottedTree node)
   {
      super(node);
   }

   public Tree dupNode()
   {
      return new DottedTree(this);
   }
}

