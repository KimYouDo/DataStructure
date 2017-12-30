import KW.CH06.BinaryTree;
import java.io.*;
import java.util.*;
public class HuffmanTree extends KW.CH06.HuffmanTree implements Serializable{
	private Map<Character, BitString> codeMap;
	public static HuffData[] buildFreqTable(BufferedReader ins) {
		Map<Character, Integer> frequencies =
				new HashMap<Character, Integer>(); // Map of frequencies.
		try {
			int nextChar; // For storing the next character as an int
			while ((nextChar= ins.read()) != -1) { // Test for more data
				Character next = new Character((char) nextChar);
				Integer count = frequencies.get(next);
				if (count == null) {
					count = 1; // First occurrence.
				} else {
					count++;
				}
				frequencies.put(next, count);
			}
			ins.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// Copy Map entries to a HuffData[] array.
		HuffData[] freqTable = new HuffData[frequencies.size()];
		int i = 0; // Start at beginning of array.
		// Get each map entry and store it in the array
		// as a weight-symbol pair.
		for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
			freqTable[i] =
					new HuffData(entry.getValue().doubleValue(),
							entry.getKey());
			i++;
		}
		return freqTable; // Return the array.
	}
	public void buildCodeTable() {
		// Initialziethe code map
		codeMap= new HashMap<Character, BitString>();
		// Call recursive method with empty bit string for code so far.
		buildCodeTable(huffTree, new BitString());
	}
	private void buildCodeTable(BinaryTree<HuffData> tree, BitString code) {
		// Get data at local root.
		HuffData datum = tree.getData();
		if (datum.getSymbol() != null) { // Test for leaf node.
			// Found a symbol, insert its code in the map.
			codeMap.put(datum.getSymbol(), code);
		} else {
			// Append 0 to code so far and traverse left.
			BitString leftCode= code.append(false);//0
			buildCodeTable(tree.getLeftSubtree(), leftCode);
			// Append 1 to code so far and traverse right.
			BitString rightCode= code.append(true);//1
			buildCodeTable(tree.getRightSubtree(), rightCode);
		}
	}
	public void encode(BufferedReader ins,ObjectOutputStream outs) {
		BitString result = new BitString(); // The complete bit string.
		try {
			int nextChar;  
			while ((nextChar= ins.read()) != -1) { // More data?
				Character next = (char) nextChar;
				System.out.println(next+" "+nextChar);
				// Get bit string corresponding to symbol nextChar.
				BitString nextChunk= codeMap.get(next);
				result = result.append(nextChunk); // Append to result string.
			}
			result.trimCapacity();
			// Write result to output file and close files.
			outs.writeObject(result);
			ins.close();
			outs.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}
	public void decode(ObjectInputStream ins, BufferedWriter out) {
		try {
			BitString codedMessage= (BitString) ins.readObject();
			BinaryTree<HuffData> currentTree= huffTree;
			for (int i= 0; i< codedMessage.size(); i++) {
				if (codedMessage.get(i)) {
					currentTree= currentTree.getRightSubtree();
				} else {
					currentTree= currentTree.getLeftSubtree();
				}
				if (currentTree.isLeaf()) {
					HuffData datum = currentTree.getData();
					out.write( datum.getSymbol() );
					currentTree= huffTree;
				}
			}
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}
}