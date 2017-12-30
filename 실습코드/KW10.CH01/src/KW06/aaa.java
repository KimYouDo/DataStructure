package KW06;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class aaa {
	public static void main(String[] args) throws FileNotFoundException {
		int countRow = 1;
		int row, cal;
		String s, num;
		int strtR, strtC;

		String fileName = "maze.txt";
		Scanner inFile = null;
		inFile = new Scanner(new File(fileName));
		
	        row = inFile.nextInt();
		cal = inFile.nextInt();
	        int[][] input = new int[row + 2][cal + 2];
		for (int i = 0; i < row + 2; i++) {
			input[i][0] = 1;
			input[i][cal + 1] = 1;
		}
		for (int j = 0; j < cal + 2; j++) {
			input[0][j] = 1;
			input[row + 1][j] = 1;
		}

		while (inFile.hasNext()) { // �� ���ξ� �о� �ֿܼ� ���
			s = inFile.next();
			for (int i = 1; i < cal + 1; i++) {
				num = s.substring(i-1,i);
				if(num.equals("E"))
					input[countRow][i] = 2;
				else
					input[countRow][i] = Integer.parseInt(num);				
			}
			countRow++;
		}
		inFile.close();
		
		fileName = "input.txt";
		inFile = null;
		inFile = new Scanner(new File(fileName));
		while (inFile.hasNext()) { // �� ���ξ� �о� �ֿܼ� ���
			MazePathMz mpm = new MazePathMz(row, cal);
			strtR = inFile.nextInt();
			strtC = inFile.nextInt();
			System.out.println("�����(S:����, E:����): " + strtR + "," + strtC);
			mpm.MazePath(input, strtR, strtC);
			System.out.println("----------------------------------------------"
					+ "---------------------------------------\n");
		}
		inFile.close();
	}
}




class MazeCell implements Comparable<MazeCell>{
	int x;
	int y;
	int dir;

	public MazeCell(int i, int j, int _dir) {
		x = j;
		y = i;
		dir = _dir;
	}

	public String toString() {
		return "<" + y + "," + x + ">";
	}

	@Override
	public int compareTo(MazeCell o) {
		// TODO Auto-generated method stub
		return 0;
	}
}

class Stack <T extends Comparable<T>>{
	private int top; // �� ���Ҹ� ����Ű�� �ε��� ����
	private int size = 5; // ����(�迭)�� ũ��
	private T[] items; // Java ��ü Ÿ���� ���Ҹ�

	// ���� ������ �� �ִ� �迭

	public Stack() { // ���� �������� �ʱ�ȭ
		top = -1;
		items = (T[]) new Comparable[size];
	}

	public boolean isEmpty() { // ������ �����ΰ��� �˻�
		return top == -1;
	}

	public void push(T item) { 
		if (top == size - 1) // ������ ������ ���
			reSize();
		items[++top] = item; // ���� ����
	} // end push()

	public void reSize() { // ���� ũ�⸦ Ȯ��
		size += (size * 2); // �迭 ũ�⸦ increment��ŭ Ȯ��
		// Ȯ��� ũ���� �迭 ����
		T[] tempArray = (T[]) new Comparable[size];
		// Ȯ��� �迭�� ���� �̵�
		for (int i = 0; i <= top; i++)
			tempArray[i] = items[i];
		// Ȯ��� �迭�� itemArray �迭 ������
		// ����Ű���� ����
		items = tempArray;
	}


	public T pop() { // ������ �� ���Ҹ� �����Ͽ� ��ȯ
		if (isEmpty())
			return null; // ������ ���
		else
			return items[top--];
	} 

	public void remove() { // ������ �� ���Ҹ� ����
		if (isEmpty())
			return; // ������ ���
		else
			top--;
	}

	public T peek() { // ������ �� ���� �˻�
		if (isEmpty())
			return null; // ������ ���
		else
			return items[top];
	} 
} 

class MazePathMz {
	private int mark[][];
	private int maze[][];
	private int[][] move = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } }; // �� �� �� ��

	public MazePathMz(int r, int c) {
		mark = new int[r + 2][c + 2];
		maze = new int[r + 2][c + 2];

	}

	public void MazePath(int[][] mpm, int strtR, int strtC) {
		for (int i = 0; i < mpm.length; i++)
			for (int j = 0; j < mpm[0].length; j++)
				maze[i][j] = mpm[i][j];
		Stack<MazeCell> st = new Stack<MazeCell>();
		Stack<MazeCell> st1 = new Stack<MazeCell>(); // �ùٸ� ��� ������ ����
		st.push(new MazeCell(strtR, strtC, 1)); // �ʱ������ġ�� ���� ����
		while (st.isEmpty() != true) {
			MazeCell mc = (MazeCell) st.pop(); // ������ ��θ� pop��
			while (mc.dir <= 3) {
				int nextI = mc.y + move[mc.dir][0]; // �������ϴ� �������� i+
				int nextJ = mc.x + move[mc.dir][1]; // �������ϴ� �������� j+

				if (maze[nextI][nextJ] == 2) { // �̷ΰ�� �߰�
					int jampStack = 0;
					System.out.println("��θ� �߰��߽��ϴ�");
					st.push(new MazeCell(mc.y, mc.x, mc.dir)); // ������ ��� ����
					st.push(new MazeCell(nextI, nextJ, 0)); // ������ ���� ����
					while (st.isEmpty() != true) {
						st1.push((MazeCell) st.pop());
						mc = st1.peek();
						if(mc.y==strtR&&mc.x==strtC)
							jampStack++;
					}
					if(jampStack==2){
						for(int i=0; i<2; i++)
							st1.remove();
					}
					jampStack=1;
					while (st1.isEmpty() != true) {											mc = (MazeCell) st1.pop(); // �ùٸ� ��� ���
						System.out.print(mc);
						if(jampStack%15==0)
							System.out.println();
						jampStack++;
						if((mc.y==nextI&&mc.x==nextJ))
							maze[mc.y][mc.x] = 2;
						else if((mc.y==strtR&&mc.x==strtC))
							maze[mc.y][mc.x] = 3;
						else
						    maze[mc.y][mc.x] = 4;
					}
					System.out.println("\n");
					displayMaze(maze); // ��� Ȯ�� �Ϸ��� ���
					return;
				}
			if (maze[nextI][nextJ] == 0 && mark[nextI][nextJ] == 0) {// �̵�����&�õ��غ��� ������ġ
				mark[nextI][nextJ] = 1;
				st.push(new MazeCell(mc.y, mc.x, mc.dir)); 
					mc.y = nextI;
					mc.x = nextJ;
					mc.dir = 0;
			} 
			else {
				mc.dir++;
			}
		}
	}
		System.out.println("��θ� �߰����� ���߽��ϴ�");
}

	public void displayMaze(int[][] maze) {
		String[] str = { "0", "1", "E", "S" ,"*"};
		System.out.print("    ");
		for (int j = 0; j < maze[0].length; j++){
				System.out.print("["+j+"]");
		}
		System.out.println();
		for (int i = 0; i < maze.length; i++) {
			if(i<10)
			System.out.print("["+i+"] ");
			else
				System.out.print("["+i+"]");
			for (int j = 0; j < maze[0].length; j++) {
				if(j<10)
					System.out.print(" "+str[(maze[i][j])]+" ");
				else
					System.out.print("  "+str[(maze[i][j])]+" ");
			}
			System.out.println();
		}
	}

}