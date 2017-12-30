import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Queue;

public class ArrayQueue<E> extends AbstractQueue<E> implements Queue<E> 
{

	public static final int DEFAULT_CAPACITY = 3; // default capacity of array

	private int capacity;                    // maximum capacity of array
	private E[] theData;                  // holds elements of queue
	private int front;                       // index where the next element will be dequeued
	private int rear;                        // index where the next element will be enqueued
	private int size; 
	/** 
       Constructs a queue of default capacity - 1. 
	 */
	public ArrayQueue() 
	{
		this(DEFAULT_CAPACITY);
	}

	/** 
       Constructs a queue of specified capacity - 1. 

       @param capacity the capacity of the queue - 1. 
	 */
	public ArrayQueue(int initCapacity) 
	{
		capacity = initCapacity;
		theData =(E[]) new Object[capacity];
		front = 0;
		rear = capacity-1;
		size = 0;
	}

	public E peek(){
		if(size==0)
			return null;
		else
			return theData[front];
	}		

	public E poll() {
		if(size==0){
			return null;
		}
		E result = theData[front];
		front = (front+1)%capacity;
		size--;
		return result;
	}

	public void  reallocate(){
		int newCapacity = 2*capacity;
		E[] newData = (E[])new  Object[newCapacity];
		int j = front;
		for(int i=0; i<size; i++){
			newData[i]=theData[j];
			j = (j+1)%capacity;
		}
		front=0;
		rear =size-1;
		capacity = newCapacity;
		theData = newData;
	}


	public int size() {
		return size;
	}

	@Override
	public boolean offer(E item) {
		if(size==capacity-1){
			reallocate();
		}
		size++;
		rear=(rear+1)%capacity;
		theData[rear]=item;
		return true;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		int temp = front;
		while (temp != rear+1) {
			s.append(theData[temp] + "\n");
			temp = (temp + 1) % capacity;
		}
		return s.toString();
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
