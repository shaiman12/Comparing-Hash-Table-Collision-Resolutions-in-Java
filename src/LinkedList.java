import java.util.NoSuchElementException;

public class LinkedList {
	
	//Node Class
	private class Node{
		private Item data;
		private Node link;
		
		public Node() {
			data = null;
			link = null;
		}
		
		public Node(Item data, Node link) {
			this.data= data;
			this.link = link;
		}

		public Item getData() {
			return data;
		}

		public void setData(Item data) {
			this.data = data;
		}

		public Node getLink() {
			return link;
		}

		public void setLink(Node link) {
			this.link = link;
		}
		
		
		
	}
	
	private Node head;
	private int probes = 0;
	public class ListIterator{
		private Node position;
		private Node previous;
		
		public ListIterator() {
			position = head;
			previous = null;
			
		}
		
		
		
		public void restart() {
			position = head;
			previous = null;
		}
		
		
		
		public boolean hasNext() {
			return (position!=null);
		}
		
		
		
		public String next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			String toReturn = position.data.toString();
			previous = position;
			position  = position.link;

			return toReturn;
		}
		
		public String peek() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return position.data.toString();
		}
		
		public void addHere(Item data) {
			if(position == null && previous!=null) {
				previous.link = new Node(data,null);
			}
			else if (position==null||previous==null) {
				addToStart(data);
			}
			else {
				Node temp = new Node(data, position);
				previous.link = temp;
				previous = temp;
			}
			
			
		}
		
		public void Delete() {
			if(position==null) {
				throw new IllegalStateException();
			}else if (previous==null) {
				head = head.link;
				position = head;
			}else {
				previous.link = position.link;
				position = position.link;
				
			}
			
			
			
		}
		
		
		
	}
	
	public ListIterator iterator() {
		return new ListIterator();
	}
	
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
	
	
	public void clear() {
		head = null;
	}
	
	public boolean isEmpty() {
		if(head==null) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public int size () {
		Node pos = head;
		int count  = 0;
		while(pos!=null) {
			count++;
			pos=pos.link;
		}
	
	return count;
	}
	
	public LinkedList() {
		head = null;
	}
	
	public void outputList() {
		Node pos = head;
		while(pos!=null) {
			System.out.print(pos.data.getDate()+"\t");
			pos=pos.link;
		}
	}
	
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

		public int getProbes() {
			return probes;
		}

}
