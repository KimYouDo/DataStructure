
public class Main2 {
	public static void main(String[] args){
		 HashtableOpen hashTable = new HashtableOpen(); 
		 int SIZE = 10;
		 for (int i= 0; i< SIZE; i++) {
		 Integer nextInt= (int) (32000 * Math.random());
		 hashTable.put(nextInt, nextInt);
		 // toString을이용하여hashTable내용을프린트한다.
		 }
		 hashTable.put(100,100);
		 System.out.println("Put");
		 System.out.println(hashTable.toString());
		 
		 for (int i= 0; i< SIZE; i++) {
		 Integer nextInt= (int) (32000 * Math.random());
		 hashTable.remove(nextInt);
		 // toString을이용하여hashTable내용을프린트한다.
		 }
		 hashTable.remove(100);
		 System.out.println("Remove");
		 System.out.println(hashTable.toString());
		 
	}
}
