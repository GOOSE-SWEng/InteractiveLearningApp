package main;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
	private static Scene start;
	private static Stage mainStage;
	
	Thread runThread;
	
	static ArrayList<Slide> slides = new ArrayList<Slide>();
	static ArrayList<Audio> audio = new ArrayList<Audio>();
	static ArrayList<Graphics2D> graphics2d = new ArrayList<Graphics2D>();
	static ArrayList<Graphics3D> graphics3d = new ArrayList<Graphics3D>();
	static ArrayList<Image> images = new ArrayList<Image>();
	static ArrayList<Shape> shapes = new ArrayList<Shape>();
	static ArrayList<SlideText> slideTexts = new ArrayList<SlideText>();
	static ArrayList<Video> videos = new ArrayList<Video>();
	
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
		start = StartScreen.createStartScreen(mainStage, defaultXSize, defaultYSize);
		mainStage.setScene(start);
		
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