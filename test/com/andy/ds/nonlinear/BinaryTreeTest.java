package com.andy.ds.nonlinear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.andy.ds.nonlinear.BinaryTree;

public class BinaryTreeTest {
	private BinaryTree<Integer> binaryTree;
	
	@Before
	public void setUp() throws Exception {
		binaryTree = new BinaryTree<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		binaryTree = null;
	}

	@Test
	public void testInsertNode() {
		assertTrue(binaryTree.insertNode(5));
		assertTrue(binaryTree.insertNode(2));
		assertTrue(binaryTree.insertNode(8));
		assertTrue(binaryTree.insertNode(3));
		assertTrue(binaryTree.insertNode(6)); 
		assertTrue(binaryTree.insertNode(1));
		assertTrue(binaryTree.insertNode(9)); 
		
		assertEquals(Integer.valueOf(2), binaryTree.getRoot().getLeftNode().getData());
		assertEquals(Integer.valueOf(8), binaryTree.getRoot().getRightNode().getData());
		
		assertEquals(Integer.valueOf(1), binaryTree.getRoot().getLeftNode().getLeftNode().getData());
		assertEquals(Integer.valueOf(9), binaryTree.getRoot().getRightNode().getRightNode().getData()); 
		
		assertEquals(Integer.valueOf(3), binaryTree.getRoot().getLeftNode().getRightNode().getData());
		assertEquals(Integer.valueOf(6), binaryTree.getRoot().getRightNode().getLeftNode().getData());
		
		assertEquals(7, binaryTree.getNumberOfNodes());
		
	}

	@Test
	public void testDeleteNode() {
		assertTrue(binaryTree.insertNode(5));
		assertTrue(binaryTree.insertNode(2));
		assertTrue(binaryTree.insertNode(8));
		
		assertTrue(binaryTree.deleteNode(5));
		assertTrue(binaryTree.deleteNode(1));
		assertTrue(binaryTree.deleteNode(6));
		
		assertEquals(0, binaryTree.getNumberOfNodes());
	}

	@Test
	public void testVisitPreOrder() {
		assertTrue(binaryTree.deleteNode(5));
		assertTrue(binaryTree.deleteNode(1));
		assertTrue(binaryTree.deleteNode(6));
		
		assertEquals(new Integer[]{5,1,6},binaryTree.visitPreOrder());
	}

	@Test
	public void testVisitInOrder() {
		assertTrue(binaryTree.deleteNode(5));
		assertTrue(binaryTree.deleteNode(1));
		assertTrue(binaryTree.deleteNode(6));
		
		assertEquals(new Integer[]{1,5,6},binaryTree.visitInOrder());
	}

	@Test
	public void testVisitPostOrder() {
		assertTrue(binaryTree.deleteNode(5));
		assertTrue(binaryTree.deleteNode(1));
		assertTrue(binaryTree.deleteNode(6));
		
		assertEquals(new Integer[]{1,6,5},binaryTree.visitPostOrder());
	}

	@Test
	public void testNumberOfNodes() {
		assertTrue(binaryTree.deleteNode(5));
		assertTrue(binaryTree.deleteNode(1));
		assertTrue(binaryTree.deleteNode(6));
		
		assertEquals(3, binaryTree.getNumberOfNodes());
	}
}
