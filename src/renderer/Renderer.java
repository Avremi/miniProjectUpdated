/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package renderer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import elements.*;
import geometries.*;
import primitives.*;
import scene.*;

/*
 * class to renderer an image
 */
public class Renderer {

	private static final int MAX_CALC_COLOR_LEVEL = 4;
	private Scene _scene;
	private ImageWriter _imageWriter;

	/************** constructors *******/
	/**
	 * constructor
	 * 
	 * @param scene
	 *            - the scene to implement
	 * @param imageWrighter
	 *            - the imageWrighter to implement
	 */
	public Renderer(Scene scene, ImageWriter imageWriter) {
		_scene = new Scene(scene);
		_imageWriter = new ImageWriter(imageWriter);
	}

	/************** Getters/Setters *******/
	/**
	 * @return the _scene
	 */
	public Scene get_scene() {
		return _scene;
	}

	/**
	 * @param _scene
	 *            the scene to set
	 */
	public void set_scene(Scene _scene) {
		this._scene = _scene;
	}

	/**
	 * @return the imageWriter
	 */
	public ImageWriter get_imageWriter() {
		return _imageWriter;
	}

	/**
	 * @param _imageWriter
	 *            the imageWriter to set
	 */
	public void set_imageWriter(ImageWriter _imageWriter) {
		this._imageWriter = _imageWriter;
	}

