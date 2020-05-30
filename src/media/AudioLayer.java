package media;

import java.util.ArrayList;

import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import main.InteractiveLearningApp;

public class AudioLayer {
	int height;
	int width;
	StackPane sp;// = new StackPane();
	//Canvas canvas = new Canvas(width,height);
	public ArrayList<Audio> audio;
	//SubScene window = new SubScene(sp,width,height);
	
	public AudioLayer(int width,int height, ArrayList<Audio> audio, StackPane sp){
		this.height = height;
		this.width = width;
		this.audio = audio;
		this.sp = sp;
		//sp.getChildren().add(canvas);
		//sp.setPickOnBounds(false);
	}
	
	public void add(String urlName, int startTime, Boolean looping, Boolean controls, int controlX, int controlY, int width, int height, int slideNumber) {
		//constructor for the audio object

		Audio slideAudio = new Audio(urlName, startTime, looping, controls, width, height, slideNumber);
		InteractiveLearningApp.slides.get(slideNumber).getSlideAudio().add(slideAudio);
		audio.add(slideAudio);
		if (controls) {
			//sp.getChildren().add(slideAudio.get());
			slideAudio.get().setTranslateX(controlX*width/100);
			slideAudio.get().setTranslateY(controlY*height/100);
		}
	}
	
	public void remove(int i) {
//		if (sp.getChildren().contains(audio.get(i).get())) {
//			sp.getChildren().remove(audio.get(i).get());
//		}
		audio.get(i).stop();
	}
	public void add(int i) {
//		if (sp.getChildren().contains(audio.get(i).get()) == false) {
//			sp.getChildren().add(audio.get(i).get());
//		}
		audio.get(i).play();
	}
	public void remove(Audio object) {
		sp.getChildren().remove(object);
	}
	public void add() {
		
	}
	public void remove() {
		
	}
	
//	public StackPane get() {
//		return sp;
//	}
}
