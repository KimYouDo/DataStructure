import java.util.Scanner;


public class BinaryTest {
	public static  void main(String[] ar){

		BinaryTree b=new BinaryTree();

		Scanner a=new Scanner(System.in);
		b=BinaryTree.readBinaryTree(a);
		System.out.println("preorder");
		System.out.print(b.toString());
		System.out.println("inorder");
		System.out.print(b.inorderToString());
	}
} 