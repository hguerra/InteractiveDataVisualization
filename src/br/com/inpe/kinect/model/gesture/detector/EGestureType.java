package br.com.inpe.kinect.model.gesture.detector;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public enum EGestureType {
	WAVERIGHT, WAVELEFT, MENU, LEFTSWIPE, RIGHTSWIPE, ZOOM;
	
	public int getValue(){
		 switch(this) {
	      case WAVERIGHT: return 1;
	      case WAVELEFT: return 2;
	      case MENU: return 3;
	      case LEFTSWIPE: return 4;
	      case RIGHTSWIPE: return 5;
	      case ZOOM: return 6;
	      default:       return 0;
	    }
	}
}
