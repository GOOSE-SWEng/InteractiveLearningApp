package userInterface;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartScreen {
	static double xOffset = 0;
	static double yOffset = 0;
	static Group logo3D;
	static Scene startScreen;
	public static Scene createStartScreen(Stage mainStage, int defaultXSize, int defaultYSize) {
		SubScene toolBar = ToolBar.createToolBar(defaultXSize);
		SubScene resizeBar = ResizeBar.CreateResizeBar(defaultXSize);
		
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
		
		//Setup left size of the screen
		Button openButton =  new Button("Open");
		openButton.setOnMouseClicked(e->nightmodeStyle());
		Button settingsButton = new Button("Settings");
		Button quitButton = new Button("Quit");
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(100,100,100,100));
		gp.add(openButton, 0, 0);
		gp.add(settingsButton, 0, 1);
		gp.add(quitButton, 0, 2);
		gp.setAlignment(Pos.CENTER);
		//gp.setGridLinesVisible(true);
		gp.setVgap(20);
		borderPane.setTop(toolBar);
		borderPane.setBottom(resizeBar);
				
		
		//ImageView imageView = null;
		BorderPane logoPane = new BorderPane();

		//Setup Right side of screen
		try {
			Image logo = new Image(new FileInputStream("src/resources/GooseLogo.png"));
			ImageView imageView = new ImageView(logo);
			imageView.setFitWidth(defaultXSize/2);
			imageView.setPreserveRatio(true);
			imageView.setY(100);
			//group for 3D element
			Group logo3D = new Group();
			logo3D.getChildren().add(imageView);

			logoPane.setCenter(imageView);
			
		}catch(IOException ioe) {
			System.out.println("Image not found");
		}
		
		

		
		borderPane.setLeft(gp);
		borderPane.setRight(logoPane);
		startScreen = new Scene(borderPane, defaultXSize, defaultYSize);
		startScreen.getStylesheets().add("style/startScreen.css");
		return startScreen;
	}
	public static void nightmodeStyle() {
		startScreen.getStylesheets().add("style/startScreenNight.css");
	}
	public static void colourblindStyle() {
		startScreen.getStylesheets().add("style/startScreenCB.css");
	}

}
