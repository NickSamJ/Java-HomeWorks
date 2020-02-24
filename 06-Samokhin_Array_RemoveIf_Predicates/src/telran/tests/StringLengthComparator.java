package telran.tests;

import java.util.Comparator;

public class StringLengthComparator implements Comparator<Object> {

	@Override
	public int compare(Object arg0, Object arg1) {
		String str0 = (String)arg0;//down cast
		String str1 = (String)arg1;
		return str0.length() >= str1.length() ? 1 : -1;
		
	}
}
