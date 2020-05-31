package media;

import java.io.IOException;
import java.util.ArrayList;

import com.sun.jmx.snmp.internal.SnmpSubSystem;

import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import main.InteractiveLearningApp;

public class VideoLayer {
	int height;
	int width;
	StackPane sp;// = new StackPane();
	public ArrayList<Video> videos;

	public VideoLayer(int width, int height, ArrayList<Video> videos,StackPane sp) {
		this.height = height;
		this.width = width;
		this.videos = videos;

		this.sp = sp;
//		sp.setPickOnBounds(false);
//		sp.setMinSize(width, height);
		//window = new SubScene(sp, width, height);
	}


	public void addVideo(String urlName, int startTime, Boolean loop, int xStart, int yStart, int slideNumber) throws IOException {
		// creates the video object and its subscene
		Video video = new Video(urlName, startTime, loop, xStart, yStart, 0, 0,0);
		
		if(video.videoFail == false) {
			// adds the video object to the array list
			videos.add(video);
			InteractiveLearningApp.slides.get(slideNumber).getSlideVideos().add(video);
			// adds the SubScene(created with the constructor) to the video layer stack pane
			sp.getChildren().add(video.get());
			video.get().setTranslateX(xStart*InteractiveLearningApp.getStageWidth()/100);
			video.get().setTranslateX(yStart*InteractiveLearningApp.getStageHeight()/100);
		}
		
		
	public void remove(int i) {
		if (sp.getChildren().contains(videos.get(i).get())) {
			sp.getChildren().remove(videos.get(i).get());
			System.out.println(videos.get(i).urlName);
		}
	}
	public void add(int i) {
		if (sp.getChildren().contains(videos.get(i).get()) == false) {
			sp.getChildren().add(videos.get(i).get());
		}
	}
	public void removeVideo(Video video) {
		sp.getChildren().remove(video.get());
	}

	//please comment on what this exactly is
//	public StackPane get() {
//		return sp;
//	}
}
