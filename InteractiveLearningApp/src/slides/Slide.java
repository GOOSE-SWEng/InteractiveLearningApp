package slides;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import media.Graphics2D;
import media.Shape;
import userInterface.ToolBar;
import media.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

//THIS IS THE SLIDE OBJECT

public class Slide {
	SubScene toolBar;// = ToolBar.createToolBar(1280);
	private Scene slide;
	private GridPane  gp = new GridPane();
	private int width;
	private int height;
	private double xOff;
	private double yOff;
	/////////////////////////////
	SubScene test;
	Graphics2D graphics;
	public Slide(Stage mainStage, int width, int height, double xOffset, double yOffset) {
		this.width = width;
		toolBar = ToolBar.createToolBar(width);
		this.height = height;
		xOff = xOffset;
		yOff = yOffset;
		///////// taken from the start screen class
		toolBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOff = event.getSceneX();
				yOff = event.getSceneY();
			}
		});
		
		//Move window with mouse
		toolBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainStage.setX(event.getScreenX() - xOff);
				mainStage.setY(event.getScreenY() - yOff);	
			}
		});
		/////////
	}
	
	public Scene update(){
		
		graphics = new Graphics2D(width,height);
		
		graphics.addOval(Color.RED,Color.BLUE,100,200,300,300);// creates oval, can also be used to create a circle
		
		graphics.addPoly(Color.MAGENTA,Color.ORANGE);//creates a new blank polygon
		graphics.addPoint(200,400);//adds sides to the polygon
		graphics.addPoint(400,400);
		graphics.addPoint(400,600);
		
		graphics.addPoly(Color.MAGENTA,Color.ORANGE);
		graphics.addPoint(700,400);
		graphics.addPoint(900,400);
		graphics.addPoint(800,600);

		test = graphics.Update(width,height);//update function returns subscene
		///////////////////////////////
		gp.add(toolBar,0,0);
		gp.add(test,0,1);
		
		slide = new Scene(gp);
		
		return slide;
	}
}
