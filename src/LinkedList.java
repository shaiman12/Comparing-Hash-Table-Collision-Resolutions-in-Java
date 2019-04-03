import java.util.NoSuchElementException;
/**
 * This is a class that stores a linked list.
 * It has a private inner class to store a node in the linked list
 * There are many methods that are used for linked lists
 * This was based off the lecture slides from first year computer science CSC1016S course at UCT
 * Creates an instance variable that stores the head node
 * Stores an instance variable called probe that counts the probes per insert or search and initially sets it to zero
 * @author ShaiAarons
 *
 */
public class LinkedList {
	
	/**
	 * This is the node inner class that stores a node that is part of the linked list.
	 * It has an instance variable that stores the power usage object and a link to the next node in the linked list
	 * @author ShaiAarons
	 *
	 */
	private class Node{
		private Item data;
		private Node link;
		/**
		 * No-arguments constructor that sets the link and data to null
		 */
		public Node() {
			data = null;
			link = null;
		}
		/**
		 * constructor that creates a node and instantiates the instance variables
		 * @param data the power usage object that is being stored
		 * @param link the link to the next node in the linked list
		 */
		public Node(Item data, Node link) {
			this.data= data;
			this.link = link;
		}
/**
 * 
 * @return the power usage object
 */
		public Item getData() {
			return data;
		}
/**
 * sets the power usage object
 * @param data the object that is being set
 */
		public void setData(Item data) {
			this.data = data;
		}
/**
 * 
 * @return the link to the next node
 */
		public Node getLink() {
			return link;
		}
/**
 * Change the link to the next node
 * @param link link to be updated
 */
		public void setLink(Node link) {
			this.link = link;
		}
		
		
		
	}
	
	private Node head;
	private int probes = 0;
	
	
	/**
	 * This method adds a node to the start of the linked list
	 * It ensures there are no duplicates
	 * The probe count is updated
	 * @param data power usage item to be stored in the linked list
	 */
	public void addToStart(Item data) {
		boolean exists = false;
		Node position = head;
		while(position!=null) {
			probes++;
			if(position.data.equals(data)) {
				System.out.println("Cannot have duplicates");
				return;
			}
			position=position.link;
		}
		head = new Node(data, head);
	}
	
	/**
	 * clears the linked list
	 */
	public void clear() {
		head = null;
	}
	/**
	 * Checks if a linked list is empty
	 * @return boolean value stating true if empty and false if not empty
	 */
	public boolean isEmpty() {
		if(head==null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Returns the size of the linked list
	 * @return the size of the linked list
	 */
	public int size () {
		Node pos = head;
		int count  = 0;
		while(pos!=null) {
			count++;
			pos=pos.link;
		}
	
	return count;
	}
	/**
	 * constructor that sets head equal to null
	 */
	public LinkedList() {
		head = null;
	}
	/**
	 * Outputs all the date/time values in the linked list
	 */
	public void outputList() {
		Node pos = head;
		while(pos!=null) {
			System.out.print(pos.data.getDate()+"\t");
			pos=pos.link;
		}
	}
	/**
	 * Method for searching the linked list for a specific key
	 * Traverses the linked list and returns the date/time value the user is searching for
	 * Initially sets probes equal to zero and then updates the probe count for every probe required
	 * @param key the key that the user is searching for
	 */
	public String find(String x) {
		probes = 0;
		Node position = head;
		String itemAtPos;
		while(position!=null) {
			probes++;
			itemAtPos=position.getData().getDate();
			if(itemAtPos.equalsIgnoreCase(x)) {
				return position.getData().toString();
			}
			position=position.link;
		}
		return  "Item not found";
	}
/**
 * Returns the total probes for any search or insert operation
 * @return the total amount of probes 
 */
		public int getProbes() {
			return probes;
		}

}
