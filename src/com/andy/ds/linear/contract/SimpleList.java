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
  DoubleLinkedRefDataObject<T> getStartNode();
  DoubleLinkedRefDataObject<T> getLastNode();
  boolean add(T data);
  boolean addAt(int index, T data);
  boolean removeAt(int index);
  boolean remove(T dataToBeRemoved);
  boolean removeAll(T dataToBeRemoved);
  boolean removeAll();
  T get(int index);
  int size();
  int indexOf(T data);
  boolean contains(T data);
  Iterator<T> iterator();
  boolean isEmpty();
  T[] toArray(T classType);
  Object[] toArray();
  boolean equals(Object o);
  int hashCode();
  boolean addAll(int index,SimpleList<? extends T> list);
  boolean addAll(int index,Collection<? extends T> list);
  boolean addAll(int index,T...array);
  boolean addAll(SimpleList<? extends T> list);
  boolean addAll(Collection<? extends T> list);
  boolean addAll(T...array);
}
