package br.inpe.worldwind.defaultcontroller;

import br.inpe.triangle.conf.Data;
import br.inpe.worldwind.controller.LayerController;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.render.AnnotationAttributes;
import gov.nasa.worldwind.render.ScreenAnnotation;

import java.awt.*;

/**
 * @author Heitor
 * @since 05/06/2016
 */
public class AnnotationLayer implements LayerController {
    private String year;
    private Data data;
    private WorldWindowGLCanvas canvas;
    private gov.nasa.worldwind.layers.AnnotationLayer annotationLayer;

    public AnnotationLayer(WorldWindowGLCanvas canvas, Data data, String year) {
        this.canvas = canvas;
        this.data = data;
        this.year = year;
        this.annotationLayer = new gov.nasa.worldwind.layers.AnnotationLayer();
    }

    @Override
    public void draw() {
        try {
            ScreenAnnotation sa = new ScreenAnnotation(
                    data.getTitle(), new Point(470,
                    700));
            sa.getAttributes().setDefaults(getDefaultAttributsTitle());
            sa.getAttributes().setCornerRadius(0);
            sa.getAttributes().setFont(Font.decode("Arial-BOLD-22"));
            sa.getAttributes().setSize(new Dimension(1300, 0));
            sa.getAttributes().setDrawOffset(new Point(100, 0));
            sa.getAttributes().setHighlightScale(1);
            annotationLayer.addAnnotation(sa);

            ScreenAnnotation sa2 = new ScreenAnnotation("Ano ".concat(year), new Point(125, 550));

            sa2.getAttributes().setDefaults(getDefaultAttributsTitle());
            sa2.getAttributes().setCornerRadius(0);
            sa2.getAttributes().setFont(Font.decode("Arial-BOLD-22"));
            sa2.getAttributes().setAdjustWidthToText(AVKey.SIZE_FIT_TEXT);
            sa2.getAttributes().setHighlightScale(1);
            annotationLayer.addAnnotation(sa2);

            if(data.getReference() != null && !data.getReference().isEmpty()){
                ScreenAnnotation sa3 = new ScreenAnnotation(
                        data.getReference(), new Point(470,
                        15));
                sa3.getAttributes().setDefaults(getDefaultAttributsTitle());
                sa3.getAttributes().setCornerRadius(0);
                sa3.getAttributes().setFont(Font.decode("Arial-BOLD-22"));
                sa3.getAttributes().setSize(new Dimension(1300, 0));
                sa3.getAttributes().setDrawOffset(new Point(100, 0));
                sa3.getAttributes().setHighlightScale(1);
                annotationLayer.addAnnotation(sa3);
            }
            LayerController.insertBeforeCompass(canvas, annotationLayer);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void remove() {
        try {
            LayerController.removeBeforeCompass(canvas, annotationLayer);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private AnnotationAttributes getDefaultAttributsTitle() {
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
}
