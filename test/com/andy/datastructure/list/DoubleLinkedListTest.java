package com.andy.datastructure.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.andy.ds.linear.DoubleLinkedList;

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

	@Test(expected=IllegalArgumentException.class)
	public void testAdd_WhenDataIsNull() {
		doubleLinkedList.add(null);
	}
	
	@Test
	public void testAddAt() {
		for(int counter = 1; counter < 11 ; counter++ ) {
			assertTrue(doubleLinkedList.add(counter));
		}
		assertTrue(doubleLinkedList.addAt(0,0));
		
		assertTrue(doubleLinkedList.addAt(11,doubleLinkedList.size()));
		
		assertTrue(doubleLinkedList.addAt(5,5));
		
		assertEquals(13,doubleLinkedList.size());
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testAddAt_whenIndexLessThanZero() {
		doubleLinkedList.addAt(1, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddAt_whenDataIsNull() {
		doubleLinkedList.addAt(null, 1);
	}
	
	@Test
	public void testRemove() {
		for(int counter = 1; counter < 11; counter++) {
			assertTrue(doubleLinkedList.add(counter));
		}
		
		assertFalse(doubleLinkedList.remove(11));
		
		assertTrue(doubleLinkedList.remove(5));
		assertEquals(9, doubleLinkedList.size());
		
		assertTrue(doubleLinkedList.remove(1)); 
		assertEquals(8, doubleLinkedList.size());
		
		assertTrue(doubleLinkedList.remove(10));
		assertEquals(7, doubleLinkedList.size());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRemove_whenDataIsNull(){
		doubleLinkedList.remove(null);
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

	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveAt_whenIndexLessThanLengthOfList(){
		doubleLinkedList.removeAt(0);
	}
	
	@Test
	public void testRemoveAllT() {
		for(int counter = 1; counter < 11 ; counter++ ) {
			assertTrue(doubleLinkedList.add(counter));
		}
		for(int counter = 1; counter < 11 ; counter++ ) {
			assertTrue(doubleLinkedList.add(counter));
		}
		
		assertTrue("Remove all 5 from list.", doubleLinkedList.removeAll(5));
		assertEquals(18, doubleLinkedList.size());	
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRemoveAll_whenDataIsNull() {
		doubleLinkedList.removeAll(null);
		
	}
	
	@Test
	public void testRemoveAll() {
		for(int counter = 1; counter < 11 ; counter++ ) {
			assertTrue(doubleLinkedList.add(counter));
		}
		
		assertTrue("Remove all from list.", doubleLinkedList.removeAll());
		assertEquals(0, doubleLinkedList.size());		
	}

	@Test
	public void testGet() {
		int counter= 1;
		for( ; counter < 11 ; counter++) {
			assertTrue(doubleLinkedList.add(counter));
		}
		counter= 1;
		for( ; counter < 11 ; counter++) {
			assertEquals(Integer.valueOf(counter),doubleLinkedList.get((counter - 1)));
		}
	}
	
	@Test
	public void testGet_whenIndexInvalid(){
		assertNull(doubleLinkedList.get(0));
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
 