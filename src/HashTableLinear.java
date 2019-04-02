
public class HashTableLinear extends HashTable{


	public HashTableLinear(int tablesize) {
		super(tablesize);
		
		
	}
	
	public void insert(Item x) {
		int probe = 0;
		if(amount==table.length) {
			System.out.println("Linear Insertion Failed: Table is full");
			System.exit(0);
			return;
		}
		int h = hash(x.getDate());
		while(table[h]!=null) {
			probe++;
			
			h=(h+1)%getTablesize();
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
			
			if(table[h].toString().substring(0, 19).equals(key)) {
				return table[h].toString();
			}
			searchProbe++;
			h=(h+1)%getTablesize();
		}
		
		
	return "Not Found";
	}
	

}
