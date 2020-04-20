package main;
import java.io.IOException;
import java.nio.file.Paths;
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
	private static Stage mainStage;
	
	//Triggers Exhibit Mode
	private boolean exhibitMode = false;
	
	Thread runThread;
	
	static ArrayList<Slide> slides = new ArrayList<Slide>();
	static ArrayList<Audio> audio = new ArrayList<Audio>();
	static ArrayList<Graphics2D> graphics2d = new ArrayList<Graphics2D>();
	static ArrayList<Graphics3D> graphics3d = new ArrayList<Graphics3D>();
	static ArrayList<Image> images = new ArrayList<Image>();
	static ArrayList<Shape> shapes = new ArrayList<Shape>();
	static ArrayList<SlideText> slideText = new ArrayList<SlideText>();
	static ArrayList<Video> videos = new ArrayList<Video>();
	
	static ArrayList<VideoLayer> videoLayers = new ArrayList<VideoLayer>();
	static ArrayList<Graphics2D> graphics2dLayers = new ArrayList<Graphics2D>();
	static ArrayList<Graphics3DLayer> graphics3dLayers = new ArrayList<Graphics3DLayer>();
	static ArrayList<ImageLayer> imageLayers = new ArrayList<ImageLayer>();
	static ArrayList<TextLayer> textLayers = new ArrayList<TextLayer>();
	static ArrayList<AudioLayer> audioLayers = new ArrayList<AudioLayer>();
	
	String vidUrl = Paths.get("src/Sun.mp4").toUri().toString();
	String xml = "src/resources/xml.xml";
	
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
		new XMLParser(xml, audioLayers, videoLayers, textLayers, imageLayers, graphics2dLayers, graphics3dLayers);
		mainStage = primaryStage;
		start = StartScreen.createStartScreen(mainStage, defaultXSize, defaultYSize);
		settings = Settings.createSettings(mainStage, defaultXSize, defaultYSize);
		//mainStage.setScene(start);
		mainStage.setScene(start);
		
		/*LOADING PROCESS*/
		Scene slide1 = createSlide();

		mainStage.setScene(slide1);
		mainStage.show();
	}
	
	public static void run() {
		FileBrowser fb = new FileBrowser();
		//File file = RETURN FILE FROM BROWSER
		//parseXML(file, ArrayLists HERE);
		//createSlides(ARRAYLISTS);
		showSlide(1);
	}
	
	public Scene createSlide() throws IOException{
		BorderPane bp = new BorderPane();
		bp.setCenter(textLayers.get(0).get());
		return new Scene(bp, defaultXSize, defaultYSize);
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
