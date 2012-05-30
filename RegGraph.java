import java.util.ConcurrentModificationException;
import java.util.TreeSet;
import java.util.Hashtable;
import java.util.Collection;
import java.util.Stack;
import java.util.Enumeration;
import java.util.ArrayList;

public class RegGraph {
   static {
      colors = addColors();
      restricted = new Hashtable<SparcRegister, TreeSet<SparcRegister>>();
   }

   public static class Node implements Comparable<Node> {
      public SparcRegister mReg, mReal;
      public TreeSet<Node> mEdges;
	  public boolean moveRelated = false;
	  public MovSparc movInstr = null;
	  public Block block = null;

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
      
      @Override
      public boolean equals(Object o)
      {
    	  if (!(o instanceof Node))
    		  return false;
    	  
    	  return this.compareTo((Node) o) == 0;
      }
   }
   
   public static class MovSparcBlockPair
   {
	   public MovSparc instr;
	   public Block block;
	   
	   public MovSparcBlockPair(MovSparc instr, Block block)
	   {
		   this.instr = instr;
		   this.block = block;
	   }
	   
	   public String toString()
	   {
		   return instr.toString() + " (" + block.toString() + ")";
	   }
	   
	   @Override
	   public boolean equals(Object other)
	   {
		   if (!(other instanceof MovSparcBlockPair))
			   return false;
		   
		   MovSparcBlockPair otherPair = (MovSparcBlockPair) other;
		   return (this.instr.getSources().get(0).compareTo(otherPair.instr.getSources().get(0)) == 0) &&
		   			(this.instr.getDests().get(0).compareTo(otherPair.instr.getDests().get(0)) == 0) &&
		   			(this.block.toString().equals(otherPair.block.toString()));
	   }
   }

   public Hashtable<SparcRegister, Node> mNodes;
   public static Hashtable<SparcRegister, TreeSet<SparcRegister>> restricted;
   public static TreeSet<SparcRegister> colors;

	ArrayList<Node> initial;		// unprocessed nodes
	ArrayList<Node> simplifyList;	// low-degree, non-move-related
	ArrayList<Node> spillList;	// degree > K
	ArrayList<Node> freezeList; 	// low-degree move-related
	ArrayList<Node> spilled;		// marked for spilling during this iteration
	ArrayList<Node> coalesced;	// when mov a->b is coalesced, a or b is added to this list, and the other is added to another work list
	ArrayList<Node> colored;
	Stack<Node> selectStack;	// temps removed from graph

	Hashtable<Node, ArrayList<MovSparcBlockPair>> moveList;
	
	// Move sets (every move in exactly one of these):
	public ArrayList<MovSparcBlockPair> coalescedMoves;
	ArrayList<MovSparcBlockPair> constrainedMoves; // source and destination interfere
	ArrayList<MovSparcBlockPair> frozenMoves;		 // no longer considered for coalescing
	ArrayList<MovSparcBlockPair> worklistMoves;	 // coalesce candidates
	ArrayList<MovSparcBlockPair> activeMoves;		 // not yet ready for coalescing
	
	ArrayList<String> coalescedMoveNames = new ArrayList<String>();
	
	Hashtable<Node, Node> alias;
   
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
    
	public boolean iteratedRegisterCoalescing(ArrayList<Block> allBlocks)
	{
		//buildGraph(allBlocks);
		for (Node n : mNodes.values())
		{
			initial.add(n);
		}
		
		makeWorkLists();

		while (!simplifyList.isEmpty() || !worklistMoves.isEmpty() || !freezeList.isEmpty() || !spillList.isEmpty())
		{
			if (!simplifyList.isEmpty())
				simplify();
			else if (!worklistMoves.isEmpty())
				coalesce();
			else if (!freezeList.isEmpty())
				freeze();
			else if (!spillList.isEmpty())
				selectSpill();
		}
	
		assignColors();
	
		if (!spilled.isEmpty())
		{
			// nodes.remove(nawd = nodes.first());
			// removeNode(nawd);
			// remNodes.push(nawd);
			// nawd.mReal = SparcRegister.makeNextSpill();
			// SparcRegister.addToSpillHash(nawd);
			
			for (Node n : spilled)
			{
				n.mReal = SparcRegister.makeNextSpill();
				SparcRegister.addToSpillHash(n);
			}
			
			return false;
		}
		else
		{
			for (MovSparcBlockPair mbp : coalescedMoves)
			{
				mbp.block.removeInstruction(mbp.instr);
			}
			
			return true;
		}
	}
	
