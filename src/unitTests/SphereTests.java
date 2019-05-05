/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import elements.*;
import geometries.*;
import primitives.* ;

class SphereTests {
		private List<Point3D> getIntersections(Camera camera, Sphere sphere) {
			List<Point3D> list = new ArrayList<>();
			for (int i = 1; i < 4; ++i) {
				for (int j = 1; j < 4; ++j) {
					Ray r = camera.constructRayThroughPixel(3, 3, i, j, 4, 9, 9);
					Map<Geometry, List<Point3D>> map = sphere.findIntersections(r);
					if (!map.isEmpty())
						list.addAll(map.get(sphere));
				}
			}
			return list;
		}

		@Test
		void testIntersectionPoints() {

			// 18 points
			Sphere sphere2 = new Sphere(9, new Point3D(Coordinate.ZERO, Coordinate.ZERO, new Coordinate(-9.5)), null, null);
			Camera camera2 = new Camera(Point3D.ZERO, new Vector(0, -1, 0), new Vector(0, 0, -1));
			List<Point3D> list2 = getIntersections(camera2, sphere2);
			if (list2 != null)
				assertEquals(18, list2.size(), "18 points");
			else
				fail("null list");
			
			// 9 points ,P0 before the center point
			
			Sphere sphere3 = new Sphere(9, new Point3D(Coordinate.ZERO, Coordinate.ZERO, new Coordinate(-4.5)), null, null);
			List<Point3D> list3 = getIntersections(camera2, sphere3);
			if (list3 != null)
				assertEquals(9, list3.size(), "9 points befor center point");
			else
				fail("null list");
			
			//9 points, P0 after center point
			Sphere sphere4 = new Sphere(9, new Point3D(Coordinate.ZERO, Coordinate.ZERO, new Coordinate(4.5)), null, null);
			List<Point3D> list4 = getIntersections(camera2, sphere4);
			if (list4 != null)
				assertEquals(9, list4.size(), "9 points, P0 after center point");
			else
				fail("null list");
			
			//0 points
			Sphere sphere5 = new Sphere(9, new Point3D(Coordinate.ZERO, Coordinate.ZERO, new Coordinate(10)), null, null);
			List<Point3D> list5 = getIntersections(camera2, sphere5);
			if (list5 != null)
				assertEquals(0, list5.size(), "0 points");
			
			//9 points ,P0 by the center point
			Sphere sphere7 = new Sphere(9, Point3D.ZERO, null, null);
			List<Point3D> list7 = getIntersections(camera2, sphere7);
			if (list7 != null)
				assertEquals(9, list7.size(), "9 points, P0 by center point");
			else
				fail("null list");
			
			//10 points 
			Sphere sphere6 = new Sphere(5, new Point3D(Coordinate.ZERO, Coordinate.ZERO, new Coordinate(-6)), null, null);
			Camera camera6 = new Camera(Point3D.ZERO, new Vector(0, -1, 0), new Vector(0, 0, -1));
			List<Point3D> list6 =new ArrayList<>();
			for (int i = 1; i < 4; ++i) {
				for (int j = 1; j < 4; ++j) {
					Ray r = camera6.constructRayThroughPixel(3, 3, i, j, 3, 12, 12);
					Map<Geometry, List<Point3D>> map = sphere6.findIntersections(r);
					if (!map.isEmpty())
						list6.addAll(map.get(sphere6));
				}
			}
			if (list6 != null)
				assertEquals(10, list6.size(), "10 points");
			
			
			
			
		}
		
		@Test
		void innerRayTest() {
			Sphere sphere=new Sphere(10, new Point3D(0, 0, 0),new Color(0, 0, 0),new Material(0, 0, 0, 0, 0));
			Ray r=new Ray(new Point3D(0,0,0), new Vector(1, 0, 0));
			List<Point3D> list = new ArrayList<>();
			list.addAll(sphere.findIntersections(r).get(sphere)); 
			Point3D p=list.get(0);
			assertEquals(new Point3D(10,0,0), p, "the same");
		}

	}


