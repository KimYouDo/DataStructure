
public class DoubleStack<T>  {
	private T[] items;
	private int size = 0;
	private int maxSize = 5;
	int m1 =0;
	int m2 =0;

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

	public DoubleStack() {
		items = (T[]) new Comparable[maxSize];
	}	
	public DoubleStack(int max) {
		items = (T[]) new Comparable[max*2];
		m2 = max;
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

	// 다음의 메소드를 완성한다.
	public void push(int i, T data) {
		if(i==1){
			items[m1]=data;
			m1++;
		}
		else{
			items[m2]=data;
			m2++;
		}
		size++;
	}

	public boolean eq() {
		for(int i=0; i<size/2; i++)
			if(items[i]!=items[size-i-1])
				return false;
		return true;
	}
	public String toString1() {
		String st1="";
		for (int i = 0; i < size/2; i++)
			st1=st1+items[i];	
		return st1;
	}

	public String toString2() {
		String st1="";
		for (int i = size/2; i < size; i++)
			st1=st1+items[i];	
		return st1;
	}

}


