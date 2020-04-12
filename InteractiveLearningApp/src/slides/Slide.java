package slides;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Group;
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
	private VideoLayer videoLayer;
	private AudioLayer audioLayer;
	private ImageLayer imageLayer;
	private TextLayer textLayer;
	private Graphics2D graphics2D;
	private Graphics3DLayer graphics3DLayer;
	private Scene slide;
	private GridPane  gp = new GridPane();
	private int width;
	private int height;
	private double xOff;
	private double yOff;
//	/////////////////////////////
	SubScene test;

	ArrayList<Group> sheps = new ArrayList<Group>();
	public Slide(Stage mainStage, int width, int height, double xOffset, double yOffset, ArrayList<VideoLayer> vl, ArrayList<AudioLayer> al, ArrayList<ImageLayer> il, ArrayList<TextLayer> tl, ArrayList<Graphics2D> G2D, ArrayList<Graphics3DLayer> G3D,ArrayList<Shape> shapes) {
		this.width = width;
		toolBar = ToolBar.createToolBar(width);
		this.height = height;
		xOff = xOffset;
		yOff = yOffset;
		videoLayer = new VideoLayer(this.width,this.height);
		audioLayer = new AudioLayer(this.width,this.height);
		imageLayer = new ImageLayer(this.width,this.height);
		textLayer = new TextLayer(this.width,this.height);
		graphics2D = new Graphics2D(this.width,this.height,shapes);
		graphics3DLayer = new Graphics3DLayer(this.width,this.height);
		
		vl.add(videoLayer);
		al.add(audioLayer);
		il.add(imageLayer);
		tl.add(textLayer);
		G2D.add(graphics2D);
		G3D.add(graphics3DLayer);
		
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
	}
	
	public Scene update(){
		
//		graphics2D = new Graphics2D(width,height,sheps);
		
//		graphics2D.addOval(Color.RED,Color.BLUE,100,200,300,300);// creates oval, can also be used to create a circle
//		
//		graphics2D.addPoly(Color.MAGENTA,Color.ORANGE);//creates a new blank polygon
//		graphics2D.addPoint(200,400);//adds sides to the polygon
//		graphics2D.addPoint(400,400);
//		graphics2D.addPoint(400,600);
//		
//		graphics2D.addPoly(Color.MAGENTA,Color.ORANGE);
//		graphics2D.addPoint(700,400);
//		graphics2D.addPoint(900,400);
//		graphics2D.addPoint(800,600);
//		
//		
//		
		test = graphics2D.Update(width,height);//update function returns subscene
//		
//		graphics.remove(sheps.get(0));
//		///////////////////////////////
		gp.add(toolBar,0,0);
		gp.add(videoLayer.get(),0,1);
		gp.add(audioLayer.get(),0,1);
		gp.add(imageLayer.get(),0,1);
		gp.add(textLayer.get(),0,1);
		gp.add(graphics3DLayer.get(),0,1);
		gp.add(test,0,1);
		slide = new Scene(gp);
		
		return slide;
	}
}
