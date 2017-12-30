package org.daijie.dome.algorithm;

import org.junit.Test;

public class BinarySearchTest {
	
	@Test
	public void test() {
		BinarySearch bs = new BinarySearch();
		int[] data = {2,5,6,8,15};
		System.out.println(bs.searchBinary(data, 6));
		System.out.println(bs.searchLine(data, 6));
		System.out.println(bs.searchLoop(data, 6));
		System.out.println(bs.searchRecursive(data, 0, data.length-1, 6));
	}
}
