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
package com.andy.adt;

public class TreeDataObject<T extends Comparable<T>> {
	private boolean colour;
	
	private T data;
	
	private TreeDataObject<T> leftNode;
	private TreeDataObject<T> rightNode;
	private TreeDataObject<T> parentNode;
	
	public static final boolean RED = false;
	public static final boolean BLACK = true;
	
	public T getData() {
		return data;
	}
	public TreeDataObject<T> getLeftNode() {
		return leftNode;
	}
	public TreeDataObject<T> getRightNode() {
		return rightNode;
	}
	public void setData(T data) {
		this.data = data;
	}
	public void setLeftNode(TreeDataObject<T> leftNode) {
		this.leftNode = leftNode;
	}
	public void setRightNode(TreeDataObject<T> rightNode) {
		this.rightNode = rightNode;
	}
	
	public TreeDataObject<T> getParentNode() {
		return parentNode;
	}
	public void setParentNode(TreeDataObject<T> parentNode) {
		this.parentNode = parentNode;
	}
	public boolean getColour() {
		return colour;
	}
	public void setColour(boolean colour) {
		this.colour = colour;
	}
	
}
