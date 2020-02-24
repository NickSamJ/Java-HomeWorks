package telran.tests;

import java.util.function.Predicate;

import telran.persons.dto.Person;

public class PersonAgePredicate implements Predicate<Object>{
	
	@Override
	public boolean test(Object arg0) {
		Person person = (Person)arg0;
		return person.getBirthYear() >=1925;
		
	}
}
