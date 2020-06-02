package main;
import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import userInterface.*;
import slides.*;
import tools.Timer;
import tools.XMLParser;
import media.*;

/**
 * main class of the program
 * mostly contains getter and setters
 * majority of presentation is built and run in other packages
 * @author - Tom Pound, Alex Marchant, Ivy Price, Hugh Mayoh
 * @version - 1.6
 * @date - 21/05/20
 *
 */
public class ExhibitPlus extends Application{
	public static int defaultXSize = 1280;
	public static int defaultYSize = 720;
	private static Scene start;
	private static Scene settings;
	private static Scene loading;
	private static Stage mainStage;
	public static int currentSlide;
	public static int slideCount;
	public static String style = "default";
	
	public static Boolean presRunning = false;
	
	public static String defaultLanguage = "English";
	public static String defaultFont = "Arial";
	public static int defaultTextSize = 16;
	public static String defaultBGColour = "White";
	public static String defaultLineColour = "Black";
	public static String defaultFontColour = "Black";
	public static String defaultFillColour = "Black";
	
	public static String chosenFont = null;
	public static int chosenTextSize = -1;
	public static String chosenLanguage = null;
	
	//Triggers Exhibit Mode
	public static boolean exhibitMode = false;
	public static Timer timer;
	
	//arrays for the media objects and the layers that hold these media objects
	public static ArrayList<Slide> slides = new ArrayList<Slide>();
	static ArrayList<Audio> audio = new ArrayList<Audio>();
	static ArrayList<Model> models = new ArrayList<Model>();
	static ArrayList<SlideImage> images = new ArrayList<SlideImage>();
	static ArrayList<Shape> shapes = new ArrayList<Shape>();
	static ArrayList<SlideText> slideText = new ArrayList<SlideText>();
	static ArrayList<Video> videos = new ArrayList<Video>();
	
	static ArrayList<VideoLayer> videoLayers = new ArrayList<VideoLayer>();
	static ArrayList<Graphics2D> graphics2d = new ArrayList<Graphics2D>();
	static ArrayList<Graphics3DLayer> graphics3dLayers = new ArrayList<Graphics3DLayer>();
	static ArrayList<ImageLayer> imageLayers = new ArrayList<ImageLayer>();
	static ArrayList<TextLayer> textLayers = new ArrayList<TextLayer>();
	static ArrayList<AudioLayer> audioLayers = new ArrayList<AudioLayer>();
	
	static String xml;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		mainStage.setMinWidth(defaultXSize);
		mainStage.setMinHeight(defaultYSize);
		start = StartScreen.createStartScreen(mainStage, defaultXSize, defaultYSize, exhibitMode);
		settings = Settings.createSettings(mainStage, defaultXSize, defaultYSize, exhibitMode);
		loading = LoadingScreen.createLoadingScreen(mainStage, defaultXSize, defaultYSize, exhibitMode);
			
