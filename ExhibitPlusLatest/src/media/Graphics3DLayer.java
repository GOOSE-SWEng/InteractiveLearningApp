package media;

import java.util.ArrayList;

import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import main.ExhibitPlus;

/**
 * Class for creating the 3D graphics and adding to the slides
 * @author Tom Pound, Alex Marchant
 * @version - 1.3
 * @date - 01/06/20
 *
 */
public class Graphics3DLayer {
	int slideNumber;
	StackPane sp;
	ArrayList<Model> models;
	SubScene window;
	
	public Graphics3DLayer(ArrayList<Model> models, StackPane sp){
		this.models = models;
		this.sp = sp;
	}
	public void addModel(String url, int modelWidth, int modelHeight, int xStart, int yStart) {
		Model model =  new Model(url, modelWidth, modelHeight, xStart, yStart,slideNumber);
		if(model.modelFail == false) {
			models.add(model);
			sp.getChildren().add(model.getModelScene());
			model.getModelScene().setTranslateX(xStart*ExhibitPlus.getStageWidth()/100);
			model.getModelScene().setTranslateY(yStart*ExhibitPlus.getStageHeight()/100);
		}
	}
	public void remove(int i) {
		if (sp.getChildren().contains(models.get(i).getModelScene())) {
			sp.getChildren().remove(models.get(i).getModelScene());
		}
	}
	public void add(int i) {
		if (sp.getChildren().contains(models.get(i).getModelScene()) == false) {
			sp.getChildren().add(models.get(i).getModelScene());
		}
	}
	public void setSlideNumber(int i) {
		this.slideNumber = i;
	}
	public int getSlideNumber() {
		return(slideNumber);
	}
}