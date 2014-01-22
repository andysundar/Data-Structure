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
		TreeDataObject<T> parentNode = findParentNode(data);

		if(data.compareTo(parentNode.getData()) <= 0){
			insertLeftChild(parentNode,data);
			isOk = true;
		} else {
			insertRightChild(parentNode,data);
			isOk = true;	
		}
		return isOk;
	}

	private void insertLeftChild(TreeDataObject<T> parentNode,T data){
		TreeDataObject<T> childNode = new TreeDataObject<T>();
		childNode.setData(data);
		parentNode.setLeftNode(childNode);
		childNode.setParentNode(parentNode);
	}
	
	private void insertRightChild(TreeDataObject<T> parentNode,T data){
		TreeDataObject<T> childNode = new TreeDataObject<T>();
		childNode.setData(data);
		parentNode.setRightNode(childNode);
		childNode.setParentNode(parentNode);
	}

	private boolean insertRoot(T data) {
		TreeDataObject<T> rootNodeObject = new TreeDataObject<T>();
		rootNodeObject.setData(data);
		root = rootNodeObject;
		return true;
	}

	public boolean deleteNode(T data) {
		if(data == null){
			throw new IllegalArgumentException("Null cannot be deleted.");
		}
		TreeDataObject<T> node = findNode(data);
		boolean isOk = ((node != null) && (data.equals(node.getData())));
		TreeDataObject<T> successorNode = null;
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
	
	private TreeDataObject<T> findDeleteNodeSuccessor(TreeDataObject<T> deleteNode) {
		TreeDataObject<T> rightChild = deleteNode.getRightNode();
		TreeDataObject<T> findExtreamLeftChild = null;
		if(rightChild != null ) {
			findExtreamLeftChild = deleteNode.getRightNode().getLeftNode();
			if(findExtreamLeftChild == null){
				findExtreamLeftChild = rightChild;
			}
		}
		
		while(findExtreamLeftChild != null) {
			if(findExtreamLeftChild.getLeftNode() == null){
				break;
			}
			findExtreamLeftChild = findExtreamLeftChild.getLeftNode();
		}
		return findExtreamLeftChild;
	}
	
	
	private void unlinkNode(TreeDataObject<T> deleteNode,TreeDataObject<T> deleteNodeChildLink){
		deleteNode.setData(null);
		TreeDataObject<T> parentNode = deleteNode.getParentNode();
		if(parentNode != null){
			if(deleteNode.equals(parentNode.getLeftNode())){
				parentNode.setLeftNode(deleteNodeChildLink);
			} else {
				parentNode.setRightNode(deleteNodeChildLink);
			}
		} else {
			root = deleteNodeChildLink;
		}
		deleteNode.setParentNode(null);
		deleteNode.setLeftNode(null);
		deleteNode.setRightNode(null);
	}
	
	protected TreeDataObject<T> findParentNode(T data){
		TreeDataObject<T> element = root;
		TreeDataObject<T> parentNode = null;
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

	protected TreeDataObject<T> findNode(T data){
		TreeDataObject<T> element = root;
		TreeDataObject<T> parentNode = null;
		if(element != null){
			while(element != null) {
				if(data.compareTo(element.getData()) <= 0) {
					parentNode = element;
					if(data.equals(element.getData())){
						break;
					}
					element = getLeftChild(element);
				} else {
					parentNode = element;
					if(data.equals(element.getData())){
						break;
					}
					element = getRightChild(element);
				}
			}
		}
		return parentNode;
	}
	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	public TreeDataObject<T> getRoot() {
		return root;
	}
	
	public TreeDataObject<T> getLeftChild(TreeDataObject<T> node){
		return node.getLeftNode();
	}
	
	public TreeDataObject<T> getRightChild(TreeDataObject<T> node){
		return node.getRightNode();
	}
}
