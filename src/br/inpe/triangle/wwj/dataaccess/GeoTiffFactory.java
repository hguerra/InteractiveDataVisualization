package br.inpe.triangle.wwj.dataaccess;

import java.awt.image.BufferedImage;
import java.io.File;

import org.w3c.dom.Document;

import br.inpe.triangle.wwj.layer.LayerController;
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
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.SurfaceImageLayer;
import gov.nasa.worldwind.render.SurfaceImage;
import gov.nasa.worldwind.util.WWIO;
import gov.nasa.worldwindx.examples.dataimport.DataInstallUtil;

public class GeoTiffFactory {
	/**
	 * Geotiff
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
		BufferedImage image = ((BufferedImageRaster) subRaster).getBufferedImage();
		subRaster.dispose();
		SurfaceImage si1 = new SurfaceImage(image, sector);
		return si1;
	}

	public void importTiff(String file, WorldWindow wwd) throws Exception {
		SurfaceImageLayer layer = new SurfaceImageLayer();
		layer.setName("Imported Surface Image");
		layer.setPickEnabled(false);
		layer.addRenderable(getLayerFromTiff(file));
		LayerController.insertBeforeCompass(wwd, layer);
	}

	protected Layer installSurfaceImage(String name, String displayName, Object imageSource, FileStore fileStore) {
		File fileStoreLocation = DataInstallUtil.getDefaultInstallLocation(fileStore);
		String cacheName = name + WWIO.replaceIllegalFileNameCharacters(displayName);
		AVList params = new AVListImpl();
		params.setValue(AVKey.FILE_STORE_LOCATION, fileStoreLocation.getAbsolutePath());
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
		if (results == null || results.iterator() == null || !results.iterator().hasNext())
			return null;

		Object o = results.iterator().next();
		if (o == null || !(o instanceof Document))
			return null;

		Layer layer = (Layer) BasicFactory.create(AVKey.LAYER_FACTORY, ((Document) o).getDocumentElement());

		layer.setEnabled(true);

		return layer;
	}
}
