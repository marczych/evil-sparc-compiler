import java.util.Hashtable;
import java.util.ArrayList;

public class StructTypes {
	private Hashtable<String, Hashtable<String, EVILType>> mHash;

	public StructTypes() {
		mHash = new Hashtable<String, Hashtable<String, EVILType>>();
	}

	public void addStruct(String name, Hashtable<String, EVILType> structTypes) {
		mHash.put(name, structTypes);
	}

	public Hashtable<String, EVILType> findStruct(String name) {
		return mHash.get(name);
	}

	public ArrayList<String> getFields(String name) {
		Hashtable<String, EVILType> table = mHash.get(name);
		Object[] ar = table.keySet().toArray();
		ArrayList<String> ret = new ArrayList<String>();
		for(Object o : ar)
				  ret.add(o.toString());

		return ret;
	}

	public String toString() {
		return mHash.toString();
	}
}
