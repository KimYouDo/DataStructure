import java.util.Stack;
import java.util.EmptyStackException;
import java.util.regex.Pattern;
import java.util.Scanner;

public class InfixToPostfixParens {
	public static class SyntaxErrorException
	extends Exception {
		SyntaxErrorException(String message) {
			super(message);
		}
	}
	private Stack<Character> operatorStack;
	private static final String OPERATORS = "-+*/()";
	private static final Pattern pattern =
			Pattern.compile("\\d+\\.\\d*|\\d+|"
					+ "\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*"
					+ "|[" + OPERATORS + "]");
	private static final int[] PRECEDENCE = {1, 1, 2, 2, -1, -1};
	private StringBuilder postfix;
	String convert(String infix) throws SyntaxErrorException {
		operatorStack = new Stack<Character>();
		postfix = new StringBuilder();
		Scanner scan = new Scanner(infix);
		try {
			String nextToken;
			while ((nextToken = scan.findInLine(pattern)) != null) {
				char firstChar = nextToken.charAt(0);
				if (Character.isJavaIdentifierStart(firstChar)
						|| Character.isDigit(firstChar)) {
					postfix.append(nextToken);
					postfix.append(' ');
				} // Is it an operator?
				else if (isOperator(firstChar)) {
					processOperator(firstChar);
				} else {
					throw new SyntaxErrorException("Unexpected Character Encountered: "
							+ firstChar);
				}
			} // End while.
			while (!operatorStack.empty()) {
				char op = operatorStack.pop();
				if (op == '(') {
					throw new SyntaxErrorException("Unmatched opening parenthesis");
				}
				postfix.append(op);
				postfix.append(' ');
			}
			return postfix.toString();
		} catch (EmptyStackException ex) {
			throw new SyntaxErrorException("Syntax Error: The stack is empty");
		}
	}
	private void processOperator(char op) {
		if (operatorStack.empty() || op == '(') {
			operatorStack.push(op);
		} else {
			char topOp = operatorStack.peek();
			if (precedence(op) > precedence(topOp)) {
				operatorStack.push(op);
			} else {
				while (!operatorStack.empty()&& precedence(op) <= precedence(topOp)) {
					operatorStack.pop();
					if (topOp == '(') {
						break;
					}
					postfix.append(topOp);
					postfix.append(' ');
					if (!operatorStack.empty()) {
						topOp = operatorStack.peek();
					}
				}
				if (op != ')') {
					operatorStack.push(op);
				}
			}
		}
	}
	private boolean isOperator(char ch) {
		return OPERATORS.indexOf(ch) != -1;
	}
	private int precedence(char op) {
		return PRECEDENCE[OPERATORS.indexOf(op)];
	}
}
