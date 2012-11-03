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
		assertTrue(doubleLinkedList.add(1));
		assertEquals(1,doubleLinkedList.size());
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
		fail("Not yet implemented"); // TODO
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
		fail("Not yet implemented"); // TODO
	}

}
