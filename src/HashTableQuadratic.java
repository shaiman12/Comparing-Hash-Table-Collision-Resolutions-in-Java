
public class HashTableQuadratic extends HashTable{

	public HashTableQuadratic(int tablesize) {
		super(tablesize);
		
	}
	
	public void insert(Item x) {
		int probe = 0;
		if(amount==table.length) {
			System.out.println("Quadratic Insertion Failed: Table is full");
			System.exit(0);
		}
		int h = hash(x.getDate());
		while(table[h]!=null) {
			probe++;
			if(probe>getTablesize()) {
				System.out.println("Quadratic Probe Failure: Number of probes exceed table size");
				System.exit(0);
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
	
		searchProbe = 0;
		int h = hash(key);
		while(table[h]!=null) {
			if(table[h].getDate().equalsIgnoreCase(key)) {
				
				return table[h].toString();
			}
			searchProbe++;
			h=(h<<1)%getTablesize();
		}
	
	return "Not Found";
	}
	
	

}
