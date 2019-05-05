/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */
package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Vector;

public class VectorTests {

	@Test
	public void testSubtruct() {
		Vector vector1=new Vector(8, 8, 8),
				vector3=new Vector(-1, 0, -5),vector4=new Vector(4, -3, 0);
		
		Vector newVector;
		
		newVector=vector1.subtract(vector3);
		assertEquals(new Vector(9,8,13),newVector );
		newVector=vector3.subtract(vector4);
		assertEquals(new Vector(-5,3,-5),newVector );
		try {
			//newVector=vector1.subtract(vector2);
			//fail("fail test. initilize vector with 0");
		} catch (Exception e) {
			
		}
		vector1 =new Vector(8.000001, 8, 8);
		try {
			//newVector=vector1.subtract(vector2);
			//fail("fail test. initilize vector with 0");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testAdd() {
		
		Vector vector1=new Vector(8, 8, 8),
				vector3=new Vector(-1, 0, -5),vector4=new Vector(4, -3, 0);
		
		Vector newVector;
		
		newVector=vector1.add(vector3);
		assertEquals(new Vector(7,8,3),newVector );
		newVector=vector3.add(vector4);
		assertEquals(new Vector(3,-3,-5),newVector );
		try {
			//newVector=vector1.add(vector2);
			//fail("fail test. initilize vector with 0");
		} catch (Exception e) {
			
		}
		vector1 =new Vector(8.0000001, 8, 8);
		try {
			//newVector=vector1.add(vector2);
			//fail("fail test. initilize vector with 0");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testDotProduct() {
		Vector vector1=new Vector(8, 8, 8),vector2=new Vector(8,8,8),
				vector3=new Vector(-1, 0, -5),vector4=new Vector(4, -3, 0);
		
		double res=vector1.dotProduct(vector2);
		
		
		assertEquals(192, res,0);
		assertEquals(-48,vector1.dotProduct(vector3),0);
		assertEquals(-4,vector3.dotProduct(vector4),0);
	}
	
	@Test
	public void testCrossProduct() {
		Vector vector1=new Vector(8, 8, 8),vector2=new Vector(-4,-4,-4),
				vector3=new Vector(1, 0,5),vector4=new Vector(4, -3, 0);
		Vector testVector=vector1.crossProduct(vector3);
		assertEquals(0,testVector.dotProduct(vector1),0);
		assertEquals(0,testVector.dotProduct(vector3),0);
		
		testVector=vector1.crossProduct(vector4);
		assertEquals(0,testVector.dotProduct(vector1),0);
		assertEquals(0,testVector.dotProduct(vector4),0);
		
		testVector=vector2.crossProduct(vector4);
		assertEquals(0,testVector.dotProduct(vector2),0);
		assertEquals(0,testVector.dotProduct(vector4),0);
		
		try {
			//vector1.crossProduct(vector2);
			//fail("error. cross product of vectors on the same line is not 0");
		} catch (Exception e) {
			
		}
		
		
	}
	
	@Test
	public void testScaling() {
		Vector vector1=new Vector(8, 8, 8),vector2=new Vector(-8,-8,-8);

		assertEquals(new Vector(24, 24, 24),vector1.scaling(3.0));
		assertEquals(new Vector(-24, -24, -24),vector1.scaling(-3.0));
		assertEquals(new Vector(24, 24, 24),vector2.scaling(-3.0));
		assertEquals(new Vector(-24, -24, -24),vector2.scaling(3.0));
		
		try {
			vector1.scaling(0.0);
			fail("error. scalar product scalar 0 is not 0");
		} catch (IllegalArgumentException e) {
			
		}
		
		
	}
	
	public void testLength() {
		Vector vector1=new Vector(1, 2, 2),vector2=new Vector(0,-4,-3);

		assertEquals(3,vector1.length(),0);
		assertEquals(5,vector2.length(),0);	
	}
	
	public void testNormalize() {
		Vector vector1=new Vector(1, 2, 2),vector2=new Vector(0,-4,-3);

		assertEquals(1,vector1.normalize().length(),0);
		assertEquals(1,vector2.normalize().length(),0);	
	}

}
