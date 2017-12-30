import KW06.BinarySearchTree;

public class BinarySearchTreeWithRotate<E extends Comparable<E>>extends BinarySearchTree<E> {
	protected Node<E> rotateRight(Node<E> root) {
		Node<E> temp = root.left;
		root.left= temp.right;
		temp.right= root;
		return temp;
	}

	protected Node<E> rotateLeft(Node<E> root) {
		Node<E> temp = root.right;
		root.right= temp.left;
		temp.left= root;
		return temp;
	}
	

	public static  void main(String[] ar){
		AVLTree a = new AVLTree();
		String str = "The quick brown fox jumps over the lazy dog";
		String[] nstr = str.split(" ");
		for(int i=0; i<nstr.length; i++)
			a.add(nstr[i]);
		
			
		System.out.println(a.toString());
	}

}