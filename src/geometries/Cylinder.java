/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package geometries;

import primitives.*;

/*
 * a class to hold a cylinder
 */
public class Cylinder extends Tube {
	private double _heghit;

	/********** Constructors  ***********/
	/**
	 * constructor
	 * @param _radius - the radius
	 * @param _axisRay - the ray
	 * @param _height - the height
	 * @param _emmission - emission
	 * @param _material - the material
	 */
	public Cylinder(double _radius, Ray _axisRay, double _height,Color _emmission, Material _material) {
		super(_radius, _axisRay, _emmission, _material);
		this._heghit = _height;
	}

	/**
	 * copy constructor
	 * @param other - an other element
	 */
	public Cylinder(Cylinder other) { 
		super(other);
		this._heghit=other.get_height();
	}

	/************** Getters/Setters *******/
	/**
	 * getter for height
	 * @return height
	 */
	public double get_height() {
		return _heghit;
	}

	/*************** Administration *****************/ 
	@Override
	public String toString() {
		return "Cylinder: heghit=" + _heghit;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Cylinder))
			return false;
		Cylinder other = (Cylinder) obj;
		if (Double.doubleToLongBits(_heghit) != Double.doubleToLongBits(other._heghit))
			return false;
		return true;
	}

}
