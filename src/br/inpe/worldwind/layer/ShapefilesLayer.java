package br.inpe.worldwind.layer;

import gov.nasa.worldwind.BasicFactory;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.avlist.AVListImpl;
import gov.nasa.worldwind.cache.FileStore;
import gov.nasa.worldwind.data.BufferedImageRaster;
import gov.nasa.worldwind.data.DataRaster;
import gov.nasa.worldwind.data.DataRasterReader;
import gov.nasa.worldwind.data.DataRasterReaderFactory;
import gov.nasa.worldwind.data.TiledImageProducer;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.layers.SurfaceImageLayer;
import gov.nasa.worldwind.render.AbstractSurfaceShape;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceImage;
import gov.nasa.worldwind.util.WWIO;
import gov.nasa.worldwindx.examples.dataimport.DataInstallUtil;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Document;

import br.inpe.util.ShapefileInfo;

/**
 * 
 * @author Heitor Class used to add shapefiles
 */
public class ShapefilesLayer {
	private WorldWindow wwd;
	private ShapefileProperties properties;

	public ShapefilesLayer(WorldWindow wwd) {
		this.wwd = wwd;
		properties = new ShapefileProperties();
	}

	/**
	 * Shapefile test
	 */

	public void printShapefileInfo(String filePath, String attributeName,
			boolean unique) {
		Shapefile shapefile = createShapeFile(filePath);
		if (unique) {
			ShapefileInfo.printUniqueShapefileAttributs(shapefile,
					attributeName);
		} else {
			ShapefileInfo.printShapefileAttributs(shapefile);
		}
	}
	/**
	 * Fix after
	 * 
	 * @param filePath
	 * @param interiorColor
	 */

	public RenderableLayer getRenderableLayer(String filePath,
			Color[] interiorColor1, Color[] interiorColor2) {
		Shapefile shape = createShapeFile(filePath);
		Shapefile shapeReverse = createShapeFile(filePath);
		Shapefile shapeColor = createShapeFile(filePath);
		int size = ShapefileProperties.getShapefileUniqueAttributes(shapeColor,
				"attr").size();
		Map<Double, Color> colors;
		if (size <= 7) {
			colors = properties.createPolygonColors(shape, "attr",
					interiorColor1);
		} else {
			colors = properties.createPolygonColors(shape, "attr",
					interiorColor2);
		}
		List<Layer> layers = new ArrayList<Layer>();

		properties.addRenderablesForPolygon(shapeReverse, layers, colors);
		return (RenderableLayer) layers.get(0);
	}
	/**
	 * 
	 * @param filePath
	 * @param interiorColor1
	 * @param interiorColor2
	 */

	public void insertRenderableLayer(String filePath, Color[] interiorColor1,
			Color[] interiorColor2) {
		Shapefile shape = createShapeFile(filePath);
		Shapefile shapeReverse = createShapeFile(filePath);
		Shapefile shapeColor = createShapeFile(filePath);
		int size = ShapefileProperties.getShapefileUniqueAttributes(shapeColor,
				"attr").size();
		Map<Double, Color> colors;
		if (size <= 7) {
			colors = properties.createPolygonColors(shape, "attr",
					interiorColor1);
		} else {
			colors = properties.createPolygonColors(shape, "attr",
					interiorColor2);
		}
		List<Layer> layers = new ArrayList<Layer>();

		properties.addRenderablesForPolygon(shapeReverse, layers, colors);
		insertLayerBeforeCompass(layers);
	}

	public void insertRenderableLayer(String filePath, Color[] interiorColor) {
		Shapefile shape = createShapeFile(filePath);
		Shapefile shapeReverse = createShapeFile(filePath);

		Map<Double, Color> colors = properties.createPolygonColors(shape,
				"attr", interiorColor);

		List<Layer> layers = new ArrayList<Layer>();

		properties.addRenderablesForPolygon(shapeReverse, layers, colors);
		insertLayerBeforeCompass(layers);
	}

	public Map<Double, Color> getColorScale(String filePath,
			Color[] interiorColor) {
		Shapefile shape = createShapeFile(filePath);
		Map<Double, Color> colors = properties.createPolygonColors(shape,
				"attr", interiorColor);
		return colors;
	}

	public Map<Renderable, Set<Entry<String, Object>>> getLayersAttributes(
			RenderableLayer layer) {
		Map<Renderable, Set<Entry<String, Object>>> attrs = new HashMap<Renderable, Set<Entry<String, Object>>>();
		ShapefileProperties properties = new ShapefileProperties();
		java.util.Iterator<Renderable> iterator = layer.getRenderables()
				.iterator();
		while (iterator.hasNext()) {
			Renderable next = null;
			next = iterator.next();
			attrs.put(next, properties.getAttr(next));
		}
		return attrs;
	}

