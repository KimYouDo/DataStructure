import java.util.Scanner;
import java.util.Stack;

public class testmain {
	public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			String st = in.next();
			PalFinder2 ast = new PalFinder2(st);
			String rst;
		
			ast.print();
		if(ast.isPalindrome())	
			System.out.println("��ġ  "+st);
		else
			System.out.println("�� ��ġ");

	}
}