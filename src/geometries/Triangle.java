/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package geometries;

import java.util.List;
import java.util.Map;

import primitives.*;

/*
 *a class for a triangle shape
 */
public class Triangle extends Plane {

	// is _p1 necessary after the impair that triangle derive from plane???
	private Point3D _p1;
	private Point3D _p2;
	private Point3D _p3;

	/********** Constructors ***********/
	/**
	 * constructor
	 * @param _p1 1td point of the triangle
	 * @param _p2 2td point of the triangle
	 * @param _p3 3td point of the triangle
	 */
	public Triangle(Point3D _p1, Point3D _p2, Point3D _p3,Color _emmission,Material _material ) {
		super(_p1, _p2, _p3,_emmission,_material );
		this._p1 = new Point3D(_p1);
		this._p2 = new Point3D(_p2);
		this._p3 = new Point3D(_p3);
	}

	/**
	 * copy constructor
	 * @param other - triangle to copy
	 */
	public Triangle(Triangle other) {
		super(other);
		this._p1 = new Point3D(get_p1());
		this._p2 = new Point3D(get_p2());
		this._p3 = new Point3D(get_p3());
	}

	/************** Getters/Setters *******/
	/**
	 * getter for 1td point of the triangle
	 * @return
	 */
	public Point3D get_p1() {
		return _p1;
	}

	/**
	 * getter for 2td point of the triangle
	 * @return
	 */
	public Point3D get_p2() {
		return _p2;
	}

	/**
	 * getter for 3td point of the triangle
	 * @return
	 */
	public Point3D get_p3() {
		return _p3;
	}

	/*************** Administration *****************/
	@Override
	public String toString() {
		return "Triangle: p1=" + _p1 + ", p2=" + _p2 + ", p3=" + _p3;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Triangle))
			return false;
		Triangle other = (Triangle) obj;
		if (_p1 == null) {
			if (other._p1 != null)
				return false;
		} else if (!_p1.equals(other._p1))
			return false;
		if (_p2 == null) {
			if (other._p2 != null)
				return false;
		} else if (!_p2.equals(other._p2))
			return false;
		if (_p3 == null) {
			if (other._p3 != null)
				return false;
		} else if (!_p3.equals(other._p3))
			return false;
		return true;
	}

	/************** Operations ***************/

	@Override
	public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
		Map<Geometry, List<Point3D>> pointsList = super.findIntersections(ray);
		if (pointsList.isEmpty())
			return pointsList;

		// ??is i=the index of array list begin with 0 ??F
		Point3D p0 = ray.get_p00();

		// v1,v2,v3 are vectors from ray begin to each kodkod
		Vector v1, v2, v3;
		try {
			v1 = _p1.subtract(p0);
			v2 = _p2.subtract(p0);
			v3 = _p3.subtract(p0);
		} catch (Exception e) {
			pointsList.clear();
			return pointsList;
		}
		
		// n1,n2,n3 are normals for each one of three vector pairs according to their
		// order
		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();
		
		
		Vector pToP0=pointsList.get(this).get(0).subtract(p0);
		// if n1-n3 are all <0 or >0 its inside the triangle
		double dir1 = pToP0.dotProduct(n1);
		double dir2 = pToP0.dotProduct(n2);
		double dir3 = pToP0.dotProduct(n3);
		if (!(dir1 > 0 && dir2 > 0 && dir3 > 0 || dir1 < 0 && dir2 < 0 && dir3 < 0))
			pointsList.clear();

		return pointsList;
	}

}
