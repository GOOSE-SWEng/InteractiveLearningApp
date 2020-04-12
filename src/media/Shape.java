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

public class Shape {
	Polygon polygon = new Polygon();
	Color fill = null;
	Color line = null;
	int sideNo;
	Group group;// = new Group();
	Canvas canvas; 
	GraphicsContext gc; 
	List<Double> pointsx = new ArrayList<Double>();
	List<Double> pointsy = new ArrayList<Double>();
	boolean oval = false;
	int oheight = 0;
	int owidth = 0;
	int ocx = 0;
	int ocy = 0;
	float width;
	float height;

	
	public Shape(Color lineColour, Color fillColour,int w, int h, int lw) { // constructor for a solid colour shape
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
	}
	public Shape(int w, int h, int lw, Color c1, Color c2, float c1x, float c1y, float c2x, float c2y, Boolean Cyclical) { //constructor for a shape with a colour gradient
		group = new Group();
		width = w;
		height = h;
		canvas = new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
		
		if (Cyclical == true) {//sets up a cyclical gradient pattern
			Stop[] stops = new Stop[] {new Stop(0,c1), new Stop(1,c2)};
			LinearGradient lg = new LinearGradient(c1x,c1y,c2x,c2y,false,CycleMethod.REFLECT,stops);
			gc.setFill(lg);
		}
		else {//standard linear gradient
			Stop[] stops = new Stop[] {new Stop(0,c1), new Stop(1,c2)};
			LinearGradient lg = new LinearGradient(c1x,c1y,c2x,c2y,false,CycleMethod.NO_CYCLE,stops);
			gc.setFill(lg);
		}
	}
	
	public void addPoint(double x, double y) {//adds point to the polygon
		pointsx.add(x);
		pointsy.add(y);
	}
	
	public void drawOval (int width, int height, int cx, int cy) {
		oval = true;
		oheight = height;
		owidth = width;
		ocx = cx;
		ocy = cy;
	}
	
	public void create() {
		if (oval == true) {
			gc.fillOval(ocx,ocy,owidth,oheight);
			gc.strokeOval(ocx,ocy,owidth,oheight);
			
			group.getChildren().add(canvas);
		}
		else {
			double[] pointx = new double[pointsx.size()];// used to create an array of points from an arraylist
			double[] pointy = new double[pointsy.size()];
			
			//adding arraylist points to an array
			for (int i = 0; i<pointsx.size(); i++) {
				pointx[i] = pointsx.get(i);
			}
			for (int i = 0; i<pointsy.size(); i++) {
				pointy[i] = pointsy.get(i);
			}
			
			gc.strokePolygon(pointx,pointy,pointsx.size());//creates a polygon outline
			gc.fillPolygon(pointx,pointy,pointsx.size());//creates the polygon solid
			group.getChildren().add(canvas);
		}
		
	}
	public Group get() {
		return group;
	}

	public void destroy() {//removes the canvas from the group
		group.getChildren().remove(canvas);
	}
}
