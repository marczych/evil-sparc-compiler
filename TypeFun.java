import java.util.Hashtable;
import java.util.ArrayList;

public class TypeFun implements EVILType {
		  protected Hashtable<String, EVILType> mParams;
		  protected ArrayList<EVILType> mOrderedParams;
		  protected Hashtable<String, EVILType> mLocals;
		  protected EVILType mReturn;
		  protected int ifCount = 0;
		  protected int returnCount = 0;
		  protected boolean hasReturned = false;


		  public TypeFun() {
					mParams = new Hashtable<String, EVILType>();
					mLocals = new Hashtable<String, EVILType>();
					mOrderedParams = new ArrayList<EVILType>();
		  }

		  public boolean isSameType(EVILType other) {
					 return other instanceof TypeFun;
		  }

	public boolean checkArgs(ArrayList<EVILType> args) {
		if(args.size() != mOrderedParams.size())
				  return false;

		for(int i = 0; i < mOrderedParams.size(); i++) {
				if(!mOrderedParams.get(i).isSameType(args.get(i)))
				  return false;
		}

		return true;
	}

	public void setReturn() { hasReturned = true; }

	public void incrementIf() { ifCount++; }
	public void incrementReturns() { returnCount++; }

	public boolean isParam(String var) {
		return mParams.get(var) != null;
	}

	public boolean addParam(String varName, EVILType type) {
		EVILType ret = mParams.get(varName);
		if(ret != null)
				  return false;

		mParams.put(varName, type);

		mOrderedParams.add(type);
		return true;
	}

	public void setReturnType(EVILType type) { mReturn = type; }

	public EVILType getReturnType() { return mReturn; }

	public boolean addLocal(String varName, EVILType type) {
		EVILType ret = mParams.get(varName);
		if(ret != null)
				  return false;

		ret = mLocals.get(varName);
		if(ret != null)
				  return false;

		mLocals.put(varName, type);
		return true;
	}

	public EVILType getVar(String varName) {
		EVILType ret = mParams.get(varName);
		if(ret != null)
				  return ret;

		return mLocals.get(varName);
	}


	public String toString() { return "mReturn type: "+mReturn+" mParams: "+mParams.toString() 
			  + " mOrderedParams: "+mOrderedParams.toString()+" mLocals: "
						 + mLocals.toString(); }
}
