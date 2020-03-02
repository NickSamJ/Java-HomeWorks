package telran.util;

import java.util.Comparator;

public class ComparatorComparable implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		@SuppressWarnings("unchecked")
		Comparable<Object> comp = (Comparable<Object>)o1;
		return comp.compareTo(o2);
	}

}
