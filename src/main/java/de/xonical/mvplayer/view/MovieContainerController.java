package de.xonical.mvplayer.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MovieContainerController {

	@FXML
	private Button showNameButton;

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleAction(ActionEvent e){
    	System.out.println("Foo");
    }

	public void setMainApp(PlaygroundController playgroundController) {
		//personTable.setItems(mainApp.getSubDirectoryData());
	}



}
