package Lab4;

public class Point implements Comparable<Point>, Cloneable {

	double x , y ;
	
	public Point() {
		this(0,0);
	}
	
	public Point(double x , double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point && obj != null) {
			if (this.x == ((Point)obj).x && this.y == ((Point)obj).y)
				return true;
			return false;
		}
		return false;
	}
	
	@Override
	public int compareTo(Point p2) {
		if (this.x > p2.x || this.x == p2.x && this.y > p2.y)
			return 1;
		else if (this.x == p2.x && this.y == p2.y)
			return 0;
		return -1;
	}
	
	
	@Override
	public String toString() {
		return "[" + this.x +" , " + this.y + "]";
	}	
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public static void main(String[] args) {
		Point p1 = new Point(2,2);
		Point p2 = new Point(2,3);
		
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		
		System.out.println("p1 equals p2: " + p1.equals(p2));
		System.out.println("p1 compareTo p2: " + p1.compareTo(p2));
	}
}
