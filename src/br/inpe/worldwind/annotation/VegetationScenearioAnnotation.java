package br.inpe.worldwind.annotation;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.AnnotationAttributes;
import gov.nasa.worldwind.render.ScreenAnnotation;
import gov.nasa.worldwindx.examples.util.PowerOfTwoPaddedImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;

import br.inpe.message.properties.DefaultDataReferences;

public class VegetationScenearioAnnotation extends RenderableLayer {

	private final static PowerOfTwoPaddedImage colorSchemePic = PowerOfTwoPaddedImage
			.fromPath("images/manoel.png");

	public AnnotationLayer getTitleAnnotion(int changeLayer) {
		AnnotationLayer annotationLayer = new AnnotationLayer();
		ScreenAnnotation sa = new ScreenAnnotation(
				DefaultDataReferences.vegetationScenarioTitle, new Point(470,
						700));
		sa.getAttributes().setDefaults(getDefaultAttributsTitle());
		sa.getAttributes().setCornerRadius(0);
		sa.getAttributes().setFont(Font.decode("Arial-BOLD-22"));
		sa.getAttributes().setSize(new Dimension(1300, 0));
		sa.getAttributes().setDrawOffset(new Point(100, 0));
		sa.getAttributes().setHighlightScale(1);
		annotationLayer.addAnnotation(sa);

		ScreenAnnotation sa2 = new ScreenAnnotation("Ano "
				+ getYear(changeLayer), new Point(125, 550));

		sa2.getAttributes().setDefaults(getDefaultAttributsTitle());
		sa2.getAttributes().setCornerRadius(0);
		sa2.getAttributes().setFont(Font.decode("Arial-BOLD-22"));
		sa2.getAttributes().setAdjustWidthToText(AVKey.SIZE_FIT_TEXT);
		sa2.getAttributes().setHighlightScale(1);
		annotationLayer.addAnnotation(sa2);
		
		ScreenAnnotation sa3 = new ScreenAnnotation(
				DefaultDataReferences.vegetationScenarioReference, new Point(470,
						15));
		sa3.getAttributes().setDefaults(getDefaultAttributsTitle());
		sa3.getAttributes().setCornerRadius(0);
		sa3.getAttributes().setFont(Font.decode("Arial-BOLD-22"));
		sa3.getAttributes().setSize(new Dimension(1300, 0));
		sa3.getAttributes().setDrawOffset(new Point(100, 0));
		sa3.getAttributes().setHighlightScale(1);
		annotationLayer.addAnnotation(sa3);

		return annotationLayer;
	}

	public AnnotationLayer addAnnotations2() {

		AnnotationAttributes defaultAttributes2 = new AnnotationAttributes();
		defaultAttributes2.setBackgroundColor(new Color(0f, 0f, 0f, 0f));
		defaultAttributes2.setTextColor(Color.WHITE);
		defaultAttributes2.setLeaderGapWidth(14);
		defaultAttributes2.setInsets(new Insets(8, 8, 8, 8));

		defaultAttributes2.setCornerRadius(0);
		defaultAttributes2.setSize(new Dimension(300, 0));
		defaultAttributes2.setAdjustWidthToText(AVKey.SIZE_FIT_TEXT); 
		defaultAttributes2.setFont(Font.decode("Arial-BOLD-12"));
		defaultAttributes2.setBorderWidth(0);
		defaultAttributes2.setHighlightScale(1); 
		defaultAttributes2.setCornerRadius(0);

		// PT-BR
		ScreenAnnotation legendHeader = new ScreenAnnotation("Frase 2",
				new Point(920, 270));

		legendHeader.getAttributes().setDefaults(defaultAttributes2);
		legendHeader.getAttributes().setBackgroundColor(
				new Color(0.2f, 0.2f, 0.2f, .5f));
		legendHeader.getAttributes().setImageSource(
				colorSchemePic.getPowerOfTwoImage());
		legendHeader.getAttributes().setImageRepeat(AVKey.REPEAT_NONE);
		legendHeader.getAttributes().setImageScale(0.75);
		legendHeader.getAttributes().setImageOffset(new Point(2, 60));
		legendHeader.getAttributes().setInsets(new Insets(6, 35, 6, 6));

		legendHeader.getAttributes().setBorderColor(Color.BLACK);
		legendHeader.getAttributes().setBorderWidth(1);

		legendHeader.getAttributes().setCornerRadius(0);
		legendHeader.getAttributes().setFont(Font.decode("Arial-BOLD-15"));
		legendHeader.getAttributes().setSize(new Dimension(200, 240));
		legendHeader.setAlwaysOnTop(false);
		this.addRenderable(legendHeader);
		legendHeader.getAttributes().setHighlightScale(1);

		AnnotationLayer annLayer = new AnnotationLayer();
		annLayer.addAnnotation(legendHeader);
		return annLayer;
	}

