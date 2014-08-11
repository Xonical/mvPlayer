package listViewTest;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movie {
	private StringProperty movieName;

	public final void setMovieName(String value) {
		movieNameProperty().set(value);
	}

	public final String getMovieName() {
		return movieNameProperty().get();
	}

	public StringProperty movieNameProperty() {
		if (movieName == null) {
			movieName = new SimpleStringProperty();
		}
		return movieName;
	}

	public Movie(String name) {
		setMovieName(name);
	}
}