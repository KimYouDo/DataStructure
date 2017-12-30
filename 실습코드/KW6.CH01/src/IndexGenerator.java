import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * Class to build an index.
 *  @author Koffman and Wolfgang
 **/
public class IndexGenerator {

    // Data Fields
    /** Tree for storing the index. */
    private BinarySearchTree<String> index;
    /**
     * Pattern for extracting words from a line
     * A word is a string of one or more letters or numbers or ' characters
     */
    private static final Pattern wordPattern =
            Pattern.compile("[\\p{L}\\p{N}']+");

    // Methods
    public IndexGenerator() {
        index = new BinarySearchTree<String>();
    }

    /**
     * Reads each word in data file bR and stores it in search tree
     * along with its line number.
     * @post Lowercase form of each word with line
     *       number stored in index.
     * @param scan A Scanner object that contains the input text
     */
    public void buildIndex(Scanner scan) {
        int lineNum = 0; // Line number
        // Keep reading lines until done.
        while (scan.hasNextLine()) {
            lineNum++;
            // Extract each token and store it in index
            String token;
            while ((token = scan.findInLine(wordPattern)) != null) {
                index.add(String.format("%s, %3d", token, lineNum));
            }
        }
    }

    /** Displays the index, one word per line. */
    public void showIndex() {
        // Use an iterator to access and display tree data.
        for (String next : index) {
            System.out.println(next);
        }
    }


}

