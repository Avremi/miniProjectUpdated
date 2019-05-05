/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package primitives;

/*
 * a class to hold details of the material of a shape
 */
public class Material {
	protected double _kd, _ks;
	protected int _nShininess;
	protected double _kr, _kt;
	
	
	/************** constructors *******/
	/**
	 * constructor
	 * @param _kd - kd parameter of material
	 * @param _ks - ks parameter of material
	 * @param _nShininess  -shininess parameter of material
	 */
	public Material(double _kd, double _ks, int _nShininess,double _kr,double _kt) {
		this._kd = _kd; 
		this._ks = _ks;
		this._nShininess = _nShininess;
		this._kr=_kr;
		this._kt=_kt;
	}
	
	/************** getters and setters *******/
	/**
	 * getter for _kd
	 * @return the _kd
	 */
	public double get_kd() {
		return _kd;
	}
	
	/**
	 * getter for _ks
	 * @return the _ks
	 */
	public double get_ks() {
		return _ks;
	}
	
	/**
	 * getter for _nShininess
	 * @return the _nShininess
	 */
	public int get_nShininess() {
		return _nShininess;
	}

	/**
	 * getter for _kr
	 * @return the _kr
	 */
	public double get_kr() {
		return _kr;
	}

	/**
	 * getter for _kt
	 * @return the _kt
	 */
	public double get_kt() {
		return _kt;
	}

}
