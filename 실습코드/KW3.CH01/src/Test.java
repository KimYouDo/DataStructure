import java.util.Scanner;

import javax.swing.JOptionPane;


public class Test{
	public static void main(String[] args) {
		InfixToPostfixParens evaluator = new InfixToPostfixParens();
		PostfixEvaluator ret = new PostfixEvaluator();

		Scanner in = new Scanner(System.in);
		String result = null;
		double r =0.0;
		String str = JOptionPane.showInputDialog("숫자만 입력하세요"); 
		
		try {
			r =ret.eval(str);
		} catch (PostfixEvaluator.SyntaxErrorException ex) {
			JOptionPane.showMessageDialog(null,"Syntax error " + ex.getMessage());
		}
		JOptionPane.showMessageDialog(null,"Value is "+r );

	}
}
