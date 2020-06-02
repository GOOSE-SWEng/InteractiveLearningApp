package media;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import main.InteractiveLearningApp;

/**
 * Class for creating the 2D graphics and adding to the slides
 * @author Alex Marchant
 * @modificatons by Tom Pound
 * @version - 1.3
 * @date - 01/06/20
 *
 */
public class Graphics2D {
	//global variables
	int paneHeight;
	int paneWidth;
	public ArrayList<Shape> shapes = new ArrayList<Shape>();
	int currentPoly = 0;
	StackPane sp;
	
	// constructor
	public Graphics2D(ArrayList<Shape> shapes, StackPane sp) {
		this.paneHeight = InteractiveLearningApp.getStageHeight();
		this.paneWidth = InteractiveLearningApp.getStageWidth();
		// clones arrayList
		this.shapes = shapes; 
		sp.setPickOnBounds(false);
		sp.setAlignment(Pos.TOP_LEFT);
		this.sp = sp;
	}
	
	//convert percentage values to definite 
	public float scaleX(float p) {
		return((p*InteractiveLearningApp.getStageWidth())/100);
	}
	public float scaleY(float p) {
		return((p*InteractiveLearningApp.getStageHeight())/100);
	}
	
	//draw a solid colour line
	public void registerLine(float xStart, float xEnd, float yStart, float yEnd, String lineColour, 
			                 int startTime, int endTime, int slideNumber) {
		//converts the hash map to a colour
		Color lc = Color.web(lineColour);
		int lineWidth = 5;
		// creates shape object
		Shape shape = new Shape(lc,lc,paneWidth,paneHeight,lineWidth, startTime, endTime, slideNumber);
		//creates start point of the line
		shape.addPoint(scaleX(xStart),scaleY(yStart));
		// creates end point of the line
		shape.addPoint(scaleX(xEnd),scaleY(yEnd));
		// add shape to the array list
		shapes.add(shape);
	}
	
	/**draw rectangle with solid colour */
	public void registerRectangle(float xStart, float yStart, float width, float height, String fillColour, 
			                      String id, int startTime, int endTime, int slideNumber) {
		Color fc = Color.web(fillColour);
		//creates the shape object for a solid colour shape
		Shape shape = new Shape(fc,fc,paneWidth,paneHeight,0, startTime, endTime, slideNumber);
		//add the 4 points to the rectangle
		shape.addPoint(scaleX(xStart),scaleY(yStart));
		shape.addPoint(scaleX(xStart+width),scaleY(yStart));
		shape.addPoint(scaleX(xStart+width),scaleY(yStart+height));
		shape.addPoint(scaleX(xStart),scaleY(yStart+height));
		shapes.add(shape);
	}
	
	/**draw rectangle with gradient fill */
	public void registerRectangle(float xStart, float yStart, float width, float height, float shading_x1, 
			                      float shading_y1, String shading_colour1, float shading_x2, float shading_y2, 
			                      String shading_colour2, Boolean shading_cyclic, int startTime, int endTime, 
			                      int slideNumber) {
		Color c1 = Color.web(shading_colour1);
		Color c2 = Color.web(shading_colour2);
		//creates shape object for gradient fill shape
		Shape shape = new Shape(paneWidth, paneHeight, 0, c1, c2, scaleX(shading_x1), scaleY(shading_y1), scaleX(shading_x2), scaleY(shading_y2),
				                shading_cyclic, startTime, endTime, slideNumber);
		shape.addPoint(scaleX(xStart),scaleY(yStart));
		shape.addPoint(scaleX(xStart+width),scaleY(yStart));
		shape.addPoint(scaleX(xStart+width),scaleY(yStart+height));
		shape.addPoint(scaleX(xStart),scaleY(yStart+height));
		shapes.add(shape);

	}
	/**draw solid colour oval */
	public void registerOval(float xStart, float yStart, float width, float height, String fillColour, 
			                 int startTime, int endTime, int slideNumber) {
		Color fc = Color.web(fillColour);
		Shape shape = new Shape(fc,fc,paneWidth,paneHeight,0, startTime, endTime, slideNumber);
		//oval constructor
		shape.drawOval((int)scaleX(width),(int)scaleY(height),(int)scaleX(xStart),(int)scaleY(yStart));
		shapes.add(shape);
	}
	
	/**draw gradient fill oval */
	public void registerOval(float xStart, float yStart, float width, float height, float shading_x1, 
			                 float shading_y1, String shading_colour1, float shading_x2, float shading_y2, 
			                 String shading_colour2, Boolean shading_cyclic, int startTime, int endTime, 
			                 int slideNumber) {
		Color c1 = Color.web(shading_colour1);
		Color c2 = Color.web(shading_colour2);
		Shape shape = new Shape(paneWidth, paneHeight, 0, c1, c2, scaleX(shading_x1), scaleY(shading_y1), scaleX(shading_x2), scaleY(shading_y2),
				                shading_cyclic, startTime, endTime, slideNumber);
		shape.drawOval((int)scaleX(width),(int)scaleY(height),(int)scaleX(xStart),(int)scaleY(yStart));
		shapes.add(shape);
	}
	//remove the object from the slide stackpane
	public void remove(int i) {
		//checks to see if the object is on the stack pane
		if (sp.getChildren().contains(shapes.get(i).get())) {
			sp.getChildren().remove(shapes.get(i).get());
		}
	}
	//used to add the shape media object to the stackpane
	public void add(int i) {
		//checks to see if the media object is already present on the stackpane
		if (sp.getChildren().contains(shapes.get(i).get()) == false) {
			shapes.get(i).create();
			sp.getChildren().add(shapes.get(i).get());
		}
	}
}
		
		