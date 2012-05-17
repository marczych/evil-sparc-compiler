import java.util.TreeSet;
import java.util.Hashtable;
import java.util.Collection;
import java.util.Stack;

public class RegGraph {
   static {
      colors = addColors();
      restricted = new Hashtable<SparcRegister, TreeSet<SparcRegister>>();
   }

   public static class Node implements Comparable<Node> {
      public SparcRegister mReg, mReal;
      public TreeSet<Node> mEdges;

      public Node(SparcRegister reg) {
         mReg = reg;
         mReal = reg.isReal() ? reg : null;
         mEdges = new TreeSet<Node>();
      }

      public int getNumEdges() {
         return mEdges.size();
      }

      public String toString() {
         String temp = "Reg: " + mReg + ", Real: " + (mReal == null ? "null" :
          mReal) + ", Edges (" + mEdges.size() + "): ";

         for (Node node : mEdges)
            temp += node.mReg.toString() + ", ";

         return temp;
      }

      public int compareTo(Node o) {
         return mReg.compareTo(o.mReg);
      }
   }

   public Hashtable<SparcRegister, Node> mNodes;
   public static Hashtable<SparcRegister, TreeSet<SparcRegister>> restricted;
   public static TreeSet<SparcRegister> colors;

   public RegGraph() {
      mNodes = new Hashtable<SparcRegister, Node>();
   }

   public static void addRestricted(SparcRegister reg, TreeSet<SparcRegister> res) {
      TreeSet<SparcRegister> ret;
      if ((ret = restricted.get(reg)) == null)
         restricted.put(reg, res);
      else
         ret.addAll(res);
   }

   private static TreeSet<SparcRegister> addColors() {
      TreeSet<SparcRegister> cols = new TreeSet<SparcRegister>();

      cols.addAll(SparcRegister.inRegs);
      cols.addAll(SparcRegister.outRegs);
      cols.addAll(SparcRegister.localRegs);
      cols.add(SparcRegister.getGlobal(1));
      cols.add(SparcRegister.getGlobal(2));
      cols.add(SparcRegister.getGlobal(3));
      cols.add(SparcRegister.getGlobal(4));
      cols.add(SparcRegister.getGlobal(5));

      return cols;
   }

   public void addEdge(SparcRegister one, SparcRegister two) {
      Node nOne, nTwo;

      if (one.compareTo(two) == 0) {
         //add to graph
         getNode(one);
         return;
      }

      nOne = getNode(one);
      nTwo = getNode(two);

      if (nOne == null || nTwo == null)
         return;

      nOne.mEdges.add(nTwo);
      nTwo.mEdges.add(nOne);
   }

   public Node getNode(SparcRegister reg) {
      Node ret;

      if (reg.compareTo(SparcRegister.getGlobal(0)) == 0 ||
       reg.compareTo(SparcRegister.framePointer) == 0)
         return null;

      if ((ret = mNodes.get(reg)) == null)
         mNodes.put(reg, ret = new Node(reg));

      return ret;
   }

   public void printGraph() {
      Collection<Node> nodes = mNodes.values();

      System.out.println("NEW GRAPH");
      System.out.println();

      for (Node nod : nodes)
         System.out.println(nod);
   }

   public void removeNode(Node node) {
      for (Node nod : node.mEdges)
         nod.mEdges.remove(node);
   }

   public void addNode(Node node) {
      for (Node nod : node.mEdges)
         nod.mEdges.add(node);
   }

   public boolean colorGraph() {
      Collection<Node> tempNodes = mNodes.values();
      TreeSet<Node> nodes = new TreeSet<Node>();

      for (Node nod : tempNodes)
         nodes.add(nod);

      Stack<Node> remNodes = new Stack<Node>();
      Node nawd = null;

      //printGraph();

      while (nodes.size() > 0) {
         for (;;) {
            //unconstrained virtual
            
            nawd = null;
            for (Node node : nodes) {
               if (node.mEdges.size() < RegGraph.colors.size() && nawd == null) {
                  nawd = node;
               }
            }

            if (nawd != null) {
               nodes.remove(nawd);
               removeNode(nawd);
               remNodes.push(nawd);
               nawd = null;
            }
            else
               break;
         }
         
         //only gets here when no unconstrained nodes can be found
         if (nodes.size() != 0) {
            nodes.remove(nawd = nodes.first());
            removeNode(nawd);
            remNodes.push(nawd);
            nawd.mReal = SparcRegister.makeNextSpill();
            SparcRegister.addToSpillHash(nawd);
            
            return false;
         }
      }

      Node node;
      TreeSet<SparcRegister> availColors, resSet;
      //System.out.println("SIZE:" + remNodes.size());
      while (!remNodes.empty()) {
         node = remNodes.pop();
         addNode(node);

         if (node.mReg.isReal()) {
            node.mReal = node.mReg;
            continue;
         }

         availColors = (TreeSet<SparcRegister>)RegGraph.colors.clone();
         for (Node nod : node.mEdges) {
            availColors.remove(nod.mReal);
         }  
         if ((resSet = RegGraph.restricted.get(node.mReg)) != null) {
            availColors.removeAll(resSet);
         }
         if (availColors.size() == 0)
            System.err.println("No colors for: " + node);
         node.mReal = availColors.first();
      }

      //printGraph();
      SparcRegister.addToRegHash(mNodes.values());

      return true;
   }
}
