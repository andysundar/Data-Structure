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
		int level = 0;
		BinaryTreeDataObject<T> element = root;
		BinaryTreeDataObject<T> parentNode = null;
		while(element != null) {
			level++;
			if(data.compareTo(element.getData()) <= 0) {
				parentNode = element;
				element = element.getLeftNode();
			} else {
				parentNode = element;
				element = element.getRightNode();
			}
		}
		
		if(data.compareTo(parentNode.getData()) <= 0){
			insertLeftChild(parentNode,data,level);
			isOk = true;
		} else {
			insertRightChild(parentNode,data,level);
			isOk = true;	
		}
		return isOk;
	}

	private void insertLeftChild(BinaryTreeDataObject<T> parentNode,T data,int level){
		BinaryTreeDataObject<T> childNode = new BinaryTreeDataObject<T>();
		childNode.setData(data);
		childNode.setLevel(level);
		parentNode.setLeftNode(childNode);
		childNode.setParentNode(parentNode);
	}
	
	private void insertRightChild(BinaryTreeDataObject<T> parentNode,T data,int level){
		BinaryTreeDataObject<T> childNode = new BinaryTreeDataObject<T>();
		childNode.setData(data);
		childNode.setLevel(level);
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
		boolean isOk = false;
		
		numberOfNodes--;
		return isOk;
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
