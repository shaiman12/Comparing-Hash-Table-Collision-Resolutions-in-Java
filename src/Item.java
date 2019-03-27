/**
 * 
 *
 * This is a class used to store a data item of the time series of power usage for a suburban dwelling in an array of objects
 * @author Shai Aarons
 */
public class Item implements Comparable<Item>{
	
	private String date;
	private double power;
	private double voltage;
	
	
	
	/**
	 * 
	 * @param date used to store the date recorded
	 * @param power used to store the global active power
	 * @param voltage used to store the voltage used by the person
	 */
	public Item(String date, double power, double voltage) {
		this.date = date;
		this.power = power;
		this.voltage = voltage;
	}
	
	/**
	 * Used as a no-args constructor
	 */
	public Item() {
		date = null;
		power = 0;
		voltage = 0;
	}

	/**
	 * Returns the date/time String
	 */
	public String getDate() {
		return date;
	}

	/**
	 * sets the value for date/time
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Returns the power usage as a double
	 */
	public double getPower() {
		return power;
	}

	/**
	 * sets the value for power
	 */
	public void setPower(double power) {
		this.power = power;
	}
	
	/**
	 * Returns the voltage as a double
	 */
	public double getVoltage() {
		return voltage;
	}
	
	/**
	 * sets the value for voltage
	 */
	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}
	
	
	/**
	 * The toString method returns the date/time, power and voltage of a specific object in a neatly organized string. 
	 */
	public String toString() {
		return getDate()+"\t"+getPower()+"\t"+getVoltage();
	}
	/**
	 * This is a compare to method that compares the date/time string of two items
	 * @return integer that stores -1,0,1 in the typical compareTo fashion
	 */
	
	public int compareTo(Item x) {
		if (x==null) {
			throw new IllegalStateException();
		
		}
		else {
			return x.getDate().compareTo(this.getDate());
		}
	}
	

}
