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

		while (inFile.hasNext()) { // 한 라인씩 읽어 콘솔에 출력
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
		while (inFile.hasNext()) { // 한 라인씩 읽어 콘솔에 출력
			MazePathMz mpm = new MazePathMz(row, cal);
			strtR = inFile.nextInt();
			strtC = inFile.nextInt();
			System.out.println("출발점(S:시작, E:도착): " + strtR + "," + strtC);
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
	private int top; // 톱 원소를 가리키는 인덱스 변수
	private int size = 5; // 스택(배열)의 크기
	private T[] items; // Java 객체 타입의 원소를

	// 실제 저장할 수 있는 배열

	public Stack() { // 스택 변수들을 초기화
		top = -1;
		items = (T[]) new Comparable[size];
	}

	public boolean isEmpty() { // 스택이 공백인가를 검사
		return top == -1;
	}

	public void push(T item) { 
		if (top == size - 1) // 스택이 만원인 경우
			reSize();
		items[++top] = item; // 원소 삽입
	} // end push()

	public void reSize() { // 스택 크기를 확장
		size += (size * 2); // 배열 크기를 increment만큼 확장
		// 확장된 크기의 배열 생성
		T[] tempArray = (T[]) new Comparable[size];
		// 확장된 배열로 원소 이동
		for (int i = 0; i <= top; i++)
			tempArray[i] = items[i];
		// 확장된 배열을 itemArray 배열 변수가
		// 가리키도록 조정
		items = tempArray;
	}


	public T pop() { // 스택의 톱 원소를 삭제하여 반환
		if (isEmpty())
			return null; // 공백인 경우
		else
			return items[top--];
	} 

	public void remove() { // 스택의 톱 원소를 삭제
		if (isEmpty())
			return; // 공백인 경우
		else
			top--;
	}

	public T peek() { // 스택의 톱 원소 검색
		if (isEmpty())
			return null; // 공백인 경우
		else
			return items[top];
	} 
} 

class MazePathMz {
	private int mark[][];
	private int maze[][];
	private int[][] move = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } }; // 북 동 남 서

	public MazePathMz(int r, int c) {
		mark = new int[r + 2][c + 2];
		maze = new int[r + 2][c + 2];

	}

	public void MazePath(int[][] mpm, int strtR, int strtC) {
		for (int i = 0; i < mpm.length; i++)
			for (int j = 0; j < mpm[0].length; j++)
				maze[i][j] = mpm[i][j];
		Stack<MazeCell> st = new Stack<MazeCell>();
		Stack<MazeCell> st1 = new Stack<MazeCell>(); // 올바른 경로 삽입할 스택
		st.push(new MazeCell(strtR, strtC, 1)); // 초기출발위치맟 방향 설정
		while (st.isEmpty() != true) {
			MazeCell mc = (MazeCell) st.pop(); // 지나온 경로를 pop함
			while (mc.dir <= 3) {
				int nextI = mc.y + move[mc.dir][0]; // 갈려고하는 방향으로 i+
				int nextJ = mc.x + move[mc.dir][1]; // 갈려고하는 방향으로 j+

				if (maze[nextI][nextJ] == 2) { // 미로경로 발견
					int jampStack = 0;
					System.out.println("경로를 발견했습니다");
					st.push(new MazeCell(mc.y, mc.x, mc.dir)); // 마지막 경로 지정
					st.push(new MazeCell(nextI, nextJ, 0)); // 마지막 지점 지정
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
					while (st1.isEmpty() != true) {											mc = (MazeCell) st1.pop(); // 올바른 경로 출력
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
					displayMaze(maze); // 경로 확인 완료후 출력
					return;
				}
			if (maze[nextI][nextJ] == 0 && mark[nextI][nextJ] == 0) {// 이동가능&시도해보지 않은위치
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
		System.out.println("경로를 발견하지 못했습니다");
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