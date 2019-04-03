import java.util.ArrayList;
/**
 * This class is used to set the foundations for a hash table that uses chaining as its collision resolution. 
 * It has methods to hash a value using a relatively simple hashing sequence
 * The table size is stored and there are accessor and mutators for this
 * The class stores and fixes the load factor of any hash table
 * It has methods for searching and inserting into the table using the criteria for quadratic probing
 * It stores a table of LinkedList objects that I have defined to my own criteria in the LinkedList class
 * @author ShaiAarons
 *
 */

public class HashTableChaining{
	private int tablesize;
	protected LinkedList[] table;
	protected int amount;
	protected  double loadfactor;
	protected int[] probesInsert;
	protected int[] probesSearch;
	protected int searchProbes;
	/**
	 * Constructor to create a basic hashtable.
	 * Sets all values to 0 and will create the table
	 * @param tablesize this is the size of the hash table that the user wishes to create
	 */
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
	 * Method for inserting into a hash table
	 * Uses chaining as its collision resolution. If there are items that collide, the new item will be added to the head of the linked list
	 * Ensures that there are no duplicates
	 * Counts the amounts of probes for a particular insertion and stores them in the probesInsert array
	 * Calls the fix load factor method to update the load factor
	 * @param x item that is being inserted
	 */
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
	/**
	 * Method for searching the hash table
	 * Uses the chaining collision resolution protocol to find a specific power usage item
	 * @param key the key that the user is searching for
	 */
	public String find(String key) {
		
		int h = hash(key);
		String output = table[h].find(key);
		searchProbes=table[h].getProbes();
		return output;
		
	}
	/**
	 * updates the load factor by using the formula:
	 * (amount of occupied indices in the table)/(table size)
	 */
	public void fixLoadFactor() {
		
			loadfactor=amount/(double)tablesize;
		
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
	
	/**
	 * Prints out the entire hash table in the neat format to show the user how chaining was achieved
	 */
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
