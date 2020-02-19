import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ToolBar {
	Button close;
	Button open;
	Text title;
	public SubScene createToolBar(int winWidth) {
		GridPane gp = new GridPane();
		
		
		
		SubScene toolBar = new SubScene(gp, winWidth, 20);
		return toolBar;
	}
	public void updateToolBar() {
		
		
		
	}
}