	private void assignColors()
	{
		while (!selectStack.isEmpty())
		{
			Node n = selectStack.pop();
			
			ArrayList<SparcRegister> okColors = new ArrayList<SparcRegister>();
			okColors.addAll(colors);
			
			for (Node w : n.mEdges)
			{
				if (colored.contains(getAlias(w)))
				{
					okColors.remove(getAlias(w).mReal);
				}
			}
			if (RegGraph.restricted.get(n) != null)
			{
				for (SparcRegister reg : RegGraph.restricted.get(n.mReg))
				{
					okColors.remove(reg);
				}
			}
			
			if (okColors.isEmpty())
			{
				spilled.add(n);
			}
			else
			{
				if (n.mReal == null)
				{
					//System.out.println("Assigning: " + n.mReg + " -> " + okColors.get(0));
					n.mReal = okColors.get(0);
				}
				colored.add(n);
			}
		}
		for (Node n : coalesced)
		{
			n.mReal = getAlias(n).mReal;
		}
		
		for (Node n : mNodes.values())
		{
			int i = colored.indexOf(n);
			if (i != -1)
			{
				n.mReal = colored.get(i).mReal;
			}
		}
		
		if (spilled.isEmpty())
			SparcRegister.addToRegHash(mNodes.values());
		
		return;
	}
	
	public void initializeIRC()
	{
		moveList = new Hashtable<Node, ArrayList<MovSparcBlockPair>>();
		initial = new ArrayList<Node>();
		simplifyList = new ArrayList<Node>();
		spillList = new ArrayList<Node>();
		freezeList = new ArrayList<Node>();
		spilled = new ArrayList<Node>();
		coalesced = new ArrayList<Node>();
		colored = new ArrayList<Node>();
		selectStack = new Stack<Node>();
		
		coalescedMoves = new ArrayList<MovSparcBlockPair>();
		constrainedMoves = new ArrayList<MovSparcBlockPair>();
		frozenMoves = new ArrayList<MovSparcBlockPair>();
		worklistMoves = new ArrayList<MovSparcBlockPair>();
		activeMoves = new ArrayList<MovSparcBlockPair>();
		
		alias = new Hashtable<Node, Node>();
		
		// populate list of initial nodes
	}
	
