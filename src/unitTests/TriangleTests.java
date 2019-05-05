/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import elements.Camera;
import geometries.*;
import primitives.*;

class TriangleTests {

	private List<Point3D> findIntersctionTriangle(Triangle t, Camera cam) {
		List<Point3D> list = new ArrayList<>();
		for (int i = 1; i < 4; i++)
			for (int j = 1; j < 4; j++) {
				Ray r = cam.constructRayThroughPixel(3, 3, i, j, 2, 9, 9);
				Map<Geometry, List<Point3D>> map = t.findIntersections(r);
				if (!map.isEmpty())
					list.addAll(map.get(t)); 
			}
		return list;
	}

	@Test
	void test() {
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, -1, 0), new Vector(0, 0, -1));

		// middle ray passes through the edge of the triangle
		Triangle triangle1 = new Triangle(new Point3D(3, -3, -10), new Point3D(-3, 3, -10), new Point3D(3, 3, -10),
				null, null);
		List<Point3D> intersctionList1 = findIntersctionTriangle(triangle1, camera);
		if (intersctionList1 != null)
			assertEquals("point of the end", 0, intersctionList1.size());
		else
			fail("null list");

		// triangle is located at the top of the camera and at its center, the ray (2.2)
		// and (1,2) passes through it.
		Triangle triangle2 = new Triangle(new Point3D(0, -8, -4), new Point3D(2, 0.5, -4), new Point3D(-2, 0.5, -4),
				null, null);
		List<Point3D> intersctionList2 = findIntersctionTriangle(triangle2, camera);
		if (intersctionList2 != null)
			assertEquals("tow point", 2, intersctionList2.size());
		else
			fail("null list");
	}

}
