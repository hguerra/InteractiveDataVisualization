package br.inpe.triangle.fx.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Heitor
 * @since 29/04/2016
 */
public class ScenarioProperty implements Comparable<ScenarioProperty> {
    private StringProperty name = new SimpleStringProperty();
    private BooleanProperty selected = new SimpleBooleanProperty();

    public ScenarioProperty(String name) {
        this.name.set(name);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public boolean getSelected() {
        return selected.get();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(ScenarioProperty o) {
        return getName().compareTo(o.getName());
    }
}
