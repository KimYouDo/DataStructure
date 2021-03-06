import javax.swing.JOptionPane;


public class TowersOfHanoi {
	public static String showNoves(int n, char startPeg, char destPeg, char tempPeg){
		if(n==1){
			return "Move disk 1 form peg "+startPeg+" to peg "+ destPeg+"\n";
		}else{
			return showNoves(n-1, startPeg, tempPeg, destPeg)+ "Move disk "+n+
					" from peg "+startPeg+" to peg "+ destPeg+"\n"
					+showNoves(n-1, tempPeg, destPeg, startPeg);
		}
	}
	public static void main(String[] args) {
		String nDisks
		= JOptionPane.showInputDialog("Enter number of disks");
		String startPeg
		= JOptionPane.showInputDialog("Enter start peg (L, M, R)");
		String destPeg
		= JOptionPane.showInputDialog("Enter destination peg (L, M, R), "
				+"but not "+startPeg);
		String tempPeg
		= JOptionPane.showInputDialog("Enter temporary peg (L, M, R), "
				+"but not "+startPeg+" or "+destPeg);
		String moves = showNoves(Integer.parseInt(nDisks),
				startPeg.toUpperCase().charAt(0), 
				destPeg.toUpperCase().charAt(0),
				tempPeg.toUpperCase().charAt(0));
		JOptionPane.showMessageDialog(null, moves);
	}
}
