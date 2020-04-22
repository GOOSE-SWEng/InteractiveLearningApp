package media;

import java.io.File;
import java.nio.file.Paths;

import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Audio {
	String source;
	Boolean controls;
	SubScene subScene;
	MediaPlayer player;
	StackPane sp = new StackPane();
	int startTime;
	int slideNumber;
	
	public Audio(String urlName, int startTime, Boolean looping, Boolean controls, int controlX, int controlY, int width, int height, int slideNumber) {
		
		player = new MediaPlayer(new Media(Paths.get(urlName).toUri().toString()));
		if (looping) {
			player.setCycleCount(MediaPlayer.INDEFINITE);
		} else {
			player.setCycleCount(1);
		}
		player.setVolume(0.1);
		source = urlName;
		subScene = new SubScene(sp,width,height);
		this.startTime= startTime;
		this.slideNumber= slideNumber;
		// construct your subscene in here
	}
	public void add() {
		// add the object to the pane
	}
	public void remove() {
		//remove the object from the pane
	}
	public void start() {
		// start playing the audio
		player.play();
	}
	public void stop() {
		player.stop();
	}
	public int getStartTime() {
		return(startTime);
	}

	public int getSlideNumber() {
		return(slideNumber);
	}
	public SubScene get() {
		return(subScene);
	}
}
