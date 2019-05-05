/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package elements;

import primitives.*;

/*
 * a class for the camera
 */
public class Camera {
	private Point3D _p0;
	private Vector _upVector, _toVector, _rightVector;

	/************** constructors *******/
	/**
	 * constructor
	 * @param _p0 - the place of the camera
	 * @param _upVector - up direction
	 * @param _toVector - to direction
	 */
	public Camera(Point3D _p0,Vector _upVector,Vector _toVector) {
		if(_upVector.dotProduct(_toVector)!=0) throw new ExceptionInInitializerError("the vectors are in the same direction");
		this._upVector=new Vector(_upVector).normalize();
		this._toVector=new Vector(_toVector).normalize();
		this._rightVector=this.get_toVector().crossProduct(this.get_upVector()).normalize();
		this._p0=new Point3D(_p0);
		
	}

	/**
	 * copy constructor
	 * @param other an other element
	 */
	public  Camera(Camera other) {
		_p0=new Point3D(other._p0);
		_upVector=new Vector(other._upVector);
		_toVector=new Vector(other._toVector);
		_rightVector=new Vector(other._rightVector);
	}
	
	/************** Getters/Setters *******/
	/**
	 * getter for p0
	 * @return - p0
	 */
	public Point3D get_p0() {
		return _p0;
	}

	/**
	 * getter for up vector
	 * @return up vector
	 */
	public Vector get_upVector() {
		return _upVector;
	}

	/**
	* getter for to vector
	 * @return to vector
	 */
	public Vector get_toVector() {
		return _toVector;
	}

	/**
	 * getter for right vector
	 * @return right vector
	 */
	public Vector getRightVector() {
		return _rightVector;
	}
	
	/*************** Administration *****************/ 
	@Override
	public String toString() {
		return "Camera p0=" + _p0 + ", up Vector=" + _upVector + ", to Vector=" + _toVector + ", right Vector="
				+ _rightVector;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Camera))
			return false;
		Camera other = (Camera) obj;
		if (_p0 == null) {
			if (other._p0 != null)
				return false;
		} else if (!_p0.equals(other._p0))
			return false;
		if (_rightVector == null) {
			if (other._rightVector != null)
				return false;
		} else if (!_rightVector.equals(other._rightVector))
			return false;
		if (_toVector == null) {
			if (other._toVector != null)
				return false;
		} else if (!_toVector.equals(other._toVector))
			return false;
		if (_upVector == null) {
			if (other._upVector != null)
				return false;
		} else if (!_upVector.equals(other._upVector))
			return false;
		return true;
	}


	/************** Operations ***************/
	/**
	 * construct a ray through pixel
	 * @param Nx - x pixel
	 * @param Ny - y pixel
	 * @param i - i index of the pixel
	 * @param j - j index of the pixel
	 * @param screenDistance - distance from screen
	 * @param screenWidth - width of the screen
	 * @param screenHeight - height of the screen
	 * @return
	 */
	public Ray constructRayThroughPixel(int Nx, int Ny, int i, int j, double screenDistance, double screenWidth, double screenHeight) {
		if(Nx==0 ||  Ny==0 || screenDistance==0) {
			//TDDO what I have to throw and what I have to do in case of distance = 0 ???????
		}
		Vector directionToPixel;  
		Point3D ijPoint,centerPoint;
		double pixelWidthX=screenWidth/Nx,pixelWidthY=screenHeight/Ny;
		
		double disToI = i-(double)(Nx+1)/2,disToJ=j-(double)(Ny+1)/2;
		
		centerPoint=this.get_p0().addVector(_toVector.scaling(screenDistance));
		
		if(disToI==0 && disToJ==0) {
			ijPoint=new Point3D(centerPoint);
		}
		else if(disToI==0) {
			ijPoint=centerPoint.addVector(this._upVector.scaling(disToJ*pixelWidthY));
			//?????????????????????
			ijPoint=new Point3D(ijPoint.getX().get(), ijPoint.getY().get()*(-1), ijPoint.getZ().get());
		}
		else if(disToJ==0) {
			ijPoint=centerPoint.addVector(this._rightVector.scaling(disToI*pixelWidthX));
		}
		else {
			ijPoint=centerPoint.addVector(this._rightVector.scaling(disToI*pixelWidthX).subtract(this._upVector.scaling(disToJ*pixelWidthY)));
		}
		
		directionToPixel=ijPoint.subtract(_p0).normalize();
		return new Ray(_p0, directionToPixel);
	}


	

}














