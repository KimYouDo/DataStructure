import java.awt.*;
public class Blob implements GridColors {
	private TwoDimGrid grid;
	public Blob(TwoDimGrid grid){
		this.grid=grid;
	}
	public int countCells(int x, int y){
		int result;

		if(x<0||x>=grid.getNCols()||y<0||y>=grid.getNRows())
			return 0;
		else if(!grid.getColor(x, y).equals(NON_BACKGROUND))
			return 0;
		else{
			grid.recolor(x,y, TEMPORARY);
			result =1
					+countCells(x-1,y-1)+countCells(x,y+1)
					+countCells(x+1,y+1)+countCells(x-1,y)
					+countCells(x,y-1)+countCells(x+1,y-1);
			return result;
		}
	}
	public void restore() {
		grid.recolor(TEMPORARY, ABNORMAL);
	}
}
