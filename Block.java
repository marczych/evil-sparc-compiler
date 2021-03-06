import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Hashtable;
import java.io.*;

public class Block {
   protected final static long BASE_INSTR_LIMIT = 15;
   public static boolean FUNCTION_INLINING = true;
   public static boolean DEAD_CODE = true;
   public static boolean TAIL_CALL = true;
   public static int deadCount = 0;
   protected static long mCount = 0;
   protected static Block curExit;
   protected static ArrayList<Block> mRoster = new ArrayList<Block>();

   protected String mLabel;
   protected ArrayList<IlocInstruction> mInstructionList;
   protected ArrayList<IlocInstruction> mInlineList;
   protected ArrayList<SparcInstruction> mSparcList;
   protected TreeSet<SparcRegister> mGenSet, mKillSet, mLiveOut;
   protected Block mThen;
   protected Block mElse;
   protected Block mInlineEntry;
   protected Block mInlineExit;
   protected long mNumber;
   protected int mLargestNumArgs;
   protected int mNumArgs;
   protected int mSpillCount;
   protected int mNumPredecessors;
   protected Register mCondReg;
   protected Compare mCondType;
   protected boolean mRemoveDeadPredecessor = false;
   protected boolean mBackwardsCondition = false;
   protected boolean mReturn = false;
   protected boolean mEntry = false;
   protected boolean mExit = false;
   protected boolean mInline = false;
   protected boolean mCallsSelf = false;
   protected boolean mLoop = false;
   protected boolean mPrinted = false;
   protected boolean mMakeSparced = false;
   protected boolean mGetAlled = false;
   protected boolean mPrintSetsed = false;

   public Block() {
      this("BLOCK");
   }

   public Block(String label) {
      mLabel = label;
      mInstructionList = new ArrayList<IlocInstruction>();
      mInlineList = new ArrayList<IlocInstruction>();
      mSparcList = new ArrayList<SparcInstruction>();
      mNumPredecessors = 0;
      mLiveOut = new TreeSet<SparcRegister>();

      mNumber = mCount++;

      mRoster.add(this);
   }
   
	public ArrayList<SparcInstruction> getSparcList()
	{
		return mSparcList;
	}

   public void setLargestNumArgs(int num) {
      mLargestNumArgs = num;
   }

   public int hashCode() {
      return getFullLabel().hashCode();
   }

   /**
    * Converts IlocInstructions into SparcInstructions and generates
    * the gen and kill set. Does the same for any subsequent blocks.
    */
   public void makeSparc() {
      if (mMakeSparced)
         return;

      mMakeSparced = true;

      for (IlocInstruction instr : mInstructionList) {
         mSparcList.addAll(instr.toSparc());
      }
	  
	  /*for (SparcInstruction instr : mSparcList)
	  {
		if (instr.isMove())
			System.out.println("Block.makeSparc(): I found a move instruction!");
	  }*/

      makeGenKill();

      if (mThen != null)
         mThen.makeSparc();
      if (mElse != null)
         mElse.makeSparc();
   }

   public boolean checkInline() {
      //don't inline main... that would be bad.
      if (FUNCTION_INLINING && !mCallsSelf && !mLoop && checkInlineLength() &&
       !getFullLabel().equals("main") && mNumArgs <= 6)
         mInline = true;
      else
         return mInline;

      mInlineEntry = inlineClone(new Hashtable<Block, Block>());

      return mInline;
   }

   private Block inlineClone(Hashtable<Block, Block> blocks) {
      Block ret;

      if ((ret = blocks.get(this)) != null) {
         return ret;
      }

      ret = new Block(getLabel() + "inline");
      ret.mCondReg = mCondReg;
      ret.mBackwardsCondition = mBackwardsCondition;
      ret.mCondType = mCondType;
      blocks.put(this, ret);

      for (IlocInstruction instr : mInstructionList) {
         if (instr instanceof LoadInArgInstr) {
            LoadInArgInstr loadIn = (LoadInArgInstr)instr;
            ret.addInstruction(new LoadInInlineArgInstr(loadIn.argNumber,
             loadIn.mReg));
         }
         else if (instr instanceof StoreRetInstr) {
            StoreRetInstr storeRet = (StoreRetInstr)instr;
            ret.addInstruction(new StoreInlineRetInstr(storeRet.mReg));
         }
         else if (instr instanceof RetInstr) {
            mInlineExit = ret;
         }
         else {
            ret.addInstruction(instr);
         }
      }

      if (mThen != null)
         ret.setThen(mThen.inlineClone(blocks));
      if (mElse != null)
         ret.setElse(mElse.inlineClone(blocks));

      return ret;
   }

