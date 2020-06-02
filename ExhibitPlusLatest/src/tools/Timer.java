package tools;

import java.util.ArrayList;
import javafx.application.Platform;
import main.ExhibitPlus;
import media.*;
import slides.Slide;

/**
 * Class program timing 
 * @author Yilia Lui, Alex Marchant, Tom Pound
 * @version - 2.1
 * @date - 01/06/20
 *
 */

public class Timer extends Thread{
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
	
	/**
	 * generic object used to store timing information of all objects
	 * @param shapes - array list of shapers
	 * @param audio - array list containing audio files
	 * @param image - array list containing images
	 * @param text - array list containing text
	 * @param video - array list contatining video files
	 * @param model - array list containing models
	 * @param graphics2d - array list contaiing graphics layers
	 * @param audioLayers - array list containing audio layers
	 * @param imageLayers - array list containing image layers
	 * @param textLayers - array list containing text layers
	 * @param videoLayers - array list containing video layers
	 * @param graphics3DLayers - array list for 3D model layers
	 * @param slides - array list of all sldes in presentation
	 */
	public Timer(ArrayList<Shape> shapes, 
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
		timingObjects = new ArrayList<TimingObject>(); 
		
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
			TimingObject TO = new TimingObject(slideNumber,startTime, false, "video",i);
			timingObjects.add(TO);
		}
		for (int i =0; i<model.size();i++) {//always there
			int slideNumber = model.get(i).getSlideNumber();
			TimingObject TO = new TimingObject(slideNumber,0, false, "model",i);
			timingObjects.add(TO);
		}
	}
	
	public void run() {
		while(true){
			//check for slide change
			slideTime();
			if(slideNumber != -1) {
				try {
					if((localTime >= slides.get(slideNumber).getEndTime())&(slides.get(slideNumber).getEndTime()>0)) {
						Platform.runLater(()->ExhibitPlus.nextSlide());
						localTime = 0;
					}
				}catch(IndexOutOfBoundsException e) {
					break;
				}
			}

			for (int i = 0; i < timingObjects.size(); i++) {
				if (timingObjects.get(i).getSlideNumber() == slideNumber) {
					if ((timingObjects.get(i).getStartTime()<=localTime) & (timingObjects.get(i).getEndTime()>=localTime)) {
						if (timingObjects.get(i).isDrawn()) {
							//do nothing
						}
						else if(timingObjects.get(i).isDrawn() == false){
							//draw it
							String type = timingObjects.get(i).getType();
							if (type == "shape") {
								final int j = i;
								Platform.runLater(()->graphics2d.get(slideNumber).add(timingObjects.get(j).getIndex()));
							}
							else if(type == "audio") {
								final int j = i;
								Platform.runLater(()->audioLayers.get(slideNumber).add(timingObjects.get(j).getIndex()));
							}
							else if(type == "image") {
								final int j = i;
								Platform.runLater(()->imageLayers.get(slideNumber).add(timingObjects.get(j).getIndex()));
							}
							else if(type == "text") {
								final int j = i;
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
							Platform.runLater(()->graphics2d.get(timingObjects.get(j).getSlideNumber()).remove(timingObjects.get(j).getIndex()));
						}
						else if(type == "audio") {
							final int j = i;
							Platform.runLater(()->audioLayers.get(timingObjects.get(j).getSlideNumber()).remove(timingObjects.get(j).getIndex()));
						}
						else if(type == "image") {
							final int j = i;
							Platform.runLater(()->imageLayers.get(timingObjects.get(j).getSlideNumber()).remove(timingObjects.get(j).getIndex()));
						}
						else if(type == "text") {
							final int j = i;
							Platform.runLater(()->textLayers.get(timingObjects.get(j).getSlideNumber()).remove(timingObjects.get(j).getIndex()));
						}
						else if(type == "video") {
							final int j = i;
							Platform.runLater(()->videoLayers.get(timingObjects.get(j).getSlideNumber()).remove(timingObjects.get(j).getIndex()));
						}
						else if(type == "model") {
							final int j = i;
							Platform.runLater(()->graphics3DLayers.get(timingObjects.get(j).getSlideNumber()).remove(timingObjects.get(j).getIndex()));
						}
						timingObjects.get(i).setDrawn(false);
					}
				}
			}
		}
	}
	public void resetTimer(int nextSlide) {
		slideNumber = nextSlide;
		localTime = 0;
	}
	private static void slideTime() {
		try {
			sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		localTime ++;
	}
}
