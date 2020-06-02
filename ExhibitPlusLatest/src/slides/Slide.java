package slides;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.ExhibitPlus;
import userInterface.ResizeBar;
import userInterface.ToolBar;
import media.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * Class for creating slides and adding media to the slides
 * @author Alex Marchant, Tom Pound
 * @version - 1.4
 * @date - 01/06/20
 *
 */

public class Slide {
	private SubScene toolBar;
	private SubScene resizeBar = null;
	//global media layer variables
	private VideoLayer videoLayer;
	private AudioLayer audioLayer;
	private ImageLayer imageLayer;
	private TextLayer textLayer;
	private Graphics2D graphics2D;
	private Graphics3DLayer graphics3DLayer;
	private Scene slide;
	
	//border pane for positioning
	private BorderPane bp = new BorderPane();
	//base stack pane for non interactive elements
	private StackPane spB = new StackPane();
	//top stack pane for interactive elements
	private StackPane intSp = new StackPane();
	//Middle stackpane for text
	private StackPane spM = new StackPane();
	//stackpane to stack the stackpanes
	private StackPane slideSp = new StackPane();
	
	//offsets for positioning
	private double xOff;
	private double yOff;

	//slide duration
	private int duration;
	
	/**
	 * Slide Constructor Method
	 * @param mainStage - main stage of window, layers are placed on top of
	 * @param id - current slide title
	 * @param width - width of slide
	 * @param height - width of slide
	 * @param vl - video layer array list
	 * @param al - audio layer array list
	 * @param il - image layer array list
	 * @param tl - text layer array list
	 * @param G2D - 2D graphics array list
	 * @param G3D - 3D graphics array list
	 * @param shapes - array list of shapes
	 * @param images - array list containing images
	 * @param audio - array list containing audio files
	 * @param slideTexts - array list containing text
	 * @param slideVideos - array list containing video files
	 * @param models - array list containing models
	 * @param duration - duration of slide
	 */
	public Slide(Stage mainStage, String id, int width, int height,
			     ArrayList<VideoLayer> vl, 
			     ArrayList<AudioLayer> al, 
			     ArrayList<ImageLayer> il, 
			     ArrayList<TextLayer> tl, 
			     ArrayList<Graphics2D> G2D, 
			     ArrayList<Graphics3DLayer> G3D,
			     ArrayList<Shape> shapes, 
			     ArrayList<SlideImage> images, 
			     ArrayList<Audio> audio, 
			     ArrayList<SlideText> slideTexts,
			     ArrayList<Video> slideVideos,
			     ArrayList<Model> models, int duration) {
		this.duration = duration;
		//create toolbar and resize bar
		toolBar = ToolBar.createToolBar(width, id, ExhibitPlus.exhibitMode);
		if(ExhibitPlus.exhibitMode == false) {
			resizeBar = ResizeBar.CreateResizeBar(width);
		}
		
		//setup the stackpanes
		spB.setAlignment(Pos.TOP_LEFT);
		spB.setPickOnBounds(false);
		
		spM.setAlignment(Pos.TOP_LEFT);
		spM.setPickOnBounds(false);

		intSp.setAlignment(Pos.TOP_LEFT);
		intSp.setPickOnBounds(false);
		
		slideSp.setAlignment(Pos.TOP_LEFT);
		
		//create the media layers and add them to the slide
		graphics2D = new Graphics2D(shapes,spB);
		textLayer = new TextLayer(slideTexts,spM);
		audioLayer = new AudioLayer(audio,intSp);
		imageLayer = new ImageLayer(images,spB);
		videoLayer = new VideoLayer(slideVideos,intSp);
		graphics3DLayer = new Graphics3DLayer(models,intSp);
		//add the layers to the correct arrayList
		G2D.add(graphics2D);
		vl.add(videoLayer);
		al.add(audioLayer);
		il.add(imageLayer);
		tl.add(textLayer);
		G3D.add(graphics3DLayer);
		//get slide number to pass into the 3d graphics layer
		int slideNumber  = G3D.indexOf(graphics3DLayer);
		graphics3DLayer.setSlideNumber(slideNumber);
		
		toolBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOff = event.getSceneX();
				yOff = event.getSceneY();
			}
		});
		
		//Move window with mouse
		toolBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainStage.setX(event.getScreenX() - xOff);
				mainStage.setY(event.getScreenY() - yOff);	
			}
		});
		//add the stackpanes to the main stackpane
		slideSp.getChildren().addAll(spB, spM, intSp);
		
		//add elements to the border panes
		bp.setTop(toolBar);
		bp.setCenter(slideSp);
		bp.setBottom(resizeBar);
		
		//set the size of the slide
		if(ExhibitPlus.exhibitMode ==true) {
			slide = new Scene(bp,ExhibitPlus.getStageWidth(), ExhibitPlus.getStageHeight());
		}else {
			slide = new Scene(bp,width, height+80);
		}
		if(ExhibitPlus.style.equals("default")) {
			slide.getStylesheets().add("style/ContentScreen/contentScreen.css");
		}else if(ExhibitPlus.style.equals("nightmode")) {
			slide.getStylesheets().add("style/ContentScreen/contentScreenNight.css");
		}else if(ExhibitPlus.style.equals("colourblind")) {
			slide.getStylesheets().add("style/ContentScreen/contentScreenCB.css");
		}else {
			//Unknown style scheme
		}
	}
	
	/** update stylesheet to default style */
	public void defaultStyle() {
		slide.getStylesheets().clear();
		slide.getStylesheets().add("style/ContentScreen/contentScreen.css");
	}
	
	/** update stylesheet to night mode style */
	public void nightmodeStyle() {
		slide.getStylesheets().clear();
		slide.getStylesheets().add("style/ContentScreen/contentScreenNight.css");
	}
	
	/** update stylesheet to colourblind style */
	public void colourblindStyle() {
		slide.getStylesheets().clear();
		slide.getStylesheets().add("style/ContentScreen/contentScreenCB.css");
	}
	
	public Scene getSlide() {
		return slide;
	}
	public int getEndTime() {
		return(duration);
	}
}
