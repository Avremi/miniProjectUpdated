/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package geometries;

import java.util.List;
import java.util.Map;

import primitives.*;

/*
 * a class for a tube
 */
public class Tube extends RadialGeometry {
	private Ray _axisRay;

	/********** Constructors ***********/
	/**
	 * constructor
	 * @param _radius - the radius
	 * @param _axisRay - the ray
	 * @param _emmission - emission
	 * @param _material - the material
	 */
	public Tube(double _radius, Ray _axisRay,Color _emmission, Material _material) {
		super(_radius, _emmission, _material);
		this._axisRay = new Ray(_axisRay);
	}
	
	/**
	 * copy  constructor
	 * @param other - an other element
	 */
	public Tube(Tube other) {
		super(other);
		this._axisRay = new Ray(other.get_axisRay());
	}

	/************** Getters/Setters *******/
	/**
	 * getter for _axisRay
	 * @return _axisRay
	 */
	public Ray get_axisRay() {
		return _axisRay;
	}

	/*************** Administration *****************/ 
	@Override
	public String toString() {
		return "Tube: axis Ray=" + _axisRay;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Tube))
			return false;
		Tube other = (Tube) obj;
		if (_axisRay == null) {
			if (other._axisRay != null)
				return false;
		} else if (!_axisRay.equals(other._axisRay))
			return false;
		return true;
	}

	/************** Operations ***************/
	@Override
	public Vector getNormal(Point3D point) {
	   Vector a= new Vector ( point.getX().get() - _axisRay.get_p00().getX().get(),point.getY().get() - _axisRay.get_p00().getY().get(),
                          point.getZ().get() - _axisRay.get_p00().getZ().get());
    Vector c = new Vector( _axisRay.get_direction().scaling(a.dotProduct(_axisRay.get_direction()) /
                          _axisRay.get_direction().length()*_axisRay.get_direction().length() ) );


        return a.subtract(c);
	}

	@Override
	public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
