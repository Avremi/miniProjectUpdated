/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package primitives;

/*
 * a class for coordinate
 */
public class Coordinate {
	//private static final double EPSILON = 0.0000001;
	private double _coord;
	public static final Coordinate ZERO = new Coordinate(0.0);

	/********** Constructors ***********/
	/**
	 * constructor
	 * @param coord - a numeric value for coordinate
	 */
	public Coordinate(double coord) {
		// if it too close to zero make it zero
		_coord = (getExp(coord) < ACCURACY) ? 0.0 : coord;
	}

	/**
	 * copy constructor
	 * @param other - an other element
	 */
	public Coordinate(Coordinate other) {
		_coord = other._coord;
	}

	/************** Getters/Setters *******/
	/**
	 * getter for the value of the coordinate
	 * @return a real numeric value of the coordinate
	 */
	public double get() {
		return _coord;
	}

	/*************** Administration *****************/
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		
		// if (Coordinate.ZERO.equals(some-number)) ....
		if (obj instanceof Double)
			return subtract((double)obj) == 0.0;

		if (!(obj instanceof Coordinate)) return false;
		
		Coordinate other = (Coordinate) obj;
		return subtract(other._coord) == 0.0;
	}

	@Override
	public String toString() {
		return "" + _coord;
	}

	/************** Operations ***************/
	/**
	 * subtract an other coordinate
	 * @param other - an other coordinate
	 * @return a new coordinate
	 */
	public Coordinate subtract(Coordinate other) {
		return new Coordinate(subtract(other._coord));
	}

	/**
	 * add an other coordinate
	 * @param other - an other coordinate
	 * @return a new coordinate
	 */
	public Coordinate add(Coordinate other) {
		return new Coordinate(add(other._coord));
	}
	
	/************** Helpers ***************/
	// It is binary, equivalent to ~1/1,000,000 in decimal (6 digits)
	private static final int ACCURACY = -20;

	// double store format (bit level): seee eeee eeee (1.)mmmm … mmmm
	// 1 bit sign, 11 bits exponent, 53 bits (52 stored) normalized mantissa
	// the number is m+2^e where 1<=m<2
	// NB: exponent is stored "normalized" (i.e. always positive by adding 1023)
	int getExp(double num) {
		// 1. doubleToRawLongBits: "convert" the stored number to set of bits
		// 2. Shift all 52 bits to the right (removing mantissa)
		// 3. Zero the sign of number bit by mask 0x7FF
		// 4. "De-normalize" the exponent by subtracting 1023
		return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
	}

	private double subtract(double other) {
		int otherExp = getExp(other);
		int thisExp = getExp(_coord);

		// if other is too small relatively to our coordinate
		// return the original coordinate
		if (otherExp - thisExp < ACCURACY)
			return _coord;

		// if our coordinate is too small relatively to other
		// return negative of other coordinate
		if (thisExp - otherExp < ACCURACY)
			return -other;

		double result = _coord - other;
		// if the result is too small tell that it is zero
		int resultExp = getExp(result);
		return resultExp < ACCURACY ? 0.0 : result;
	}

	private double add(double other) {
		int otherExp = getExp(_coord);
		int thisExp = getExp(_coord);

		// if other is too small relatively to our coordinate
		// return the original coordinate
		if (otherExp - thisExp < ACCURACY)
			return _coord;

		// if our coordinate is too small relatively to other
		// return other coordinate
		if (thisExp - otherExp < ACCURACY)
			return other;

		double result = _coord + other;
		// if the result is too small tell that it is zero
		int resultExp = getExp(result);
		return resultExp < ACCURACY ? 0.0 : result;
	}

}
