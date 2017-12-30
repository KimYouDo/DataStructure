import java.util.Stack;
public class PalFinder {
	private String inputString;
	private Stack<Character> charStack0 = new Stack<Character>();
	private Stack<Character> charStack1 = new Stack<Character>();
	
	public PalFinder(String str) {
		inputString = str;
		fillStack();
	}
	void fillStack() {
		int su = inputString.length()/2;
		if(inputString.length()%2==0){
			for (int i = 0; i < su; i++) {
				charStack0.push(inputString.charAt(i));
			}

			for (int i = inputString.length()-1; i >=su ; i--) {
				charStack1.push(inputString.charAt(i));
			}
		}
		
		else{
			for (int i = 0; i < su; i++) {
				charStack0.push(inputString.charAt(i));
			}

			for (int i = inputString.length()-1; i >su ; i--) {
				charStack1.push(inputString.charAt(i));
			}
		}
	}
	
	boolean isPalindrome() {
		return charStack0.toString().equalsIgnoreCase(charStack1.toString());
	}
}