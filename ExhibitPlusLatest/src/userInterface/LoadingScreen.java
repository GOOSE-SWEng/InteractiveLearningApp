package userInterface;

import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.ExhibitPlus;

/**
 * Class for the loading screen
 * @author - Tom Poumd
 * @version - 1.0
 * @date - Unknown
 */
public class LoadingScreen {
	//global variables
	static SubScene toolBar;
	static SubScene resizeBar;
	static Scene loadingScene;
	
	/**
	 * 
	 * @param stage - main stage of program to place this scene on top of
	 * @param width - width of window
	 * @param height - height of window
	 * @param exhibitMode 
	 * @return returns a scene containing the loading screen
	 */
	public static Scene createLoadingScreen(Stage stage, int width, int height, boolean exhibitMode) {
		//create toolbar and resize bar for scene
		toolBar = ToolBar.createToolBar(width, "Loading...", exhibitMode);
		resizeBar = ResizeBar.CreateResizeBar(width);
		
		//create border pane with loading text
		BorderPane bp = new BorderPane();
		bp.setTop(toolBar);
		Text prezLoad = new Text("Your presentation is Loading");
		prezLoad.setId("centerText");
		bp.setCenter(prezLoad);
		
		loadingScene = new Scene(bp,width,height);
		if(ExhibitPlus.style.equals("nightmode")) {
			nightmodeStyle();
		}else if(ExhibitPlus.style.equals("colourblind")) {
			colourblindStyle();
		}else {
			defaultStyle();	//Default
		}
		return loadingScene;
	}
	/** sets style of loading screen to default */
	public static void defaultStyle() {
		loadingScene.getStylesheets().clear();
		loadingScene.getStylesheets().add("style/LoadingScreen/loadingScreen.css");
	}
	
	/** sets style of loading screen to night mode */
	public static void nightmodeStyle() {
		loadingScene.getStylesheets().clear();
		loadingScene.getStylesheets().add("style/LoadingScreen/loadingScreenNight.css");
	}
	
	/** sets style of loading screen to colour blind */
	public static void colourblindStyle() {
		loadingScene.getStylesheets().clear();
		loadingScene.getStylesheets().add("style/LoadingScreen/loadingScreenCB.css");
	}
}
