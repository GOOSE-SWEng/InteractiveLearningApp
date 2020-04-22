package slides;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import userInterface.ToolBar;
import media.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

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
	private StackPane sp = new StackPane();
	private int width;
	private int height;
	private double xOff;
	private double yOff;
	/////////////////////////////
	SubScene test;
	Graphics2D graphics;
	public Slide(Stage mainStage, int width, int height, double xOffset, double yOffset,
			ArrayList<VideoLayer> vl, ArrayList<AudioLayer> al, ArrayList<ImageLayer> il, ArrayList<TextLayer> tl, ArrayList<Graphics2D> G2D, ArrayList<Graphics3DLayer> G3D,
			ArrayList<Shape> shapes, ArrayList<SlideImage> images, ArrayList<Audio> audio, ArrayList<SlideText> slideTexts) {
		this.width = width;
		toolBar = ToolBar.createToolBar(width);
		this.height = height;
		xOff = xOffset;
		yOff = yOffset;
		sp.setMinSize(width,height);
		sp.setAlignment(Pos.TOP_LEFT);
		videoLayer = new VideoLayer(this.width,this.height);
		audioLayer = new AudioLayer(this.width,this.height,audio,sp);
		imageLayer = new ImageLayer(this.width,this.height,images,sp);
		textLayer = new TextLayer(this.width,this.height,slideTexts,sp);
		graphics2D = new Graphics2D(this.width,this.height,shapes,sp);
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
		gp.add(toolBar,0,0);
		gp.add(sp,0,1);
		slide = new Scene(gp);
		return slide;
	}
}
