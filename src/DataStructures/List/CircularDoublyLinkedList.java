package DataStructures.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularDoublyLinkedList<E> implements List<E> {

	private class Node {
		private E value;
		private Node next, prev;

		public Node(E value, Node next, Node prev) {
			this.value = value;
			this.next = next;
			this.prev = prev;
		}

		public Node(E value) {
			this(value, null, null); // Delegate to other constructor
		}

		public Node() {
			this(null, null, null); // Delegate to other constructor
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}

		public void clear() {
			value = null;
			next = prev = null;
		}				
	} // End of Node class


	private class ListIterator implements Iterator<E> {

		private Node nextNode;

		public ListIterator() {
			nextNode = header.getNext();
		}

		@Override
		public boolean hasNext() {
			return nextNode != trailer;
		}

		@Override
		public E next() {
			if (hasNext()) {
				E val = nextNode.getValue();
				nextNode = nextNode.getNext();
				return val;
			}
			else
				throw new NoSuchElementException();				
		}

	} // End of ListIterator class, DO NOT REMOVE, TEST WILL FAIL
	
	/* private fields */
	private Node header, trailer; // "dummy" nodes
	private int currentSize;


	public CircularDoublyLinkedList() {
		/**
		 * Set dummy nodes to point to each other
		 * 
		 * 		 --> header <--> trailer <--
		 * 		 |					       |
		 *       __________________________
		 * 
		 */
		header = new Node(null, trailer, trailer);
		trailer = new Node(null, header, header);
		currentSize = 0;
	}

	@Override
	public void add(E obj) {
		/*TODO ADD YOUR CODE HERE*/
		Node nextNode = this.trailer;
		Node prevNode = nextNode.getPrev();
		
		Node newNode = new Node(obj,null,null);
		
		newNode.setNext(nextNode);
		newNode.setPrev(prevNode);
		
		prevNode.setNext(newNode);
		nextNode.setPrev(newNode);
		
		currentSize++;
	}

	@Override
	public void add(int index,E elm) {
		/*TODO ADD YOUR CODE HERE*/
		Node prevNode = this.header;
		Node nextNode = prevNode.getNext();

		int counter = -1;
		while(counter < index) {
			prevNode = nextNode;
			nextNode = prevNode.getNext();
			counter++;
			
		}
		if(counter == index ) {
			Node newNode = new Node(elm,null,null);
			
			newNode.setNext(nextNode);
			newNode.setPrev(prevNode);
			
			prevNode.setNext(newNode);
			nextNode.setPrev(newNode);
		}
	}

	@Override
	public boolean remove(E obj) {
		/*TODO ADD YOUR CODE HERE*/
		Node prevNode = this.header;
		Node rmNode = prevNode.getNext();
		Node nextNode = rmNode.getNext();
		while (!rmNode.getValue().equals(obj)) {
			
		
			prevNode = rmNode;
			rmNode = nextNode;
			nextNode = rmNode.getNext();
		}
		if(rmNode.getValue().equals(obj)) {
			nextNode.setPrev(prevNode);
			prevNode.setNext(nextNode);
				
			rmNode.clear();
			return true;

				
			}
		
		return false;
	}

	@Override
	public boolean removePos(int index) {
		/*TODO ADD YOUR CODE HERE*/
		Node prevNode = this.header;
		Node rmNode = prevNode.getNext();
		Node nextNode = rmNode.getNext();
		int counter = 0;
		
		while (counter < index) {
			prevNode = rmNode;
			rmNode = nextNode;
			nextNode = rmNode.getNext();
		}
		if(counter == index) {
			nextNode.setPrev(prevNode);
			prevNode.setNext(nextNode);
				
			rmNode.clear();
			return true;

				
			}
		
		return false;
	}

	@Override
	public int removeAll(E obj) {
		/*TODO ADD YOUR CODE HERE*/
		Node prevNode = this.header;
		Node rmNode = prevNode.getNext();
		Node nextNode = rmNode.getNext();
		
		int rmTotal = 0;
		
		while (rmNode != this.trailer) {
			if(rmNode.getValue().equals(obj)) {
				nextNode.setPrev(prevNode);
				prevNode.setNext(nextNode);
					
				rmNode.clear();
				currentSize--;
				rmTotal++;
				
				rmNode = nextNode;
				nextNode = rmNode.getNext();
				}
			else {
			prevNode = rmNode;
			rmNode = nextNode;
			nextNode = rmNode.getNext();
			}
		}
		
		
		return rmTotal;
	}

	@Override
	public E get(int index) {
		/*TODO ADD YOUR CODE HERE*/
		Node curNode = this.header;
		Node nextNode = curNode.getNext();
		int counter = -1;
		while(counter < index) {
			curNode = nextNode;
			nextNode = curNode.getNext();
			counter++;
		}
		if(counter == index) {
			E result = curNode.getValue();
			return result;
		}

		return null;
	}

	@Override
	public E set(int index,E elm) {
		/*TODO ADD YOUR CODE HERE*/
		Node curNode = this.header;
		Node nextNode = curNode.getNext();
		int counter = -1;
		if(index <= -1 || index > size() )
		while(counter < index) {
			curNode = nextNode;
			nextNode = curNode.getNext();
			counter++;
		}
		if(counter == index) {
			curNode.setValue(elm);
			return curNode.getValue();
		}

		return null;
	}

	@Override
	public int firstIndex(E obj) {
		/*TODO ADD YOUR CODE HERE*/
		Node prevNode = this.header;
		Node nextNode = prevNode.getNext();
		int counter = 0;
		while(nextNode != trailer) {
			if(nextNode.getValue().equals(obj)) {
				return counter;
			}
			else {
				prevNode = nextNode;
				nextNode = prevNode.getNext();
				counter++;
			}
		}
		return -1;
	}

	@Override
	public int lastIndex(E obj) {
		/*TODO ADD YOUR CODE HERE*/
		Node prevNode = this.trailer;
		Node nextNode = prevNode.getPrev();
		int counter = 0;
		while(nextNode != header) {
			if(nextNode.getValue().equals(obj)) {
				return counter;
			}
			else {
				prevNode = nextNode;
				nextNode = prevNode.getPrev();
				counter++;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		/*TODO ADD YOUR CODE HERE*/
		return currentSize;
	}

	@Override
	public boolean isEmpty() {
		/*TODO ADD YOUR CODE HERE*/
		return size() == 0;
	}

	@Override
	public boolean contains(E obj) {
		/*TODO ADD YOUR CODE HERE*/
		Node prevNode = this.header;
		Node nextNode = prevNode.getNext();
		while(nextNode != trailer){
			if(nextNode.getValue().equals(obj)) {
				return true;
			}
			else {
				prevNode = nextNode;
				nextNode = prevNode.getNext();
			}
		}
		return false;
	}

	@Override
	public void clear() {
		/*TODO ADD YOUR CODE HERE*/
		while(size() > 0) {
			removePos(0);
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	} //DO NOT DELETE, TESTS WILL FAIL

}
