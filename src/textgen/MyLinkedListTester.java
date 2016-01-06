/**
 * 
 */
package textgen;
 
import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH = 10;

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<Integer> list2;
	MyLinkedList<Integer> list3;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
		shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		

		list2 = new MyLinkedList<Integer>();
		list3 = new MyLinkedList<Integer>();

	}

	/**
	 * Test if the get method is working correctly.
	 */
	/*
	 * You should not need to add much to this method. We provide it as an
	 * example of a thorough test.
	 */
	@Test
	public void testGet() {
		// test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));

		try {
			shortList.get(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		// test longer list contents
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			assertEquals("Check " + i + " element", (Integer) i, longerList.get(i));
		}

		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}

	}

	/**
	 * Test removing an element from the list. We've included the example from
	 * the concept challenge. You will want to add more tests.
	 */
	@Test
	public void testRemove() {
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());

		// TODO: Add more tests here
		
		// test invalid negative index
				try {
					list3.remove(-10);
					fail("Removing at negative index test failed");
				} catch (IndexOutOfBoundsException e) {

				}
				
				// test invalid index bigger than list size
				try {
					list3.remove(100);
					fail("Removing at  index bigger than list size test failed");
				} catch (IndexOutOfBoundsException e) {

				}
	}

	/**
	 * Test adding an element into the end of the list, specifically public
	 * boolean add(E element)
	 */
	@Test
	public void testAddEnd() {

		try {
			// test add at empty list

			list2.add(10);
			assertEquals("Checking if last element was added ", (Integer) 10, list2.get(0));

			// test add at the end in a list with elements

			list2.add(11);
			assertEquals("Checking if last element was added ", (Integer) 11, list2.get(1));

			list2.add(12);
			assertEquals("Checking if last element was added ", (Integer) 12, list2.get(2));
		} catch (IndexOutOfBoundsException e) {

			fail("Adding at empty list failed");
		}

	}

	/** Test the size of the list */
	@Test
	public void testSize() {

		assertEquals("Correct size of list with elements ", list1.size, 3);
		assertEquals("Correct size of empty list ", list2.size, 0);


	
	}

	/**
	 * Test adding an element into the list at a specified index, specifically:
	 * public void add(int index, E element)
	 */
	@Test
	public void testAddAtIndex() {
		
		
		// test invalid negative index
		try {
			list3.add(-10, 3);
			fail("Inserting at negative index test failed");
		} catch (IndexOutOfBoundsException e) {

		}
		
		// test invalid index bigger than list size
		try {
			list3.add(10, 3);
			fail("Inserting  index bigger than list size test failed");
		} catch (IndexOutOfBoundsException e) {

		}
		
		// test add element in an empty list
		
		list3.add(0, 1);
		assertEquals("Checking if last element was added ", (Integer) 1, list3.get(0));
		
		list3.add(0, 2);
		assertEquals("Checking if last element was added ", (Integer) 2, list3.get(0));
		
		list3.add(1, 3);
		assertEquals("Checking if last element was added ", (Integer) 2, list3.get(0));
		
		// test invalid null element
		try {
			list3.add(0, null);
			fail("Inserting null element test failed");
		} catch (NullPointerException e) {

		}

				
	}

	/** Test setting an element in the list */
	@Test
	public void testSet() {
		
 list2.add(1);
 list2.add(2);
 list2.add(3);
 
 assertEquals("Checking that old value is returned ", list2.set(0, 10), (Integer)1);
 assertEquals("Checking first element was changed ", (Integer) 10, list2.get(0));
 
 // test invalid index
		try {
			list2.set(-10, 3);
			fail("Inserting at negative index test failed");
		} catch (IndexOutOfBoundsException e) {

		}
		
		try {
			list2.set(10, 3);
			fail("Inserting at big index test failed");
		} catch (IndexOutOfBoundsException e) {

		}
		
		
		
	}
	
	

	// TODO: Optionally add more test methods.

}
