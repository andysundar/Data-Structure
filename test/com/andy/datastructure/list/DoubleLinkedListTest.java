package com.andy.datastructure.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DoubleLinkedListTest {
	private DoubleLinkedList<Integer> doubleLinkedList = null;

	@Before
	public void setUp() throws Exception {
		doubleLinkedList = new DoubleLinkedList<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		doubleLinkedList = null;
	}

	@Test
	public void testAdd() {
		for(int counter = 1; counter < 11 ; counter++ ) {
			assertTrue(doubleLinkedList.add(counter));
		}
		
		assertEquals(10,doubleLinkedList.size());
	}

	@Test
	public void testAddAt() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveAt() {
		for(int counter = 1; counter < 11 ; counter++ ) {
			assertTrue(doubleLinkedList.add(counter));
		}
		assertEquals(10,doubleLinkedList.size());
		 
		assertTrue("Reomved from 1st position.",doubleLinkedList.removeAt(0));
		assertEquals(9,doubleLinkedList.size());
		
		assertTrue("Reomved from last position.",doubleLinkedList.removeAt((doubleLinkedList.size() - 1)));
		assertEquals(8,doubleLinkedList.size());
		
		assertTrue("Removed from middle position.", doubleLinkedList.removeAt(5));
		assertEquals(7, doubleLinkedList.size());
	}

	@Test
	public void testRemoveAllT() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGet() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSize() {
		int counter= 1;
		for( ; counter < 11 ; counter++) {
		assertTrue(doubleLinkedList.add(counter));
		}
		assertEquals((counter - 1), doubleLinkedList.size());
	}

}
 