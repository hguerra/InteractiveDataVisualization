package br.inpe.worldwind.defaultcontroller;

import br.inpe.worldwind.controller.LayerController;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.engine.ShapefileProperties;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.util.WWIO;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShapefileLayer implements ShapefileController {
    private WorldWindow canvas;
    private ShapefileProperties properties;
    private List<Layer> layers;

    public ShapefileLayer(WorldWindow canvas) {
        this.canvas = canvas;
        this.properties = new ShapefileProperties();
        this.layers = new ArrayList<>();

    }

    @Override
    public void draw() {
        if (layers.isEmpty())
            return;
        LayerController.insertBeforeCompass(canvas, layers);
    }

    @Override
    public void remove() {
        LayerController.removeBeforeCompass(canvas, layers);
    }

    public String getDisplayName(Object source) {
        String name = WWIO.getSourcePath(source);
        if (name != null)
            name = WWIO.getFilename(name);
        if (name == null) {
            name = new StringBuilder().append("Shapefile-").append(layers.size()).toString();
        }
        return name;
    }

    @Override
    public List<Layer> shapefile2Layers(String layerName, String attributeName, Shapefile shapefile, Map<Object, Color> colors) {
        List<Layer> layers = new ArrayList<>();
        properties.addRenderablesForPolygon(shapefile, layerName, attributeName, layers, colors);
        return layers;
    }

    @Override
    public RenderableLayer shapefile2RenderableLayer(String layerName, String attributeName, Shapefile shapefile, Map<Object, Color> colors) {
        return (RenderableLayer) shapefile2Layers(layerName, attributeName, shapefile, colors).get(0);
    }

    @Override
    public void addShapefile(Layer layer) {
        this.layers.add(layer);
    }

    @Override
    public void addShapefile(String layerName, String attributeName, Shapefile shapefile, Map<Object, Color> colors) {
        RenderableLayer layer = shapefile2RenderableLayer(layerName, attributeName, shapefile, colors);
        addShapefile(layer);
    }

    @Override
    public boolean addShapefile(String filepath, String columnName, Color... c) {
        boolean success = true;
        try {
            String layerName = getDisplayName(filepath);
            Shapefile shpColors = ShapefileController.createShapefile(filepath);
            Shapefile shapefile = ShapefileController.createShapefile(filepath);
            Map<Object, Color> colors = properties.createPolygonColors(shpColors, columnName, c);
            addShapefile(layerName, columnName, shapefile, colors);
        } catch (Exception e) {
            success = false;
            System.err.println(e);
        }
        return success;
    }
    public List<Layer> getLayers() {
        return layers;
    }

}
