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

public class SingleLinkedList<T> extends AbstractSimpleList<T> implements Iterable<T> {

  public SingleLinkedList(){
    
  }
  
  public SingleLinkedList(SimpleList<? extends T> list){
    this();
    addAll(list);
  }
  
  public SingleLinkedList(Collection<? extends T> collection){
    this();
    addAll(collection);
  }
  
  public SingleLinkedList(T...array){
    this();
    addAll(array);
  }
  
  private void addLast(T data) {
    DoubleLinkedRefDataObject<T> node = new DoubleLinkedRefDataObject<T>();
    getLastNode().setNextReference(node);
    node.setData(data);
    setLastNode(node);
    setSize((getSize() + 1));
  }

  /**
   * Add element to list
   * 
   * @param data
   *          node to be added to list
   * @return if node successfully add then true else false
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
   * Add element to list at particular index
   * 
   * @param index
   *          node to inserted at
   * @param data
   *          node to be added to list
   * @return if node successfully add then true else false
   */
  public boolean addAt(int index, T data) {
    boolean isOk = false;
    checkIndexBoundForPosition(index);

    if (index == INDEX_START) {
      addFirst(data);
      isOk = true;
    } else if (index == getSize()) {
      addLast(data);
      isOk = true;
    } else {
      DoubleLinkedRefDataObject<T> ithNode = getIthNode((index - 1));
      DoubleLinkedRefDataObject<T> node = new DoubleLinkedRefDataObject<T>();
      node.setData(data);
      node.setNextReference(ithNode.getNextReference());
      ithNode.setNextReference(node);
      setSize((getSize() + 1));
      isOk = true;
    }

    return isOk;
  }

  public boolean addAll(int index,SimpleList<? extends T> list){
    Object[] objects = list.toArray();
    return addAllElements(index, objects);
  }

  
  public boolean addAll(int index,Collection<? extends T> collection){
    Object[] objects = collection.toArray();
    return addAllElements(index, objects);
  }
  
  public boolean addAll(int index,T...array){
    return addAllElements(index, array);
  }

  private boolean addAllElements(int index, Object []objects) {
    boolean isOk = false;
    checkIndexBoundForPosition(index);
    
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
          subListLast.setNextReference(node);
          subListLast = node;  
        }
        subListsize++;        
      }
      subListLast.setNextReference(ithNode.getNextReference());
      ithNode.setNextReference(subListStart);
      setSize((getSize() + subListsize));
      isOk = true;
    } else {
      addElementsToEndOrFrontOfList(index, objects);
    }
   
    return isOk;
  }
  
  private void removeFirst() {
    DoubleLinkedRefDataObject<T> nextStartNode = getStartNode().getNextReference();
    getStartNode().setData(null);
    getStartNode().setNextReference(null);
    setStartNode(nextStartNode);
    setSize((getSize() - 1));
  }

  /**
   * Remove element from list at particular index
   * 
   * @param index
   *          node to remove at
   * @return if node successfully remove then true else false
   */
  public boolean removeAt(int index) {
    boolean isOk = false;
    checkIndexBoundForElement(index);
    if (index == INDEX_START) {
      removeFirst();
      isOk = true;
    } else {
      DoubleLinkedRefDataObject<T> beforeDeleteNode = getIthNode((index - 1));
      DoubleLinkedRefDataObject<T> deleteNode = beforeDeleteNode.getNextReference();
      if (deleteNode.getNextReference() == null) {
        setLastNode(beforeDeleteNode);
      }
      beforeDeleteNode.setNextReference(deleteNode.getNextReference());
      deleteNode.setData(null);
      deleteNode.setNextReference(null);
      isOk = true;
      setSize((getSize() - 1));
    }

    return isOk;
  }

  /**
   * Remove element from list if parameter matched
   * 
   * @param dataToBeRemoved
   *          to be removed if matched
   * @return if node successfully remove then true else false
   */

  public boolean removeFirstOccurance(T dataToBeRemoved) {
    return remove(dataToBeRemoved, false);
  }

  /**
   * 
   * @param dataToBeRemoved
   * @param removeAll
   * @return
   */
  private boolean remove(T dataToBeRemoved, boolean removeAll) {
    boolean isOk = false;
    if (getStartNode() == null) {
      return isOk;
    }
    boolean enableBreaker = !removeAll;
    if (isEqualData(dataToBeRemoved, getStartNode().getData())) {
      removeFirst();
      isOk = true;
    } else {
      removeAll = true;
    }

    if (removeAll) {
      DoubleLinkedRefDataObject<T> canBeDeletedNode = getStartNode().getNextReference();
      DoubleLinkedRefDataObject<T> nextLinkingNode = (canBeDeletedNode == null) ? null : canBeDeletedNode
              .getNextReference();
      DoubleLinkedRefDataObject<T> beforeDeleteNode = getStartNode();

      for (int index = (INDEX_START + 1); index < getSize(); index++) {
        if (isEqualData(dataToBeRemoved, canBeDeletedNode.getData())) {
          beforeDeleteNode.setNextReference(nextLinkingNode);
          if (canBeDeletedNode.getNextReference() == null) {
            setLastNode(beforeDeleteNode);
          }
          canBeDeletedNode.setData(null);
          canBeDeletedNode.setNextReference(null);

          canBeDeletedNode = nextLinkingNode;
          nextLinkingNode = (canBeDeletedNode == null) ? null : canBeDeletedNode.getNextReference();

          isOk = true;
          setSize((getSize() - 1));
          if (enableBreaker) {
            break;
          }
        } else {
          canBeDeletedNode = canBeDeletedNode.getNextReference();
          nextLinkingNode = (canBeDeletedNode == null) ? null : canBeDeletedNode.getNextReference();
          beforeDeleteNode = beforeDeleteNode.getNextReference();
          if (canBeDeletedNode == null) {
            break;
          }
        }
      }
    }

    return isOk;
  }

  /**
   * 
   * @param dataToBeRemoved
   * @return
   */
  public boolean removeAll(T dataToBeRemoved) {
    return remove(dataToBeRemoved, true);
  }

  /**
   * Remove all elements from list
   * 
   * @return if node successfully remove then true else false
   */
  public boolean removeAll() {
    boolean isOk = true;
    for (int index = getSize(); index > INDEX_START; index--) {
      isOk &= removeAt(INDEX_START);
    }
    return isOk;
  }

  /**
   * get <T> type object at particular index
   * 
   * @param index
   * @return <T> type object from list
   */
  public T get(int index) {
    DoubleLinkedRefDataObject<T> node = getIthNode(index);
    return node.getData();
  }

  /**
   * getIthNode method return i th node object
   * 
   * @param index
   * @return the i th node from the list
   */

  private DoubleLinkedRefDataObject<T> getIthNode(int index) {
    checkIndexBoundForElement(index);
    DoubleLinkedRefDataObject<T> node = getStartNode();

    if (index != INDEX_START && index < (getSize() - 1)) {
      int tempIndex = INDEX_START;
      while ((node != null) && (tempIndex < index)) {
        node = node.getNextReference();
        tempIndex++;
      }
    } else if (index == (getSize() - 1)) {
      node = getLastNode();
    }
    return node;
  }
 
}
