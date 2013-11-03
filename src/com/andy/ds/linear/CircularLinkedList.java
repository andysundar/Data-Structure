/**
 * @Author Anindya Bandopadhyay
 *
 * DataStructure\com.andy.ds.linear.CircularLinkedList.java
 *
 * @Created on Nov 3, 2013  7:27:46 AM
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
