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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.andy.ds.linear.contract.SimpleList;

public class DoubleLinkedListTest {
  private SimpleList<Integer> doubleLinkedList = null;
  private SimpleList<Integer>  simpleListforEmptyList = null;
  private SimpleList<Integer>  simpleList = null;
  private List<Integer>  linkedListforEmptyList = null;
  private List<Integer>  linkedList = null;
  private Integer  []subArrayForEmpty = {1,2,3};
  private Integer  []subArray = {1,9,10,5};
  
  @Before
  public void setUp() throws Exception {
    doubleLinkedList = new DoubleLinkedList<Integer>();
    
    simpleListforEmptyList = new SingleLinkedList<Integer>();
    simpleListforEmptyList.add(1);
    simpleListforEmptyList.add(2);
    simpleListforEmptyList.add(3);
    
    simpleList = new SingleLinkedList<Integer>();
    simpleList.add(0);
    simpleList.add(9);
    simpleList.add(10);
    simpleList.add(5);
    
    linkedListforEmptyList = new LinkedList<Integer>();
    linkedListforEmptyList.add(1);
    linkedListforEmptyList.add(2);
    linkedListforEmptyList.add(3);
    
    linkedList = new LinkedList<Integer>();
    linkedList.add(2);
    linkedList.add(10);
    linkedList.add(9);
    linkedList.add(5);
  }

  @After
  public void tearDown() throws Exception {
    doubleLinkedList = null;
  }

  @Test
  public void testAdd() {
    for (int counter = 1; counter < 11; counter++) {
      assertTrue(doubleLinkedList.add(counter));
    }

    assertEquals(10, doubleLinkedList.size());
  }

  @Test
  public void testAdd_WhenDataIsNull() {
    assertTrue(doubleLinkedList.add(null));
  }

