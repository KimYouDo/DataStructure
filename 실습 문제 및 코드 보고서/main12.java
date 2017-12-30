package week12;

import java.util.Scanner;

public class Main12 {
	public static void main(String[] args) {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
		String command;
		int item;
		Scanner input = new Scanner(System.in);
		System.out
				.println("Enter a command: e(nqueue), d(equeue), s(ize), 	peek, sort, p(rint), or q(uit)");
		System.out.print("> ");
		command = input.next();
		while (!command.equals("q")) {
			if (command.equals("e")) {
				item = input.nextInt();
				queue.enqueue(item);
			} else if (command.equals("d"))
				queue.dequeue();
			else if (command.equals("s"))
				System.out.println("size: " + queue.size());
			else if (command.equals("peek"))
				System.out.println("Front of the queue: " + queue.peek());
			else if (command.equals("p"))
				System.out.println(queue);
			else if (command.equals("sort"))
				radixSort();

			System.out.print("> ");
			command = input.next();
		}
		System.out.println("Commands Terminated.");
	}

	private static void radixSort() {
		final int MAX_ITEMS = 20;
		Integer[] items = new Integer[MAX_ITEMS];
		for (int i = 0; i < items.length; i++)
			items[i] = (int) (10000.0 * Math.random());
		System.out.print("정렬되지 않은 데이터:\t");
		for (int i = 0; i < items.length; i++)
			System.out.print(items[i] + " ");
		System.out.println();

		int radix = 10;
		ArrayQueue<Integer>[] queues = new ArrayQueue[radix];
		for (int i = 0; i < radix; i++)
			queues[i] = new ArrayQueue<Integer>();

		for (int i = 0; i < MAX_ITEMS; i++)
			queues[items[i] % 10].enqueue(items[i]);
		System.out.print("1의 자리 정렬\t");
		for (int i = 0; i < radix; i++) {
			System.out.print(queues[i]);
		}
		System.out.println();
		for (int i = 0; i < radix; i++)
			queues[i] = new ArrayQueue<Integer>();

		for (int i = 0; i < MAX_ITEMS; i++)
			queues[(items[i] / 10) - ((items[i] / 100) * 10)].enqueue(items[i]);
		System.out.print("10의 자리 정렬\t");
		for (int i = 0; i < radix; i++)
			System.out.print(queues[i]);
		System.out.println();
		for (int i = 0; i < radix; i++)
			queues[i] = new ArrayQueue<Integer>();

		for (int i = 0; i < MAX_ITEMS; i++)
			queues[(items[i] / 100) - ((items[i] / 1000) * 10)]
					.enqueue(items[i]);
		System.out.print("100의 자리 정렬\t");
		for (int i = 0; i < radix; i++)
			System.out.print(queues[i]);
		System.out.println();
		for (int i = 0; i < radix; i++)
			queues[i] = new ArrayQueue<Integer>();

		for (int i = 0; i < MAX_ITEMS; i++)
			queues[items[i] / 1000].enqueue(items[i]);
		System.out.print("1000의 자리 정렬\t");
		for (int i = 0; i < radix; i++)
			System.out.print(queues[i]);
		System.out.println();
	}
}

class ArrayQueue<T> implements Queue<T> {
	private int front;
	private int rear;
	private int count;
	private int MaxSize;
	private T[] items;

	public ArrayQueue() { // 무인자 큐 생성자
		front = 0;
		rear = 0;
		count = 0;
		MaxSize = 3;
		items = (T[]) new Object[MaxSize];
	}

	public boolean isEmpty() {
		return (count == 0);
	}

	public int size() {
		return count;
	}

	public void enqueue(T item) {
		if (count == MaxSize - 1) {
			System.out.println("resize called");
			resize();
		}
		items[rear] = item;
		rear = (rear + 1) % MaxSize;
		count++;
	}

	public void resize() {
		int oldsize = MaxSize;
		MaxSize += (MaxSize * 2);
		T[] tempArray = (T[]) new Object[MaxSize];
		for (int i = 0; i < count; i++) {
			tempArray[i] = items[front];
			front = (front + 1) % oldsize;
		}
		items = tempArray;
		front = 0;
		rear = count;
	}

	public T dequeue() {
		if (isEmpty())
			return null;

		T item = items[front];
		front = (front + 1) % MaxSize;
		count--;
		return item;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		int temp = front;
		while (temp != rear) {
			s.append(items[temp] + " ");
			temp = (temp + 1) % MaxSize;
		}
		return s.toString();
	}

	public T peek() {
		if (isEmpty())
			return null;
		else
			return items[front];
	}

}

interface Queue<T> {
	public void enqueue(T item);

	public T dequeue();

	public T peek();

	public boolean isEmpty();
}
