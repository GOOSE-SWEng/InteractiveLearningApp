package media;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.layout.StackPane;
import main.ExhibitPlus;

/**
 * Class for adding the video to the slides 
 * @author Alex Marchant, Cezara Lidia, Rimas Giana, Tom Pound
 * @version - 1.2
 * @date - 01/06/20
 *
 */

public class VideoLayer {
	public StackPane sp;
	public ArrayList<Video> videos;

	public VideoLayer(ArrayList<Video> videos,StackPane sp) {
		this.videos = videos;
		this.sp = sp;
	}


	public void addVideo(String urlName, String subUrlName, int startTime, Boolean loop, int xStart, int yStart, int slideNumber) throws IOException {
		// creates the video object and its subscene
		Video video = new Video(urlName, subUrlName, startTime, loop,slideNumber);
		
		if(video.videoFail == false) {
			// adds the video object to the array list
			videos.add(video);
			//moves the position of the video
			video.getSubScene().setTranslateX(xStart*ExhibitPlus.getStageWidth()/100);
			video.getSubScene().setTranslateY(yStart*ExhibitPlus.getStageHeight()/100);
		}
	}
		
		
	public void remove(int i) {
		videos.get(i).stop();
		if (sp.getChildren().contains(videos.get(i).getSubScene())) {
			sp.getChildren().remove(videos.get(i).getSubScene());

		}
	}
	public void add(int i) {
		videos.get(i).play();
		if (sp.getChildren().contains(videos.get(i).getSubScene()) == false) {
			sp.getChildren().add(videos.get(i).getSubScene());
		}
	}
	public void removeVideo(Video video) {
		sp.getChildren().remove(video.getSubScene());
	}
}
