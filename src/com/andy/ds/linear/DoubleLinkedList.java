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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collection;

import com.andy.adt.DoubleLinkedRefDataObject;
import com.andy.ds.linear.contract.SimpleList;

public class DoubleLinkedList<T> extends AbstractSimpleList<T> {
  
  private static final long serialVersionUID = -1148710922805563919L;

  public DoubleLinkedList(){
    
  }
  
  public DoubleLinkedList(SimpleList<? extends T> list){
    this();
    addAll(list);
  }
  
  public DoubleLinkedList(Collection<? extends T> collection){
    this();
    addAll(collection);
  }
  
  public DoubleLinkedList(T[] array){
    this();
    addAll(array);
  }

  @Override
  protected DoubleLinkedRefDataObject<T> addFirst(T data) {
    DoubleLinkedRefDataObject<T> node = new DoubleLinkedRefDataObject<>();
    node.setData(data);
    node.setNextReference(getStartNode());
    if(getStartNode() != null) {
      getStartNode().setPreviousReference(node);
    }
    if(getLastNode() == null){
      setLastNode(node);
    }
    setStartNode(node);
    setSize((getSize() + 1));
    return node;
  }
  /**
   * Add element at last of list.
   * 
   * @param data
   */
  protected void addLast(T data) {
    DoubleLinkedRefDataObject<T> dataObject = new DoubleLinkedRefDataObject<>();
    dataObject.setData(data);
    dataObject.setPreviousReference(getLastNode());
    getLastNode().setNextReference(dataObject);
    setLastNode(dataObject);
    setSize((getSize() + 1));
  }

