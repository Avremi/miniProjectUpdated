/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package primitives;

/**
 * a wrapper class to hold and manage the color of java.awt 
 */
public class Color {
	private java.awt.Color _color;
	private double _r;
	private double _g;
	private double _b;
	
	// ***************** Constructors ********************** //
	/**
	 * constructor
	 * @param _r -red color
	 * @param _g - green color
	 * @param _b - blue color
	 */
	public Color(double _r, double _g, double _b) {
		this._r = _r;
		this._g = _g;
		this._b = _b;
		//_color=new java.awt.Color(0); //its the cons posion to init _color or the getter posion ????
	}
	
	public Color(java.awt.Color color) {
		this._r =color.getRed();
		this._g =color.getGreen();
		this._b =color.getBlue();
	}

	/**
	 * copy constructor
	 * @param color - an other color to copy
	 */
	public  Color(Color color) {
		_r=color._r;
		_g=color._g;
		_b=color._b;
	}

	
	// ***************** getters/setters ********************** //
	//what is about setters ???
	/** 
	 * getter for red color parameter
	 * @return the _r
	 */
	public double get_r() {
		return _r;
	}


	/**
	 * getter for green color parameter
	 * @return the _g
	 */
	public double get_g() {
		return _g;
	}


	/**
	 * getter for blue color parameter
	 * @return the _b
	 */
	public double get_b() {
		return _b;
	}


	/**
	 * getter for _color
	 * @return the _color
	 */
	public java.awt.Color get_color() {
		if (_r>255)
			_r=255;
		if(_r<0)
			_r=0;
		if (_g>255)
			_g=255;
		if(_g<0)
			_g=0;
		if (_b>255)
			_b=255;
		if(_b<0)
			_b=0;
		_color= new java.awt.Color((int)_r, (int)_g, (int)_b); //???????? who have to init _color ? the getter or the cons ??
		return _color;//I mean that I have to generate and force to the correct value in the getter !!!!  ???
	}


	// ***************** Operations ********************** //
	/**
	 * add a color to the current color
	 * @param color - the color to add
	 * @return 
	 */
	public Color add(Color color) {
		//I have to check if its correct ! note: why can I get to color._r although it in private ???
		_r+=color._r;
		_g+=color._g;
		_b+=color._b;
		return this;
	}
	
	/**
	 * Scaling  _colorValue
	 * @param scalar- the scalar to scale
	 */
	public Color scale(double scalar) {
		_r*=scalar;
		_g*=scalar;
		_b*=scalar;
		return this;
	}
	
	/**
	 * reduce _colorValue
	 * @param value - the value to reduce
	 */
	public void reduce(double value) {
		//I have to check if its correct !
		_r-=value;
		_g-=value;
		_b-=value;
	}
	
	
}
