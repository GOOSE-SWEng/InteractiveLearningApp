package tools;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javafx.stage.Stage;
import main.ExhibitPlus;
import media.*;
import slides.Slide;

/**
 * reads tags in an XML file and finds the media file to store in array lists
 * @author - Tom Pound
 * @contributors - Ivy Price, Alex Marchant, Alex Kneller
 * @version - 1.3
 * @date - 06/05/20
 * Class for the XML parser
 */
public class XMLParser {
	//Initialise the schema and xml for parsing
	static String schemaName = "src/schema.xsd";
	static Schema schema = null;
	static String xmlName = "src/xml.xml";
	static Document xmlDoc;
	int slideCurrent = 0;
	int currentSlide = 0; //Slide counter
	int slideCount; //Total slide count
	
	//Slide arrayLists
	public static ArrayList<Slide> slides;
	//All layer arrayLists
	public static ArrayList<AudioLayer> audioLayers;
	public static ArrayList<VideoLayer> videoLayers;
	public static ArrayList<TextLayer> textLayers;
	public static ArrayList<ImageLayer> imageLayers;
	public static ArrayList<Graphics2D> g2dLayers;
	public static ArrayList<Graphics3DLayer> graphics3DLayers;
	//All media elements arrayLists
	public static ArrayList<Audio> audio;
	public static ArrayList<Model> models;
	public static ArrayList<SlideImage> images;
	public static ArrayList<Shape> shapes;
	public static ArrayList<SlideText> slideText;
	public static ArrayList<Video> videos;
		
