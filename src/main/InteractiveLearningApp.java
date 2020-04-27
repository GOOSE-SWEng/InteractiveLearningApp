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
	private static int slideCount;
	private static int currentSlide;
	
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
	
	//String vidUrl = Paths.get("src/Sun.mp4").toUri().toString();
	//String xml = "src/resources/xml.xml";
	static String xml;
	
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
		mainStage.setMinWidth(defaultXSize);
		mainStage.setMinHeight(defaultYSize);
		start = StartScreen.createStartScreen(mainStage, defaultXSize, defaultYSize);
		settings = Settings.createSettings(mainStage, defaultXSize, defaultYSize);
		loading = LoadingScreen.createLoadingScreen(mainStage, defaultXSize, defaultYSize);
		
		//////////////////REMOVE THIS//////////////////////

		//slideCount = parser.getSlideCount();
		//////////////////REMOVE THIS//////////////////////
		
		//mainStage.setScene(start);
		//mainStage.setScene(settings);
		
		/*LOADING PROCESS*/
		//LOADING SCREEN
		//SlideAssembler.createSlides(slideCount, slides, videoLayers, graphics2dLayers, graphics3dLayers, imageLayers, textLayers, audioLayers);
		//Scene slide1 = createSlide(0);

		mainStage.setScene(start);
		mainStage.show();
	}
	
	public static void run() {
		//FileBrowser fb = new FileBrowser();
		File file = new FileChooser().showOpenDialog(null);
		xml = file.getPath();
		//File file = RETURN FILE FROM BROWSER
		//parseXML(file, ArrayLists HERE);
		//createSlides(ARRAYLISTS);
		mainStage.setScene(loading);
		//showSlide(1);
		SlideAssembler.createSlides(xml, slides, videoLayers, graphics2d, 
									graphics3dLayers, imageLayers, textLayers, audioLayers, 
									shapes, images, audio, slideText, videos, models);
		showSlide(0);
	}
	
	public Scene createSlide(int slideNo) throws IOException{
		BorderPane bp = new BorderPane();
		bp.setTop(ToolBar.createToolBar(defaultXSize));
		bp.setBottom(ResizeBar.CreateResizeBar(defaultXSize));
		//imageLayers.add(new ImageLayer(defaultXSize, defaultYSize-100, images));
		//imageLayers.get(0).add("https://cdn.eso.org/images/screen/eso1907a.jpg", 0, 0, 110, 110, 0, 1000, 0);
		//images.get(0).start();
		StackPane sp = new StackPane();
		SubScene imageScene = imageLayers.get(slideNo).get();
		SubScene videoScene = videoLayers.get(slideNo).get();
		SubScene modelScene = graphics3dLayers.get(slideNo).get();
		SubScene textScene = textLayers.get(slideNo).get();
		SubScene shapesScene = graphics2dLayers.get(slideNo).get();
		SubScene audioScene = audioLayers.get(slideNo).get();
		
		imageScene.setTranslateX(-defaultXSize/2 + imageScene.getWidth()/2);
		//videoScene.setTranslateX(defaultXSize/2 - videoScene.getWidth()/2);
		//videoScene.setTranslateY(defaultYSize/2 - videoScene.getHeight()/2);
		//modelScene.setTranslateX(-defaultXSize/2 + modelScene.getWidth()/2);
		textScene.setTranslateY(defaultYSize/2 - textScene.getHeight()+30);
		shapesScene.setTranslateX(-defaultXSize/2 + shapesScene.getWidth()/2);
		shapesScene.setTranslateY(-defaultYSize/2 + shapesScene.getHeight()/2+20);
		
		sp.getChildren().add(shapesScene);
		sp.getChildren().add(imageScene);
		//sp.getChildren().add(textScene);
		
		sp.getChildren().add(modelScene);
		sp.getChildren().add(audioScene);
		sp.getChildren().add(videoScene);
		
		//slideText.get(slideNo).start();
		images.get(slideNo).start();
		audio.get(slideNo).start();
		videos.get(slideNo).play();
		images.get(slideNo+1).start();
		for(int i = 0; i< shapes.size();i++) {
			if(shapes.get(i).getSlideNumber() == slideNo) {
				shapes.get(i).create();
			}
		}
		
		bp.setCenter(sp);
		Scene scene1 = new Scene(bp, defaultXSize, defaultYSize);
		scene1.getStylesheets().add("style/contentScreen.css");
		return scene1;
	}
	
	/*public void assembleSlides() {
		XMLParser parser = new XMLParser(xml, audioLayers, videoLayers, textLayers, imageLayers, graphics2dLayers, graphics3dLayers, 
				audio,graphics2d,models,images,shapes,slideText,videos);
		slideCount = parser.getSlideCount();
		for(int i =0;i<slideCount;i++) {
			//slides.add(new Slide(mainStage, defaultXSize, defaultYSize,xOffset,yOffset));
		}
	}*/
	
	public static Stage getStage() {
		return mainStage;
	}
	
	public static void nextSlide() {
		try {
			System.out.println("NEXT SLIDE BABY");
			mainStage.setScene(slides.get(currentSlide+1).getSlide());
			currentSlide++;
		}catch(NullPointerException | IndexOutOfBoundsException e) {
			System.out.println("Presentation Restarted");
			mainStage.setScene(slides.get(0).getSlide());
			currentSlide = 0;
		}
	}
	
	/*public static void prevSlide() {
		try {
			System.out.println("NEXT SLIDE BABY");
			mainStage.setScene(slides.get(currentSlide+1).getSlide());
			currentSlide++;
		}catch(NullPointerException | IndexOutOfBoundsException e) {
			System.out.println("Presentation Restarted");
			mainStage.setScene(slides.get(0).getSlide());
			currentSlide = 0;
		}
	}*/

	public static void setMainStage(Stage mainStage) {
		InteractiveLearningApp.mainStage = mainStage;
	}

	public static void showSlide(int index) {
		mainStage.setScene(slides.get(index).getSlide());
		currentSlide = index;
	}
}
