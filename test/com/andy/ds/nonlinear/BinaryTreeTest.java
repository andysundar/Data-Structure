/**
 * Copyright [2012] Anindya Bandopadhyay
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.andy.ds.nonlinear;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.andy.adt.TreeDataObject;
import com.andy.ds.nonlinear.BinaryTree;

public class BinaryTreeTest {
	private BinaryTree<Integer> binaryTree;
	private TreeDataObject<Integer> node =null;
	
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
		node = binaryTree.insertNode(5);
		assertEquals(node.getData(), binaryTree.getRoot().getData());
		node = binaryTree.insertNode(2);
		assertEquals(node.getData(), Integer.valueOf(2));
		node = binaryTree.insertNode(8);
		assertEquals(node.getData(), Integer.valueOf(8));
		node = binaryTree.insertNode(3);
		assertEquals(node.getData(), Integer.valueOf(3));
		node = binaryTree.insertNode(6); 
		assertEquals(node.getData(), Integer.valueOf(6));
		node = binaryTree.insertNode(1);
		assertEquals(node.getData(), Integer.valueOf(1));
		node = binaryTree.insertNode(9);
		assertEquals(node.getData(), Integer.valueOf(9));
		
		assertEquals(Integer.valueOf(2), binaryTree.getRoot().getLeftChildNode().getData());
		assertEquals(Integer.valueOf(8), binaryTree.getRoot().getRightChildNode().getData());
		
		assertEquals(Integer.valueOf(1), binaryTree.getRoot().getLeftChildNode().getLeftChildNode().getData());
		assertEquals(Integer.valueOf(9), binaryTree.getRoot().getRightChildNode().getRightChildNode().getData()); 
		
		assertEquals(Integer.valueOf(3), binaryTree.getRoot().getLeftChildNode().getRightChildNode().getData());
		assertEquals(Integer.valueOf(6), binaryTree.getRoot().getRightChildNode().getLeftChildNode().getData());
		
		assertEquals(7, binaryTree.getNumberOfNodes());
		
	}

	@Test
	public void testDeleteNode() {
		node = binaryTree.insertNode(5);
		assertEquals(node.getData(), binaryTree.getRoot().getData());
		node = binaryTree.insertNode(2);
		assertEquals(node.getData(), Integer.valueOf(2));
		node = binaryTree.insertNode(8);
		assertEquals(node.getData(), Integer.valueOf(8));
		
		assertEquals(3, binaryTree.getNumberOfNodes()); 
		
		binaryTree.deleteNode(5);
		binaryTree.deleteNode(2);
		binaryTree.deleteNode(8);
		
		assertEquals(0, binaryTree.getNumberOfNodes());
	}

	@Test
	public void testDeleteNode_withRightChildOnly() {
		node = binaryTree.insertNode(4);
		assertEquals(node.getData(), binaryTree.getRoot().getData());
		node = binaryTree.insertNode(1);
		assertEquals(node.getData(), Integer.valueOf(1));
		node = binaryTree.insertNode(3);
		assertEquals(node.getData(), Integer.valueOf(3));
		node = binaryTree.insertNode(2);
		assertEquals(node.getData(), Integer.valueOf(2));
		node = binaryTree.insertNode(8);
		assertEquals(node.getData(), Integer.valueOf(8));
		node = binaryTree.insertNode(6);
		assertEquals(node.getData(), Integer.valueOf(6));
		node = binaryTree.insertNode(5);
		assertEquals(node.getData(), Integer.valueOf(5));
		
		assertEquals(7, binaryTree.getNumberOfNodes());
		
		binaryTree.deleteNode(5);
		assertEquals(Integer.valueOf(4), binaryTree.getRoot().getData());
		binaryTree.deleteNode(4);
		assertEquals(Integer.valueOf(6), binaryTree.getRoot().getData());
		binaryTree.deleteNode(1);
		assertEquals(Integer.valueOf(6), binaryTree.getRoot().getData());
		binaryTree.deleteNode(3);
		assertEquals(Integer.valueOf(6), binaryTree.getRoot().getData());
		binaryTree.deleteNode(2);
		assertEquals(Integer.valueOf(6), binaryTree.getRoot().getData());
		binaryTree.deleteNode(8);
		assertEquals(Integer.valueOf(6), binaryTree.getRoot().getData());
		binaryTree.deleteNode(6);
		
		assertEquals(0, binaryTree.getNumberOfNodes());
	}
	
	@Test
	public void testDeleteNode_withLeftChildOnly() {
		node = binaryTree.insertNode(5);
		assertEquals(node.getData(), binaryTree.getRoot().getData());
		node = binaryTree.insertNode(1);
		assertEquals(node.getData(), Integer.valueOf(1));
		node = binaryTree.insertNode(3);
		assertEquals(node.getData(), Integer.valueOf(3));
		node = binaryTree.insertNode(2);
		assertEquals(node.getData(), Integer.valueOf(2));
		
		assertEquals(4, binaryTree.getNumberOfNodes());
		
		binaryTree.deleteNode(5);
		assertEquals(Integer.valueOf(3), binaryTree.getRoot().getData());
		binaryTree.deleteNode(2);
		assertEquals(Integer.valueOf(3), binaryTree.getRoot().getData());
		binaryTree.deleteNode(3);
		assertEquals(Integer.valueOf(1), binaryTree.getRoot().getData());
		binaryTree.deleteNode(1);
		
		assertEquals(0, binaryTree.getNumberOfNodes());
	}
	
	@Test
	public void testDeleteNode_withOutRightChild() {
		binaryTree.insertNode(1);
		binaryTree.insertNode(5);
		binaryTree.insertNode(2);

		
		binaryTree.deleteNode(5);
		binaryTree.deleteNode(2);
		binaryTree.deleteNode(1);
		
		assertEquals(0, binaryTree.getNumberOfNodes());
	}
	@Test
	public void testNumberOfNodes() {
		binaryTree.insertNode(5);
		binaryTree.insertNode(1);
		binaryTree.insertNode(6);
		
		assertEquals(3, binaryTree.getNumberOfNodes());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInsertNodeWithNULLdata(){
		binaryTree.insertNode(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDeleteNodeWithNULLdata(){
		binaryTree.deleteNode(null);
	}
	
	@Test
	public void testDeleteNode_withDataWhichIsNotInTree(){
		binaryTree.insertNode(1);
		binaryTree.insertNode(5);
		binaryTree.insertNode(2);
		
		assertNull(binaryTree.deleteNode(3));
	}
	
	private void insertNodeIntoTree(){
		binaryTree.insertNode(1);
		binaryTree.insertNode(5);
		binaryTree.insertNode(2);
	}
	
	@Test
	public void testGetMaxValueNode(){
		insertNodeIntoTree();
		assertEquals(Integer.valueOf(5) , binaryTree.getMaxValueNode(binaryTree.getRoot()).getData());
	}
	
	@Test
	public void testGetMinValueNode(){
		insertNodeIntoTree();
		assertEquals(Integer.valueOf(1) , binaryTree.getMinValueNode(binaryTree.getRoot()).getData());
	}
}
