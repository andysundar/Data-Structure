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

import com.andy.adt.DoubleLinkListDataObject;


public class CircularLinkedList<T> extends DoubleLinkedList<T> {
	/**
	 * Get the last node and put the reference of start node into last node 
	 * next reference.
	 */
	private void makeCircularLink() {
		DoubleLinkListDataObject<T> lastNode = getLastNode();
		if(lastNode != null) {
			lastNode.setNextReference(getStartNode());
		}
	}
	
	@Override
	public boolean add(T data){
		boolean flag = super.add(data);
		makeCircularLink();
		return flag;
	}
	
	@Override
	public boolean addAt(T data,int index) {
		boolean flag = super.addAt(data,index);
		makeCircularLink();
		return flag;
	}
	
	@Override
	public boolean remove(T data) {
		boolean flag = super.remove(data); 
		makeCircularLink();
		return flag;
	}
	
	@Override
	public boolean removeAll(T data){
		boolean flag = super.removeAll(data);
		makeCircularLink();
		return flag;
	}
	
	@Override 
	public boolean removeAt(int index){
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
	
}
