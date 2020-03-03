package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public interface IndexedList<T> extends Iterable<T>{

	void add(T obj);
	boolean add(int index, T obj);
	int binarySearch(T pattern);
	int binarySearch(T pattern, Comparator<T> comp);
	IndexedList<T> filter(Predicate<T> predicate);
	T get(int index);
	int indexOf(Object pattern);
	int lastIndexOf(Object pattern);
	Object remove(int index);
	Object remove(Object index);
	boolean removeIf(Predicate<T> predicate);
	Object set(int index, T newObject);
	int size();
	void sort();
	void sort(Comparator<T> comparator);
	Iterator iterator();
	
}
