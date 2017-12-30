
public class DoubleStack3<T> {
	public DoubleStack3() {
		items = (T[]) new Comparable[maxSize];
	}	
	public DoubleStack3(int max) {
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
}
