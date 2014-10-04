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

import com.andy.adt.SingleLinkedRefDataObject;


public class Queue<E>{
  
  private LinkedList<E> queue = new LinkedList<E>();
 
  
  public boolean enter(E data) {
     return queue.add(data);
  }
  
  public E poll() {
    E data = peek();
    if(data == null){
      return null;
    }
    queue.remove(data);
    return data;
  }

  public E peek() {
    SingleLinkedRefDataObject<E> node = queue.getStartNode();
    if(node == null){
      return null;
    }
    return node.getData();
  }
  public int size(){
    return queue.size();
  }
  
  public boolean isEmpty(){
    return queue.isEmpty();
  }
}
