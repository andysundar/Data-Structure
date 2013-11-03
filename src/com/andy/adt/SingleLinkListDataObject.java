package com.andy.adt;

public class SingleLinkListDataObject<T> {
	private T data;
	private SingleLinkListDataObject<T> nextReference;
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public SingleLinkListDataObject<T> getNextReference() {
		return nextReference;
	}

	public void setNextReference(SingleLinkListDataObject<T> nextReference) {
		this.nextReference = nextReference;
	}
}
