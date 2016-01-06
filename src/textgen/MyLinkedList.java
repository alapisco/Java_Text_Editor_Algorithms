package textgen;
 
import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E>
 *            The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {

		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;

	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element
	 *            The element to add
	 */
	public boolean add(E element) {

		try {

			add(size, element);
			return true;
		} catch (Exception e) {

			return false;

		}

		/*
		 * 
		 * LLNode<E> newNode = new LLNode<E>(element); LLNode<E> lastNode =
		 * head;
		 * 
		 * for (int i = 0; i < size; i++) {
		 * 
		 * lastNode = lastNode.next; }
		 * 
		 * lastNode.next = newNode; newNode.prev = lastNode; newNode.next =
		 * tail; tail.prev = newNode;
		 * 
		 * size++;
		 */

	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E get(int index) {

		if (index < 0 || index >= size) {

			throw new IndexOutOfBoundsException();

		}

		LLNode<E> currentNode = head;

		for (int i = 0; i <= index; i++) {

			currentNode = currentNode.next;

		}

		return currentNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The
	 *            index where the element should be added
	 * @param element
	 *            The element to add
	 */
	public void add(int index, E element)  {
		
		if (element==null){
			
			throw new NullPointerException();
		}

		if (index < 0 || index > size) {

			throw new IndexOutOfBoundsException();

		}

		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> lastNode = head;

		for (int i = 0; i < index; i++) {

			lastNode = lastNode.next;
		}

		newNode.prev = lastNode;
		newNode.next = lastNode.next;

		lastNode.next.prev = newNode;
		lastNode.next = newNode;

		// }

		size++;

	}

	/** Return the size of the list */
	public int size() {
		// TODO: Implement this method
		return size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index
	 *            The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException
	 *             If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {

		if (index < 0 || index >= size) {

			throw new IndexOutOfBoundsException();

		}

		LLNode<E> elementAtIndex = head;

		for (int i = 0; i <= index; i++) {

			elementAtIndex = elementAtIndex.next;
		}

		elementAtIndex.prev.next = elementAtIndex.next;
		elementAtIndex.next.prev = elementAtIndex.prev;

		size--;

		return elementAtIndex.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index
	 *            The index of the element to change
	 * @param element
	 *            The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E set(int index, E element) {
		
		if(element==null){
			
			throw new	NullPointerException();
			
		}
		
		if (index < 0 || index >= size) {

			throw new IndexOutOfBoundsException();

		}

		LLNode<E> elementAtIndex = head;
		
		for (int i = 0; i <= index; i++) {

			elementAtIndex = elementAtIndex.next;
		}
		
		E oldData = elementAtIndex.data;
		
		elementAtIndex.data=element;
		

		
		return oldData;
	}

	public String toString() {
		String listStr = "[";

		LLNode<E> elementAtIndex = head;

		for (int i = 0; i < size; i++) {

			elementAtIndex = elementAtIndex.next;
			listStr+=" "+elementAtIndex.data;
		}
		
		listStr+=" ]";

		return listStr;

	}

}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
