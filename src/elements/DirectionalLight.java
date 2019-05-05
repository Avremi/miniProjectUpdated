/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package elements;

import primitives.*;

/*
 * a Derived class from Light to hold directional light
 */
public class DirectionalLight extends Light implements LightSource {

	private Vector _direction;

	/************** constructors *******/
	/**
	 * constructor
	 * 
	 * @param color
	 *            the color of the light
	 * @param direction
	 *            - the direction of the light
	 */
	public DirectionalLight(Color color, Vector direction) {
		super(color);
		_direction = new Vector(direction).normalize();
	}

	/************** operations *******/
	@Override
	public Color getIntensity(Point3D point) {
		return getIntensity();
	}

	@Override
	public Vector getL(Point3D point) {//it is better to rerurn the direction or the get D????
		return getD(point);
	}

	@Override
	public Vector getD(Point3D point) {
		return _direction;
	}

	@Override
	public double getDistance(Point3D point) {
		return Double.MAX_VALUE;
	}

}
