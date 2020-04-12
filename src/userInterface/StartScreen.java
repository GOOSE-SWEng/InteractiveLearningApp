package userInterface;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.Graphics2D;
import media.Shape;

public class StartScreen {
	static double xOffset = 0;
	static double yOffset = 0;
	
	public static Scene createStartScreen(Stage mainStage, int defaultXSize, int defaultYSize) {
		SubScene toolBar = ToolBar.createToolBar(defaultXSize);
		SubScene resizeBar = ResizeBar.CreateResizeBar(defaultXSize);
		
		toolBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		
		//Move window with mouse
		toolBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainStage.setX(event.getScreenX() - xOffset);
				mainStage.setY(event.getScreenY() - yOffset);	
			}
		});
		
		//Setting stage style to transparent removes the default toolbar
		mainStage.initStyle(StageStyle.TRANSPARENT);
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(toolBar);
		borderPane.setBottom(resizeBar);
		return new Scene(borderPane, defaultXSize, defaultYSize);
	}

}
