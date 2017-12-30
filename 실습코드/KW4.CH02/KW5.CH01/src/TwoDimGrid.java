import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
public class TwoDimGrid extends JPanel implements GridColors {
	private static final int PREFERED_BUTTON_SIZE = 60;
	private static final int DEFAULT_COLS = 20;
	private static final int DEFAULT_ROWS = 20;
	private JButton[][] theGrid;
	private int nRows;
	private int nCols;
	public TwoDimGrid(int nRows, int nCols) {
		this.nRows = nRows;
		this.nCols = nCols;
		setPreferredSize(new Dimension(nCols * PREFERED_BUTTON_SIZE, nRows * PREFERED_BUTTON_SIZE));
		setLayout(new GridLayout(nRows, nCols));
		theGrid = new JButton[nCols][];
		for (int i = 0; i != nCols; ++i) {
			theGrid[i] = new JButton[nRows];
			for (int j = 0; j != nRows; ++j) {
				theGrid[i][j] = new JButton(i + ", " + j);
				theGrid[i][j].setBackground(BACKGROUND);
				theGrid[i][j].addActionListener(new ToggleColor(theGrid[i][j]));
			}
		}
		for (int j = 0; j != nRows; ++j) {
			for (int i = 0; i != nCols; ++i) {
				add(theGrid[i][j]);
			}
		}
	}
	//Accessors and Mutators
	public int getNCols() {
		return nCols;
	}
	public int getNRows() {
		return nRows;
	}
	public Color getColor(int x, int y) {
		return theGrid[x][y].getBackground();
	}
	public void recolor(int x, int y, Color newColor) {
		theGrid[x][y].setBackground(newColor);
		repaint();
	}
	public void recolor(char[][] bitMap, Color aColor) {
		for (int i = 0; i != bitMap.length; ++i) {
			for (int j = 0; j != bitMap[i].length; ++j) {
				if (bitMap[i][j] == '1') {
					theGrid[j][i].setBackground(aColor);
				}
			}
		}
	}
	public void recolor(Color tempColor, Color newColor) {
		for (int i = 0; i != getNCols(); ++i) {
			for (int j = 0; j != getNRows(); ++j) {
				if (theGrid[i][j].getBackground().equals(tempColor)) {
					theGrid[i][j].setBackground(newColor);
				}
			}
		}
		repaint();
	}
	//Inner class
	/** ActionListener class to toggle color when clicked */
	private class ToggleColor implements ActionListener {
		private JButton me;
		public ToggleColor(JButton theButton) {
			me = theButton;
		}
		public void actionPerformed(ActionEvent e) {
			if (me.getBackground().equals(BACKGROUND)) {
				me.setBackground(NON_BACKGROUND);
			} else {
				me.setBackground(BACKGROUND);
			}
		}
	}
}
