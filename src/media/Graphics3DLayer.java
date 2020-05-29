package media;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import main.InteractiveLearningApp;

public class Graphics3DLayer {
	int paneHeight;
	int paneWidth;
	StackPane sp = new StackPane();
	ArrayList<Model> models;
	SubScene window;
	
	public Graphics3DLayer(int width,int height, ArrayList<Model> models){
		this.paneHeight = height-40;//*InteractiveLearningApp.getDefaultHeight()/100;
		this.paneWidth = width;//*InteractiveLearningApp.getDefaultWidth()/100;
		
		//this.modelHeight = InteractiveLearningApp.getDefaultHeight();
		//this.modelWidth = InteractiveLearningApp.getDefaultWidth();
		this.models = models;
		System.out.println(paneWidth + ", " + paneHeight);
		sp.setPickOnBounds(false);
		sp.setAlignment(Pos.TOP_LEFT);
		//window = new SubScene(sp, paneWidth, paneHeight);
	}
	
	public void add(String url, int modelWidth, int modelHeight, int xStart, int yStart) {
		Model model =  new Model(url, modelWidth, modelHeight, xStart, yStart);
		if(model.modelFail == false) {
			models.add(model);
			sp.getChildren().add(model.getModelScene());
			model.getModelScene().setTranslateX(xStart*InteractiveLearningApp.getStageWidth()/100);
			model.getModelScene().setTranslateY(yStart*InteractiveLearningApp.getStageHeight()/100);
		}
		
	}
	
	public void remove(Model object) {
		sp.getChildren().remove(object);
	}
	
	public StackPane get() {
		return sp;
	}
}