  @Test
  public void testAddAt() {
    for (int counter = 1; counter < 11; counter++) {
      assertTrue(doubleLinkedList.add(counter));
    }
    assertTrue(doubleLinkedList.addAt(0, 0));

    assertTrue(doubleLinkedList.addAt(doubleLinkedList.size(), 11));

    assertTrue(doubleLinkedList.addAt(5, 5));

    assertEquals(13, doubleLinkedList.size());
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testAddAt_whenIndexLessThanZero() {
    doubleLinkedList.addAt(1, 1);
  }

  @Test
  public void testAddAt_whenDataIsNull() {
    assertTrue(doubleLinkedList.add(1));
    assertTrue(doubleLinkedList.addAt(0, null));
  }

  @Test
  public void testRemoveFirstOccurance() {
    for (int counter = 1; counter < 11; counter++) {
      assertTrue(doubleLinkedList.add(counter));
    }

    assertFalse(doubleLinkedList.removeFirstOccurance(11));

    assertTrue(doubleLinkedList.removeFirstOccurance(5));
    assertEquals(9, doubleLinkedList.size());

    assertTrue(doubleLinkedList.removeFirstOccurance(1));
    assertEquals(8, doubleLinkedList.size());

    assertTrue(doubleLinkedList.removeFirstOccurance(10));
    assertEquals(7, doubleLinkedList.size());
  }

  @Test
  public void testRemoveFirstOccurance_whenDataIsNull() {
    assertTrue(doubleLinkedList.add(1));
    assertFalse(doubleLinkedList.removeFirstOccurance(null));
  }

  @Test
  public void testRemoveAt() {
    for (int counter = 1; counter < 11; counter++) {
      assertTrue(doubleLinkedList.add(counter));
    }
    assertEquals(10, doubleLinkedList.size());

    assertTrue("Reomved from 1st position.", doubleLinkedList.removeAt(0));
    assertEquals(9, doubleLinkedList.size());

    assertTrue("Reomved from last position.", doubleLinkedList.removeAt((doubleLinkedList.size() - 1)));
    assertEquals(8, doubleLinkedList.size());

    assertTrue("Removed from middle position.", doubleLinkedList.removeAt(5));
    assertEquals(7, doubleLinkedList.size());
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testRemoveAt_whenIndexLessThanLengthOfList() {
    doubleLinkedList.removeAt(0);
  }

  @Test
  public void testRemoveAllT() {
    for (int counter = 1; counter < 11; counter++) {
      assertTrue(doubleLinkedList.add(counter));
    }
    for (int counter = 1; counter < 11; counter++) {
      assertTrue(doubleLinkedList.add(counter));
    }

    assertTrue("Remove all 5 from list.", doubleLinkedList.removeAll(5));
    assertEquals(18, doubleLinkedList.size());
  }

  @Test
  public void testRemoveAll_whenDataIsNull() {
    Integer i = null;
    assertTrue(doubleLinkedList.add(i));
    assertTrue(doubleLinkedList.add(i));
    assertTrue(doubleLinkedList.removeAll(i));

  }

  @Test
  public void testRemoveAll() {
    for (int counter = 1; counter < 11; counter++) {
      assertTrue(doubleLinkedList.add(counter));
    }

    assertTrue("Remove all from list.", doubleLinkedList.removeAll());
    assertEquals(0, doubleLinkedList.size());
  }

  @Test
  public void testGet() {
    int counter = 1;
    for (; counter < 11; counter++) {
      assertTrue(doubleLinkedList.add(counter));
    }
    counter = 1;
    for (; counter < 11; counter++) {
      assertEquals(Integer.valueOf(counter), doubleLinkedList.get((counter - 1)));
    }
  }

  @Test
  public void testGet_whenDataIsNull() {
    assertTrue(doubleLinkedList.add(null));
    assertEquals(null, doubleLinkedList.get((0)));

  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGet_whenIndexInvalid() {
    doubleLinkedList.get(0);
  }

  @Test
  public void testSize() {
    int counter = 1;
    for (; counter < 11; counter++) {
      assertTrue(doubleLinkedList.add(counter));
    }
    assertEquals((counter - 1), doubleLinkedList.size());
  }

  @Test
  public void testIsEmpty() {
    assertTrue(doubleLinkedList.isEmpty());
  }

  private void addDataToList() {
    for (int index = 0; index < 10; index++) {
      doubleLinkedList.add(index);
    }
  }

  @Test
  public void testIterator_hasNext() {
    addDataToList();
    Iterator<Integer> slDO = doubleLinkedList.iterator();
    assertTrue(slDO.hasNext());
  }

  @Test
  public void testIterator_Remove() {
    addDataToList();
    Iterator<Integer> slDO = doubleLinkedList.iterator();
    slDO.remove();
    assertEquals(9,doubleLinkedList.size());
  }

  @Test
  public void testIsEmpty_whenNoElement() {
    assertTrue(doubleLinkedList.isEmpty());
  }

  @Test
  public void testIsEmpty_whenElementThere() {
    addDataToList();
    assertFalse(doubleLinkedList.isEmpty());
  }

  @Test
  public void testGetLastNode() {
    assertTrue(doubleLinkedList.add(Integer.valueOf(1)));
    assertTrue(doubleLinkedList.add(Integer.valueOf(2)));
    assertNotNull(doubleLinkedList.getLastNode());
  }

  @Test
  public void testIndexOf_whenNoElementInList() {
    assertEquals(-1, doubleLinkedList.indexOf(null));
  }

  @Test
  public void testIndexOf() {
    assertTrue(doubleLinkedList.add(1));
    assertTrue(doubleLinkedList.add(2));
    assertTrue(doubleLinkedList.add(3));
    assertEquals(1, doubleLinkedList.indexOf(2));
  }

  @Test
  public void testContains_whenNoElementInList() {
    assertFalse(doubleLinkedList.contains(2));
  }

  @Test
  public void testContains() {
    assertTrue(doubleLinkedList.add(1));
    assertTrue(doubleLinkedList.add(2));
    assertTrue(doubleLinkedList.add(3));
    assertTrue(doubleLinkedList.contains(2));
  }

  @Test
  public void testIterator() {
    assertTrue(doubleLinkedList.add(1));
    assertTrue(doubleLinkedList.add(2));
    assertTrue(doubleLinkedList.add(3));
    int index = 1;
    for (Integer element : doubleLinkedList) {
      assertEquals(Integer.valueOf(index), element);
      index++;
    }
  }
  
  @Test
  public void testAddAll_SimpleList_whenListIsEmpty(){
   SimpleList<Number> linkedList = new DoubleLinkedList<Number>(simpleListforEmptyList);
    assertFalse(linkedList.isEmpty());
    assertEquals(1,linkedList.get(0));
  }

 @Test
  public void testAddAll_Collection_whenListIsEmpty(){
    SimpleList<Number> linkedList = new DoubleLinkedList<Number>(linkedListforEmptyList);
    assertFalse(linkedList.isEmpty());
    assertEquals(1,linkedList.get(0));
  }

 @Test
  public void testAddAll_Array_whenListIsEmpty(){
    SimpleList<Number> linkedList = new DoubleLinkedList<Number>(subArrayForEmpty);
    assertFalse(linkedList.isEmpty());
    assertEquals(1,linkedList.get(0));
  }
 
 @Test
 public void testRetainAll_Array_whenListIsEmpty(){
   assertFalse(doubleLinkedList.retainAll(subArrayForEmpty));
 }
 
 @Test
 public void testRetainAll_Array_whenListIsNotEmpty(){
   addDataToList();
   assertTrue(doubleLinkedList.retainAll(subArray));
   assertEquals(subArray.length -1 , doubleLinkedList.size());
 }
 
 @Test
 public void testRetainAll_SimpleList_whenListIsEmpty(){
   assertFalse(doubleLinkedList.retainAll(simpleListforEmptyList));
 }
 
 @Test
 public void testRetainAll_SimpleList_whenListIsNotEmpty(){
   addDataToList();
   assertTrue(doubleLinkedList.retainAll(simpleList));
   assertEquals(simpleList.size() -1 , doubleLinkedList.size());
 }
 
 
 @Test
 public void testRetainAll_Collection_whenListIsEmpty(){
   assertFalse(doubleLinkedList.retainAll(linkedListforEmptyList));
 }
 
 @Test
 public void testRetainAll_Collection_whenListIsNotEmpty(){
   addDataToList();
   assertTrue(doubleLinkedList.retainAll(linkedList));
   assertEquals(linkedList.size() -1 , doubleLinkedList.size());
 }
 
 
 @Test
 public void testRemoveAll_Array_whenListIsEmpty(){
   assertFalse(doubleLinkedList.retainAll(subArray));
 }
 
 @Test
 public void testRemoveAll_Array_whenListIsNotEmpty(){
   addDataToList();  
   int resultSize = doubleLinkedList.size() - subArray.length + 1;
   assertTrue(doubleLinkedList.removeAll(subArray));
   assertEquals(resultSize, doubleLinkedList.size());
 }
 
 @Test
 public void testRemoveAll_Collection_whenListIsEmpty(){
   assertFalse(doubleLinkedList.removeAll(linkedListforEmptyList));
 }
 
 @Test
 public void testRemoveAll_Collection_whenListIsNotEmpty(){
   addDataToList();
   int resultSize = doubleLinkedList.size() - linkedList.size() + 1;
   assertTrue(doubleLinkedList.removeAll(linkedList));
   assertEquals(resultSize, doubleLinkedList.size());
 }
 
 @Test
 public void testRemoveAll_SimpleList_whenListIsEmpty(){
   assertFalse(doubleLinkedList.removeAll(simpleListforEmptyList));
 }
 
 @Test
 public void testRemoveAll_SimpleList_whenListIsNotEmpty(){
   addDataToList();
   int resultSize = doubleLinkedList.size() - simpleList.size() + 1;
   assertTrue(doubleLinkedList.removeAll(simpleList));
   assertEquals(resultSize, doubleLinkedList.size());
 }
 
 @Test 
 public void testSubList(){
   addDataToList();
   SimpleList<Integer> simpleList = doubleLinkedList.createSubList(4, 8);
   int count = 3 ;
   for(Integer data : simpleList){
     assertEquals(Integer.valueOf(count), data);
     count++;
   }
 }
}
