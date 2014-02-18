/**
 * @Author Anindya Bandopadhyay
 *
 * DataStructure\com.andy.ds.nonlinear.RedBlackTree.java
 *
 * @Created on Jan 18, 2014  10:31:44 PM
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

import com.andy.adt.TreeDataObject;

public class RedBlackTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean isRed(TreeDataObject<T> node) {
		if (node == null) {
			return false;
		}
		return (node.getColour() == TreeDataObject.RED);
	}

	public boolean isBlack(TreeDataObject<T> node) {
		if (node == null) {
			return true;
		}
		return (node.getColour() == TreeDataObject.BLACK);
	}

	/**
	 * Left rotate a 
	 * 				\ 
	 * 				 b 
	 * here node a is marked as 'subRoot' and b is marked as 'temp' 
	 * after left rotation 
	 *                   b
	 *                  / 
	 *                 a 
	 *                 'subRoot' is sub-root where new node
	 * will be placed as child.
	 * 
	 * @return return the new sub-root after rotation.
	 */
	public TreeDataObject<T> rotateLeft(TreeDataObject<T> subRoot) {
		TreeDataObject<T> temp = subRoot.getRightChildNode();
		
		subRoot.setRightChildNode(temp.getLeftChildNode());
		if(subRoot.getRightChildNode() != null) {
			subRoot.getRightChildNode().setParentNode(subRoot);
		}
		
		
		temp.setLeftChildNode(subRoot);
		temp.setParentNode(subRoot.getParentNode());
		
		subRoot.setParentNode(temp);
		
		
		temp.setColour(subRoot.getColour());
		subRoot.setColour(TreeDataObject.RED);
		TreeDataObject<T> parentNode = temp.getParentNode();
		if(parentNode == null){
			setRoot(temp);
		} else {
			if(subRoot.equals(parentNode.getLeftChildNode())){
				parentNode.setLeftChildNode(temp);
			} else {
				parentNode.setRightChildNode(temp);
			}
		}
		return temp;
	}

	/**
	 * Right rotate 
	 * 				a 
	 *             / 
	 *            b 
	 *            here node a is marked as 'subRoot' and b is marked as
	 * 'temp' after right rotation 
	 * 							  b 
	 * 							   \
	 * 								a 'subRoot' is sub-root where new node
	 * will be placed as child.
	 * 
	 * @return return the new sub-root after rotation.
	 */
	public TreeDataObject<T> rotateRight(TreeDataObject<T> subRoot) {
		TreeDataObject<T> temp = subRoot.getLeftChildNode();
		
		subRoot.setLeftChildNode(temp.getRightChildNode());
		if(subRoot.getLeftChildNode() != null){
			subRoot.getLeftChildNode().setParentNode(subRoot);
		}
		
		temp.setRightChildNode(subRoot);
		temp.setParentNode(subRoot.getParentNode());
		
		subRoot.setParentNode(temp);
		
		temp.setColour(subRoot.getColour());
		subRoot.setColour(TreeDataObject.RED);
		
		TreeDataObject<T> parentNode = temp.getParentNode();
		if(parentNode == null){
			setRoot(temp);
		} else {
			if(subRoot.equals(parentNode.getLeftChildNode())){
				parentNode.setLeftChildNode(temp);
			} else {
				parentNode.setRightChildNode(temp);
			}
		}
		
		return temp;
	}

	/**
	 * Flip the color of sub root and there children
	 * 
	 * @param subRoot
	 *            is sub-root where colour will be flip
	 */
	public void flipColors(TreeDataObject<T> subRoot) {
		subRoot.setColour(!subRoot.getColour());
		subRoot.getLeftChildNode().setColour(
				!subRoot.getLeftChildNode().getColour());
		subRoot.getRightChildNode().setColour(
				!subRoot.getRightChildNode().getColour());
	}


	public void insertRedBlackNode(T data){
		TreeDataObject<T> node = super.insertNode(data);
		//0. for root node
		
		
		// 1. The root & leaves are black.
		// 2. Every Red node has black parent.
		// 3. All simple paths from a node X to a descendant 
		//    leave of X have same number of black node(s).
		TreeDataObject<T> parentNode = node.getParentNode();
		if(parentNode != null){
			TreeDataObject<T> grandParentNode = parentNode.getParentNode();
			if(grandParentNode != null){
				while(isRed(parentNode) && (!getRoot().equals(parentNode))){
					TreeDataObject<T> uncleNode = getUncleNode(parentNode,grandParentNode);
					
					if(isRed(uncleNode) && isRed(parentNode)) {
						flipColors(grandParentNode);
						node = grandParentNode;
						parentNode = node.getParentNode();
						if(parentNode == null){
							grandParentNode = null;
						} else {
							grandParentNode = parentNode.getParentNode();
						}
					}
					
					if(isRed(parentNode)&& isRed(node) && (grandParentNode != null)){
						if(isBlack(grandParentNode.getLeftChildNode()) && isRed(grandParentNode.getRightChildNode())) {
							parentNode = rotateLeft(grandParentNode);
							grandParentNode = parentNode.getParentNode();
							node = parentNode.getLeftChildNode();
						} else if(isRed(grandParentNode.getLeftChildNode()) && isBlack(grandParentNode.getRightChildNode())) {
							parentNode = rotateRight(grandParentNode);
							grandParentNode = parentNode.getParentNode();
							node = parentNode.getRightChildNode();		
						}
					}
				}
			}
		}
		
		getRoot().setColour(TreeDataObject.BLACK);
	}
	
	private TreeDataObject<T> getUncleNode(TreeDataObject<T> parentNode,TreeDataObject<T> grandParentNode) {
		TreeDataObject<T> uncleNode = null;
		if(parentNode.equals(grandParentNode.getLeftChildNode())){
			uncleNode = grandParentNode.getRightChildNode();
		} else {
			uncleNode = grandParentNode.getLeftChildNode();
		}
		return uncleNode;
	}
}
