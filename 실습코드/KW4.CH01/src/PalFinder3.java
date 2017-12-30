import java.util.Stack;


public class PalFinder3 {
	private String inputString;
	private DoubleStack3<Character> charStack0 = new DoubleStack3<Character>();
	
	public PalFinder3(String str) {
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

