package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import telran.persons.dto.Person;
import telran.test.PersonAgeComparator;
import telran.util.Array;

class ArrayTests {
	int numbers[] = { 10, -8, 70, 75, 30 };

	Person p1 = new Person(1234, "Grisha", 1920);
	Person p2 = new Person(1233, "Grisha2", 1925);
	Person p3 = new Person(1232, "Grisha3", 1930);

	private Array getArray() {
		Array array = new Array(4);

		for (int i = 0; i < numbers.length; i++) {
			array.add(numbers[i]);
		}
		return array;
	}

	private Array getArrayPersons() {
		Array array = new Array();

		array.add(p1);
		array.add(p2);
		array.add(p3);

		return array;
	}

	@Test
	void testAddGetSize() {
		Array array = getArray();
		for (int i = 0; i < array.size(); i++) {
			assertEquals(numbers[i], array.get(i));
		}
		assertNull(array.get(array.size()));

	}

	@Test
	void testAddAtIndex() {
		int expectedNumbers[] = { -10, 10, -8, 70, -70, 75, 30, -30 };
		Array array = getArray();
		assertTrue(array.add(0, -10));
		assertTrue(array.add(4, -70));
		assertTrue(array.add(7, -30));
		int actualNumbers[] = getActualNumbers(array);
		assertArrayEquals(expectedNumbers, actualNumbers);
		assertFalse(array.add(-1, 100));
		assertFalse(array.add(100, 100));

	}

	private int[] getActualNumbers(Array array) {
		int res[] = new int[array.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = (int) array.get(i);
		}
		return res;
	}

	@Test
	void testRemoveAtIndex() {
		int expectedNumbers[] = { -8, 75 };
		Array array = getArray();
		assertEquals(10, array.remove(0));
		assertEquals(70, array.remove(1));
		assertEquals(30, array.remove(2));
		assertArrayEquals(expectedNumbers, getActualNumbers(array));
		assertNull(array.remove(2));
		assertNull(array.remove(-1));
	}

	@Test
	void testSetAtIndex() {
		int expectedNumbers[] = { -10, -8, 70, 75, -30 };
		Array array = getArray();
		assertEquals(10, array.set(0, -10));
		assertEquals(30, array.set(4, -30));
		assertArrayEquals(expectedNumbers, getActualNumbers(array));
		assertNull(array.set(-1, 100));
		assertNull(array.set(100, 100));
	}

	@Test
	void testArrayComparatorSort() {
		Person[] expPersons = { p3, p2, p1 };
		Array persons = new Array();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);

		persons.sort(new PersonAgeComparator());

		assertEquals(expPersons[0], persons.get(0));

	}

	@Test
	void testArrayComparableSort() {
		Person[] expPersons = { p3, p2, p1 };
		Array persons = new Array();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);

		persons.sort();

		assertEquals(expPersons[0], persons.get(0));
	}

	@Test
	void testArrayComparatorBinarySearch() {
		Person perToFind = new Person(1234, "Grisha", 1920);
		int exp = 0;
		Array persons = getArrayPersons();
		assertEquals(exp, persons.binarySearch(perToFind, new PersonAgeComparator()));
	}

	@Test
	void testArrayCompareBinarySearch() {
		Person perToFind = new Person(1234, "Grisha", 1920);
		Person perNotToFind = new Person(2333, "Mark", 1990);
		int exp1 = 0;
		int exp2 = -1;
		Array persons = getArrayPersons();

		assertEquals(exp1, persons.binarySearch(perToFind));
		assertEquals(exp2, persons.binarySearch(perNotToFind));
	}

	@Test
	void testArrayFilterByPredicate() {
		int[] numbers = { 10, -8, 70, 30 };
		Array array = getArray();

		Array arrayEven = array.filter(new EvenNumbersPredicate());
		int[] actualNumbers = getActualNumbers(arrayEven);

		assertArrayEquals(numbers, actualNumbers);
	}

	@Test
	void testArrayFilterByPredicatePersonAge() {
		Person[] expPersons = { p2, p3 };
		Array persons = getArrayPersons();

		Array arrayEven = persons.filter(new PersonAgePredicate());

		assertEquals(expPersons[0], arrayEven.get(0));
	}

	@Test
	void testArrayRemoveIf() {
		Person[] expPersons = { p1 };
		Array persons = getArrayPersons();

		Array persons2 = new Array();
		persons2.add(p1);
		persons2.add(p1);

		boolean result = persons.removeIf(new PersonAgePredicate());
		boolean result2 = persons2.removeIf(new PersonAgePredicate());

		assertEquals(expPersons[0], persons.get(0));
		assertTrue(result);
		assertFalse(result2);
	}
	@Test
	/**
	 * additional test for sorting array numbers according to the following
	 * all odd numbers should go before the even ones
	 * odd numbers should be sorted in the ascending order
	 * even numbers should be sorted in the descending order
	 */
	void testSortingEvenOdd() {
		int[] numbers = {  75, -8, 10, 30, 70};
		Array array = getArray();
		
		array.sort(new SortOddEvenComparator());
		
		int[] actualNumbers = getActualNumbers(array);		

		assertArrayEquals(numbers, actualNumbers);

	}
}