	public void setRenderableLayerColor(RenderableLayer layer,
			Color interiorColor) {
		java.util.Iterator<Renderable> iterator = layer.getRenderables()
				.iterator();
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

	/**
	 * Geotiff Test
	 */

	public SurfaceImage getLayerFromTiff(String file) throws Exception {
		File sourceFile = new File(file);

		DataRasterReaderFactory readerFactory = (DataRasterReaderFactory) WorldWind
				.createConfigurationComponent(AVKey.DATA_RASTER_READER_FACTORY_CLASS_NAME);
		DataRasterReader reader = readerFactory.findReaderFor(sourceFile, null);

		DataRaster[] rasters = reader.read(sourceFile, null);
		if (rasters == null || rasters.length == 0)
			throw new Exception("Can't read the image file.");
		DataRaster raster = rasters[0];
		final Sector sector = (Sector) raster.getValue(AVKey.SECTOR);
		if (sector == null)
			throw new Exception("No location specified with image.");
		int width = raster.getWidth();
		int height = raster.getHeight();
		DataRaster subRaster = raster.getSubRaster(width, height, sector, null);
		raster.dispose();
		if (!(subRaster instanceof BufferedImageRaster))
			throw new Exception("Cannot get BufferedImage.");
		BufferedImage image = ((BufferedImageRaster) subRaster)
				.getBufferedImage();
		subRaster.dispose();
		SurfaceImage si1 = new SurfaceImage(image, sector);
		return si1;
	}

	public void importTiff(String file) throws Exception {
		SurfaceImageLayer layer = new SurfaceImageLayer();
		layer.setName("Imported Surface Image");
		layer.setPickEnabled(false);
		layer.addRenderable(getLayerFromTiff(file));
		insertBeforeCompass(wwd, layer);
	}

	protected Layer installSurfaceImage(String name, String displayName,
			Object imageSource, FileStore fileStore) {
		File fileStoreLocation = DataInstallUtil
				.getDefaultInstallLocation(fileStore);
		String cacheName = name
				+ WWIO.replaceIllegalFileNameCharacters(displayName);
		AVList params = new AVListImpl();
		params.setValue(AVKey.FILE_STORE_LOCATION,
				fileStoreLocation.getAbsolutePath());
		params.setValue(AVKey.DATA_CACHE_NAME, cacheName);
		params.setValue(AVKey.DATASET_NAME, displayName);
		TiledImageProducer producer = new TiledImageProducer();
		try {
			producer.setStoreParameters(params);
			producer.offerDataSource(imageSource, null);
			producer.startProduction();
		} catch (Exception e) {
			producer.removeProductionState(); // Clean up on failure.
			e.printStackTrace();
			return null;
		}
		Iterable<?> results = producer.getProductionResults();
		if (results == null || results.iterator() == null
				|| !results.iterator().hasNext())
			return null;

		Object o = results.iterator().next();
		if (o == null || !(o instanceof Document))
			return null;

		Layer layer = (Layer) BasicFactory.create(AVKey.LAYER_FACTORY,
				((Document) o).getDocumentElement());

		layer.setEnabled(true);

		return layer;
	}

	/**
	 * Normal code
	 */

	/**
	 * Important Method
	 * 
	 * @param wwd
	 * @param layer
	 */
	protected void insertLayerListBeforeCompass(List layers) {
		for (Object i : layers) {
			Layer l = (Layer) i;
			insertBeforeCompass(wwd, l);
		}
		wwd.redraw();
	}

	public void addLayer(Layer layer) {
		wwd.getModel().getLayers().add(layer);
		wwd.setModel(wwd.getModel());
		wwd.redraw();
	}

	public void insertBeforeCompass(WorldWindow wwd, Layer layer) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = wwd.getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.add(compassPosition, layer);
	}

	public void removeCompassLayer(WorldWindow wwd) {
		int compassPosition = 0;
		LayerList toRemoveLayers = wwd.getModel().getLayers();
		for (Layer l : toRemoveLayers) {
			if (l instanceof CompassLayer)
				compassPosition = toRemoveLayers.indexOf(l);
		}
		toRemoveLayers.remove(compassPosition);
	}

	public String makeDisplayName(Object source) {
		String name = WWIO.getSourcePath(source);
		if (name != null)
			name = WWIO.getFilename(name);
		if (name == null)
			name = "Shapefile";
		return name;
	}

	/**
	 * Insert Layer
	 * 
	 * @param layers
	 */
	public void insertLayerBeforeCompass(List<Layer> layers) {
		insertLayerListBeforeCompass(layers);
	}

