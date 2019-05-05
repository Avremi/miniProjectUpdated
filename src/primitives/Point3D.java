/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package primitives;

/**
 * Class for 3D points  in out 3D graphics model
 */
public class Point3D extends Point2D {
	
	public static Point3D ZERO=new Point3D(0,0,0);
	
	private Coordinate _z;
	
	/********** Constructors ***********/
	/*
	 * constructor
	 * @param x - value for x
	 * @param y - value for y
	 * @param z - value for z
	 */
	public Point3D(double x, double y, double z) {
		super(x, y);
		_z = new Coordinate(z);
	}

	/**
	 * @param other - a 3D point to copy
	 */
	public Point3D(Point3D other) {
		super(other);
		_z = new Coordinate(other._z);
	}
	
	/**
	 * @param x - Coordinate value for x
	 * @param y - Coordinate value for y
	 * @param z - Coordinate value for z
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		super(x, y);
		_z = new Coordinate(z);
	}

	/************** Getters/Setters *******/
	/**
	 * @return coordinate z
	 */
	public Coordinate getZ() {
		return _z;
	}
	
	/*************** Administration *****************/
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point3D))
			return false;
		Point3D oth = (Point3D)obj;
		return super.equals(oth) && _z.equals(oth._z);
	}

	@Override
	public String toString() {
		return "(" + getX() + "," + getY() + "," + _z + ")";
	}
	
	/************** Operations ***************/
	/**
	 * Subtract other point from this one resulting in vector from other point to this point
	 * @param other - 2nd point 
	 * @return resulting Vector
	 */
	public Vector subtract(Point3D other) {
		return new Vector(new Point3D(
				_x.subtract(other._x), 
				_y.subtract(other._y),
				_z.subtract(other._z)
				));
	}
	
	/**
	 * add vector to the current point to calculate the arrival point
	 * @param vector - the vector to add
	 * @return arrival point
	 */
	public Point3D addVector(Vector vector) {
		return new Point3D(
				this.getX().add(vector.get_head().getX()), 
				this.getY().add(vector.get_head().getY()),
				this.getZ().add(vector.get_head().getZ())
				);
	}
	
	/**
	 * calculate distance between 2 points
	 * @param other - 2td point
	 * @return distance
	 */
	public double distance(Point3D other) {
		double dx = other._x.get() - _x.get();
		double dy = other._y.get()-_y.get();
		double dz = other._z.get()-_z.get();
		
		return Math.sqrt(dx*dx+dy*dy+dz*dz);
	}
	
}
