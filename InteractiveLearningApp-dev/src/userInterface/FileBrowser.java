package userInterface;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.InteractiveLearningApp;

public class FileBrowser {
	
	static Stage fileBrowser;
	static int browserWidth = 640;
	Button minimizeWindowButton;
	Button exitButton;
	SubScene toolBar;
	BorderPane borderPane;
	GridPane gridPane;
	Text title;
	static boolean isOpen = false;
	
	public static void createFileBrowser(){
		
		FileBrowser.fileBrowser = new Stage();
		SubScene toolBar = CreateToolBar();
		BorderPane borderPane = new BorderPane();
		Scene fileBrowserScene = new Scene(borderPane, browserWidth, 360);
		
		fileBrowser.initStyle(StageStyle.TRANSPARENT);
		fileBrowser.setTitle("Open");
		fileBrowser.setScene(fileBrowserScene);
		
		fileBrowser.setX(InteractiveLearningApp.getStage().getX() + 15);
		fileBrowser.setY(InteractiveLearningApp.getStage().getY() + 15);
		
		borderPane.setTop(toolBar);
		fileBrowserScene.getStylesheets().add("style/startScreen.css");
		
		fileBrowser.show();
		isOpen = true;
		
	}
	
	
	public static SubScene CreateToolBar() {
		
		GridPane gridPane = new GridPane();
		
		Text title = new Text("Select a File");
		Button minimizeWindowButton = new Button("Minimize to Tray");
		Button exitButton = new Button("Exit");
		
		exitButton.setOnAction(e -> ExitButtonPressed());
		minimizeWindowButton.setOnAction(e -> MinimizeButtonPressed());
		
		gridPane.setPadding(new Insets(10,0,10,0));
		
		title.setTextAlignment(TextAlignment.CENTER);
		gridPane.setAlignment(Pos.CENTER);
		
		GridPane.setHalignment(title, HPos.CENTER);
		gridPane.setHgap(0);
		
		gridPane.add(title,0,0);
		gridPane.add(minimizeWindowButton, 1, 0);
		gridPane.add(exitButton, 2, 0);
		gridPane.setGridLinesVisible(true);
		
		GridPane.setHalignment(title, HPos.CENTER);
		GridPane.setHalignment(minimizeWindowButton, HPos.CENTER);
		GridPane.setHalignment(exitButton, HPos.CENTER);
		
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setPercentWidth(80);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(10);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(10);
		
		gridPane.getColumnConstraints().addAll(column0, column1, column2);
		
		SubScene toolBar = new SubScene(gridPane, browserWidth, 20);
		toolBar.setUserAgentStylesheet("style/hotBar.css");
		
		return toolBar;
	}
	
	
	public static void MinimizeButtonPressed() {
		System.out.println("Minimize Window Button Pressed");
		fileBrowser.setIconified(true);
	}

	public static void ExitButtonPressed() {
		System.out.println("Exit Button Pressed");
		isOpen = false;
		fileBrowser.close();
	}

}

	

