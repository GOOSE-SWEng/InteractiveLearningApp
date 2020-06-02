package media;

import java.util.ArrayList;
import org.w3c.dom.Node;
import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import main.ExhibitPlus;

/**
 * Class for creating the Text and adding to the slides
 * @author Alex Marchant
 * @version - 1.3
 * @date - 01/06/20
 *
 */

public class TextLayer {
	StackPane sp;
	public ArrayList<SlideText> slideText;
	SubScene window;
	
	public TextLayer(ArrayList<SlideText>slideText, StackPane sp){
		this.slideText = slideText;
		this.sp = sp;
	}
	
	public void add(Node node, int slideNumber) {
		//constructor for the text object
		SlideText text = new SlideText(node, slideNumber, ExhibitPlus.getStageWidth(), ExhibitPlus.getStageHeight());
		slideText.add(text);
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
		}
	}
}