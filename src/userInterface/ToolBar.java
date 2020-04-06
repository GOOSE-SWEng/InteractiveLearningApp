package userInterface;

import java.io.File;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.InteractiveLearningApp;

/*TODO
 * add additional comments
 * when the maximize window button is pressed, update the text on the button to say "minimize window"
 */

public class ToolBar {
	
	//Global button/title names
	Button exitButton;
	Button openButton;
	Button newFileButton;
	Button optionsButton;
	Button settingsButton;
	Button minimizeWindowButton;
	Button maximizeWindowButton;
	static GridPane gridPane;
	Text title;
	
	
	public static SubScene createToolBar(int winWidth) {
		
		//instantiate the new grid pane, then instantiate the buttons to fill it
		GridPane gridPane = new GridPane();
		
		//To be replaced with icons
		Text title = new Text("Home Screen");
		title.setId("title");
		Button openButton = new Button("Open");
		Button settingsButton = new Button("Settings");
		Button newFileButton = new Button("New File");
		Button minimizeWindowButton = new Button("Minimize to Tray");
		Button maximizeWindowButton = new Button("Maximize Window");
		Button exitButton = new Button("Exit");
		
		
		//connects each button to its corresponding event
		openButton.setOnAction(e -> OpenButtonPressed());
		settingsButton.setOnAction(e -> SettingsButtonPressed());
		newFileButton.setOnAction(e -> NewFileButtonPressed());
		minimizeWindowButton.setOnAction(e -> MinimizeButtonPressed());
		maximizeWindowButton.setOnAction(e -> MaximizeButtonPressed());
		exitButton.setOnAction(e -> ExitButtonPressed());
		
		
		//adds 10 pixel padding to the top, bottom, left and right of the toolbar
		
		gridPane.setPadding(new Insets(10,0,10,0));
		
		//sets the text so it is aligned in the centre of  its bounding box
		title.setTextAlignment(TextAlignment.CENTER);
		gridPane.setAlignment(Pos.CENTER);
		
		//sets the title to always be in the centre
		//and resize button to be on the left of the toolbar
		
		GridPane.setHalignment(title, HPos.CENTER);
		gridPane.setHgap(0);
		
		//adding buttons to gridpane
		gridPane.add(openButton, 0, 0);
		gridPane.add(settingsButton, 1, 0);
		gridPane.add(newFileButton, 2, 0);
		gridPane.add(title, 3, 0);
		gridPane.add(minimizeWindowButton, 4, 0);
		gridPane.add(maximizeWindowButton, 5, 0);
		gridPane.add(exitButton, 6, 0);
		gridPane.setGridLinesVisible(true);
		
		//Sets to middle of each cell
		GridPane.setHalignment(title, HPos.CENTER);
		GridPane.setHalignment(openButton, HPos.CENTER);
		GridPane.setHalignment(settingsButton, HPos.CENTER);
		GridPane.setHalignment(newFileButton, HPos.CENTER);
		GridPane.setHalignment(minimizeWindowButton, HPos.CENTER);
		GridPane.setHalignment(maximizeWindowButton, HPos.CENTER);
		GridPane.setHalignment(exitButton, HPos.CENTER);
		

		//creates column constraints so if the toolbar is resized, the distance between buttons 
		//remains consistent
		/*TODO
		 * work with the design and marketing manager to get the toolbar looking good 
		 */
	
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setPercentWidth(5);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(5);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(5);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(70);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(5);
		ColumnConstraints column5 = new ColumnConstraints();
		column5.setPercentWidth(5);
		ColumnConstraints column6 = new ColumnConstraints();
		column6.setPercentWidth(5);

		gridPane.getColumnConstraints().addAll(column0, column1, column2, column3, 
											   column4, column5, column6);
		
		//great a subscene on top of the gridpane, with a width defined by the windowwidth and a height of 20
		SubScene toolBar = new SubScene(gridPane, winWidth, 20);
		
		toolBar.widthProperty().bind(InteractiveLearningApp.getStage().widthProperty());
		toolBar.setUserAgentStylesheet("style/hotBar.css");
		return toolBar;
	}
	
	public static void OpenButtonPressed() {
		/*
		 * TODO
		 * Maybe make our own file browser? (would be a lot of work but could be something to do on the side
		 * 
		 */
		System.out.println("Open Button Pressed");
		FileChooser fileChooser = new FileChooser();
		
		/*
		 * TODO: Add appropriate file extension filters (e.g. .stl files)
		fileChooser.getExtensionFilters().addAll(c)
		
		
		*/
		fileChooser.setTitle("Open a new Presentation");
		File selectedFile = fileChooser.showOpenDialog(InteractiveLearningApp.getStage());
		
		//from here file can be returned or parsed to another method for scene building
	}
	
	public static void SettingsButtonPressed() {
	/*
	 * TOOD maybe have the settings scene as a separate class?
	 * This is just a basic idea of what it should do
	 */
		System.out.println("Settings Button Pressed");
		Label settingsLabel = new Label("This will be the settings screen");
		
		StackPane settingsLayout = new StackPane();
		settingsLayout.getChildren().add(settingsLabel);
		
		Stage settingsWindow = new Stage();
		Scene settingsScene = new Scene(settingsLayout,230,100);
		
		settingsWindow.setTitle("Settings Screen");
		settingsWindow.setScene(settingsScene);
		
		settingsWindow.setX(InteractiveLearningApp.getStage().getX() + 200);
		settingsWindow.setY(InteractiveLearningApp.getStage().getX() + 100);
		
		settingsWindow.show();
	}
	
	public static void NewFileButtonPressed() {
		System.out.println("New File Button Pressed");
		//TODO add functionality so a new empty scene opens??
		//not sure abt this one 
	}
	
	public static void MinimizeButtonPressed() {
		System.out.println("Minimize Window Button Pressed");
		InteractiveLearningApp.getStage().setIconified(true);
	}
	
	
	public static void MaximizeButtonPressed() {
		System.out.println("Maximize Window Button Pressed");
		
		//if window is not maximized, maximizes the window and sets the value to true
		if (InteractiveLearningApp.getStage().isMaximized() == false) {
			InteractiveLearningApp.getStage().setMaximized(true);
		}
		
		//if window is maximized, returns to the default window size and sets the the value to false
		else {
			InteractiveLearningApp.getStage().setWidth(InteractiveLearningApp.defaultXSize);
			InteractiveLearningApp.getStage().setHeight(InteractiveLearningApp.defaultYSize);
			InteractiveLearningApp.getStage().setMaximized(false);
		}

	}
	
	public static void ExitButtonPressed() {
		System.out.println("Exit Button Pressed");
		InteractiveLearningApp.getStage().close();
	}
}
