import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PowerHashApp {
	public static HashTable table;
	public static HashTableChaining tableChaining;
	public static ArrayList<String> searchKeysUnshuffled = new ArrayList<String>();
	public static String[] searchKeys;
	public static int probesSearch[];
	
	
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
	
	public static void makeShuffledArray(int size) {
		Collections.shuffle(searchKeysUnshuffled);
		searchKeys = new String[size];
		for(int i = 0;i<size;i++) {
			searchKeys[i]=searchKeysUnshuffled.get(i);
		}
		probesSearch = new int[size];
	}
	
	
	
	
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
	
	public static int totalSearchProbes() {
		int sum = 0;
		for(int i =0;i<probesSearch.length;i++) {
			sum+=probesSearch[i];
		}
		return sum;
	}
	
	public static double getAveProbes(int keys) {
		return (double)totalSearchProbes()/keys;
	}
	
	public static int getMaxProbes() {
		int max = 0;
		for(int i = 0;i<probesSearch.length;i++) {
			if(probesSearch[i]>max) {
				max = probesSearch[i];
			}
		}
		
		
		return max;
	}
	

	public static void main(String[] args) {
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
		System.out.println("Total number of probes generated over "+keys+" searches:\t"+totalSearchProbes());
		System.out.println("Average number of probes per search:\t"+getAveProbes(keys));
		System.out.println("The maximum number of probes over the set of K searches:\t"+getMaxProbes());
	}

}
