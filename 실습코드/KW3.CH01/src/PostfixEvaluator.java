import java.util.Stack;
import java.util.Scanner;
import java.util.EmptyStackException;
public class PostfixEvaluator {
	public static class SyntaxErrorException extends Exception {
		SyntaxErrorException(String message) {
			super(message);
		}
	}
	private static final String OPERATORS = "-+*/";
	private Stack<Double> operandStack;
	private double evalOp(char op) { 
		Double rhs = operandStack.pop(); 
		Double lhs = operandStack.pop(); 
		double result = 0.0;
		switch (op) {
		case '+':
			result = lhs + rhs;
			break;
		case '-':
			result = lhs - rhs;
			break;
		case '/':
			result = lhs / rhs;
			break;
		case '*':
			result = lhs * rhs;
			break;
		}
		return result;
	}
	private boolean isOperator(char ch) {
		return OPERATORS.indexOf(ch) != -1;
	}
	public Double eval(String expression) throws SyntaxErrorException { 
		operandStack = new Stack<Double>();
		Scanner scan = new Scanner(expression);
		try {
			String nextToken;  //\\d*(\\.?\\d*)
			while ((nextToken = scan.findInLine("\\d*(\\.?\\d+)|[-+/\\*]")) != null) {
				if (Character.isDigit(nextToken.charAt(0))) {
					Double value = Double.parseDouble(nextToken); 
					operandStack.push(value);
				} // Is it an operator?
				else if (isOperator(nextToken.charAt(0))) {
					double result = evalOp(nextToken.charAt(0)); 
					operandStack.push(result);
				} 
				else {
					throw new SyntaxErrorException("Invalid character encountered");
				}
			} // End while. int answer = operandStack.pop();
			// Operand stack should be empty.
			// End while.
			Double answer = operandStack.pop();
			if (operandStack.empty()) { 
				return answer;
			} 
			else {
				throw new SyntaxErrorException(
						"Syntax Error: Stack should be empty");
			}
		} catch (EmptyStackException ex) {
			throw new SyntaxErrorException(
					"Syntax Error: The stack is empty");
		}
	}
}

