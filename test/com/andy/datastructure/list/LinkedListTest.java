package com.andy.datastructure.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	private LinkedList<Integer> linkedList = null;
	
	@Before
	public void beforeEveryMethod() {
		linkedList = new LinkedList<Integer>();
	}
	
	@After
	public void afterEveryMethod() {
		linkedList = null;
	}
	
	@Test
	public void testAdd() {
		assertTrue(linkedList.add(1));
		assertTrue(linkedList.add(2));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAdd_whenParamIsNull() {
		assertTrue(linkedList.add(null));
	}

	@Test
	public void testAddAt() {
		assertTrue(linkedList.addAt(1,0));
		assertEquals(Integer.valueOf(1),linkedList.get(0));
		assertTrue(linkedList.addAt(2,1));
		assertEquals(Integer.valueOf(2),linkedList.get(1));
		assertTrue(linkedList.addAt(3,2));
		assertEquals(Integer.valueOf(3),linkedList.get(2));
		assertTrue(linkedList.addAt(4,1));
		assertEquals(Integer.valueOf(4),linkedList.get(1));
		assertTrue(linkedList.addAt(5,0));
		assertEquals(Integer.valueOf(5),linkedList.get(0));
		assertTrue(linkedList.addAt(6,5));
		assertEquals(Integer.valueOf(6),linkedList.get(5));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddAt_WhenDataIsNull() {
		linkedList.addAt(null,0);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testAddAt_WhenIndexIs2ForEmptyList() {
		linkedList.addAt(1,2);
	}
	
	@Test
	public void testRemoveAt() {
		assertTrue(linkedList.add(Integer.valueOf(1)));
		assertTrue(linkedList.add(Integer.valueOf(2)));
		assertTrue(linkedList.removeAt(0));
		assertTrue(linkedList.removeAt(0));
		assertEquals(Integer.valueOf(0), Integer.valueOf(linkedList.size()));
		
		assertTrue(linkedList.add(Integer.valueOf(1)));
		assertTrue(linkedList.add(Integer.valueOf(2)));
		assertTrue(linkedList.add(Integer.valueOf(3)));
		assertTrue(linkedList.removeAt(1));
		assertEquals(Integer.valueOf(2), Integer.valueOf(linkedList.size()));
		
		assertTrue(linkedList.add(Integer.valueOf(1)));
		assertTrue(linkedList.add(Integer.valueOf(2)));
		assertTrue(linkedList.add(Integer.valueOf(3)));
		assertTrue(linkedList.removeAt(0));
		assertEquals(Integer.valueOf(4), Integer.valueOf(linkedList.size()));
		
		assertTrue(linkedList.add(Integer.valueOf(1)));
		assertTrue(linkedList.add(Integer.valueOf(2)));
		assertTrue(linkedList.add(Integer.valueOf(3)));
		assertTrue(linkedList.removeAt(2));
		assertEquals(Integer.valueOf(6), Integer.valueOf(linkedList.size()));
		
		
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveAt_whenIndexIsLessThanZero() {
		linkedList.removeAt(-1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveAt_whenIndexIsGreaterThanOrEqualToLengthOfList() {
		linkedList.removeAt(linkedList.size());
	}
	
	@Test
	public void testRemove() {
		for(int index = 1; index <= 10 ; index++) {
			assertTrue(linkedList.add(index));
		}
		assertTrue(linkedList.remove(5));
		assertEquals(Integer.valueOf(9), Integer.valueOf(linkedList.size()));
		
		assertTrue(linkedList.remove(10));
		assertEquals(Integer.valueOf(8), Integer.valueOf(linkedList.size()));
		
		assertTrue(linkedList.remove(1));
		assertEquals(Integer.valueOf(7), Integer.valueOf(linkedList.size()));
		
		assertEquals(Integer.valueOf(2), linkedList.get(0));
		assertEquals(Integer.valueOf(9), linkedList.get((linkedList.size() - 1)));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRemove_WhenDataIsNull() {
		linkedList.remove(null);
	}
	
	@Test
	public void testRemoveAll() {
		for(int index = 1; index <= 10 ; index++) {
			assertTrue(linkedList.add(index));
		}
		assertTrue(linkedList.removeAll());
		assertEquals(Integer.valueOf(0), Integer.valueOf(linkedList.size()));
	}

	@Test
	public void testGet() {
		for(int index = 0; index < 100 ; index++) {
			linkedList.add((index + 1));
			assertEquals(Integer.valueOf((index + 1)),linkedList.get(index));
		}		
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGet_whenIndexIsLessThanZero() {
		linkedList.get(-1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGet_whenIndexIsGreaterThanOrEqualToLengthOfList() {
		linkedList.get(linkedList.size());
	}

}
