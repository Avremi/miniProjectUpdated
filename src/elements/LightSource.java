/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package elements;

import primitives.*;


/*
 * an interface for outer lights 
 */
public interface LightSource {

	/**
	 * get the intensity of light
	 * @param point the intensity at the point
	 * @return the intensity
	 */
    public Color getIntensity(Point3D point);
    
    /**
     * get the L vector - the vector from light to point
     * @param point the vector to
     * @return L vector
     */
	public Vector getL(Point3D point);
	
	/**
	 * get the nearest direction of the light to point
	 * @param point the nearest vector to
	 * @return the direction of the light
	 */
	public Vector getD(Point3D point);

	/**
	 * get the distance from the position of the light to the 
	 * point of the parameter 
	 * @param point the point to calculate distance to
	 * @return the distance
	 */
	public double getDistance(Point3D point);
}
 