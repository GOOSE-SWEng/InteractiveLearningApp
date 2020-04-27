package userInterface;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Settings {
	static String title = "Settings";
	static SubScene toolBar;
	static SubScene resizeBar;
	static String currentLanguage = "English";
	Boolean colourBlind = false;
	Boolean darkMode = false;
	static String currentFont = "Arial";
	static String currentTextSize = "16pt";
	
	public static Scene createSettings(Stage stage,int defaultXSize, int defaultYSize) {
		//Create top and bottom tool bars
		toolBar = ToolBar.createToolBar(defaultXSize, title);
		resizeBar = ResizeBar.CreateResizeBar(defaultXSize);
		//Create Layouts
		BorderPane bp = new BorderPane();
		GridPane gp =  new GridPane();
		//Create Labels
		Text colourBlindFilter = new Text("Colour Blind Filter");
		Text nightMode = new Text("Dark Mode");
		Text textSize = new Text("Text Size");
		Text font = new Text("Font");
		Text language = new Text("Language");
		//Text captions = new Text("Captions");
		//Text audioDesc = new Text("Audio Description");
		//Create Check Boxes
		CheckBox cBBox = new CheckBox();
		CheckBox nMBox = new CheckBox();
		CheckBox captionsBox = new CheckBox();
		CheckBox audioDescBox = new CheckBox();
		
		//Create text size drop down menu
		ComboBox<String> textSizeMenu = new ComboBox<String>();
		textSizeMenu.getItems().addAll("11pt",
									"16pt",
									"24pt",
									"36pt");
		textSizeMenu.setValue("11pt"); //Set current value
		//Create event listener
		textSizeMenu.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue observable, String oldValue, String newValue) {
				currentTextSize = newValue;
			}
		});
		//Create font drop down menu
		ComboBox<String> fontMenu = new ComboBox<String>();
		fontMenu.getItems().addAll("Comic Sans MS",
									"Arial",
									"Verdana",
									"Helvetica");
		fontMenu.setValue("Arial"); //Set current value
		//Create event listener
		fontMenu.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue observable, String oldValue, String newValue) {
				currentFont = newValue;
			}
		});
		///Create language drop down menu
		ComboBox<String> languageMenu = new ComboBox<String>();
		languageMenu.getItems().addAll("English",
									"Spanish",
									"German",
									"French");
		languageMenu.setValue("English"); //Set current value
		//Create event listener
		languageMenu.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue observable, String oldValue, String newValue) {
				currentLanguage = newValue;
			}
		});

		//Apply and default buttons
		Button apply = new Button("Apply");
		Button setDefault = new Button("Return to Default");
		//Apply event listeners
		apply.setOnAction(e -> applyButtonPressed());
		setDefault.setOnAction(e -> defaultButtonPressed());
		
		//Add all elements to the grid pane
		gp.add(textSize, 0, 0);
		gp.add(textSizeMenu, 1, 0);
		
		gp.add(font, 0, 1);
		gp.add(fontMenu, 1, 1);
		
		gp.add(language, 0, 2);
		gp.add(languageMenu, 1, 2);
		
		gp.add(nightMode, 0, 3);
		gp.add(nMBox, 1, 3);
		
		//gp.add(colourBlindFilter, 0, 4);
		//gp.add(cBBox, 1, 4);
		
		//gp.add(captions, 0, 5);
		//gp.add(captionsBox, 1, 5);
		
		//gp.add(audioDesc, 0, 6);
		//gp.add(audioDescBox, 1, 6);
		
		gp.add(apply, 0, 7);
		gp.add(setDefault, 1, 7);
		//Set insets and layout
		gp.setVgap(10);
		gp.setHgap(50);
		gp.setPadding(new Insets(10,10,10,10));
		gp.setAlignment(Pos.CENTER);
		
		//gp.setGridLinesVisible(true);
		//Add to the borderpane scene
		bp.setTop(toolBar);
		bp.setBottom(resizeBar);
		bp.setCenter(gp);
		//Create the scene
		Scene settings  = new Scene(bp,defaultXSize,defaultYSize);
		settings.getStylesheets().add("style/settingsScreen.css");
		return settings;
	}
	
	public static void applyButtonPressed() {
		
		
		
	}
	public static void defaultButtonPressed() {
		
		
		
		
	}
}
