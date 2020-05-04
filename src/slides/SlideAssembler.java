package slides;

import java.io.File;
import java.util.ArrayList;

import media.Audio;
import media.AudioLayer;
import media.Graphics2D;
import media.Graphics3DLayer;
import media.ImageLayer;
import media.Model;
import media.Shape;
import media.SlideImage;
import media.SlideText;
import media.TextLayer;
import media.Video;
import media.VideoLayer;
import tools.XMLParser;

public class SlideAssembler {
	private static int slideNo;
	private ArrayList<Slide> slides;
	ArrayList<VideoLayer> videoLayers;
	ArrayList<Graphics2D> g2dLayers;
	ArrayList<Graphics3DLayer> graphics3dLayers;
	ArrayList<ImageLayer> imageLayers;
	ArrayList<TextLayer> textLayers;
	ArrayList<AudioLayer> audioLayers;
	
	ArrayList<Audio> audio;
	ArrayList<Graphics2D> graphics2d;
	ArrayList<Model> models;
	ArrayList<SlideImage> images;
	ArrayList<Shape> shapes;
	ArrayList<SlideText> slideTexts;
	ArrayList<Video> videos;
	
	
	public static void createSlides(String xml, ArrayList<Slide> slides, ArrayList<VideoLayer> videoLayers,
														ArrayList<Graphics2D> g2dLayers,
														ArrayList<Graphics3DLayer> graphics3dLayers,
														ArrayList<ImageLayer> imageLayers,
														ArrayList<TextLayer> textLayers,
														ArrayList<AudioLayer> audioLayers,
														ArrayList<Shape> shapes, 
														ArrayList<SlideImage> images, 
														ArrayList<Audio> audio, 
														ArrayList<SlideText> slideTexts,
														ArrayList<Video> videos, 
														ArrayList<Model> models){ //Slide/media arraylists
		/*this.slides = slides;
		this.audioLayers = audioLayers;
		this.videoLayers = videoLayers;
		this.textLayers = textLayers;
		this.imageLayers = imageLayers;
		this.g2dLayers = g2dLayers;
		this.graphics3dLayers = graphics3dLayers;
		
		this.audio = audio;
		this.graphics2d = graphics2d;
		this.models = models;
		this.images =images;
		this.shapes = shapes;
		this.slideText = slideText;
		this.videos = videos;*/
		//XMLParser parser = new XMLParser(null, audioLayers, videoLayers, textLayers, imageLayers, g2dLayers, graphics3dLayers, null, graphics2d, null, null, null, null, null);
		//slideNo = parser.getSlideCount();
		XMLParser parser = new XMLParser(xml, slides, audioLayers, videoLayers, textLayers, imageLayers, g2dLayers, graphics3dLayers, audio, models, images, shapes, slideTexts, videos);
		slideNo = parser.getSlideCount();
		for(int i = 0; i< slideNo;i++) {
			slides.get(i).setAudioLayer(audioLayers.get(i));
			slides.get(i).setGraphics2D(g2dLayers.get(i));
			slides.get(i).setTextLayer(textLayers.get(i));
			slides.get(i).setVideoLayer(videoLayers.get(i));
			slides.get(i).setImageLayer(imageLayers.get(i));
			slides.get(i).setGraphics3DLayer(graphics3dLayers.get(i));
			slides.get(i).applyLayers();

			slides.get(i).getImageLayer().images.get(i).start();
			slides.get(i).getVideoLayer().videos.get(i).play();
			slides.get(i).getAudioLayer().audio.get(i).start();
			slides.get(i).getGraphics2D().shapes.get(i).create();
			slides.get(i).getTextLayer().slideText.get(i).start();
		}
		/*
		slides.get(0).getImageLayer().images.get(0).start();
		slides.get(0).getVideoLayer().videos.get(0).play();
		slides.get(0).getAudioLayer().audio.get(0).start();
		slides.get(0).getGraphics2D().shapes.get(0).create();
		slides.get(0).getTextLayer().slideText.get(0).start();*/

	}
}
