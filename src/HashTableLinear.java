/**
 * This is a class for a hash table that uses linear probing as its collision resolution
 * It is a child class of the HashTable class
 * It has methods for searching and inserting into the table using the criteria for linear probing
 * @author ShaiAarons
 *
 */
public class HashTableLinear extends HashTable{

/**
 * Constructor for this class
 * @param tablesize sets the size of the hash table the user wishes to create
 */
	public HashTableLinear(int tablesize) {
		super(tablesize);
		
		
	}
	
	
	/**
	 * Method for inserting into a hash table
	 * Checks that the table is not full and if it is it will exit the program
	 * Uses linear probing if there are any insertion issues
	 * Counts the amounts of probes for a particular insertion and stores them in the probesInsert array as defined in the HashTable class
	 * Calls the fix load factor method to update the load factor
	 * @param x item that is being inserted
	 */
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
	/**
	 * Method for searching the hash table
	 * Uses linear probing to find a specific power usage item
	 * @param key the key that the user is searching for
	 */
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
