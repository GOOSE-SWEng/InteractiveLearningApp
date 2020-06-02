package userInterface;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.ExhibitPlus;

/**
 * Class for the resize bar used to resize the window
 * @author - Ivy Price
 * @version - 1.1
 * @date - 28/02/20
 */
public class ResizeBar {
	//global button variable
	Button resizeButton;
	static int height = 20;
	
	/**
	 * Method to create a subscene containg the button to resize the window
	 * @param winWidth width of the window
	 * @return a subscene containng the resize window button
	 */
	public static SubScene CreateResizeBar(int winWidth) {
		//gridpane to hold elements
		GridPane gridPane = new GridPane();
		Button resizeButton = new Button();
		try {
			Image homeIcon = new Image(new FileInputStream("resources/toolbar/resizeHandle.png"));
			ImageView imageView = new ImageView(homeIcon);
			imageView.setFitHeight(height);
			imageView.setPreserveRatio(true);
			resizeButton.setGraphic(imageView);		
		}catch(IOException ioe) {
			//Image not found
		}
		resizeButton.setId("resizeButton");
		resizeButton.setStyle("-fx-background-color: transparent");
		
		//settings alignment and padding of the column to contain the resize button
		gridPane.setAlignment(Pos.CENTER_RIGHT);
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.setHgap(0);
		gridPane.add(resizeButton, 0, 0);
		
		//Adds a mouse listener event to the resize button allowing the window to be resized
		resizeButton.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Stage window = ExhibitPlus.getStage();
				if(window.getMinWidth() < (event.getScreenX() - window.getX())) {
					window.setWidth(event.getScreenX() - window.getX());

				}
				if(window.getMinHeight() < (event.getScreenY() - window.getY())) {
					window.setHeight(event.getScreenY() - window.getY());
				}
			}
		});

		SubScene resizeBar = new SubScene(gridPane, winWidth, height);
		
		//resize bar must be binded with the main window to allow resize to work
		resizeBar.widthProperty().bind(ExhibitPlus.getStage().widthProperty());
		resizeBar.setUserAgentStylesheet("style/Extras/toolBar.css");
		return resizeBar;
	}
}
