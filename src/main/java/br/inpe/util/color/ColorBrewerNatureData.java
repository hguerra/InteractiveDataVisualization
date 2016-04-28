package br.inpe.util.color;


import org.geotools.brewer.color.BrewerPalette;
import org.geotools.brewer.color.ColorBrewer;

/**
 * @author Heitor
 * @since 28/04/2016
 */
public enum ColorBrewerNatureData {

    SEQUENTIAL {
        @Override
        public BrewerPalette[] getPallete(int numberDataClasses) {
            return ColorBrewer.instance().getPalettes(ColorBrewer.SEQUENTIAL, numberDataClasses);
        }
    },
    DIVERGING {
        @Override
        public BrewerPalette[] getPallete(int numberDataClasses) {
            return ColorBrewer.instance().getPalettes(ColorBrewer.DIVERGING, numberDataClasses);
        }
    },
    QUALITATIVE {
        @Override
        public BrewerPalette[] getPallete(int numberDataClasses) {
            return ColorBrewer.instance().getPalettes(ColorBrewer.QUALITATIVE, numberDataClasses);
        }
    };

    public abstract BrewerPalette[] getPallete(int numberDataClasses);
}