		mainStage.setScene(start);
		mainStage.show();
	}
	
	//Run App
	public static void run() {
		//Clear All
		if(presRunning == true) {
			xml = null;
			slides.clear();
			
			images.clear();
			videos.clear();
			audio.clear();
			slideText.clear();
			models.clear();
			shapes.clear();
			
			graphics2d.clear();
			graphics3dLayers.clear();
			imageLayers.clear();
			audioLayers.clear();
			videoLayers.clear();
			textLayers.clear();
		}
		presRunning = false;
		//Create File Browser
		FileChooser fileChooser = new FileChooser();
		//Change to another directory when we export as a jar
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		fileChooser.setInitialFileName("defaultPresentation.xml");
		fileChooser.setTitle("Choose a file to present...");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Presentation Files (*.xml)","*.xml"));
		
		File file = fileChooser.showOpenDialog(mainStage);
		
		try{
			//Get File path
			xml = file.getPath(); 
			//Create slides using XML
			XMLParser parser = new XMLParser(xml, slides, audioLayers, videoLayers, textLayers, imageLayers, graphics2d,
										     graphics3dLayers, audio, models, images, shapes, slideText, videos, mainStage);
			ExhibitPlus.slideCount = parser.getSlideCount();
			//Show first slide of presentation
			currentSlide = 0;
			showSlide(currentSlide);
			timer = new Timer(shapes, audio,images,slideText,videos,models,graphics2d,audioLayers,imageLayers,textLayers,
							  videoLayers,graphics3dLayers, slides);
			timer.start();
			presRunning = true;
		}catch(NullPointerException e) {
			showStart();
		}
	}
	public static void resumePres() {
		showSlide(currentSlide);
	}
	
	public static Scene getStart() {
		return start;
	}
	
	public static Stage getStage() {
		return mainStage;
	}
	
	public static void setStart(Scene start) {
		ExhibitPlus.start = null;
		ExhibitPlus.start = start;
	}
	
	public static void setSettings(Scene settings) {
		ExhibitPlus.settings = null;
		ExhibitPlus.settings = settings;
	}
	
	public static void setLoading(Scene loading) {
		ExhibitPlus.loading = null;
		ExhibitPlus.loading = loading;
	}
	
	/** Show settings screen */
	public static void showSettings() {
		mainStage.setScene(settings); 
	}
	
	/** Show start screen */
	public static void showStart() {
		mainStage.setScene(start); 
	}
	
	public static void showLoading() {
		mainStage.setScene(loading); 
	}
	
	public static void showMap() {
		//mainStage.setScene(map);
	}
	
	
	public static void nextSlide() {
		try {
			currentSlide++;
			mainStage.setScene(slides.get(currentSlide).getSlide());
			timer.resetTimer(currentSlide);
		}catch(NullPointerException | IndexOutOfBoundsException e) {
			if(presRunning == false) {
				mainStage.setScene(start);
			}else {
				mainStage.setScene(slides.get(0).getSlide());
				currentSlide = 0;
				timer.resetTimer(currentSlide);
			}
		}
	}
	
	public static void prevSlide() {
		try {
			currentSlide--;
			mainStage.setScene(slides.get(currentSlide).getSlide());
			timer.resetTimer(currentSlide);
		}catch(NullPointerException | IndexOutOfBoundsException e) {
			if(presRunning == false) {
				mainStage.setScene(start);
			}else {
				mainStage.setScene(slides.get(0).getSlide());
				currentSlide = 0;
				timer.resetTimer(currentSlide);
			}
		}
	}

	public static int getStageWidth() {
		return (int) mainStage.getWidth();
	}
	
	public static int getStageHeight() {
		return ((int) (mainStage.getHeight()*0.95)-20);
	}
	public static void setMainStage(Stage mainStage) {
		ExhibitPlus.mainStage = mainStage;
	}

	public static void showSlide(int index) {
		mainStage.setScene(slides.get(index).getSlide());
		currentSlide = index;
	}
	
	public static int getDefaultWidth() {
		return defaultXSize;
	}

	public static void setDefaultWidth(int defaultXSize) {
		ExhibitPlus.defaultXSize = defaultXSize;
	}

	public static int getDefaultHeight() {
		return defaultYSize;
	}

	public static void setDefaultHeight(int defaultYSize) {
		ExhibitPlus.defaultYSize = defaultYSize;
	}

	public static String getDefaultLanguage() {
		return defaultLanguage;
	}

	public static void setDefaultLanguage(String defaultLanguage) {
		ExhibitPlus.defaultLanguage = defaultLanguage;
	}

	public static String getDefaultFont() {
		return defaultFont;
	}

	public static void setDefaultFont(String defaultFont) {
		ExhibitPlus.defaultFont = defaultFont;
	}

	public static int getDefaultTextSize() {
		return defaultTextSize;
	}

	public static void setDefaultTextSize(int defaultTextSize) {
		ExhibitPlus.defaultTextSize = defaultTextSize;
	}

	public String getDefaultBGColour() {
		return defaultBGColour;
	}

	public static void setDefaultBGColour(String defaultBGColour) {
		ExhibitPlus.defaultBGColour = defaultBGColour;
	}

	public String getDefaultLineColour() {
		return defaultLineColour;
	}

	public static void setDefaultLineColour(String defaultLineColour) {
		ExhibitPlus.defaultLineColour = defaultLineColour;
	}

	public String getDefaultFontColour() {
		return defaultFontColour;
	}

	public static void setDefaultFontColour(String defaultFontColour) {
		ExhibitPlus.defaultFontColour = defaultFontColour;
	}

	public static String getDefaultFillColour() {
		return defaultFillColour;
	}

	public static void setDefaultFillColour(String defaultFillColour) {
		ExhibitPlus.defaultFillColour = defaultFillColour;
	}
	public static String getChosenFont() {
		return chosenFont;
	}

	public static void setChosenFont(String chosenFont) {
		ExhibitPlus.chosenFont = chosenFont;
	}

	public static int getChosenTextSize() {
		return chosenTextSize;
	}

	public static void setChosenTextSize(int chosenTextSize) {
		ExhibitPlus.chosenTextSize = chosenTextSize;
	}

	public String getChosenLanguage() {
		return chosenLanguage;
	}

	public static void setChosenLanguage(String chosenLanguage) {
		ExhibitPlus.chosenLanguage = chosenLanguage;
	}
}
