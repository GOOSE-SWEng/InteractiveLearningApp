package media;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Audio_IVY_P {
	
	String path;
	Button playPauseButton;
	static Stage audioWindow;
	static boolean isPaused = false;
	
	public static void playAudio(){
		
		String path = "/HCTWJ.mp3";
		Stage audioWindow = new Stage();
		Button playPauseButton = new Button();
		
		Media media = new Media(new File(path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		
		playPauseButton.setText("Play/Pause");
		playPauseButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(isPaused == false) {
		    		mediaPlayer.pause();
		    		isPaused = true;
		    	}
		    	else {
		    		mediaPlayer.play();
		    		isPaused = false;
		    	}
		    	
		    }
		});
		Scene scene = new Scene(playPauseButton,200,100);
		audioWindow.setScene(scene);
		audioWindow.show();
		
		
	
	}
	
	
}