	/**
	 * 
	 * @param fileDir - directory of the XML file
	 * @param slides - array lists containing all slides
	 * @param audioLayers - array list to store audio layers
	 * @param videoLayers - array list to store video layers
	 * @param textLayers - array list to store text layers
	 * @param imageLayers - array list to store image layers
	 * @param g2dLayers - array list to store 2d graphics layers
	 * @param graphics3DLayers - array list to store 3d graphics layers
	 * @param audio - array list to store audio files 
	 * @param models - array list to store 3d models
	 * @param images - array list to store images
	 * @param shapes - array list to store shapes
	 * @param slideText - array list to store text
	 * @param videos - array list to store video
	 */
	public XMLParser(String fileDir, ArrayList<Slide> slides, 
									 ArrayList<AudioLayer> audioLayers, 
								 	 ArrayList<VideoLayer> videoLayers, 
									 ArrayList<TextLayer> textLayers, 
									 ArrayList<ImageLayer> imageLayers,
									 ArrayList<Graphics2D> g2dLayers, 
									 ArrayList<Graphics3DLayer> graphics3DLayers,
									 ArrayList<Audio> audio,
									 ArrayList<Model> models,
									 ArrayList<SlideImage> images,
									 ArrayList<Shape> shapes,
									 ArrayList<SlideText> slideText, 
									 ArrayList<Video> videos,
									 Stage mainStage) {
		//Store all arrayLists from the main program
		this.slides = slides;
		this.audioLayers = audioLayers;
		this.videoLayers = videoLayers;
		this.textLayers = textLayers;
		this.imageLayers = imageLayers;
		this.g2dLayers = g2dLayers;
		this.graphics3DLayers = graphics3DLayers;
		
		this.audio = audio;
		this.models = models;
		this.images =images;
		this.shapes = shapes;
		this.slideText = slideText;
		this.videos = videos;
		
		//Store the document in memory
		xmlDoc = getDocument(fileDir); 
		xmlDoc.getDocumentElement().normalize();
		
		//Validate the XML against the schema
		/*try {
			validateXML(xmlDoc);
		}catch(SAXException|IOException e) {
		}*/
		
		//print content of XML to console
		//can be removed for final build
		//DOC INFO TAGS
		//Get docInfo tag
		Node documentsInfo = xmlDoc.getElementsByTagName("documentinfo").item(0); 
		//Create nodelist of subtags
		NodeList docInfoNodeList = documentsInfo.getChildNodes();
		//Initialise node
		Node docInfoNode; 
		
		/*
		 * For loop loops through amount of subtags
		 * stores the current node
		 * and stores it if it is a printable element 
		 */
		for(int i=0;i<docInfoNodeList.getLength();i++) { 
			docInfoNode = docInfoNodeList.item(i); 
			if(docInfoNode instanceof Element) { 
				//STORE HERE
			}
		}

		//DEFAULTS
		//Get defaults tag
		NodeList defaults = xmlDoc.getElementsByTagName("defaults"); 
		//Gets name of default tag
		//Create nodelist of subtags
		NodeList defaultsNodeList = defaults.item(0).getChildNodes(); 
		
		//Initialise node
		Node defaultNode; 
		
		/* Updates all application default settings
		 * Loop through default tags 
		 */
		for(int i=0;i<defaultsNodeList.getLength();i++) { 
			//Store current node
			defaultNode = defaultsNodeList.item(i); 
			//If the node is a usable element
			if(defaultNode instanceof Element) { 
				//Update default BG colour
				if(defaultNode.getNodeName().equals("backgroundcolor")) {
					ExhibitPlus.setDefaultBGColour(defaultNode.getTextContent()); 
				}
				//Update default Font
				else if(defaultNode.getNodeName().equals("font")) {
					ExhibitPlus.setDefaultFont(defaultNode.getTextContent()); 
				}
				//Update default Font size
				else if(defaultNode.getNodeName().equals("fontsize")) {
					ExhibitPlus.setDefaultTextSize(Integer.parseInt(defaultNode.getTextContent())); 
				}
				//Update default Font colour
				else if(defaultNode.getNodeName().equals("fontcolor")) {
					ExhibitPlus.setDefaultFontColour(defaultNode.getTextContent()); 
				}
				//Update default line colour
				else if(defaultNode.getNodeName().equals("linecolor")) {
					ExhibitPlus.setDefaultLineColour(defaultNode.getTextContent()); 
				}
				//Update default fill colour
				else if(defaultNode.getNodeName().equals("fillcolor")) {
					ExhibitPlus.setDefaultFillColour(defaultNode.getTextContent()); 
				}
				//Update default slide width
				else if(defaultNode.getNodeName().equals("slidewidth")) {
					ExhibitPlus.setDefaultWidth(Integer.parseInt(defaultNode.getTextContent())); 
				}
				//Update default slide height
				else if(defaultNode.getNodeName().equals("slideheight")) {
					ExhibitPlus.setDefaultHeight(Integer.parseInt(defaultNode.getTextContent())); 
				}
				else {
					System.out.println(defaultNode.getNodeName() + " is an unrecognisable default tag");
				}
			}
		}
		
		/////////////////// SLIDES ///////////////////
		//Create list of slide tags
		NodeList slideList = xmlDoc.getElementsByTagName("slide"); 
		//Show number of slides
		slideCount = slideList.getLength();
		/*
		 * Cycles through each slide tag
		 * Gets first tag name
		 * Gets tags from slides
		 * Creates a node for each slide
		 */
		for(int j=0;j<slideList.getLength();j++) {
			int slideDuration = 0;
			String slideTitle = null;
			//each time a new slide is found
			Node currentNode = slideList.item(j);
			if(currentNode.hasAttributes()) {
				NamedNodeMap attMap = currentNode.getAttributes();
				for(int i=0; i< attMap.getLength();i++) {
					Node attNode = attMap.item(i);
					if(attNode.getNodeName().equals("id")) {
						slideTitle = attNode.getNodeValue();
					}
					else if(attNode.getNodeName().equals("duration")) {
						slideDuration = Integer.parseInt(attNode.getNodeValue());
					}
					else {
						//Unrecognised node name
					}
				}
			//Create a new slide for each loop
			Slide newSlide = new Slide(mainStage, slideTitle, ExhibitPlus.getStageWidth(),ExhibitPlus.getStageHeight(),
									   videoLayers, audioLayers, imageLayers, textLayers, g2dLayers, 
					                   graphics3DLayers, shapes, images, audio, slideText, videos, models, slideDuration);
			slides.add(newSlide);
			}
			else{
				//No attributes
			}
			//If the node has sub nodes
			if(currentNode.hasChildNodes()) {
				getSubNodes(currentNode.getChildNodes());
			}
			else{
				//Has no sub nodes
			}
			currentSlide++;
		}
	}
	//Loop for sub tags
	public void getSubNodes(NodeList subNodeList) {
		for(int i =0;i<subNodeList.getLength();i++) {
			Node currentNode = subNodeList.item(i);
			if(currentNode instanceof Element) {
				if(currentNode.getNodeName() == "video") {
					videoParse(currentNode);
				}
				else if(currentNode.getNodeName() == "text") {
					textParse(currentNode);
				}
				else if(currentNode.getNodeName() == "audio") {
					audioParse(currentNode);
				}
				else if(currentNode.getNodeName() == "image") {
					imageParse(currentNode);
				}
				else if(currentNode.getNodeName() == "shape") {
					shapeParse(currentNode);
				}
				else if(currentNode.getNodeName() == "line") {
					lineParse(currentNode);
				}
				else if(currentNode.getNodeName() == "model") {
					modelParse(currentNode);
				}
				else{
					if(currentNode instanceof Element) {
						//Unrecognised slide node name
					}
				}
			}
		}
	}
	
