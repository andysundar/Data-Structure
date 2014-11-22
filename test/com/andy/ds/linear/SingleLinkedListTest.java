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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.andy.ds.linear.contract.SimpleList;

public class SingleLinkedListTest {
  private SimpleList<Integer> singleLinkedList = null;
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  
  @Before
  public void beforeEveryMethod() {
    singleLinkedList = new SingleLinkedList<Integer>();
  }

  @After
  public void afterEveryMethod() {
    singleLinkedList = null;
  }

  @Test
  public void testIsEmpty() {
    assertTrue(singleLinkedList.isEmpty());
  }

  @Test
  public void testAdd() {
    assertTrue(singleLinkedList.add(1));
    assertTrue(singleLinkedList.add(2));
  }

  @Test
  public void testAdd_whenParamIsNull() {
    assertTrue(singleLinkedList.add(null));
  }

  @Test
  public void testAddAt() {
    assertTrue(singleLinkedList.add(1));
    assertEquals(Integer.valueOf(1), singleLinkedList.get(0));
    assertTrue(singleLinkedList.addAt(1, 2));
    assertEquals(Integer.valueOf(2), singleLinkedList.get(1));
    assertTrue(singleLinkedList.addAt(2, 3));
    assertEquals(Integer.valueOf(3), singleLinkedList.get(2));
    assertTrue(singleLinkedList.addAt(1, 4));
    assertEquals(Integer.valueOf(4), singleLinkedList.get(1));
    assertTrue(singleLinkedList.addAt(0, 5));
    assertEquals(Integer.valueOf(5), singleLinkedList.get(0));
    assertTrue(singleLinkedList.addAt(5, 6));
    assertEquals(Integer.valueOf(6), singleLinkedList.get(5));
  }

