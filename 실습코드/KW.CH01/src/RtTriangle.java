import java.util.Scanner;

public class RtTriangle extends Shape {
	private double base = 0;
	private double height = 0;
	public RtTriange() {
		super("RtTriangle");
	}
	public RtTriange(double base, double height) {
		super("RtTriangle");
		this.base = base;
		this.height = height;}
	public double getBase() {
		return base;
	}
	public double getHeight() {
		return height;
	}

	@Override
	public double computeArea() {
		return (height * base)/2;
	}
	public double computePerimeter() {
		return base+((Math.sqrt((base/2)+height))*2);
	}
	public void readShapeData() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the base of the RtTriangle");
		base = in.nextDouble();
		System.out.println("Enter the height of the RtTriangle");
		height = in.nextDouble();
	}
	public String toString() {
		return super.toString() + ": base is " + base
				+ ", height is " + height;
	}
}