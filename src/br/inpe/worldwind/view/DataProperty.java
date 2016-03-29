package br.inpe.worldwind.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DataProperty {
	private ObjectProperty<java.awt.Color> color;
	private ObjectProperty<Object> value;
	private StringProperty description;

	public DataProperty(java.awt.Color color, Object value, String description) {
		this.color = new SimpleObjectProperty<java.awt.Color>(color);
		this.value = new SimpleObjectProperty<Object>(value);
		this.description = new SimpleStringProperty(description);
	}

	/* simple getters */
	public java.awt.Color getColor() {
		return color.get();
	}

	public Object getValue() {
		return value.get();
	}

	public String getDescription() {
		return description.get();
	}

	/* property */
	public ObjectProperty<java.awt.Color> getColorProperty() {
		return color;
	}

	public ObjectProperty<Object> getValueProperty() {
		return value;
	}

	public StringProperty getDescriptionProperty() {
		return description;
	}

}
