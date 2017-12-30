import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		BufferedReader ins = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt"))); 
		ObjectOutputStream outs = new ObjectOutputStream(new FileOutputStream("out.txt"));
		BufferedWriter out = new BufferedWriter(new FileWriter("out2.txt"));
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("in2.txt"));
		HuffmanTree HT = new HuffmanTree();
		HT.encode(ins, outs);
	}
}
