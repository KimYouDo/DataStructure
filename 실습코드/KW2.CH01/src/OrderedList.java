import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Iterator;

public class OrderedList<E extends Comparable<E>>implements Iterable<E> {
	private LinkedList<E> theList = new LinkedList<E>();
	public OrderedList() {
		theList = new LinkedList<E>();
	}

	public void add (E e) {
		ListIterator<E> iter = theList.listIterator();
		while (iter.hasNext()) {
			if (e.compareTo(iter.next()) < 0) {
				// found element > new one
				iter.previous(); // back up by one
				iter.add(e); // add new one
				return; // done
			}
		}
		iter.add(e); // will add at end
	}

	public E get (int index) {
		return theList.get(index);
	}

	public int size () {
		return theList.size();
	}

	public boolean remove (E e) {
		return theList.remove(e);
	}

	// returns an iterator positioned before the first element
	public Iterator iterator() {
		return theList.iterator();
	}	
}
// Method를 채워 Class를 완성한다.
