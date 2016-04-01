package br.inpe.triangle.conf;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;

import br.inpe.gdal.transform.GeoFormat;

@XmlRootElement
public class Data {
	@Expose
	private String title;
	@Expose
	private String reference;
	@Expose
	private GeoFormat format;
	@Expose
	private String filepath;
	@Expose
	private Map<Object, String> colors;
	@Expose
	private Map<Object, String> description;
	@Expose
	private String column;
	// exclude attribute
	private Map<Object, java.awt.Color> awtColors;

	public Data() {
		this.colors = new HashMap<>();
		this.awtColors = new HashMap<>();
		this.description = new HashMap<>();
	}

	/**
	 * Bean
	 * 
	 * @return
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

	/**
	 * Transform methods
	 * 
	 * @return
	 */
	public Map<Object, java.awt.Color> getAwtColors() {
		if (!awtColors.isEmpty())
			return this.awtColors;
		colors.forEach((k, v) -> {
			java.awt.Color value = Color.decode(v);
			if (value == null)
				value = java.awt.Color.black;
			this.awtColors.put(k, value);
		});

		return this.awtColors;
	}

	public void setAwtColors(Map<Object, java.awt.Color> awtColors) {
		this.awtColors = awtColors;
	}

	@Override
	public String toString() {
		return new StringBuffer().append(format).append(" - ").append(filepath).append(" - ").append(colors).toString();
	}
}