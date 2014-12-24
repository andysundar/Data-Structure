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

import java.io.Serializable;

public class DoubleLinkedRefDataObject<T> implements Cloneable,Serializable{
	
	private static final long serialVersionUID = -1250475451233884271L;
	private T data;
	private transient DoubleLinkedRefDataObject<T> nextReference;
	private transient DoubleLinkedRefDataObject<T> previousReference;
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public DoubleLinkedRefDataObject<T> getNextReference() {
		return nextReference;
	}

	public void setNextReference(DoubleLinkedRefDataObject<T> nextReference) {
		this.nextReference = nextReference;
	}

	public DoubleLinkedRefDataObject<T> getPreviousReference() {
		return previousReference;
	}

	public void setPreviousReference(DoubleLinkedRefDataObject<T> previousReference) {
		this.previousReference = previousReference;
	}

}
