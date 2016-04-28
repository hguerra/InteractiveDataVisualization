package br.inpe.util.color;

import org.geotools.brewer.color.*;

/**
 * @author Heitor
 * @since 28/04/2016
 */
public enum ColorBrewerPaletteName implements ColorBrewerPalette {
    YlGn {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, YlGnBu {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, GnBu {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, BuGn {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, PuBuGn {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, PuBu {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, BuPu {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, RdPu {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, PuRd {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, OrRd {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, YlOrRd {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, YlOrBr {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Purples {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Blues {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Greens {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Oranges {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Reds {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Greys {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, PuOr {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, BrBG {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, PRGn {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, PiYG {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, RdBu {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, RdGy {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, RdYlBu {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Spectral {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, RdYlGn {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Accent {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Dark2 {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Paired {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Pastel1 {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Pastel2 {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Set1 {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Set2 {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    }, Set3 {
        @Override
        public BrewerPalette getPallete() {
            return getPallete(this);
        }
    };

    protected static BrewerPalette getPallete(ColorBrewerPaletteName colorBrewerPaletteName) {
        return org.geotools.brewer.color.ColorBrewer.instance().getPalette(colorBrewerPaletteName.toString());
    }

    public String toString() {
        switch (this) {
            case YlGn:
                return "YlGn";
            case YlGnBu:
                return "YlGnBu";
            case GnBu:
                return "GnBu";
            case BuGn:
                return "BuGn";
            case PuBuGn:
                return "PuBuGn";
            case PuBu:
                return "PuBu";
            case BuPu:
                return "BuPu";
            case RdPu:
                return "RdPu";
            case PuRd:
                return "PuRd";
            case OrRd:
                return "OrRd";
            case YlOrRd:
                return "YlOrRd";
            case YlOrBr:
                return "YlOrBr";
            case Purples:
                return "Purples";
            case Blues:
                return "Blues";
            case Greens:
                return "Greens";
            case Oranges:
                return "Oranges";
            case Reds:
                return "Reds";
            case Greys:
                return "Greys";
            case PuOr:
                return "PuOr";
            case BrBG:
                return "BrBG";
            case PRGn:
                return "PRGn";
            case PiYG:
                return "PiYG";
            case RdBu:
                return "RdBu";
            case RdGy:
                return "RdGy";
            case RdYlBu:
                return "RdYlBu";
            case Spectral:
                return "Spectral";
            case RdYlGn:
                return "RdYlGn";
            case Accent:
                return "Accent";
            case Dark2:
                return "Dark2";
            case Paired:
                return "Paired";
            case Pastel1:
                return "Pastel1";
            case Pastel2:
                return "Pastel2";
            case Set1:
                return "Set1";
            case Set2:
                return "Set2";
            case Set3:
                return "Set3";
            default:
                return "UNDEFINED";
        }
    }
}
