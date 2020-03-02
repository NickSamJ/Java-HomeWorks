package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ArrayShuffling{
	public static void main(String[] args) {
		int[] array = {1,2,3,4,5,6,7};
		List<Integer> array2 = Arrays.asList(1,2,3,4,5,6,7);
		
		/*
		 * Shuffling with comparator
		 */
		Comparator comp = new ShufflingComparator();
		array2.sort(comp);
		System.out.println(Arrays.toString(array2.toArray()));
	}
	
	/* Write method shuffling that mixes up all array's elements
	* in the random order such that for size >= 10 the probability
	* that two shuffling calls will provide the same order, equals 0
	* @param array
	* @return shuffled array
	*/
	public static void shuffle(int[] arr) {
		Random rand = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			int shuffleIndex = rand.nextInt(arr.length);
			System.out.println(shuffleIndex);
			int temp = arr[shuffleIndex];
			arr[shuffleIndex] = arr[i];
			arr[i] = temp;
		}
	}
}