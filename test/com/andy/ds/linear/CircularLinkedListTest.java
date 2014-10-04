/**
 * @Author Anindya Bandopadhyay
 *
 * DataStructure\com.andy.ds.linear.CircularLinkedListTest.java
 *
 * @Created on Mar 22, 2014  11:48:32 PM
 */

package com.andy.ds.linear;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircularLinkedListTest {

	private CircularLinkedList<Integer> circularLinkedList;
	@Before
	public void setUp() throws Exception {
		circularLinkedList = new CircularLinkedList<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		circularLinkedList = null;
	}

	@Test
	public void testAdd() {
		assertTrue(circularLinkedList.add(1));
		assertTrue(circularLinkedList.add(2));
	}

	@Test
	public void testAddAt() {
		assertTrue(circularLinkedList.addAt(0,1));
	}
	
	@Test
	public void testIsEmpty_whenListHaveOneElement() {
		assertTrue(circularLinkedList.add(1));
		assertFalse(circularLinkedList.isEmpty());
	}
	
	@Test
	public void testIsEmpty_whenListHaveNoElement() {
		assertTrue(circularLinkedList.isEmpty());
	}
	
	@Test
	public void testRemove_whenDataPresentInList() {
		assertTrue(circularLinkedList.add(1));
		assertTrue(circularLinkedList.remove(1));
	}
	
	@Test
	public void testRemove_whenDataNotPresentInList() {
		assertTrue(circularLinkedList.add(1));
		assertFalse(circularLinkedList.remove(2));
	}
	
	@Test
	public void testRemove_whenListIsEmpty() {
		assertFalse(circularLinkedList.remove(2));
	} 
	
	@Test
	public void testRemove_whenDataInMiddleOfTheList() {
		assertTrue(circularLinkedList.add(1));
		assertTrue(circularLinkedList.add(2));
		assertTrue(circularLinkedList.add(3));
		assertTrue(circularLinkedList.remove(3));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemove_whenDataIsNull(){
		circularLinkedList.remove(null);
	}
	
	@Test
	public void testRemoveAll_whenSameDataPresentInListMultipleTimes() {
		assertTrue(circularLinkedList.add(1));
		assertTrue(circularLinkedList.add(1));
		assertTrue(circularLinkedList.removeAll(1));
	}
	
	@Test
	public void testRemoveAt_whenDataPresentInList() {
		assertTrue(circularLinkedList.add(1));
		assertTrue(circularLinkedList.add(2));
		assertTrue(circularLinkedList.removeAt(1));
	}
	
	@Test
	public void testRemoveAll() {
		assertTrue(circularLinkedList.add(1));
		assertTrue(circularLinkedList.add(2));
		assertTrue(circularLinkedList.removeAll());
	}
	
	@Test
	public void testRemoveAll_whenDataPresentInList() {
		assertTrue(circularLinkedList.add(1));
		assertTrue(circularLinkedList.add(1));
		assertTrue(circularLinkedList.remove(1));
	}
}
