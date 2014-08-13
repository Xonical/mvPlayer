package de.xonical.mvplayer.presentation.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageboxPresenter implements Initializable{

	@FXML
	ImageView imageView;

	@FXML
	Label imageLabel;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}


	public void setTextImageLabel(String string) {
		this.imageLabel.setText(string);
	}


	public void setImage(Image image) {
		this.imageView.setImage(image);
	}

}
