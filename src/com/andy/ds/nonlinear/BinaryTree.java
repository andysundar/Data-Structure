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

import com.andy.adt.BinaryTreeDataObject;

public class BinaryTree<T extends Comparable<T>> {
	private int numberOfNodes;
	private BinaryTreeDataObject<T> root;
	
	public boolean insertNode(T data){
		if(data == null){
			throw new IllegalArgumentException("Null cannot be inserted. ");
		}
		boolean isOk = false;
		if(root == null){
			isOk = insertRoot(data);
		} else {
			isOk = insertChild(data);
		}
		if(isOk){
			numberOfNodes++;
		}
		return isOk;
	}
	
	private boolean insertChild(T data) {
		boolean isOk = false;
		BinaryTreeDataObject<T> parentNode = findParentNode(data);

		if(data.compareTo(parentNode.getData()) <= 0){
			insertLeftChild(parentNode,data);
			isOk = true;
		} else {
			insertRightChild(parentNode,data);
			isOk = true;	
		}
		return isOk;
	}

	private void insertLeftChild(BinaryTreeDataObject<T> parentNode,T data){
		BinaryTreeDataObject<T> childNode = new BinaryTreeDataObject<T>();
		childNode.setData(data);
		parentNode.setLeftNode(childNode);
		childNode.setParentNode(parentNode);
	}
	
	private void insertRightChild(BinaryTreeDataObject<T> parentNode,T data){
		BinaryTreeDataObject<T> childNode = new BinaryTreeDataObject<T>();
		childNode.setData(data);
		parentNode.setRightNode(childNode);
		childNode.setParentNode(parentNode);
	}

	private boolean insertRoot(T data) {
		BinaryTreeDataObject<T> rootNodeObject = new BinaryTreeDataObject<T>();
		rootNodeObject.setData(data);
		root = rootNodeObject;
		return true;
	}

	public boolean deleteNode(T data) {
		if(data == null){
			throw new IllegalArgumentException("Null cannot be deleted.");
		}
		BinaryTreeDataObject<T> node = findParentNode(data);
		boolean isOk = ((node != null) && (data.equals(node.getData())));
		BinaryTreeDataObject<T> successorNode = null;
		if(isOk){
			if((node.getLeftNode() == null) && (node.getRightNode() == null)) {
				unlinkNode(node,null);
			} else if((node.getLeftNode() != null) && (node.getRightNode() != null)) {
				successorNode = findDeleteNodeSuccessor(node);
				successorNode.setParentNode(node.getParentNode());
				if(!successorNode.equals(node.getRightNode())){
					successorNode.setRightNode(node.getRightNode());
				}
				successorNode.setLeftNode(node.getLeftNode());
				unlinkNode(node, successorNode);
			} else {
				if(node.getLeftNode() != null) {
					successorNode = node.getLeftNode();
					node.setLeftNode(null);
				} else {
					successorNode = node.getRightNode();
					node.setRightNode(null);
				}
				successorNode.setParentNode(node.getParentNode());
				unlinkNode(node, successorNode);
			}
			numberOfNodes--;
		}
		return isOk;
	}
	
	private BinaryTreeDataObject<T> findDeleteNodeSuccessor(BinaryTreeDataObject<T> deleteNode) {
		BinaryTreeDataObject<T> findExtreamLeftChild = deleteNode.getRightNode().getLeftNode();
		
		while(findExtreamLeftChild != null) {
			if(findExtreamLeftChild.getLeftNode() == null){
				break;
			}
			findExtreamLeftChild = findExtreamLeftChild.getLeftNode();
		}
		return findExtreamLeftChild;
	}
	
	
	private void unlinkNode(BinaryTreeDataObject<T> deleteNode,BinaryTreeDataObject<T> deleteNodeChildLink){
		deleteNode.setData(null);
		BinaryTreeDataObject<T> parentNode = deleteNode.getParentNode();
		if(parentNode != null){
			if(deleteNode.equals(parentNode.getLeftNode())){
				parentNode.setLeftNode(deleteNodeChildLink);
			} else {
				parentNode.setRightNode(deleteNodeChildLink);
			}
		}
		deleteNode.setParentNode(null);
		deleteNode.setLeftNode(null);
		deleteNode.setRightNode(null);
	}
	
	protected BinaryTreeDataObject<T> findParentNode(T data){
		BinaryTreeDataObject<T> element = root;
		BinaryTreeDataObject<T> parentNode = null;
		if(element != null){
			while(element != null) {
				if(data.compareTo(element.getData()) <= 0) {
					parentNode = element;
					element = getLeftChild(element);
				} else {
					parentNode = element;
					element = getRightChild(element);
				}
			}
		}
		return parentNode;
	}

	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	public BinaryTreeDataObject<T> getRoot() {
		return root;
	}
	
	public BinaryTreeDataObject<T> getLeftChild(BinaryTreeDataObject<T> node){
		return node.getLeftNode();
	}
	
	public BinaryTreeDataObject<T> getRightChild(BinaryTreeDataObject<T> node){
		return node.getRightNode();
	}
}
