import java.util.*;
public class TestArrayList {
	/** Traverses ordered list and displays each element.
Displays an error message if an element is out of order.
@param testList An ordered list of integers
	 */
	public static void traverseAndShow(SortedArrayList<Integer> testList) {
		int prevItem = testList.get(0);
		// Traverse ordered list and display any value that
		// is out of order.
		for (int thisIndex=0; thisIndex<testList.size(); thisIndex++) { // OrderedList Iterator()
			System.out.println(testList.get(thisIndex));
			if (prevItem > testList.get(thisIndex))
				System.out.println("*** FAILED, value is"  + testList.get(thisIndex));
						prevItem = testList.get(thisIndex);
		}
	}
	public static void main(String[] args) {
		SortedArrayList<Integer> testList = new SortedArrayList<Integer>();
		final int MAX_INT = 500;
		final int START_SIZE = 100;
		//Create a random number generator.
		Random random = new Random();
		for (int i = 0; i < START_SIZE; i++) {
			int anInteger = random.nextInt(MAX_INT);
			testList.add(anInteger);
		}
		//Add to beginning and end of list.
		testList.add(-1);
		testList.add(MAX_INT + 1);
		traverseAndShow(testList); // Traverse and display.
		//Remove first, last, and middle elements.
		Integer first = testList.get(0);
		Integer last = testList.get(testList.size() - 1);
		Integer middle = testList.get(testList.size() / 2);
		testList.remove(first);
		testList.remove(last);
		testList.remove(middle);
		traverseAndShow(testList); // Traverse and display.
	}
}
