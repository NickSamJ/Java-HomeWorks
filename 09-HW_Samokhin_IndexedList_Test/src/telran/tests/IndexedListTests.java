package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Array;
import telran.util.IndexedLinkedList;
import telran.util.IndexedList;

class IndexedListTests {
	int numbers[] = { 10, -8, 70, 75, 30 };
	IndexedList<Integer> listNumbers;
	@BeforeEach
	void setUp() {
		listNumbers = getList();
	}
	
	@Test
	void testAddGetSize() {
		for (int i = 0; i < listNumbers.size(); i++) {
			assertEquals(numbers[i], listNumbers.get(i));
		}
		assertNull(listNumbers.get(listNumbers.size()));

	}

	private IndexedList<Integer> getList() {
//		IndexedList<Integer> list = new Array<>(4); // Single place where code updates
		IndexedList<Integer> list = new IndexedLinkedList<>(4); // Single place where code updates

		for (int i = 0; i < numbers.length; i++) {
			list.add(numbers[i]);
		}
		return list;
	}

	@Test
	void testAddAtIndex() {
		int expectedNumbers[] = { -10, 10, -8, 70, -70, 75, 30, -30 };
		assertTrue(listNumbers.add(0, -10));
		assertTrue(listNumbers.add(4, -70));
		assertTrue(listNumbers.add(7, -30));
		int actualNumbers[] = getActualNumbers(listNumbers);
		assertArrayEquals(expectedNumbers, actualNumbers);
		assertFalse(listNumbers.add(-1, 100));
		assertFalse(listNumbers.add(100, 100));

	}
	
	@Test
	void testSimpleAdd() {
		int exp[] = {5, 10,10, -8, 70, 75, 30 };
		assertTrue(listNumbers.add(0, 10));
		assertTrue(listNumbers.add(0, 5));
		int actualNumbers[] = getActualNumbers(listNumbers);
		assertArrayEquals(exp, actualNumbers);
	}

	private int[] getActualNumbers(IndexedList<Integer> array) {
		int res[] = new int[array.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = (int) array.get(i);
		}
		return res;
	}

	@Test
	void testRemoveAtIndex() {
		int expectedNumbers[] = { -8, 75 };
		assertEquals(10, listNumbers.remove(0));
		assertEquals(70, listNumbers.remove(1));
		assertEquals(30, listNumbers.remove(2));
		assertArrayEquals(expectedNumbers, getActualNumbers(listNumbers));
		assertNull(listNumbers.remove(2));
		assertNull(listNumbers.remove(-1));
	}
	
	@Test
	void testSimpleRemoveAtIndex() {
		int expectedNumbers[] = { 10, -8, 75, 30 };
		assertEquals(70, listNumbers.remove(2));
		assertArrayEquals(expectedNumbers, getActualNumbers(listNumbers));
	}
	
	@Test
	void testSetAtIndex() {
		int expectedNumbers[] = { -10, -8, -70, 75, -30 };
		assertEquals(10, listNumbers.set(0, -10));
		assertEquals(70, listNumbers.set(2, -70));
		assertEquals(30, listNumbers.set(4, -30));
		assertArrayEquals(expectedNumbers, getActualNumbers(listNumbers));
		assertNull(listNumbers.set(-1, 100));
		assertNull(listNumbers.set(100, 100));
	}

	@Test
	void testSorting() {

		Person personMoshe = new Person(123, "Moshe", 1980);
		Person personVova = new Person(100, "Vova", 1970);
		try {
			IndexedList<Person> listPersons = listNumbers.getClass() // getting object of Class
			.getConstructor() // getting constructor by default
			.newInstance();
			listPersons.add(personMoshe);
			listPersons.add(personVova);
			listPersons.sort();
			assertEquals(personVova, listPersons.get(0));
			assertEquals(personMoshe, listPersons.get(1));
			listPersons.sort(new PersonAgeComparator());
			assertEquals(personVova, listPersons.get(1));
			assertEquals(personMoshe, listPersons.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void testBinarySearch() {
		String stringsNaturalOrder[] = { "abcd", "lm", "lmnopr", "x", "y", "z" };
		String stringsLengthOrder[] = { "x", "y", "z", "lm", "abcd", "lmnopr" };
		Comparator<String> compLength = new StringLengthComparator();
		IndexedList<String> stringsNatural = getListStrings(stringsNaturalOrder);
		IndexedList<String> stringsLength = getListStrings(stringsLengthOrder);
		assertEquals(-3, stringsNatural.binarySearch("lmn"));
		assertEquals(1, stringsNatural.binarySearch("lm"));
		assertEquals(-5, stringsLength.binarySearch("lmn", compLength));
		assertEquals(3, stringsLength.binarySearch("lm", compLength));
	}

	private IndexedList<String> getListStrings(String[] strings) {
		try {
//			getting object of the same class as the object listNumbers
			IndexedList<String> list = 
					listNumbers.getClass().getConstructor(int.class).newInstance(strings.length);
			
			for (int i = 0; i < strings.length; i++) {
				list.add(strings[i]);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Test
	void testFilter() {
		int expected[] = { 10, -8, 70, 30 };
		IndexedList<Integer> listNoEven = listNumbers.filter(new EvenNumbersPredicate());
		int actualNumbers[] = getActualNumbers(listNoEven);
		assertArrayEquals(expected, actualNumbers);
	}

	@Test
	void testRemoveIf() {
		// {10, -8, 70, 75, 30};
		listNumbers.add(75);
		int expected[] = { 75, 75 };
		EvenNumbersPredicate predicateEven = new EvenNumbersPredicate();
		assertTrue(listNumbers.removeIf(predicateEven));
		assertFalse(listNumbers.removeIf(predicateEven));
		assertArrayEquals(expected, getActualNumbers(listNumbers));
	}

	@Test
	/**
	 * additional test for sorting array numbers according to the following all odd
	 * numbers should go before the even ones odd numbers should be sorted in the
	 * ascending order even numbers should be sorted in the descending order
	 */
	void testSortingEvenOdd() {
		// {10, -8, 70, 75, 30}
		listNumbers.add(73);
		listNumbers.add(3);
		int[] expected = { 3, 73, 75, 70, 30, 10, -8 };
		listNumbers.sort(new EvenOddComparator());
		assertArrayEquals(expected, getActualNumbers(listNumbers));
	}

	@Test
	void testRemoveObject() {
		int[] expected = { 10, -8, 75, 30 };
		listNumbers.remove((Integer) 70);
		assertArrayEquals(expected, getActualNumbers(listNumbers));
	}

	@Test
	void testIterable() {
		int sumExp = 177;
		int sumActual = 0;
		for (Integer num : listNumbers) {
			sumActual += num;
		}

		assertEquals(sumExp, sumActual);
	}
	@Test
	void testIterableRemove() {
		IndexedList<Integer> listNumbers = getList();
		
		listNumbers.add(77);
		listNumbers.add(77);
		listNumbers.add(78);
		listNumbers.add(80);
		listNumbers.add(80);
		listNumbers.add(80);
		int expected[] = { -8, 75, 77, 77 };
		// Need to be FIXED
//		for (Integer num : listNumbers) {
//			if (num > 0 && num % 2 == 0) {
//				listNumbers.remove(num);
//			}
//		}
		
		
		// Added remove method to the Iterator
		Iterator<Integer> it = listNumbers.iterator();
		while(it.hasNext()) {
			Integer num = it.next();
			if(num>= 0 && num % 2 ==0) {
				it.remove();
			}
		}
		assertArrayEquals(expected, getActualNumbers(listNumbers));
	}
}
