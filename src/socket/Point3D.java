package socket;

public class Point3D extends Point2D{
	private float z;

	public Point3D(float x, float y, float z) {
		super(x, y);
		this.z = z;
	}

	public float getZ() {
		return z;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
	@Override
	public String toString() {
		return super.toString()+"z=[" + z + "], ";
	}
	 
	public static double calculerDistance(Point3D p1, Point3D p2) {
		return (Math.sqrt((p1.getX()-p2.getX())*(p1.getX()-p2.getX())+(p1.getY()-p2.getY())*(p1.getY()-p2.getY())+(p1.z-p2.z)*(p1.z-p2.z)));

	}
	
	public static void main(String[] args) {
		Point3D p1 = new Point3D(1,1,1);
		Point3D p2 = new Point3D(3,3,3);
		System.out.printf(p1.toString());
		System.out.println(calculerDistance(p1,p2));
	}

}
