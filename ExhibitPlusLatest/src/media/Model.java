package media;

import java.io.File;
import java.util.ArrayList;

import com.interactivemesh.jfx.importer.ModelImporter;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import com.interactivemesh.jfx.importer.tds.TdsModelImporter;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.input.PickResult;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import main.ExhibitPlus;

/**
 * 3D Model from interactive mesh - InteractiveMesh.org
 * @author - Tom Pound
 * @modifications by - Alex Marchant
 * @Version - 1.2
 * @date - 09/04/20
 */

public class Model {
	
	//Scene of the model with width, height and x,y position
	SubScene modelScene; 
	int width; //Width of SubScene
	int height; //Height of SubScenes
	int xStart; //X value for position
	int yStart; //Y value for position
	int slideNumber;
	boolean modelFail = false;
	PhongMaterial material;
	
	 //Group containing all 3D Elements
	Group modelGroup;
	//Global camera
	Camera camera; 
	//Timeline for animations
	Timeline timeline; 
	//Arraylist of interactive points
	Boolean showControls = true;
	Transform reqTransform;
	Transform reqTranslate;
	Scale reqScale;
	
	public Model(String url, int modelWidth, int modelHeight, int xStart, int yStart, int slideNumber){
		//Width of SubScene as a percentage
		this.width = modelWidth * ExhibitPlus.getDefaultWidth()/100; 
		//Height of SubScene as a percentage
		this.height = modelHeight * ExhibitPlus.getDefaultHeight()/100; 
		this.xStart = (xStart*ExhibitPlus.getDefaultWidth())/100;
		this.yStart = (yStart*ExhibitPlus.getDefaultWidth())/100;
		this.slideNumber = slideNumber;
		//Create the and store scene
		reqTransform = new Rotate(-90, Rotate.X_AXIS);
		reqTranslate = new Translate(0,0,0);
		reqScale =  new Scale(3,3,3);
		material =  new PhongMaterial(Color.BEIGE);
		modelScene = createModel(url);
		
		modelScene.setTranslateX(this.xStart);
		modelScene.setTranslateY(this.yStart);
	}
	
	public Model(String url, int modelWidth, int modelHeight){
		//Width of SubScene
		this.width = modelWidth * ExhibitPlus.getDefaultWidth()/100; 
		//Height of SubScene
		this.height = modelHeight * ExhibitPlus.getDefaultHeight()/100; 
		showControls = false;
		//Create the and store scene
		reqTransform = new Translate();
		reqTranslate = new Translate();
		reqScale = new Scale();
		material =  new PhongMaterial(Color.web("#3AA9B8"));
		modelScene = createModel(url);
	}
	