	public void buildGraph(ArrayList<Block> allBlocks)
	{
		for (Block b : allBlocks)
		{
			// get block's liveOut
			TreeSet<SparcRegister> live = b.mLiveOut;
			
			// for all instructions instr in the block (in reverse order)
			ArrayList<SparcInstruction> sparcList = b.getSparcList();
			for (int i = sparcList.size() - 1; i >= 0; i --)
			{
				SparcInstruction instr = sparcList.get(i);
				//SparcRegister src = instr.getSources().size() > 0 ? instr.getSources().get(0) : null;
				//SparcRegister dst = instr.getDests().size() > 0 ? instr.getDests().get(0) : null;
				if (instr.isMove())
				{
					SparcRegister src = instr.getSources().get(0);
					SparcRegister dst = instr.getDests().get(0);
					
					// remove instr's source from live
					live.remove(src);
					
					// add src and dst nodes to moveList
					Node n = getNode(src);
					if (n != null)
					{
						addToMoveList(n, (MovSparc) instr, b);
						n.moveRelated = true;
						//n.movInstr = (MovSparc) instr;
						//n.block = b;
					}
					n = getNode(dst);
					if (n != null)
					{
						addToMoveList(n, (MovSparc) instr, b);
						n.moveRelated = true;
						//n.movInstr = (MovSparc) instr;
						//n.block = b;
					}
					
					// add instr to worklistMoves
					worklistMoves.add(new MovSparcBlockPair((MovSparc) instr, b));
				}
				
				// live = live union def(instr)
				for (SparcRegister reg : instr.getDests())
				{
					live.add(reg);
				}
				
				// for all reg in def(instr)
				for (SparcRegister reg : instr.getDests())
				{
					// for all l in live
					for (SparcRegister l : live)
					{
						// add edge (l, reg)
						addEdge(l, reg);
					}
				}
				
				// live = use(instr) union (live - def(instr))
				for (SparcRegister src : instr.getSources())
				{
					live.add(src);
				}
				for (SparcRegister dst : instr.getDests())
				{
					live.remove(dst);
				}
			}
		}
		return;
	}
	
	public void makeWorkLists()
	{
		for (Node n : initial)
		{
			if (n.mEdges.size() >= colors.size())
			{
				spillList.add(n);
			}
			else if (n.moveRelated)
			{
				freezeList.add(n);
			}
			else
			{
				simplifyList.add(n);
			}		
		}
		
		initial.clear();
		return;
	}
	
	public void simplify()
	{
		Node n = simplifyList.get(0);
		//System.out.println("Simplifying node " + n.mReg);
		simplifyList.remove(n);
		selectStack.push(n);
		//TreeSet<Node> edgesClone = (TreeSet<Node>) n.mEdges.clone();
		for (Node m : n.mEdges/*edgesClone*/)
		{
			decrementDegree(m, n);
		}
		return;
	}
	
	private void decrementDegree(Node m, Node n)
	{
		//System.out.println("decrementDegree: " + m + ", " + n);
		int d = m.mEdges.size();
		//System.out.println("Degree of " + m + " is " + d);
		m.mEdges.remove(n);
		if (d == colors.size())
		{
			enableMoves(m);
			enableMoves(adjacent(m));
			spillList.remove(m);
			if (!nodeMoves(m).isEmpty())
			{
				freezeList.add(m);
			}
			else
			{
				simplifyList.add(m);
			}
		}
	}
	
	private void enableMoves(ArrayList<Node> nodes)
	{
		for (Node n : nodes)
		{
			enableMoves(n);
		}
	}
	
	private void enableMoves(Node n)
	{
		//System.out.println("enableMoves(" + n + ")");
		for (MovSparcBlockPair m : nodeMoves(n))
		{
			//System.out.println("Found in move list:" + m);
			if (activeMoves.contains(m))
			{
				//System.out.println(m + " is in activeMoves");
				activeMoves.remove(m);
				worklistMoves.add(m);
			}
		}
	}
	
	// adjacent(n) --> adjList[n] - (selectStack union coalescedNodes)
	private ArrayList<Node> adjacent(Node n)
	{
		ArrayList<Node> ret = new ArrayList<Node>();
		for (Node adj : n.mEdges)
		{
			if (selectStack.search(adj) == -1 && !coalesced.contains(adj))
				ret.add(adj);
		}
		return ret;
	}
	
	//nodeMoves(n) --> moveList[n] intersect (activeMoves union worklistMoves)
	private ArrayList<MovSparcBlockPair> nodeMoves(Node n)
	{
		ArrayList<MovSparcBlockPair> ret = new ArrayList<MovSparcBlockPair>();
		
		if (moveList.get(n) == null)
			return ret;
		
		for (MovSparcBlockPair instr : moveList.get(n))
		{
			boolean inActiveMoves = activeMoves.contains(instr);
			//boolean inWorklistMoves = worklistMoves.contains(instr);
			boolean inWorklistMoves = worklistMoves.contains(instr);
			if (inActiveMoves || inWorklistMoves)
				ret.add(instr);
		}
		
		return ret;
	}
	
