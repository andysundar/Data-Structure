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

import static org.junit.Assert.assertEquals;

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
	public void testInsertNode_LeftRotation() {
		for(int index = 1 ; index < 10;index++ ) {
			redBlackTree.insertRedBlackNode(index);
		}
		assertEquals(Integer.valueOf(4), redBlackTree.getRoot().getData());
	}
	
	@Test
	public void testInsertNode_RightRotation() {
		for(int index = 10 ; index > 0;index-- ) {
			redBlackTree.insertRedBlackNode(index);
		}
		assertEquals(Integer.valueOf(7), redBlackTree.getRoot().getData());
	}

	@Test
	public void testDeleteNode(){
		
	}
}
