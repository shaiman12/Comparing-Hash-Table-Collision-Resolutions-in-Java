
public class HashTableQuadratic extends HashTable{
	private int searchesPerformed;
	public HashTableQuadratic(int tablesize) {
		super(tablesize);
		searchesPerformed = 0;
	}
	
	public void insert(Item x) {
		int probe = 0;
		if(amount==table.length) {
			System.out.println("Quadratic Insertion Failed: Table is full");
			return;
		}
		int h = hash(x.getDate());
		while(table[h]!=null) {
			probe++;
			if(probe>getTablesize()) {
				System.out.println("Quadratic Probe Failure: Number of probes exceed table size");
				return ;
			}
			h = (h<<1)%getTablesize();
		}
		table[h] = x;
		amount++;
		probesInsert[amount-1] = probe;
		fixLoadFactor();
	}
	
	
	public String find(String key) {
		int probe = 0;
		int h = hash(key);
		while(table[h]!=null) {
			if(table[h].getDate().equalsIgnoreCase(key)) {
				probesSearch[searchesPerformed] = probe;
				searchesPerformed++;
				return table[h].toString();
			}
			probe++;
			h=(h<<1)%getTablesize();
		}
		
		searchesPerformed++;
	return "Not Found";
	}
	
	

}
