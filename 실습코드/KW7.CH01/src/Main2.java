import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Main2 {
	public static void main(String[] args)  {
		MapContactList contactList= new MapContactList();
		List<String> nums= new ArrayList<String>();
		nums.add("123"); nums.add("345");
		contactList.addOrChangeEntry("Jones", nums);
		nums= new ArrayList<String>();
		nums.add("135"); nums.add("357");
		contactList.addOrChangeEntry("King", nums);
		contactList.display();
	}
}

class MapContactList {
	Map<String, List<String>> contacts = new TreeMap<String, List<String>>();
	public List<String> addOrChangeEntry(String name,
			List<String> newNumbers) {
		List<String> oldNumbers= contacts.put(name, newNumbers);
		return oldNumbers;
	}
	public List<String> lookupEntry(String name) {
		return contacts.get(name);
	}
	public List<String> removeEntry(String name) {
		return contacts.remove(name);
	}
	public void display() {
		for (Map.Entry<String, List<String>> current :
			contacts.entrySet()) {
			// Display the name
			System.out.print(current.getKey()+"\t");
			// Display the list of numbers
			System.out.println(current.getValue());
		}
	}
}