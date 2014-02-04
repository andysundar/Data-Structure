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

	/**
	 * Left rotate
	 * 						a
	 * 						 \
	 * 						  b
	 * here node a is marked as 'subRoot' and b is marked as 'temp'
	 * after left rotation
	 * 						b
	 * 					   /
	 * 					  a 					
	 * @param 'subRoot' is sub-root where new node will be placed as child.
	 * @return return the new sub-root after rotation.
	 */
	public TreeDataObject<T> rotateLeft(TreeDataObject<T> subRoot){
		TreeDataObject<T> temp = subRoot.getRightChildNode();
		subRoot.setRightChildNode(temp.getLeftChildNode());
		subRoot.getRightChildNode().setParentNode(subRoot);
		temp.setLeftChildNode(subRoot);
		temp.setParentNode(subRoot.getParentNode());
		subRoot.setParentNode(temp);
		temp.setColour(subRoot.getColour());
		subRoot.setColour(TreeDataObject.RED);
		return temp;
	}
	
	/**
	 * Right rotate
	 * 						a
	 * 					   /	 
	 * 					  b
	 * here node a is marked as 'subRoot' and b is marked as 'temp'
	 * after right rotation
	 * 						b
	 * 					   	 \	
	 * 					      a 		 
	 * @param 'subRoot' is sub-root where new node will be placed as child.
	 * @return  return the new sub-root after rotation.
	 */
	public TreeDataObject<T> rotateRight(TreeDataObject<T> subRoot){
		TreeDataObject<T> temp = subRoot.getLeftChildNode();
		subRoot.setLeftChildNode(temp.getRightChildNode());
		subRoot.getLeftChildNode().setParentNode(subRoot);
		temp.setRightChildNode(subRoot);
		subRoot.setParentNode(temp);
		temp.setColour(subRoot.getColour());
		subRoot.setColour(TreeDataObject.RED);
		return temp;
	}
	
	/**
	 * Flip the color of sub root and there childs
	 * @param subRoot is sub-root where colour will be flip 
	 */
	public void flipColors(TreeDataObject<T> subRoot){
		subRoot.setColour(!subRoot.getColour());
		subRoot.getLeftChildNode().setColour(!subRoot.getLeftChildNode().getColour());
		subRoot.getRightChildNode().setColour(!subRoot.getRightChildNode().getColour());
	}
}
