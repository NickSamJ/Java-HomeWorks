package telran.arrays.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SystemTest {

	@Test
	void testArraycopy() {
		int[] arrSrc = {1, 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
		int[] arrDest = new int[17];
		
		
		int length = 17;
		
		int[] expected = {1, 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};

		System.arraycopy(arrSrc, 0, arrDest, 0, length);
		
		assertArrayEquals(expected, arrDest, "Here you got an Fail");
	}
	
	@Test
	void testArraycopyForArraysOfDifferentLength() {
		int[] src = {1, 2, 3, 4, 5};
		int[] dest = {6, 7, 8, 9};
		
		int l = 4;
		int[] expected = {1, 2, 3, 4};
		System.arraycopy(src, 0, dest, 0, l);
		assertArrayEquals(expected, dest);
	}
	
	@Test
	void testArraycopyIfLengthZero() {
		int[] src = {1, 2, 3, 4, 5};
		int[] dest = {6, 7, 8, 9, 10};
		
		int l = 0;
		int[] expected = {6, 7, 8, 9, 10};
		System.arraycopy(src, 0, dest, 1, l);
		
		assertArrayEquals(expected, dest);
	}
	
	@Test
	void testArraycopyIfSrcAndDestReferenceTypes() {
		Integer[] src = {1, 2, 3, 4, 5};
		Integer[] dest = {6, 7, 8, 9, 10};
		
		Integer[] expected = {6, 1, 2, 3, 4};
		System.arraycopy(src, 0, dest, 1, 4);
		
		assertArrayEquals(expected, dest);
	}
	
	@Test
	void testArraycopyIfDestNull() {
		int[] arrSrc = {1, 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
		int[] arrDest = null;


		int length = 17;
		
		assertThrows(RuntimeException.class, ()->{System.arraycopy(arrSrc, 0, arrDest, 0, length);}, "Test works ok");
	}
	
	@Test
	void testArraycopyIfSrcNull() {
		int[] arrSrc = null;
		int[] arrDest = {1, 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};


		int length = 17;
		
		assertThrows(RuntimeException.class, ()->{System.arraycopy(arrSrc, 0, arrDest, 0, length);}, "Test works ok");
	}
}
