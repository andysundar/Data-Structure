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
		if(data == null) {
			throw new IllegalArgumentException("Data to be added cannot be null.");
		}
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
		if(data == null) {
			throw new IllegalArgumentException("Data to be added cannot be null.");
		}
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
		if(data == null) {
			throw new IllegalArgumentException("Data to be removed cannot be null.");
		}

		DoubleLinkListDataObject<T> findToBeDeleteNode = startNode;
		while((findToBeDeleteNode != null) && (!data.equals(findToBeDeleteNode.getData()))){
			findToBeDeleteNode = findToBeDeleteNode.getNextReference();
		}
			
		return unLinkNode(findToBeDeleteNode);
	}

	private boolean unLinkNode(DoubleLinkListDataObject<T> node) {
		boolean isOk = false;
		if(node != null) {
			DoubleLinkListDataObject<T> beforeDeleteNode = node.getPreviousReference();
			DoubleLinkListDataObject<T> afterDeleteNode = node.getNextReference();
			if(beforeDeleteNode != null) {
				beforeDeleteNode.setNextReference(afterDeleteNode);
			} else {
				startNode = afterDeleteNode;
			}
			if(afterDeleteNode != null) {
				afterDeleteNode.setPreviousReference(beforeDeleteNode);
			} else {
				lastNode = beforeDeleteNode;
			}
			node.setData(null);
			node.setNextReference(null);
			node.setPreviousReference(null);
			length--;
			isOk = true;
		}
		return isOk;
	}
	
	public boolean removeAll(T data){
	
		if(data == null) {
			throw new IllegalArgumentException("Data to be removed cannot be null.");
		}
		boolean isOk = false;
		int halfLength = (int)Math.ceil(length/2);
		DoubleLinkListDataObject<T> fromBeginingNode = startNode;
		DoubleLinkListDataObject<T> fromEndNode = lastNode;
				
		while(halfLength > -1) {
			if((fromBeginingNode != null) && (data.equals(fromBeginingNode.getData()))) {
				isOk = unLinkNode(fromBeginingNode);
				--halfLength;
			} else if(fromBeginingNode != null){
				fromBeginingNode = fromBeginingNode.getNextReference();
			}
			
			if((fromEndNode != null) && (data.equals(fromEndNode.getData()))) {
				isOk = unLinkNode(fromEndNode);
				--halfLength;
			} else if(fromEndNode != null){
				fromEndNode = fromEndNode.getPreviousReference();
			}

			halfLength--;
		}
		return isOk;
	}
	
	
	
	public boolean removeAt(int index){
		if(index < INDEX_START || index >= length) {
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
			isOk = unLinkNode(getIthNode(index));
		}
		return isOk;
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
		DoubleLinkListDataObject<T> dataObject = getIthNode(index);
		return (dataObject == null)?null:dataObject.getData();
	}
	
	public int size() {
		return length;
	}
	
	private DoubleLinkListDataObject<T> getIthNode(int index) {
		DoubleLinkListDataObject<T> dataObjectToBeReturned = startNode;
		if(index == (length - 1)) {
			dataObjectToBeReturned = lastNode;
		} else if(index != INDEX_START) {
			int middle = length/2;
			if(index > middle) {
				dataObjectToBeReturned = lastNode;
				for(int tempIndex = (length - 2); tempIndex >= index; tempIndex--) {
					dataObjectToBeReturned = dataObjectToBeReturned.getPreviousReference();
				}
			} else {
				dataObjectToBeReturned = startNode;
				for(int tempIndex = (INDEX_START + 1); tempIndex <= index; tempIndex++) {
					dataObjectToBeReturned = dataObjectToBeReturned.getNextReference();
				}
			}
			
		}
		
		return dataObjectToBeReturned;
	}
}
