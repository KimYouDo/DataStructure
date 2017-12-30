import java.util.Stack;


public class PalFinder2 {
	private String inputString;
	private DoubleStack<Character> charStack0 = new DoubleStack<Character>();
	
	
	public PalFinder2(String str) {
		inputString = str;
		charStack0 = new DoubleStack<Character>(inputString.length()/2);
		fillStack();
	}
	void fillStack() {
		int su = inputString.length()/2;
		if(inputString.length()%2==0){
			for (int i = 0; i < su; i++) {
				charStack0.push(1,inputString.charAt(i));
				charStack0.push(2,inputString.charAt(i+su));
			}
		}
		
		else{
			for (int i = 0; i < su; i++) {
				charStack0.push(1,inputString.charAt(i));
				charStack0.push(2,inputString.charAt(i+1+su));
			}
		}
	}
	public void print() {
		System.out.println(charStack0.toString1()+"---"+charStack0.toString2());
	}
	
	boolean isPalindrome() {
		return charStack0.eq();
	}
}
