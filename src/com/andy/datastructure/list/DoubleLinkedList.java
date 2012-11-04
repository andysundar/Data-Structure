package com.andy.datastructure.list;

import com.andy.datastructure.DoubleLinkListDataObject;

public class DoubleLinkedList<T> {
	private int length;
	private DoubleLinkListDataObject<T> startNode;
	private DoubleLinkListDataObject<T> lastNode;
	
	private void addFirst(T data) {
		DoubleLinkListDataObject<T> dataObject = new DoubleLinkListDataObject<T>();
		dataObject.setData(data);
		dataObject.setNextReference(startNode);
		startNode = dataObject;
		length++;
	}
	
	private void addLast(T data){
		DoubleLinkListDataObject<T> dataObject = new DoubleLinkListDataObject<T>();
		dataObject.setData(data);
		dataObject.setPreviousReference(lastNode);
		lastNode = dataObject;
		length++;
	}
	
	public boolean add(T data) {
		boolean isOk = false;
		if(startNode == null) {
			addFirst(data);
			lastNode = startNode;
			isOk = true;
		} else {
			addLast(data);
			isOk = true;
		}
		return isOk;
	}
	
	public boolean addAt(T data,int index) {
		return false;
	}
	
	private void removeFirst(){
		
	}
	
	private void removeLast(){
		
	}
	
	public boolean remove(T data){
		return false;
	}

	public boolean removeAt(int index){
		return false;
	}

	public boolean removeAll(T data){
		return false;
	}
	
	public boolean removeAll() {
		return false;
	}

	public T get(int T) {
		return null;
	}
	
	public int size() {
		return length;
	}
}
