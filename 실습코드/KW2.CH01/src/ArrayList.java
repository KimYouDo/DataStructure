import java.util.*;
class SortedArrayList <T extends Comparable<T>> {
	private T[] items;
	private int size = 0;
	private int maxSize = 5;
	
	private int scanPos = 0;// 스캔하기 위해 필요한 스캔 초기 위치
	
	public boolean hasNext(){// 배열에 액세스할 데이터가 있는지 검사
		return scanPos != size-1;
	}
	public T next(){// 현재 스캔 위치에서 배열의 데이터를 반환
		return items[scanPos++];//.. 하고 현재 스캔 위치를 증가
	}
	public void reset(){// 스캔 위치를 배열의 첫번 째로 초기화
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
	
	// 다음의 메소드를 완성한다.
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
	
	public T get(int n){ // 탐색은 이진 탐색으로 구현한다.
		return items[n];
	}

}