	//Method to create model scene
	public SubScene createModel(String url) {
		//new camera for subscene
		camera = new PerspectiveCamera(); 

		if(url.startsWith("https://")) {
			//Online source
		}
		else if(url.startsWith("resources")) {
			try {
				File modelFile = new File(url);
				url = modelFile.toURI().toString();
			} catch (Exception e) {
				modelFail = true;
				return null;
			}
		}
		else {
			//Model origin unknown
		}
		
		//FOR 3DS MODELS
		if(url.endsWith(".3ds")) {
			//3DS importer
			ModelImporter tdsImporter = new TdsModelImporter(); 
			tdsImporter.read(url);
			//Store in a node array
			Node[] tdsMesh = (Node[]) tdsImporter.getImport(); 
			tdsImporter.close();
			modelGroup = new Group();
			modelGroup.getChildren().addAll(tdsMesh);
			//Add clickable points
		}
		//FOR STL MODELS
		else if(url.endsWith(".stl")) {
			//STL importer
			StlMeshImporter stlImporter = new StlMeshImporter(); 
	        stlImporter.read(url);
	        //Store in a mesh
	        TriangleMesh cylinderHeadMesh = stlImporter.getImport(); 
	        //Creates new Mesh view
	        MeshView cylinderHeadMeshView = new MeshView();
	        //Sets material of model
	        cylinderHeadMeshView.setMaterial(material);
	        //Sets the mesh for the mesh view
	        cylinderHeadMeshView.setMesh(cylinderHeadMesh); 
	        stlImporter.close();
			modelGroup = new Group();
			modelGroup.getChildren().addAll(cylinderHeadMeshView); 
		}else{
			//Unknown file type
		}
		
        // Create Shape3D
		modelGroup.getTransforms().addAll(reqTransform, reqTranslate, reqScale);
		//Create pivot
        Translate pivot = new Translate(); 
        //Setup rotates
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
        Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
        Rotate zRotate = new Rotate(0, Rotate.Z_AXIS);
		camera.getTransforms().addAll(pivot, yRotate, zRotate, xRotate);
		//modelGroup.getTransforms().add(new Translate(width/2, -height/2),0));
		camera.getTransforms().add(new Translate(-width/2,-height/2,0));
		
		//Setup Animation
        timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(0), 
                        //Start with angle of 0
                        new KeyValue(yRotate.angleProperty(), 0) 
                ),
                new KeyFrame(
                        Duration.seconds(15), 
                        //Finish with angle of 360
                        new KeyValue(yRotate.angleProperty(), 360) 
                )
        );
        //Loop animation	
        timeline.setCycleCount(Timeline.INDEFINITE); 
        //Run animation
        timeline.play(); 
        
        //Pause when mouse is on model
        modelGroup.setOnMouseEntered(e->timeline.pause());
        //Play when mouse is off model
        modelGroup.setOnMouseExited(e->timeline.play());
        
        modelGroup.setOnScroll(e->{
        	if(e.getDeltaY() > 0) {
        		scale(1.1,1.1,1.1);
        	}else{
        		scale(0.9,0.9,0.9);
        	}
        });

        //Set pivot points
        pivot.setX(modelGroup.getTranslateX());
        pivot.setY(modelGroup.getTranslateY());
        pivot.setZ(modelGroup.getTranslateZ());
        
        BorderPane bp = new BorderPane();
        
        //Create subscene
		SubScene modelSubScene = new SubScene(modelGroup, width, height-40, true, SceneAntialiasing.BALANCED);
		//Apply the camera
		modelSubScene.setCamera(camera); 
		bp.setCenter(modelSubScene);
		if(showControls) {
	        GridPane controls = createControls();
			bp.setBottom(controls);
			bp.setAlignment(controls, Pos.CENTER);
		}
		SubScene graphics3d = new SubScene(bp, width, height);
		return graphics3d;
	}
	
	public Group get() {
		return modelGroup;
	}
	public SubScene getScene() {
		return modelScene;
	}
	
	/** method for creating the control panel for the 3D models */
	public GridPane createControls() {
		GridPane gp = new GridPane();
		Button zoomIn = new Button("+");
		Button zoomOut = new Button("-");
		Button rotateLeft = new Button("<");
		Button rotateRight = new Button(">");
		Button play = new Button("Pause");
		
		zoomIn.setOnMouseClicked(e->scale(1.1, 1.1, 1.1));
		zoomOut.setOnMouseClicked(e->scale(0.9, 0.9, 0.9));
		rotateLeft.setOnMouseClicked(e->rotate(0, 0, 20));
		rotateRight.setOnMouseClicked(e->rotate(0, 0, -20));
		play.setOnMouseClicked(e->playAnimation(play));
		
		gp.add(zoomIn, 0,0);
		gp.add(zoomOut, 1,0);
		gp.add(rotateLeft, 2,0);
		gp.add(rotateRight, 3,0);
		gp.add(play, 4, 0);
		gp.setAlignment(Pos.CENTER);
		return gp;
	}
	
	public void playAnimation(Button play) {
		if(timeline.getStatus() == Status.RUNNING) {
			timeline.pause();
			play.setText("Play");
		}else{
			timeline.play();
			play.setText("Pause");
		}
	}
		
	/**Rotate Camera function */
	public void rotateCam(int Xangle, int Yangle, int Zangle) {
		modelScene.getCamera().getTransforms().add(new Rotate(Xangle, Rotate.X_AXIS));
		modelScene.getCamera().getTransforms().add(new Rotate(Yangle, Rotate.Y_AXIS));
		modelScene.getCamera().getTransforms().add(new Rotate(Zangle, Rotate.Z_AXIS));
	}
	/**Move Camera function */
	public void moveCam(int x,int y, int z) {
		camera.getTransforms().add(new Translate(x,y,z));
	}
	
	/**Rotate function */
	public void rotate(int Xangle, int Yangle, int Zangle) {
		modelGroup.getTransforms().add(new Rotate(Xangle, Rotate.X_AXIS));
		modelGroup.getTransforms().add(new Rotate(Yangle, Rotate.Y_AXIS));
		modelGroup.getTransforms().add(new Rotate(Zangle, Rotate.Z_AXIS));
	}
	/**Move function */
	public void move(int x,int y, int z) {
		modelGroup.getTransforms().add(new Translate(x,y,z));
	}
	
	/** Scale function */
	public void scale(double x,double y, double z) {
		modelGroup.getTransforms().add(new Scale(x,y,z));
	}
	
	public SubScene getModelScene() {
		return modelScene;
	}

	public void setModelScene(SubScene modelScene) {
		this.modelScene = modelScene;
	}
	public int getSlideNumber() {
		return(slideNumber);
	}
	
	public int getxStart() {
		return xStart;
	}

	public void setxStart(int xStart) {
		this.xStart = xStart;
	}

	public int getyStart() {
		return yStart;
	}

	public void setyStart(int yStart) {
		this.yStart = yStart;
	}
}
