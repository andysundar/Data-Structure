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

import java.util.Collection;

import com.andy.adt.DoubleLinkedRefDataObject;
import com.andy.ds.linear.contract.SimpleList;

public class DoubleLinkedList<T> extends AbstractSimpleList<T> {
  
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
  
  public DoubleLinkedList(T...array){
    this();
    addAll(array);
  }

  @Override
  protected DoubleLinkedRefDataObject<T> addFirst(T data) {
    DoubleLinkedRefDataObject<T> node = new DoubleLinkedRefDataObject<T>();
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
    DoubleLinkedRefDataObject<T> dataObject = new DoubleLinkedRefDataObject<T>();
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
      DoubleLinkedRefDataObject<T> dataObject = new DoubleLinkedRefDataObject<T>();
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
        setStartNode(afterDeleteNode);
      }
      if (afterDeleteNode != null) {
        afterDeleteNode.setPreviousReference(beforeDeleteNode);
      } else {
        setLastNode(beforeDeleteNode);
      }
      node.setData(null);
      node.setNextReference(null);
      node.setPreviousReference(null);
      setSize((getSize() - 1));
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
    Object[] object = list.toArray();
    return addAllElements(index, object);
  }
  
  public boolean addAll(int index, Collection<? extends T> collection) {
    Object[] object = collection.toArray();
    return addAllElements(index, object);
  }
  
  public boolean addAll(int index, T... array) {
    return addAllElements(index, array);
  }
  
  private boolean addAllElements(int index, Object[] objects) {
    checkIndexBoundForPosition(index);
    
    boolean isOk = false;
    if(index != INDEX_START && index != getSize()){
      DoubleLinkedRefDataObject<T> ithNode = getIthNode((index - 1));
      DoubleLinkedRefDataObject<T> subListStart = null;
      DoubleLinkedRefDataObject<T> subListLast = null;
      int subListsize = 0;
      for(Object dataObject:objects){
        DoubleLinkedRefDataObject<T> node = new DoubleLinkedRefDataObject<T>();
        @SuppressWarnings("unchecked")
        T data = (T)dataObject;
        node.setData(data);
        if(subListStart == null){
          subListStart = node;
          subListLast = node;
        }else {
          node.setPreviousReference(subListLast);
          subListLast.setNextReference(node);
          subListLast = node;  
        }
        subListsize++;        
      }
      subListLast.setNextReference(ithNode.getNextReference());
      ithNode.getNextReference().setPreviousReference(subListLast);
      subListStart.setPreviousReference(ithNode);
      ithNode.setNextReference(subListStart);
      setSize((getSize() + subListsize));
      isOk = true;
    } else {
      addElementsToEndOrFrontOfList(index, objects);
    }
   
    return isOk;
  }
  
}
