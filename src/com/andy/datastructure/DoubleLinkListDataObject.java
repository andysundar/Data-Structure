package com.andy.datastructure;

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