	private void freeze()
	{
		Node u = freezeList.get(0);
		//System.out.println("Freezing " + u.mReg);
		freezeList.remove(u);
		simplifyList.add(u);
		freezeMoves(u);
	}

	private void freezeMoves(Node u)
	{
		for (MovSparcBlockPair m : nodeMoves(u))
		{
			if (activeMoves.contains(m))
				activeMoves.remove(m);
			else
				worklistMoves.remove(m);
			
			frozenMoves.add(m);
			
			Node x = getNode(m.instr.getSources().get(0));
			Node y = getNode(m.instr.getDests().get(0));
			Node v = getAlias(y).equals(u) ? getAlias(x) : getAlias(y);
			//Node v = y.equals(u) ? x : y;
			
			if (nodeMoves(v).isEmpty() && v.mEdges.size() < colors.size())
			{
				freezeList.remove(getNode(m.instr.getSources().get(0)));
				simplifyList.add(getNode(m.instr.getSources().get(0)));
			}
		}
	}
	
	private void coalesce()
	{
		MovSparcBlockPair m = worklistMoves.get(0);
		//System.out.println("Coalescing " + m);
		Node x = getNode(m.instr.getSources().get(0));
		Node y = getNode(m.instr.getDests().get(0));
		
		x = getAlias(x);
		y = getAlias(y);
		Node u, v;
		if (y.mReal != null)	// y is precolored
		{
			u = y;		// u -> dest
			v = x;		// v -> src
		}
		else
		{
			u = x;		// u -> src
			v = y;		// v -> dest
		}
		
		worklistMoves.remove(m);
		if (u.equals(v))
		{
			//System.out.println("Src and dst equivalent.");
			coalescedMoves.add(m);
			coalescedMoveNames.add(m.instr.toString());
			moveList.get(u).remove(m.instr);
			addWorkList(u);
		}
		else if (v.mReal != null || u.mEdges.contains(v)) // the mov's src and dest interfere
		{
			//System.out.println("Move is constrained.");
			constrainedMoves.add(m);
			moveList.get(u).remove(m);
			moveList.get(v).remove(m);
			addWorkList(u);
			addWorkList(v);
		}
		else if (/*(u.mReal != null && precoloredCoalesceOK(u, v)) ||*/ (u.mReal == null && okToCoalesce(u, v)))
		{
			if ((u.mReal != null && precoloredCoalesceOK(u, v)))
				System.out.print("");
			
			//System.out.println("OK to coalesce.");
			coalescedMoves.add(m);
			coalescedMoveNames.add(m.instr.toString());
			combine(u, v);
			moveList.get(u).remove(m.instr);
			addWorkList(u);
		}
		else
		{
			//System.out.println("Adding to active moves.");
			activeMoves.add(m);
		}
	}
	
	private Node getAlias(Node n)
	{
		if (coalesced.contains(n))
		{
			return getAlias(alias.get(n));
		}
		else
		{
			return n;
		}
	}

	private boolean okToCoalesce(Node u, Node v)
	{
		int k = 0;
		for (Node n : adjacent(u))
		{
			if (n.mEdges.size() >= colors.size()) ++k;
		}
		for (Node n : adjacent(v))
		{
			if (n.mEdges.size() >= colors.size()) ++k;
		}
		return k < colors.size();
	}
	
	private boolean precoloredCoalesceOK(Node u, Node v)
	{
		boolean ok = true;
		for (Node t : adjacent(v))
		{
			ok &= t.mEdges.size() < colors.size() || t.mReal != null || t.mEdges.contains(u);
		}
		return ok;
	}

