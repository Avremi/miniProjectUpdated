/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package primitives;

/**
 * class to manage a vector
 *
 */
public class Vector {
	private Point3D _head;
	
	/********** Constructors ***********/
	/**
	 * constructor
	 * @param x coordinate
	 * @param y coordinate
	 * @param z coordinate
	 */
    public Vector(double x, double y, double z) {
		_head=  new Point3D(x,y,z);
		if (Point3D.ZERO.equals(_head)) {
			throw new IllegalArgumentException("incorrect initialize a Vector with 0");
		}
    }
    
    /**
     * construct a vector by a point
     * @param point point to became the head
     */
	public Vector(Point3D point) {
		if (Point3D.ZERO.equals(_head)) {
			throw new IllegalArgumentException("incorrect initialize a Vector with 0");
		}
		this._head = new Point3D(point);
	}
	
	 /**
	  * copy constructor
	  * @param other - vector to copy
	  */
	public Vector(Vector other) {
		this._head = new Point3D(other.get_head());
	}
	
	/************** Getters/Setters *******/
	/**
	 * get head of the vector
	 * @return head
	 */
	public Point3D get_head() {
		return _head;
	}
	
	/*************** Administration *****************/
	@Override
	public String toString() {
		return "Vector head=" + _head  ;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Vector)) return false; 

		Vector other = (Vector) obj;
		return _head.equals(other._head);
	}

	/************** Operations ***************/
	/**
	 * Subtract a vector got in parameter to the current vector and return the vector between they  
	 * @param other - a vector to subtract
	 * @return a new vector between the vectors
	 */
	public Vector subtract(Vector other) {
		return new Vector(new Point3D(
				this._head.getX().subtract(other.get_head().getX()), 
				this._head.getY().subtract(other.get_head().getY()),
				this._head.getZ().subtract(other.get_head().getZ())
				));
	}
	
	/**
	 * add a vector got in parameter to the current vector and return the vector between they  
	 * @param other - a vector to add
	 * @return a new vector between the vectors
	 */
	public Vector add(Vector other) {
		return new Vector(new Point3D(
				this._head.getX().add(other.get_head().getX()), 
				this._head.getY().add(other.get_head().getY()),
				this._head.getZ().add(other.get_head().getZ())
				));
	}
	
	/**
	 * Scale the vector with the value in the parameter
	 * @param _scalar the number to scale with
	 * @return a new vector scaled with the value in parameter
	 */
	public Vector scaling(Double _scalar) {
		return new Vector(this._head.getX().get()*_scalar, this._head.getY().get()*_scalar, this._head.getZ().get()*_scalar);
	}
	
	//what about accuracy while product numbers ??? do its correct to return a value from a coordinate ???
	/**
	 * dot product the current vector with "other"
	 * @param other 2td vector
	 * @return the value of the dot product
	 */
	public double dotProduct(Vector other) {
		return  this._head.getX().get()*other.get_head().getX().get()
				+ this._head.getY().get()*other.get_head().getY().get()
				+ this._head.getZ().get()*other.get_head().getZ().get();
	}
	
	/**
	 * cross product the current vector with "other"
	 * @param other 2td vector
	 * @return orthogonal vector
	 */
	public Vector crossProduct(Vector other) {
		return new Vector(this._head.getY().get()*other.get_head().getZ().get()
				-this._head.getZ().get()*other.get_head().getY().get()
				,
				this._head.getZ().get()*other.get_head().getX().get()
				-this._head.getX().get()*other.get_head().getZ().get()
				,
				this._head.getX().get()*other.get_head().getY().get()
				-this._head.getY().get()*other.get_head().getX().get()
				);
	}
	
	/**
	 * calculate the length of the vector
	 * @return the length of the vector
	 */
	public double length() {
		return _head.distance(new Point3D(0,0,0));
	}
	
	/**
	 * normalize the vector
	 * @return the current vector normalized
	 */
	public Vector normalize() {
		double length=this.length();
		this._head=new Point3D(this._head.getX().get()/length,
				this._head.getY().get()/length,
				this._head.getZ().get()/length);
		return this;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
