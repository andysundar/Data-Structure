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

import com.andy.adt.DoubleLinkedRefDataObject;

public class DoubleLinkedList<T> implements Iterable<DoubleLinkedRefDataObject<T>> {
  private int length;
  public static final int INDEX_START = 0;
  private DoubleLinkedRefDataObject<T> startNode;
  private DoubleLinkedRefDataObject<T> lastNode;

  /**
   * Add element at front of list.
   * 
   * @param data
   */
  private void addFirst(T data) {
    DoubleLinkedRefDataObject<T> dataObject = new DoubleLinkedRefDataObject<T>();
    dataObject.setData(data);
    dataObject.setNextReference(startNode);
    startNode = dataObject;
    length++;
  }

  /**
   * Add element at last of list.
   * 
   * @param data
   */
  private void addLast(T data) {
    DoubleLinkedRefDataObject<T> dataObject = new DoubleLinkedRefDataObject<T>();
    dataObject.setData(data);
    dataObject.setPreviousReference(lastNode);
    lastNode.setNextReference(dataObject);
    lastNode = dataObject;
    length++;
  }

  /**
   * Added element sequentially to the list.
   * 
   * @param data
   * @return return true if added successfully else false.
   */
  public boolean add(T data) {
    boolean isOk = false;
    if (startNode == null) {
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
   * Add element to list at particular index.
   * 
   * @param index
   *          at which element will be added.
   * @param data
   * @return return true if added successfully else false.
   */
  public boolean addAt(int index, T data) {
    if (index < INDEX_START || index > length) {
      throw new IndexOutOfBoundsException(getOutOfBoundMessage());
    }
    boolean isOk = false;
    if (index == INDEX_START) {
      addFirst(data);
      isOk = true;
    } else if (index == length) {
      addLast(data);
      isOk = true;
    } else {
      DoubleLinkedRefDataObject<T> dataObject = new DoubleLinkedRefDataObject<T>();
      DoubleLinkedRefDataObject<T> iThDataObject = getIthNode(index);
      dataObject.setData(data);
      dataObject.setNextReference(iThDataObject);
      dataObject.setPreviousReference(iThDataObject.getPreviousReference());
      iThDataObject.setPreviousReference(dataObject);
      length++;
      isOk = true;
    }
    return isOk;
  }

  /**
   * Remove first element from list.
   */
  private void removeFirst() {
    DoubleLinkedRefDataObject<T> deleteNode = startNode;
    startNode = deleteNode.getNextReference();
    deleteNode.setData(null);
    deleteNode.setNextReference(null);
    length--;
  }

  /**
   * Remove last element from list.
   */
  private void removeLast() {
    DoubleLinkedRefDataObject<T> deleteNode = lastNode;
    lastNode = deleteNode.getPreviousReference();
    deleteNode.setData(null);
    deleteNode.setPreviousReference(null);
    length--;
  }

  /**
   * Remove the fist element matched.
   * 
   * @param data
   * @return return true if removed successfully else false.
   */
  public boolean remove(T data) {
    DoubleLinkedRefDataObject<T> findToBeDeleteNode = getStartNode();
    while ((findToBeDeleteNode != null) && (!isEqualData(data, findToBeDeleteNode.getData()))) {
      findToBeDeleteNode = findToBeDeleteNode.getNextReference();
    }

    return unLinkNode(findToBeDeleteNode);
  }

  /**
   * Unlink the node from the list chain.
   * 
   * @param node
   * @return return true if object found and removed successfully.
   */
  protected boolean unLinkNode(DoubleLinkedRefDataObject<T> node) {
    boolean isOk = false;
    if (node != null) {
      DoubleLinkedRefDataObject<T> beforeDeleteNode = node.getPreviousReference();
      DoubleLinkedRefDataObject<T> afterDeleteNode = node.getNextReference();
      if (beforeDeleteNode != null) {
        beforeDeleteNode.setNextReference(afterDeleteNode);
      } else {
        startNode = afterDeleteNode;
      }
      if (afterDeleteNode != null) {
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

  /**
   * Remove all element matching from the list.
   * 
   * @param data
   * @return return true if object found and removed successfully.
   */
  public boolean removeAll(T data) {
    boolean isOk = false;
    int halfLength = (int) Math.ceil(length / 2);
    DoubleLinkedRefDataObject<T> fromBeginingNode = startNode;
    DoubleLinkedRefDataObject<T> fromEndNode = lastNode;

    while (halfLength > -1) {
      if ((fromBeginingNode != null) && (isEqualData(data, fromBeginingNode.getData()))) {
        isOk = unLinkNode(fromBeginingNode);
        --halfLength;
      } else if (fromBeginingNode != null) {
        fromBeginingNode = fromBeginingNode.getNextReference();
      }

      if ((fromEndNode != null) && (isEqualData(data, fromEndNode.getData()))) {
        isOk = unLinkNode(fromEndNode);
        --halfLength;
      } else if (fromEndNode != null) {
        fromEndNode = fromEndNode.getPreviousReference();
      }

      halfLength--;
    }
    return isOk;
  }

  /**
   * 
   * @param index
   * @return return true if object found and removed successfully.
   */
  public boolean removeAt(int index) {
    if (index < INDEX_START || index >= length) {
      throw new IndexOutOfBoundsException(getOutOfBoundMessage());
    }
    boolean isOk = false;
    if (index == INDEX_START) {
      removeFirst();
      isOk = true;
    } else if (index == (length - 1)) {
      removeLast();
      isOk = true;
    } else {
      isOk = unLinkNode(getIthNode(index));
    }
    return isOk;
  }

  /**
   * 
   * @return return true if object found and removed successfully.
   */
  public boolean removeAll() {
    boolean isOk = false;
    DoubleLinkedRefDataObject<T> deleteObject = startNode;
    DoubleLinkedRefDataObject<T> tempObject = null;

    while (deleteObject != null) {
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

  /**
   * 
   * @param index
   * @return data content object from the list.
   */
  public T get(int index) {
    DoubleLinkedRefDataObject<T> dataObject = getIthNode(index);
    return (dataObject == null) ? null : dataObject.getData();
  }

  /**
   * 
   * @return length of the list.
   */
  public int size() {
    return length;
  }

  /**
   * 
   * @param index
   * @return data object from the list.
   */
  private DoubleLinkedRefDataObject<T> getIthNode(int index) {
    DoubleLinkedRefDataObject<T> dataObjectToBeReturned = startNode;
    if (index == (length - 1)) {
      dataObjectToBeReturned = lastNode;
    } else if (index != INDEX_START) {
      int middle = length / 2;
      if (index > middle) {
        dataObjectToBeReturned = lastNode;
        for (int tempIndex = (length - 2); tempIndex >= index; tempIndex--) {
          dataObjectToBeReturned = dataObjectToBeReturned.getPreviousReference();
        }
      } else {
        dataObjectToBeReturned = startNode;
        for (int tempIndex = (INDEX_START + 1); tempIndex <= index; tempIndex++) {
          dataObjectToBeReturned = dataObjectToBeReturned.getNextReference();
        }
      }

    }
    return dataObjectToBeReturned;
  }

  protected DoubleLinkedRefDataObject<T> getLastNode() {
    return lastNode;
  }

  protected DoubleLinkedRefDataObject<T> getStartNode() {
    return startNode;
  }

  public boolean isEmpty() {
    return (getStartNode() == null);
  }

  public Iterator<DoubleLinkedRefDataObject<T>> iterator() {
    return new LinkedListIterator();
  }

  /**
   * 
   * @param dataToBeRemoved
   * @param canBeDeletedNode
   * @return
   */
  protected boolean isEqualData(T dataOne, T dataTwo) {
    if (dataOne == null) {
      return (dataOne == dataTwo);
    }
    return (dataOne.equals(dataTwo));
  }

  /**
   * Return the index of first match object
   * @param data
   * @return
   */
  public int indexOf(T data){
    int index = -1;
    DoubleLinkedRefDataObject<T> node = startNode;

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
  
  private String getOutOfBoundMessage() {
    return "Index cannot be less than 0 and greater than or equal to " + length;
  }
 
  /**
   * Added Iterator feature
   * 
   * @author Anindya Bandopadhyay
   * 
   */
  private class LinkedListIterator implements Iterator<DoubleLinkedRefDataObject<T>> {
    
    private DoubleLinkedRefDataObject<T> currentNode = getStartNode();

    public boolean hasNext() {
      return (currentNode.getNextReference() != null);
    }

    public DoubleLinkedRefDataObject<T> next() {
      currentNode = currentNode.getNextReference();
      return currentNode;
    }

    public void remove() {
      throw new UnsupportedOperationException(
              "DoubleLinkedList doesn't support this remove feature. Please use the other remove APIs available.");
    }
  }
}