   public Block inlineMakeClone(Hashtable<Block, Block> blocks,
    Hashtable<Register, Register> regs) {
      Block ret;

      if ((ret = blocks.get(this)) != null) {
         return ret;
      }

      ret = new Block(getLabel());
      blocks.put(this, ret);

      ret.mCondReg = mCondReg;
      ret.mCondType = mCondType;
      ret.mBackwardsCondition = mBackwardsCondition;
      IlocInstruction copy;

      for (IlocInstruction instr : mInstructionList) {
         //clone instr and do reg replace
         ret.addInstruction(copy = instr.copy());
         copy.regReplace(regs);
      }

      if (ret.mCondReg != null)
         ret.mCondReg = regs.get(ret.mCondReg);

      if (mThen != null)
         ret.setThen(mThen.inlineMakeClone(blocks, regs));
      if (mElse != null)
         ret.setElse(mElse.inlineMakeClone(blocks, regs));

      if (mThen == null && mElse == null)
         curExit = ret;

      ret.mInlineExit = curExit;

      return ret;
   }

   private boolean checkInlineLength() {
      if (getInstrCount() < BASE_INSTR_LIMIT)
         return true;
      return false;
   }

   public int getInstrCount() {
      return mInstructionList.size() + (mThen != null ?
       mThen.getInstrCount() : 0) + (mElse != null ? mElse.getInstrCount() : 0);
   }

   public void setSpillCount(int val) {
      mSpillCount = val;
   }

   public void clearMakeSparced() {
      mMakeSparced = false;
   }

   /**
    * Remakes the sparc instructions. This is called after spills occur
    * and we must inject loads and stores to get the values we care about.
    */
   public void remakeSparc() {
      if (mMakeSparced)
         return;

      mMakeSparced = true;

      ArrayList<SparcInstruction> old = mSparcList;
      mSparcList = new ArrayList<SparcInstruction>();

      // Go through every old sparc instructon and get all necessary
      // instructoins - most likely just the same one but it could also
      // have a load/store if it uses a spill register.
      for (SparcInstruction instr : old)
         mSparcList.addAll(instr.getInstrs());

      makeGenKill();

      if (mThen != null)
         mThen.remakeSparc();
      if (mElse != null)
         mElse.remakeSparc();
   }

   public TreeSet<SparcRegister> getLiveOut() {
      return mLiveOut;
   }

   public boolean checkDead() {
      TreeSet<SparcRegister> prevLive = mLiveOut, nextPrevLive = mLiveOut;
      SparcInstruction instr;
      ArrayList<SparcRegister> dests;
      ArrayList<SparcInstruction> remList = new ArrayList<SparcInstruction>();
      boolean contains;

      for (int i = mSparcList.size() - 1; i >= 0; i --) {
         instr = mSparcList.get(i);
         prevLive = nextPrevLive;
         nextPrevLive = instr.mLiveOut;
         if (!instr.canRemoveDead())
            continue;

         dests = instr.getDests();

         contains = false;
         for (SparcRegister dest : dests) {
            if (contains = prevLive.contains(dest))
               break;
         }

         if (contains)
            continue;

         remList.add(instr);
      }

      mSparcList.removeAll(remList);

      deadCount += remList.size();

      return remList.size() > 0;
   }

   public void getAllBlocks(ArrayList<Block> list) {
      if (mGetAlled)
         return;

      mGetAlled = true;

      if (mThen != null)
         mThen.getAllBlocks(list);
      if (mElse != null)
         mElse.getAllBlocks(list);

      list.add(this);
   }

   /**
    * Generates the live out set for this block.
    * Returns true if the live out has changed.
    */
   public boolean makeLiveOut() {
      TreeSet<SparcRegister> newLiveOut = new TreeSet<SparcRegister>();

      if (mThen != null)
         liveOutHelper(mThen, newLiveOut); 
      if (mElse != null)
         liveOutHelper(mElse, newLiveOut); 

      if (mLiveOut.equals(newLiveOut))
         return false;

      mLiveOut = newLiveOut;

      return true;
   }

