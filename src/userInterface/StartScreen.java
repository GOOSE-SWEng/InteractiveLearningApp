package userInterface;

import java.nio.file.Paths;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.InteractiveLearningApp;
import media.Model;

public class StartScreen {
	private static double xOffset = 0;	//Screen x offset
	private static double yOffset = 0;	//Screen y offset
	private static Scene startScreen;	//Scene
	private static String title = "Start Screen"; //Title of screen
	
	public static Scene createStartScreen(Stage mainStage, int defaultXSize, int defaultYSize) {
		SubScene toolBar = ToolBar.createToolBar(defaultXSize, title); //Create relative toolbar
		SubScene resizeBar = ResizeBar.CreateResizeBar(defaultXSize); //Create resize bar
		//Get coordinate offsets of window when mouse press
		toolBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		
		//Move window with mouse
		toolBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainStage.setX(event.getScreenX() - xOffset);
				mainStage.setY(event.getScreenY() - yOffset);	
			}
		});
		
		//Setting stage style to transparent removes the default toolbar
		mainStage.initStyle(StageStyle.TRANSPARENT);
		BorderPane borderPane = new BorderPane();
		
		//Setup left side of the screen
		//Create buttons
		Button openButton =  new Button("Open");
		Button settingsButton = new Button("Settings");
		Button quitButton = new Button("Quit");
		
		//Setup button actions
		openButton.setOnMouseClicked(e->InteractiveLearningApp.run());
		settingsButton.setOnMouseClicked(e->InteractiveLearningApp.showSettings());
		quitButton.setOnMouseClicked(e->System.exit(1));
		
		//Create button menu
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(100,100,100,100));
		gp.add(openButton, 0, 0);	//Add open button
		gp.add(settingsButton, 0, 1);	//Add settings button
		gp.add(quitButton, 0, 2);	//Add quit button
		gp.setAlignment(Pos.CENTER);	//Center gridpane to center of location
		gp.setVgap(20); //Set the gap between buttons in the menu
		borderPane.setTop(toolBar); //Add toolbar to top of scene
		borderPane.setBottom(resizeBar); //Add resize bar to bottom of scene
		
		//Import 3D goose model
		Model gooseModel = new Model(Paths.get("src/resources/3D_Models/startScreenGoose.stl").toUri().toString(), defaultXSize/2,defaultYSize-40);
		gooseModel.moveCam(0, 0, 600); //Zoom into model
		
		//Add buttons to the left of the screen
		borderPane.setLeft(gp);
		//Add model to the left of the screen
		borderPane.setRight(gooseModel.getModelScene());
		
		startScreen = new Scene(borderPane, defaultXSize, defaultYSize);
		startScreen.getStylesheets().add("style/StartScreen/startScreen.css");	//Default
		//startScreen.getStylesheets().add("style/StartScreen/startScreenNight.css");	//Nightmode
		//startScreen.getStylesheets().add("style/StartScreen/startScreenCB.css");	//Colourblind?
		return startScreen;
	}
	
	public static void defaultStyle() {
		startScreen.getStylesheets().clear();
		startScreen.getStylesheets().add("style/StartScreen/startScreen.css");
	}
	
	public static void nightmodeStyle() {
		startScreen.getStylesheets().clear();
		startScreen.getStylesheets().add("style/StartScreen/startScreenNight.css");	//Nightmode
	}
	
	public static void colourblindStyle() {
		startScreen.getStylesheets().clear();
		startScreen.getStylesheets().add("style/StartScreen/startScreenCB.css");
	}
}
