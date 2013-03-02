package com.andy.algo.sort;



public class InsertionSort<T extends Comparable<T>>{

	private T temp;
	
	public T[] sort(T[] list) {
		int size = list.length;
		for(int i = 0; i < size; i++) {
			int index = i;
			int subIndex = (i - 1);
			while(subIndex > -1 && (list[subIndex].compareTo(list[index])== 1)){
				temp = list[index];
				list[index] = list[subIndex];
				list[subIndex] = temp;
				subIndex--;
				index--;
			}
		}
		return list;
	}
}
