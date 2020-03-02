package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class Array<T> implements IndexedList<T>{
private Object[] array;
private int size = 0;
public Array(int capacity) {
	array = new Object[capacity];
}
public Array() {
	this(16);
}
public void add(T obj) {
	if (size == array.length) {
		allocateArray();
	}
	array[size++] = obj;
}

private void allocateArray() {
	array = Arrays.copyOf(array, array.length * 2);
	
}
public T get(int index) {
	T res = null;
	if (index >= 0 && index < size) {
		res = (T)array[index];
	}
	return res;
}
public int size() {
	return size;
}
/**
 * adds an object at a specified index
 * @param index
 * @param obj
 * @return true if index value in the range [0 - size]
 *  (size included) otherwise false
 */
public boolean add(int index, T obj) {
	boolean res = false;
	if (index >= 0 && index <= size) {
		if (size == array.length) {
			allocateArray();
		}
		res = true;
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
	}
	return res;
}
/**
 * removes the object at a specified index
 * @param index
 * @return reference to the removed object or null in the case of
 * wrong index
 */
public Object remove(int index) {
	Object res = null;
	if (isIndexValid(index)) {
		res = array[index];
		if (index != size - 1) {
			//if size equals capacity index + 1 for the last element will cause error
			System.arraycopy(array, index + 1, array,
					index, size - index -1);
		}
		size--;
		array[size] = null; //candidate to be collected as a garbage
		
	}
	return res;
}
/**
 * sets new object at a specified index
 * @param index
 * @param obj
 * @return old reference to the object or null in the case of
 * wrong index value
 */
public Object set(int index, T obj) {
	Object res = null;
	if (isIndexValid(index)) {
		res = array[index];
		array[index] = obj;
	}
	return res;
}
private boolean isIndexValid(int index) {
	return index >= 0 && index < size;
}
public int indexOf(Object pattern) {
	int res = -1;
	if (pattern != null) {
		for (int i = 0; i < size; i++) {
			if (pattern.equals(array[i])) {
				res = i;
				break;
			}
		}
	}
	return res;
	
}
public int lastIndexOf(Object pattern) {
	int res = -1;
	if (pattern != null) {
		for (int i = size - 1; i >= 0; i--) {
			if (pattern.equals(array[i])) {
				res = i;
				break;
			}
		}
	}
	return res;
	
}
public int binarySearch(T pattern, Comparator<T> comp) {
	int left = 0; 
	int right = size - 1;
	int middle = (left + right) / 2;
	while (left <= right && !pattern.equals(array[middle])) {
		if (comp.compare(pattern, (T)array[middle]) < 0) {
			right = middle - 1;
		} else {
			left = middle + 1;
		}
		middle = (left + right) / 2;
	}
	return left > right ? -(left + 1) : middle;
}
/**
 * the same binary search but based on Comparable<Object>
 *  rather than Comparator<Object>
 * @param pattern
 * @return
 */
public int binarySearch(T pattern) {
	
	return binarySearch(pattern, (Comparator<T>)Comparator.naturalOrder());
}
/**
 * sorting based on Comparator<Object>
 * in the meantime the simple bubble sort that we did at class
 * may be applied O[N^2]
 * @param comp
 */
public void sort(Comparator<T> comp) {
	boolean flSort = false;
	int length = size;
	do {
		flSort = true;
		length--;
		for (int i = 0; i < length; i++) {
			if (comp.compare((T)array[i], (T)array[i + 1]) > 0) {
				Object tmp = array[i];
				array[i] = array[i + 1];
				array[i + 1] = tmp;
				flSort = false;
			}
		}
		
		
	}while (!flSort);
	
}

/**
 * sorting based on Comparable<Object>
 */
public void sort() {
	sort((Comparator<T>)Comparator.naturalOrder());
	//below the code with no ComparatorComparable
//	boolean flSort = false;
//	int length = size;
//	do {
//		flSort = true;
//		length--;
//		for (int i = 0; i < length; i++) {
//			if (((Comparable<Object>)array[i]).compareTo(array[i + 1]) > 0) {
//				Object tmp = array[i];
//				array[i] = array[i + 1];
//				array[i + 1] = tmp;
//				flSort = false;
//		}
//		
//		
//	}while (!flSort);
}
public Array<T> filter (Predicate<T> predicate) {
	Array<T> res = new Array();
	for (int i = 0; i < size; i++) {
		if (predicate.test((T)array[i])) {
			res.add((T)array[i]);
		}
	}
	return res;
}
/**
 * removes objects matching the given predicate
 * @param predicate
 * @return true if at least one object has been removed
 */
public boolean removeIf(Predicate<T> predicate) {
	 Array<T> res = filter(predicate.negate());
//	Array res = new Array();
//	for (int i = 0; i < size; i++) {
//		if (!predicate.test(array[i])) {
//			res.add(array[i]);
//		}
//	}
	int original = size;
	array = res.array;
	size = res.size;
	return size < original;
}
/**
 * 
 * @param pattern
 * @return
 */
public Object remove(Object pattern) {
	Object res = null;
	int index = indexOf(pattern);
	if (index >= 0) {
		res = remove(index);
	}
	return res;
}
private class ArrayIterator<T> implements Iterator<T> {
	int currentIndex = 0;
	
	@Override
	public boolean hasNext() {
		return currentIndex<size;
	}

	@Override
	public T next() {
		return (T)array[currentIndex++];
	}
	
	@Override
	public void remove() {
		Array.this.remove(--currentIndex);
	}
}
@Override
public Iterator<T> iterator() {
	return new ArrayIterator();
}
}
