/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package renderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * a class to write an image
 */
public class ImageWriter {

	private int _imageWidth, _imageHeight;
	private int _Nx, _Ny;

	final String PROJECT_PATH = System.getProperty("user.dir");
	
	private BufferedImage _image;
	
	private String _imageName;
	
	// ***************** Constructors ********************** // 
	/**
	 * constructor
	 * @param imageName the image name
	 * @param width the width of the image
	 * @param height the height of the image
	 * @param Nx number of pixels for X
	 * @param Ny number of pixels for Y
	 */
	public ImageWriter(String imageName, int width, int height, int Nx, int Ny) {
		_imageName = imageName;
		_imageWidth = width;
		_imageHeight = height;
		_Nx = Nx;
		_Ny = Ny;
		
		_image = new BufferedImage(_imageWidth, _imageHeight, BufferedImage.TYPE_INT_RGB);
	}
	
	/**
	 * copy constructor
	 * @param imageWriter the element to copy
	 */
	public ImageWriter (ImageWriter imageWriter) {
		this(	imageWriter._imageName,
				imageWriter._imageWidth, imageWriter._imageHeight,
				imageWriter._Nx, imageWriter._Ny);
	}
	
	// ***************** Getters/Setters ********************** //
	/**
	 * getter for width
	 * @return width
	 */
	public int getWidth()  { return _imageWidth;  }
	
	/**
	 * getter for height
	 * @return height
	 */
	public int getHeight() { return _imageHeight; }

	/**
	 * getter for number of pixel in X 
	 * @return number of pixel in X 
	 */
	public int getNy() { return _Ny; }
	
	/**
	 * getter for number of pixel in Y 
	 * @return number of pixel in Y
	 */
	public int getNx() { return _Nx; }

	/**
	 * setter for number of pixel in X
	 * @param _Ny number of pixel in X to set
	 */
	public void setNy(int _Ny) { this._Ny = _Ny; }
	
	/**
	 * setter number of pixel in Y
	 * @param _Nx number of pixel in Y to set
	 */
	public void setNx(int _Nx) { this._Nx = _Nx; }
		
	// ***************** Operations ******************** // 
	
	/**
	 * write the image to the file
	 */
	public void writeToimage(){
		File ouFile = new File(PROJECT_PATH + "/" + _imageName + ".jpg");

		try {
			ImageIO.write(_image, "jpg", ouFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * write pixel to the buffer
	 * @param xIndex X index
	 * @param yIndex Y index
	 * @param r red color
	 * @param g green color
	 * @param b blue color
	 */
	public void writePixel(int xIndex, int yIndex, int r, int g, int b){
		int rgb = new Color(r, g, b).getRGB();
		_image.setRGB(xIndex, yIndex, rgb);
	}
	
	/**
	 * write pixel to the buffer
	 * @param xIndex X index
	 * @param yIndex Y index
	 * @param rgbArray array of the colors
	 */
	public void writePixel(int xIndex, int yIndex, int[] rgbArray){
		int rgb = new Color(rgbArray[0], rgbArray[1], rgbArray[2]).getRGB();
		_image.setRGB(xIndex, yIndex, rgb);
	}
	
	/**
	 * write pixel to the buffer
	 * @param xIndex X index
	 * @param yIndex Y index
	 * @param color the color to set
	 */
	public void writePixel(int xIndex, int yIndex, Color color){
		_image.setRGB(xIndex, yIndex, color.getRGB());
	}
	
}