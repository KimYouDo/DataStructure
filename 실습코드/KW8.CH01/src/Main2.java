
public class Main2 {
	public static void main(String[] args){
		 HashtableOpen hashTable = new HashtableOpen(); 
		 int SIZE = 10;
		 for (int i= 0; i< SIZE; i++) {
		 Integer nextInt= (int) (32000 * Math.random());
		 hashTable.put(nextInt, nextInt);
		 // toString���̿��Ͽ�hashTable����������Ʈ�Ѵ�.
		 }
		 hashTable.put(100,100);
		 System.out.println("Put");
		 System.out.println(hashTable.toString());
		 
		 for (int i= 0; i< SIZE; i++) {
		 Integer nextInt= (int) (32000 * Math.random());
		 hashTable.remove(nextInt);
		 // toString���̿��Ͽ�hashTable����������Ʈ�Ѵ�.
		 }
		 hashTable.remove(100);
		 System.out.println("Remove");
		 System.out.println(hashTable.toString());
		 
	}
}
