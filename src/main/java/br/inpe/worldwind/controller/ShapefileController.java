package br.inpe.worldwind.controller;

import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.*;
import gov.nasa.worldwind.util.WWIO;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Map;

public interface ShapefileController extends LayerController {

    List<Layer> shapefile2Layers(String layerName, String attributeName, Shapefile shapefile, Map<Object, Color> colors);

    RenderableLayer shapefile2RenderableLayer(String layerName, String attributeName, Shapefile shapefile, Map<Object, Color> colors);

    void addShapefile(Layer layer);

    void addShapefile(String layerName, String attributeName, Shapefile shapefile, Map<Object, Color> colors);

    boolean addShapefile(String filepath, String columnName, Color... colors);

    public static Shapefile createShapefile(String filepath) throws Exception {
        return new Shapefile(new File(filepath));
    }

    public static String getDisplayName(Object source) {
        String name = WWIO.getSourcePath(source);
        if (name != null)
            name = WWIO.getFilename(name);
        if (name == null) {
            name = "Shapefile";
        }
        return name;
    }

    public static void setRenderableLayerColor(RenderableLayer layer, Color interiorColor) {
        java.util.Iterator<Renderable> iterator = layer.getRenderables().iterator();
        while (iterator.hasNext()) {
            Renderable next = null;
            next = iterator.next();
            if (next instanceof AbstractSurfaceShape) {
                ShapeAttributes attr = new BasicShapeAttributes();
                Material interior = new Material(interiorColor);
                attr.setInteriorMaterial(interior);
                attr.setDrawOutline(false);
                ((AbstractSurfaceShape) next).setAttributes(attr);
            }
        }
    }

}
