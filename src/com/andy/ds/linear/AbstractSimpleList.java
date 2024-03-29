/* Copyright [2012] Anindya Bandopadhyay
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
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import com.andy.adt.DoubleLinkedRefDataObject;
import com.andy.ds.linear.contract.SimpleList;

public abstract class AbstractSimpleList<T> implements SimpleList<T>,Serializable {

  private static final long serialVersionUID = 1L;
  protected static final int INDEX_START = 0;
  
  private transient int size;
  private transient DoubleLinkedRefDataObject<T> startNode;
  private transient DoubleLinkedRefDataObject<T> lastNode;

  public static final String START_END_INDEX_MESSAGE = "Start index cannot be greater than end index.";
  
  protected DoubleLinkedRefDataObject<T> addFirst(T data) {
    DoubleLinkedRefDataObject<T> node = new DoubleLinkedRefDataObject<>();
    node.setData(data);
    node.setNextReference(getStartNode());
    setStartNode(node);
    if(getLastNode() == null) {
      setLastNode(node);
    }
    setSize((getSize() + 1));
    return node;
  }

  protected boolean isEqualData(T dataOne, T dataTwo) {
    if (dataOne == null) {
      return (null == dataTwo);
    }
    return (dataOne.equals(dataTwo));
  }

  public int indexOf(T data) {
    int index = -1;
    DoubleLinkedRefDataObject<T> node = startNode;

    while (node != null) {
      if (isEqualData(data, node.getData())) {
        index++;
        break;
      }
      node = node.getNextReference();
      index++;
    }

    return index;
  }

  public boolean contains(T data) {
    return (indexOf(data) != -1);
  }

  protected boolean isIndexOutOfBoundForPosition(int index) {
    return (index < INDEX_START || index > size);
  }
  
  protected boolean isIndexOutOfBoundForElement(int index) {
    return (index < INDEX_START || index >= size);
  }

  protected String getOutOfBoundMessage() {
    return "Index cannot be less than 0 and greater than or equal to " + size;
  }

  protected int getSize() {
    return size;
  }

  protected void setSize(int size) {
    this.size = size;
  }

  public DoubleLinkedRefDataObject<T> getStartNode() {
    return startNode;
  }

  public void setStartNode(DoubleLinkedRefDataObject<T> startNode) {
    this.startNode = startNode;
  }

  public DoubleLinkedRefDataObject<T> getLastNode() {
    return lastNode;
  }

  public void setLastNode(DoubleLinkedRefDataObject<T> lastNode) {
    this.lastNode = lastNode;
  }

  public int size() {
    return size;
  }

  protected void checkIndexBoundForElement(int index) {
    if (isIndexOutOfBoundForElement(index)) {
      throw new IndexOutOfBoundsException(getOutOfBoundMessage());
    }
  }
  
  protected void checkIndexBoundForPosition(int index) {
    if (isIndexOutOfBoundForPosition(index)) {
      throw new IndexOutOfBoundsException(getOutOfBoundMessage());
    }
  }
  
  public boolean isEmpty() {
    return (size() == 0);
  }

  public T[] toArray(T classType) {
    @SuppressWarnings("unchecked")
    T[] typeObject = (T[]) java.lang.reflect.Array.newInstance(classType.getClass(), size);

    int index = 0;
    for (T simpleList : this) {
      typeObject[index] = simpleList;
      index++;
    }
    return typeObject;
  }
  
  public Object[] toArray() {
   Object[] object = new Object[size];

    int index = 0;
    for (T simpleList : this) {
      object[index] = simpleList;
      index++;
    }
    return object;
  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SimpleList) {
      @SuppressWarnings("unchecked")
      SimpleList<T> simpleList = ((SimpleList<T>) o);
      Iterator<T> iteratorO = simpleList.iterator();
      Iterator<T> iterator = this.iterator();
      while (iterator.hasNext() && iteratorO.hasNext()) {
        T dataO = iteratorO.next();
        T data = iterator.next();
        if (!isEqualData(data, dataO)) {
          return false;
        }
      }
      return !(iterator.hasNext() || iteratorO.hasNext());
    }
    return false;
  }

  public int hashCode() {
    int hashCode = 1;
    for (T type : this) {
      hashCode = 31 * hashCode + (type == null ? 0 : type.hashCode());
    }
    return hashCode;
  }

  public Iterator<T> iterator() {
    return new SimpleListIterator();
  }
  
  private SimpleListIterator simpleListIterator() {
    return new SimpleListIterator();
  }
  
 
  
  public boolean addAll(SimpleList<? extends T> list){
    return addAll(getSize(),list);
  }
  
  public boolean addAll(Collection<? extends T> collection){
    return addAll(getSize(),collection);
  }
  
  public boolean addAll(T[] array){
    return addAll(getSize(),array);
  }

 
  
  public boolean removeAll(Collection<? extends T> collection) {
    boolean isOk = false;
    SimpleListIterator iterator = simpleListIterator();
    for (T subListData : collection) {
      if(removeAllElements(iterator,subListData)){
        isOk = true;
      }
      iterator.reset();
    }
    return isOk;
  }
  
  

  public boolean removeAll(SimpleList<? extends T> list) {
    boolean isOk = false;
    SimpleListIterator iterator = simpleListIterator();
    for (T subListData : list) {
      if(removeAllElements(iterator,subListData)){
        isOk = true;
      }
      iterator.reset();
    }
    return isOk;
  }
  
  public boolean removeAll(T[] array) {
    boolean isOk = false;
    SimpleListIterator iterator = simpleListIterator();
    for (T subListData : array) {
      if(removeAllElements(iterator,subListData)){
        isOk = true;
      }
      iterator.reset();
    }
    return isOk;
  }
  
  private boolean removeAllElements(Iterator<T> iterator,T subListData) {
   while(iterator.hasNext()){
      T data = iterator.next();
      if(isEqualData(data, subListData)){
          iterator.remove();
          return true;
        }
      }
    return false;
  }
  
  public boolean retainAll(Collection<? extends T> collection){
    boolean isOk = false;
    Iterator<T> iterator = iterator();
    while (iterator.hasNext()) {
     T data = iterator.next();
      if (!collection.contains(data)) {
        iterator.remove();
        isOk = true;
      }
    }
    return isOk;
  }
  
  public boolean retainAll(SimpleList<? extends T> list){
    boolean isOk = false;
    Iterator<T> iterator = iterator();
    
    while (iterator.hasNext()) {
      boolean present = false;
      T data = iterator.next();
      for (T subListData : list) {
        if (isEqualData(data, subListData)) {
          present = true;
        }
      }
      if (!present) {
        iterator.remove();
        isOk = true;
      }
    }
    return isOk;
  }
  
  public boolean retainAll(T[] array) {
    boolean isOk = false;
    Iterator<T> iterator = iterator();
    while (iterator.hasNext()) {
      boolean present = false;
      T data = iterator.next();
      for (T subListData : array) {
        if (isEqualData(data, subListData)) {
          present = true;
        }
      }
      if (!present) {
        iterator.remove();
        isOk = true;
      }
    }
    return isOk;
  }
  
  public SimpleList<T> createSubList(int fromIndex,int toIndex){
    if(fromIndex > toIndex ){
      throw new IndexOutOfBoundsException(START_END_INDEX_MESSAGE);
    }
    checkIndexBoundForElement(fromIndex);
    checkIndexBoundForElement(toIndex);
    SimpleList<T> simpleList = null;
    if(this instanceof SingleLinkedList){
      simpleList = new SingleLinkedList<>();
    } else if(this instanceof CircularLinkedList){
      simpleList = new CircularLinkedList<>();
    } else if(this instanceof DoubleLinkedList){
      simpleList = new DoubleLinkedList<>();
    } 
    
    int count = 0;
    if(simpleList != null){
      Iterator<T> iterator = iterator();
      while (iterator.hasNext()) {
        T data = iterator.next();
        if(count >= fromIndex  && count < toIndex){
          simpleList.add(data);
        }
        if(count == toIndex){
          simpleList.add(data);
          break;
        }
      }
    }
    return simpleList;
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
  
  protected abstract void adjustWithCurrentList(int index, DoubleLinkedRefDataObject<T> subListStart,
          DoubleLinkedRefDataObject<T> subListLast, int subListsize);
  
  
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    String comma = "";
    for(T value: this)
    {
      sb.append(comma);
      if(value == null) {
        sb.append("null");
      } else {
        sb.append(value.toString());
      }
      comma = ", ";
    }
    sb.append("]");
    return sb.toString();
  }
  
  
  private void writeObject(ObjectOutputStream outStream) throws IOException{
    //Removing the transient fields from this stream
    outStream.defaultWriteObject();
    outStream.write(getSize());
    for(T object:this) {
      outStream.writeObject(object);
    }
  }
  /**
   * Iterator for lists
   * 
   * @author Anindya Bandopadhyay
   * 
   */

  private final class SimpleListIterator implements Iterator<T> {

    private DoubleLinkedRefDataObject<T> currentNode;
    private DoubleLinkedRefDataObject<T> previousNode;
    int index;

    public SimpleListIterator(){
      reset();
    }
    
    void reset(){
      currentNode = getStartNode();
      previousNode = getStartNode();
      index = 0;  
    }
    
    public boolean hasNext() {
      return (index != size);
    }

    public T next() {
      T data = currentNode.getData();
      previousNode = currentNode;
      currentNode = currentNode.getNextReference();
      index++;
      return data;
    }

    @Override
    public void remove() {
      if(AbstractSimpleList.this.unLinkNode(previousNode)){
        index--;
      }
    }
  }

}
