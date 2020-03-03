package telran.tests;

import java.util.Comparator;

public class StringLengthComparator implements Comparator<String> {

	@Override
	public int compare(String str0, String str1) {
		return str0.length() >= str1.length() ? 1 : -1;
	}

}
