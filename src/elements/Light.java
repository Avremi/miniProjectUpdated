/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package elements;

import primitives.Color;

/*
 * abstracted class for light
 */
public abstract class Light {
	protected Color _color;
	
	/************** constructors *******/
	/**
	 * constructor
	 * @param _color - the color of the light
	 */
	public Light(Color _color) {
		this._color = new Color(_color);
	}
	
	/************** operation *******/
	/**
	 * get the intensity of the light
	 * @return the intensity of the light
	 */
	public Color getIntensity() {
		return new Color(_color);
	}

}
