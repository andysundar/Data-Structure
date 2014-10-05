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
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
  public void testRemove_whenDataPresentInList() {
    assertTrue(circularLinkedList.add(1));
    assertTrue(circularLinkedList.remove(1));
  }

  @Test
  public void testRemove_whenDataNotPresentInList() {
    assertTrue(circularLinkedList.add(1));
    assertFalse(circularLinkedList.remove(2));
  }

  @Test
  public void testRemove_whenListIsEmpty() {
    assertFalse(circularLinkedList.remove(2));
  }

  @Test
  public void testRemove_whenDataInMiddleOfTheList() {
    assertTrue(circularLinkedList.add(1));
    assertTrue(circularLinkedList.add(2));
    assertTrue(circularLinkedList.add(3));
    assertTrue(circularLinkedList.remove(3));
    checkStartAndLast();
  }

  @Test
  public void testRemove_whenDataIsNull() {
    assertTrue(circularLinkedList.add(null));
    assertTrue(circularLinkedList.remove(null));
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
}
