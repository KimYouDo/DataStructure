import java.util.LinkedList;

public class BTree<E extends Comparable<E>> {
	private static class Node<E> {
		private int size = 0;
		private E[] data;
		private Node<E>[] child;
	
		@SuppressWarnings("unchecked")
		public Node(int order) {
			data = (E[]) new Comparable[order -1];
			child = (Node<E>[]) new Node[order];
			size = 0;
		}
		
		@Override
		public String toString() {// BtreeNode 내용을프린트
			StringBuilder sb= new StringBuilder();
			for (int i = 0; i < size -1; ++i) {
				sb.append(data[i].toString());
				sb.append(", ");
			}
			sb.append(data[size -1].toString());
			return sb.toString();
		}
	}
	// Data fields
	
	private Node<E> root = null;
	/** The maximum number of children of a node */
	private int order;
	/** The parent of a split node returned from insert */
	private E newParent;
	/** The right half of a split node returned from insert */
	private Node<E> newChild;

	public BTree(int order) {
		if (order < 2) {
			throw new IllegalArgumentException("order < 2");
		}
		this.order= order;
		root = null;
	}
	
	public boolean add(E item){
		if(root==null){
			root = new Node<E>(order);
			root.data[0] = item;
			root.size = 1;
			return true;
		}
		newChild = null;
		boolean result = insert(root, item);
		if(newChild != null){
			Node<E> newRoot = new Node<E>(order);
			newRoot.child[0] = root;
			newRoot.child[1] = newChild;
			newRoot.data[0] = newParent;
			newRoot.size = 1;
			root = newRoot;
		}
		return result;
	}

	private boolean insert(Node<E> root, E item) {
		int index = binarySearch(item, root.data, 0, root.size);
		if (index != root.size&& item.compareTo(root.data[index]) == 0) {
			return false;
		}
		if (root.child[index] == null) {
			if (root.size< order -1) {
				insertIntoNode(root, index, item, null);
				newChild= null;
			} else {
				splitNode(root, index, item, null);
			}
			return true;
		} else {
			boolean result = insert(root.child[index], item);
			if (newChild!= null) {
				if (root.size< order -1) {
					insertIntoNode(root, index, newParent, newChild);
					newChild= null;
				} else {
					splitNode(root, index, newParent, newChild);
				}
			}
			return result;
		}
	}
	
	private void insertIntoNode(Node<E> node, int index, E item, Node<E> child) {
		for (int i = node.size; i > index; i--) {
			node.data[i] = node.data[i-1];
			node.child[i+ 1] = node.child[i];
		}
		node.data[index] = item;
		node.child[index + 1] = child;
		node.size++;
	}
	
	private void splitNode(Node<E> node, int index, E item, Node<E> child){
		// Create new child
		newChild= new Node<E>(order);
		// Determine number of items to move
		int numToMove= (order -1) -((order -1) / 2);
		// If insertion point is to the right half, move one less item
		if (index > (order -1) / 2) {
			numToMove--;
		}
		// Move items and their children
		System.arraycopy(node.data, order -numToMove-1,newChild.data, 0, numToMove);
		System.arraycopy(node.child, order -numToMove, newChild.child, 1, numToMove);
		node.size= order -numToMove-1;
		newChild.size= numToMove;
		//Insert new item
		if (index == ((order -1) / 2)) { // Insert into middle
			newParent= item;
			newChild.child[0] = child;
		} else {
			if (index < ((order -1) / 2)) { // Insert into the left
				insertIntoNode(node, index, item, child);
			} else {
				insertIntoNode(newChild, index -((order -1) / 2) -1,
						item, child);
			}
			//The rightmost item of the node is the new parent
			newParent= node.data[node.size-1];
			//Its child is the left child of the split-off node
			newChild.child[0] = node.child[node.size];
			node.size--;
		}
		//Remove contents and references to moved items
		for (int i = node.size; i < node.data.length; i++) {
			node.data[i] = null;
			node.child[i+ 1] = null;
		}
	}
	
	private int binarySearch(E target, E[] data, int first, int last) {
		if (first == last) return first;
		if (last -first == 1) {
			if (target.compareTo(data[first]) <= 0) {
				return first;
			} else {
				return last;
			}
		}
		int middle = first + (last -first) / 2;
		int compResult= target.compareTo(data[middle]);
		if (compResult== 0) {
			return middle;
		}
		if (compResult< 0) {
			return binarySearch(target, data, first, middle);
		} else {
			return binarySearch(target, data, middle + 1, last);
		}
	}
	
	@Override
	public String toString() { // Btree를PreOrderTraversal 순서로프린트
		StringBuilder sb= new StringBuilder();
		preOrderTraverse(root, 0, sb);
		return sb.toString();
	}
	
	private void preOrderTraverse(Node<E> node, int d, StringBuilder sb) {
		for (int i = 0; i != d; ++i) {
			sb.append(" ");
		}
		if (node == null) {
			sb.append("null");
		} else {
			for (int i = 0; i != node.size; ++i) {
				sb.append(node.data[i]);
				if (i!= node.size-1) {
					sb.append(", ");
				}
			}
			sb.append("\n");
			for (int i = 0; i != node.size; ++i) {
				preOrderTraverse(node.child[i], d + 1, sb);
				sb.append("\n");
			}
			preOrderTraverse(node.child[node.size], d + 1, sb);
			sb.append("\n\n");
		}
	}
	
	public String toString2() { // Btree를PreOrderTraversal 순서로프린트
		LinkedList<String> sb= new LinkedList<String>();
		preOrderTraverse2(root, 0, sb);
		LinkedList<String>[] printline = new LinkedList[5];
	
		for(int i=0; i<5; i++){
			printline[i] = new LinkedList<String>();
		}
		for(int i=0; i<sb.size(); i++){
			String[] nst=sb.get(i).split(" - ");
			printline[Integer.parseInt(nst[0])].add(nst[1]);	
		}
		for(int i=0; i<5; i++){
			for(int z=0; z<15-(i*2); z++)
				System.out.print("   ");
			for(int j=0; j<printline[i].size(); j++)
				System.out.print(printline[i].get(j)+"     ");
			System.out.println();
		}
			return sb.toString();
	}
	
	private void preOrderTraverse2(Node<E> node, int d, LinkedList<String> sb) {
		String st = "";
		st+=d+" - ";
		if (node == null) {
			//sb.add("null");
		} else {
			st+="(";
			for (int i = 0; i != node.size; ++i) {
				st+=node.data[i];
				if (i!= node.size-1) {
					st+=", ";
				}
			}
			st+=")";
			sb.add(st);
			for (int i = 0; i != node.size; ++i) {
				preOrderTraverse2(node.child[i], d + 1, sb);
				//sb.add("\n");
			}
			preOrderTraverse2(node.child[node.size], d + 1, sb);
			//sb.add("\n\n");
		}
	}
	
	 public static void main(String[] args){
		 BTree a = new BTree(3);
		 String str = "The quick brown fox jumps over the lazy dog";
			String[] nstr = str.split(" ");
			for(int i=0; i<nstr.length; i++)
				a.add(nstr[i]);
			
			System.out.println(a.toString());
	 }
}