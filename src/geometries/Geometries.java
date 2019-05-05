/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package geometries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import primitives.*;

/*
 * class to hold a collection of geometries shapes  
 */
public class Geometries extends Geometry {
	private List<Geometry> _geometries;

	// ***************** Constructors ********************** //
	public Geometries() {
		_geometries=new ArrayList<Geometry>();
	}
	
	/**
	 * constructor
	 * @param list 
	 */
	public Geometries(List<Geometry> list) {
		_geometries=new ArrayList<Geometry>();
		_geometries.addAll(list);
	}
	
	/**
	 * copy constructor
	 * @param other an other elemenet
	 */
	public  Geometries( Geometries other) {
		_geometries=new ArrayList<Geometry>();
		_geometries.addAll(other._geometries);
	}

	// ***************** operations ********************** //
	@Override
	public Vector getNormal(Point3D point) {
		return null;
	}

	@Override
	public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
		Map<Geometry, List<Point3D>> pointsList = new HashMap<Geometry, List<Point3D>>();
		for (Geometry geometry : _geometries) {
			pointsList.putAll(geometry.findIntersections(ray));
		}
		return pointsList;
	}

	/**
	 * add Geometry shapes to the list of the class
	 * @param geometry the Geometry to add
	 */
	public void add(Geometry geometry) {
		_geometries.add(geometry);
	}
	
	/**
	 * get the iterator of the list in the class
	 * @return the iterator of _geometries
	 */
	public Iterator<Geometry> getIterator() {
		return _geometries.iterator();
	}
}
