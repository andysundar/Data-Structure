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
			TreeDataObject<T> parentNode = findParentNodeForNewNode(getRoot(),data);
			node = insertChild(parentNode,data);
		}
		if(node != null){
			numberOfNodes++;
		}
		return node;
	}
	
	private TreeDataObject<T> insertChild(TreeDataObject<T> parentNode,T data) {
		TreeDataObject<T> child = null;
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
		return childNode;
	}
	
	private TreeDataObject<T> insertRightChild(TreeDataObject<T> parentNode,T data){
		TreeDataObject<T> childNode = new TreeDataObject<T>();
		childNode.setData(data);
		parentNode.setRightChildNode(childNode);
		return childNode;
	}

	private TreeDataObject<T> insertRoot(T data) {
		TreeDataObject<T> rootNodeObject = new TreeDataObject<T>();
		rootNodeObject.setData(data);
		return rootNodeObject;
	}

	public TreeDataObject<T> deleteNode(T data) {
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
				successorNode = getMinValueNode(node.getRightChildNode());
				//updateParentChildWithNodeChild(successorNode);
				successorNode.setParentNode(node.getParentNode());
				if(!successorNode.equals(node.getRightChildNode())){
					successorNode.setRightChildNode(node.getRightChildNode());
				}
				successorNode.setLeftChildNode(node.getLeftChildNode());
				unlinkNode(node, successorNode);
			} else {
				if(node.getLeftChildNode() != null) {
					successorNode = getMaxValueNode(node.getLeftChildNode());
					if(successorNode.getParentNode() != null){
						successorNode.getParentNode().setRightChildNode(successorNode.getLeftChildNode());
					}
				} else if(node.getRightChildNode() != null) {
					successorNode = getMinValueNode(node.getRightChildNode());
					if(successorNode.getParentNode() != null){
						successorNode.getParentNode().setLeftChildNode(successorNode.getRightChildNode());
					}
				}
				successorNode.setParentNode(node.getParentNode());
				if(!successorNode.equals(node.getRightChildNode())){
					successorNode.setRightChildNode(node.getRightChildNode());
				}
				if(!successorNode.equals(node.getLeftChildNode())){
					successorNode.setLeftChildNode(node.getLeftChildNode());
				}
				unlinkNode(node, successorNode);
			}
			numberOfNodes--;
		}
		return successorNode;
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
			setRoot(deleteNodeChildLink);
		}
		deleteNode.setParentNode(null);
		deleteNode.setLeftChildNode(null);
		deleteNode.setRightChildNode(null);
	}
	
	private TreeDataObject<T> findParentNodeForNewNode(TreeDataObject<T> element,T data){
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
		while(element != null && data != null) {
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
	
	public void setRoot(TreeDataObject<T> root) {
		this.root = root;
	}
	
	public TreeDataObject<T> getLeftChild(TreeDataObject<T> node){
		return node.getLeftChildNode();
	}
	
	public TreeDataObject<T> getRightChild(TreeDataObject<T> node){
		return node.getRightChildNode();
	}
	
	public TreeDataObject<T> getMaxValueNode(TreeDataObject<T> node) {
		while(node != null){
			if(getRightChild(node) == null) {
				break;
			} else {
				node = getRightChild(node);
			}
		}
		return node;
	}
	
	public TreeDataObject<T> getMinValueNode(TreeDataObject<T> node) {
		while(node != null){
			if(getLeftChild(node) == null){
				break;
			} else {
				node = getLeftChild(node);
			}
			
		}
		return node;
	}
}
