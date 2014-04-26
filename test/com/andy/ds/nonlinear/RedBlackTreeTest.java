/**
 * @Author Anindya Bandopadhyay
 *
 * DataStructure\com.andy.ds.nonlinear.RedBlackTree.java
 *
 * @Created on Feb 11, 2014  3:19:12 PM
 * 
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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.andy.adt.TreeDataObject;

import static org.junit.Assert.*;

public class RedBlackTreeTest {

	private RedBlackTree<Integer> redBlackTree;
	
	@Before
	public void setUp() throws Exception {
		redBlackTree = new RedBlackTree<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		redBlackTree = null;
	}
	
	@Test
	public void testInsertNode_leftRotation() {
		for(int index = 1 ; index < 9;index++ ) {
			redBlackTree.insertNode(index);
		}
		assertEquals(Integer.valueOf(4), redBlackTree.getRoot().getData());
	}

	@Test
	public void testInsertNode_rightRotation() {
		for(int index = 10 ; index > 0;index-- ) {
			redBlackTree.insertNode(index);
		}
		assertEquals(Integer.valueOf(7), redBlackTree.getRoot().getData());
	}

	@Test
	public void testSiblingNode() {
		TreeDataObject<Integer> siblingNode = null; 
		TreeDataObject<Integer> insertedNode = null; 
		for(int index = 1 ; index < 4;index++ ) {
			insertedNode = redBlackTree.insertNode(index);
		}	
		
		siblingNode = redBlackTree.siblingNode(insertedNode);
		assertEquals(Integer.valueOf(1), siblingNode.getData());
	}
	
	@Test
	public void testSiblingNode_whenNodeIsNULL() {
		TreeDataObject<Integer> siblingNode = null; 
		TreeDataObject<Integer> insertedNode = null;
		
		siblingNode = redBlackTree.siblingNode(insertedNode);
		assertNull(siblingNode);
	}
	
	@Test
	public void testSiblingNode_whenParentNodeIsNULL() {
		TreeDataObject<Integer> siblingNode = null; 
		TreeDataObject<Integer> insertedNode = null; 
		for(int index = 1 ; index < 3;index++ ) {
			insertedNode = redBlackTree.insertNode(index);
		}	
		siblingNode = redBlackTree.siblingNode(insertedNode.getParentNode());
		assertNull(siblingNode);
	}
	
	@Test
	public void testDeleteNode_successorHaveSameColour(){
		for(int index = 1 ; index < 4; index++){
			redBlackTree.insertNode(index);
		}
		assertEquals(Integer.valueOf(2), redBlackTree.getRoot().getData());
		
		redBlackTree.deleteNode(2);
		assertTrue(redBlackTree.isBlack(redBlackTree.getRoot()));
	}
	
	@Test
	public void testDeleteNode_deletedNodeIsRedSuccessorNodeIsRed() {
		for(int index = 1 ; index < 7;index++ ) {
			redBlackTree.insertNode(index);
		}
		TreeDataObject<Integer> successorNode = redBlackTree.deleteNode(4);
		assertTrue(redBlackTree.isRed(successorNode));
	}
	
	@Test
	public void testDeleteNode_deletedNodeIsBlackSuccessorNodeIsBlack() {
		for(int index = 1 ; index < 6;index++ ) {
			redBlackTree.insertNode(index);
		}
		TreeDataObject<Integer> successorNode = redBlackTree.deleteNode(4);
		assertTrue(redBlackTree.isBlack(successorNode));
	}
	
	@Test
	public void testDeleteNode_whenSiblingIsRed(){
		TreeDataObject<Integer> insertedNode = null;
		for(int index = 1 ; index < 6;index++ ) {
			insertedNode = redBlackTree.insertNode(index);
		}
		TreeDataObject<Integer> siblingNode = redBlackTree.siblingNode(insertedNode);
		TreeDataObject<Integer> successorNode = redBlackTree.deleteNode(4);
		assertTrue(redBlackTree.isBlack(successorNode));
		assertTrue(redBlackTree.isRed(successorNode.getParentNode()));
		assertTrue(redBlackTree.isBlack(siblingNode));
	}
	
}
