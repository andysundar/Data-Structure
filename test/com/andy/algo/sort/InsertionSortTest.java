package com.andy.algo.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;

public class InsertionSortTest {

	private InsertionSort<Integer> intSort;
	private Integer dataForSort[] = {3,4,1,6,9,10,5,2,8,7};
	
	@Before
	public void setUp() throws Exception {
		intSort = new InsertionSort<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		intSort = null;
		dataForSort = null;
	}

	@Test
	public void testSort() {
		Integer sortResult[] = intSort.sort(dataForSort);
		for(int i = 0; i < sortResult.length; i++){
			int nextIndex = (i +1)==sortResult.length?i:(i+1);
			assertFalse("Insertion Sort is fail due to " + sortResult[i] + " before "+ sortResult[nextIndex]+ " in result array.", 
					(sortResult[i]>sortResult[nextIndex]));
		}	
	}

}
