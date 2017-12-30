package org.daijie.dome.algorithm;

import org.junit.Test;
 
public class NumberSortTest {  
	
	@Test
	public void test() {
		int[] array = {8,2,5,3,9,7,6};
		NumberSort.quickSort(array,0,6);
//		NumberSort.mergeSort(array,0,6);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}   

}
