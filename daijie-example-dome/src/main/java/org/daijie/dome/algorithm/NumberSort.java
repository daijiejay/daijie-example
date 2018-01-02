package org.daijie.dome.algorithm;

/**
 * 数组排序
 * @author daijie
 * @since 2017年6月5日
 */
public class NumberSort {   
	//私有构造方法，禁止实例化  
	private NumberSort() {   
		super();   
	}    
	//冒泡法排序 
	public static void bubbleSort(int[] numbers) {   
		int temp; // 记录临时中间值   
		int size = numbers.length; // 数组大小   
		for (int i = 0; i < size - 1; i++) {   
			for (int j = i + 1; j < size; j++) {   
				if (numbers[i] < numbers[j]) { // 交换两数的位置   
					temp = numbers[i];   
					numbers[i] = numbers[j];   
					numbers[j] = temp;   
				}   
			}   
		}   
	}   
	//快速排序
	public static void quickSort(int[] numbers, int start, int end) {   
		if (start < end) {   
			int base = numbers[start]; // 选定的基准值（第一个数值作为基准值）   
			int temp; // 记录临时中间值   
			int i = start, j = end;   
			do {   
				while ((numbers[i] < base) && (i < end))   
					i++;   
				while ((numbers[j] > base) && (j > start))   
					j--;   
				if (i <= j) {   
					temp = numbers[i];   
					numbers[i] = numbers[j];   
					numbers[j] = temp;   
					i++;   
					j--;   
				}   
			} while (i <= j);   
			if (start < j)   
				quickSort(numbers, start, j);   
			if (end > i)   
				quickSort(numbers, i, end);   
		}   
	}   
	//选择排序 
	public static void selectSort(int[] numbers) {   
		int size = numbers.length, temp;   
		for (int i = 0; i < size; i++) {   
			int k = i;   
			for (int j = size - 1; j > i; j--) {   
				if (numbers[j] < numbers[k])   
					k = j;   
			}   
			temp = numbers[i];   
			numbers[i] = numbers[k];   
			numbers[k] = temp;   
		}   
	}   
	//插入排序    
	// @param numbers  
	public static void insertSort(int[] numbers) {   
		int size = numbers.length, temp, j;   
		for (int i = 1; i < size; i++) {   
			temp = numbers[i];   
			for (j = i; j > 0 && temp < numbers[j - 1]; j--)   
				numbers[j] = numbers[j - 1];   
			numbers[j] = temp;   
		}   
	}   
	//归并排序  
	public static void mergeSort(int[] numbers, int left, int right) {   
		int t = 1;// 每组元素个数   
		int size = right - left + 1;   
		while (t < size) {   
			int s = t;// 本次循环每组元素个数   
			t = 2 * s;   
			int i = left;   
			while (i + (t - 1) < size) {   
				merge(numbers, i, i + (s - 1), i + (t - 1));   
				i += t;   
			}   
			if (i + (s - 1) < right)   
				merge(numbers, i, i + (s - 1), right);   
		}   
	}    
	//归并算法实现  
	private static void merge(int[] data, int p, int q, int r) {   
		int[] B = new int[data.length];   
		int s = p;   
		int t = q + 1;   
		int k = p;   
		while (s <= q && t <= r) {   
			if (data[s] <= data[t]) {   
				B[k] = data[s];   
				s++;   
			} else {   
				B[k] = data[t];   
				t++;   
			}   
			k++;   
		}   
		if (s == q + 1)   
			B[k++] = data[t++];   
		else  
			B[k++] = data[s++];   
		for (int i = p; i <= r; i++)   
			data[i] = B[i];   
	}  

}
