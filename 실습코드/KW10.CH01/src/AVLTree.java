

public class AVLTree<E extends Comparable<E>> 
         extends BinarySearchTreeWithRotate<E> { 
	private static class AVLNode<E>extends Node<E> {
		/** Constant to indicate left-heavy */
		public static final int LEFT_HEAVY= -1;
		/** Constant to indicate balanced */
		public static final int BALANCED= 0;
		/** Constant to indicate right-heavy */
		public static final int RIGHT_HEAVY= 1;
		/** balance is right subtreeheight-left subtreeheight */
		private int balance;

		// Methods
		/**
		 * Construct a node with the given item as the data field.
		 * @paramitem The data field
		 */
		public AVLNode(E item) {
			super(item);
			balance = BALANCED;
		}
		/**
		 * Return a string representation of this object.
		 * The balance value is appended to the contents.
		 * @return String representation of this object
		 */
		@Override
		public String toString() {
			return balance + ": " + super.toString();
		}
	}
	/** add starter method.
	pre: the item to insert implements the Comparableinterface.
	@paramitem The item being inserted.
	@return true if the object is inserted; false
	if the object already exists in the tree
	@throws ClassCastExceptionif item is not Comparable
	 */
	private boolean increase;
	@Override
	public boolean add(E item) {
		increase = false;
		root = add((AVLNode<E>) root, item);
		return addReturn; // true or false
	}
	/** Recursive addmethod. Inserts the given object into the tree.
	post: addReturnis set trueif the item is inserted,
	false if the item is already in the tree.
	@paramlocalRootThe local root of the subtree
	@paramitem The object to be inserted
	@return The new local root of the subtreewith the item
	inserted
	 */
	private AVLNode<E> add(AVLNode<E> localRoot, E item){
		if (localRoot== null) {
			addReturn= true;
			increase= true;
			return new AVLNode<E>(item);
		}
		if (item.compareTo(localRoot.data) == 0) {
			// Item is already in the tree.
			increase = false;
			addReturn= false;
			return localRoot;
		}
		else if (item.compareTo(localRoot.data) <0) {
			// item < data
			localRoot.left = add((AVLNode<E>) localRoot.left, item);
			if (increase) {
				decrementBalance(localRoot);
				if (localRoot.balance < AVLNode.LEFT_HEAVY) {
					increase = false;
					return rebalanceLeft(localRoot);
				}
			}
			return localRoot; // Rebalance not needed.
		} else { // item > data
			localRoot.right= add((AVLNode<E>) localRoot.right, item);
			if (increase) {
				increamentBalance(localRoot);
				if (localRoot.balance> AVLNode.RIGHT_HEAVY) {
					return rebalanceRight(localRoot);
				} else {
					return localRoot;
				}
			} else {
				return localRoot;
			}
		}
	}
	
	private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
		// Obtain reference to left child.
				AVLNode<E> rightChild= (AVLNode<E>) localRoot.right;
				// See whether left-right heavy.
				if (rightChild.balance< AVLNode.BALANCED) {
					// Obtain reference to left-right child.
					AVLNode<E> RightleftChild= (AVLNode<E>) rightChild.left;
					// Adjust the balances to be their new values after
					// the rotations are performed.
					if (RightleftChild.balance> AVLNode.BALANCED) {
						rightChild.balance= AVLNode.LEFT_HEAVY;
						RightleftChild.balance= AVLNode.BALANCED;
						localRoot.balance= AVLNode.BALANCED;
					} else if (RightleftChild.balance< AVLNode.BALANCED) {
						rightChild.balance= AVLNode.BALANCED;
						RightleftChild.balance= AVLNode.BALANCED;
						localRoot.balance= AVLNode.RIGHT_HEAVY;
					} else {
						rightChild.balance= AVLNode.BALANCED;
						localRoot.balance= AVLNode.BALANCED;
					}
					// Perform left rotation.
					localRoot.right= rotateRight(rightChild);
				} else { //Left-Left case
					// In this case the leftChild(the new root)
					// and the root (new right child) will both be balanced
					// after the rotation.
					rightChild.balance= AVLNode.BALANCED;
					localRoot.balance= AVLNode.BALANCED;
				}
				// Now rotate the local root right.
				return (AVLNode<E>) rotateLeft(localRoot);
	}

	private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
		// Obtain reference to left child.
		AVLNode<E> leftChild= (AVLNode<E>) localRoot.left;
		// See whether left-right heavy.
		if (leftChild.balance> AVLNode.BALANCED) {
			// Obtain reference to left-right child.
			AVLNode<E> leftRightChild= (AVLNode<E>) leftChild.right;
			// Adjust the balances to be their new values after
			// the rotations are performed.
			if (leftRightChild.balance< AVLNode.BALANCED) {
				leftChild.balance= AVLNode.LEFT_HEAVY;
				leftRightChild.balance= AVLNode.BALANCED;
				localRoot.balance= AVLNode.BALANCED;
			} else if (leftRightChild.balance> AVLNode.BALANCED) {
				leftChild.balance= AVLNode.BALANCED;
				leftRightChild.balance= AVLNode.BALANCED;
				localRoot.balance= AVLNode.RIGHT_HEAVY;
			} else {
				leftChild.balance= AVLNode.BALANCED;
				localRoot.balance= AVLNode.BALANCED;
			}
			// Perform left rotation.
			localRoot.left= rotateLeft(leftChild);
		} else { //Left-Left case
			// In this case the leftChild(the new root)
			// and the root (new right child) will both be balanced
			// after the rotation.
			leftChild.balance= AVLNode.BALANCED;
			localRoot.balance= AVLNode.BALANCED;
		}
		// Now rotate the local root right.
		return (AVLNode<E>) rotateRight(localRoot);
	}
	private void decrementBalance(AVLNode<E> node) {
		// Decrement the balance.
		node.balance--;
		if (node.balance== AVLNode.BALANCED) {
			/** If now balanced, overall height has not increased. */
			increase = false;
		}
	}

	private void increamentBalance(AVLNode<E> node) {
		// Decrement the balance.
		node.balance++;
		if (node.balance== AVLNode.BALANCED) {
			/** If now balanced, overall height has not increased. */
			increase = false;
		}
	}
}

