package telran.util;

import java.util.Comparator;

import telran.persons.dto.Person;

public class ComparatorComparable implements Comparator<Object> {
	@Override
	public int compare(Object o1, Object o2) {
		Comparable<Object> comp = (Comparable<Object>)o1;
		return comp.compareTo(o2);
	}
}
