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

public class InsertionSort<T> {

  public void sort(T[] list, Comparator<? super T> comparator) {
    T temp = null;
    int size = list.length;
    for (int i = 0; i < size; i++) {
      int index = i;
      int subIndex = (i - 1);
      while (subIndex > -1 && (comparator.compare(list[subIndex], list[index]) == 1)) {
        temp = list[index];
        list[index] = list[subIndex];
        list[subIndex] = temp;
        subIndex--;
        index--;
      }
    }
  }

  public void sort(Comparable<? super T>[] list) {
    Comparable<? super T> temp = null;
    int size = list.length;
    for (int i = 0; i < size; i++) {
      int index = i;
      int subIndex = (i - 1);
      @SuppressWarnings("unchecked")
      T o = (T) list[index];
      while (subIndex > -1 && (list[subIndex].compareTo(o) == 1)) {
        temp = list[index];
        list[index] = list[subIndex];
        list[subIndex] = temp;
        subIndex--;
        index--;
      }
    }
  }

}
