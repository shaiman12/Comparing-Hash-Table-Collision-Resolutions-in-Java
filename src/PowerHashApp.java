import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * This is the main class that will run procedures regarding interacting with various types of hash table
 * The user will choose which collision resolution they wish to use, the size of the table (Which will be checked by this program if it is a prime number),
 * the data file from which input will be recieved, and the amount of random keys the user wishes to search for
 * It has instance variables to store a hash table (which uses linear or quadratic probing) and a hash table which uses chaining
 * It has instance variables for storing a list of all the date/time keys and an array that randomizes those keys to a specific size of an array as specified by the user
 * @author ShaiAarons
 * @author https://beginnersbook.com/2014/01/java-program-to-check-prime-number/ for the prime number checker
 *
 */
public class PowerHashApp {
	public static HashTable table;
	public static HashTableChaining tableChaining;
	public static ArrayList<String> searchKeysUnshuffled = new ArrayList<String>();
	public static String[] searchKeys;
	public static int probesSearch[];
	
	/**
	 * This method builds the hash table to the requirements as specified by the user
	 * @param file the file with the input data
	 * @param choice a value to represent if chaining is used as the collision resolution or not
	 */
	public static void build(String file, int choice) {
		try {
			Scanner scan = new Scanner(new File(file));
			scan.nextLine();
			int count = 0;
			
			while(count<500) {
				
				Scanner line = new Scanner(scan.nextLine()).useDelimiter(",");
				String date = line.next();
				double power = line.nextDouble();
				line.next();
				double voltage = line.nextDouble();
				Item temp = new Item(date,power,voltage);
				if(choice == 0) {
				table.insert(temp); 
				}
				else {
					tableChaining.insert(temp);
				}
				
				
				
				count++;
				
			}
	
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads in "FileWithSearchKeys.txt" to create an array that stores all the keys from the "cleaned_data.csv" file
	 */
	public static void makeArray() {
		try {
			Scanner scan = new Scanner(new File("FileWithSearchKeys.txt"));
			int i = 0;
			while(scan.hasNext()) {
				searchKeysUnshuffled.add(scan.nextLine());
				
				i++;
			}
		
		
		
		
		
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This creates a subset of the original array whereby the keys are in a random sequence and the size of this array is specified by the user
	 * @param size The amount of keys the user wishes for the program to search for
	 */
	public static void makeShuffledArray(int size) {
		Collections.shuffle(searchKeysUnshuffled);
		searchKeys = new String[size];
		for(int i = 0;i<size;i++) {
			searchKeys[i]=searchKeysUnshuffled.get(i);
		}
		probesSearch = new int[size];
	}
	
	
	
	/**
	 * Checks if a number is a prime number
	 * @param x the number being checked
	 * @return true if it is prime, false if not prime
	 */
	public static boolean checkPrime(int x) {
		boolean isPrime = true;
		if(x==1) {
			return false;
		}
		int i = 2;
		while(i<=x/2) {
			if(x%i==0) {
				isPrime = false;
				break;
			}
			i++;
		}
		
		return isPrime;
	}
	/**
	 * This is the method that runs the sequence of searches and records the probe count for each search. 
	 * It stores the probeCount for each search in an array called probeSearch
	 * @param chaining an integer value which determines if chaining is the chosen collision resolution
	 */
	public static void searchSequence(int chaining) {

		if(chaining==0) {
			
		for(int i = 0;i<searchKeys.length;i++) {
			table.find(searchKeys[i]);
			probesSearch[i]=table.searchProbe;
		}}else {
			
			for(int i = 0;i<searchKeys.length;i++) {
				tableChaining.find(searchKeys[i]);
				probesSearch[i]=tableChaining.searchProbes;}
		}
		
	}
	/**
	 * Returns the total amount of probes over all searches
	 * @return the sum of probes over all searches
	 */
	public static int totalSearchProbes() {
		int sum = 0;
		for(int i =0;i<probesSearch.length;i++) {
			sum+=probesSearch[i];
		}
		return sum;
	}
	/**
	 * Gets the average amount of probes per search
	 * @param keys the amount of searches that have taken place
	 * @return the average amount of probes per search
	 */
	public static double getAveProbes(int keys) {
		return (double)totalSearchProbes()/keys;
	}
	/**
	 * Gets the instance whereby there was the maximum amount of probes over all the searches
	 * @return the highest amount of probes over all searches
	 */
	public static int getMaxProbes() {
		int max = 0;
		for(int i = 0;i<probesSearch.length;i++) {
			if(probesSearch[i]>max) {
				max = probesSearch[i];
			}
		}
		
		
		return max;
	}
	/**
	 * This writes to a specified file the information recorded by the program, as recieved as parameters:
	 * @param chaining an integer value determining if chaining has been used as the collision resolution or not
	 * @param loadfactor the load factor of a hash table
	 * @param probeCount the total amount of probes over all inserts
	 * @param searchProbes the total amount of probes over all searches
	 * @param average the average amount of probes over all searches
	 * @param max the highest amount of probes over all searches
	 */
	public static void writer(int chaining, double loadfactor, int probeCount, int searchProbes, double average, int max) {
		FileWriter writer;
		try {
			writer = new FileWriter("InsertionPerformanceChaining.csv", true);
			BufferedWriter bw = new BufferedWriter(writer);
			PrintWriter pw = new PrintWriter(bw);
			if(chaining==0) {
			pw.println(table.getTablesize()+","+loadfactor+","+probeCount+","+searchProbes+","+average+","+max);}
			else {
				pw.println(tableChaining.getTablesize()+","+loadfactor+","+probeCount+","+searchProbes+","+average+","+max);
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
/**
 * Main class that runs the sequence
 * @param args
 * @throws IOException
 */
	public static void main(String[] args) throws IOException {
		boolean isPrime = false;
		int x =0;
		int chaining = 0;
		makeArray();
		while(isPrime==false) {
		System.out.println("Input table size (must be a prime number):");
		Scanner keyboard = new Scanner(System.in);
		x =  Integer.parseInt(keyboard.nextLine());
		if(checkPrime(x)==false) {
			System.out.println("Not a prime number");
		}else {
			isPrime = true;
		}
		}
		System.out.println("Choose your resolution scheme (input corresponding number):\n"
							+ "1) Linear Probing\n"
							+ "2) Quadratic Probing\n"
							+ "3) Chaining");
		
		Scanner keyboard = new Scanner(System.in);
		int choice = keyboard.nextInt();
		switch(choice) {
		case 1:
			table = new HashTableLinear(x);
		break;
		case 2:
			table = new HashTableQuadratic(x);
		break;
		case 3:
			tableChaining = new HashTableChaining(x);
			chaining = 1;
			break;
		
		}
		
	
		
		
		System.out.println("Enter input data file (to be hashed):");
		keyboard  = new Scanner(System.in);
		String file = keyboard.nextLine();
		int keys = 0;
		System.out.println("Enter the number of keys (K) to be searched for in the table:");
		keyboard  = new Scanner(System.in);
		keys = keyboard.nextInt();
		
		
		build(file, chaining);
		
		
		
		makeShuffledArray(keys);
		
		searchSequence(chaining);
		
		
		
		
		if(chaining==0) {
			System.out.println("Load Factor:\t"+table.loadfactor);
			System.out.println("Total amount of probes required for all insertions:\t"+table.totalInsertProbes());
			
			
			
		}else {
			System.out.println("Load Factor:\t"+tableChaining.loadfactor);
			System.out.println("Total amount of probes required for all insertions:\t"+tableChaining.totalInsertProbes());
			
		}
		
		
		
		
		if(keys!=0) {
		System.out.println("Total number of probes generated over "+keys+" searches:\t"+totalSearchProbes());
		System.out.println("Average number of probes per search:\t"+getAveProbes(keys));
		System.out.println("The maximum number of probes over the set of K searches:\t"+getMaxProbes());
		if(chaining==0) {
			writer(chaining, table.loadfactor, table.totalInsertProbes(), totalSearchProbes(), getAveProbes(keys),getMaxProbes());
		}else {
			writer(chaining, tableChaining.loadfactor, tableChaining.totalInsertProbes(), totalSearchProbes(), getAveProbes(keys),getMaxProbes());
		}
	}}

}
