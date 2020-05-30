package media;

import java.io.IOException;
import java.util.ArrayList;

import com.sun.jmx.snmp.internal.SnmpSubSystem;

import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import main.InteractiveLearningApp;

public class VideoLayer {
	int height;
	int width;
	StackPane sp;// = new StackPane();
	public ArrayList<Video> videos;
	SubScene window;

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
		InteractiveLearningApp.slides.get(slideNumber).getSlideVideos().add(video);
		// adds the video object to the array list
		videos.add(video);
		// adds the SubScene(created with the constructor) to the video layer stack pane
		//sp.getChildren().add(video.get());
		video.get().setTranslateX(xStart *width/100);//-(width/2 - video.get().getWidth()/2) + xStart/2 *width/100);
		video.get().setTranslateY(yStart *height/100);//-(height/2 - video.get().getHeight()/2) + yStart/2 *height/100);
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
