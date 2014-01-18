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

public class BinaryTreeDataObject<T extends Comparable<T>> {
	private byte colour;
	private T data;
	private BinaryTreeDataObject<T> leftNode;
	private BinaryTreeDataObject<T> rightNode;
	private BinaryTreeDataObject<T> parentNode;
	
	public T getData() {
		return data;
	}
	public BinaryTreeDataObject<T> getLeftNode() {
		return leftNode;
	}
	public BinaryTreeDataObject<T> getRightNode() {
		return rightNode;
	}
	public void setData(T data) {
		this.data = data;
	}
	public void setLeftNode(BinaryTreeDataObject<T> leftNode) {
		this.leftNode = leftNode;
	}
	public void setRightNode(BinaryTreeDataObject<T> rightNode) {
		this.rightNode = rightNode;
	}
	
	public BinaryTreeDataObject<T> getParentNode() {
		return parentNode;
	}
	public void setParentNode(BinaryTreeDataObject<T> parentNode) {
		this.parentNode = parentNode;
	}
	public byte getColour() {
		return colour;
	}
	public void setColour(byte colour) {
		this.colour = colour;
	}
	
}