	public AnnotationAttributes getDefaultAttributsTitle() {
		AnnotationAttributes defaultAttributes = new AnnotationAttributes();
		defaultAttributes.setCornerRadius(10);
		defaultAttributes.setInsets(new Insets(8, 8, 8, 8));
		defaultAttributes.setBackgroundColor(new Color(00, 00, 99));
		defaultAttributes.setTextColor(Color.WHITE);
		defaultAttributes.setDrawOffset(new Point(25, 25));
		defaultAttributes.setDistanceMinScale(.5);
		defaultAttributes.setDistanceMaxScale(2);
		defaultAttributes.setDistanceMinOpacity(.5);
		defaultAttributes.setLeaderGapWidth(14);
		defaultAttributes.setDrawOffset(new Point(20, 40));
		return defaultAttributes;
	}

	public AnnotationAttributes getDefaultAttributsInformation1() {
		AnnotationAttributes defaultAttributes = new AnnotationAttributes();
		defaultAttributes.setCornerRadius(10);
		defaultAttributes.setInsets(new Insets(8, 8, 8, 8));
		defaultAttributes.setBackgroundColor(new Color(00, 00, 99));
		defaultAttributes.setTextColor(Color.WHITE);
		defaultAttributes.setDrawOffset(new Point(25, 25));
		defaultAttributes.setDistanceMinScale(.5);
		defaultAttributes.setDistanceMaxScale(2);
		defaultAttributes.setDistanceMinOpacity(.5);
		defaultAttributes.setLeaderGapWidth(14);
		defaultAttributes.setDrawOffset(new Point(20, 40));
		return defaultAttributes;
	}

	public AnnotationAttributes getDefaultAttributsInformation2() {
		AnnotationAttributes defaultAttributes2 = new AnnotationAttributes();
		defaultAttributes2.setBackgroundColor(new Color(0f, 0f, 0f, 0f));
		defaultAttributes2.setTextColor(Color.WHITE);
		defaultAttributes2.setLeaderGapWidth(14);
		defaultAttributes2.setInsets(new Insets(8, 8, 8, 8));
		defaultAttributes2.setCornerRadius(0);
		defaultAttributes2.setSize(new Dimension(300, 0));
		defaultAttributes2.setAdjustWidthToText(AVKey.SIZE_FIXED);
		defaultAttributes2.setFont(Font.decode("Arial-BOLD-16"));
		defaultAttributes2.setBorderWidth(0);
		defaultAttributes2.setHighlightScale(1);
		defaultAttributes2.setCornerRadius(0);
		return defaultAttributes2;
	}

	public AnnotationLayer generalInformationLayer1() {
		AnnotationLayer generalAnnotationLayer = new AnnotationLayer();
		generalAnnotationLayer.setMinActiveAltitude(9000000.0);

		// RETRIEVE RELEVANT INFORMATION
		double averageGDP = 0;

		double population = 0;

		double averageDefor = 0;

		double averageAcumDefor = 0;

		double totalArea = 0;

		averageAcumDefor = averageAcumDefor / totalArea;
		averageDefor = averageDefor / totalArea;
		averageGDP = (averageGDP * 1000 / population);

		ScreenAnnotation factAnnotation = new ScreenAnnotation("", new Point(
				160, 380));
		factAnnotation.getAttributes().setBackgroundColor(
				new Color(0.2f, 0.2f, 0.2f, .5f));
		factAnnotation.getAttributes().setBorderColor(Color.BLACK);
		factAnnotation.getAttributes().setTextColor(Color.WHITE);
		factAnnotation.getAttributes().setBorderWidth(1);
		factAnnotation.getAttributes().setCornerRadius(0);
		factAnnotation.getAttributes().setFont(Font.decode("Arial-BOLD-17"));
		factAnnotation.getAttributes().setSize(new Dimension(280, 0));
		factAnnotation.setAlwaysOnTop(false);
		factAnnotation.getAttributes().setAdjustWidthToText(AVKey.SIZE_FIXED);
		generalAnnotationLayer.addAnnotation(factAnnotation);

		ScreenAnnotation statAnnotation = new ScreenAnnotation("", new Point(
				175, 210));
		statAnnotation.getAttributes().setDefaults(
				getDefaultAttributsInformation2());
		statAnnotation.getAttributes().setBackgroundColor(
				new Color(0.2f, 0.2f, 0.2f, .5f));
		statAnnotation.getAttributes().setBorderColor(Color.BLACK);
		statAnnotation.getAttributes().setBorderWidth(1);
		statAnnotation.getAttributes().setCornerRadius(0);
		statAnnotation.getAttributes().setFont(Font.decode("Arial-BOLD-17"));
		statAnnotation.setAlwaysOnTop(false);
		statAnnotation.getAttributes()
				.setAdjustWidthToText(AVKey.SIZE_FIT_TEXT);
		generalAnnotationLayer.addAnnotation(statAnnotation);
		return generalAnnotationLayer;
	}

	public String getYear(int changeLayer) {
		String year = "";
		switch (changeLayer) {
		case 0:
			year = "2000";
			break;
		case 1:
			year = "2005";
			break;
		case 2:
			year = "2010";
			break;
		case 3:
			year = "2015";
			break;
		case 4:
			year = "2020";
			break;
		case 5:
			year = "2025";
			break;
		case 6:
			year = "2030";
			break;
		case 7:
			year = "2035";
			break;
		case 8:
			year = "2040";
			break;
		case 9:
			year = "2045";
			break;
		case 10:
			year = "2050";
			break;
		}
		return year;
	}

}
