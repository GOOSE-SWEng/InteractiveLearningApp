package slides;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.InteractiveLearningApp;
import userInterface.ResizeBar;
import userInterface.ToolBar;
import media.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

//THIS IS THE SLIDE OBJECT

public class Slide {
	SubScene toolBar;// = ToolBar.createToolBar(1280);
	SubScene resizeBar;
	private VideoLayer videoLayer;
	private AudioLayer audioLayer;
	private ImageLayer imageLayer;
	private TextLayer textLayer;
	private Graphics2D graphics2D;
	private Graphics3DLayer graphics3DLayer;
	private Scene slide;
	private BorderPane bp = new BorderPane();
	private StackPane sp = new StackPane();
	private int width;
	private int height;
	private double xOff;
	private double yOff;
	
	
	private String id;
	private int duration;
	/////////////////////////////
	SubScene test;
	Graphics2D graphics;
	public Slide(Stage mainStage, int width, int height, double xOffset, double yOffset,
			ArrayList<VideoLayer> vl, ArrayList<AudioLayer> al, ArrayList<ImageLayer> il, ArrayList<TextLayer> tl, ArrayList<Graphics2D> G2D, ArrayList<Graphics3DLayer> G3D,
			ArrayList<Shape> shapes, ArrayList<SlideImage> images, ArrayList<Audio> audio, ArrayList<SlideText> slideTexts) {
		
		this.width = width;
		this.height = height;
		toolBar = ToolBar.createToolBar(width, id);

		xOff = xOffset;
		yOff = yOffset;
		sp.setMinSize(width,height);
		sp.setAlignment(Pos.TOP_LEFT);
		
		/*videoLayer = new VideoLayer(this.width,this.height);
		audioLayer = new AudioLayer(this.width,this.height,audio,sp);
		imageLayer = new ImageLayer(this.width,this.height,images,sp);
		textLayer = new TextLayer(this.width,this.height,slideTexts,sp);
		graphics2D = new Graphics2D(this.width,this.height,shapes,sp);
		graphics3DLayer = new Graphics3DLayer(this.width,this.height);*/
		
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
	
	public Slide(int width, int height, String id, int duration) {
		this.width = width;
		this.height = height;
		this.id = id;
		this.duration = duration;
		toolBar = ToolBar.createToolBar(width, id);
		resizeBar = ResizeBar.CreateResizeBar(width);

		bp.setTop(toolBar);
		bp.setCenter(sp);
		bp.setBottom(resizeBar);
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
				InteractiveLearningApp.getStage().setX(event.getScreenX() - xOff);
				InteractiveLearningApp.getStage().setY(event.getScreenY() - yOff);	
			}
		});
		sp.setPickOnBounds(false);
		slide = new Scene(bp,width, height);
		slide.getStylesheets().add("style/contentScreen.css");
	}
	public void applyLayers() {

		sp.getChildren().add(audioLayer.get());
		sp.getChildren().add(graphics2D.get());
		sp.getChildren().add(videoLayer.get());
		sp.getChildren().add(graphics3DLayer.get());
		sp.getChildren().add(textLayer.get());
		sp.getChildren().add(imageLayer.get());
	}
	
	
	public Scene getSlide() {
		return slide;
	}

	public void setSlide(Scene slide) {
		this.slide = slide;
	}

	public VideoLayer getVideoLayer() {
		return videoLayer;
	}

	public void setVideoLayer(VideoLayer videoLayer) {
		this.videoLayer = videoLayer;
	}

	public AudioLayer getAudioLayer() {
		return audioLayer;
	}

	public void setAudioLayer(AudioLayer audioLayer) {
		this.audioLayer = audioLayer;
	}

	public ImageLayer getImageLayer() {
		return imageLayer;
	}

	public void setImageLayer(ImageLayer imageLayer) {
		this.imageLayer = imageLayer;
	}

	public TextLayer getTextLayer() {
		return textLayer;
	}

	public void setTextLayer(TextLayer textLayer) {
		this.textLayer = textLayer;
	}

	public Graphics2D getGraphics2D() {
		return graphics2D;
	}

	public void setGraphics2D(Graphics2D graphics2d) {
		graphics2D = graphics2d;
	}

	public Graphics3DLayer getGraphics3DLayer() {
		return graphics3DLayer;
	}

	public void setGraphics3DLayer(Graphics3DLayer graphics3dLayer) {
		graphics3DLayer = graphics3dLayer;
	}

	/*public Scene update(){
		bp.setTop(toolBar);
		bp.setCenter(sp);
		slide = new Scene(sp);
		return slide;
	}*/
}
