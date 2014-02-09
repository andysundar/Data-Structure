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

import com.andy.adt.TreeDataObject;

public class BinaryTree<T extends Comparable<T>> {
	private int numberOfNodes;
	private TreeDataObject<T> root;
	
	public TreeDataObject<T> insertNode(T data){
		if(data == null){
			throw new IllegalArgumentException("Null cannot be inserted. ");
		}
		TreeDataObject<T> node = null;
		if(root == null){
			root = insertRoot(data);
			node = root;
		} else {
			node = insertChild(data);
		}
		if(node != null){
			numberOfNodes++;
		}
		return node;
	}
	
	private TreeDataObject<T> insertChild(T data) {
		TreeDataObject<T> child = null;
		TreeDataObject<T> parentNode = findParentNodeForNewNode(data);

		if(data.compareTo(parentNode.getData()) <= 0){
			child = insertLeftChild(parentNode,data);
		} else {
			child = insertRightChild(parentNode,data);
		}
		return child;
	}

	private TreeDataObject<T> insertLeftChild(TreeDataObject<T> parentNode,T data){
		TreeDataObject<T> childNode = new TreeDataObject<T>();
		childNode.setData(data);
		parentNode.setLeftChildNode(childNode);
		childNode.setParentNode(parentNode);
		return childNode;
	}
	
	private TreeDataObject<T> insertRightChild(TreeDataObject<T> parentNode,T data){
		TreeDataObject<T> childNode = new TreeDataObject<T>();
		childNode.setData(data);
		parentNode.setRightChildNode(childNode);
		childNode.setParentNode(parentNode);
		return childNode;
	}

	private TreeDataObject<T> insertRoot(T data) {
		TreeDataObject<T> rootNodeObject = new TreeDataObject<T>();
		rootNodeObject.setData(data);
		return rootNodeObject;
	}

	public boolean deleteNode(T data) {
		if(data == null){
			throw new IllegalArgumentException("Null cannot be deleted.");
		}
		TreeDataObject<T> node = findNode(data);
		boolean isOk = ((node != null) && (data.equals(node.getData())));
		TreeDataObject<T> successorNode = null;
		if(isOk){
			if((node.getLeftChildNode() == null) && (node.getRightChildNode() == null)) {
				unlinkNode(node,null);
			} else if((node.getLeftChildNode() != null) && (node.getRightChildNode() != null)) {
				successorNode = findDeleteNodeSuccessor(node);
				successorNode.setParentNode(node.getParentNode());
				if(!successorNode.equals(node.getRightChildNode())){
					successorNode.setRightChildNode(node.getRightChildNode());
				}
				successorNode.setLeftChildNode(node.getLeftChildNode());
				unlinkNode(node, successorNode);
			} else {
				if(node.getLeftChildNode() != null) {
					successorNode = node.getLeftChildNode();
					node.setLeftChildNode(null);
				} else {
					successorNode = node.getRightChildNode();
					node.setRightChildNode(null);
				}
				successorNode.setParentNode(node.getParentNode());
				unlinkNode(node, successorNode);
			}
			numberOfNodes--;
		}
		return isOk;
	}
	
	private TreeDataObject<T> findDeleteNodeSuccessor(TreeDataObject<T> deleteNode) {
		TreeDataObject<T> rightChild = deleteNode.getRightChildNode();
		TreeDataObject<T> findExtreamLeftChild = null;
		if(rightChild != null ) {
			findExtreamLeftChild = deleteNode.getRightChildNode().getLeftChildNode();
			if(findExtreamLeftChild == null){
				findExtreamLeftChild = rightChild;
			}
		}
		
		while(findExtreamLeftChild != null) {
			if(findExtreamLeftChild.getLeftChildNode() == null){
				break;
			}
			findExtreamLeftChild = findExtreamLeftChild.getLeftChildNode();
		}
		return findExtreamLeftChild;
	}
	
	
	private void unlinkNode(TreeDataObject<T> deleteNode,TreeDataObject<T> deleteNodeChildLink){
		deleteNode.setData(null);
		TreeDataObject<T> parentNode = deleteNode.getParentNode();
		if(parentNode != null){
			if(deleteNode.equals(parentNode.getLeftChildNode())){
				parentNode.setLeftChildNode(deleteNodeChildLink);
			} else {
				parentNode.setRightChildNode(deleteNodeChildLink);
			}
		} else {
			root = deleteNodeChildLink;
		}
		deleteNode.setParentNode(null);
		deleteNode.setLeftChildNode(null);
		deleteNode.setRightChildNode(null);
	}
	
	private TreeDataObject<T> findParentNodeForNewNode(T data){
		TreeDataObject<T> element = root;
		TreeDataObject<T> parentNode = null;
		while(element != null) {
			if(data.compareTo(element.getData()) <= 0) {
				parentNode = element;
				element = getLeftChild(element);
			} else {
				parentNode = element;
				element = getRightChild(element);
			}
		}
		return parentNode;
	}

	public TreeDataObject<T> findNode(T data){
		TreeDataObject<T> element = root;
		while(element != null) {
				int compairValue = data.compareTo(element.getData());
				if(compairValue < 0) {
					element = getLeftChild(element);
				} else if(compairValue > 0) {
					element = getRightChild(element);
				} else {
					return element;
				}
		}
		return null;
	}
	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	public TreeDataObject<T> getRoot() {
		return root;
	}
	
	public TreeDataObject<T> getLeftChild(TreeDataObject<T> node){
		return node.getLeftChildNode();
	}
	
	public TreeDataObject<T> getRightChild(TreeDataObject<T> node){
		return node.getRightChildNode();
	}
}
