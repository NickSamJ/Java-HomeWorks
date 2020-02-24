package telran.tests;

import java.util.function.Predicate;

public class EvenNumbersPredicate implements Predicate<Object> {
	@Override
	public boolean test(Object o) {
		int num = (int) o;
		return num % 2 == 0;
	}
}
