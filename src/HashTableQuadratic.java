/**
 * This is a class for a hash table that uses quadratic probing as its collision resolution
 * It is a child class of the HashTable class
 * It has methods for searching and inserting into the table using the criteria for quadratic probing
 * @author ShaiAarons
 *
 */
public class HashTableQuadratic extends HashTable{
	/**
	 * Constructor for this class
	 * @param tablesize sets the size of the hash table the user wishes to create
	 */
	public HashTableQuadratic(int tablesize) {
		super(tablesize);
		
	}
	/**
	 * Method for inserting into a hash table
	 * Checks that the table is not full and if it is it will exit the program
	 * Uses quadratic probing if there are any insertion issues and ensures that the amount of probes never exceed the table size
	 * Uses a left bit shift to optimize the quadratic probing sequence
	 * Counts the amounts of probes for a particular insertion and stores them in the probesInsert array as defined in the HashTable class
	 * Calls the fix load factor method to update the load factor
	 * @param x item that is being inserted
	 */
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
	
	/**
	 * Method for searching the hash table
	 * Uses quadratic probing to find a specific power usage item
	 * @param key the key that the user is searching for
	 */
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
