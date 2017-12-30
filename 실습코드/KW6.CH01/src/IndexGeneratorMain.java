import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class IndexGeneratorMain {
	public static  void main(String[] ar) throws FileNotFoundException{
		Scanner inFile = new Scanner(new File(" "));
		IndexGenerator IG = new IndexGenerator();

		IG.buildIndex(inFile);
		IG.showIndex();
	}
}
