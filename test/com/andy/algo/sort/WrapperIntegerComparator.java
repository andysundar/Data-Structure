/**
 * @Author Anindya Bandopadhyay
 *
 * DataStructure\com.andy.algo.sort.WrapperObject.java
 *
 * @Created on Jan 26, 2015  4:10:53 PM
 */

package com.andy.algo.sort;

import java.util.Comparator;

public class WrapperIntegerComparator implements Comparator<Integer> {

  @Override
  public int compare(Integer x, Integer y) {
    if (x < y)
      return -1;
    else
      return ((x == y) ? 0 : 1);
  }

}
