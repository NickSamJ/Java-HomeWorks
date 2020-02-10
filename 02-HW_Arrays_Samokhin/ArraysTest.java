package telran.arrays.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ArraysTest {

	@Test
	void testArrayBinarySearch() {
		int[] array = {1, 2, 3, 4, 5};
		int key = 1;
		
		int k = Arrays.binarySearch(array, key);
		int expected = 0;
		
		assertEquals(expected, k);
		assertEquals(-6, Arrays.binarySearch(array, 100));
		assertEquals(-1, Arrays.binarySearch(array, -100));
	}

}
