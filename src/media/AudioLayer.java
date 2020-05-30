package media;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import main.InteractiveLearningApp;

public class AudioLayer {
	int height;
	int width;
	StackPane sp = new StackPane();
	public ArrayList<Audio> audio;
	//SubScene window = new SubScene(sp,width,height);
	
	public AudioLayer(int width,int height, ArrayList<Audio> audio){
		this.height = height;
		this.width = width;
		this.audio = audio;
		sp.setPickOnBounds(false);
		sp.setAlignment(Pos.TOP_LEFT);
	}
	
	public void add(String urlName, int startTime, Boolean looping, Boolean controls, int controlX, int controlY, int width, int height, int slideNumber) {
		//constructor for the audio object
		Audio slideAudio = new Audio(urlName, startTime, looping, controls, width, height, slideNumber);
		if(slideAudio.audioFail == false) {
			audio.add(slideAudio);
			InteractiveLearningApp.slides.get(slideNumber).getSlideAudio().add(slideAudio);
			if (controls) {
				sp.getChildren().add(slideAudio.get());
				slideAudio.get().setTranslateX(controlX*InteractiveLearningApp.getStageWidth()/100);
				slideAudio.get().setTranslateY(controlY*InteractiveLearningApp.getStageHeight()/100);
			}
		}
	}
	
	public void remove(Audio object) {
		sp.getChildren().remove(object);
	}
	
	public StackPane get() {
		return (sp);
	}
}
