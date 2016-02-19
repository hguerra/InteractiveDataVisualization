package br.inpe.triangle.conf;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;

import br.inpe.gdal.transform.GeoFormat;

@XmlRootElement
public class DataSource {
	@Expose
	private GeoFormat format;
	@Expose
	private String filepath;
	@Expose
	private Map<Double, String> colors;
	// exclude attribute
	private Map<Double, java.awt.Color> awtColors;

	public DataSource() {
		this.awtColors = new HashMap<>();
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

	public Map<Double, String> getColors() {
		return colors;
	}

	@XmlElement
	public void setColors(Map<Double, String> colors) {
		this.colors = colors;
	}
	
	
	@Override
	public String toString() {
		return new StringBuffer().append(format).append(" - ").append(filepath).append(" - ").append(colors).toString();
	}

	/**
	 * Transform methods
	 * 
	 * @return
	 */
	public Map<Double, java.awt.Color> getAwtColors() {
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
}