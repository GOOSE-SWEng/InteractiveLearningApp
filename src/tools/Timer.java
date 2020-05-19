package tools;

import java.util.ArrayList;

import javafx.application.Platform;
import main.InteractiveLearningApp;

public class Timer extends Thread{
	int currentTimer = 0;
	int currentSlideNo = 0;
	//public ArrayList<MediaElement> mediaElements;
	public void run() {
		while(InteractiveLearningApp.presRunning == true) {
			if(InteractiveLearningApp.slides.get(currentSlideNo).getDuration() == currentTimer) {
				Platform.runLater(()->InteractiveLearningApp.nextSlide());
			}
			for(int i = 0; i< InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().size();i++) {
				if(InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().get(i).startTime == currentTimer) {
					int id = InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().get(i).mediaId;
					switch(InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().get(i).mediaType) {
					case AUDIO:
						InteractiveLearningApp.slides.get(currentSlideNo).getAudioLayer().audio.get(id-1).start();
						System.out.println("AUDIO PLAYING");
						break;
					case VIDEO:
						InteractiveLearningApp.slides.get(currentSlideNo).getVideoLayer().videos.get(id-1).play();
						System.out.println("VIDEO PLAYING");
						break;
					case IMAGE:
						Platform.runLater(() ->InteractiveLearningApp.slides.get(currentSlideNo).getImageLayer().images.get(id-1).start());
						System.out.println("IMAGE PLAYING");
						break;
					case GRAPHICS2D:
						InteractiveLearningApp.slides.get(currentSlideNo).getGraphics2D().shapes.get(id-1).create();
						System.out.println("SHAPE PLAYING");
						break;
					/*case GRAPHICS3D:
						InteractiveLearningApp.slides.get(currentSlideNo).getGraphics3DLayer().models.get(i).;
						break;*/
					case TEXT:
						InteractiveLearningApp.slides.get(currentSlideNo).getTextLayer().slideText.get(id-1).start();
						System.out.println("TEXT PLAYING");
						break;
					default:
						break;
					}
				}
				if(InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().get(i).endTime == currentTimer){
					int id = InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().get(i).mediaId;
					switch(InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().get(i).mediaType) {
					case AUDIO:
						InteractiveLearningApp.slides.get(currentSlideNo).getAudioLayer().audio.get(id-1).stop();
						System.out.println("AUDIO STOPPING");
						break;
					case VIDEO:
						InteractiveLearningApp.slides.get(currentSlideNo).getVideoLayer().videos.get(id-1).remove();
						System.out.println("VIDEO STOPPING");
						break;
					case IMAGE:
						Platform.runLater(()->InteractiveLearningApp.slides.get(currentSlideNo).getImageLayer().images.get(id-1).remove());
						System.out.println("IMAGE STOPPING");
						break;
					case GRAPHICS2D:
						InteractiveLearningApp.slides.get(currentSlideNo).getGraphics2D().shapes.get(id-1).destroy();
						break;
					/*case GRAPHICS3D:
						InteractiveLearningApp.slides.get(currentSlideNo).getGraphics3DLayer().models.get(i).;
						break;*/
					case TEXT:
						InteractiveLearningApp.slides.get(currentSlideNo).getTextLayer().slideText.get(id-1).remove();
						break;
					default:
						break;
					}
				}
			}
			try {
				sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentTimer++;
		}
	}
	public void resetTimer(int currentSlide) {
		currentTimer = 0;
		currentSlideNo = currentSlide;
	}
}
