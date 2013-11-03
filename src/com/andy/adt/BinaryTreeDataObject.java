package com.andy.adt;

public class BinaryTreeDataObject<T extends Comparable<T>> {
	private T data;
	private BinaryTreeDataObject<T> leftNode;
	private BinaryTreeDataObject<T> rightNode;
	private BinaryTreeDataObject<T> parentNode;
	
	public T getData() {
		return data;
	}
	public BinaryTreeDataObject<T> getLeftNode() {
		return leftNode;
	}
	public BinaryTreeDataObject<T> getRightNode() {
		return rightNode;
	}
	public void setData(T data) {
		this.data = data;
	}
	public void setLeftNode(BinaryTreeDataObject<T> leftNode) {
		this.leftNode = leftNode;
	}
	public void setRightNode(BinaryTreeDataObject<T> rightNode) {
		this.rightNode = rightNode;
	}
	
	public BinaryTreeDataObject<T> getParentNode() {
		return parentNode;
	}
	public void setParentNode(BinaryTreeDataObject<T> parentNode) {
		this.parentNode = parentNode;
	}
	
}
