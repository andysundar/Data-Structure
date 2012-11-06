package com.andy.datastructure.list;

import com.andy.datastructure.DoubleLinkListDataObject;

public class DoubleLinkedList<T> {
	private int length;
	public static final int INDEX_START = 0;
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
		lastNode.setNextReference(dataObject);
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
		if(index < INDEX_START || index > length){
			throw new IndexOutOfBoundsException("Index cannot be less than 0 and greater than or equal to "+length);
		}
		boolean isOk = false;
		if(index == INDEX_START){
			addFirst(data);
			isOk = true;
		} else if(index == length) {
			addLast(data);
			isOk = true;
		} else {
			DoubleLinkListDataObject<T> dataObject = new DoubleLinkListDataObject<T>();
			DoubleLinkListDataObject<T> iThDataObject = getIthNode(index);
			dataObject.setData(data);
			dataObject.setNextReference(iThDataObject);
			dataObject.setPreviousReference(iThDataObject.getPreviousReference());
			iThDataObject.setPreviousReference(dataObject);
			length++;
			isOk = true;
		}
		return isOk;
	}
	
	private void removeFirst(){
		DoubleLinkListDataObject<T> deleteNode = startNode;
		startNode = deleteNode.getNextReference();
		deleteNode.setData(null);
		deleteNode.setNextReference(null);
		length--;
	}
	
	private void removeLast(){
		DoubleLinkListDataObject<T> deleteNode = lastNode;
		lastNode = deleteNode.getPreviousReference();
		deleteNode.setData(null);
		deleteNode.setPreviousReference(null);
		length--;
	}
	
	public boolean remove(T data){
		return false;
	}

	public boolean removeAt(int index){
		if(index < INDEX_START || index >= length){
			throw new IndexOutOfBoundsException("Index cannot be less than 0 and greater than or equal to "+length);
		}
		boolean isOk = false;
		if(index == INDEX_START) {
			removeFirst();
			isOk = true;
		}else if(index == (length - 1)) {
			removeLast();
			isOk = true;
		} else {
			DoubleLinkListDataObject<T> dataObject = getIthNode(index);
			dataObject.getNextReference().setPreviousReference(dataObject.getPreviousReference());
			dataObject.getPreviousReference().setNextReference(dataObject.getNextReference());
			dataObject.setData(null);
			dataObject.setNextReference(null);
			dataObject.setPreviousReference(null);
			length--;
			isOk = true;
		}
		return isOk;
	}

	public boolean removeAll(T data){
		return false;
	}
	
	public boolean removeAll() {
		boolean isOk = false;
		DoubleLinkListDataObject<T> deleteObject = startNode;
		DoubleLinkListDataObject<T> tempObject = null;
		
		while(deleteObject != null){
			deleteObject.setData(null);
			deleteObject.setPreviousReference(null);
			tempObject = deleteObject.getNextReference();
			deleteObject.setNextReference(null);
			deleteObject = tempObject;
			isOk = true;
			length--;
		}
		return isOk;
	}

	public T get(int index) {
		if(index < INDEX_START || index > length){
			throw new IndexOutOfBoundsException("Index cannot be less than 0 and greater than or equal to "+length);
		}
		return getIthNode(index).getData();
	}
	
	public int size() {
		return length;
	}
	
	private DoubleLinkListDataObject<T> getIthNode(int index) {
		DoubleLinkListDataObject<T> dataObjectToBeReturned = startNode;
		if(index == (length - 1)) {
			dataObjectToBeReturned = lastNode;
		} else if(index != INDEX_START) {
			for(int tempIndex = (INDEX_START + 1); tempIndex <= index; tempIndex++) {
				dataObjectToBeReturned = dataObjectToBeReturned.getNextReference();
			}
		}
		
		return dataObjectToBeReturned;
	}
}
