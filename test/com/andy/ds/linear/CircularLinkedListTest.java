/**
 * @Author Anindya Bandopadhyay
 *
 * DataStructure\com.andy.ds.linear.CircularLinkedListTest.java
 *
 * @Created on Mar 22, 2014  11:48:32 PM
 */

package com.andy.ds.linear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.andy.adt.DoubleLinkedRefDataObject;
import com.andy.ds.linear.contract.SimpleList;

public class CircularLinkedListTest {

  private SimpleList<Integer> circularLinkedList;

  @Before
  public void setUp() throws Exception {
    circularLinkedList = new CircularLinkedList<Integer>();
  }

  @After
  public void tearDown() throws Exception {
    circularLinkedList = null;
  }

  @Test
  public void testAdd() {
    assertTrue(circularLinkedList.add(1));
    assertTrue(circularLinkedList.add(2));
    checkStartAndLast();
  }

  private void checkStartAndLast() {
    assertEquals(circularLinkedList.getLastNode(), circularLinkedList.getStartNode().getPreviousReference());
    assertEquals(circularLinkedList.getStartNode(), circularLinkedList.getLastNode().getNextReference());
  }

  @Test
  public void testAddAt() {
    assertTrue(circularLinkedList.add(2));
    assertTrue(circularLinkedList.addAt(0, 1));
    checkStartAndLast();
  }

  @Test
  public void testIsEmpty_whenListHaveOneElement() {
    assertTrue(circularLinkedList.add(1));
    assertFalse(circularLinkedList.isEmpty());
    checkStartAndLast();
  }

  @Test
  public void testIsEmpty_whenListHaveNoElement() {
    assertTrue(circularLinkedList.isEmpty());
  }

  @Test
  public void testRemoveFirstOccurance_whenDataPresentInList() {
    assertTrue(circularLinkedList.add(1));
    assertTrue(circularLinkedList.removeFirstOccurance(1));
  }

  @Test
  public void testRemoveFirstOccurance_whenDataNotPresentInList() {
    assertTrue(circularLinkedList.add(1));
    assertFalse(circularLinkedList.removeFirstOccurance(2));
  }

  @Test
  public void testRemoveFirstOccurance_whenListIsEmpty() {
    assertFalse(circularLinkedList.removeFirstOccurance(2));
  }

  @Test
  public void testRemoveFirstOccurance_whenDataInMiddleOfTheList() {
    assertTrue(circularLinkedList.add(1));
    assertTrue(circularLinkedList.add(2));
    assertTrue(circularLinkedList.add(3));
    assertTrue(circularLinkedList.removeFirstOccurance(3));
    checkStartAndLast();
  }

  @Test
  public void testRemoveFirstOccurance_whenDataIsNull() {
    assertTrue(circularLinkedList.add(null));
    assertTrue(circularLinkedList.removeFirstOccurance(null));
  }

