package testeGestureDetector;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public enum EGestureResult {
	FAIL, SUCCEED, PAUSING;
	
	public int getValue(){
		 switch(this) {
	      case FAIL: return 1;
	      case SUCCEED: return 2;
	      case PAUSING: return 3;
	      default:       return 0;
	    }
	}
}
