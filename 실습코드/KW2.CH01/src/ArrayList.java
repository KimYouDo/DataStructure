import java.util.*;
class SortedArrayList <T extends Comparable<T>> {
	private T[] items;
	private int size = 0;
	private int maxSize = 5;
	
	private int scanPos = 0;// ��ĵ�ϱ� ���� �ʿ��� ��ĵ �ʱ� ��ġ
	
	public boolean hasNext(){// �迭�� �׼����� �����Ͱ� �ִ��� �˻�
		return scanPos != size-1;
	}
	public T next(){// ���� ��ĵ ��ġ���� �迭�� �����͸� ��ȯ
		return items[scanPos++];//.. �ϰ� ���� ��ĵ ��ġ�� ����
	}
	public void reset(){// ��ĵ ��ġ�� �迭�� ù�� °�� �ʱ�ȭ
		scanPos = 0;
	}
	
	public SortedArrayList() {
		items = (T[]) new Comparable[maxSize];
	}
	
	public SortedArrayList(int max) {
		items = (T[]) new Comparable[max];
		maxSize = max;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public boolean isFull() {
		return size == maxSize;
	}
	public int size() {
		return size;
	}
	public void resize() {	
		T[] newItems = (T[])new Comparable[2*items.length];
		for (int i = 0; i < items.length; i++)
			newItems[i] = items[i];
		items = newItems;	
		maxSize = 2*size;
	}
	
	// ������ �޼ҵ带 �ϼ��Ѵ�.
	public void add(T data) {
		int i;
		if(isFull())
			resize();
		for(i=0; i<size; i++)
		{
			if(items[i].compareTo(data)>0)
				break;
		}
		for(int j=size-1; j>=i; j--)
			items[j+1]=items[j];
		items[i]=data;
		size++;

	}
	public boolean remove(T data) {
		if (isEmpty())
			throw new java.util.NoSuchElementException("remove(): list empty");
		int i, j;
		for (i = 0; i < size; i++)
			if (items[i].equals(data))
				break;
			else if (items[i].compareTo(data) > 0)
				return false;
		if(i==size)
			return false;
		for (j = i + 1; j < size; j++)
			items[j-1] = items[j];
		size--;
		return true;
	}
	
	public T get(int n){ // Ž���� ���� Ž������ �����Ѵ�.
		return items[n];
	}

}