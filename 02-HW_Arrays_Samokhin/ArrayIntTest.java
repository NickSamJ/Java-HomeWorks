package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.ArrayInt;

class ArrayIntTest {

	@Test
	void testSearch() {
		int[] arr= {5, 2, -5, 1};
		assertEquals(3, ArrayInt.search(arr, 1 ));
		assertEquals(-1, ArrayInt.search(arr, 0 ));
//		assertTrue(ArrayInt.search(arr, -200)<0);
	}

	@Test
	void testSort() {
		int nNumbers = 1000;
		int[] array = getRundomArray(nNumbers);
		
		ArrayInt.sort(array);
		for(int i=1; i<nNumbers; i++) {
			assertTrue(array[i-1]<=array[i]);
		}
	}
	@Test
	void testArrayBinarySearchCustom() {
		int[] array = {1, 2, 3, 4, 5, 8};
		int key = 1;
		
		int k = ArrayInt.binarySearch(array, key);
		int expected = 0;
		
		assertEquals(expected, k);
		assertEquals(-7, ArrayInt.binarySearch(array, 100));
		assertEquals(-1, ArrayInt.binarySearch(array, -100));
		assertEquals(-6, ArrayInt.binarySearch(array, 6));
	}


	int[] getRundomArray(int nNumbers) {
		int[] array = new int[nNumbers];
		
		for(int i=0; i<nNumbers; i++) {
			array[i] = (int) (Math.random()*1000000); 
		}
		return array;
	};

	
}
