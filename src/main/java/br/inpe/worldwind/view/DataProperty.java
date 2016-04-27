package br.inpe.worldwind.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.awt.*;

public class DataProperty {
	private ObjectProperty<Color> color;
	private ObjectProperty<Object> value;
	private StringProperty description;

	public DataProperty(Color color, Object value, String description) {
		this.color = new SimpleObjectProperty<Color>(color);
		this.value = new SimpleObjectProperty<Object>(value);
		this.description = new SimpleStringProperty(description);
	}

	/* simple getters */
	public Color getColor() {
		return color.get();
	}

	public Object getValue() {
		return value.get();
	}

	public String getDescription() {
		return description.get();
	}

	/* property */
	public ObjectProperty<Color> getColorProperty() {
		return color;
	}

	public ObjectProperty<Object> getValueProperty() {
		return value;
	}

	public StringProperty getDescriptionProperty() {
		return description;
	}

	public void setColor(Color value) {
		this.color.set(value);
	}
	
	public void setDescription(String value){
		this.description.set(value);
	}

}
