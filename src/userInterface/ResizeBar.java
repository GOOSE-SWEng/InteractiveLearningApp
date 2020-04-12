package userInterface;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.InteractiveLearningApp;

public class ResizeBar {
	
	Button resizeButton;
	
	public static SubScene CreateResizeBar(int winWidth) {
		
		GridPane gridPane = new GridPane();
		Button resizeButton = new Button("Resize");
		
		
		gridPane.setAlignment(Pos.CENTER_RIGHT);
		
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.setHgap(0);
		
		
		
		gridPane.add(resizeButton, 0, 0);
		
		
		//Adds a mouse listener event to the resize button allowing the window to be resized
		resizeButton.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Stage window = InteractiveLearningApp.getStage();
				window.setWidth(event.getScreenX() - window.getX());
				window.setHeight(event.getScreenY() - window.getY());
				
			}
		});
		
		SubScene resizeBar = new SubScene(gridPane, winWidth, 20);
		resizeBar.widthProperty().bind(InteractiveLearningApp.getStage().widthProperty());
		
		return resizeBar;
	}

}
