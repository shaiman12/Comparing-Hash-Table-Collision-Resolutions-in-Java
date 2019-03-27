
public class HashTableLinear extends HashTable{

	public HashTableLinear(int tablesize) {
		super(tablesize);
		
	}
	
	public void insert(Item x) {
		int h = hash(x.getDate());
		while(table[h]!=null) {
			h=(h+1)%getTablesize();
		}
		table[h] = x;
		
		
		
	}
	
	public String find(String key) {
		int h = hash(key);
		while(table[h]!=null) {
			if(table[h].getDate().equalsIgnoreCase(key)) {
				return table[h].toString();
			}
			h=(h+1)%getTablesize();
		}
	return "Not Found";
	}
	

}
