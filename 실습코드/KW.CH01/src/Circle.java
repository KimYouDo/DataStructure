import java.util.Scanner;

public class Circle extends Shape {
	private double radius = 0;
	public Circle() {
		super("Circle");
	}
	public Circle(double radius) {
		super("Circle");	
		this.radius = radius;
	}
	public double getRadius() {
		return radius;
	}

	@Override
	public double computeArea() {
		return radius*radius*3.14;
	}
	public double computePerimeter() {
		return radius*2*3.14;
	}
	public void readShapeData() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the radius of the Circle");
		radius = in.nextDouble();	
	}
	public String toString() {return super.toString() + ": radius is " + radius;
	}
}