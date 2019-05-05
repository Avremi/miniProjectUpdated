/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package scene;

import java.util.ArrayList;
import java.util.List;

import elements.*;
import geometries.*;
import primitives.Color;

/*
 * class to hold the scene
 */
public class Scene {
	private String _sceneName;
	//what is about safety and getters/setters for the next 2 fields ????
	private Color _background;
	private Geometries _geometries;
	private AmbientLight _ambientLight;
	private List<LightSource> _lights;
    private Camera _camera;
    private double _screenDistance;

 // ***************** Constructors ********************** //
	/**
	 * constructor
	 * @param name the name of the scene
	 */
	public Scene(String name) {
		_sceneName=name;
		_lights=new ArrayList<LightSource>();
		_geometries=new Geometries();
	}
	
	/**
	 * copy constructor
	 * @param other - an other element
	 */
	public Scene(Scene other) {
		_sceneName=other._sceneName;
		_background=new Color(other._background);
		_geometries=other._geometries;
		_ambientLight=new AmbientLight(other._ambientLight);
		_lights=other._lights;
		_camera=new Camera(other._camera);
		_screenDistance=other._screenDistance;
	}

	// ***************** setters/getters ********************** //
	/**
	 * getter for background
	 * @return the _background
	 */
	public Color get_background() {
		return _background;
	}

	/**
	 * setter for background
	 * @param _background the _background to set
	 */
	public void set_background(Color _background) {
		this._background = _background;
	}

	/**
	 * getter for geometries
	 * @return the _geometries
	 */
	public Geometries get_geometries() {
		return _geometries;
	}

	/**
	 * setter for geometries
	 * @param _geometries the _geometries to set
	 */
	public void set_geometries(Geometries _geometries) {
		this._geometries = _geometries;
	}

	/**
	 * getter for ambient light
	 * @return the _ambientLight
	 */
	public AmbientLight get_ambientLight() {
		return _ambientLight;
	}

	/**
	 * setter for ambient light
	 * @param _ambientLight the _ambientLight to set
	 */
	public void set_ambientLight(AmbientLight _ambientLight) {
		this._ambientLight = _ambientLight;
	}

	/**
	 * getter for light list
	 * @return
	 */
	public List<LightSource> get_lights() {
		return _lights;
	}

	/**
	 * setter for light list
	 * @param _lights - the list light to set
	 */
	public void set_lights(List<LightSource> _lights) {
		this._lights = _lights;
	}

	/**
	 * getter for camera
	 * @return camera
	 */
	public Camera get_camera() {
		return _camera;
	}

	/**
	 * setter for camera
	 * @param _camera - camera
	 */
	public void set_camera(Camera _camera) {
		this._camera = _camera;
	}

	/**
	 * getter for distance of the camera from the screen
	 * @return distance
	 */
	public double get_screenDistance() {
		return _screenDistance;
	}

	/**
	 * setter for the distance of the camera from the screen
	 * @param _distance - distace
	 */
	public void set_screenDistance(double _distance) {
		this._screenDistance = _distance;
	}
	
	// ***************** operations ********************** //
	/**
	 * add geometry to _geometry
	 * @param geometry the geometry to add
	 */
	public void addGeometry(Geometry geometry) {
		_geometries.add(geometry);
	}
}
