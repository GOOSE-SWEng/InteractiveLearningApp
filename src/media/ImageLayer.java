package media;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import main.InteractiveLearningApp;

public class ImageLayer {
	int sceneHeight;
	int sceneWidth;
	StackPane sp;// = new StackPane();
	public ArrayList<SlideImage> images;
	SubScene window;
	

	public ImageLayer(int width,int height, ArrayList<SlideImage> images, StackPane sp){
    sceneHeight = InteractiveLearningApp.getStageHeight();
		sceneWidth = InteractiveLearningApp.getStageWidth();
//		sp.setMinSize(sceneWidth,sceneHeight);
//		sp.setPickOnBounds(false);
//		sp.setAlignment(Pos.TOP_LEFT);
		this.images = images;
		this.sp = sp;
		System.out.println("Image Layer created: " + width + ", " + height);
	}
	
	public void add(String urlName, int xStart, int yStart, int width, int height, int startTime, int endTime, int slideNumber) {
		//constructor for the image object
		SlideImage image = new SlideImage(urlName, xStart, yStart, width, height, startTime, endTime, slideNumber, sceneWidth, sceneHeight);
		if(image.imageFail == false) {
			images.add(image);
			sp.getChildren().add(image.get());
			InteractiveLearningApp.slides.get(slideNumber).getSlideImages().add(image);
			//image.get().setLayoutX(xStart);
			//image.get().setLayoutY(yStart);
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
			images.get(i).get().setLayoutX(images.get(i).getStartX());
			images.get(i).get().setLayoutY(images.get(i).getStartY());
			
		}
	}
	
//	public void remove(SlideImage object) {
//		sp.getChildren().remove(object.get());
//	}
	
//	public StackPane get() {
//    //window = new SubScene(sp,sceneWidth,sceneHeight);
//		return (sp);
//  }
  
  public ArrayList<SlideImage> getList() {
    return images;
  }
}