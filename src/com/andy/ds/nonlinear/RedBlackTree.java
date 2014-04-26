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
		temp.setLeftChildNode(subRoot);		
		fixParentAndColourAfterRotation(subRoot, temp);
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
		temp.setRightChildNode(subRoot);
		
		fixParentAndColourAfterRotation(subRoot, temp);
		
		return temp;
	}

	/**
	 * fix the parent object reference after rotation
	 * @param subRoot
	 * @param temp
	 */
	private void fixParentAndColourAfterRotation(TreeDataObject<T> subRoot,
			TreeDataObject<T> temp) {
		temp.setParentNode(subRoot.getParentNode());
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
		
	}


	
	/**
	 * Flip the color of sub root and there children
	 * 
	 * @param subRoot
	 *            is sub-root where colour will be flip
	 */
	private void flipColors(TreeDataObject<T> subRoot) {
		subRoot.setColour(!subRoot.getColour());
		subRoot.getLeftChildNode().setColour(
				!subRoot.getLeftChildNode().getColour());
		subRoot.getRightChildNode().setColour(
				!subRoot.getRightChildNode().getColour());
	}


	@Override
	public TreeDataObject<T> insertNode(T data){
		TreeDataObject<T> node = super.insertNode(data);
		TreeDataObject<T> returnNode = node;
		TreeDataObject<T> parentNode = node.getParentNode();
		if(parentNode != null){
			TreeDataObject<T> grandParentNode = parentNode.getParentNode();
			if(grandParentNode != null){
				while(isRed(parentNode) && (!getRoot().equals(parentNode))){
					TreeDataObject<T> uncleNode = siblingNode(parentNode);
					
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
		return returnNode;
	}
	
	protected TreeDataObject<T> siblingNode(TreeDataObject<T> node) {
		TreeDataObject<T> siblingNode = null;
		TreeDataObject<T> parentNode = null;
		if(node != null){
			parentNode = node.getParentNode();
			if(parentNode!= null){
				if(node.equals(parentNode.getLeftChildNode())){
					siblingNode = parentNode.getRightChildNode();
				} else {
					siblingNode = parentNode.getLeftChildNode();
				}
			}
		}
		return siblingNode;
	}
	
	@Override
	public TreeDataObject<T> deleteNode(T data){
		TreeDataObject<T> successorNode = super.deleteNode(data);
//		while(successorNode != getRoot() && isBlack(successorNode)){
//			
//		}
		return successorNode;
	}
	
	@Override
	protected void updateSuccessorNodeChildrensWithDeletedNodeChildrens(
			TreeDataObject<T> node, TreeDataObject<T> successorNode) {
		super.updateSuccessorNodeChildrensWithDeletedNodeChildrens(node, successorNode);
		successorNode.setColour(node.getColour());
	}
	
	@Override
	protected void updateSuccessorNodeChildrenParentRefBeforeShift(
			TreeDataObject<T> toBeDeletednode, TreeDataObject<T> successorNode) {
		TreeDataObject<T> rightChildOfSuccessor = successorNode.getRightChildNode();
		if(rightChildOfSuccessor != null) {
			rightChildOfSuccessor.setColour(successorNode.getColour());
		}
		super.updateSuccessorNodeChildrenParentRefBeforeShift(toBeDeletednode, successorNode);
	}
}
