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

package com.andy.ds.linear;

import java.util.Iterator;

import com.andy.adt.SingleLinkedRefDataObject;

public class LinkedList<T> implements Iterable<SingleLinkedRefDataObject<T>>{
	private int length; 
	private static final int INDEX_START = 0;
	private SingleLinkedRefDataObject<T> startNode;
	private SingleLinkedRefDataObject<T> lastNode;
	
	/**
	 * addFirst add object at the beginning of the list
	 * @param data
	 */
	private void addFirst(T data) {
		SingleLinkedRefDataObject<T> node  = new SingleLinkedRefDataObject<T>();
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
		SingleLinkedRefDataObject<T> node  = new SingleLinkedRefDataObject<T>();
		lastNode.setNextReference(node);
		node.setData(data);
		lastNode = node;
		length++;
	}
	
	/**
	 * This method return start node of the list
	 * @return DataObject<T> object
	 */
	public SingleLinkedRefDataObject<T> getStartNode() {
		return startNode;
	}
	
	/**
	 *  This method return last node of the list
	 * @return DataObject<T> object
	 */
	public SingleLinkedRefDataObject<T> getLastNode() {
		return lastNode;
	}
	
/**
 * Add element to list 
 * @param data node to be added to list
 * @return if node successfully add then true else false
 */

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
 
 /**
  * Add element to list at  particular index
 * @param index node to inserted at
 * @param data node to be added to list
  * @return if node successfully add then true else false
  */
 public boolean addAt(int index,T data) {
	 boolean isOk = false;
	 if(isIndexOutOfBound(index)) {
		 throw new IndexOutOfBoundsException(getOutOfBoundMessage());
	 }
	 
	 if(index == INDEX_START){
		 addFirst(data);
     lastNode = startNode;
		 isOk = true;
	 } else if(index == length) {
		 addLast(data);
		 isOk = true;
	 } else {
		 SingleLinkedRefDataObject<T> ithNode = getIthNode((index - 1));
		 SingleLinkedRefDataObject<T> node = new SingleLinkedRefDataObject<T>();
		 node.setData(data);
		 node.setNextReference(ithNode.getNextReference());
		 ithNode.setNextReference(node);
		 isOk = true;
		 length ++;
	 }
	 
	 return isOk;
 }

  private boolean isIndexOutOfBound(int index) {
    if (startNode == null) {
      index = -1;
    }
    return (index < INDEX_START || index > length);
  }
 
