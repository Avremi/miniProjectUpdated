/**
 * @author Avreimi cheshin 203220876 AHH24826@gmail.com
 * @author Elchay shoval 203548976 shoval@gmail.com
 */

package unitTests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.*;

class RendererTest {

	@Test
	void lightTests_ambientLight() {

		Scene scene = new Scene("my scene");
		scene.set_background(new Color(0, 0, 70));
		scene.set_ambientLight(new AmbientLight(new Color(225, 0, 0), 1));
		scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)));
		scene.set_screenDistance(50); 
		
		scene.addGeometry(new Triangle(new Point3D(-100, 50, 0), new Point3D(-0, 50, 100), new Point3D(100, 50, 0),
				new Color(0, 0, 0), new Material(0, 0, 0, 0, 0)));
		
		ImageWriter imageWriter1 = new ImageWriter("ambient light test 1", 500, 500, 500, 500);
		Renderer myRenderer = new Renderer(scene, imageWriter1);
		myRenderer.renderImage();
		myRenderer.printGrid(50);
		myRenderer.get_imageWriter().writeToimage();
		
		scene.set_ambientLight(new AmbientLight(new Color(250, 0, 0), 0.5));
		ImageWriter imageWriter2 = new ImageWriter("ambient light test 2", 500, 500, 500, 500);
		myRenderer = new Renderer(scene, imageWriter2);
		myRenderer.renderImage();
		myRenderer.printGrid(50);
		myRenderer.get_imageWriter().writeToimage();
	}
	
	@Test
	void AmishenLightTests() {

		Scene scene = new Scene("my scene");
		scene.set_background(new Color(0, 0, 70));
		scene.set_ambientLight(new AmbientLight(new Color(0, 0, 0), 0));
		scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)));
		scene.set_screenDistance(50); 
		
		scene.addGeometry(new Triangle(new Point3D(-100, 50, 0), new Point3D(0, 50, 100), new Point3D(100, 50, 0),
				new Color(255, 0, 0), new Material(0, 0, 0, 0, 0)));
		
		ImageWriter imageWriter1 = new ImageWriter("amishen light test ", 500, 500, 500, 500);
		Renderer myRenderer = new Renderer(scene, imageWriter1);
		myRenderer.renderImage();
		myRenderer.printGrid(50);
		myRenderer.get_imageWriter().writeToimage();
		
	
	}
	
	@Test
	void DirectionalLightTests() {
		

		Scene scene = new Scene("my scene");
		scene.set_background(new Color(0, 0, 70));
		scene.set_ambientLight(new AmbientLight(new Color(0, 0, 0), 0));
		scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)));
		scene.set_screenDistance(50); 
		// geomtris
		scene.addGeometry(new Triangle(new Point3D(-100, 50, 0), new Point3D(0, 50, 100), new Point3D(100, 50, 0),
				new Color(255, 0, 0), new Material(0.5, 0, 0, 0, 0)));
		// light
		List<LightSource> lightSourcehs = new ArrayList<LightSource>();
		lightSourcehs.add(new DirectionalLight(new Color(225, 225, 225), new Vector(0, 1, 0)));
		scene.set_lights(lightSourcehs);
		
		ImageWriter imageWriter1 = new ImageWriter("direction light test ", 500, 500, 500, 500);
		Renderer myRenderer = new Renderer(scene, imageWriter1);
		myRenderer.renderImage();
		myRenderer.get_imageWriter().writeToimage();
		
	
	}
	
	@Test
	void pointLightTests() {
		

		Scene scene = new Scene("my scene");
		scene.set_background(new Color(0, 0, 70));
		scene.set_ambientLight(new AmbientLight(new Color(0, 0, 0), 0));
		scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)));
		scene.set_screenDistance(50); 
		// geomtris
		scene.addGeometry(new Triangle(new Point3D(-100, 50, -20), new Point3D(0, 50, 80), new Point3D(100, 50, -20),
				new Color(255, 0, 0), new Material(1, 1, 40, 0, 0)));
		// light
		List<LightSource> lightSourcehs = new ArrayList<LightSource>();
		lightSourcehs.add(new PointLight(new  Color(225,225,225), new Point3D(0,10,0), 1, 0,0.0005 ));
		scene.set_lights(lightSourcehs);
		
		ImageWriter imageWriter1 = new ImageWriter("point light test ", 500, 500, 500, 500);
		Renderer myRenderer = new Renderer(scene, imageWriter1);
		myRenderer.renderImage();
		myRenderer.get_imageWriter().writeToimage();
		
		
	
	}

	@Test
	void spotLightTests() {
		

		Scene scene = new Scene("my scene");
		scene.set_background(new Color(0, 0, 70));
		scene.set_ambientLight(new AmbientLight(new Color(0, 0, 0), 0));
		scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)));
		scene.set_screenDistance(50); 
		// geomtris
		scene.addGeometry(new Triangle(new Point3D(-200, 50, -20), new Point3D(0, 50, 200), new Point3D(200, 50, -20),
				new Color(255, 0, 0), new Material(2, 1, 40, 0, 0)));
		// light
		List<LightSource> lightSourcehs = new ArrayList<LightSource>();
		lightSourcehs.add(new SpotLight(new  Color(225,225,225), new Point3D(0,10,0), 1, 0,0.0005, new Vector(1.5, 1, 0) ));
		scene.set_lights(lightSourcehs);
		
		ImageWriter imageWriter1 = new ImageWriter("spot light test ", 500, 500, 500, 500);
		Renderer myRenderer = new Renderer(scene, imageWriter1);
		myRenderer.renderImage();
		myRenderer.get_imageWriter().writeToimage();
		
		
	 
	}

	
	
	@Test
	void test() {

		Scene scene = new Scene("my scene");
		scene.set_background(new Color(225, 225, 225));
		scene.set_ambientLight(new AmbientLight(new Color(0, 0, 0), 1));
		scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)));
		scene.set_screenDistance(50);

		scene.addGeometry(new Triangle(new Point3D(0, 50, 100), new Point3D(-100, 50, 100), new Point3D(-100, 50, 0),
				new Color(50, 90, 0), new Material(0, 0, 0, 0, 0)));
		scene.addGeometry(new Triangle(new Point3D(0, 50, 100), new Point3D(100, 50, 100), new Point3D(100, 50, 0),
				new Color(100, 60, 95), new Material(0, 0, 0, 0, 0)));
		scene.addGeometry(new Triangle(new Point3D(100, 50, 0), new Point3D(100, 50, -100), new Point3D(0, 50, -100),
				new Color(150, 250, 110), new Material(0, 0, 0, 0, 0)));
		scene.addGeometry(new Triangle(new Point3D(0, 50, -100), new Point3D(-100, 50, -100), new Point3D(-100, 50, 0),
				new Color(250, 0, 150), new Material(0, 0, 0, 0, 0)));

		scene.addGeometry(new Sphere(85, new Point3D(0, 120, 0), new Color(200, 0, 0), new Material(0, 0, 0, 0, 0)));
		List<LightSource> lightSourcehs = new ArrayList<LightSource>();
		lightSourcehs.add(new DirectionalLight(new Color(200, 200, 200), new Vector(1, 0, 0)));
		scene.set_lights(lightSourcehs);
		// scene.addGeometry(new Geometries(list));
		ImageWriter imageWriter = new ImageWriter("myImage2", 500, 500, 500, 500);
		Renderer myRenderer = new Renderer(scene, imageWriter);
		myRenderer.renderImage();
		myRenderer.printGrid(50);
		myRenderer.get_imageWriter().writeToimage();
	}

	@Test
	void test1() {

		Scene scene = new Scene("my scene");
		scene.set_background(new Color(225, 225, 225));
		scene.set_ambientLight(new AmbientLight(new Color(0, 0, 0), 1));
		scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)));
		scene.set_screenDistance(50);

		scene.addGeometry(new Sphere(85, new Point3D(0, 119, 0), new Color(200, 0, 0), new Material(0, 0, 0, 0, 0)));
		scene.addGeometry(
				new Sphere(85, new Point3D(-50, 120, 0), new Color(150, 30, 45), new Material(0, 0, 0, 0, 0)));
		scene.addGeometry(new Sphere(85, new Point3D(50, 120, 0), new Color(30, 200, 95), new Material(0, 0, 0, 0, 0)));
		scene.addGeometry(new Triangle(new Point3D(0, 50, -250), new Point3D(-100, 50, 0), new Point3D(100, 50, 0),
				new Color(100, 220, 185), new Material(0, 0, 0, 0, 0)));
		// scene.addGeometry(new Geometries(list));
		List<LightSource> lightSourcehs = new ArrayList<LightSource>();
		lightSourcehs.add(new DirectionalLight(new Color(200, 200, 200), new Vector(1, 0, 0)));
		scene.set_lights(lightSourcehs);
		ImageWriter imageWriter = new ImageWriter("myImage3", 500, 500, 500, 500);
		Renderer myRenderer = new Renderer(scene, imageWriter);
		myRenderer.renderImage();
		myRenderer.printGrid(50);
		myRenderer.get_imageWriter().writeToimage();
	}

	@Test
	void test2() {

		Scene scene = new Scene("my scene");
		scene.set_background(new Color(225, 225, 225));
		scene.set_ambientLight(new AmbientLight(new Color(0, 0, 0), 1));
		scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)));
		scene.set_screenDistance(50);

		// Since the Sphere is closer to my camera than the triangles, it hides the
		// triangles
		scene.addGeometry(new Sphere(85, new Point3D(0, 100, 0), new Color(200, 0, 0), new Material(0, 0, 0, 0, 0)));
		scene.addGeometry(new Triangle(new Point3D(0, 25, 50), new Point3D(-50, 25, 0), new Point3D(50, 25, 0),
				new Color(50, 220, 80), new Material(0, 0, 0, 0, 0)));
		scene.addGeometry(new Triangle(new Point3D(0, 25, -52), new Point3D(-50, 25, 0), new Point3D(50, 25, 0),
				new Color(75, 200, 180), new Material(0, 0, 0, 0, 0)));

		// scene.addGeometry(new Geometries(list));
		List<LightSource> lightSourcehs = new ArrayList<LightSource>();
		lightSourcehs.add(new DirectionalLight(new Color(200, 200, 200), new Vector(1, 0, 0)));
		scene.set_lights(lightSourcehs);
		ImageWriter imageWriter = new ImageWriter("myImage4", 500, 500, 500, 500);
		Renderer myRenderer = new Renderer(scene, imageWriter);
		myRenderer.renderImage();
		myRenderer.printGrid(50);
		myRenderer.get_imageWriter().writeToimage();
	}

	@Test
	void test3() {

		Scene scene = new Scene("my scene");
		scene.set_background(new Color(0, 0, 0));
		scene.set_ambientLight(new AmbientLight(new Color(0, 0, 50), 1));
		scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)));
		scene.set_screenDistance(50);
		// ArrayList<Geometry> list =new ArrayList<>();

		// Since the Sphere is closer to my camera than the triangles, it hides the
		// triangles
		scene.addGeometry(new Sphere(80, new Point3D(0, 100, 0), new Color(100, 0, 0), new Material(2, 2, 150, 0, 0)));
		scene.addGeometry(new Triangle(new Point3D(-500, 180, 500), new Point3D(-500, 180, -500),
				new Point3D(500, 180, 500), new Color(0, 0, 80), new Material(1, 0, 0, 0, 0)));
		scene.addGeometry(new Triangle(new Point3D(-500, 180, -500), new Point3D(500, 180, -500),
				new Point3D(500, 180, 500), new Color(0, 0, 80), new Material(1, 0, 0, 0, 0)));

		// scene.addGeometry(new Geometries(list));
		List<LightSource> lightSourcehs = new ArrayList<LightSource>();
		lightSourcehs.add(new PointLight(new Color(100, 100, 100), new Point3D(-50, 0, 50), 1, 0, 0));// ,1,0.01,0.01));
		lightSourcehs.add(new DirectionalLight(new Color(100, 100, 100), new Vector(1, 0.2, -1)));
		scene.set_lights(lightSourcehs);
		ImageWriter imageWriter = new ImageWriter("myImage5", 500, 500, 500, 500);
		Renderer myRenderer = new Renderer(scene, imageWriter);
		myRenderer.renderImage();
		// myRenderer.printGrid(50);
		myRenderer.get_imageWriter().writeToimage();
	}

	@Test
	void test4() {

		Scene scene = new Scene("my scene");
		scene.set_background(new Color(0, 5, 0));
		scene.set_ambientLight(new AmbientLight(new Color(0, 0, 50), 1));
		scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)));
		scene.set_screenDistance(50);

		// Geometries
		scene.addGeometry(
				new Sphere(10, new Point3D(175, 75, -100), new Color(100, 0, 0), new Material(2, 500, 150, 0, 0)));
		scene.addGeometry(
				new Sphere(10, new Point3D(150, 120, -100), new Color(100, 0, 0), new Material(2, 500, 150, 0, 0)));
		scene.addGeometry(
				new Sphere(80, new Point3D(0, 100, 0), new Color(100, 0, 0), new Material(2, 500, 150, 0, 0)));
		scene.addGeometry(new Triangle(new Point3D(-500, 180, 500), new Point3D(-500, 180, -500),
				new Point3D(500, 180, 500), new Color(0, 0, 80), new Material(1, 0, 0, 0, 0)));
		scene.addGeometry(new Triangle(new Point3D(-500, 180, -500), new Point3D(500, 180, -500),
				new Point3D(500, 180, 500), new Color(0, 0, 80), new Material(1, 0, 0, 0, 0)));

		List<LightSource> lightSourcehs = new ArrayList<LightSource>();
		lightSourcehs.add(new DirectionalLight(new Color(100, 100, 100), new Vector(1, 0.2, -1)));
		scene.set_lights(lightSourcehs);
		ImageWriter imageWriter = new ImageWriter("myImage6", 500, 500, 500, 500);
		Renderer myRenderer = new Renderer(scene, imageWriter);
		myRenderer.renderImage();
		// myRenderer.printGrid(50);
		myRenderer.get_imageWriter().writeToimage();
	}

	@Test
	void test5() {

		Scene scene = new Scene("my scene");
		scene.set_background(new Color(0, 5, 0));
		scene.set_ambientLight(new AmbientLight(new Color(0, 0, 50), 1));
		scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)));
		scene.set_screenDistance(50);
		// ArrayList<Geometry> list =new ArrayList<>();

		// Geometry
		// scene.addGeometry(new Sphere(10 ,new Point3D(175, 75, -100), new Color(20,
		// 20, 50), new Material(2, 500, 130)));
		scene.addGeometry(
				new Sphere(10, new Point3D(-180, 90, -100), new Color(200, 0, 0), new Material(2, 50, 20, 0, 0)));
		scene.addGeometry(
				new Sphere(80, new Point3D(0, 100, 0), new Color(150, 0, 0), new Material(2, 500, 150, 0, 0)));
		scene.addGeometry(new Triangle(new Point3D(0, 100, 250), new Point3D(-250, 100, -250),
				new Point3D(250, 100, -250), new Color(80, 80, 80), new Material(1, 250, 250, 0, 0)));

		scene.addGeometry(new Triangle(new Point3D(-500, 180, 500), new Point3D(-500, 180, -500),
				new Point3D(500, 180, 500), new Color(0, 0, 80), new Material(1, 0, 0, 0, 0)));
		scene.addGeometry(new Triangle(new Point3D(-500, 180, -500), new Point3D(500, 180, -500),
				new Point3D(500, 180, 500), new Color(0, 0, 80), new Material(1, 0, 0, 0, 0)));

		// scene.addGeometry(new Geometries(list));
		List<LightSource> lightSourcehs = new ArrayList<LightSource>();
		// 2 Source of light , 2 PointLight
		lightSourcehs.add(new PointLight(new Color(100, 100, 100), new Point3D(-50, 10, 30), 1, 0, 0));// ,1,0.01,0.01));
		lightSourcehs.add(new PointLight(new Color(100, 100, 100), new Point3D(50, 10, -35), 1, 0, 0));// ,1,0.01,0.01));
		// lightSourcehs.add(new DirectionalLight(new Color(100, 100, 100),new
		// Vector(1,0.2,-1)));
		scene.set_lights(lightSourcehs);
		ImageWriter imageWriter = new ImageWriter("myImage7", 500, 500, 500, 500);
		Renderer myRenderer = new Renderer(scene, imageWriter);
		myRenderer.renderImage();
		// myRenderer.printGrid(50);
		myRenderer.get_imageWriter().writeToimage();
	}

	@Test
	void reflectionTest() {
		Scene scene = new Scene("endTest");

		Geometries geometries = new Geometries();
		List<LightSource> light = new ArrayList<LightSource>();
		Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
		scene.set_geometries(geometries);
		scene.set_lights(light);
		scene.set_camera(camera);
		scene.set_ambientLight(new AmbientLight(new Color(100, 100, 100), 0.5));
		scene.set_background(new Color(java.awt.Color.BLACK));
		scene.set_screenDistance(50);

		Material reflectMaterial = new Material(0, 0, 0, 1, 0);
		Material matt = new Material(1, 0.3, 200, 0, 0);
		
		//front well
		scene.addGeometry(new Rectangle(new Point3D(350, 50, -80), new Point3D(150, 50, -80),
				new Point3D(150, -300, -80), new Point3D(350, -300, -80), new Color(150,0,0), matt));
		
		scene.addGeometry(new Rectangle(new Point3D(350, 300, -80), new Point3D(150, 300, -80),
				new Point3D(150, 150, -80), new Point3D(350, 150, -80), new Color(150,0,0), matt));
		
		scene.addGeometry(new Rectangle(new Point3D(200, 150, -80), new Point3D(150, 150, -80),
				new Point3D(150, 50, -80), new Point3D(200, 50, -80), new Color(150,0,0), matt));
		
		scene.addGeometry(new Rectangle(new Point3D(350, 150, -80), new Point3D(300, 150, -80),
				new Point3D(300, 50, -80), new Point3D(350, 50, -80), new Color(150,0,0), matt));

		//behind well
		scene.addGeometry(new Rectangle(new Point3D(350, 300, -180), new Point3D(150, 300, -180),
				new Point3D(150, -300, -180), new Point3D(350, -300, -180), new Color(225,0,0), matt));

		//left well
		scene.addGeometry(new Rectangle(new Point3D(150, 300, -80), new Point3D(150, 300, -180),
				new Point3D(150, 100, -180), new Point3D(150, 100, -80), new Color(150,0,100), matt));

		scene.addGeometry(new Rectangle(new Point3D(150, 100, -155), new Point3D(150, 100, -180),
				new Point3D(150, -300, -180), new Point3D(150, -300, -155), new Color(150,0,100), matt));

		scene.addGeometry(new Rectangle(new Point3D(150, 100, -80), new Point3D(150, 100, -105),
				new Point3D(150, -300, -105), new Point3D(150, -300, -80), new Color(150,0,100), matt));

		//right well
		scene.addGeometry(new Rectangle(new Point3D(350, 300, -180), new Point3D(350, 300, -80),
				new Point3D(350, -300, -80), new Point3D(350, -300, -180), new Color(225,0,0), matt));
		
		//roof
		scene.addGeometry(new Triangle(new Point3D(150, 300, -180), new Point3D(150, 300, -80),
				new Point3D(250, 550, -130),  new Color(180,0,0), matt));
		scene.addGeometry(new Triangle(new Point3D(350, 300, -180), new Point3D(350, 300, -80),
				new Point3D(250, 550, -130),  new Color(180,0,0), matt));
		scene.addGeometry(new Triangle(new Point3D(150, 300, -80), new Point3D(350, 300, -80),
				new Point3D(250, 550, -130),  new Color(180,0,0), matt));
		scene.addGeometry(new Triangle(new Point3D(150, 300, -180), new Point3D(350, 300, -180),
				new Point3D(250, 550, -130),  new Color(180,0,0), matt));
		
		//floor
		scene.addGeometry(new Rectangle(new Point3D(350, -300, -180), new Point3D(150, -300, -180),
				new Point3D(150, -300, -80), new Point3D(350, -300, -80), new Color(0,0,0), new Material(1, 0, 0, 0.5, 0)));
		
		//reflected tray - see
		scene.addGeometry(new Rectangle(new Point3D(350, -120, -280), new Point3D(-150, -120, -280),
				new Point3D(-150, -120, 0), new Point3D(350, -120, 0), new Color(50,50,220), reflectMaterial));
		//Continent
		//scene.addGeometry(new Rectangle(new Point3D(480, -120, -280), new Point3D(350, -120, -280),
			//	new Point3D(350, -120, 0), new Point3D(480, -120, 0), new Color(java.awt.Color.DARK_GRAY), matt));
		
		light.add(new PointLight(new Color(150,150,0), new Point3D(200, 250, -130), 1, 0, 0));
		
		
		ImageWriter imageWriter = new ImageWriter("reflection test", 500, 500, 500, 500);
		Renderer testRender = new Renderer(scene,imageWriter);
		testRender.renderImage();
		testRender.get_imageWriter().writeToimage();
	}


	@Test
	void refractionTest() {
		Scene scene = new Scene("endTest");

		Geometries geometries = new Geometries();
		List<LightSource> light = new ArrayList<LightSource>();
		Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
		scene.set_geometries(geometries);
		scene.set_lights(light);
		scene.set_camera(camera);
		scene.set_ambientLight(new AmbientLight(new Color(100, 100, 100), 0.5));
		scene.set_background(new Color(java.awt.Color.BLACK));//0, 0, 255).add(new Color(java.awt.Color.WHITE).scale(1 / 150.0)));
		scene.set_screenDistance(50);
		
		Material transparencyMaterial = new Material(1, 0, 0, 0, 1);
		Material matt = new Material(1, 0.3, 200, 0, 0);
		
		//front well
		scene.addGeometry(new Rectangle(new Point3D(350, 50, -80), new Point3D(150, 50, -80),
				new Point3D(150, -300, -80), new Point3D(350, -300, -80), new Color(150,0,0), matt));
		
		scene.addGeometry(new Rectangle(new Point3D(350, 300, -80), new Point3D(150, 300, -80),
				new Point3D(150, 150, -80), new Point3D(350, 150, -80), new Color(150,0,0), matt));
		
		scene.addGeometry(new Rectangle(new Point3D(200, 150, -80), new Point3D(150, 150, -80),
				new Point3D(150, 50, -80), new Point3D(200, 50, -80), new Color(150,0,0), matt));
		
		scene.addGeometry(new Rectangle(new Point3D(350, 150, -80), new Point3D(300, 150, -80),
				new Point3D(300, 50, -80), new Point3D(350, 50, -80), new Color(150,0,0), matt));

		//behind well
		scene.addGeometry(new Rectangle(new Point3D(350, 300, -180), new Point3D(150, 300, -180),
				new Point3D(150, -300, -180), new Point3D(350, -300, -180), new Color(225,0,0), matt));

		//left well
		scene.addGeometry(new Rectangle(new Point3D(150, 300, -80), new Point3D(150, 300, -180),
				new Point3D(150, 100, -180), new Point3D(150, 100, -80), new Color(150,0,100), matt));

		scene.addGeometry(new Rectangle(new Point3D(150, 100, -155), new Point3D(150, 100, -180),
				new Point3D(150, -300, -180), new Point3D(150, -300, -155), new Color(150,0,100), matt));

		scene.addGeometry(new Rectangle(new Point3D(150, 100, -80), new Point3D(150, 100, -105),
				new Point3D(150, -300, -105), new Point3D(150, -300, -80), new Color(150,0,100), matt));

		//right well
		scene.addGeometry(new Rectangle(new Point3D(350, 300, -180), new Point3D(350, 300, -80),
				new Point3D(350, -300, -80), new Point3D(350, -300, -180), new Color(225,0,0), matt));
		
		//roof
		scene.addGeometry(new Triangle(new Point3D(150, 300, -180), new Point3D(150, 300, -80),
				new Point3D(250, 550, -130),  new Color(180,0,0), matt));
		scene.addGeometry(new Triangle(new Point3D(350, 300, -180), new Point3D(350, 300, -80),
				new Point3D(250, 550, -130),  new Color(180,0,0), matt));
		scene.addGeometry(new Triangle(new Point3D(150, 300, -80), new Point3D(350, 300, -80),
				new Point3D(250, 550, -130),  new Color(180,0,0), matt));
		scene.addGeometry(new Triangle(new Point3D(150, 300, -180), new Point3D(350, 300, -180),
				new Point3D(250, 550, -130),  new Color(180,0,0), matt));
		
		//floor
		scene.addGeometry(new Rectangle(new Point3D(350, -300, -180), new Point3D(150, -300, -180),
				new Point3D(150, -300, -80), new Point3D(350, -300, -80), new Color(0,0,0), new Material(1, 0, 0, 0.5, 0)));
		
		//reflected tray - see
		scene.addGeometry(new Rectangle(new Point3D(350, -120, -280), new Point3D(-150, -120, -280),
				new Point3D(-150, -120, 0), new Point3D(350, -120, 0), new Color(50,50,220), transparencyMaterial));
		//Continent
		scene.addGeometry(new Rectangle(new Point3D(480, -120, -280), new Point3D(350, -120, -280),
				new Point3D(350, -120, 0), new Point3D(480, -120, 0), new Color(java.awt.Color.DARK_GRAY), matt));
		
		light.add(new PointLight(new Color(150,150,0), new Point3D(200, 250, -130), 1, 0, 0.000001));
		
		
		ImageWriter imageWriter = new ImageWriter("refraction test", 500, 500, 500, 500);
		Renderer testRender = new Renderer(scene,imageWriter);
		testRender.renderImage();
		testRender.get_imageWriter().writeToimage();
	}

	
	@Test
	void finelTest() {
		Scene scene = new Scene("reflectionTest");

		Geometries geometries = new Geometries();
		List<LightSource> light = new ArrayList<LightSource>();
		Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
		scene.set_geometries(geometries);
		scene.set_lights(light);
		scene.set_camera(camera);
		scene.set_ambientLight(new AmbientLight(new Color(100, 100, 100), 0.5));
		scene.set_background(new Color(0, 40, 0).add(new Color(java.awt.Color.WHITE).scale(1 / 100.0)));
		scene.set_screenDistance(200);
		
		// -------- matrial
		Material reflectMaterial = new Material(1, 0.3, 1, 1, 0);
		Material shakuf = new Material(1, 0.3, 1, 0.02, 1);
		Material matt = new Material(1, 0.3, 1, 0.1, 0);
	
		
		// ------  geometris
		scene.addGeometry(new Rectangle(new Point3D(50, -100, -200), new Point3D(-90, -100, -150),
				new Point3D(-90, 100, -150), new Point3D(50, 100, -200), new Color(0,0,80), reflectMaterial));
		
		scene.addGeometry(new Rectangle(new Point3D(170, -100, -150), new Point3D(50, -100, -200),
			new Point3D(50, 100, -200), new Point3D(170, 100, -150), new Color(0,0,80), reflectMaterial));
			
		scene.addGeometry(new Sphere(15, new Point3D(50, 20, -100), new Color(100, 0, 100), shakuf));
		scene.addGeometry(new Sphere(5, new Point3D(50,20,-100), new Color(180, 0,0),matt)); 
		scene.addGeometry(new Sphere(10, new Point3D(20,50,-130), new Color(0, 255,20),matt));
		scene.addGeometry(new Sphere(10, new Point3D(20,-10,-110), new Color(0, 0,255),matt));
		scene.addGeometry(new Sphere(19, new Point3D(50, 20, -100), new Color(120, 0, 120), shakuf));	
		scene.addGeometry(new Sphere(5, new Point3D(85,20,-100), new Color(180, 0,100),reflectMaterial));
		scene.addGeometry(new Sphere(10, new Point3D(80,-45,-95), new Color(0, 255,255),reflectMaterial));
		scene.addGeometry(new Sphere(6, new Point3D(75,-40,-70), new Color(150, 255,255),matt));
		scene.addGeometry(new Sphere(6, new Point3D(-105,-100,-100), new Color(0, 0,0),matt));
		scene.addGeometry(new Sphere(13, new Point3D(-105,-100,-100), new Color(100, 80,0),shakuf));
		
		
		// --------  light
		light.add(new PointLight(new Color(255, 255, 255), new Point3D(-105, -100, -100), 1, 0, 0.00001));
		light.add(new SpotLight(new Color(255, 255, 255), new Point3D(0, 0, 0), 1, 0.2, 0.2, new Vector(30, 20, -100)));
		light.add(new DirectionalLight(new Color(100, 100, 100), new Vector(-15, -20, -15))); // -50,-20,-15
		
		ImageWriter imageWriter = new ImageWriter("finelTest", 500, 500, 500, 500);
		Renderer testRender = new Renderer(scene,imageWriter);
		testRender.renderImage();
		testRender.get_imageWriter().writeToimage();
	}


}
