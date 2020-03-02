package telran.tests;

import java.util.function.Predicate;

public class EvenNumbersPredicate implements Predicate<Integer> {

	@Override
	public boolean test(Integer arg0) {
		return arg0 % 2 == 0;
	}

}
