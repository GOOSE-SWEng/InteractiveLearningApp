package userInterface;

import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoadingScreen {
	static SubScene toolBar;
	static SubScene resizeBar;
	public static Scene createLoadingScreen(Stage stage, int width, int height){
		toolBar = ToolBar.createToolBar(width, "Loading...");
		resizeBar = ResizeBar.CreateResizeBar(width);
		BorderPane bp = new BorderPane();
		bp.setTop(toolBar);
		Text prezLoad = new Text("Your presentation is Loading");
		Text pWait = new Text("Please wait...");
		bp.setCenter(prezLoad);
		Scene loadingScene = new Scene(bp,width,height);
		loadingScene.getStylesheets().add("style/ContentScreen/contentScreen.css");
		return loadingScene;
	}
}
