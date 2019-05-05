/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package geometries;

import java.util.List;
import java.util.Map;

import primitives.*;

/*
 * Abstract class to hold shapes
 */
public abstract class Geometry {
	
	protected Color _emmission;
	protected Material _material;

	// ***************** Constructors ********************** //
	public Geometry() {
		// the empty constructor is for geometries that don't use the filed _emmission
	}

	/**
	 * constructor
	 * @param emmission - the emmission
	 * @param material - the material
	 */
	public Geometry(Color emmission, Material material) {
		if (emmission != null)// temp line !!!!!!!!!!!!!!
			_emmission = new Color(emmission);
		_material=material;
		
	}

	/**
	 * copy constructor
	 * @param geometry an other element to copy
	 */
	public Geometry(Geometry geometry) {
		if (geometry._emmission != null)// temp line!!!!!!!!!!!1
			_emmission = new Color(geometry.get_emmission());
		_material=geometry._material;
	}

	// ***************** getters\setters ********************** //
	/**
	 * getter for emmission
	 * @return the _emmission
	 */
	public Color get_emmission() {
		return _emmission;
	}

	/**
	 * getter for emmission
	 * @return the _material
	 */
	public Material get_material() {
		return _material;
	}

	// ***************** operations ********************** //
	/**
	 * get the normal of the shape at the point of the parameter
	 * @param point the point to get the normal here
	 * @return the vector normal
	 */
	public abstract Vector getNormal(Point3D point);

	/**
	 * get intersection points in a map of the geometry as a key 
	 * and the intersection point as a list
	 * @param ray the ray that the intersected
	 * @return a map of the geometry as a key and the intersection point as a list
	 */
	public abstract Map<Geometry, List<Point3D>> findIntersections(Ray ray);

}
