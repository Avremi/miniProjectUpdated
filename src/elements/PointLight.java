/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package elements;

import primitives.*;

/*
 * Derived class from light to hold point light
 */
public class PointLight extends Light implements LightSource {
	private Point3D _point;
	private double _kc, _kl, _kq;

	/**
	 * constructor
	 * @param _color - color of the light
	 * @param _point - the place of the light
	 * @param _kc constant parameter of the light
	 * @param _kl - linear parameter of the light
	 * @param _kq - sqwer parameter of the light
	 */
	public PointLight(Color _color, Point3D _point, double _kc, double _kl, double _kq) {
		super(_color);
		this._point = _point;
		this._kc = _kc;
		this._kl = _kl;
		this._kq = _kq;
	}
	
	/************** getters and setters *******/
	/**
	 * @return the _point
	 */
	public Point3D get_point() {
		return _point;
	}

	/************** operation *******/
	@Override
	public Color getIntensity(Point3D point) {
		double distance = point.subtract(_point).length();
		return getIntensity().scale(1 / (_kc +_kl * distance + _kq * distance * distance));
	}

	@Override
	public Vector getL(Point3D point) {
		return point.subtract(_point).normalize();
	}

	@Override
	public Vector getD(Point3D point) {
		return getL(point);
	}

	@Override
	public double getDistance(Point3D point) {
		return _point.distance(point);
	}

}
