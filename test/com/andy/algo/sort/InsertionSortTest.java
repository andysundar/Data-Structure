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

package com.andy.algo.sort;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		 intSort.sort(dataForSort);
		for(int i = 0; i < dataForSort.length; i++){
			int nextIndex = (i +1)==dataForSort.length?i:(i+1);
			assertFalse("Insertion Sort is fail due to " + dataForSort[i] + " before "+ dataForSort[nextIndex]+ " in result array.", 
					(dataForSort[i]>dataForSort[nextIndex]));
		}	
	}
	
	@Test
  public void testSort_withComparator() {
     intSort.sort(dataForSort, new WrapperIntegerComparator());
    for(int i = 0; i < dataForSort.length; i++){
      int nextIndex = (i +1)== dataForSort.length?i:(i+1);
      assertFalse("Insertion Sort is fail due to " + dataForSort[i] + " before "+ dataForSort[nextIndex]+ " in result array.", 
          (dataForSort[i]>dataForSort[nextIndex]));
    } 
  }

}
