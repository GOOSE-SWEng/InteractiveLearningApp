package tools;

import java.util.ArrayList;

import com.sun.javafx.util.Utils;

import javafx.application.Platform;
import main.InteractiveLearningApp;
import media.*;
import slides.Slide;

public class Timer_2 extends Thread{
	int slideNumber = 0;
	int slideStart;
	static int localTime;
	ArrayList<TimingObject> timingObjects;
	ArrayList<Graphics2D> graphics2d;
	ArrayList<AudioLayer> audioLayers;
	ArrayList<ImageLayer> imageLayers;
	ArrayList<TextLayer> textLayers;
	ArrayList<VideoLayer> videoLayers;
	ArrayList<Graphics3DLayer> graphics3DLayers;
	ArrayList<Slide> slides;
	
	public Timer_2(ArrayList<Shape> shapes, 
			ArrayList<Audio> audio, 
			ArrayList<SlideImage> image, 
			ArrayList<SlideText> text, 
			ArrayList<Video> video, 
			ArrayList<Model> model,
			ArrayList<Graphics2D> graphics2d,
			ArrayList<AudioLayer> audioLayers,
			ArrayList<ImageLayer> imageLayers,
			ArrayList<TextLayer> textLayers,
			ArrayList<VideoLayer> videoLayers,
			ArrayList<Graphics3DLayer> graphics3DLayers,
			ArrayList<Slide> slides) {
		this.graphics2d = graphics2d;
		this.audioLayers = audioLayers;
		this.imageLayers = imageLayers;
		this.textLayers = textLayers;
		this.videoLayers = videoLayers;
		this.graphics3DLayers = graphics3DLayers;
		this.slides = slides;
		timingObjects = new ArrayList<TimingObject>(); // generic object used to store timing information of all objects
		
		for (int i =0; i<shapes.size();i++) {
			int slideNumber = shapes.get(i).getSlideNumber();
			int startTime = shapes.get(i).getStartTime();
			int endTime = shapes.get(i).getEndTime();
			TimingObject TO = new TimingObject(slideNumber,startTime,endTime, false, "shape",i);
			timingObjects.add(TO);
		}
		for (int i =0; i<audio.size();i++) {
			int slideNumber = audio.get(i).getSlideNumber();
			int startTime = audio.get(i).getStartTime();
			//int endTime = audio.get(i).getEndTime();
			TimingObject TO = new TimingObject(slideNumber,startTime, false, "audio",i);
			timingObjects.add(TO);
		}
		for (int i =0; i<image.size();i++) {
			int slideNumber = image.get(i).getSlideNumber();
			int startTime = image.get(i).getStartTime();
			int endTime = image.get(i).getEndTime();
			TimingObject TO = new TimingObject(slideNumber,startTime,endTime, false, "image",i);
			timingObjects.add(TO);
		}
		for (int i =0; i<text.size();i++) {
			int slideNumber = text.get(i).getSlideNumber();
			int startTime = text.get(i).getStartTime();
			int endTime = text.get(i).getEndTime();
			TimingObject TO = new TimingObject(slideNumber,startTime,endTime, false, "text",i);
			timingObjects.add(TO);
		}
		for (int i =0; i<video.size();i++) {
			int slideNumber = video.get(i).getSlideNumber();
			int startTime = video.get(i).getStartTime();
			TimingObject TO = new TimingObject(i,startTime, false, "video",i);
			timingObjects.add(TO);
		}
		for (int i =0; i<model.size();i++) {//always there
//			int slideNumber = model.get(i).getSlideNumber();
//			int startTime = model.get(i).getStartTime();
//			int endTime = model.get(i).getEndTime();
			TimingObject TO = new TimingObject(i,1000, false, "model",i);
			timingObjects.add(TO);
		}
		
		
	}
	