  /**
   * Added element sequentially to the list.
   * 
   * @param data
   * @return return true if added successfully else false.
   */
  public boolean add(T data) {
    boolean isOk = false;
    if (getStartNode() == null) {
      addFirst(data);
      setLastNode(getStartNode());
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
   checkIndexBoundForPosition(index);
   
    boolean isOk = false;
    if (index == INDEX_START) {
      addFirst(data);
      isOk = true;
    } else if (index == getSize()) {
      addLast(data);
      isOk = true;
    } else {
      DoubleLinkedRefDataObject<T> dataObject = new DoubleLinkedRefDataObject<>();
      DoubleLinkedRefDataObject<T> iThDataObject = getIthNode(index);
      dataObject.setData(data);
      dataObject.setNextReference(iThDataObject);
      dataObject.setPreviousReference(iThDataObject.getPreviousReference());
      iThDataObject.setPreviousReference(dataObject);
      setSize(getSize() + 1);
      isOk = true;
    }
    return isOk;
  }

  /**
   * Remove first element from list.
   */
  private void removeFirst() {
    DoubleLinkedRefDataObject<T> deleteNode = getStartNode();
    setStartNode(deleteNode.getNextReference());
    deleteNode.setData(null);
    deleteNode.setNextReference(null);
    setSize((getSize() - 1));
  }

  /**
   * Remove last element from list.
   */
  private void removeLast() {
    DoubleLinkedRefDataObject<T> deleteNode = getLastNode();
    setLastNode(deleteNode.getPreviousReference());
    deleteNode.setData(null);
    deleteNode.setPreviousReference(null);
    setSize((getSize() - 1));
  }

  /**
   * Remove the fist element matched.
   * 
   * @param data
   * @return return true if removed successfully else false.
   */
  public boolean removeFirstOccurrence(T data) {
    DoubleLinkedRefDataObject<T> findToBeDeleteNode = getStartNode();
    while ((findToBeDeleteNode != null) && (!isEqualData(data, findToBeDeleteNode.getData()))) {
      findToBeDeleteNode = findToBeDeleteNode.getNextReference();
    }

    return unLinkNode(findToBeDeleteNode);
  }


  /**
   * Remove all element matching from the list.
   * 
   * @param data
   * @return return true if object found and removed successfully.
   */
  public boolean removeAll(T data) {
    boolean isOk = false;
    int halfLength = (int) Math.ceil(getSize() / 2);
    DoubleLinkedRefDataObject<T> fromBeginingNode = getStartNode();
    DoubleLinkedRefDataObject<T> fromEndNode = getLastNode();

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
    checkIndexBoundForElement(index);
    
    boolean isOk = false;
    if (index == INDEX_START) {
      removeFirst();
      isOk = true;
    } else if (index == (getSize() - 1)) {
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
    DoubleLinkedRefDataObject<T> deleteObject = getStartNode();
    DoubleLinkedRefDataObject<T> tempObject = null;

    while (deleteObject != null) {
      deleteObject.setData(null);
      deleteObject.setPreviousReference(null);
      tempObject = deleteObject.getNextReference();
      deleteObject.setNextReference(null);
      deleteObject = tempObject;
      isOk = true;
      setSize((getSize() - 1));
    }
    return isOk;
  }

  /**
   * 
   * @param index
   * @return data content object from the list.
   */
  public T get(int index) {
    checkIndexBoundForElement(index);
    DoubleLinkedRefDataObject<T> dataObject = getIthNode(index);
    return dataObject.getData();
  }

  /**
   * 
   * @param index
   * @return data object from the list.
   */
  private DoubleLinkedRefDataObject<T> getIthNode(int index) {
    DoubleLinkedRefDataObject<T> dataObjectToBeReturned = getStartNode();
    if (index == (getSize() - 1)) {
      dataObjectToBeReturned = getLastNode();
    } else if (index != INDEX_START) {
      int middle = getSize() / 2;
      if (index > middle) {
        dataObjectToBeReturned = getLastNode();
        for (int tempIndex = (getSize() - 2); tempIndex >= index; tempIndex--) {
          dataObjectToBeReturned = dataObjectToBeReturned.getPreviousReference();
        }
      } else {
        dataObjectToBeReturned = getStartNode();
        for (int tempIndex = (INDEX_START + 1); tempIndex <= index; tempIndex++) {
          dataObjectToBeReturned = dataObjectToBeReturned.getNextReference();
        }
      }

    }
    return dataObjectToBeReturned;
  }

  public boolean addAll(int index, SimpleList<? extends T> list) {
    checkIndexBoundForPosition(index);
    boolean isOk = false;

    DoubleLinkedRefDataObject<T> subListStart = null;
    DoubleLinkedRefDataObject<T> subListLast = null;
    int subListsize = 0;
    for (T data : list) {
      DoubleLinkedRefDataObject<T> node = new DoubleLinkedRefDataObject<>();
      node.setData(data);
      if (subListStart == null) {
        subListStart = node;
        subListLast = node;
      } else {
        node.setPreviousReference(subListLast);
        subListLast.setNextReference(node);
        subListLast = node;
      }
      subListsize++;
    }
    adjustWithCurrentList(index, subListStart, subListLast, subListsize);
    isOk = true;
    return isOk;
  }
  
  public boolean addAll(int index, Collection<? extends T> collection) {
    checkIndexBoundForPosition(index);
    boolean isOk = false;

    DoubleLinkedRefDataObject<T> subListStart = null;
    DoubleLinkedRefDataObject<T> subListLast = null;
    int subListsize = 0;
    for (T data : collection) {
      DoubleLinkedRefDataObject<T> node = new DoubleLinkedRefDataObject<>();
      node.setData(data);
      if (subListStart == null) {
        subListStart = node;
        subListLast = node;
      } else {
        node.setPreviousReference(subListLast);
        subListLast.setNextReference(node);
        subListLast = node;
      }
      subListsize++;
    }
    adjustWithCurrentList(index, subListStart, subListLast, subListsize);
    isOk = true;
    return isOk;
  }
  
  public boolean addAll(int index, T[] array) {
    checkIndexBoundForPosition(index);
    boolean isOk = false;

    DoubleLinkedRefDataObject<T> subListStart = null;
    DoubleLinkedRefDataObject<T> subListLast = null;
    int subListsize = 0;
    for (T data : array) {
      DoubleLinkedRefDataObject<T> node = new DoubleLinkedRefDataObject<>();
      node.setData(data);
      if (subListStart == null) {
        subListStart = node;
        subListLast = node;
      } else {
        node.setPreviousReference(subListLast);
        subListLast.setNextReference(node);
        subListLast = node;
      }
      subListsize++;
    }
    adjustWithCurrentList(index, subListStart, subListLast, subListsize);
    isOk = true;
    return isOk;
  }
  
  @Override
  protected void adjustWithCurrentList(int index, DoubleLinkedRefDataObject<T> subListStart,
          DoubleLinkedRefDataObject<T> subListLast, int subListsize) {
    if (!isEmpty()) {
      DoubleLinkedRefDataObject<T> ithNode = getIthNode((index - 1));
      subListLast.setNextReference(ithNode.getNextReference());
      ithNode.getNextReference().setPreviousReference(subListLast);
      subListStart.setPreviousReference(ithNode);
      ithNode.setNextReference(subListStart);
    } else {
      setStartNode(subListStart);
      setLastNode(subListLast);
    }
    setSize((getSize() + subListsize));
  }
  
  protected void readObject(ObjectInputStream inStream) throws IOException, ClassNotFoundException {
    inStream.defaultReadObject();
    int size = inStream.readInt();
    
    setSize(size);
    for(int index = 0; index < size ; index++){
      @SuppressWarnings("unchecked")
      T data = (T)inStream.readObject();
      add(data);
    }
  }
  
  public T replaceValue(int at, T data) {
    DoubleLinkedRefDataObject<T> tempNode = getIthNode(at);
    T oldData = tempNode.getData();
    tempNode.setData(data);
    return oldData;
  }
}
