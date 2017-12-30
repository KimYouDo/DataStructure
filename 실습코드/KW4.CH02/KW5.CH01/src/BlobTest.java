import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
public class BlobTest extends JFrame implements GridColors {
	private TwoDimGrid theGrid;
	public static void main(String[] args) {
		try {
			String reply = JOptionPane.showInputDialog("Enter number of rows");
			int nRows = Integer.parseInt(reply);
			reply = JOptionPane.showInputDialog("Enter number of columns");
			int nCols = Integer.parseInt(reply);
			TwoDimGrid aGrid = new TwoDimGrid(nRows, nCols);
			new BlobTest(aGrid);
		} catch (Exception ex) {
			System.err.println("Exception " + ex);
			ex.printStackTrace();
			System.exit(1);
		}
	}
	private BlobTest(TwoDimGrid aGrid) {
		theGrid = aGrid;
		getContentPane().add(aGrid, BorderLayout.CENTER);
		Blob aBlob = new Blob(aGrid);
		JTextArea instruct = new JTextArea(2, 10);
		instruct.setText("Toggle a button to change its color" + "\nPress SOLVE when ready");
				getContentPane().add(instruct, BorderLayout.NORTH);
		JPanel bottomPanel = new JPanel();
		JButton solveButton = new JButton("SOLVE");
		solveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solve();
			}
		});
		JButton resetButton = new JButton("RESET");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				(new Blob(theGrid)).restore();
			}
		});
		bottomPanel.add(solveButton);
		bottomPanel.add(resetButton);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	public void solve() {
		String reply = JOptionPane.showInputDialog("Enter x coordinate of blob cell");
		int x = Integer.parseInt(reply);
		reply = JOptionPane.showInputDialog("Enter y coordinate of blob cell");
		int y = Integer.parseInt(reply);
		Blob aBlob = new Blob(theGrid);
		JOptionPane.showMessageDialog(null, "For blob at ("
				+ x + "," + y + ") "
				+ "\ncount is "
				+ aBlob.countCells(x, y)
				+ "\nReset blob and try again");
	}
}