package main;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import userInterface.ResizeBar;
import userInterface.ToolBar;


public class InteractiveLearningApp extends Application{
	
	public static int defaultXSize = 1280;
	public static int defaultYSize = 720;
	
	//Current screen offset
	private double yOffset = 0;
	private double xOffset = 0;
	
	private static Stage mainStage;
	
	
	public static void main(String[] args) {
		System.out.println("Running...");
		launch(args);
		System.out.println("Finished...");

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		
		primaryStage.setTitle("Interactive Learning Application");
		
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
		
		
		//setting stage style to transparent removes the default toolbar
		mainStage.initStyle(StageStyle.TRANSPARENT);
		BorderPane borderPane = new BorderPane();
		
		borderPane.setTop(toolBar);
		borderPane.setBottom(resizeBar);
		
		Scene scene = new Scene(borderPane, defaultXSize, defaultYSize);
		mainStage.setScene(scene);
		
		mainStage.show();	
	}
	
	public static Stage getStage() {
		return mainStage;
	}
}
