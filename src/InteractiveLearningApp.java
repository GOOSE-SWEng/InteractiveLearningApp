import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class InteractiveLearningApp extends Application{
	static int defaultXSize = 1280;
	static int defaultYSize = 720;
	private static Stage stage;
	public static void main(String[] args) {
		System.out.println("Running...");
		launch(args);
		System.out.println("Finished...");

	}
	
	public static Stage getStage() {
		return stage;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		SubScene toolBar = ToolBar.createToolBar(defaultXSize);
		//setting stage style to transparent removes the default toolbar
		stage.initStyle(StageStyle.TRANSPARENT);
		BorderPane borderPane = new BorderPane();
		
		borderPane.setTop(toolBar);
		Scene scene = new Scene(borderPane, defaultXSize, defaultYSize);
		stage.setScene(scene);
		
		stage.show();	
	}
}
