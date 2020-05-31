package media;


import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
/**
 * Class for drawing the 2D shapes
 * @author - Alex Marchant
 * @version - 1.0
 * @date 28/04/20
 */
public class Shape {
	private Color fill = null;
	private Color line = null;
	//to be returned
	private Group group;
	private Canvas canvas; 
	private GraphicsContext gc;
	//points for the polygons
	private List<Double> pointsX = new ArrayList<Double>(); 
	private List<Double> pointsY = new ArrayList<Double>();
	private boolean oval = false;
	//oval height
	private int ovalHeight = 0;
	private int ovalWidth = 0;
	//oval reference coordinates
	private int ovalCentreX = 0;
	private int ovalCentreY = 0;
	//frame size
	private float width;
	private float height;
	private int startTime = 0;
	private int endTime;
	private int slideNumber;

	/** Constructor for a solid colour shape */
	public Shape(Color lineColour, Color fillColour,int w, int h, int lw, 
			     int startTime, int endTime, int slideNumber) { 
		group = new Group();
		fill = fillColour;
		line = lineColour;
		width = w;
		height = h;
		canvas = new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(fill);
		gc.setStroke(line);
		gc.setLineWidth(lw);
		this.startTime = startTime;
		this.endTime = endTime;
		this.slideNumber = slideNumber;
	}
	
	/**constructor for a shape with a colour gradient */
	public Shape(int w, int h, int lw, Color c1, Color c2, float c1x, float c1y, 
			     float c2x, float c2y, Boolean Cyclical, int startTime, 
			     int endTime, int slideNumber) { 
		group = new Group();
		width = w;
		height = h;
		canvas = new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
		this.startTime = startTime;
		this.endTime = endTime;
		this.slideNumber = slideNumber;
		
		//sets up a cyclical gradient pattern
		if (Cyclical == true) {
			Stop[] stops = new Stop[] {new Stop(0,c1), new Stop(1,c2)};
			LinearGradient lg = new LinearGradient(c1x,c1y,c2x,c2y,false,CycleMethod.REFLECT,stops);
			gc.setFill(lg);
			gc.setStroke(Color.TRANSPARENT);
		}
		//standard linear gradient
		else {
			Stop[] stops = new Stop[] {new Stop(0,c1), new Stop(1,c2)};
			LinearGradient lg = new LinearGradient(c1x,c1y,c2x,c2y,false,CycleMethod.NO_CYCLE,stops);
			gc.setFill(lg);
			gc.setStroke(Color.TRANSPARENT);
		}
	}
	
	/**adds point to the polygon */
	public void addPoint(double x, double y) {
		pointsX.add(x);
		pointsY.add(y);
	}
	
	/** draws an oval, cx,cy correspond to distance from the top left corner to enclosing box */
	public void drawOval (int width, int height, int cx, int cy) {
		// changes shape from polygon to oval
		oval = true; 
		ovalHeight = height;
		ovalWidth = width;
		ovalCentreX = cx;
		ovalCentreY = cy;
	}
	
	/** method to create shapes */
	public void create() {
		if (oval == true) {
			gc.fillOval(ovalCentreX,ovalCentreY,ovalWidth,ovalHeight);
			gc.strokeOval(ovalCentreX,ovalCentreY,ovalWidth,ovalHeight);
			group.getChildren().add(canvas);
		}
		// used to create an array of points from an arraylist
		else {
			double[] pointX = new double[pointsX.size()];
			double[] pointY = new double[pointsY.size()];
			
			//adding arraylist points to an array
			for (int i = 0; i<pointsX.size(); i++) {
				pointX[i] = pointsX.get(i);
			}
			
			for (int i = 0; i<pointsY.size(); i++) {
				pointY[i] = pointsY.get(i);
			}
			
			//creates a polygon outline
			gc.strokePolygon(pointX,pointY,pointsX.size());
			//creates the polygon solid
			gc.fillPolygon(pointX,pointY,pointsX.size());
			//adds canvas to the group
			group.getChildren().add(canvas);
		}else {
			//System.err.println("Tried to add already drawn shape");
		}
		
	}
	public Group get() {
		return group;
	}

	/** removes the canvas from the group */
	public void destroy() {
		group.getChildren().remove(canvas);
	}
	
	public int getSlideNumber() {
		return(slideNumber);
	}
	
	public int getStartTime() {
		return(startTime);
	}
	
	public int getEndTime() {
		return(endTime);
	}
}
