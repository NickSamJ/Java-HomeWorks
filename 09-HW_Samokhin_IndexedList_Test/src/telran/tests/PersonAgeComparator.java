package telran.tests;

import java.util.Comparator;




public class PersonAgeComparator implements Comparator<Person>{

	@Override
	public int compare(Person o1, Person o2) {
		return (o1).getBirthYear() >= (o2).getBirthYear() ? -1 : 1;
	}

}
