/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package elements;

import primitives.Color;

/*
 * a class for ambient light
 */
public class AmbientLight extends Light{  
	private double _ka;
  
	/************** constructors *******/
	/**
	 * constructor
	 * @param color - the color of the light
	 * @param ka the parameter of the color
	 */
	public AmbientLight(Color color,double ka) {
		super(new Color(color).scale(ka));
		_ka=ka;
	}
	
	/**
	 * copy constructor
	 * @param other - an other element
	 */
	public  AmbientLight(AmbientLight other) {
		super(new Color(other._color).scale(other._ka));
		_ka=other._ka;
	}
	
	/// TDOD what about getters and setters ???????????
	
	/************** operations *******/
	/**
	 * get the intensity the color
	 * @return the intensity color
	 */
	@Override
	public Color getIntensity() {
		//_color.scale(_ka);
		return _color;
	}


}