   /**
    * Find all register interferences and add them to the given graph.
    */
   public void makeInstrLiveOut(RegGraph graph) {
      TreeSet<SparcRegister> prev = mLiveOut;
      SparcInstruction instr;

      // Currently we only have live range analysis on a block level. That is
      // to say that we know what values are needed after this block.
      // Now we need to do this for each instruction to determine interferences.
      // Loop through instructions backwards adding edges as we go.
      for (int i = mSparcList.size() - 1; i >= 0; i --) {
         instr = mSparcList.get(i);
         // Start this instruction's live out set to be the same as the
         // previous (sequentially after) instruction's liv eout.
         instr.mLiveOut = (TreeSet<SparcRegister>)prev.clone();

         for (SparcRegister dest : instr.getDests()) {
            // We're killing the value so its live range starts here.
            instr.mLiveOut.remove(dest);

            // add edge from dest to all elements in Live
            for (SparcRegister live : prev)
			{
               graph.addEdge(dest, live);
			}
         }
		 
		 // If it's a move instruction, store a reference to the instruction in the
		 // nodes corresponding to its source and destination registers.
		if (instr.isMove())
		{
			graph.addInstrToMoveList((MovSparc) instr, this);
		}

         // Any register this instruction uses is live for any previous instructions.
         for (SparcRegister src : instr.getSources()) {
            instr.mLiveOut.add(src);
         }

         prev = instr.mLiveOut;
      }
   }

   public TreeSet<SparcRegister> getKillSet() {
      return mKillSet;
   }

   public TreeSet<SparcRegister> getGenSet() {
      return mGenSet;
   }

   /**
    * Add registers to the given liveout set based on the given block's liveout,
    * gen, and kill sets.
    */
   private void liveOutHelper(Block succ, TreeSet<SparcRegister> liveOut) {
      TreeSet<SparcRegister> sLiveOut = succ.getLiveOut(), sKillSet =
       succ.getKillSet();

      liveOut.addAll(succ.getGenSet());

      for (SparcRegister reg : sLiveOut)
         if (!sKillSet.contains(reg))
            liveOut.add(reg);
   }

   public void makeGenKill() {
      mGenSet = new TreeSet<SparcRegister>();
      mKillSet = new TreeSet<SparcRegister>();
      ArrayList<SparcRegister> srcs, dests;

      for (SparcInstruction instr : mSparcList) {
         srcs = instr.getSources();
         dests = instr.getDests();

         for (SparcRegister src : srcs) {
            if (!mKillSet.contains(src))
               mGenSet.add(src);
         }

         for (SparcRegister dest : dests) {
            mKillSet.add(dest);
         }
      }
   }

   public void printSets() {
      if (mPrintSetsed)
         return;

      mPrintSetsed = true;
      if (mThen != null)
         mThen.printSets();
      if (mElse != null)
         mElse.printSets();
   }

   public void setLabel(String label) {
      mLabel = label;
   }

   public void setNumArgs(int num) {
      mNumArgs = num;
   }

   public void setEntry() {
      mEntry = true;
   }

   public void setExit() {
      mExit = true;
   }

   public void setInline() {
      mInline = true;
   }

   public void addInstruction(IlocInstruction instr) {
      if (mReturn)
         return;
      mInstructionList.add(instr);
   }
   
	public void removeInstruction(SparcInstruction instr)
	{
		mSparcList.remove(instr);
	}

   public void appendInstruction(ArrayList<IlocInstruction> instr) {
      if (mReturn)
         return;
      mInstructionList.addAll(instr);
   }

   public void appendBackwardsCondition(Register res, Compare condType) {
      appendCondition(res, condType);
      mBackwardsCondition = true;
   }

   public void appendCondition(Register res, Compare condType) {
      if (mReturn)
         return;

      mCondReg = res;
      mCondType = condType;
   }

   public void setThen(Block thenBlock) {
      if (mReturn)
         return;

      if (mThen != null) {
         mThen.removePredecessor();
      }

      mThen = thenBlock;
      if (mThen != null) {
         mThen.addPredecessor();
      }
   }

   public Block getThen() {
      return mThen;
   }

   public void setElse(Block elseBlock) {
      if (mReturn)
         return;

      if (mElse != null) {
         mElse.removePredecessor();
      }

      mElse = elseBlock;
      if (mElse != null) {
         mElse.addPredecessor();
      }
   }

   public void addPredecessor() {
      mNumPredecessors++;
   }

   public void removePredecessor() {
      mNumPredecessors--;
   }

   public static void writeIloc(FileOutputStream os) {
      try {
         for (Block block : mRoster) {
            os.write(block.toIloc().getBytes());
         }
         os.write("\n".getBytes());
      }
      catch (IOException exc) {
         System.err.println("Error writing block in Block.writeIloc!");
      }
   }

   public static ArrayList<Block> getRoster() {
      return mRoster;
   }

   public static void printIloc() {
      for (Block block : mRoster) {
         System.out.println(block.toIloc());
      }
   }

   public static void printBlocks() {
      System.out.println("Total blocks: " + mCount);

      for (Block block : mRoster) {
         System.out.println(block);
      }
   }