  private void removeFirst() {
	 SingleLinkedRefDataObject<T> nextStartNode = startNode.getNextReference();
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
	 if(isIndexOutOfBound(index)) {
		 throw new IndexOutOfBoundsException(getOutOfBoundMessage());
	 }
	 if(index == INDEX_START){
		 removeFirst();
		 isOk = true;
	 } else {
		 SingleLinkedRefDataObject<T> beforeDeleteNode = getIthNode((index - 1));
		 SingleLinkedRefDataObject<T> deleteNode = beforeDeleteNode.getNextReference();
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
	return remove(dataToBeRemoved, false);
 }
 
 /**
  * 
  * @param dataToBeRemoved
  * @param removeAll
  * @return
  */
 private boolean remove(T dataToBeRemoved,boolean removeAll) {
	 boolean isOk = false;
	 if(startNode == null){
	   return isOk;
	 }
	 boolean enableBreaker = !removeAll;
	 if(isEqualData(dataToBeRemoved,startNode.getData())) {
		 removeFirst();
		 isOk = true;
	 } else {
		 removeAll =true;
	 }
	 
	 if(removeAll){
		 SingleLinkedRefDataObject<T> canBeDeletedNode = startNode.getNextReference();
		 SingleLinkedRefDataObject<T> nextLinkingNode = (canBeDeletedNode== null)?null:canBeDeletedNode.getNextReference();
		 SingleLinkedRefDataObject<T> beforeDeleteNode = startNode;
		 
		 for(int index = (INDEX_START + 1); index < length ; index++ ) {
			 if(isEqualData(dataToBeRemoved,canBeDeletedNode.getData())) {
				 beforeDeleteNode.setNextReference(nextLinkingNode);
				 if(canBeDeletedNode.getNextReference() == null) {
					 lastNode = beforeDeleteNode;
				 }
				 canBeDeletedNode.setData(null);
				 canBeDeletedNode.setNextReference(null);
				 
				 canBeDeletedNode = nextLinkingNode;
				 nextLinkingNode = (canBeDeletedNode== null)?null:canBeDeletedNode.getNextReference();
				 
				 isOk = true;
				 length--;
				 if(enableBreaker) {
					 break;
				 }
			 } else {
				 canBeDeletedNode = canBeDeletedNode.getNextReference();
				 nextLinkingNode = (canBeDeletedNode== null)?null:canBeDeletedNode.getNextReference();
				 beforeDeleteNode = beforeDeleteNode.getNextReference();
				 if(canBeDeletedNode == null){
					 break;
				 }
			 }
		 }
	 }
	 
	 return isOk;
 }

 /**
  * 
  * @param dataOne
  * @param dataTwo
  * @return
  */
private boolean isEqualData(T dataOne, T dataTwo) {
  if(dataOne == null){
    return (dataOne == dataTwo);
  }
  return (dataOne.equals(dataTwo));
}
 
 /**
  * 
  * @param dataToBeRemoved
  * @return
  */
 public boolean removeAll(T dataToBeRemoved){
	return remove(dataToBeRemoved, true);
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
	 SingleLinkedRefDataObject<T> node = getIthNode(index);
	 return node.getData();
 }
 
 /**
  * getIthNode method return i th node object
  * @param index
  * @return the i th node from the list
  */
 
 private SingleLinkedRefDataObject<T> getIthNode(int index) {
	 if(isIndexOutOfBound(index)) {
		 throw new IndexOutOfBoundsException(getOutOfBoundMessage());
	 }
	 SingleLinkedRefDataObject<T> node = startNode;
	 
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

  private String getOutOfBoundMessage() {
    return "Index cannot be less than 0 and greater than or equal to " + length;
  }
 
 /**
  * size method return length of the list
  * @return length of the list
  */
 public int size() {
	 return length;
 }
 
 /**
  * 
  * @return true if list is empty else false
  */
 public boolean isEmpty(){
	 return (getStartNode() == null);
 }
 
 /**
  * Return the index of first match object
  * @param data
  * @return
  */
 public int indexOf(T data){
   int index = -1;
   SingleLinkedRefDataObject<T> node = startNode;

    while(node != null) {
      if(isEqualData(data, node.getData())){
        index++;
        break;
      }
      node = node.getNextReference();
      index++;
    }
    
   return index;
 }
 
 /**
  * Return true if data is found otherwise false.
  * @param data
  * @return true if data is found otherwise false.
  */
 public boolean contains(T data){
   return (indexOf(data) != -1);
 }
 /**
  * Added iterator feature so that user can iterate thorough the list easily.
  */

public Iterator<SingleLinkedRefDataObject<T>> iterator() {
	return new LinkedListIterator();
}

/**
 * Added Iterator feature
 * @author Anindya Bandopadhyay
 *
 */
	private class LinkedListIterator implements Iterator<SingleLinkedRefDataObject<T>> {
	
		private SingleLinkedRefDataObject<T> currentNode = getStartNode();
		public boolean hasNext() {
			return (currentNode.getNextReference() != null);
		}
	
		public SingleLinkedRefDataObject<T> next() {
			currentNode = currentNode.getNextReference();
			return currentNode;
		}
	
		public void remove() {
			throw new UnsupportedOperationException("LinkedList doesn't support this remove feature. Please use the other remove APIs available.");
		}
	}
}
