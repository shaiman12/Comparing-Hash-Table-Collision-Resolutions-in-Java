import java.util.ArrayList;


public class HashTableChaining{
	private int tablesize;
	protected LinkedList[] table;
	protected int amount;
	protected int loadfactor;
	protected int[] probesInsert;
	protected int[] probesSearch;
	private int searchesPerformed;
	
	public HashTableChaining(int tablesize) {
		this.setTablesize(tablesize);
		table = new LinkedList[tablesize];
		for(int i = 0;i<tablesize;i++) {
			table[i] = new LinkedList();
		}
		probesInsert = new int[500];
		probesSearch = new int[500];
		amount = 0;
		loadfactor =0;
		searchesPerformed = 0;
		
		
	}
	
	
	public int hash(String key) {
		int hashVal = 0;
		for(int i =0;i<key.length();i++) {
			hashVal = (37*hashVal)+key.charAt(i);
		}
		hashVal%=getTablesize();
		if(hashVal<0) {
			hashVal+=getTablesize();
		}
		return hashVal;
	}
	
	
	
	public int getTablesize() {
		return tablesize;
	}

	public void setTablesize(int tablesize) {
		this.tablesize = tablesize;
	}
	
	public void insert(Item x) {
		int probe = 0;
		int h = hash(x.getDate());
		
		table[h].addToStart(x);
		amount++;
		probesInsert[amount-1] = table[h].getProbes();
		fixLoadFactor();
		
		
		
	}
	
	public String find(String key) {
		
		int h = hash(key);
		String output = table[h].find(key);
		probesSearch[searchesPerformed] = table[h].getProbes();
		searchesPerformed++;
		return output;
		
	}
	
	public void fixLoadFactor() {
		if(amount!=0) {
			loadfactor=amount/tablesize;
		}
	}
	
	
}