	public void run() {
		while(InteractiveLearningApp.presRunning){
			//check for slide change
			
			slideTime();
			if(localTime >= slides.get(slideNumber).getEndTime()) {
				Platform.runLater(()->InteractiveLearningApp.nextSlide());
				localTime = 0;
			}

			for (int i = 0; i < timingObjects.size(); i++) {
				if (timingObjects.get(i).getSlideNumber() == slideNumber) {
					if(timingObjects.get(i).getStartTime()>localTime) {
						
					}
					else if ((timingObjects.get(i).getStartTime()<=localTime) & (timingObjects.get(i).getEndTime()>=localTime)) {
						if (timingObjects.get(i).isDrawn()) {
							//do nothing
						}
						else if(timingObjects.get(i).isDrawn() == false){
							//draw it
							System.out.println(timingObjects.get(i).isDrawn() + ":" + i);
							String type = timingObjects.get(i).getType();
							if (type == "shape") {
								final int j = i;
								Platform.runLater(()->graphics2d.get(slideNumber).add(timingObjects.get(j).getIndex()));
								//System.out.println(timingObjects.get(i).getIndex());
							}
							else if(type == "audio") {
								final int j = i;
								Platform.runLater(()->audioLayers.get(slideNumber).add(timingObjects.get(j).getIndex()));
							}
							else if(type == "image") {
								final int j = i;

								//Platform.runLater(()->imageLayers.get(slideNumber).add(timingObjects.get(j).getIndex()));
//								Platform.runLater(()->imageLayers.get(slideNumber).add(0));
//								Platform.runLater(()->imageLayers.get(slideNumber).add(1));
								//Platform.runLater(()->imageLayers.get(slideNumber).add(2));
								//Platform.runLater(()->imageLayers.get(slideNumber).add(3));
								
								System.out.println(timingObjects.get(i).getIndex());
								Platform.runLater(()->imageLayers.get(slideNumber).add(timingObjects.get(j).getIndex()));
							}
							else if(type == "text") {
								final int j = i;
								System.out.println("text drawn");
								Platform.runLater(()->textLayers.get(slideNumber).add(timingObjects.get(j).getIndex()));
							}
							else if(type == "video") {
								final int j = i;
								Platform.runLater(()->videoLayers.get(slideNumber).add(timingObjects.get(j).getIndex()));
							}
							else if(type == "model") {
								final int j = i;
								Platform.runLater(()->graphics3DLayers.get(slideNumber).add(timingObjects.get(j).getIndex()));
							}
							timingObjects.get(i).setDrawn(true);
						}
					}
					else {
						if (timingObjects.get(i).isDrawn()) {
							
							//undraw
							String type = timingObjects.get(i).getType();
							if (type == "shape") {
								final int j = i;
								Platform.runLater(()->graphics2d.get(slideNumber).remove(timingObjects.get(j).getIndex()));
							}
							else if(type == "audio") {
								final int j = i;
								Platform.runLater(()->audioLayers.get(slideNumber).remove(timingObjects.get(j).getIndex()));
							}
							else if(type == "image") {
								final int j = i;
								Platform.runLater(()->imageLayers.get(slideNumber).remove(timingObjects.get(j).getIndex()));
							}
							else if(type == "text") {
								final int j = i;
								Platform.runLater(()->textLayers.get(slideNumber).remove(timingObjects.get(j).getIndex()));
							}
							else if(type == "video") {
								final int j = i;
								Platform.runLater(()->videoLayers.get(slideNumber).remove(timingObjects.get(j).getIndex()));
							}
							else if(type == "model") {
								final int j = i;
								Platform.runLater(()->graphics3DLayers.get(slideNumber).remove(timingObjects.get(j).getIndex()));
							}
							timingObjects.get(i).setDrawn(false);
						}
						else{
							//do nothing 
						}
					}
				}
				else{
					if (timingObjects.get(i).isDrawn()) {
						
						//undraw
						String type = timingObjects.get(i).getType();
						if (type == "shape") {
							final int j = i;
							Platform.runLater(()->graphics2d.get(slideNumber).remove(timingObjects.get(j).getIndex()));
						}
						else if(type == "audio") {
							final int j = i;
							Platform.runLater(()->audioLayers.get(slideNumber).remove(timingObjects.get(j).getIndex()));
						}
						else if(type == "image") {
							final int j = i;
							Platform.runLater(()->imageLayers.get(slideNumber).remove(timingObjects.get(j).getIndex()));
						}
						else if(type == "text") {
							final int j = i;
							Platform.runLater(()->textLayers.get(slideNumber).remove(timingObjects.get(j).getIndex()));
						}
						else if(type == "video") {
							final int j = i;
							Platform.runLater(()->videoLayers.get(slideNumber).remove(timingObjects.get(j).getIndex()));
						}
						else if(type == "model") {
							final int j = i;
							Platform.runLater(()->graphics3DLayers.get(slideNumber).remove(timingObjects.get(j).getIndex()));
						}
						timingObjects.get(i).setDrawn(false);
					}
				}
			}
		}
	}
	private void clearSlide(int num) {
/*		//loop through all on previous slide and remove
		//reset local time
		for (int i = 0; i < timingObjects.size(); i++) {
			if (timingObjects.get(i).getSlideNumber() == num) {
				if(timingObjects.get(num).isDrawn()) {
					String type = timingObjects.get(i).getType();
					if (type == "shape") {
						final int j = i;
						Platform.runLater(()->graphics2d.get(num).remove(timingObjects.get(j).getIndex()));
					}
					else if(type == "audio") {
						final int j = i;
						Platform.runLater(()->audioLayers.get(num).remove(timingObjects.get(j).getIndex()));
					}
					else if(type == "image") {
						final int j = i;
						Platform.runLater(()->imageLayers.get(num).remove(timingObjects.get(j).getIndex()));
					}
					else if(type == "text") {
						final int j = i;
						Platform.runLater(()->textLayers.get(num).remove(timingObjects.get(j).getIndex()));
					}
					else if(type == "video") {
						final int j = i;
						Platform.runLater(()->videoLayers.get(num).remove(timingObjects.get(j).getIndex()));
					}
					else if(type == "model") {
						final int j = i;
						Platform.runLater(()->graphics3DLayers.get(num).remove(timingObjects.get(j).getIndex()));
					}
					timingObjects.get(i).setDrawn(false);
				}
			}
		}*/
	}
	public void resetTimer(int nextSlide) {
		clearSlide(slideNumber);
		slideNumber = nextSlide;
		localTime = 0;
		System.out.println("timer reset");
	}
	private static void slideTime() {
		try {
			sleep(1);
			//System.out.println(localTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		localTime ++;
	}
}
