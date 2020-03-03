package telran.tests;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer n1, Integer n2) {
		if (n1 % 2 == 1 && n2 % 2 == 0) {
			return -1;
		}
		if (n1 % 2 == 0 && n2 % 2 == 1) {
			return 1;
		}
		if (n1 % 2 == 1 && n2 % 2 == 1) {
			return n1 - n2;
		}
		return n2 - n1;
	}

}
