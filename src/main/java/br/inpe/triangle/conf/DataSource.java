package br.inpe.triangle.conf;

import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.engine.ShapefileProperties;
import com.google.gson.annotations.Expose;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.Layer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
public class DataSource {
    @Expose
    private Map<String, Data> dataSet;
    private Map<String, List<Layer>> layers;
    private ShapefileProperties shpProperties;

    public DataSource() {
        this.dataSet = new HashMap<>();
        this.layers = new HashMap<>();
        this.shpProperties = new ShapefileProperties();
    }

    public Data addData(String name, Data data) {
        return dataSet.put(name, data);
    }

    public Data removeData(Data data) {
        return  dataSet.remove(data);
    }

    public Map<String, List<Layer>> refreshLayers() {
        dataSet.forEach((k, v) -> {
            List<Layer> value = createLayers(v);
            if (!value.isEmpty())
                this.layers.put(k, value);
        });
        return this.layers;
    }

    public Map<String, List<Layer>> getLayers() {
        if (!layers.isEmpty())
            return this.layers;
        return refreshLayers();
    }

    public List<Layer> createLayers(Data data) {
        List<Layer> layer = new ArrayList<>();
        try {
            Shapefile shapefile = ShapefileController.createShapefile(data.getFilepath());
            layer = shpProperties.createLayers(data.getTitle(), data.getColumn(), shapefile, data.getAwtColors());
        } catch (Exception e) {
            System.err.println(e);
        }
        return layer;
    }

    /**
     * Getters and setters
     *
     * @return
     */
    public Map<String, Data> getDataSet() {
        return dataSet;
    }

    @XmlElement
    public void setDataSet(Map<String, Data> dataSet) {
        this.dataSet = dataSet;
    }


}
