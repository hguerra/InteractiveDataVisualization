package br.inpe.triangle.data;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.inpe.triangle.gdal.GeoFormat;
import br.inpe.triangle.wwj.dataaccess.ShapefileProperties;
import br.inpe.triangle.wwj.layer.ShapefileController;

import com.google.gson.annotations.Expose;

@XmlRootElement
public class Data implements Comparable<Data> {
    @Expose
    private String title;
    @Expose
    private String reference;
    @Expose
    private GeoFormat format;
    @Expose
    private String filepath;
    @Expose
    private String date;
    @Expose
    private Map<Object, String> colors;
    @Expose
    private Map<Object, String> description;
    @Expose
    private String column;
    // exclude attribute
    private Map<Object, Color> awtColors;

    public Data() {
        this.colors = new HashMap<>();
        this.awtColors = new HashMap<>();
        this.description = new HashMap<>();
    }

    /**
     * Bean
     */
    public GeoFormat getFormat() {
        return format;
    }

    @XmlElement
    public void setFormat(GeoFormat format) {
        this.format = format;
    }

    public String getFilepath() {
        return filepath;
    }

    @XmlElement
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Map<Object, String> getColors() {
        return colors;
    }

    @XmlElement
    public void setColors(Map<Object, String> colors) {
        this.colors = colors;
    }

    public String getReference() {
        return reference;
    }

    @XmlElement
    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public Map<Object, String> getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(Map<Object, String> description) {
        this.description = description;
    }

    public String getColumn() {
        return this.column;
    }

    @XmlElement
    public void setColumn(String column) {
        this.column = column;
    }

    public String getDate() {
        return date;
    }

    @XmlElement
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Transform methods
     *
     * @return
     */
    public Map<Object, Color> getAwtColors() {
        if (!awtColors.isEmpty())
            return this.awtColors;
        colors.forEach((k, v) -> {
            Color value = Color.decode(v);
            if (value == null)
                value = Color.black;
            this.awtColors.put(k, value);
        });

        return this.awtColors;
    }

    @XmlTransient
    public void setAwtColors(Map<Object, Color> awtColors) {
        this.awtColors = awtColors;
    }

    @Override
    public String toString() {
        return new StringBuffer().append(format).append(" - ").append(filepath).append(" - ").append(colors).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (!title.equals(data.title)) return false;
        if (reference != null ? !reference.equals(data.reference) : data.reference != null) return false;
        if (format != data.format) return false;
        if (!filepath.equals(data.filepath)) return false;
        if (date != null ? !date.equals(data.date) : data.date != null) return false;
        if (!colors.equals(data.colors)) return false;
        if (description != null ? !description.equals(data.description) : data.description != null) return false;
        if (column != null ? !column.equals(data.column) : data.column != null) return false;
        return awtColors != null ? awtColors.equals(data.awtColors) : data.awtColors == null;

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (reference != null ? reference.hashCode() : 0);
        result = 31 * result + format.hashCode();
        result = 31 * result + filepath.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + colors.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (column != null ? column.hashCode() : 0);
        result = 31 * result + (awtColors != null ? awtColors.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Data o) {
        return date.compareTo(o.getDate());
    }
}