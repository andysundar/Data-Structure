package com.andy.datastructure;

public class DataObject<T> {
	private T data;
	private DataObject<T> nextReference;
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public DataObject<T> getNextReference() {
		return nextReference;
	}

	public void setNextReference(DataObject<T> nextReference) {
		this.nextReference = nextReference;
	}

}
