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

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.andy.ds.linear.SingleLinkedList;
import com.andy.ds.linear.contract.SimpleList;

public class InsertionSortTest {

  private InsertionSort<Integer> intSort;
  private Integer dataForSort[] ;
  private List<Integer> list;
  private SimpleList<Integer> simpleList;

  @Before
  public void setUp() throws Exception {
    intSort = new InsertionSort<Integer>();
    Random random = new Random();
    dataForSort = new Integer[10];
    for(int index = 0; index < 10; index++){
      dataForSort[index] = random.nextInt();
    }
  }

  @After
  public void tearDown() throws Exception {
    intSort = null;
    dataForSort = null;
  }

  @Test
  public void testSort_withArrayParm() {
    intSort.sort(dataForSort);
    for (int index = 0; index < dataForSort.length; index++) {
      int nextIndex = (index + 1) == dataForSort.length ? index : (index + 1);
      assertFalse("Insertion Sort is fail due to " + dataForSort[index] + " before " + dataForSort[nextIndex]
              + " in result array.", (dataForSort[index] > dataForSort[nextIndex]));
    }
  }
  
  @Test
  public void testReverseSort_withArrayParm() {
    intSort.sort(dataForSort,true);
    for (int index = 0; index < dataForSort.length; index++) {
      int nextIndex = (index + 1) == dataForSort.length ? index : (index + 1);
      assertFalse("Insertion Sort is fail due to " + dataForSort[index] + " before " + dataForSort[nextIndex]
              + " in result array.", (dataForSort[index] < dataForSort[nextIndex]));
    }
  }

  @Test
  public void testSort_withArrayAndComparator() {
    intSort.sort(dataForSort, new WrapperIntegerComparator());
    for (int index = 0; index < dataForSort.length; index++) {
      int nextIndex = (index + 1) == dataForSort.length ? index : (index + 1);
      assertFalse("Insertion Sort is fail due to " + dataForSort[index] + " before " + dataForSort[nextIndex]
              + " in result array.", (dataForSort[index] > dataForSort[nextIndex]));
    }
  }
  
  @Test
  public void testReverseSort_withArrayAndComparator() {
    intSort.sort(dataForSort, new WrapperIntegerComparator(),true);
    for (int index = 0; index < dataForSort.length; index++) {
      int nextIndex = (index + 1) == dataForSort.length ? index : (index + 1);
      assertFalse("Insertion Sort is fail due to " + dataForSort[index] + " before " + dataForSort[nextIndex]
              + " in result array.", (dataForSort[index] < dataForSort[nextIndex]));
    }
  }
  
  @Test
  public void testSort_withListParm() {
    list = new ArrayList<Integer>(dataForSort.length);
    populateListFromArray();
    intSort.sort(list);
    int size = list.size();
    for (int index = 0; index < size; index++) {
      int nextIndex = (index + 1) == size ? index : (index + 1);
      assertFalse("Insertion Sort is fail due to " + list.get(index) + " before " + list.get(nextIndex)
              + " in result array.", (list.get(index) > list.get(nextIndex)));
    }
    //System.out.printf("%s result \n %s\n","Insertion Sort of List ",list.toString());
  }

  @Test
  public void testReverseSort_withListParm() {
    list = new ArrayList<Integer>(dataForSort.length);
    populateListFromArray();
    intSort.sort(list,true);
    int size = list.size();
    for (int index = 0; index < size; index++) {
      int nextIndex = (index + 1) == size ? index : (index + 1);
      assertFalse("Insertion Sort is fail due to " + list.get(index) + " before " + list.get(nextIndex)
              + " in result array.", (list.get(index) < list.get(nextIndex)));
    }
    //System.out.printf("%s result \n %s\n","Insertion Reverse Sort of List ",list.toString());
  }
  
