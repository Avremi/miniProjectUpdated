/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.junit.Test;

import elements.Camera;
import geometries.Geometry;
import geometries.Plane;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class PlaneTests {

	//Plane plane1 = new Plane(new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(1, 1, 0),null);
	Plane plane2 = new Plane(new Point3D(0, 0, 0), new Vector(0, 1, 0), null, null);

	//Vector normal1 = new Vector(0, 0, -1);
	Vector normal2 = new Vector(0, 1, 0);

	private List<Point3D> findIntersctionPlane(Plane p, Camera cam) {
		List<Point3D> list = new ArrayList<>();
		for (int i = 1; i < 4; i++) 
			for (int j = 1; j < 4; j++) {
				Ray r = cam.constructRayThroughPixel(3, 3, i, j, 2, 9, 9);
				Map<Geometry, List<Point3D>> map = p.findIntersections(r);
				if (!map.isEmpty())
					list.addAll(map.get(p));
		}
		return list;
	}
	@Test
	public void testConstructor() {

		try {
			// 2 same points
			new Plane(new Point3D(1, 1, 1), new Point3D(1, 1, 1), new Point3D(1, 2, 3),null,null);
			fail("expected Exeption");
		} catch (Exception e) {
		}

		try {
			// all 3 points are on the same line
			new Plane(new Point3D(1, 1, 1), new Point3D(2, 2, 2), new Point3D(3, 3, 3),null,null);
			fail("expected Exeption");
		} catch (Exception e) {
		}

		try {
			new Plane(new Point3D(1, 1, 1), new Point3D(2, 1, 3), new Point3D(1, 2, 3),null,null);
			new Plane(new Point3D(1, 1, 1), new Vector(1, 2, 3), null, null);
		} catch (Exception e) {
			fail("not expected Exeption");
		}
	}

	
	@Test
	public void testGetNormal() {
		try {
			
			// assertEquals(normal1, plane1.getNormal(new Point3D(2,2,0)));
			// assertEquals(normal1, plane1.getNormal(new Point3D(1,0,0)));
			assertEquals(normal2, plane2.getNormal(new Point3D(1, 0, 1)));
			assertEquals(normal2, plane2.getNormal(new Point3D(0, 0, 0)));
		} catch (Exception e) {
			fail("not expected Exeption");
		}
	}

	@Test
	void testFindIntersction() {
		Camera camera = new Camera(Point3D.ZERO, new Vector(0,-1, 0), new Vector(0, 0, -1));
		
		//Check when the plane is perpendicular
		Plane plane1 = new Plane(new Point3D(Coordinate.ZERO, Coordinate.ZERO, new Coordinate(-50)),
				new Vector(0, 0, -1), null, null);
		List<Point3D> intersctionList1 = findIntersctionPlane(plane1, camera);
		if(intersctionList1!= null)
			assertEquals("plane is perpendicular", 9, intersctionList1.size());
		else
			fail("null list");
		
		//0 
		Plane plane2 = new Plane(new Point3D(Coordinate.ZERO, Coordinate.ZERO, new Coordinate(50)),
				new Vector(0, 0, -1), null, null);
		List<Point3D> intersctionList2 = findIntersctionPlane(plane2, camera);
		if(intersctionList2!= null)
			assertEquals("plane before P0", 0, intersctionList2.size());
		else
			fail("null list");
		
		//plane is horizontal and vectors or contained in or not touching it
		Plane plane3 = new Plane(new Point3D(Coordinate.ZERO, Coordinate.ZERO, new Coordinate(-50)),
				new Vector(0, 1, 0), null, null);
		List<Point3D> intersctionList3 = findIntersctionPlane(plane3, camera);
		if(intersctionList3!= null)
			assertEquals("plane horizontal", 0, intersctionList3.size());
		else
			fail("null list");
		
		//plane is horizontal but not in the middle
		Plane plane4 = new Plane(new Point3D(Coordinate.ZERO,new Coordinate(100),new Coordinate(-50)),new Vector(0,1,0), null, null);
		List<Point3D> intersctionList4 = findIntersctionPlane(plane4, camera);
		if(intersctionList4!= null)
			assertEquals("plane horizontal up", 3, intersctionList4.size());
		else
			fail("null list");
		
		//The plane is diagonal 45 degrees
		//The ray in the pixel (2,3) is also at 45 degrees and does not harm it.
		Plane plane5 = new Plane(new Point3D(Coordinate.ZERO, Coordinate.ZERO, new Coordinate(-10)), new Vector(0,-10,-10), null, null);
		List<Point3D> intersctionList5 = findIntersctionPlane(plane5, camera);
		if(intersctionList5!= null)
			assertEquals("plane parallel to one ray", 6, intersctionList5.size());
		else
			fail("null list");

		//P0=P
		Plane plane6 = new Plane(new Point3D(Coordinate.ZERO, Coordinate.ZERO, Coordinate.ZERO),
				new Vector(0, 0, -1), null, null);
		List<Point3D> intersctionList6 = findIntersctionPlane(plane6, camera);
		if(intersctionList6!= null)
			assertEquals("plane before P0", 0, intersctionList6.size());
		else
			fail("null list");
	}	


}