	/**
	 * Create a factory to build a document
	 * Method ignores commented out XML and whitespace
	 * takes the directory for the XML file as a parameter
	 * returns the xml file as a readable document
	 */
	public static Document getDocument(String name) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true); 
			//factory.setValidating(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(new InputSource(name)); 
		}
		catch(ParserConfigurationException | IOException | SAXException e) {
			//Document failed
		}
		return null;
	}
	
	/** Validation of the XML against the schema */
//	public void validateXML(Document xmlDoc) throws SAXException, IOException {
//		try {
//			  String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
//			  SchemaFactory factory = SchemaFactory.newInstance(language);
//			  schema = factory.newSchema(new File(schemaName));
//		} catch (Exception e) {
//			    e.printStackTrace();
//		}
//		Validator validator = schema.newValidator();
//		validator.validate(new DOMSource(xmlDoc));
//	}
	
	/**
	 * if a video tag is found
	 * @param currentNode - current node of the xml
	 */
	public void videoParse(Node currentNode) {
		//Defaults
		String urlName = "";
		String subUrlName = "";
		Boolean loop = false;
		int xStart = 0;
		int yStart = 0;
		int startTime = 0;
		
		//If the tag has attributes
		if(currentNode.hasAttributes()) {
			//Place attributes into an attribute map
			NamedNodeMap attMap = currentNode.getAttributes(); 
			//Loop through attribute map and compare node names
			for(int j=0 ; j<attMap.getLength();j++) { 
				//If urlname is found
				if(attMap.item(j).getNodeName().equals("urlname")) {
					//Store the value in the node
					urlName = attMap.item(j).getNodeValue(); 
				}
				//If suburlname attribute is found
				else if(attMap.item(j).getNodeName().equals("suburlname")) {
					//Store the value in the node
					subUrlName = attMap.item(j).getNodeValue();
				}
				//If loop attribute is found
				else if(attMap.item(j).getNodeName().equals("loop")) { 
					//Store the value in the node
					loop = Boolean.parseBoolean(attMap.item(j).getNodeValue()); 
				}
				//If xstart attribute is found
				else if(attMap.item(j).getNodeName().equals("xstart")) {
					//Store the value in the node
					xStart = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				//If ystart attribute is found
				else if(attMap.item(j).getNodeName().equals("ystart")) {
					//Store the value in the node
					yStart = Integer.parseInt(attMap.item(j).getNodeValue()); 
				}
				//If starttime attribute is found
				else if(attMap.item(j).getNodeName().equals("starttime")) {
					//Store the value in the node
					startTime = Integer.parseInt(attMap.item(j).getNodeValue()); 
				}
				//If tag is unrecognised, print message
				else{
					//Not recognised in the video tag
				}
			}
		}else{}
		if(currentNode.hasChildNodes()) {
			getSubNodes(currentNode.getChildNodes());
		}else{}
		
		try {
			//Add a new video to this layer
			videoLayers.get(currentSlide).addVideo(urlName, subUrlName, startTime, loop, xStart, yStart, currentSlide);
		}catch(IOException e) {
			//Video Unavailable
		}
	}
	
	/**
	 * if image tag is found
	 * works largely the same as video parser
	 * refer to above method for details 
	 */
	public void imageParse(Node currentNode) {
		String urlName = null;
		int xStart=0;
		int yStart=0;
		int width=0;
		int height=0;
		int startTime=0;
		int endTime=0;
		
		if(currentNode.hasAttributes()) {
			NamedNodeMap attMap = currentNode.getAttributes();
			for(int j=0 ; j<attMap.getLength();j++) {
				if(attMap.item(j).getNodeName().equals("urlname")) {
					urlName = attMap.item(j).getNodeValue();
				}
				else if(attMap.item(j).getNodeName().equals("xstart")) {
					xStart = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("ystart")) {
					yStart = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("endtime")) {
					endTime = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("starttime")) {
					startTime = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("width")) {
					width = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("height")) {
					height = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else{
				}
			}
		}
		else{
			//No Attributes for this node
		}
		if(currentNode.hasChildNodes()) {
			getSubNodes(currentNode.getChildNodes());
		}
		else{
			//No sub nodes
		}
		imageLayers.get(currentSlide).add(urlName, xStart,yStart,width,height,startTime,endTime,currentSlide);
	}
	
	/**
	 * if 2D graphics tag is found
	 * works largely the same as video parser
	 * refer to above method for details 
	 */
	public void shapeParse(Node currentNode) {
		//Shading variables
		Boolean shading = false;
		int x1=0;
		int y1=0;
		String colour1 =null;
		int x2=0;
		int y2=0;
		String colour2= null;
		Boolean cyclic= false;
		
		//Shape variables
		String type=null;
		String id = null;
		float xStart=0;
		float yStart=0;
		float width=0;
		float height=0;
		String fillColor = null;
		int startTime=0;
		int endTime=0;
		if(currentNode.hasAttributes()) {
			NamedNodeMap attMap = currentNode.getAttributes();
			for(int j=0 ; j<attMap.getLength();j++) {
				if(attMap.item(j).getNodeName().equals("type")) {
					if(attMap.item(j).getTextContent().equals("rectangle") | attMap.item(j).getTextContent().equals("oval")) {
						type = attMap.item(j).getNodeValue();
					}
				}
				else if(attMap.item(j).getNodeName().equals("width")) {
					width = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("height")) {
					height = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("xstart")) {
					xStart = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("ystart")) {
					yStart = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("starttime")) {
					startTime = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("endtime")) {
					endTime = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("fillcolor")) {
					fillColor = attMap.item(j).getNodeValue();
				}
				else{
					//not recognized in the shape tag
				}
			}
		}
		else{}
		//Shading
		if(currentNode.hasChildNodes()) {
			for(int k = 0; k<currentNode.getChildNodes().getLength();k++) {
				if(currentNode.getChildNodes().item(k).getNodeName().equals("shading")) {
					shading = true;
					currentNode = currentNode.getChildNodes().item(k);
					NamedNodeMap attMap = currentNode.getAttributes();
					attMap = currentNode.getAttributes();
					for(int i=0 ; i<attMap.getLength();i++) {
						if(attMap.item(i).getNodeName().equals("x1")) {
							x1 = Integer.parseInt(attMap.item(i).getNodeValue());
						}
						else if(attMap.item(i).getNodeName().equals("y1")) {
							y1 = Integer.parseInt(attMap.item(i).getNodeValue());
						}
						else if(attMap.item(i).getNodeName().equals("x2")) {
							x2 = Integer.parseInt(attMap.item(i).getNodeValue());
						}
						else if(attMap.item(i).getNodeName().equals("y2")) {
							y2 = Integer.parseInt(attMap.item(i).getNodeValue());
						}
						else if(attMap.item(i).getNodeName().equals("color1")) {
							colour1 = attMap.item(i).getNodeValue();
						}
						else if(attMap.item(i).getNodeName().equals("color2")) {
							colour2 = attMap.item(i).getNodeValue();
						}
						else if(attMap.item(i).getNodeName().equals("cyclic")) {
							cyclic = Boolean.parseBoolean(attMap.item(i).getNodeValue());
						}
						else {
							//Not recognized in the shading tag
						}
					}
				}else{
					shading = false;
				}
			}
		}
		else{}
		if(type.equals("oval") & !shading) {
			g2dLayers.get(currentSlide).registerOval(xStart, yStart,width, height, fillColor, startTime, endTime, currentSlide);
		}else if(type.equals("rectangle") & !shading) {
			g2dLayers.get(currentSlide).registerRectangle(xStart, yStart,width, height, fillColor, id, startTime, endTime, currentSlide);
		}else if(type.equals("oval") & shading) {
			g2dLayers.get(currentSlide).registerOval(xStart, yStart, width, height, x1, y1, colour1, x2, y2, colour2, cyclic, startTime, endTime, currentSlide);
		}else if(type.equals("rectangle") & shading) {
			g2dLayers.get(currentSlide).registerRectangle(xStart, yStart, width, height, x1, y1, colour1, x2, y2, colour2, cyclic, startTime, endTime, currentSlide);
		}else {}
	}
	
	/** parses text if the text tag is found */
	public void textParse(Node currentNode) {
		textLayers.get(currentSlide).add(currentNode, currentSlide);
	}
	
	/**
	 * if audio tag is found
	 * works largely the same as video parser
	 * refer to above method for details 
	 */
	public void audioParse(Node currentNode) {
		String urlName=null;
		int startTime = 0;
		Boolean loop = null;
		Boolean controls = false;
		
		if(currentNode.hasAttributes()) {
			NamedNodeMap attMap = currentNode.getAttributes();
			for(int j=0 ; j<attMap.getLength();j++) {
				if(attMap.item(j).getNodeName().equals("urlname")) {
					urlName = attMap.item(j).getNodeValue();
				}
				else if(attMap.item(j).getNodeName().equals("starttime")) {
					startTime = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("loop")) {
					loop = Boolean.parseBoolean(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("controls")) {
					controls = Boolean.parseBoolean(attMap.item(j).getNodeValue());
				}
				else{
					//Not recognized in the audio tag
				}
			}
		}
		else{}
		if(currentNode.hasChildNodes()) {
			getSubNodes(currentNode.getChildNodes());
		}
		else{}
		audioLayers.get(currentSlide).add(urlName,startTime, loop, controls,37,85,30,10,currentSlide);
	}
	/**
	 * if line tag is found
	 * refer to videoParse method for more details
	 */
	public void lineParse(Node currentNode) {
		float xStart=0;
		float yStart=0;
		float xEnd=0;
		float yEnd=0;
		String lineColor=null;
		int startTime=0;
		int endTime=0;
		
		if(currentNode.hasAttributes()) {
			NamedNodeMap attMap = currentNode.getAttributes();
			for(int j=0 ; j<attMap.getLength();j++) {
				if(attMap.item(j).getNodeName().equals("xend")) {
					xEnd = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("xstart")) {
					xStart = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("ystart")) {
					yStart = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("endtime")) {
					endTime = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("starttime")) {
					startTime = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("yend")) {
					yEnd = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("linecolor")) {
					lineColor = attMap.item(j).getNodeValue();
				}
				else{
					//Not recognized in the line tag
				}
			}
		}
		else{}
		if(currentNode.hasChildNodes()) {
			getSubNodes(currentNode.getChildNodes());
		}
		else{}
		g2dLayers.get(currentSlide).registerLine(xStart,xEnd, yStart, yEnd,lineColor,startTime, endTime, currentSlide);
	}
	
	/** if model tag is found */
	public void modelParse(Node currentNode) {
		//Defaults
		String urlName = null;
		int xStart = 0;
		int yStart = 0;
		int modelWidth = 0;
		int modelHeight = 0;
		
		if(currentNode.hasAttributes()) {
			//Create an attribute map
			NamedNodeMap attMap = currentNode.getAttributes(); 
			//Loop through all attributes in the map
			for(int j=0 ; j<attMap.getLength();j++) { 
				if(attMap.item(j).getNodeName().equals("urlname")) {
					urlName = attMap.item(j).getNodeValue();
				}
				else if(attMap.item(j).getNodeName().equals("xstart")) {
					xStart = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("ystart")) {
					yStart = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("width")) {
					modelWidth = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else if(attMap.item(j).getNodeName().equals("height")) {
					modelHeight = Integer.parseInt(attMap.item(j).getNodeValue());
				}
				else{
					//Not recognized in the image tag
				}
			}
		}
		else{}
		if(currentNode.hasChildNodes()) {
			getSubNodes(currentNode.getChildNodes());
		}
		else{}
		graphics3DLayers.get(currentSlide).addModel(urlName, modelWidth, modelHeight, xStart, yStart);;
	}

	public int getSlideCount() {
		return slideCount;
	}

	public void setSlideCount(int slideCount) {
		this.slideCount = slideCount;
	}
}
