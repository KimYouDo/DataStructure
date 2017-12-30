package KW9.CH01;

import java.util.Arrays;


public class Person implements Comparable<Person> {
	// Data Fields
	/* The last name */
	private String lastName;
	/* The first name */
	private String firstName;
	/* Birthday represented by an integer from 1 to 366 */
	private int birthDay;
	// Methods
	Person(String firstName, String lastName, int birthDay){
		this.lastName=lastName;
		this.firstName=firstName;
		this.birthDay=birthDay;
	}
	/** Compares two Personobjects based on names.
The result is based on the last names if they are
different; otherwise, it is based on the first names.
@paramobjThe other Person
@return A negative integer if this person’s name
precedes the other person’s name;
0 if the names are the same;
a positive integer if this person’s name follows the other person’s name.
	 */
	@Override
	public int compareTo(Person other) {
		// Compare this Personto otherusing last names.
		int result = lastName.compareTo(other.lastName);
		// Compare first names if last names are the same.
		if (result == 0)
			return firstName.compareTo(other.firstName);
		else
			return result;
	}
	//Other methods
	/**
	 * Return a String representation of this node
	 * @return a String representation of this node
	 */
	@Override
	public String toString() {// Person 내용을프린트
		StringBuilder sb= new StringBuilder();
		sb.append(firstName+" ");
		sb.append(lastName+" ");
		sb.append(birthDay);
		//firstName, lastName, birthDay를차례로sb에append
		return sb.toString();
	}
	
	public int getBirthDay() {
		return birthDay;
	}
	
	 public static void main(String[] args){
		 Person [] people = new Person[10];
		
		 
		 people[0] = new Person("A2","A2", 4);
		 people[1] = new Person("A3","A2", 4);
		 people[2] = new Person("A4","A2", 4);
		 people[3] = new Person("A8","A2", 4);
		 people[4] = new Person("A7","A2", 4);
		 people[5] = new Person("A5","A2", 4);
		 people[6] = new Person("A9","A8", 4);
		 people[7] = new Person("A1","A1", 4);
		 people[8] = new Person("A6","A7", 2);
		 people[9] = new Person("A1","A2", 4);
		 for(int i=0; i<10; i++){
			 System.out.println(people[i].toString());
		 }
		 System.out.println("--------------------------------------------");
		
		 Arrays.sort(people);
		 for(int i=0; i<10; i++){
			 System.out.println(people[i].toString());
		 }
	 }
}