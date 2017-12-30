import java.util.Scanner;

import javax.swing.JOptionPane;


public class TestInfixToPostfix{
	public static void main(String[] args) {
		InfixToPostfixParens evaluator = new InfixToPostfixParens();
		PostfixEvaluator ret = new PostfixEvaluator();

		Scanner in = new Scanner(System.in);
		String result = null;
		double r =0.0;
		String str = JOptionPane.showInputDialog("숫자만 입력하세요"); 

		try {
			result = evaluator.convert(str);
		} catch (InfixToPostfixParens.SyntaxErrorException ex) {
			JOptionPane.showMessageDialog(null,"Syntax error " + ex.getMessage());
		}
		try {
			r =ret.eval(result);
		} catch (PostfixEvaluator.SyntaxErrorException ex) {
			JOptionPane.showMessageDialog(null,"Syntax error " + ex.getMessage());
		}
		JOptionPane.showMessageDialog(null,"Value is " + result + " = "+r );

	}
}