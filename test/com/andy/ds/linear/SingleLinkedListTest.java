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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.andy.ds.linear.contract.SimpleList;

public class SingleLinkedListTest {
  private SimpleList<Integer> linkedList = null;

  @Before
  public void beforeEveryMethod() {
    linkedList = new SingleLinkedList<Integer>();
  }

  @After
  public void afterEveryMethod() {
    linkedList = null;
  }

  @Test
  public void testIsEmpty() {
    assertTrue(linkedList.isEmpty());
  }

  @Test
  public void testAdd() {
    assertTrue(linkedList.add(1));
    assertTrue(linkedList.add(2));
  }

  @Test
  public void testAdd_whenParamIsNull() {
    assertTrue(linkedList.add(null));
  }

  @Test
  public void testAddAt() {
    assertTrue(linkedList.add(1));
    assertEquals(Integer.valueOf(1), linkedList.get(0));
    assertTrue(linkedList.addAt(1, 2));
    assertEquals(Integer.valueOf(2), linkedList.get(1));
    assertTrue(linkedList.addAt(2, 3));
    assertEquals(Integer.valueOf(3), linkedList.get(2));
    assertTrue(linkedList.addAt(1, 4));
    assertEquals(Integer.valueOf(4), linkedList.get(1));
    assertTrue(linkedList.addAt(0, 5));
    assertEquals(Integer.valueOf(5), linkedList.get(0));
    assertTrue(linkedList.addAt(5, 6));
    assertEquals(Integer.valueOf(6), linkedList.get(5));
  }

