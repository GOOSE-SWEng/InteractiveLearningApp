import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InteractiveLearningApp extends Application{
	private int defaultXSize = 1280;
	private int defaultYSize = 720;
	private Stage stage;
	
	public static void main(String[] args) {
		System.out.println("Running...");
		launch(args);
		System.out.println("Finished...");

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.initStyle(StageStyle.TRANSPARENT);
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp, defaultXSize, defaultYSize);
		stage.setScene(scene);
		stage.show();	
	}
}
