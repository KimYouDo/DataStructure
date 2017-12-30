package KW9.CH01;

import java.util.ArrayList;
import java.util.Collections;

public class ex2Main {
	 public static void main(String[] args){
	ArrayList<Person> peopleList= new ArrayList<Person>();
	Person [] people = new Person[10];
	people[0] = new Person("A2","A2", 8);
	 people[1] = new Person("A3","A2", 9);
	 people[2] = new Person("A4","A2", 3);
	 people[3] = new Person("A8","A2", 4);
	 people[4] = new Person("A7","A2", 2);
	 people[5] = new Person("A5","A2", 1);
	 people[6] = new Person("A9","A8", 4);
	 people[7] = new Person("A1","A1", 6);
	 people[8] = new Person("A6","A7", 5);
	 people[9] = new Person("A1","A2", 7);
	 
	 for(int i=0; i<10; i++)
		 peopleList.add(people[i]);
	
	 for(int i=0; i<10; i++){
		 System.out.println(peopleList.get(i));
	 }
	 Collections.sort(peopleList, new ComparePerson());
	 
	 System.out.println("------------------------------------");
	 for(int i=0; i<10; i++){
		 System.out.println(peopleList.get(i));
	 }
}
}
