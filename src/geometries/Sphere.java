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
 * a class to represent a sphere
 */
public class Sphere extends RadialGeometry {
	private Point3D _center;

	// ***************** Constructors ********************** //
	/**
	 * constructor
	 * @param _radius - radius
	 * @param _center - center point
	 * @param _emmission - emmission
	 * @param _material - material
	 */
	public Sphere(double _radius, Point3D _center,Color _emmission, Material _material) {
		super(_radius, _emmission, _material);
		this._center = new Point3D(_center);
	}

	/**
	 * copy constructor
	 * @param other - an other element
	 */
	public Sphere(Sphere other) {
		super(other);
		this._center = new Point3D(other.get_center());
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * a getter for the center point
	 * @return center point
	 */
	public Point3D get_center() {
		return _center;
	}

	/*************** Administration *****************/
	@Override
	public String toString() {
		return "Sphere: center=" + _center;
	}

	/************** Operations ***************/
	@Override
	public Vector getNormal(Point3D point) {
		// A vector that comes out of the head and crosses the ball at the given point
		return point.subtract(_center).normalize();
	}

	@Override
	public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
		List<Point3D> pointsList = new ArrayList<Point3D>();
		Map<Geometry, List<Point3D>> map = new HashMap<Geometry, List<Point3D>>();

		Point3D p0 = ray.get_p00();
		Vector v = ray.get_direction();

		Vector u = _center.subtract(p0);
		double tm =new Coordinate(v.dotProduct(u)).get();
		double lengthU = u.length();
		double d = Math.sqrt(lengthU * lengthU - tm * tm);
		if (d > _radius)
			return map;

		double th = new Coordinate(Math.sqrt(_radius * _radius - d * d)).get();
		if (Coordinate.ZERO.equals(th)) {
			if (new Coordinate( tm).get() > 0)
				pointsList.add(p0.addVector(v.scaling(tm)));
		} else {
			double t1 = tm - th;
			double t2 = tm + th;
			if (new Coordinate(t1).get() > 0)
				pointsList.add(p0.addVector(v.scaling(t1)));
			if (new Coordinate(t2).get() > 0)
				pointsList.add(p0.addVector(v.scaling(t2)));
			if(pointsList.isEmpty())
				return map;
		}
		map.put(this, pointsList);
		return map;
	}

}
