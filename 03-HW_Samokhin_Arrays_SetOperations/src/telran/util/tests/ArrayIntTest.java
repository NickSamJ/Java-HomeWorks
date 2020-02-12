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
		
		// find first indention
		// count repeating numbers
        int[] array = {1, 2, 3, 4, 5, 8};
        int key = 1;
		
		int k = ArrayInt.binarySearch(array, key);
		int expected = 0;
		
//		int ind = ArrayInt.binarySearch(array, 20);
		
//		assertEquals(ind, 20);
		assertEquals(expected, k);
		assertEquals(-7, ArrayInt.binarySearch(array, 100));
		assertEquals(-1, ArrayInt.binarySearch(array, -100));
		assertEquals(-6, ArrayInt.binarySearch(array, 6));
		
	}

	@Test
	void testArrayBinarySearchCustom222() {
		
		int arr[] = {10,  20, 20, 30, 30, 40, 50, 60, 70, 70, 70, 200, 300, 400};
		assertEquals(0, ArrayInt.binarySearch(arr, 10));
		assertEquals(6, ArrayInt.binarySearch(arr, 50));
		assertEquals(-2, ArrayInt.binarySearch(arr, 15));
		assertEquals(-4, ArrayInt.binarySearch(arr, 25));
		assertEquals(1, ArrayInt.binarySearch(arr, 20));
		assertEquals(3, ArrayInt.binarySearch(arr, 30));
		assertEquals(8, ArrayInt.binarySearch(arr, 70));
		
		assertEquals(3, ArrayInt.countSorted(arr, 70));
		
		
	}
	
	@Test
	void testInsertNumbers() {
		int arr[] = {1, 2, -5, 10, 8};
		int exp0[] = {100, 1, 2, -5, 10, 8};
		int exp5[] = {1, 2, -5, 10, 8, 50};
		int exp3[] = {1, 2, -5, 30 ,10 ,8};
		
		assertArrayEquals(exp0, ArrayInt.insert(arr, 0, 100));
		assertArrayEquals(exp5, ArrayInt.insert(arr, 5, 50));
		assertArrayEquals(exp3, ArrayInt.insert(arr, 3, 30));
		assertArrayEquals(arr, ArrayInt.insert(arr, -3, 30));
	}
	
	@Test
	void testDeleteNumbers() {
		int arr[] = {1, 2, -5, 10, 8};
		int exp0[] = { 2, -5, 10, 8};
		int exp4[] = {1, 2, -5, 10};
		int exp3[] = {1, 2, -5 ,8};
		
		assertArrayEquals(exp0, ArrayInt.deleteElement(arr, 0));
		assertArrayEquals(exp4, ArrayInt.deleteElement(arr, 4));
		assertArrayEquals(exp3, ArrayInt.deleteElement(arr, 3));
		assertArrayEquals(arr, ArrayInt.deleteElement(arr, -3));
		assertArrayEquals(arr, ArrayInt.deleteElement(arr, 9));
	}
	
	@Test
	void testInsertNumbersToSorted() {
		int arr[] = {10, 20, 30, 40, 50};
		int exp0[] = {5, 10, 20, 30, 40, 50};
		int exp5[] = {10, 20, 30, 40, 50, 55};
		int exp3[] = {10, 20, 30, 35, 40, 50};
		int exp4[] = {10, 20, 30, 40, 50};
		int used[] = {10, 20, 21, 30, 40, 50};
		assertArrayEquals(used, ArrayInt.insertSorted(exp4,21));
		
		assertArrayEquals(exp0, ArrayInt.insertSorted(arr, 5));
		assertArrayEquals(exp5, ArrayInt.insertSorted(arr, 55));
		
		assertArrayEquals(exp3, ArrayInt.insertSorted(arr, 35));
	}
	
	
	int[] getRundomArray(int nNumbers) {
		int[] array = new int[nNumbers];
		
		for(int i=0; i<nNumbers; i++) {
			array[i] = (int) (Math.random()*1000000); 
		}
		return array;
	};
	/* tests for HW #3 */
	@Test
	void testUnion () {
		int ar1[] = {10, 30, -8, 20};
		int ar2[] = {0, -3, 7, 11};
		int ar3[] = {0, -8, 20, 10};
		int exp1[] = {10, 30, -8, 20, 0, -3, 7, 11};
		int exp2[] = {10, 30, -8, 20, 0};
		assertArrayEquals(exp1, ArrayInt.union(ar1, ar2));
		assertArrayEquals(exp2, ArrayInt.union(ar1, ar3));
	}
	@Test
	void testIntersection () {
		int ar1[] = {10, 30, -8, 20};
		int ar2[] = {0, -3, 7, 11};
		int ar3[] = {0, -8, 20, 10};
		int exp1[] = {};
		int exp2[] = {10, -8, 20};
		assertArrayEquals(exp1, ArrayInt.intersection(ar1, ar2));
		assertArrayEquals(exp2, ArrayInt.intersection(ar1, ar3));
	}
	@Test
	void testDifference () {
		int ar1[] = {10, 30, -8, 20};
		int ar2[] = {0, -3, 7, 11};
		int ar3[] = {0, -8, 20, 10};
		int exp1[] = ar1;
		int exp2[] = {30};
		assertArrayEquals(exp1, ArrayInt.difference(ar1, ar2));
		assertArrayEquals(exp2, ArrayInt.difference(ar1, ar3));
	}
}
