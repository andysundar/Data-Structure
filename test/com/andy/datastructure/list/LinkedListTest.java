package com.andy.datastructure.list;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class LinkedListTest {
	private LinkedList<Integer> linkedList = new LinkedList<Integer>();
	
	@Test
	public void testAdd() {
		assertTrue(linkedList.add(1));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAdd_whenParamIsNull() {
		assertTrue(linkedList.add(null));
	}

	@Test
	public void testAddAt() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveAt() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGet() {
		linkedList.add(1);
		assertNotNull(linkedList.get(0));
		linkedList.add(2);
		assertNotNull(linkedList.get(1));
		linkedList.add(3);
		assertNotNull(linkedList.get(2));
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
