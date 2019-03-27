
public class HashTable {
	private int tablesize;
	protected Item[] table;
	private int amount;
	private int loadfactor;
	public HashTable(int tablesize) {
		this.setTablesize(tablesize);
		table = new Item[tablesize];
		amount = 0;
		loadfactor =0;
	}
	
	public int hash(String key) {
		int hashVal = 0;
		for(int i =0;i<key.length();i++) {
			hashVal = (37*hashVal)+key.charAt(i);
		}
		return hashVal%getTablesize();
	}

	public int getTablesize() {
		return tablesize;
	}

	public void setTablesize(int tablesize) {
		this.tablesize = tablesize;
	}
	


	
	
	
	
	
	

}
