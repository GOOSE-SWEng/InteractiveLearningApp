package media;

import java.util.ArrayList;
import javafx.scene.layout.StackPane;
import main.ExhibitPlus;

/**
 * Class for creating the audio elements and adding to the slides
 * @author Alex Marchant, Alex Kneller
 * @version - 1.4
 * @date - 01/06/20
 *
 */

public class AudioLayer {
	StackPane sp;
	public ArrayList<Audio> audio;
	
	public AudioLayer(ArrayList<Audio> audio, StackPane sp){
		this.audio = audio;
		this.sp = sp;
	}
	
	public void add(String urlName, int startTime, Boolean looping, Boolean controls, int controlX, int controlY, int width, int height, int slideNumber) {
		//constructor for the audio object
		Audio slideAudio = new Audio(urlName, startTime, looping, controls, width, height, slideNumber);
		if(slideAudio.audioFail == false) {
			audio.add(slideAudio);
			if (controls) {
				slideAudio.get().setTranslateX(controlX*ExhibitPlus.getStageWidth()/100);
				slideAudio.get().setTranslateY(controlY*ExhibitPlus.getStageHeight()/100);
			}
		}
	}
	
	public void remove(int i) {
		if (sp.getChildren().contains(audio.get(i).get())) {
			audio.get(i).stop();
			if (audio.get(i).controlsDrawn()) {
				sp.getChildren().remove(audio.get(i).get());
			}
		}
	}
	public void add(int i) {
		if (!sp.getChildren().contains(audio.get(i).get())) {
			audio.get(i).play();
			if (audio.get(i).controlsDrawn()) {
				sp.getChildren().add(audio.get(i).get());
			}
		}
	}
	
	public StackPane get() {
		return (sp);
  }
}
