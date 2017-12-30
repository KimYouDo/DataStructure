
public class DoubleStack<T>  {
	private T[] items;
	private int size = 0;
	private int maxSize = 5;
	int m1 =0;
	int m2 =0;

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

	// ������ �޼ҵ带 �ϼ��Ѵ�.
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


