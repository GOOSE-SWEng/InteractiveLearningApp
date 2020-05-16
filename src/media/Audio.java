package media;

import java.io.File;
import java.nio.file.Paths;

import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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
		if (controls) {
			
			//Creating the buttons
			Button playButton  = new Button("Play");
			Button pauseButton = new Button("Pause");
			Button resetButton = new Button("Reset");
			
			//Creating a GridPane with the 3 buttons in side-by-side
			GridPane gp = new GridPane();
			gp.add(playButton, 1, 0);
			gp.add(pauseButton, 1, 0);
			gp.add(resetButton, 2, 0);
			gp.setHgap(5);
			
			//Making the buttons all equal width inside the GridPane
			ColumnConstraints audioControls = new ColumnConstraints();
			audioControls.setPercentWidth(50);
			gp.getColumnConstraints().addAll(audioControls, audioControls, audioControls);
		}
	}
	public void add() {

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