  @Test
  public void testAddAt_WhenDataIsNull() {
    assertTrue(singleLinkedList.add(1));
    assertTrue(singleLinkedList.addAt(0, null));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testAddAt_WhenIndexIs2ForEmptyList() {
    singleLinkedList.addAt(2, 1);
  }

  @Test
  public void testRemoveAt() {
    assertTrue(singleLinkedList.add(Integer.valueOf(1)));
    assertTrue(singleLinkedList.add(Integer.valueOf(2)));
    assertTrue(singleLinkedList.removeAt(0));
    assertTrue(singleLinkedList.removeAt(0));
    assertEquals(Integer.valueOf(0), Integer.valueOf(singleLinkedList.size()));

    assertTrue(singleLinkedList.add(Integer.valueOf(1)));
    assertTrue(singleLinkedList.add(Integer.valueOf(2)));
    assertTrue(singleLinkedList.add(Integer.valueOf(3)));
    assertTrue(singleLinkedList.removeAt(1));
    assertEquals(Integer.valueOf(2), Integer.valueOf(singleLinkedList.size()));

    assertTrue(singleLinkedList.add(Integer.valueOf(1)));
    assertTrue(singleLinkedList.add(Integer.valueOf(2)));
    assertTrue(singleLinkedList.add(Integer.valueOf(3)));
    assertTrue(singleLinkedList.removeAt(0));
    assertEquals(Integer.valueOf(4), Integer.valueOf(singleLinkedList.size()));

    assertTrue(singleLinkedList.add(Integer.valueOf(1)));
    assertTrue(singleLinkedList.add(Integer.valueOf(2)));
    assertTrue(singleLinkedList.add(Integer.valueOf(3)));
    assertTrue(singleLinkedList.removeAt(2));
    assertEquals(Integer.valueOf(6), Integer.valueOf(singleLinkedList.size()));

  }

  @Test
  public void testRemoveAT_RemovingTheLastElement() {
    assertTrue(singleLinkedList.add(Integer.valueOf(1)));
    assertTrue(singleLinkedList.add(Integer.valueOf(2)));
    assertTrue(singleLinkedList.removeAt(1));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testRemoveAt_whenIndexIsLessThanZero() {
    singleLinkedList.removeAt(-1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testRemoveAt_whenIndexIsGreaterThanOrEqualToLengthOfList() {
    singleLinkedList.removeAt(singleLinkedList.size());
  }

  @Test
  public void testRemoveFirstOccurance() {
    for (int index = 1; index <= 10; index++) {
      assertTrue(singleLinkedList.add(index));
    }
    assertTrue(singleLinkedList.removeFirstOccurance(5));
    assertEquals(Integer.valueOf(9), Integer.valueOf(singleLinkedList.size()));

    assertTrue(singleLinkedList.removeFirstOccurance(10));
    assertEquals(Integer.valueOf(8), Integer.valueOf(singleLinkedList.size()));

    assertTrue(singleLinkedList.removeFirstOccurance(1));
    assertEquals(Integer.valueOf(7), Integer.valueOf(singleLinkedList.size()));

    assertEquals(Integer.valueOf(2), singleLinkedList.get(0));
    assertEquals(Integer.valueOf(9), singleLinkedList.get((singleLinkedList.size() - 1)));
  }

  @Test
  public void testRemoveFirstOccurance_WhenDataIsNull() {
    assertTrue(singleLinkedList.add(1));
    assertFalse(singleLinkedList.removeFirstOccurance(null));
  }

  @Test
  public void testRemoveAll_withDataAsParam() {
    for (int index = 1; index <= 10; index++) {
      assertTrue(singleLinkedList.add(index));
    }
    for (int index = 1; index <= 10; index++) {
      assertTrue(singleLinkedList.add(index));
    }
    assertTrue(singleLinkedList.removeAll(5));
    assertEquals(Integer.valueOf(18), Integer.valueOf(singleLinkedList.size()));

    assertTrue(singleLinkedList.removeAll(1));
    assertEquals(Integer.valueOf(16), Integer.valueOf(singleLinkedList.size()));
  }

  @Test
  public void testRemoveAll_withDataAsParam_WhenDataIsNull() {
    Integer i = null;
    assertTrue(singleLinkedList.add(i));
    assertTrue(singleLinkedList.add(i));
    assertTrue(singleLinkedList.add(i));
    assertTrue(singleLinkedList.add(i));
    assertTrue(singleLinkedList.removeAll(i));
  }

  @Test
  public void testRemoveAll_withoutParam() {
    for (int index = 1; index <= 10; index++) {
      assertTrue(singleLinkedList.add(index));
    }
    assertTrue(singleLinkedList.removeAll());
    assertEquals(Integer.valueOf(0), Integer.valueOf(singleLinkedList.size()));
  }

  @Test
  public void testGet() {
    for (int index = 0; index < 100; index++) {
      singleLinkedList.add((index + 1));
      assertEquals(Integer.valueOf((index + 1)), singleLinkedList.get(index));
    }
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGet_whenIndexIsLessThanZero() {
    singleLinkedList.get(-1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGet_whenIndexIsGreaterThanOrEqualToLengthOfList() {
    singleLinkedList.get(singleLinkedList.size());
  }

  private void addDataToList() {
    for (int index = 0; index < 10; index++) {
      singleLinkedList.add(index);
    }
  }

  @Test
  public void testIterator_hasNext() {
    addDataToList();
    Iterator<Integer> slDO = singleLinkedList.iterator();
    assertTrue(slDO.hasNext());
  }

  @Test
  public void testIterator() {
    addDataToList();
    int index = 0;
    for (Integer element : singleLinkedList) {
      assertEquals(Integer.valueOf(index), element);
      index++;
    }
    assertEquals(index, singleLinkedList.size());
  }

  @Test
  public void testIsEmpty_whenNoElement() {
    assertTrue(singleLinkedList.isEmpty());
  }

  @Test
  public void testIsEmpty_whenElementsAreThere() {
    singleLinkedList.add(1);
    assertFalse(singleLinkedList.isEmpty());
  }

  @Test
  public void testGetLastNode() {
    assertTrue(singleLinkedList.add(Integer.valueOf(1)));
    assertTrue(singleLinkedList.add(Integer.valueOf(2)));
    assertNotNull(singleLinkedList.getLastNode());
  }

  @Test
  public void testRemoveAll_withSameDataAsParam() {
    assertTrue(singleLinkedList.add(1));
    assertTrue(singleLinkedList.add(1));
    assertTrue(singleLinkedList.removeAll(1));
  }

  @Test
  public void testRemoveAll_withDataNotPresentInList() {
    assertTrue(singleLinkedList.add(1));
    assertTrue(singleLinkedList.add(2));
    assertFalse(singleLinkedList.removeAll(0));
  }

  @Test
  public void testRemoveFirstOccurance_whenNoElementInList() {
    assertFalse(singleLinkedList.removeFirstOccurance(null));
  }

  @Test
  public void testIndexOf_whenNoElementInList() {
    assertEquals(-1, singleLinkedList.indexOf(null));
  }

  @Test
  public void testIndexOf() {
    assertTrue(singleLinkedList.add(1));
    assertTrue(singleLinkedList.add(2));
    assertTrue(singleLinkedList.add(3));
    assertEquals(1, singleLinkedList.indexOf(2));
  }

  @Test
  public void testContains_whenNoElementInList() {
    assertFalse(singleLinkedList.contains(2));
  }

  @Test
  public void testContains() {
    assertTrue(singleLinkedList.add(1));
    assertTrue(singleLinkedList.add(2));
    assertTrue(singleLinkedList.add(3));
    assertTrue(singleLinkedList.contains(2));
  }
  
  @Test
  public void testHashCode(){
    Integer data = null;
    int hashCode = 1;
    for(int index =1 ; index < 11; index++) {
      data = new Integer(index);
      assertTrue(singleLinkedList.add(data));
      hashCode = 31*hashCode + (data==null ? 0 : data.hashCode());
      assertEquals(hashCode,singleLinkedList.hashCode());
    }
    assertTrue(singleLinkedList.add(null));
    hashCode = 31*hashCode;
    assertEquals(hashCode,singleLinkedList.hashCode());
  }
  
  @Test
  public void testToArray_withParameter(){
    for(int index =1 ; index < 11; index++) {
      assertTrue(singleLinkedList.add(index));
    }
    int listSize = singleLinkedList.size();
    Integer[] intArray = singleLinkedList.toArray(new Integer(1));
    assertEquals(listSize,intArray.length);
    int index = 0;
    for(Integer data:singleLinkedList) {
      assertEquals(data,intArray[index]);
      index++;
    }
  }
  
  @Test
  public void testEquals_whenMatchSameObject(){
    singleLinkedList.add(1);
    assertTrue(singleLinkedList.equals(singleLinkedList));
  }
  
  @Test
  public void testEquals_whenMatchDifferentObject(){
    singleLinkedList.add(1);
    SimpleList<Integer> anotherList = new SingleLinkedList<Integer>();
    anotherList.add(1);
    assertTrue(singleLinkedList.equals(anotherList));
  }
  
  @Test
  public void testEquals_whenMatchDifferentObjectDifferentSize(){
    singleLinkedList.add(1);
    SimpleList<Integer> anotherList = new SingleLinkedList<Integer>();
    anotherList.add(1);
    anotherList.add(2);
    assertFalse(singleLinkedList.equals(anotherList));
  }
  
  @Test
  public void testEquals_whenMatchDifferentObjectDifferentElements(){
    singleLinkedList.add(1);
    SimpleList<String> anotherList = new SingleLinkedList<String>();
    anotherList.add("1");
    assertFalse(singleLinkedList.equals(anotherList));
  }
  
  @Test
  public void testEquals_whenMatchDifferentTypeOfObject(){
    singleLinkedList.add(1);
    String anotherObjectType = "Test";
    assertFalse(singleLinkedList.equals(anotherObjectType));
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
  
  @Test
  public void testToArray(){
    addDataToList();
    int listSize = singleLinkedList.size();
    Object[] intArray = singleLinkedList.toArray();
    assertEquals(listSize,intArray.length);
    int index = 0;
    for(Integer data:singleLinkedList) {
      assertEquals(data,intArray[index]);
      index++;
    }
  }
  
  @Test(expected=IndexOutOfBoundsException.class)
  public void testSubList_whenListIsEmpty() {
    singleLinkedList.createSubList(0, 10);
  }
  
  @Test(expected=IndexOutOfBoundsException.class)
  public void testSubList_withStartIndexViolation(){
    addDataToList();
    singleLinkedList.createSubList(-23, 4);
  }
  
  @Test(expected=IndexOutOfBoundsException.class)
  public void testSubList_withEndIndexViolation(){
    addDataToList();
    singleLinkedList.createSubList(0, 12);
  }
  
  @Test
  public void testSubList_withStartEndIndexViolation(){
    addDataToList();
    thrown.expect(IndexOutOfBoundsException.class);
    thrown.expectMessage(SingleLinkedList.START_END_INDEX_MESSAGE);
    singleLinkedList.createSubList(10, 1);
  }
  
  @Test 
  public void testSubList(){
    addDataToList();
    SimpleList<Integer> simpleList = singleLinkedList.createSubList(4, 8);
    int count = 3 ;
    for(Integer data : simpleList){
      assertEquals(Integer.valueOf(count), data);
      count++;
    }
  }
}
