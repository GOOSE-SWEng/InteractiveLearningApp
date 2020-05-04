package main;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import userInterface.*;
import slides.*;
import tools.XMLParser;
import media.*;

public class InteractiveLearningApp extends Application{
	public static int defaultXSize = 1280;
	public static int defaultYSize = 720;
	//Current screen offset
	private double yOffset = 0;
	private double xOffset = 0;
	private static Scene start;
	private static Scene settings;
	private static Scene loading;
	private static Stage mainStage;
	public static int currentSlide;
	public static int slideCount;
	
	public static Boolean presRunning = false;
	
	public static String defaultLanguage = "English";
	public static String defaultFont = "Arial";
	public static String defaultTextSize = "16pt";
	
	public static String defaultBGColour = "White";
	public static String defaultLineColour = "Black";
	public static String defaultFontColour = "Black";
	public static String defaultFillColour = "Black";
	
	//Triggers Exhibit Mode
	private boolean exhibitMode = false;
	
	Thread runThread;
	
	static ArrayList<Slide> slides = new ArrayList<Slide>();
	static ArrayList<Audio> audio = new ArrayList<Audio>();
	static ArrayList<Graphics2D> graphics2d = new ArrayList<Graphics2D>();
	static ArrayList<Model> models = new ArrayList<Model>();
	static ArrayList<SlideImage> images = new ArrayList<SlideImage>();
	static ArrayList<Shape> shapes = new ArrayList<Shape>();
	static ArrayList<SlideText> slideText = new ArrayList<SlideText>();
	static ArrayList<Video> videos = new ArrayList<Video>();
	
	static ArrayList<VideoLayer> videoLayers = new ArrayList<VideoLayer>();
	static ArrayList<Graphics2D> graphics2dLayers = new ArrayList<Graphics2D>();
	static ArrayList<Graphics3DLayer> graphics3dLayers = new ArrayList<Graphics3DLayer>();
	static ArrayList<ImageLayer> imageLayers = new ArrayList<ImageLayer>();
	static ArrayList<TextLayer> textLayers = new ArrayList<TextLayer>();
	static ArrayList<AudioLayer> audioLayers = new ArrayList<AudioLayer>();
	
	static String xml;
	
	public static void main(String[] args) {
		System.out.println("Running...");
		launch(args);
		System.out.println("Finished...");
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		mainStage.setMinWidth(defaultXSize);
		mainStage.setMinHeight(defaultYSize);
		start = StartScreen.createStartScreen(mainStage, defaultXSize, defaultYSize);
		settings = Settings.createSettings(mainStage, defaultXSize, defaultYSize);
		loading = LoadingScreen.createLoadingScreen(mainStage, defaultXSize, defaultYSize);
			
		/*LOADING PROCESS*/
		//LOADING SCREEN
		//SlideAssembler.createSlides(slideCount, slides, videoLayers, graphics2dLayers, graphics3dLayers, imageLayers, textLayers, audioLayers);
		mainStage.setScene(start);
		mainStage.show();
	}
	
	//Run App
	public static void run() {
		presRunning = false;
		//Create File Browser
		File file = new FileChooser().showOpenDialog(null);
		try{
			xml = file.getPath(); //Get File path
			mainStage.setScene(loading);
			//Create slides using XML
			SlideAssembler.createSlides(xml, slides, videoLayers, graphics2d, 
										graphics3dLayers, imageLayers, textLayers, audioLayers, 
										shapes, images, audio, slideText, videos, models);
			showSlide(0); //Show first slide of presentation
			presRunning = true;
		}catch(NullPointerException e) {
			showStart();
		}
	}
	
	public static Stage getStage() {
		return mainStage;
	}
	
	public static void showSettings() {
		mainStage.setScene(settings); //Show settings screen
	}
	
	public static void showStart() {
		mainStage.setScene(start); //Show settings screen
	}
	
	public static void showMap() {
		//mainStage.setScene(map);
	}
	
	public static void nextSlide() {
		try {
			System.out.println("NEXT SLIDE");
			mainStage.setScene(slides.get(currentSlide+1).getSlide());
			currentSlide++;
		}catch(NullPointerException | IndexOutOfBoundsException e) {
			System.out.println("Presentation Restarted");
			mainStage.setScene(slides.get(0).getSlide());
			currentSlide = 0;
		}
	}
	
	public static void prevSlide() {
		try {
			System.out.println("PREV SLIDE");
			mainStage.setScene(slides.get(currentSlide-1).getSlide());
			currentSlide--;
		}catch(NullPointerException | IndexOutOfBoundsException e) {
			System.out.println("Presentation Restarted");
			mainStage.setScene(slides.get(0).getSlide());
			currentSlide = 0;
		}
	}

	public static void setMainStage(Stage mainStage) {
		InteractiveLearningApp.mainStage = mainStage;
	}

	public static void showSlide(int index) {
		mainStage.setScene(slides.get(index).getSlide());
		currentSlide = index;
	}
	
	public static int getDefaultWidth() {
		return defaultXSize;
	}

	public static void setDefaultWidth(int defaultXSize) {
		InteractiveLearningApp.defaultXSize = defaultXSize;
	}

	public static int getDefaultHeight() {
		return defaultYSize;
	}

	public static void setDefaultHeight(int defaultYSize) {
		InteractiveLearningApp.defaultYSize = defaultYSize;
	}

	public static String getDefaultLanguage() {
		return defaultLanguage;
	}

	public static void setDefaultLanguage(String defaultLanguage) {
		InteractiveLearningApp.defaultLanguage = defaultLanguage;
	}

	public static String getDefaultFont() {
		return defaultFont;
	}

	public static void setDefaultFont(String defaultFont) {
		InteractiveLearningApp.defaultFont = defaultFont;
	}

	public static String getDefaultTextSize() {
		return defaultTextSize;
	}

	public static void setDefaultTextSize(String defaultTextSize) {
		InteractiveLearningApp.defaultTextSize = defaultTextSize;
	}

	public String getDefaultBGColour() {
		return defaultBGColour;
	}

	public static void setDefaultBGColour(String defaultBGColour) {
		InteractiveLearningApp.defaultBGColour = defaultBGColour;
	}

	public String getDefaultLineColour() {
		return defaultLineColour;
	}

	public static void setDefaultLineColour(String defaultLineColour) {
		InteractiveLearningApp.defaultLineColour = defaultLineColour;
	}

	public String getDefaultFontColour() {
		return defaultFontColour;
	}

	public static void setDefaultFontColour(String defaultFontColour) {
		InteractiveLearningApp.defaultFontColour = defaultFontColour;
	}

	public static String getDefaultFillColour() {
		return defaultFillColour;
	}

	public static void setDefaultFillColour(String defaultFillColour) {
		InteractiveLearningApp.defaultFillColour = defaultFillColour;
	}
}
