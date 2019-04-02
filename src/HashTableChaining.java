import java.util.ArrayList;


public class HashTableChaining{
	private int tablesize;
	protected LinkedList[] table;
	protected int amount;
	protected  double loadfactor;
	protected int[] probesInsert;
	protected int[] probesSearch;
	protected int searchProbes;
	
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
		int h = hash(x.getDate());
		if(!table[h].find(x.getDate()).equals("Item not found")) {
			return;
		}
		
		table[h].addToStart(x);
		amount++;
		probesInsert[amount-1] = table[h].getProbes();
		fixLoadFactor();
		
		
		
	}
	
	public String find(String key) {
		
		int h = hash(key);
		String output = table[h].find(key);
		searchProbes=table[h].getProbes();
		return output;
		
	}
	
	public void fixLoadFactor() {
		
			loadfactor=amount/(double)tablesize;
		
	}
	
	public int totalInsertProbes() {
		int sum  =0;
		for(int i = 0;i<probesInsert.length;i++) {
			sum+=probesInsert[i];
		}
		return sum;
	}
	
	
	public void printTable() {
		for(int i = 0;i<tablesize;i++) {
			if(table[i].isEmpty()==false) {
			table[i].outputList();
			System.out.println("");}
			else {
				System.out.println("Null");
			}
		}
	}
	
}
