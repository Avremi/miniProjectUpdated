/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package geometries;

import java.util.List;
import java.util.Map;

import primitives.Color;
import primitives.Coordinate;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/*
 * class to manage rectangle
 */
public class Rectangle extends Plane {
	Point3D _downRightP;

	Vector _left;
	Vector _up;

	double _width;
	double _height;

	/**
	 * 
	 * @param downRightP a point on the rectangle
	 * @param left 1th vector
	 * @param up 2nd vector
	 * @param width 3th vector
	 * @param height 4th vector
	 * @param color the color of the geometry
	 * @param material the material of the geometry
	 */
	public Rectangle(Point3D downRightP, Vector left, Vector up, double width, double height, Color color,
			Material material) {
		super(downRightP, downRightP.addVector(up), downRightP.addVector(left), color, material);
		if (left.dotProduct(up) != 0)
			throw new IllegalArgumentException("vectors should be vertical");
		if (Coordinate.ZERO.equals(width) || Coordinate.ZERO.equals(height))
			throw new IllegalArgumentException("rectangle must have some surface");
		_left = left.normalize();
		_up = up.normalize();
		_width = width;
		_height = height;
		_downRightP = downRightP;
	}

	/**
	 * constructor
	 * @param drP first point
	 * @param dlP 2nd point
	 * @param ulP 2th point
	 * @param urP 4th point
	 * @param color the color of the geometry
	 * @param material the material of the geometry
	 */
	public Rectangle(Point3D drP, Point3D dlP, Point3D ulP, Point3D urP, Color color, Material material) {
		super(drP, urP, dlP, color, material);
		if (urP.subtract(ulP).dotProduct(urP.subtract(drP)) != 0
				|| dlP.subtract(drP).dotProduct(dlP.subtract(ulP)) != 0)
			throw new IllegalArgumentException("vectors should be vertical");
		
		_left = dlP.subtract(drP);
		_up = urP.subtract(drP);
		_width = _left.length();
		_height = _up.length();
		_downRightP = drP;
		
		_left = _left.normalize();
		_up = _up.normalize();
	}

	/**
	 * getter for _downRightP
	 * @return _downRightP
	 */
	public Point3D downRightP() {
		return _downRightP;
	}

	/**
	 * getter for _left
	 * @return _left
	 */
	public Vector left() {
		return _left;
	}

	/**
	 * getter for_up
	 * @return _up
	 */
	public Vector up() {
		return _up;
	}

	/**
	 * getter for _width
	 * @return _width
	 */
	public double get_width() {
		return _width;
	}

	/**
	 * getter for _height
	 * @return _height
	 */
	public double height() {
		return _height;
	}

	@Override
	public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
		Map<Geometry, List<Point3D>> intersections = super.findIntersections(ray);
		if (intersections.isEmpty())
			return intersections;

		Point3D intersectionPoint = intersections.get(this).get(0);

		Point3D p2 = _downRightP.addVector(_left.scaling(_width));
		Point3D p3 = p2.addVector(_up.scaling(_height));
		Point3D p4 = p3.addVector(_left.scaling(-_width));

		Vector v3 = _left.scaling(-1.0);
		Vector v4 = null;
		Vector v5 = null;
		Vector v6 = _up.scaling(-1.0);
		Vector v7 = null;
		Vector v8 = null;
		try {
			v4 = intersectionPoint.subtract(_downRightP).normalize();
			v5 = intersectionPoint.subtract(p3).normalize();
			v7 = intersectionPoint.subtract(p2).normalize();
			v8 = intersectionPoint.subtract(p4).normalize();
		} catch (IllegalArgumentException e) {
			if (e.getMessage().matches("zero vector is illegal"))
				return intersections;
			else System.out.println("something wrong happaned");
		}
		
		double n1 = _left.dotProduct(v4);
		double n2 = v3.dotProduct(v5);
		double n3 = _up.dotProduct(v7);
		double n4 = v6.dotProduct(v8); 

		if (!(n1 >= 0 && n2 >= 0 && n3 >= 0 && n4 >= 0))
			intersections.clear();
		return intersections;
	}

	@Override
	public Vector getNormal(Point3D other) {
		return get_normal();
	}

}
