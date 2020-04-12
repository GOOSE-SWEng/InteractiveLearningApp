package media;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GraphicsHandler {
	private StackPane pane;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<String> ids = new ArrayList<String>();
	private int paneWidth;
	private int paneHeight;
	public GraphicsHandler(StackPane targetPane) {
		this.pane = targetPane;
		paneWidth = (int)pane.getMinWidth();
		paneHeight = (int)pane.getMinHeight();
		System.out.println(paneWidth);
		System.out.println(paneHeight);
	}
	public void drawLine(float xStart, float xEnd, float yStart, float yEnd, String lineColour, String id) {
		Color lc = Color.web(lineColour);
		int lineWidth = 5;
		Shape shape = new Shape(lc,lc,paneWidth,paneHeight,lineWidth);
		shape.addPoint((int)(paneWidth*xStart),(int)(paneHeight*yStart));
		shape.addPoint((int)(paneWidth*xEnd),(int)(paneHeight*yEnd));
		shapes.add(shape);
		shape.create();
		if (addId(id)) {
			pane.getChildren().add(shape.get());
		}
		else {};
	}
	public void drawRectangle(float xStart, float yStart, float width, float height, String fillColour, String id) {
		Color fc = Color.web(fillColour);
		Shape shape = new Shape(fc,fc,paneWidth,paneHeight,0);
		shape.addPoint((int)(paneWidth*xStart),(int)(paneHeight*yStart));
		shape.addPoint((int)(paneWidth*(xStart+width)),(int)(paneHeight*yStart));
		shape.addPoint((int)(paneWidth*(xStart+width)),(int)(paneHeight*(yStart+height)));
		shape.addPoint((int)(paneWidth*xStart),(int)(paneHeight*(yStart+height)));
		shapes.add(shape);
		shape.create();
		if (addId(id)) {
			pane.getChildren().add(shape.get());
		}
		else {};
		
	}
	public void drawRectangle(float xStart, float yStart, float width, float height, String id, float shading_x1, float shading_y1, String shading_colour1, float shading_x2, float shading_y2, String shading_colour2, Boolean shading_cyclic) {
		Color c1 = Color.web(shading_colour1);
		Color c2 = Color.web(shading_colour2);
		Shape shape = new Shape(paneWidth,paneHeight,0,c1,c2,(int)(paneWidth*shading_x1),(int)(paneHeight*shading_y1),(int)(paneWidth*shading_x2),(int)(paneHeight*shading_y2),shading_cyclic);
		shape.addPoint((int)(paneWidth*xStart),(int)(paneHeight*yStart));
		shape.addPoint((int)(paneWidth*(xStart+width)),(int)(paneHeight*yStart));
		shape.addPoint((int)(paneWidth*(xStart+width)),(int)(paneHeight*(yStart+height)));
		shape.addPoint((int)(paneWidth*xStart),(int)(paneHeight*(yStart+height)));
		shapes.add(shape);
		shape.create();
		if (addId(id)) {
			pane.getChildren().add(shape.get());
		}
		else {};
	}
	public void drawOval(float xStart, float yStart, float width, float height, String fillColour, String id) {
		Color fc = Color.web(fillColour);
		Shape shape = new Shape(fc,fc,paneWidth,paneHeight,0);
		shape.drawOval((int)(paneWidth*width),(int)(paneHeight*height),(int)(paneWidth*xStart),(int)(paneHeight*yStart));
		shapes.add(shape);
		shape.create();
		if (addId(id)) {
			pane.getChildren().add(shape.get());
		}
		else {};
		
	}
	public void drawOval(float xStart, float yStart, float width, float height, String id, float shading_x1, float shading_y1, String shading_colour1, float shading_x2, float shading_y2, String shading_colour2, Boolean shading_cyclic) {
		Color c1 = Color.web(shading_colour1);
		Color c2 = Color.web(shading_colour2);
		Shape shape = new Shape(paneWidth,paneHeight,0,c1,c2,(int)(paneWidth*shading_x1),(int)(paneHeight*shading_y1),(int)(paneWidth*shading_x2),(int)(paneHeight*shading_y2),shading_cyclic);
		shape.drawOval((int)(paneWidth*width),(int)(paneHeight*height),(int)(paneWidth*xStart),(int)(paneHeight*yStart));
		shapes.add(shape);
		shape.create();
		if (addId(id)) {
			pane.getChildren().add(shape.get());
		}
		else {};
		
	}
	public void removeGraphic(String id) {
		if(id == "invalid id assigned") {
			return;
		}
		int i = ids.indexOf(id);
		if (i>=0) {
			pane.getChildren().remove(shapes.get(i).get());
		shapes.get(i).destroy();
		}
		else {}
	}
	
	private Boolean addId(String id) {
		int length = ids.size();
		Boolean unique = true;
		for (int i=0;i<length;i++) {
			if(id == ids.get(i)) {
				unique = false;
				break;
			}
			else {}
		}
		if (unique == true) {
			ids.add(id);
		}
		else if(unique == false) {
			ids.add("invalid id assigned");
		}
		return(unique);
	}
}
