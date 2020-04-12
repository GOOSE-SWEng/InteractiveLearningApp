package media;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Graphics2D {
	int height;
	int width;
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	ArrayList<Group> sheps = new ArrayList<Group>();
	int currentPoly = 0;
	StackPane sp = new StackPane();
	
	public Graphics2D(int width,int height,ArrayList<Shape> shapes) {
		this.height = height;
		this.width = width;
		this.shapes = shapes;
	}
	
	public void addOval(Color LineColour, Color fillColour, int oWidth, int oHeight, int cX, int cY) {
		Shape temp = new Shape(LineColour,fillColour,width,height,5);
		temp.drawOval(oWidth,oHeight,cX,cY);
		shapes.add(temp);
	}
	
	public void addPoly(Color LineColour,Color fillColour) {
		Shape temp = new Shape(LineColour,fillColour,width,height,5);
		shapes.add(temp);
		currentPoly = shapes.size()-1;
	}
	
	public void addPoint(double x, double y) {
		shapes.get(currentPoly).addPoint(x,y);
	}
	
	public SubScene Update(double width, double height) {
		StackPane sp2 = new StackPane();
		//Canvas canvas2 = new Canvas(1280,720);
		//sp2.getChildren().add(canvas2);
		//sp2.setPrefSize(1280,720);
		sp2.setMinSize(1280,684);
		GraphicsHandler gh = new GraphicsHandler(sp2);
		gh.drawLine((float)0.5,(float)0.5,0,1,"#ff00ff","dave");
		gh.drawLine(0,1,(float)0.5,(float)0.5,"#ff00ff","dave4");
		gh.drawOval((float)0.51,0,(float)0.49,(float)0.49,"#ff0000","dave2");
		gh.drawRectangle(0,(float)0.51,(float)0.49,(float)0.49,"dave3",0,(float)0.5,"#782144",(float)0.5,1,"#1384d4",false);
		gh.drawRectangle(0,0,(float)0.49,(float)0.49,"#663366","dave5");
		gh.drawOval((float)0.51,(float)0.51,(float)0.49,(float)0.49,"dave6",0,0,"ff0000",(float)0.05,0,"0000ff",true);
		gh.removeGraphic("invalid id assigned");
		Canvas canvas = new Canvas(width, height);
		sp.getChildren().add(canvas);
		
		
		for (int i = 0; i<shapes.size();i++) {
			sheps.add(shapes.get(i).get());
		}
		for (int i = 0; i<sheps.size(); i++) {
			sp.getChildren().add(sheps.get(i));
		}
		
		SubScene Window = new SubScene(sp2,width,height);
		return Window;
	}
}
