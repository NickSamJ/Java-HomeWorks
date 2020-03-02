package telran.tests;

public class Person implements Comparable<Object>{


private long id;
private String name;
private int birthYear;
public Person() {
	
}
public Person(long id, String name, int birthYear) {
	this.id = id;
	this.name = name;
	this.birthYear = birthYear;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public long getId() {
	return id;
}
public int getBirthYear() {
	return birthYear;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + birthYear;
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Person other = (Person) obj;
	if (birthYear != other.birthYear)
		return false;
	if (id != other.id)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}
@Override
public int compareTo(Object arg0) {
	
	return this.id <= ((Person)arg0).id ? -1 : 1 ;
}



}