	/************** Operations ***************/
	/**
	 * render the image by calculate the intersection points and the proper color
	 */
	public void renderImage() {
		int nx = _imageWriter.getNx(), ny = _imageWriter.getNy();
		// run each pixel
		for (int i = 1; i < nx; i++) {
			for (int j = 1; j < ny; j++) {
				Ray ray = _scene.get_camera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), i, j,
						_scene.get_screenDistance(), _imageWriter.getWidth(), _imageWriter.getHeight());
				Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
				if (intersectionPoints.isEmpty()) {
					_imageWriter.writePixel(i, j, _scene.get_background().get_color());
				} else {
					Entry<Geometry, Point3D> closestPoint = getClosestPoint(_scene.get_camera().get_p0(),
							intersectionPoints).entrySet().iterator().next();
					_imageWriter.writePixel(i, j,
							calcColor(closestPoint.getKey(), closestPoint.getValue(), ray).get_color());
				}
			}
		}
	}

	/**
	 * print a grid on the image
	 * 
	 * @param interval
	 *            - the value between the lines
	 */
	public void printGrid(int interval) {
		int width = _imageWriter.getWidth(), height = _imageWriter.getHeight();

		for (int i = 1; i < width; i += interval) {
			for (int j = 1; j < height; j++) {
				_imageWriter.writePixel(i, j, _scene.get_ambientLight().getIntensity().get_color());
			}
		}
		for (int j = 1; j < width; j += interval) {
			for (int i = 1; i < height; i++) {
				_imageWriter.writePixel(i, j, _scene.get_ambientLight().getIntensity().get_color());
			}
		}
	}

	/************** Helpers ***************/
	/**
	 * get the closet intersected point in case there are a few of them
	 * 
	 * @param list
	 *            -a list of the intersection point of a geometry
	 * @return the closet 3d point
	 */
	private Map<Geometry, Point3D> getClosestPoint(Point3D p, Map<Geometry, List<Point3D>> intersectionPoints) {
		double distance = Double.MAX_VALUE;
		Map<Geometry, Point3D> minDistancePoint = null;
		//run for each pair in the list
		for (Entry<Geometry, List<Point3D>> set : intersectionPoints.entrySet()) {
			//run on the list of the current pair
			for (Point3D point : set.getValue()) {
				if (p.distance(point) < distance) {
					minDistancePoint = new HashMap<>();
					minDistancePoint.put(set.getKey(), point);
					distance = p.distance(point);
				}
			}
		}
		return minDistancePoint;
	}

	/**
	 * get a list of all intersection points of the scene for a ray got in parameter
	 * 
	 * @param ray
	 *            - the ray to check intersection
	 * @return a list of all intersection points
	 */
	private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {
		return _scene.get_geometries().findIntersections(ray);
	}

	/**
	 * a wrapper function to calculate the color for a point got in parameter
	 * 
	 * @param geometry
	 *            - the geometry of the point
	 * @param point
	 *            - the point to color
	 * @param inRay
	 * @return the color
	 */
	private Color calcColor(Geometry geometry, Point3D point, Ray inRay) {
		return calcColor(geometry, point, inRay, MAX_CALC_COLOR_LEVEL, 1.0);
	}

	/**
	 * a recursion function to calculate the color for a point got in parameter
	 * 
	 * @param geometry
	 *            - the geometry of the point
	 * @param point
	 *            - the point to color
	 * @param inRay
	 *            - the ray
	 * @param level
	 *            - the level of transparency to calculate
	 * @param k
	 *            - parameter of transparency
	 * @return the effective color
	 */
	private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level, double k) {
		if (level == 0 || Coordinate.ZERO.equals(k))
			return new Color(java.awt.Color.BLACK);

		Color color = new Color(_scene.get_ambientLight().getIntensity());
		color.add(geometry.get_emmission());

		Vector v = inRay.get_direction();
		Vector n = geometry.getNormal(point);
		int nShininess = geometry.get_material().get_nShininess();
		double kd = geometry.get_material().get_kd();
		double ks = geometry.get_material().get_ks();

		for (LightSource lightSource : _scene.get_lights()) {
			Vector l = lightSource.getL(point);

			// check that the light is on this side
			if (new Coordinate(n.dotProduct(l)).get() * new Coordinate(n.dotProduct(v)).get() > 0) {
				double occlud = occluded(l, point, geometry, lightSource);
				if (!Coordinate.ZERO.equals(occlud)) {
					Color lightIntensity = lightSource.getIntensity(point);
					lightIntensity.scale(occlud);
					color.add(calcDiffusive(kd, l, n, lightIntensity));
					color.add(calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}

		// Recursive call for a reflected ray
		double kr = geometry.get_material().get_kr();
		Color reflectedLight = new Color(java.awt.Color.BLACK);
		List<Ray> raysList = getNearbyRays(constructReflectedRay(n, point, inRay));
		for (Ray r : raysList) {
			Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(r);
			if (!intersectionPoints.isEmpty()) {
				Entry<Geometry, Point3D> reflectedPoint = getClosestPoint(point, intersectionPoints).entrySet()
						.iterator().next();
				reflectedLight.add(calcColor(reflectedPoint.getKey(), reflectedPoint.getValue(), r, level - 1, k * kr));
			}
		}
		reflectedLight.scale(kr / raysList.size());
		color.add(reflectedLight);

		// recursive call for refracted light
		double kt = geometry.get_material().get_kt();
		Color refractedLight = new Color(java.awt.Color.BLACK);

		raysList = getNearbyRays(constructRefractedRay(n, point, inRay));
		for (Ray r : raysList) {
			Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(r);
			if (!intersectionPoints.isEmpty()) {
				Entry<Geometry, Point3D> refractedPoint = getClosestPoint(point, intersectionPoints).entrySet()
						.iterator().next();
				refractedLight.add(calcColor(refractedPoint.getKey(), refractedPoint.getValue(), r, level - 1, k * kt));
			}
		}
		refractedLight.scale(kt / raysList.size());
		color.add(refractedLight);

		return color;
	}

	/**
	 * calculate the light for a point that have occlude
	 * 
	 * @param l
	 * 
	 *            - vector from light to the point
	 * @param point
	 *            - the point to check shadow
	 * @param geometry
	 *            the geometry of point
	 * @param lightSource
	 *            the source of the light
	 * @return true if there are occlude at the point
	 */
	private double occluded(Vector l, Point3D point, Geometry geometry, LightSource lightSource) {
		Vector toLightDirection = l.scaling((double) -1); // from point to light source
		Vector normal = geometry.getNormal(point);

		Point3D epsPoint = addEpsToPoint(point, normal, toLightDirection);

		Ray toLightRay = new Ray(epsPoint, toLightDirection);

		Map<Geometry, List<Point3D>> intersectionPoints = _scene.get_geometries().findIntersections(toLightRay);

		double distanceOfLight = lightSource.getDistance(point);
		double shadowK = 1;
		for (Map.Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {
			for (Point3D p : entry.getValue()) {
				if (point.distance(p) < distanceOfLight)
					shadowK *= entry.getKey().get_material().get_kt();
			}
		}
		return shadowK;
	}

	/**
	 * calculate specular
	 * 
	 * @param ks
	 *            - ks parameter of the material
	 * @param l
	 *            - vector from light to the point
	 * @param n
	 *            - normal at this point
	 * @param v
	 *            -
	 * @param nShininess
	 *            the shininess of the material shape
	 * @param lightIntensity
	 *            the light in this point without specular and diffusing
	 * @return color of the light + specular
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {

		Vector r = l.subtract(n.scaling(2 * l.dotProduct(n)));

		if (v.dotProduct(r) > 0) {
			return new Color(java.awt.Color.BLACK);
		}
		Color color = new Color(lightIntensity);
		color.scale(Math.pow(v.scaling((double) -1).dotProduct(r), nShininess) * ks);

		return color;

	}

	/**
	 * calculate diffusing
	 * 
	 * @param kd
	 *            - kd parameter of the material
	 * @param l
	 *            - vector from light to the point
	 * @param n
	 *            - normal at this point
	 * @param lightIntensity
	 *            - lightIntensity the light in this point without specular and
	 *            diffusing
	 * @return color of the light + diffusing
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		Color color = new Color(lightIntensity);
		color.scale(kd * Math.abs(l.dotProduct(n)));
		return color;
	}

	/**
	 * construct refracted ray
	 * 
	 * @param normal
	 *            -the normal of the geometry
	 * @param point
	 *            - the base of the retracted ray
	 * @param baseRay
	 *            the ray from the camera
	 * @return Refracted ray
	 */
	private Ray constructRefractedRay(Vector normal, Point3D point, Ray baseRay) {
		Vector baseDirection = baseRay.get_direction();
		Point3D epsPoint = addEpsToPoint(point, normal, baseDirection);
		return new Ray(epsPoint, baseDirection);
	}

	/**
	 * construct reflected ray
	 * 
	 * @param normal
	 *            - normal at the point
	 * @param point
	 *            - the base of the reflected ray
	 * @param baseRay
	 *            - the ray from camera to geometry
	 * @return reflected ray
	 */
	private Ray constructReflectedRay(Vector normal, Point3D point, Ray baseRay) {
		Vector baseDirection = baseRay.get_direction();
		Vector reflected = baseDirection.subtract(normal.scaling(2 * baseDirection.dotProduct(normal))).normalize();

		Point3D epsPointp = addEpsToPoint(point, normal, reflected);

		return new Ray(epsPointp, reflected);
	}

	/**
	 * get the nearby rays in a cone shape around the base ray
	 * 
	 * @param baseRay-
	 *            the base ray
	 * @return a list of the nearby rays
	 */
	private List<Ray> getNearbyRays(Ray baseRay) {
		Vector v1, v2;
		Vector direction = baseRay.get_direction();
		Point3D p = baseRay.get_p00();
		double x = direction.get_head().getX().get(), y = direction.get_head().getY().get(),
				z = direction.get_head().getZ().get();

		// calculate the first normal to the base ray direction
		if (x != 0 || y != 0) {
			v1 = new Vector(y, x * (-1), 0).normalize();
		} else {
			v1 = new Vector(z, 0, x * (-1)).normalize();
		}
		// calculate the other normal vectors
		v2 = baseRay.get_direction().crossProduct(v1).normalize();
		
		List<Ray> raysList = new LinkedList<Ray>();
		raysList.add(baseRay);

		for (int i = 0; i < 15; i++) {
			raysList.add(new Ray(p, direction.add(getRandomVectorInPlane(v1, v2).scaling(getTeta()))));
		}
		return raysList;
	}

	/**
	 * get a random scalar until teta as an angle
	 * 
	 * @return a number as an angle
	 */
	private double getTeta() {
		Random random = new Random();
		double teta = 0.03;
		return random.nextInt((int) (teta * 10000)) / 10000.0 + 0.0001;
	}

	/**
	 * get a random vector in the plane that fold up with v1,v2
	 * 
	 * @param v1
	 *            - a vector in the plane
	 * @param v2
	 *            - a vector in the plane normal to v1
	 * @return a new vector in the plane
	 */
	private Vector getRandomVectorInPlane(Vector v1, Vector v2) {
		Random random = new Random();
		//one is added to avoid 0
		int scalar1 = random.nextInt()+ 1;
		if (scalar1 % 2 == 0)
			scalar1 *= (-1);
		int scalar2 = random.nextInt()+ 1;
		if (scalar2 % 2 == 0)
			scalar2 *= (-1);
		//the scalar is in half to avoid from range exception 
		Vector productedV1 = v1.scaling(scalar1/2.0);
		Vector productedV2 = v2.scaling(scalar2/2.0);
		return productedV1.add(productedV2).normalize();
	}

	/**
	 * advance the position of the point with epsilon toward direction
	 * 
	 * @param point
	 *            - the point to advance
	 * @param normal
	 *            - the normal of the geomatry at this point
	 * @param direction
	 *            - the direction toward to advance
	 * @return a new point at the new position
	 */
	private Point3D addEpsToPoint(Point3D point, Vector normal, Vector direction) {
		Vector epsVector = normal.scaling(normal.dotProduct(direction) > 0 ? 2.0 : -2.0);
		return point.addVector(epsVector);

	}

}
