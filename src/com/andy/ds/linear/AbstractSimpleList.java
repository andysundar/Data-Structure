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


import java.util.Iterator;

import com.andy.adt.DoubleLinkedRefDataObject;
import com.andy.ds.linear.contract.SimpleList;

public abstract class AbstractSimpleList<T> implements SimpleList<T> {

  private int size;
  protected static final int INDEX_START = 0;
  private DoubleLinkedRefDataObject<T> startNode;
  private DoubleLinkedRefDataObject<T> lastNode;

  protected DoubleLinkedRefDataObject<T> addFirst(T data) {
    DoubleLinkedRefDataObject<T> node = new DoubleLinkedRefDataObject<T>();
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
      return (dataOne == dataTwo);
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

  /**
   * Iterator for lists
   * 
   * @author Anindya Bandopadhyay
   * 
   */

  private class SimpleListIterator implements Iterator<T> {

    private DoubleLinkedRefDataObject<T> currentNode = getStartNode();
    int index = 0;

    public boolean hasNext() {
      return (index != size);
    }

    public T next() {
      T data = currentNode.getData();
      currentNode = currentNode.getNextReference();
      index++;
      return data;
    }

    public void remove() {
      throw new UnsupportedOperationException(
              "SimpleListIterator doesn't support this remove feature. Please use the other remove APIs available.");
    }
  }

}
