package com.andy.datastructure.list;

import com.andy.datastructure.DataObject;

public class LinkedList<T> {
	private int length; 
	private static final int INDEX_START = 0;
	private DataObject<T> startNode;
	private DataObject<T> lastNode;
	
	/**
	 * addFirst add object at the beginning of the list
	 * @param data
	 */
	private void addFirst(T data) {
		DataObject<T> node  = new DataObject<T>();
		node.setData(data);
		node.setNextReference(startNode);
		startNode = node;
		length++;
	}

	/**
	 * addLast add object at the end of the list
	 * @param data
	 */
	private void addLast(T data) {
		DataObject<T> node  = new DataObject<T>();
		lastNode.setNextReference(node);
		node.setData(data);
		lastNode = node;
		length++;
	}
	
	/**
	 * This method return start node of the list
	 * @return DataObject<T> object
	 */
	public DataObject<T> getStartNode() {
		return startNode;
	}
	
	/**
	 *  This method return last node of the list
	 * @return DataObject<T> object
	 */
	public DataObject<T> getLastNode() {
		return lastNode;
	}
	
/**
 * Add element to list 
 * @param data node to be added to list
 * @return if node successfully add then true else false
 */

 public boolean add(T data) {
	 if(data == null) {
		 throw new IllegalArgumentException("Parameter cannot be null.");
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
 
 /**
  * Add element to list at  particular index
  * @param data node to be added to list
  * @param index node to inserted at
  * @return if node successfully add then true else false
  */
 public boolean addAt(T data,int index) {
	 boolean isOk = false;
	 if(data == null) {
		 throw new IllegalArgumentException("Data parameter cannot be null.");
	 }
	 if(index < INDEX_START || index > length) {
		 throw new IndexOutOfBoundsException("Index cannot be less than 0 and greater than or equal to "+length);
	 }
	 
	 if(index == INDEX_START){
		 addFirst(data);
		 if(index == (length - 1)){
			 lastNode = startNode;
		 }
		 isOk = true;
	 } else if(index == length) {
		 addLast(data);
		 isOk = true;
	 } else {
		 DataObject<T> ithNode = getIthNode((index - 1));
		 DataObject<T> node = new DataObject<T>();
		 node.setData(data);
		 node.setNextReference(ithNode.getNextReference());
		 ithNode.setNextReference(node);
		 isOk = true;
		 length ++;
	 }
	 
	 return isOk;
 }
 
  private void removeFirst() {
	 DataObject<T> nextStartNode = startNode.getNextReference();
	 startNode.setData(null);
	 startNode.setNextReference(null);
	 startNode = nextStartNode; 
	 length--;
  }
 /**
  * Remove element from list at particular index
  * @param index node to remove at
  * @return if node successfully remove then true else false
  */
 public boolean removeAt(int index) {
	 boolean isOk = false;
	 if(index < INDEX_START || index >= length) {
		 throw new IndexOutOfBoundsException("Index cannot be less than 0 and greater than or equal to "+length);
	 }
	 if(index == INDEX_START){
		 removeFirst();
		 isOk = true;
	 } else {
		 DataObject<T> beforeDeleteNode = getIthNode((index - 1));
		 DataObject<T> deleteNode = beforeDeleteNode.getNextReference();
		 if(deleteNode.getNextReference() == null){
			 lastNode = beforeDeleteNode;
		 }
		 beforeDeleteNode.setNextReference(deleteNode.getNextReference());
		 deleteNode.setData(null);
		 deleteNode.setNextReference(null);
		 isOk = true;
		 length--;
	 }
	
	 return isOk;
 }
 
 /**
  * Remove element from list if parameter matched 
  * @param dataToBeRemoved to be removed if matched
  * @return if node successfully remove then true else false
  */
 
 public boolean remove(T dataToBeRemoved) {
	 boolean isOk = false;
	 if(dataToBeRemoved == null) {
		 throw new IllegalArgumentException("Parameter cannot be null.");
	 }
	 
	
	 if(dataToBeRemoved.equals(startNode.getData())) {
		 removeFirst();
		 isOk = true;
	 } else {
		 DataObject<T> canBeDeletedNode = startNode.getNextReference();
		 DataObject<T> nextLinkingNode = (canBeDeletedNode== null)?null:canBeDeletedNode.getNextReference();
		 DataObject<T> beforeDeleteNode = startNode;
		 
		 for(int index = (INDEX_START + 1); index < length ; index++ ) {
			 if(dataToBeRemoved.equals(canBeDeletedNode.getData())) {
				 beforeDeleteNode.setNextReference(nextLinkingNode);
				 if(canBeDeletedNode.getNextReference() == null) {
					 lastNode = beforeDeleteNode;
				 }
				 canBeDeletedNode.setData(null);
				 canBeDeletedNode.setNextReference(null);
				 isOk = true;
				 break;
			 } else {
				 canBeDeletedNode = canBeDeletedNode.getNextReference();
				 nextLinkingNode = (canBeDeletedNode== null)?null:canBeDeletedNode.getNextReference();
				 beforeDeleteNode = beforeDeleteNode.getNextReference();
			 }
		 }
		 
		 if(isOk) {
			 length--;
		 }
	 
	 }
		
	 return isOk;
 }
 
 /**
  * Remove all elements from list
  * @return if node successfully remove then true else false
  */
 public boolean removeAll() {
	 boolean isOk = true;
	 for(int index = length; index > INDEX_START ; index--){
		 isOk &= removeAt(INDEX_START);
	 }
	 return isOk;
 }
 
 /**
  * get <T> type object at particular index
  * @param index
  * @return <T> type object from list
  */
 public T get(int index) {
	 DataObject<T> node = getIthNode(index);
	 return node.getData();
 }
 
 /**
  * getIthNode method return i th node object
  * @param index
  * @return the i th node from the list
  */
 
 private DataObject<T> getIthNode(int index) {
	 if(index < INDEX_START || index >= length) {
		 throw new IndexOutOfBoundsException("Index cannot be less than 0 and greater than or equal to "+length);
	 }
	 DataObject<T> node = startNode;
	 
	 if(index != INDEX_START && index < (length - 1)) {
		 int tempIndex = INDEX_START;
		 while((node != null) && (tempIndex < index)) {
			 node = node.getNextReference();
			 tempIndex++;
		 }
	 } else if(index == (length - 1)) {
		 node = lastNode;
	 }
	 return node;
 }
 
 /**
  * size method return length of the list
  * @return length of the list
  */
 public int size() {
	 return length;
 }
}
