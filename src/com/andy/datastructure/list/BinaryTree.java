package com.andy.datastructure.list;

import com.andy.datastructure.BinaryTreeDataObject;

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
		BinaryTreeDataObject<T> parentNode = findNodePosition(data);

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
		childNode.setLevel((parentNode.getLevel()+ 1));
		parentNode.setLeftNode(childNode);
		childNode.setParentNode(parentNode);
	}
	
	private void insertRightChild(BinaryTreeDataObject<T> parentNode,T data){
		BinaryTreeDataObject<T> childNode = new BinaryTreeDataObject<T>();
		childNode.setData(data);
		childNode.setLevel((parentNode.getLevel()+ 1));
		parentNode.setRightNode(childNode);
		childNode.setParentNode(parentNode);
	}

	private boolean insertRoot(T data) {
		BinaryTreeDataObject<T> rootNodeObject = new BinaryTreeDataObject<T>();
		rootNodeObject.setLevel(0);
		rootNodeObject.setData(data);
		root = rootNodeObject;
		return true;
	}

	public boolean deleteNode(T data) {
		if(data == null){
			throw new IllegalArgumentException("Null cannot be deleted.");
		}
		BinaryTreeDataObject<T> node = findNodePosition(data);
		boolean isOk = ((node != null) && (data.equals(node.getData())));
		BinaryTreeDataObject<T> childNode = null;
		if(isOk){
			if((node.getLeftNode() == null) && (node.getRightNode() == null)) {
				unlinkNode(node,null);
			} else if((node.getLeftNode() != null) && (node.getRightNode() != null)) {
				childNode = inorderSuccessor(node);
				childNode.setParentNode(node.getParentNode());
				childNode.setLevel(node.getLevel());
				childNode.setRightNode(node.getRightNode());
				childNode.setLeftNode(node.getLeftNode());
				unlinkNode(node, childNode);
			} else {
				if(node.getLeftNode() != null) {
					childNode = node.getLeftNode();
					node.setLeftNode(null);
				} else {
					childNode = node.getRightNode();
					node.setRightNode(null);
				}
				childNode.setParentNode(node.getParentNode());
				unlinkNode(node, childNode);
			}
			numberOfNodes--;
		}
		return isOk;
	}
	
	private BinaryTreeDataObject<T> inorderSuccessor(BinaryTreeDataObject<T> deleteNode) {
		
		return null;
	}
	
	private void unlinkNode(BinaryTreeDataObject<T> deleteNode,BinaryTreeDataObject<T> parentNodeChildLink){
		deleteNode.setData(null);
		BinaryTreeDataObject<T> parentNode = deleteNode.getParentNode();
		if(parentNode != null){
			if(deleteNode.equals(parentNode.getLeftNode())){
				parentNode.setLeftNode(parentNodeChildLink);
			} else {
				parentNode.setRightNode(parentNodeChildLink);
			}
		}
		deleteNode.setLevel(0);
		deleteNode.setParentNode(null);
		deleteNode.setLeftNode(null);
		deleteNode.setRightNode(null);
	}
	protected BinaryTreeDataObject<T> findNodePosition(T data){
		BinaryTreeDataObject<T> element = root;
		BinaryTreeDataObject<T> parentNode = null;
		if(element != null){
			while(element != null) {
				if(data.compareTo(element.getData()) <= 0) {
					parentNode = element;
					element = element.getLeftNode();
				} else {
					parentNode = element;
					element = element.getRightNode();
				}
			}
		}
		return parentNode;
	}
	
	public T[] visitPreOrder() {
		return null;
	}
	
	public T[] visitInOrder() {
		return null;
	}
	
	public T[] visitPostOrder() {
		return null;
	}

	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	public BinaryTreeDataObject<T> getRoot() {
		return root;
	}
}
