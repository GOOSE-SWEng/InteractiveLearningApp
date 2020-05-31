package media;

import java.util.ArrayList;

import org.w3c.dom.Node;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import main.InteractiveLearningApp;

public class TextLayer {
	int height;
	int width;
	StackPane sp;// = new StackPane();
	public ArrayList<SlideText> slideText;
	SubScene window;
	
	public TextLayer(int width,int height, ArrayList<SlideText>slideText, StackPane sp){
		this.height = height;
		this.width = width;

//		sp.setMinSize(width,height);
//		sp.setPickOnBounds(false);
		this.slideText = slideText;
		this.sp = sp;
//		sp.setAlignment(Pos.TOP_LEFT);
		System.out.println("Text Layer created: " + width + ", " + height);
	}
	
	public void add(Node node, int slideNumber) {
		//constructor for the text object
		SlideText text = new SlideText(node, slideNumber, width, height);
		slideText.add(text);
		//text.get().setLayoutX();
		InteractiveLearningApp.slides.get(slideNumber).getSlideTexts().add(text);
	}
	
	public void remove(int i) {
		if (sp.getChildren().contains(slideText.get(i).get())) {
			sp.getChildren().remove(slideText.get(i).get());
		}
	}
	public void add(int i) {
		if (sp.getChildren().contains(slideText.get(i).get()) == false) {
			sp.getChildren().add(slideText.get(i).get());
			slideText.get(i).start();
			System.out.println("here"+slideText.get(i).getSlideNumber());
		}
	}
//	public void remove(SlideText object) {
//		sp.getChildren().remove(object.get());
//	}
	
	public StackPane get() {
    //window = new SubScene(sp,width,height);
		return (sp);
		
  }
  
  public ArrayList<SlideText> getList() {
    return slideText;
  }
}