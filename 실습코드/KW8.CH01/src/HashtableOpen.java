import java.util.Map;

public class HashtableOpen<K, V> {
	public static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		public V setValue(V val) {
			V oldVal = value;
			value = val;
			return oldVal;
		}
	}
	// Data Fields
	private Entry<K, V>[] table;
	private static final int START_CAPACITY = 5;
	private double LOAD_THRESHOLD = 0.75;
	private int numKeys;
	private int numDeletes;
	private final Entry<K, V> DELETED =
			new Entry<K, V>(null, null);
	// Constructor
	public HashtableOpen() {
		table = new Entry[START_CAPACITY];
	}
	// Insert inner class Entry<K, V> here
	private int find(Object key) {
		int index = key.hashCode() % table.length;
		if (index < 0) {
			index += table.length; // Make it positive.
		}
		while ((table[index] != null)&&(!key.equals(table[index].key))) {
			index++;
			if (index >= table.length) {
				index = 0; // Wrap around.
			}
		}
		return index;
	}
	public V get(Object key) {
		// Find the first table element that is empty
		// or the table element that contains the key.
		int index = find(key);
		// If the search is successful, return the value.
		if (table[index] != null) {
			return table[index].value;
		} else {
			return null; // key not found.
		}
	}
	public V put(K key, V value) {
		int index = find(key);
		if (table[index] == null) {
			table[index] = new Entry<K, V>(key, value);
			numKeys++;
			double loadFactor= (double) (numKeys+ numDeletes)
					/ table.length;
			if (loadFactor> LOAD_THRESHOLD) {
				rehash();
			}
			return null;
		}
		V oldVal= table[index].value;
		table[index].value = value;
		return oldVal;
	}

	public V remove(Object key) {
		int index = find(key);
		if (table[index] == null) {
		return null;
		}
		V val = table[index].value;
		table[index] = DELETED;
		numKeys--;
		return val;
	}

	public String toString() {
		String st="";
		if (table == null) {
			return null;
		}
		for(int i=0; i<table.length; i++){
			if (table[i]==null)
				st+="";
			else
				st+=table[i].getValue()+"\n";
		}
		return st;
	}

	private void rehash() {
		// Save a reference to oldTable.
		Entry<K, V>[] oldTable= table;
		// Double capacity of this table.
		table = new Entry[2 * oldTable.length+ 1];
		// Reinsert all items in oldTableinto expanded table.
		numKeys= 0;
		numDeletes= 0;
		for (int i= 0; i< oldTable.length; i++) {
			if ((oldTable[i] != null) && (oldTable[i] != DELETED)){
				// Insert entry in expanded table
				put(oldTable[i].key, oldTable[i].value);
			}
		}
	}
}