
public abstract class HashTable {
	private int tablesize;
	protected Item[] table;
	protected int amount;
	protected double loadfactor;
	protected int[] probesInsert;
	protected int searchProbe;
	protected int searchesPerformed;
	public HashTable(int tablesize) {
		this.setTablesize(tablesize);
		table = new Item[tablesize];
		probesInsert = new int[500];
		searchProbe = 0;
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
	
	public void fixLoadFactor() {
		if(amount!=0) {
			loadfactor=amount/(double)tablesize;
		}
	}
	
	public abstract void insert(Item x);
	public abstract String find(String key);

	
	public void printTable() {
		for(int i = 0;i<tablesize;i++) {
			if(table[i]!=null) {
			System.out.println(table[i].getDate());}
			else {
				System.out.println("Null");
			}
		}
	}
	
	public int totalInsertProbes() {
		int sum  =0;
		for(int i = 0;i<probesInsert.length;i++) {
			sum+=probesInsert[i];
		}
		return sum;
	}
	
	
	
	

}
