package socket;

import java.io.Serializable;
import java.math.*;

public class Point2D  implements Serializable{
	private float x;
	private float y;
	
	public Point2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public String toString() {
		return "x=[" + x + "], y=[" + y+"],";
	}
	
	public static double calculerDistance(Point2D p1, Point2D p2) {
		
		return (Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y)));
	}

	public static void main(String[] args) {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(3,3);
		System.out.println(calculerDistance(p1,p2));

	}

}
