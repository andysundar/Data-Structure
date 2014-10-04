/* Copyright [2012] Anindya Bandopadhyay
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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QueueTest {

  private Queue<String> queue;
  
  @Before
  public void setup(){
    queue = new Queue<String>();
  }
  
  @Test
  public void testEnter(){
    assertTrue(queue.enter("Test1"));
    assertEquals(1, queue.size());
  }
  
  @Test
  public void testPoll(){
    assertTrue(queue.enter("Test1"));    
    assertNotNull(queue.poll());
    assertTrue(queue.isEmpty());
  }
  
  @Test
  public void testPeek(){
    assertTrue(queue.enter("Test1"));    
    assertNotNull(queue.peek());
    assertFalse(queue.isEmpty());
  }
  
  @Test
  public void testPoll_WhenQueueIsEmpty(){
    assertNull(queue.poll());
    assertTrue(queue.isEmpty());
  }
  
  @Test
  public void testPeek_WhenQueueIsEmpty(){
    assertNull(queue.peek());
    assertTrue(queue.isEmpty());
  }
  
  
}
