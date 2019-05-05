
/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */
package geometries;

import primitives.Color;
import primitives.Material;

/*
 * Abstract class that are manage radius and are the super for elements that have radius
 */
public abstract class RadialGeometry extends Geometry {

	protected double _radius;
	
	/********** Constructors ***********/
	/**
	 * constructor
	 * @param _radius - radius
	 * @param _material 
	 */
	public RadialGeometry(double _radius,Color _emmission, Material _material) {
		super(_emmission, _material);
		//?????what is with 0??? 
		if(_radius<0) {
			throw new ExceptionInInitializerError("radius can not be a negative number");
		}
		this._radius = _radius;
	}
	
	/**
	 * copy constructor
	 * @param other - element to copy
	 */
	public RadialGeometry(RadialGeometry other) {
		super(other);
		this._radius = other.get_radius();
	}
	
	/************** Getters/Setters *******/
	/**
	 * getter for radius
	 * @return - radius
	 */
	public double get_radius() {
		return _radius;
	}

	/*************** Administration *****************/ 
	@Override
	public String toString() {
		return "radius=" + _radius;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RadialGeometry))
			return false;
		RadialGeometry other = (RadialGeometry) obj;
		if (Double.doubleToLongBits(_radius) != Double.doubleToLongBits(other._radius))
			return false;
		return true;
	}
	

}
