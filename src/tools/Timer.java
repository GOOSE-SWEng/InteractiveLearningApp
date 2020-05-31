package tools;

import java.util.ArrayList;

import javafx.application.Platform;
import main.InteractiveLearningApp;

public class Timer extends Thread{
	int currentTimer = 0;
	int currentSlideNo = 0;
	int prevSlide = 0;
	public void run() {
		while(InteractiveLearningApp.presRunning == true) {
			if(InteractiveLearningApp.slides.get(currentSlideNo).getDuration() == currentTimer) {
				Platform.runLater(()->InteractiveLearningApp.nextSlide());
				prevSlide = currentSlideNo;
			}
			for(int i = 0; i< InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().size();i++) {
				if(InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().get(i).startTime == currentTimer) {
					int id = InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().get(i).mediaId;
					switch(InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().get(i).mediaType) {
					case AUDIO:
						InteractiveLearningApp.slides.get(currentSlideNo).getSlideAudio().get(id).play();
						break;
					case VIDEO:
						InteractiveLearningApp.slides.get(currentSlideNo).getSlideVideos().get(id).play();
						System.out.println("VIDEO PLAYING");
						break;
					case IMAGE:
						Platform.runLater(() ->{
							try {
								InteractiveLearningApp.slides.get(currentSlideNo).getSlideImages().get(id).start();
							}catch(IndexOutOfBoundsException e) {
								
							}
						});
						
						System.out.println("IMAGE PLAYING");
						break;
					case SHAPE:
						Platform.runLater(()->InteractiveLearningApp.slides.get(currentSlideNo).getSlideShapes().get(id).create());
						System.out.println("SHAPE PLAYING");
						break;
					case TEXT:
						Platform.runLater(()->InteractiveLearningApp.slides.get(currentSlideNo).getSlideTexts().get(id).start());
						break;
					default:
						break;
					}
				}
				if(InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().get(i).endTime == currentTimer){
					int id = InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().get(i).mediaId;
					switch(InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().get(i).mediaType) {
					case AUDIO:
						InteractiveLearningApp.slides.get(currentSlideNo).getSlideAudio().get(id).stop();
						break;
					case VIDEO:
						InteractiveLearningApp.slides.get(currentSlideNo).getSlideVideos().get(id).stop();
						break;
					case IMAGE:
						Platform.runLater(()->InteractiveLearningApp.slides.get(currentSlideNo).getSlideImages().get(id).remove());
						break;
					case SHAPE:
						Platform.runLater(()->{
							try {
								InteractiveLearningApp.slides.get(currentSlideNo).getSlideShapes().get(id).destroy();
							}catch (IndexOutOfBoundsException ioobe) {
								System.err.println("Tried to remove undrawn shape");
							}
						});
						break;
					case TEXT:
						Platform.runLater(()->InteractiveLearningApp.slides.get(currentSlideNo).getSlideTexts().get(id).remove());
						break;
					default:
						break;
					}
				}
			}
			try {
				sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currentTimer++;
		}
	}
	public void resetTimer(int currentSlide) {
		stopSlide();
		currentTimer = 0;
		currentSlideNo = currentSlide;
	}
	public void stopSlide() {
		//for(int i = 0;i<InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements().size();i++) {
		ArrayList<MediaElement> elements = InteractiveLearningApp.slides.get(currentSlideNo).getSlideElements();
		for(int i=0;i<elements.size();i++) {
			int id = elements.get(i).mediaId;
			switch(elements.get(i).mediaType) {
				case AUDIO:
					InteractiveLearningApp.slides.get(currentSlideNo).getSlideAudio().get(id).stop();
					break;
				case VIDEO:
					InteractiveLearningApp.slides.get(currentSlideNo).getSlideVideos().get(id).getPlayer().stop();
					break;
				case IMAGE:
					Platform.runLater(() ->{
						try {
							InteractiveLearningApp.slides.get(prevSlide).getSlideImages().get(id).remove();
						}catch(IndexOutOfBoundsException e) {
							InteractiveLearningApp.slides.get(InteractiveLearningApp.slides.size()-1).getSlideImages().get(id).remove();
						}
					});
					break;
				case SHAPE:
						Platform.runLater(()->{
							try{
								InteractiveLearningApp.slides.get(prevSlide).getSlideShapes().get(id).destroy();
							}catch (IndexOutOfBoundsException e) {
								InteractiveLearningApp.slides.get(InteractiveLearningApp.slides.size()-1).getSlideShapes().get(id).destroy();
							}
						});
					break;
				case TEXT:
					Platform.runLater(()->{
						try {
							InteractiveLearningApp.slides.get(prevSlide).getSlideTexts().get(id).remove();
						}catch(IndexOutOfBoundsException e) {
							InteractiveLearningApp.slides.get(InteractiveLearningApp.slides.size()-1).getSlideTexts().get(id).remove();
						}
					});
					break;
			default:
				break;
			}
		}
		
	}
}
