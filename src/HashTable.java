/**
 * This class is used to set the foundations for a hash table. 
 * It has methods to hash a value using a relatively simple hashing sequence
 * The table size is stored and there are accessor and mutators for this
 * The class stores and fixes the load factor of any hash table
 * This is an abstract class as the methods for inserting and searching will be defined in the child classes
 * @author ShaiAarons
 *
 */
public abstract class HashTable {
	private int tablesize;
	protected Item[] table;
	protected int amount;
	protected double loadfactor;
	protected int[] probesInsert;
	protected int searchProbe;
	protected int searchesPerformed;
	/**
	 * Constructor to create a basic hashtable.
	 * Sets all values to 0 and will create the table
	 * @param tablesize this is the size of the hash table that the user wishes to create
	 */
	public HashTable(int tablesize) {
		this.setTablesize(tablesize);
		table = new Item[tablesize];
		probesInsert = new int[500];
		searchProbe = 0;
		amount = 0;
		loadfactor =0;
		searchesPerformed = 0;
	}
	/**
	 * Simple hash function that converts strings into a hash value by shifting individual characters by 37
	 * @param key item to be hashed
	 * @return the hash value after the string has been hashed
	 */
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
/**
 * Returns table size
 * @return size of the table
 */
	public int getTablesize() {
		return tablesize;
	}
/**
 * Sets the size of the table
 * @param tablesize the size of the table the user wishes to create
 */
	public void setTablesize(int tablesize) {
		this.tablesize = tablesize;
	}
	/**
	 * updates the load factor by using the formula:
	 * (amount of occupied indices in the table)/(table size)
	 */
	public void fixLoadFactor() {
		if(amount!=0) {
			loadfactor=amount/(double)tablesize;
		}
	}
	/**
	 * Abstract method for inserting an item into a hash table
	 * @param x power usgae item to be inserted
	 */
	public abstract void insert(Item x);
	
	/**
	 * Abstract method to find an item in a hash table
	 * @param key date/time string to be searched for
	 * @return the toString of the power usage item
	 */
	public abstract String find(String key);

	/**
	 * Prints out the entire hash table
	 */
	public void printTable() {
		for(int i = 0;i<tablesize;i++) {
			if(table[i]!=null) {
			System.out.println(table[i].getDate());}
			else {
				System.out.println("Null");
			}
		}
	}
	/**
	 * 
	 * @return The total amount of probes over all insertions
	 */
	public int totalInsertProbes() {
		int sum  =0;
		for(int i = 0;i<probesInsert.length;i++) {
			sum+=probesInsert[i];
		}
		return sum;
	}
	
	
	
	

}
