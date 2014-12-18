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

package com.andy.ds.linear.contract;

import java.util.Collection;
import java.util.Iterator;

import com.andy.adt.DoubleLinkedRefDataObject;

public interface SimpleList<T> extends Iterable<T>{
  /**
   * Get the start node of simple list.
   * @return null if simple list is empty else start node.
   */
  DoubleLinkedRefDataObject<T> getStartNode();
  
  /**
   * Get the last node of simple list.
   * @return null if simple list is empty else start node
   */
  DoubleLinkedRefDataObject<T> getLastNode();
  
  /**
   * Get the data of that particular index.
   * @throws  IndexOutOfBoundsException when index parameter out of range.
   * @return data of that particular index.
   */
  T get(int index);
  
  /**
   * Return the number of element in the simple list.
   * @return the number of element in the simple list.
   */
  int size();
  
  /**
   * Return the index of the data element if found in simple list
   * @return the index of the data element if found in simple list else -1.
   */
  int indexOf(T data);
  
  /**
   * Return {@code true} if data element found in simple list else {@code false}.
   * @return {@code true} if data element found in simple list else {@code false}.
   */
  boolean contains(T data);
  
  /**
   * Return Iterator&gt;T&lt; object of simple list. 
   */
  Iterator<T> iterator();
  
  /**
   * Return {@code true} if simple list is empty else {@code false}.
   * @return {@code true} if simple list is empty else {@code false}.
   */
  boolean isEmpty();
  
  /**
   * This method return an array of data of simple list (from start to end).
   * @param classType a class type object is passed.
   * @return array of data.
   */
  T[] toArray(T classType);
  
  /**
   * This method return an array of data of simple list (from start to end).
   * @return array of data.
   */
  Object[] toArray();
  
  /**
   * Return {@code true} if two simple lists are contain same data objects else {@code false}.
   * @return {@code true} if two simple lists are contain same data objects else {@code false}.
   */
  boolean equals(Object o);
  
  /**
   * Return hash code of the simple list.
   * Hash Code is calculated using 
   * <pre>
   *  for (T type : this) {
   *   hashCode = 31 * hashCode + (type == null ? 0 : type.hashCode());
   *  }
   * </pre>
   * @return hash code of the simple list.
   */
  int hashCode();
  
  /**
   * Return {@code SimpleList<T>} object which will have all the data objects 
   * of actual simple list from {@code fromIndex} to {@code toIndex}. 
   * @throws IndexOutOfBoundsException if {@code fromIndex} or {@code toIndex} is out of range or 
   *         {@code toIndex} greater than {@code fromIndex}.
   * @return {@code SimpleList<T>} object will be returned from actual simple list. 
   */
  SimpleList<T> createSubList(int fromIndex,int toIndex);
  
  /**
   * Add {@code data} object to the simple list.
   * @return {@code true} if simple list is modified else {@code false}. 
   */
  boolean add(T data);
  
  /**
   * Add {@code data} object to the simple list at {@code index} of simple list.
   * @throws IndexOutOfBoundsException if {@code index} is out of range.
   * @return {@code true} if simple list is modified else {@code false}. 
   */
  boolean addAt(int index, T data);
  boolean addAll(int index,SimpleList<? extends T> list);
  boolean addAll(int index,Collection<? extends T> list);
  boolean addAll(int index,T array[]);
  boolean addAll(SimpleList<? extends T> list);
  boolean addAll(Collection<? extends T> list);
  boolean addAll(T array[]);

  
  boolean removeAt(int index);
  boolean removeFirstOccurance(T dataToBeRemoved);
  boolean removeAll(T dataToBeRemoved);
  boolean removeAll();
  boolean removeAll(SimpleList<? extends T> list);
  boolean removeAll(Collection<? extends T> collection);
  boolean removeAll(T array[]);
  
  boolean retainAll(SimpleList<? extends T> list);
  boolean retainAll(Collection<? extends T> collection);
  boolean retainAll(T array[]);
}