  @Test
  public void testRemoveAll_whenSameDataPresentInListMultipleTimes() {
    assertTrue(circularLinkedList.add(1));
    assertTrue(circularLinkedList.add(1));
    assertTrue(circularLinkedList.removeAll(1));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testRemoveAt_whenNoElementInList() {
    circularLinkedList.removeAt(1);
  }

  @Test
  public void testRemoveAll() {
    assertTrue(circularLinkedList.add(1));
    assertTrue(circularLinkedList.add(2));
    assertTrue(circularLinkedList.removeAll());
  }

  @Test
  public void testRemoveAll_whenDataPresentInListOnlyOnce() {
    assertTrue(circularLinkedList.add(1));
    assertTrue(circularLinkedList.add(2));
    assertTrue(circularLinkedList.removeAll(1));
    assertEquals(1, circularLinkedList.size());
    checkStartAndLast();
  }

  @Test
  public void testRemoveAt() {
    assertTrue(circularLinkedList.add(1));
    assertTrue(circularLinkedList.add(2));
    assertTrue(circularLinkedList.add(3));
    assertTrue(circularLinkedList.removeAt(2));
    checkStartAndLast();
  }
  
  @Test
  public void testIterator() {
    assertTrue(circularLinkedList.add(1));
    assertTrue(circularLinkedList.add(2));
    assertTrue(circularLinkedList.add(3));
    int index = 1;
    for (Integer element : circularLinkedList) {
      assertEquals(Integer.valueOf(index), element);
      index++;
    }
  }
  
  
  @Test
  public void testAddAll_SimpleList_whenListIsEmpty(){
    SimpleList<Integer> sublinkedList = new CircularLinkedList<Integer>();
    sublinkedList.add(1);
    sublinkedList.add(2);
    sublinkedList.add(3);
    SimpleList<Number> linkedList = new CircularLinkedList<Number>(sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(1,linkedList.get(0));
  }
  
  @Test
  public void testAddAll_whenSimpleListAddedtoEnd(){
    SimpleList<Number> linkedList = new CircularLinkedList<Number>();
    linkedList.add(4);
    SimpleList<Integer> sublinkedList = new CircularLinkedList<Integer>();
    sublinkedList.add(1);
    sublinkedList.add(2);
    sublinkedList.add(3);
    linkedList.addAll(sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(4,linkedList.get(1));
  }
  
  @Test
  public void testAddAll_whenSimpleListAddedInBetween(){
    SimpleList<Number> linkedList = new CircularLinkedList<Number>();
    linkedList.add(1);
    linkedList.add(2);
    linkedList.add(6);
    SimpleList<Integer> sublinkedList = new CircularLinkedList<Integer>();
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
    SimpleList<Number> linkedList = new CircularLinkedList<Number>(sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(1,linkedList.get(0));
  }
  
  @Test
  public void testAddAll_whenCollectionAddedtoEnd(){
    SimpleList<Number> linkedList = new CircularLinkedList<Number>();
    linkedList.add(4);
    java.util.LinkedList<Integer> sublinkedList = new java.util.LinkedList<Integer>();
    sublinkedList.add(1);
    sublinkedList.add(2);
    sublinkedList.add(3);
    linkedList.addAll(sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(4,linkedList.get(1));
  }
  
  @Test
  public void testAddAll_whenCollectionAddedInBetween(){
    SimpleList<Number> linkedList = new CircularLinkedList<Number>();
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
    SimpleList<Number> linkedList = new CircularLinkedList<Number>(sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(1,linkedList.get(0));
  }
  
  @Test
  public void testAddAll_whenArrayAddedtoEnd(){
    SimpleList<Number> linkedList = new CircularLinkedList<Number>();
    linkedList.add(4);
    Integer  []sublinkedList = {5,6,7};
    linkedList.addAll(sublinkedList);
    assertFalse(linkedList.isEmpty());
    assertEquals(4,linkedList.get(1));
  }
  
  @Test
  public void testAddAll_whenArrayAddedInBetween(){
    SimpleList<Number> linkedList = new CircularLinkedList<Number>();
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
  public void testLinkOfCircularLinkedList(){
    SimpleList<Number> linkedList = new CircularLinkedList<Number>();
    linkedList.add(1);
    linkedList.add(2);
    linkedList.add(6);
    SimpleList<Integer> sublinkedList = new CircularLinkedList<Integer>();
    sublinkedList.add(3);
    sublinkedList.add(4);
    sublinkedList.add(5);
    linkedList.addAll(2, sublinkedList);
    
    linkedList.removeFirstOccurance(4);
    linkedList.removeAt(3);

    int length = linkedList.size();
    for(Number num:linkedList){
      assertNotNull(num);
    }
    DoubleLinkedRefDataObject<Number> previousNode = linkedList.getStartNode().getPreviousReference();
    while (previousNode != null) {
      
      assertNotNull(previousNode.getData());
      previousNode = previousNode.getPreviousReference();
      length--;
      if(length < 1) {
        break;
      }
    }

  }
}
