package com.andy.datastructure.list;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.andy.datastructure.DataObject;

public class LinkedListTest {
	@Mock DataObject<Integer> node;
	
	private LinkedList<Integer> linkedList; 
	@Before
	public void setUp() throws Exception {
		linkedList = new LinkedList<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		linkedList = null;
	}

	@Test
	public void testAdd() {
		assertTrue(linkedList.add(node));
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
		fail("Not yet implemented"); // TODO
	}

}
