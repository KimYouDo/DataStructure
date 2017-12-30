package KW06;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;




class BinaryTree<E> implements Serializable {

	protected static class Node<E> implements Serializable {
		public E data;
		public Node<E> left;
		public Node<E> right;
		public Object parent;
		public Node(E data) {
			this.data = data;
			left = null;
			right = null;
		}
		public String toString() {
			return data.toString();
		}
	}

	protected Node<E> root;


	public BinaryTree() {
		root = null;
	}

	protected BinaryTree(Node<E> root) {
		this.root = root;
	}


	public BinaryTree(E data, BinaryTree<E> leftTree,BinaryTree<E> rightTree) {
		root = new Node<E>(data);

		if (leftTree != null) {
			root.left = leftTree.root;
		} else {
			root.left = null;
		}

		if (rightTree != null) {
			root.right = rightTree.root;
		} else {
			root.right = null;
		}
	}


	public BinaryTree<E> getLeftSubtree() {
		if (root != null && root.left != null) {
			return new BinaryTree<E>(root.left);
		} else {
			return null;
		}
	}
	public BinaryTree<E> getRightSubtree() {
		if (root != null && root.right != null) {
			return new BinaryTree<E>(root.right);
		} else {
			return null;
		}
	}


	public boolean isLeaf() {
		return (root.left == null && root.right == null);
	}
	
	public static int logB(int x) {
	    return (int) (Math.log(x) / Math.log(2));
	  }

	public String toString() {
		ArrayList<String> sb = new ArrayList<String>();
		preOrderTraverse(root, 1, sb);
		int h= logB(sb.size())+3;

		LinkedList<String>[] gap= new LinkedList[h];
		for(int i=0; i<h; i++)
			gap[i]= new LinkedList<String>();

		for(int i=0; i<sb.size(); i++){
			String[] ngap=sb.get(i).split(" - ");
			gap[Integer.parseInt(ngap[0])].add(ngap[1]);
		}
		
		String strEnd="";
		for(int i=1; i<h; i++){
			for(int q=0; q<15-(i*4); q++)
				strEnd+="  ";
			for(int j=0; j<gap[i].size(); j++){
				if(i==1)
					strEnd+="      ";
				else
				if(j==(gap[i].size()/2))
					for(int w=0; w<10-(j*2); w++)
					strEnd+="  ";
				strEnd+=(gap[i].get(j)+"     ");
			}
			
			strEnd+="\n";
		}
		
		return strEnd;
	}

	private void preOrderTraverse(Node<E> node, int depth,
			ArrayList<String> sb) {
		//for (int i = 1; i < depth; i++) {
			//sb.add(depth+" - "); // indentation
		//}
		if (node == null) {
			sb.add(depth+" - "+"   ");
		} else {
			sb.add(depth+" - "+node.toString());
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}

	 public String inorderToString() {
         StringBuilder stb = new StringBuilder();
         inorderToString(stb, root);
         return stb.toString();
	 }

	
	private void inorderToString(StringBuilder stb, Node<E> root) {
        if (root.left != null) {
            stb.append("(");
            inorderToString(stb, root.left);
            stb.append(") ");
        }
        stb.append(root);
        if (root.right != null) {
            stb.append(" (");
            inorderToString(stb, root.right);
            stb.append(")");
        }
	}

     public static BinaryTree<String> readBinaryTree(Scanner scan) {
		String data = scan.next();
		if (data.equals(null)) {
			
			return null;
		} else {
			BinaryTree<String> leftTree= readBinaryTree(scan);
			BinaryTree<String> rightTree= readBinaryTree(scan);
			return new BinaryTree<String>(data, leftTree,rightTree);
		}
	}
     public E getData() {
         if (root != null) {
             return root.data;
         } else {
             return null;
         }
     }
     
     
     
    /* public E getData() {
         if (root != null) {
             return root.data;
         } else {
             return null;
         }
     }
     
     public String inorderToString() {
         StringBuilder stb = new StringBuilder();
         inorderToString(stb, root);
         return stb.toString();
     }

     private void inorderToString(StringBuilder stb, Node<E> root) {
         if (root.left != null) {
             stb.append("(");
             inorderToString(stb, root.left);
             stb.append(") ");
         }
         stb.append(root);
         if (root.right != null) {
             stb.append(" (");
             inorderToString(stb, root.right);
             stb.append(")");
         }
     }*/


}




