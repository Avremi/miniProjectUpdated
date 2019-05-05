/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package geometries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import primitives.*;

/*
 * class to manage a plane
 */
public class Plane extends Geometry {
	protected Point3D _p;
	protected Vector _normal;

	/********** Constructors ***********/
	/**
	 * constructor
	 * @param _p - plane point
	 * @param _normal - normal vector
	 * @param material 
	 */
	public Plane(Point3D _p, Vector _normal, Color _emmission, Material material) {
		super(_emmission, material);
		this._p = new Point3D(_p);
		this._normal = new Vector(_normal).normalize();
	}

	/**
	 * constructor
	 * @param _p1 - a point 
	 * @param _p2 - a point
	 * @param _p3 - a point
	 * @param _emmission - emmission
	 * @param material - material
	 */
	public Plane(Point3D _p1, Point3D _p2, Point3D _p3,Color _emmission, Material material) {
		super(_emmission, material);
		try {
			this._p = new Point3D(_p1);
			Vector _tempVectorUp, _tempVectorTo;
			// if p1=p2 exception will be thrown from vector generating bcos of zero vector
			_tempVectorUp = _p2.subtract(_p1);
			// if p1=p3 exception will be thrown from vector generating bcos of zero vector
			_tempVectorTo = _p3.subtract(_p1);
			// if p2=p3 or the points are on the same line zero vector will be generated
			// and then there will be exception
			this._normal = _tempVectorUp.crossProduct(_tempVectorTo).normalize();
		} catch (Exception e) {
			throw new IllegalArgumentException("error. initilize a Plane with the same point or in one line", e);
		}
	}

	/**
	 * copy constructor
	 * @param other - plane to copy
	 */
	public Plane(Plane other) {
		super(other);
		this._p = new Point3D(other.get_p());
		this._normal = new Vector(other.get_normal());
	}

	/************** Getters/Setters *******/
	/**
	 * getter for the plane point
	 * @return - plane point
	 */
	public Point3D get_p() {
		return _p;
	}

	/**
	 * getter for normal
	 * @return - normal
	 */
	public Vector get_normal() {
		return _normal;
	}

	/*************** Administration *****************/
	@Override
	public String toString() {
		return "Plane: p=" + _p + ", normal=" + _normal;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Plane))
			return false;
		Plane other = (Plane) obj;
		if (_normal == null) {
			if (other._normal != null)
				return false;
		} else if (!_normal.equals(other._normal))
			return false;
		if (_p == null) {
			if (other._p != null)
				return false;
		} else if (!_p.equals(other._p))
			return false;
		return true;
	}

	/************** Operations ***************/
	@Override
	public Vector getNormal(Point3D point) {
		return _normal;
	}

	
	@Override
	public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
		List<Point3D> pointsList = new ArrayList<Point3D>();
		
		Map<Geometry, List<Point3D>> map=new HashMap<Geometry,List<Point3D>>(); //Map<Geometry, List<Point3D>>();
		Point3D p0 = ray.get_p00();
		Vector v = ray.get_direction();
		// if they ray and plane normal are orthogonal - ray is parallel to the plane
		// and there are no intersections
		if (Coordinate.ZERO.equals(_normal.dotProduct(v)))
			return map;

		// t is the scalar to scaling the vector of the ray
		Vector u = _p.subtract(p0);
		double t = _normal.dotProduct(_p.subtract(ray.get_p00())) / _normal.dotProduct(v);
		// if it is in the ray - add an intersection point to the list
		if (t > 0) {
			pointsList.add(p0.addVector(v.scaling(t)));
			map.put(this, pointsList);
		} 
		return map;
		
	}

}