	private void addWorkList(Node u)
	{
		if (u.mReal != null && nodeMoves(u).size() == 0 && u.mEdges.size() < colors.size())
		{
			freezeList.remove(u);
			simplifyList.add(u);
		}
	}

	private void combine(Node u, Node v)
	{
		//System.out.println("Combining " + u.mReg + ", " + v.mReg);
		if (freezeList.contains(v))
			freezeList.remove(v);
		else
			spillList.remove(v);
			
		coalesced.add(v);
		alias.put(v, u);
		
		// pseudocode says nodeMoves[u] := nodeMoves[u] union nodeMoves[v]... idk what this is doing.
		// I feel like if they meant moveList they would've said moveList, but there isn't a nodeMoves data
		// structure mentioned in the pseudocode anywhere...
		for (MovSparcBlockPair instr : nodeMoves(v))
		{
			if (!moveList.get(u).contains(instr))
				moveList.get(u).add(instr);
		}
		
		for (Node t : adjacent(v))
		{
			t.mEdges.add(u);
			u.mEdges.add(t);
			decrementDegree(t, v);
		}
		if (u.mEdges.size() >= colors.size() && freezeList.contains(u))
		{
			freezeList.remove(u);
			spillList.add(u);
		}
	}
	
	private void addToMoveList(Node n, MovSparc instr, Block b)
	{
		if (!moveList.containsKey(n))
			moveList.put(n, new ArrayList<MovSparcBlockPair>());
		
		//System.out.println("Adding " + reg.toString() + " -> " + instr.toString() + " to move list");
		moveList.get(n).add(new MovSparcBlockPair(instr, b));
	}
	
	private void selectSpill()
	{
		Node n = spillList.get(0);
		spillList.remove(n);
		simplifyList.add(n);
		freezeMoves(n);
	}
	
	private void rewriteProgram(ArrayList<Block> allBlocks)
	{
		ArrayList<Node> newTemps = new ArrayList<Node>();
		
		// code for handling spills goes here.
		// (add all newly created temporary regs to newTemps)
		
		spilled.clear();
		initial.addAll(colored);
		initial.addAll(coalesced);
		initial.addAll(newTemps);
		colored.clear();
		coalesced.clear();
	}

	public void addInstrToMoveList(MovSparc instr, Block block)
	{
		Node nSrc = getNode(instr.getSources().get(0));
		Node nDest = getNode(instr.getDests().get(0));
		
		worklistMoves.add(new MovSparcBlockPair(instr, block));
		
		if (nSrc != null)
		{
			nSrc.movInstr = instr;
			nSrc.block = block;
			nSrc.moveRelated = true;
			addToMoveList(nSrc, instr, block);
		}
		
		if (nDest != null)
		{
			nDest.movInstr = instr;
			nDest.block = block;
			nDest.moveRelated = true;
			addToMoveList(nDest, instr, block);
		}
	}
   
   public void addEdge(SparcRegister one, SparcRegister two)
   {
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

      if (/*reg.compareTo(SparcRegister.getGlobal(0)) == 0 ||*/
       reg.compareTo(SparcRegister.framePointer) == 0)
         return null;

      if ((ret = mNodes.get(reg)) == null)
	  {
         mNodes.put(reg, ret = new Node(reg));
	  }

      return ret;
   }
   
   public void printMoveRelatedNodes()
   {
	Enumeration keys = mNodes.keys();
	while (keys.hasMoreElements())
	{
		SparcRegister sr = (SparcRegister) keys.nextElement();
		Node n = mNodes.get(sr);
		if (n.moveRelated)
		{
			System.out.println("Found a move-related node. Instruction: " + n.movInstr.toString() + ", block: " + n.block.toString());
		}
	}
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
	  //System.out.println("****************************");
	  //	for (Node n : mNodes.values())
		//{
		//	System.out.println("mReg = " + n.mReg + ", mReal = " + n.mReal);
		//}
      SparcRegister.addToRegHash(mNodes.values());

      return true;
   }
}
