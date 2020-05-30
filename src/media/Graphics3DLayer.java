package media;

import java.util.ArrayList;
import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import main.InteractiveLearningApp;

public class Graphics3DLayer {
	int paneHeight;
	int paneWidth;
	//StackPane sp = new StackPane();
	StackPane spIn;
	ArrayList<Model> models;
	SubScene window;
	
	public Graphics3DLayer(int width,int height, ArrayList<Model> models, StackPane sp){
		this.paneHeight = height*InteractiveLearningApp.getDefaultHeight()/100;
		this.paneWidth = width*InteractiveLearningApp.getDefaultWidth()/100;
		this.models = models;
		System.out.println(paneWidth + ", " + paneHeight);
		spIn = sp;
		spIn.setPickOnBounds(false);
		//this.sp.getChildren().add(canvas);
		//this.sp.setMinSize(paneWidth, paneHeight);
		//window = new SubScene(this.sp, paneWidth, paneHeight);
		//spIn.getChildren().add(window);
	}

	
	public void addModel(String url, int modelWidth, int modelHeight, int xStart, int yStart) {
		Model model =  new Model(url, modelWidth, modelHeight, xStart, yStart);
		models.add(model);
		//sp.getChildren().add(model.getModelScene());
	}
	public void remove(int i) {
		if (spIn.getChildren().contains(models.get(i).getModelScene())) {
			spIn.getChildren().remove(models.get(i).getModelScene());
		}
	}
	public void add(int i) {
		if (spIn.getChildren().contains(models.get(i).getModelScene()) == false) {
			spIn.getChildren().add(models.get(i).getModelScene());
			//models.get(i).getModelScene().setTranslateX();
			System.out.println("model should be here");
			
		}
	}
	
//	public void remove(Model object) {
//		sp.getChildren().remove(object);
//	}
	
//	public SubScene get() {
//		return window;
//	}
}