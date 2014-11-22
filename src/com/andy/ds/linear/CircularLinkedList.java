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
 *
 * @Author Anindya Bandopadhyay
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

import java.util.Collection;

import com.andy.adt.DoubleLinkedRefDataObject;
import com.andy.ds.linear.contract.SimpleList;

public class CircularLinkedList<T> extends DoubleLinkedList<T> {
  
public CircularLinkedList(){
    
  }
  
  public CircularLinkedList(SimpleList<? extends T> list){
    this();
    addAll(list);
  }
  
  public CircularLinkedList(Collection<? extends T> collection){
    this();
    addAll(collection);
  }
  
  public CircularLinkedList(T array[]){
    this();
    addAll(array);
  }

  
  /**
   * Get the last node and put the reference of start node into last node
   * next reference.
   */
  private void makeCircularLink() {
    DoubleLinkedRefDataObject<T> lastNode = getLastNode();
    DoubleLinkedRefDataObject<T> startNode = getStartNode();
    if (lastNode != null) {
      lastNode.setNextReference(getStartNode());
    }
    if (startNode != null) {
      startNode.setPreviousReference(getLastNode());
    }
  }

  @Override
  public boolean add(T data) {
    boolean flag = super.add(data);
    makeCircularLink();
    return flag;
  }

  @Override
  public boolean addAt(int index, T data) {
    boolean flag = super.addAt(index, data);
    makeCircularLink();
    return flag;
  }

  @Override
  public boolean removeFirstOccurance(T data) {
    DoubleLinkedRefDataObject<T> findToBeDeleteNode = getStartNode();
    while ((findToBeDeleteNode != null) && (!isEqualData(data, findToBeDeleteNode.getData()))) {
      findToBeDeleteNode = findToBeDeleteNode.getNextReference();
      if (getStartNode().equals(findToBeDeleteNode)) {
        break;
      }
    }

    boolean flag = false;
    if ((findToBeDeleteNode != null) && (isEqualData(data, findToBeDeleteNode.getData()))) {
      flag = unLinkNode(findToBeDeleteNode);
    }
    makeCircularLink();
    return flag;
  }

  @Override
  public boolean removeAll(T data) {
    boolean flag = super.removeAll(data);
    makeCircularLink();
    return flag;
  }

  @Override
  public boolean removeAt(int index) {
    boolean flag = super.removeAt(index);
    makeCircularLink();
    return flag;
  }

  @Override
  public boolean removeAll() {
    boolean flag = super.removeAll();
    makeCircularLink();
    return flag;
  }

  @Override
  public boolean isEmpty() {
    return (getStartNode() == null && getLastNode() == null);
  }

  @Override
  public boolean addAll(int index, SimpleList<? extends T> list) {
    boolean flag = super.addAll(index,list);
    makeCircularLink();
    return flag;
  }
  
  @Override
  public boolean addAll(int index, Collection<? extends T> list) {
    boolean flag = super.addAll(index,list);
    makeCircularLink();
    return flag;
  }
  
  @Override
  public boolean addAll(int index,T array[]) {
    boolean flag = super.addAll(index,array);
    makeCircularLink();
    return flag;
  }
}
