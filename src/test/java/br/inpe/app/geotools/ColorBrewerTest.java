package br.inpe.app.geotools;

import org.geotools.brewer.color.BrewerPalette;
import org.geotools.brewer.color.ColorBrewer;
import org.geotools.brewer.color.PaletteType;
import org.geotools.brewer.color.SampleScheme;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Heitor on 27/04/2016.
 */
public class ColorBrewerTest {
    public static void main(String[] args) throws IOException {
        /**
         * Create a instance of ColorBrewer
         */
        ColorBrewer colorBrewer = ColorBrewer.instance();
        System.out.println(colorBrewer.getName());
        System.out.println(colorBrewer.getDescription());

        System.out.println("\nPaletteNames:\n");
        String[] paletteNames = colorBrewer.getPaletteNames();
        for(String p: paletteNames){
            System.out.println(p);
        }
        /**
         * Number of data classes:
         */
        int numberDataClasses = 3;
        /**
         * Nature of your data:
         */
        PaletteType natureData = ColorBrewer.DIVERGING;

        /**
         * Get the palette of nature data DIVERGING and 3 classes
         */
        System.out.println("\nDIVERGIND and 3 Classes\n");
        BrewerPalette[] palettesDiverging = colorBrewer.getPalettes(natureData, numberDataClasses);

        for(BrewerPalette palette: palettesDiverging){
            System.out.println("Name:"+palette.getName());
            System.out.println("Description:"+palette.getDescription());
            System.out.println("Count:"+palette.getCount());
            SampleScheme colorScheme = palette.getColorScheme();
            System.out.println("min classes:"+colorScheme.getMinCount());
            System.out.println("max classes:"+colorScheme.getMaxCount());
        }

        /**
         * for this pallet name
         */
        String palletName = "BrBG";

        System.out.println("\nGet pallet:".concat(palletName).concat("\n"));

        BrewerPalette palette = colorBrewer.getPalette("BrBG");
        Color[] colors = palette.getColors();

        for(Color c: colors){
            System.out.println(c.toString());
        }

        System.out.println();
        SampleScheme colorScheme = palette.getColorScheme();
        System.out.println("min classes:"+colorScheme.getMinCount());
        System.out.println("max classes:"+colorScheme.getMaxCount());
    }
}