  @Test
  public void testAddAt_WhenDataIsNull() {
    assertTrue(linkedList.add(1));
    assertTrue(linkedList.addAt(0, null));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testAddAt_WhenIndexIs2ForEmptyList() {
    linkedList.addAt(2, 1);
  }

  @Test
  public void testRemoveAt() {
    assertTrue(linkedList.add(Integer.valueOf(1)));
    assertTrue(linkedList.add(Integer.valueOf(2)));
    assertTrue(linkedList.removeAt(0));
    assertTrue(linkedList.removeAt(0));
    assertEquals(Integer.valueOf(0), Integer.valueOf(linkedList.size()));

    assertTrue(linkedList.add(Integer.valueOf(1)));
    assertTrue(linkedList.add(Integer.valueOf(2)));
    assertTrue(linkedList.add(Integer.valueOf(3)));
    assertTrue(linkedList.removeAt(1));
    assertEquals(Integer.valueOf(2), Integer.valueOf(linkedList.size()));

    assertTrue(linkedList.add(Integer.valueOf(1)));
    assertTrue(linkedList.add(Integer.valueOf(2)));
    assertTrue(linkedList.add(Integer.valueOf(3)));
    assertTrue(linkedList.removeAt(0));
    assertEquals(Integer.valueOf(4), Integer.valueOf(linkedList.size()));

    assertTrue(linkedList.add(Integer.valueOf(1)));
    assertTrue(linkedList.add(Integer.valueOf(2)));
    assertTrue(linkedList.add(Integer.valueOf(3)));
    assertTrue(linkedList.removeAt(2));
    assertEquals(Integer.valueOf(6), Integer.valueOf(linkedList.size()));

  }

  @Test
  public void testRemoveAT_RemovingTheLastElement() {
    assertTrue(linkedList.add(Integer.valueOf(1)));
    assertTrue(linkedList.add(Integer.valueOf(2)));
    assertTrue(linkedList.removeAt(1));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testRemoveAt_whenIndexIsLessThanZero() {
    linkedList.removeAt(-1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testRemoveAt_whenIndexIsGreaterThanOrEqualToLengthOfList() {
    linkedList.removeAt(linkedList.size());
  }

  @Test
  public void testRemove() {
    for (int index = 1; index <= 10; index++) {
      assertTrue(linkedList.add(index));
    }
    assertTrue(linkedList.remove(5));
    assertEquals(Integer.valueOf(9), Integer.valueOf(linkedList.size()));

    assertTrue(linkedList.remove(10));
    assertEquals(Integer.valueOf(8), Integer.valueOf(linkedList.size()));

    assertTrue(linkedList.remove(1));
    assertEquals(Integer.valueOf(7), Integer.valueOf(linkedList.size()));

    assertEquals(Integer.valueOf(2), linkedList.get(0));
    assertEquals(Integer.valueOf(9), linkedList.get((linkedList.size() - 1)));
  }

  @Test
  public void testRemove_WhenDataIsNull() {
    assertTrue(linkedList.add(1));
    assertFalse(linkedList.remove(null));
  }

  @Test
  public void testRemoveAll_withDataAsParam() {
    for (int index = 1; index <= 10; index++) {
      assertTrue(linkedList.add(index));
    }
    for (int index = 1; index <= 10; index++) {
      assertTrue(linkedList.add(index));
    }
    assertTrue(linkedList.removeAll(5));
    assertEquals(Integer.valueOf(18), Integer.valueOf(linkedList.size()));

    assertTrue(linkedList.removeAll(1));
    assertEquals(Integer.valueOf(16), Integer.valueOf(linkedList.size()));
  }

  @Test
  public void testRemoveAll_withDataAsParam_WhenDataIsNull() {
    assertTrue(linkedList.add(null));
    assertTrue(linkedList.add(null));
    assertTrue(linkedList.add(null));
    assertTrue(linkedList.add(null));
    assertTrue(linkedList.removeAll(null));
  }

  @Test
  public void testRemoveAll_withoutParam() {
    for (int index = 1; index <= 10; index++) {
      assertTrue(linkedList.add(index));
    }
    assertTrue(linkedList.removeAll());
    assertEquals(Integer.valueOf(0), Integer.valueOf(linkedList.size()));
  }

  @Test
  public void testGet() {
    for (int index = 0; index < 100; index++) {
      linkedList.add((index + 1));
      assertEquals(Integer.valueOf((index + 1)), linkedList.get(index));
    }
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGet_whenIndexIsLessThanZero() {
    linkedList.get(-1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGet_whenIndexIsGreaterThanOrEqualToLengthOfList() {
    linkedList.get(linkedList.size());
  }

  private void addDataToList() {
    for (int index = 0; index < 10; index++) {
      linkedList.add(index);
    }
  }

  @Test
  public void testIterator_hasNext() {
    addDataToList();
    Iterator<Integer> slDO = linkedList.iterator();
    assertTrue(slDO.hasNext());
  }

  @Test
  public void testIterator() {
    addDataToList();
    int index = 0;
    for (Integer element : linkedList) {
      assertEquals(Integer.valueOf(index), element);
      index++;
    }
    assertEquals(index, linkedList.size());
  }

  @Test
  public void testIsEmpty_whenNoElement() {
    assertTrue(linkedList.isEmpty());
  }

  @Test
  public void testIsEmpty_whenElementsAreThere() {
    linkedList.add(1);
    assertFalse(linkedList.isEmpty());
  }

  @Test
  public void testGetLastNode() {
    assertTrue(linkedList.add(Integer.valueOf(1)));
    assertTrue(linkedList.add(Integer.valueOf(2)));
    assertNotNull(linkedList.getLastNode());
  }

  @Test
  public void testRemoveAll_withSameDataAsParam() {
    assertTrue(linkedList.add(1));
    assertTrue(linkedList.add(1));
    assertTrue(linkedList.removeAll(1));
  }

  @Test
  public void testRemoveAll_withDataNotPresentInList() {
    assertTrue(linkedList.add(1));
    assertTrue(linkedList.add(2));
    assertFalse(linkedList.removeAll(0));
  }

  @Test
  public void testRemove_whenNoElementInList() {
    assertFalse(linkedList.remove(null));
  }

  @Test
  public void testIndexOf_whenNoElementInList() {
    assertEquals(-1, linkedList.indexOf(null));
  }

  @Test
  public void testIndexOf() {
    assertTrue(linkedList.add(1));
    assertTrue(linkedList.add(2));
    assertTrue(linkedList.add(3));
    assertEquals(1, linkedList.indexOf(2));
  }

  @Test
  public void testContains_whenNoElementInList() {
    assertFalse(linkedList.contains(2));
  }

  @Test
  public void testContains() {
    assertTrue(linkedList.add(1));
    assertTrue(linkedList.add(2));
    assertTrue(linkedList.add(3));
    assertTrue(linkedList.contains(2));
  }
  
  @Test
  public void testHashCode(){
    Integer data = null;
    int hashCode = 1;
    for(int index =1 ; index < 11; index++) {
      data = new Integer(index);
      assertTrue(linkedList.add(data));
      hashCode = 31*hashCode + (data==null ? 0 : data.hashCode());
      assertEquals(hashCode,linkedList.hashCode());
    }
    assertTrue(linkedList.add(null));
    hashCode = 31*hashCode;
    assertEquals(hashCode,linkedList.hashCode());
  }
  
  @Test
  public void testToArray(){
    for(int index =1 ; index < 11; index++) {
      assertTrue(linkedList.add(index));
    }
    int listSize = linkedList.size();
    Integer[] intArray = linkedList.toArray(new Integer(1));
    assertEquals(listSize,intArray.length);
    int index = 0;
    for(Integer data:linkedList) {
      assertEquals(data,intArray[index]);
      index++;
    }
  }
  
  @Test
  public void testEquals_whenMatchSameObject(){
    linkedList.add(1);
    assertTrue(linkedList.equals(linkedList));
  }
  
  @Test
  public void testEquals_whenMatchDifferentObject(){
    linkedList.add(1);
    SimpleList<Integer> anotherList = new SingleLinkedList<Integer>();
    anotherList.add(1);
    assertTrue(linkedList.equals(anotherList));
  }
  
  @Test
  public void testEquals_whenMatchDifferentObjectDifferentSize(){
    linkedList.add(1);
    SimpleList<Integer> anotherList = new SingleLinkedList<Integer>();
    anotherList.add(1);
    anotherList.add(2);
    assertFalse(linkedList.equals(anotherList));
  }
  
  @Test
  public void testEquals_whenMatchDifferentObjectDifferentElements(){
    linkedList.add(1);
    SimpleList<String> anotherList = new SingleLinkedList<String>();
    anotherList.add("1");
    assertFalse(linkedList.equals(anotherList));
  }
  
  @Test
  public void testEquals_whenMatchDifferentTypeOfObject(){
    linkedList.add(1);
    String anotherObjectType = "Test";
    assertFalse(linkedList.equals(anotherObjectType));
  }
  
  @Test
  public void testAddAll_SimpleList_whenListIsEmpty(){
    SimpleList<Integer> sublinkedList = new SingleLinkedList<Integer>();
    sublinkedList.add(1);
    sublinkedList.add(2);
    sublinkedList.add(3);
    SimpleList<Number> linkedList = new SingleLinkedList<Number>(sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(1,linkedList.get(0));
  }
  
  @Test
  public void testAddAll_whenSimpleListAddedtoEnd(){
    SimpleList<Number> linkedList = new SingleLinkedList<Number>();
    linkedList.add(4);
    SimpleList<Integer> sublinkedList = new SingleLinkedList<Integer>();
    sublinkedList.add(1);
    sublinkedList.add(2);
    sublinkedList.add(3);
    linkedList.addAll(sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(1,linkedList.get(1));
  }
  
  @Test
  public void testAddAll_whenSimpleListAddedInBetween(){
    SimpleList<Number> linkedList = new SingleLinkedList<Number>();
    linkedList.add(1);
    linkedList.add(2);
    linkedList.add(6);
    SimpleList<Integer> sublinkedList = new SingleLinkedList<Integer>();
    sublinkedList.add(3);
    sublinkedList.add(4);
    sublinkedList.add(5);
    linkedList.addAll(2, sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(6,linkedList.size());
    
    for(int count = 1; count <= linkedList.size(); count++) {
      Number num = linkedList.get((count - 1));
      assertEquals(count,num);
    }
  }
  
  
  @Test
  public void testAddAll_Collection_whenListIsEmpty(){
    java.util.LinkedList<Integer> sublinkedList = new java.util.LinkedList<Integer>();
    sublinkedList.add(1);
    sublinkedList.add(2);
    sublinkedList.add(3);
    SimpleList<Number> linkedList = new SingleLinkedList<Number>(sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(1,linkedList.get(0));
  }
  
  @Test
  public void testAddAll_whenCollectionAddedtoEnd(){
    SimpleList<Number> linkedList = new SingleLinkedList<Number>();
    linkedList.add(4);
    java.util.LinkedList<Integer> sublinkedList = new java.util.LinkedList<Integer>();
    sublinkedList.add(1);
    sublinkedList.add(2);
    sublinkedList.add(3);
    linkedList.addAll(sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(1,linkedList.get(1));
  }
  
  @Test
  public void testAddAll_whenCollectionAddedInBetween(){
    SimpleList<Number> linkedList = new SingleLinkedList<Number>();
    linkedList.add(1);
    linkedList.add(2);
    linkedList.add(6);
    java.util.LinkedList<Integer> sublinkedList = new java.util.LinkedList<Integer>();
    sublinkedList.add(3);
    sublinkedList.add(4);
    sublinkedList.add(5);
    linkedList.addAll(2, sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(6,linkedList.size());
    
    for(int count = 1; count <= linkedList.size(); count++) {
      Number num = linkedList.get((count - 1));
      assertEquals(count,num);
    }
  }
  
  @Test
  public void testAddAll_Array_whenListIsEmpty(){
    Integer  []sublinkedList = {1,2,3};
    SimpleList<Number> linkedList = new SingleLinkedList<Number>(sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(1,linkedList.get(0));
  }
  
  @Test
  public void testAddAll_whenArrayAddedtoEnd(){
    SimpleList<Number> linkedList = new SingleLinkedList<Number>();
    linkedList.add(4);
    Integer  []sublinkedList = {5,6,7};
    linkedList.addAll(sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(5,linkedList.get(1));
  }
  
  @Test
  public void testAddAll_whenArrayAddedInBetween(){
    SimpleList<Number> linkedList = new SingleLinkedList<Number>();
    linkedList.add(1);
    linkedList.add(2);
    linkedList.add(6);
    Integer  []sublinkedList = {3,4,5};
    linkedList.addAll(2, sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(6,linkedList.size());
    
    for(int count = 1; count <= linkedList.size(); count++) {
      Number num = linkedList.get((count - 1));
      assertEquals(count,num);
    }
  }
}
