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

package com.andy.algo.sort;

import java.util.Comparator;
import java.util.List;

import com.andy.ds.linear.contract.SimpleList;

public class InsertionSort<T> {

  public void sort(T[] objectArray, Comparator<? super T> comparator) {
    T temp = null;
    int size = objectArray.length;
    for (int i = 0; i < size; i++) {
      int index = i;
      int subIndex = (i - 1);
      while (subIndex > -1 && (comparator.compare(objectArray[subIndex], objectArray[index]) == 1)) {
        temp = objectArray[index];
        objectArray[index] = objectArray[subIndex];
        objectArray[subIndex] = temp;
        subIndex--;
        index--;
      }
    }
  }

  public void sort(Comparable<? super T>[] objectArray) {
    Comparable<? super T> temp = null;
    int size = objectArray.length;
    for (int i = 0; i < size; i++) {
      int index = i;
      int subIndex = (i - 1);
      @SuppressWarnings("unchecked")
      T o = (T) objectArray[index];
      while (subIndex > -1 && (objectArray[subIndex].compareTo(o) == 1)) {
        temp = objectArray[index];
        objectArray[index] = objectArray[subIndex];
        objectArray[subIndex] = temp;
        subIndex--;
        index--;
      }
    }
  }

  @SuppressWarnings("hiding")
  public <T extends Comparable<? super T>> void sort(List<T> list) {
    T temp = null;
    int size = list.size();
    for (int i = 0; i < size; i++) {
      int index = i;
      int subIndex = (i - 1);
      while (subIndex > -1 && (list.get(subIndex).compareTo(list.get(index)) == 1)) {
        temp = list.set(index, list.get(subIndex));
        list.set(subIndex, temp);
        subIndex--;
        index--;
      }
    }
  }
  
  public void sort(List<T> list, Comparator<? super T> comparator) {
    T temp = null;
    int size = list.size();
    for (int i = 0; i < size; i++) {
      int index = i;
      int subIndex = (i - 1);
      while (subIndex > -1 && (comparator.compare(list.get(subIndex), list.get(index)) == 1)) {
        temp = list.set(index,list.get(subIndex));
        list.set(subIndex,temp);
        subIndex--;
        index--;
      }
    }
  }
  
  
  @SuppressWarnings("hiding")
  public <T extends Comparable<? super T>> void sort(SimpleList<T> list) {
    T temp = null;
    int size = list.size();
    for (int i = 0; i < size; i++) {
      int index = i;
      int subIndex = (i - 1);
      while (subIndex > -1 && (list.get(subIndex).compareTo(list.get(index)) == 1)) {
        temp =  list.replaceValue(index, list.get(subIndex));
        list.replaceValue(subIndex, temp);
        subIndex--;
        index--;
      }
    }
  }
  
  public void sort(SimpleList<T> list, Comparator<? super T> comparator) {
    T temp = null;
    int size = list.size();
    for (int i = 0; i < size; i++) {
      int index = i;
      int subIndex = (i - 1);
      while (subIndex > -1 && (comparator.compare(list.get(subIndex), list.get(index)) == 1)) {
        temp =  list.replaceValue(index, list.get(subIndex));
        list.replaceValue(subIndex, temp);
        subIndex--;
        index--;
      }
    }
  }
}