	public void insertLayerBeforeCompass(String path, String layerName,
			boolean pickEnabled) {
		List<Layer> layers = createLayersFromSource(path, layerName,
				pickEnabled);
		insertLayerBeforeCompass(layers);
	}

	public void insertLayerBeforeCompass(String path) {
		List<Layer> layers = createLayersFromSource(path);
		insertLayerBeforeCompass(layers);
	}

	public void removeLayer(String layerName) {
		List<Layer> toRemoveLayers = wwd.getModel().getLayers();
		for (Layer l : toRemoveLayers) {
			if (l.getName().equals(layerName)) {
				toRemoveLayers.remove(l);
			}

		}
		wwd.redraw();
	}

	public void removeLayer() {
		removeLayer("Renderable");
	}

	/**
	 * Insert RenderableLayer
	 * 
	 * @param filePath
	 * @param interiorColor
	 * @param outLineColor
	 */
	public void insertRenderableLayer(String filePath, Color interiorColor,
			Color outLineColor) {
		Shapefile shape = createShapeFile(filePath);
		RenderableLayer layer = createRenderableLayer(shape);
		if (outLineColor != null) {
			setRenderableLayerColor(layer, interiorColor, outLineColor);
		} else {
			setRenderableLayerColor(layer, interiorColor);
		}

		addLayer(layer);
	}

	public void insertRenderableLayer(String filePath) {
		Shapefile shape = createShapeFile(filePath);
		RenderableLayer layer = createRenderableLayer(shape);
		addLayer(layer);
	}

	public void insertRenderableLayerBeforeCompass(String filePath) {
		List<RenderableLayer> layers = createRenderableLayersFromSource(filePath);
		insertLayerListBeforeCompass(layers);
	}

	public void insertRenderableLayerBeforeCompass(String filePath,
			String layerName, boolean pickEnabled) {
		List<RenderableLayer> layers = createRenderableLayersFromSource(
				filePath, layerName, pickEnabled);
		insertLayerListBeforeCompass(layers);
	}

	/**
	 * Create a layer
	 * 
	 * @param filepath
	 * @return
	 */

	public List<Layer> createLayersFromSource(String filepath) {
		return new ShapefileLoader().createLayersFromSource(filepath);

	}

	public List<Layer> createLayersFromSource(String filepath,
			String layerName, boolean pickEnabled) {
		List<Layer> layers = createLayersFromSource(filepath);
		setLayersName(layers, layerName, pickEnabled);
		return layers;
	}

	protected void setLayersName(List layers, String makeDisplayName,
			boolean pickEnabled) {
		for (int i = 0; i < layers.size(); i++) {
			Layer l = (Layer) layers.get(i);
			l.setName(i == 0 ? makeDisplayName : makeDisplayName + "-"
					+ Integer.toString(i));
			l.setPickEnabled(pickEnabled);
		}
	}

	/**
	 * Create a RenderableLayer
	 * 
	 * @param filepath
	 * @return
	 */
	public List<RenderableLayer> createRenderableLayersFromSource(
			String filepath) {
		List<RenderableLayer> layers = new ArrayList<RenderableLayer>();
		RenderableLayer layer = createRenderableLayer(filepath);
		layers.add(layer);
		return layers;
	}

	public List<RenderableLayer> createRenderableLayersFromSource(
			String filepath, String layerName, boolean pickEnabled) {
		List<RenderableLayer> layers = createRenderableLayersFromSource(filepath);
		setLayersName(layers, layerName, pickEnabled);
		return layers;
	}

	public Shapefile createShapeFile(String filepath) {
		return new Shapefile(new File(filepath));
	}

	public RenderableLayer createRenderableLayer(Shapefile shapeFile) {
		return (RenderableLayer) new ShapefileLoader()
				.createLayerFromShapefile(shapeFile);
	}

	public RenderableLayer createRenderableLayer(String filepath) {
		return createRenderableLayer(createShapeFile(filepath));
	}

	/**
	 * Set RenderableLayerColor
	 * 
	 * @param layer
	 * @param interiorColor
	 * @param outLineColor
	 */
	public void setRenderableLayerColor(RenderableLayer layer,
			Color interiorColor, Color outLineColor) {
		java.util.Iterator<Renderable> iterator = layer.getRenderables()
				.iterator();

		while (iterator.hasNext()) {
			Renderable next = null;
			next = iterator.next();

			if (next instanceof AbstractSurfaceShape) {
				ShapeAttributes attr = new BasicShapeAttributes();
				Material interior = new Material(interiorColor);
				Material outLine = new Material(outLineColor);
				attr.setInteriorMaterial(interior);
				attr.setOutlineMaterial(outLine);
				((AbstractSurfaceShape) next).setAttributes(attr);
			}
		}

	}

}
