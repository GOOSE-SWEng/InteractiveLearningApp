package media;

import java.util.ArrayList;
import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import main.ExhibitPlus;

/**
 * Class for creating the image layer to place images on the slide
 * @author Alex Marchant
 * @version - 1.1
 * @date - 01/06/20
 *
 */

public class ImageLayer {
	StackPane sp;
	public ArrayList<SlideImage> images;
	SubScene window;
	

	public ImageLayer(ArrayList<SlideImage> images, StackPane sp){
		this.images = images;
		this.sp = sp;
	}
	
	public void add(String urlName, int xStart, int yStart, int width, int height, int startTime, int endTime, int slideNumber) {
		//constructor for the image object
		SlideImage image = new SlideImage(urlName, xStart, yStart, width, height, startTime, endTime, slideNumber, 
										  ExhibitPlus.getStageWidth(), ExhibitPlus.getStageHeight());
		if(image.imageFail == false) {
			images.add(image);
		}
	}
	public void remove(int i) {
		if (sp.getChildren().contains(images.get(i).get())) {
			sp.getChildren().remove(images.get(i).get());
		}
	}
	public void add(int i) {
		if (sp.getChildren().contains(images.get(i).get()) == false) {
			images.get(i).start();
			sp.getChildren().add(images.get(i).get());			
		}
	}
  
  public ArrayList<SlideImage> getList() {
    return images;
  }
}