  @Test
  public void testSort_withListAndComparator() {
    list = new ArrayList<Integer>(dataForSort.length);
    populateListFromArray();
    intSort.sort(list,new WrapperIntegerComparator());
    int size = list.size();
    for (int index = 0; index < size; index++) {
      int nextIndex = (index + 1) == size ? index : (index + 1);
      assertFalse("Insertion Sort is fail due to " + list.get(index) + " before " + list.get(nextIndex)
              + " in result array.", (list.get(index) > list.get(nextIndex)));
    }    
    //System.out.printf("%s result \n %s\n","Insertion Sort of List using Comparator",list.toString());
  }
  
  @Test
  public void testReverseSort_withListAndComparator() {
    list = new ArrayList<Integer>(dataForSort.length);
    populateListFromArray();
    intSort.sort(list,new WrapperIntegerComparator(),true);
    int size = list.size();
    for (int index = 0; index < size; index++) {
      int nextIndex = (index + 1) == size ? index : (index + 1);
      assertFalse("Insertion Sort is fail due to " + list.get(index) + " before " + list.get(nextIndex)
              + " in result array.", (list.get(index) < list.get(nextIndex)));
    }    
    //System.out.printf("%s result \n %s\n","Insertion Reverse Sort of List using Comparator",list.toString());
  }
  
  @Test
  public void testSort_withSimpleListParm() {
    simpleList = new SingleLinkedList<Integer>(dataForSort);
    intSort.sort(simpleList);
    int size = simpleList.size();
    for (int index = 0; index < size; index++) {
      int nextIndex = (index + 1) == size ? index : (index + 1);
      assertFalse("Insertion Sort is fail due to " + simpleList.get(index) + " before " + simpleList.get(nextIndex)
              + " in result array.", (simpleList.get(index) > simpleList.get(nextIndex)));
    }
    //System.out.printf("%s result \n %s\n","Insertion Sort of SimpleList ",simpleList.toString());
  }
  
  @Test
  public void testReverseSort_withSimpleListParm() {
    simpleList = new SingleLinkedList<Integer>(dataForSort);
    intSort.sort(simpleList,true);
    int size = simpleList.size();
    for (int index = 0; index < size; index++) {
      int nextIndex = (index + 1) == size ? index : (index + 1);
      assertFalse("Insertion Sort is fail due to " + simpleList.get(index) + " before " + simpleList.get(nextIndex)
              + " in result array.", (simpleList.get(index) < simpleList.get(nextIndex)));
    }
    //System.out.printf("%s result \n %s\n","Insertion Reverse Sort of SimpleList ",simpleList.toString());
  }

  @Test
  public void testSort_withSimpleListAndComparator() {
    simpleList = new SingleLinkedList<Integer>(dataForSort);
    intSort.sort(simpleList, new WrapperIntegerComparator());
    int size = simpleList.size();
    for (int index = 0; index < size; index++) {
      int nextIndex = (index + 1) == size ? index : (index + 1);
      assertFalse("Insertion Sort is fail due to " + simpleList.get(index) + " before " + simpleList.get(nextIndex)
              + " in result array.", (simpleList.get(index) > simpleList.get(nextIndex)));
    }
    //System.out.printf("%s result \n %s\n","Insertion Sort of SimpleList using Comparator",simpleList.toString());
  }
  
  @Test
  public void testReverseSort_withSimpleListAndComparator() {
    simpleList = new SingleLinkedList<Integer>(dataForSort);
    intSort.sort(simpleList, new WrapperIntegerComparator(),true);
    int size = simpleList.size();
    for (int index = 0; index < size; index++) {
      int nextIndex = (index + 1) == size ? index : (index + 1);
      assertFalse("Insertion Sort is fail due to " + simpleList.get(index) + " before " + simpleList.get(nextIndex)
              + " in result array.", (simpleList.get(index) < simpleList.get(nextIndex)));
    }
    //System.out.printf("%s result \n %s\n","Insertion Reverse Sort of SimpleList using Comparator",simpleList.toString());
  }

  private void populateListFromArray() {
    if (list != null && list.isEmpty()) {
      for (Integer value : dataForSort) {
        list.add(value);
      }
    }
  }
  
}
