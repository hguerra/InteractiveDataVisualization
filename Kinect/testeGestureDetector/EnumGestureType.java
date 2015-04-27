package testeGestureDetector;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public enum EnumGestureType {
	WAVERIGHT, WAVELEFT, MENU, LEFTSWIPE, RIGHTSWIPE;
	
	public int getValue(){
		 switch(this) {
	      case WAVERIGHT: return 1;
	      case WAVELEFT: return 2;
	      case MENU: return 3;
	      case LEFTSWIPE: return 4;
	      case RIGHTSWIPE: return 5;
	      default:       return 0;
	    }
	}
}
