import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

//TODO add additional comments, to make things clearer

public class ToolBar {
	
	//Global button/title names
	Button exitButton;
	Button openButton;
	Button newFileButton;
	Button optionsButton;
	Button settingsButton;
	Button minimizeButton;
	Button maximizeButton;
	Text title;
	
	public static SubScene createToolBar(int winWidth) {
		
		//instantiate the new grid pane, then instantiate the buttons to fill it
		GridPane gridPane = new GridPane();
		
		Text title = new Text("Home Screen");
		Button openButton = new Button("Open");
		Button settingsButton = new Button("Settings");
		Button newFileButton = new Button("New File");
		Button minimizeWindowButton = new Button("Minimize Window");
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
		gridPane.setPadding(new Insets(10,10,10,10));
		
		//sets the text so it is aligned in the centre of  its bounding box
		title.setTextAlignment(TextAlignment.CENTER);
		
		
		gridPane.setAlignment(Pos.CENTER);
		
		//sets the title to always be in the centre
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
		
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(5);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(5);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(10);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(55);
		ColumnConstraints column5 = new ColumnConstraints();
		column5.setPercentWidth(10);
		ColumnConstraints column6 = new ColumnConstraints();
		column6.setPercentWidth(10);
		ColumnConstraints column7 = new ColumnConstraints();
		column7.setPercentWidth(5);

		gridPane.getColumnConstraints().addAll(column1, column2, column3, 
											   column4, column5, column6,
											   column7);
		
		//great a subscene on top of the gridpane, with a width defined by the windowwidth and a height of 20
		SubScene toolBar = new SubScene(gridPane, winWidth, 20);
		
		return toolBar;
	}
	
	
	public static void OpenButtonPressed() {
		System.out.println("Open Button Pressed");
		//TODO add functionality so a file browser opens
	}
	
	public static void SettingsButtonPressed() {
		System.out.println("Settings Button Pressed");
		//TODO add functionality so a settings scene opens
	}
	
	public static void NewFileButtonPressed() {
		System.out.println("New File Button Pressed");
		//TODO add functionality so a new empty scne opens??
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
