import java.util.Scanner;

public class CH01 {
	public static void main(String args[]) {
		Shape myShape;
		double perimeter;
		double area = 0;
		myShape = getShape(); // Ask for figure type
		myShape.readShapeData(); // Read the shape data
		perimeter = myShape.computePerimeter();  // Compute perimeter area = myShape.computeArea();  // Compute the area
		area = myShape.computeArea();
		System.out.println(myShape);
		displayResult(area, perimeter); // Display the result
		System.exit(0); // Exit the program
	}


	public static Shape getShape() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter C for circle");
		System.out.println("Enter R for Rectangle");
		System.out.println("Enter T for Right Triangle");
		String figType = in.next();
		if (figType.equalsIgnoreCase("c")) {
			return new Circle();
		} else if (figType.equalsIgnoreCase("r")) {
			return new Rectangle();
		} else if (figType.equalsIgnoreCase("t")) {
			return new Rt();
		} else {
			return null;
		}
	}
	private static void displayResult(double area, double perim) {
		System.out.printf("The area is %.2f%nThe perimeter is %.2f%n", area, perim);
	}
}