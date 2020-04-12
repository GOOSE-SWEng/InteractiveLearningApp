package main;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import userInterface.*;
import slides.*;
import media.*;

public class InteractiveLearningApp extends Application{
	public static int defaultXSize = 1280;
	public static int defaultYSize = 720;
	//Current screen offset
	private double yOffset = 0;
	private double xOffset = 0;
	private static Stage mainStage;
	
	
	
	Thread runThread;
	
	static ArrayList<Slide> slides = new ArrayList<Slide>();
	static ArrayList<Audio> audio = new ArrayList<Audio>();
	static ArrayList<Shape> shapes = new ArrayList<Shape>();
	static ArrayList<Group> sheps = new ArrayList<Group>();
	static ArrayList<Graphics3D> graphics3d = new ArrayList<Graphics3D>();
	static ArrayList<Image> images = new ArrayList<Image>();
	static ArrayList<SlideText> slideTexts = new ArrayList<SlideText>();
	static ArrayList<Video> videos = new ArrayList<Video>();
	
	static ArrayList<VideoLayer> videoLayers = new ArrayList<VideoLayer>();
	static ArrayList<Graphics2D> graphics2d = new ArrayList<Graphics2D>();
	static ArrayList<Graphics3DLayer> graphics3dLayers = new ArrayList<Graphics3DLayer>();
	static ArrayList<ImageLayer> imageLayers = new ArrayList<ImageLayer>();
	static ArrayList<TextLayer> textLayers = new ArrayList<TextLayer>();
	static ArrayList<AudioLayer> audioLayers = new ArrayList<AudioLayer>();
	//static ArrayList<Video> videos = new ArrayList<Video>();
	
/*MEDIA ARRAYLIST DECLARATION
 * ETC...
 * 2DGraphics
 * 3DGraphics
 * Images
 * Video
 * Text
 * Audio
 * Buttons links
 */
	
	public static void main(String[] args) {
		System.out.println("Running...");
		launch(args);
		System.out.println("Finished...");
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		//silde = new Slide(mainStage,defaultXSize,(int)(defaultYSize*0.95),xOffset,yOffset,videoLayer,audioLayer,imageLayer,textLayer,graphics2D,graphics3DLayer);
		
		//start = StartScreen.createStartScreen(mainStage, defaultXSize, defaultYSize);
		createSlide();
//		String s = "#44568f";
//		Color c =  Color.web(s);
//		graphics2d.get(0).addOval(c,c,500,500,300,0);
		
//		graphics2d.get(0).addPoly(c,c);
//		graphics2d.get(0).addPoint(0,0);
//		graphics2d.get(0).addPoint(defaultXSize,0);
//		graphics2d.get(0).addPoint(defaultXSize,defaultYSize*0.95);
//		graphics2d.get(0).addPoint(0,defaultYSize*0.95);
//		
		//graphics2d.get(0).remove(shapes.get(0));
		//graphics2d.get(0).Update((double)defaultXSize,(double)(defaultYSize*0.95));
		mainStage.setScene(slides.get(0).update());
		//shapes.get(0).destroy();
		/*LOADING PROCESS*/
		
		mainStage.show();
		
	}
	
	public static void run() {
		FileBrowser fb = new FileBrowser();
		//File file = RETURN FILE FROM BROWSER
		//parseXML(file, ArrayLists HERE);
		//createSlides(ARRAYLISTS);
		showSlide(1);
	}
	
	public void createSlide() {
		Slide slide = new Slide(mainStage, defaultXSize, (int)(defaultYSize*0.95),xOffset,yOffset,videoLayers,audioLayers,imageLayers,textLayers,graphics2d,graphics3dLayers,shapes);
		slides.add(slide);
	}
	
	public static Stage getStage() {
		return mainStage;
	}

	public static void setMainStage(Stage mainStage) {
		InteractiveLearningApp.mainStage = mainStage;
	}

	public static void showSlide(int index) {
		mainStage.setScene(slides.get(index).update());
		
	}
}
