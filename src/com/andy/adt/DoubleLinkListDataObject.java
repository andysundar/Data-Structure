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
<<<<<<< HEAD
=======
 
>>>>>>> f008bdf68fa6ca54317ed3429c250b04ccc94133
package com.andy.adt;

public class DoubleLinkListDataObject<T> {
	private T data;
	private DoubleLinkListDataObject<T> nextReference;
	private DoubleLinkListDataObject<T> previousReference;
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public DoubleLinkListDataObject<T> getNextReference() {
		return nextReference;
	}

	public void setNextReference(DoubleLinkListDataObject<T> nextReference) {
		this.nextReference = nextReference;
	}

	public DoubleLinkListDataObject<T> getPreviousReference() {
		return previousReference;
	}

	public void setPreviousReference(DoubleLinkListDataObject<T> previousReference) {
		this.previousReference = previousReference;
	}

}
