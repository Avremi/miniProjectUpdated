/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package elements;

import primitives.*;

/*
 * Derived class from point light to hold spot light
 */
public class SpotLight extends PointLight {

	private Vector _ditection;
	
	/**
	 * constructor
	 * @param _color - color of the light
	 * @param _point - the place of the light
	 * @param _kc constant parameter of the light
	 * @param _kl - linear parameter of the light
	 * @param _kq - sqwer parameter of the light
	 * @param direction - the direction of the light
	 */
	public SpotLight(Color _color, Point3D _point, double _kc, double _kl, double _kq,Vector direction) {
		super(_color, _point, _kc, _kl, _kq);
		_ditection=new Vector(direction).normalize();
	}
	
	/************** operation *******/
	@Override
	public Color getIntensity(Point3D point) {
		return super.getIntensity(point).scale(getD(point).dotProduct(getL(point)));
	}
	
	@Override
	public Vector getD(Point3D point) {
		return _ditection;
	}
}
