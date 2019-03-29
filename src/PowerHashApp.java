import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PowerHashApp {
	public static HashTable table;
	
	
	public static void build(String file) {
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
				table.insert(temp); 
				count++;
				
			}
	
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	
	

	public static void main(String[] args) {
		boolean isPrime = false;
		int x =0;
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
		
		}
		
		
		System.out.println("Enter input data file (to be hashed):");
		keyboard  = new Scanner(System.in);
		String file = keyboard.nextLine();
		build(file);
		table.printTable();

	}

}
