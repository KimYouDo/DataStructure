package KW.CH06;

import java.io.*;
import java.util.*;
/* Class to represent and build a Huffman tree. */
public class HuffmanTree implements Serializable {
	// Nested Classes
	/** A datum in the Huffman tree. */
	public static class HuffData implements Serializable {
		// Data Fields
		/** The weight or probability assigned to this HuffData. */
		private double weight;
		/** The alphabet symbol if this is a leaf. */
		private Character symbol;
		public HuffData(double weight, Character symbol) {
			this.weight = weight;
			this.symbol = symbol;
		}
		public Character getSymbol() {return symbol;}
	}
	/** A reference to the completed Huffman tree. */
	protected BinaryTree<HuffData> huffTree;
	/** A Comparator for Huffman trees; nested class. */
	private static class CompareHuffmanTrees
	implements Comparator<BinaryTree<HuffData>> {
		public int compare(BinaryTree<HuffData> treeLeft,
				BinaryTree<HuffData> treeRight) {
			double wLeft = treeLeft.getData().weight;
			double wRight = treeRight.getData().weight;
			return Double.compare(wLeft, wRight);
		}
	}
	/**
	 * Builds the Huffman tree using the given alphabet and weights.
	 * @post huffTree contains a reference to the Huffman tree.
	 * @param symbols An array of HuffData objects
	 */
	public void buildTree(HuffData[] symbols) {
		Queue<BinaryTree<HuffData>> theQueue =
				new PriorityQueue<BinaryTree<HuffData>>(symbols.length,
						new CompareHuffmanTrees());
		// Load the queue with the leaves.
		for (HuffData nextSymbol : symbols) {
			BinaryTree<HuffData> aBinaryTree =
					new BinaryTree<HuffData>(nextSymbol, null, null);
			theQueue.offer(aBinaryTree);
		}
		//Build the tree.
		while (theQueue.size() > 1) {
			BinaryTree<HuffData> left = theQueue.poll();
			BinaryTree<HuffData> right = theQueue.poll();
			double wl = left.getData().weight;
			double wr = right.getData().weight;
			HuffData sum = new HuffData(wl + wr, null);
			BinaryTree<HuffData> newTree =
					new BinaryTree<HuffData>(sum, left, right);
			theQueue.offer(newTree);
		}
		//The queue should now contain only one item.
		huffTree = theQueue.poll();
	}
	private void printCode(String code, BinaryTree<HuffData> tree) {
		HuffData theData = tree.getData();
		if (theData.symbol != null) {
			if (theData.symbol.equals(' ')) {
				System.out.println("space: " + code);
			} else {
				System.out.println(theData.symbol + ": " + code);
			}
		} else {
			printCode(code + "0", tree.getLeftSubtree());
			printCode(code + "1", tree.getRightSubtree());
		}
	}
	/* Outputs the resulting code. */
	public void printCode() {
		printCode("", huffTree);
	}
	/**
	 * Method to decode a message that is input as a string of
	 * digit characters '0' and '1'.
	 */
	public String decode(String codedMessage) {
		StringBuilder result = new StringBuilder();
		BinaryTree<HuffData> currentTree = huffTree;
		for (int i = 0; i < codedMessage.length(); i++) {
			if (codedMessage.charAt(i) == '1') {
				currentTree = currentTree.getRightSubtree();
			} else {
				currentTree = currentTree.getLeftSubtree();
			}
			if (currentTree.isLeaf()) {
				HuffData theData = currentTree.getData();
				result.append(theData.symbol);
				currentTree = huffTree;
			}
		}
		return result.toString();
	}
}