package media;

import java.util.ArrayList;

import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;

public class AudioLayer {
	int height;
	int width;
	StackPane sp = new StackPane();
	Canvas canvas = new Canvas(width,height);
	ArrayList<Audio> audio = new ArrayList<Audio>();
	SubScene window = new SubScene(sp,width,height);
	
	public AudioLayer(int width,int height, ArrayList<Audio> audio){
		this.height = height;
		this.width = width;
		this.audio = audio;
		sp.getChildren().add(canvas);
	}
	
	public void add(String urlName, int startTime, Boolean looping, Boolean controls, int controlX, int controlY, int width, int height, int slideNumber) {
		//constructor for the audio object
		Audio slideAudio = new Audio(urlName, startTime, looping, controls, controlX, controlY, width, height,  slideNumber);
		audio.add(slideAudio);
		sp.getChildren().add(slideAudio.get());
	}
	
	public void remove(Audio object) {
		sp.getChildren().remove(object);
	}
	
	public SubScene get() {
		return (window);
	}
}
