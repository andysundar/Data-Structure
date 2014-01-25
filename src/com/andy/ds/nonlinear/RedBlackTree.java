/**
 * @Author Anindya Bandopadhyay
 *
 * DataStructure\com.andy.ds.nonlinear.RedBlackTree.java
 *
 * @Created on Jan 18, 2014  10:31:44 PM
 */

package com.andy.ds.nonlinear;

import com.andy.adt.TreeDataObject;

public class RedBlackTree<T extends Comparable<T>> extends BinaryTree<T> {

	public TreeDataObject<T> rotateLeft(TreeDataObject<T> h){
		TreeDataObject<T> temp = h.getRightNode();
		h.setRightNode(temp.getLeftNode());
		temp.setLeftNode(h);
		temp.setColour(h.getColour());
		h.setColour(TreeDataObject.RED);
		return temp;
	}
	
	public TreeDataObject<T> rotateRight(TreeDataObject<T> h){
		TreeDataObject<T> temp = h.getLeftNode();
		h.setLeftNode(temp.getRightNode());
		temp.setRightNode(h);
		temp.setColour(h.getColour());
		h.setColour(TreeDataObject.RED);
		return temp;
	}
}