   public void setReturn() {
      mReturn = true;
   }

   public String getFullLabel() {
      if (mEntry)
         return mLabel.equals("main") ? mLabel : "fun" + mLabel;

      return mLabel + mNumber;
   }

   public String getLabel() {
      if (mLabel.contains("-CONT"))
         return mLabel.substring(0, mLabel.length() - 5);
      return mLabel;
   }

   //TODO Use StringBuilder
   public void toSparc(StringBuilder strBuild) {
      if (mPrinted) {
         return;
      }

      mPrinted = true;
      int saveSize = 96;

      if(mLargestNumArgs > 6)
         saveSize += (mLargestNumArgs-6)*4;
      saveSize += mSpillCount*4;
      if(saveSize % 8 != 0)
         saveSize += 4;

      strBuild.append(getFullLabel() + ":");

      if (mEntry) {
         strBuild.append("\n\t!#PROLOGUE# 0\n");
         strBuild.append("\tsave\t%sp, -"+saveSize+", %sp\n");
         strBuild.append("\t!#PROLOGUE# 1");
         strBuild.append("\n" + getFullLabel() + "BODY:");
      }

      for (SparcInstruction instr : mSparcList) {
         strBuild.append("\n\t");
         strBuild.append(instr.toString());
      }

      if (mThen != null) {
         strBuild.append("\n");
         mThen.toSparc(strBuild);
      }
      if (mElse != null) {
         strBuild.append("\n");
         mElse.toSparc(strBuild);
      }

      strBuild.append("\n");
      return;
   }

   public Block getBlock() {
      if (mInstructionList.size() != 0 || mElse != null || mThen == null) {
         return this;
      } else {
         if (!mRemoveDeadPredecessor) {
            mRemoveDeadPredecessor = true;
            mThen.removePredecessor();
         }

         return mThen.getBlock();
      }
   }

   // Finds tail calls that are not of the form "return call();"
   public static void checkTailCalls() {
      if (!Block.TAIL_CALL) {
         return;
      }

      ArrayList<IlocInstruction> instrs;
      IlocInstruction ilocInstr;

      for (Block block : mRoster) {
         instrs = block.mInstructionList;

         // Tail call time.
         if (instrs.size() > 0 && (ilocInstr = instrs.get(instrs.size() - 1))
          instanceof CallInstr && (block.mThen == null || block.mThen.mExit)) {
            System.out.println("Tail: " + ((CallInstr)ilocInstr).getFunName());
            ((CallInstr)ilocInstr).setTail(true);
            block.setThen(null);
            block.setElse(null);

            for (int i = instrs.size() - 2; i >= 0; i --) {
               ilocInstr = instrs.get(i);

               if (ilocInstr instanceof StoreoutInstr) {
                  ((StoreoutInstr)ilocInstr).setTail(true);
               } else {
                  break;
               }
            }
         }
      }
   }

   public static void eliminateDeadBlocks() {
      for (Block block : mRoster) {
         if (block.mThen != null) {
            Block then = block.mThen.getBlock();

            if (then != block.mThen) {
               block.setThen(then);
            }
         }
         if (block.mElse != null) {
            Block elseBlock = block.mElse.getBlock();

            if (elseBlock != block.mElse) {
               block.setElse(elseBlock);
            }
         }
      }
   }

   public String toIloc() {
      // We need to branch and we don't know what register the condition
      // is in so we must branch based off the condition type
      if (mCondReg == null && mElse != null) {
         String label;
         Compare cond;

         // Looks wrong but we want to jump over the wrong block so we
         // usually reverse the condition. However if we're branching
         // backwards then we don't want to switch it.
         if (mBackwardsCondition) {
            label = mThen.getFullLabel();
            cond = mCondType;
         } else {
            label = mElse.getFullLabel();
            cond = mCondType.reverse();
         }

         mInstructionList.add(new BranchInstr(cond, label));
      } else if (mThen != null && mElse != null) {
         mInstructionList.add(new CompiInstr(mCondReg, CFG.TRUE_VAL));
         mInstructionList.add(new BranchInstr(Compare.EQ,
          mThen.getFullLabel()));
         mInstructionList.add(new BranchInstr(null, mElse.getFullLabel()));
      } else if (mThen != null && mThen.mNumPredecessors > 1) {
         mInstructionList.add(new JumpiInstr(mThen.getFullLabel()));
      }

      String temp = getFullLabel() + ":";

      for (IlocInstruction instr : mInstructionList)
         temp = temp + "\n\t" + instr.toIloc();

      return temp + "\n";
   }

   public String toString() {
      return getLabel() + mNumber;
   }
}
