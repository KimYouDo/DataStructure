import java.io.PrintStream;


public class HuffmanTreeMain {
	public static  void main(String[] ar){
		PrintStream out = null ;
	HuffmanTree HT = new HuffmanTree();
	HuffmanTree.HuffData[] HD = new HuffmanTree.HuffData[5];
	HD[0] = new HuffmanTree.HuffData(64,'a');
	HD[1] = new HuffmanTree.HuffData(13,'b');
	HD[2] = new HuffmanTree.HuffData(22,'c');
	HD[3] = new HuffmanTree.HuffData(32,'d');
	HD[4] =new HuffmanTree.HuffData(103,'e');
	HT.buildTree(HD);
	HT.toString();
	HT.printCode();
	
	System.out.println(HT.decode("0101110"));
	
}
}