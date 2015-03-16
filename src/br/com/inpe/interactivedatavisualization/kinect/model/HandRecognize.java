package br.com.inpe.interactivedatavisualization.kinect.model;

import processing.core.PApplet;
import processing.core.PVector;
public class HandRecognize {
	private Contours contours;
	int w;
	int h;
	int numfingers;
	int[] tmp;
	int meltFactor = 30;
	double[] screenx;
	double[] screeny;
	double[] normalx;
	double[] normaly;
	int[] contour;
	boolean printVerbose = true;
	double FINGER_RADIUS = 15.0;		  // perimeter of a fingertip
	double ROUNDNESS_THRESHOLD = -1.33;	  // minimum allowable value for 
                                          // ratio of area / perimeter of fingertip
	
	public HandRecognize(PApplet parent, int w, int h) {
		this.w = w;
		this.h = h;
		contours = new Contours(parent, w, h);
		setThreshold(128);
		
		screenx = new double[w * h];
		screeny = new double[w * h];
		normalx = new double[w * h];
		normaly = new double[w * h];
		contour = new int[w * h];
		tmp = new int[w * h];
		
	}
  
  public void setThreshold(int value) {
	  contours.setThreshold(value);
  }
  
  private int[] removeZeroPixels(int[] pix){
	  int[] result = new int[pix.length];
	  for(int i = 0; i < pix.length; i++){
		  if(pix[i] == 0){
			  result[i] = 20000; //ridiculously high value
		  } else {
			  result[i] = pix[i];
		  }
	  }
	  return result;
  }
  
  public void setMeltFactor(int value) {
    meltFactor = value;
  }
  
  public void update(int[] pix) {

	pix = removeZeroPixels(pix);
	
    ////////////////////////////////////////////////////
    // FIND contours in pix
    ////////////////////////////////////////////////////
	int numcontours = contours.find(pix);
    
    ////////////////////////////////////////////////////
    // MELT Contours
    ////////////////////////////////////////////////////
    for (int i = 0 ; i < meltFactor ; i++)
      contours.meltContours();
    

    ////////////////////////////////////////////////////
    // FIND Fingers on contours
    // Use 1D connected components (spans)
    // Assume fingers are centers of these 1-D spans
    ////////////////////////////////////////////////////
    numfingers = 0;    
    int spanoffset = 0;
    int window = (int)FINGER_RADIUS;
    double[] tips = contours.findRoundedCorners(window);
    numfingers = 0;
    for (int k = 0; k < numcontours; k++) {
      int l = contours.getContourLength(k);
      for (int i = 0; i < l; i++) {
        if (tips[contours.getValidIndex(k, i)] > ROUNDNESS_THRESHOLD) {
          spanoffset = i + 1;
          break;
        }
      }
      int span = 0;
      for (int i = spanoffset; i < l + spanoffset; i++) {
        double roundness = tips[contours.getValidIndex(k, i)];
        
        if (roundness <= ROUNDNESS_THRESHOLD) {
          span++;
        } else {
          if (span > 0) {
            int tip = (i - 1) - span/2;
            int lo = tip - window;
            int hi = tip + window;
            if (contours.measureDistance(k, lo, hi) < 2 * FINGER_RADIUS) {
              double cx = 0;
              double cy = 0;
              for (int j = lo; j <= hi; j++) {
                cx += contours.getContourX(k,j);
                cy += contours.getContourY(k,j);
              }
              cx = cx / (2 * window + 1);
              cy = cy / (2 * window + 1);
              screenx[numfingers] = cx;
              screeny[numfingers] = cy;
              contour[numfingers] = k;              
              numfingers++;
            }
            
          }
          span = 0;
        }
      }
    }
  }
  
  public void drawContour(int i){
	  contours.drawContour(i);
  }
  
  public int getNumContours(){
	  return contours.getNumContours();
  }
  
  public PVector getFinger(int i){
	return new PVector((float)getFingerX(i), (float)getFingerY(i));  
  }
  
  public double getFingerX(int i) {
    return screenx[i];
  }
  
  public double getFingerY(int i) {
    return screeny[i];
  }
    
  public int getContour(int i) {
    return contour[i];
  }
  
  public int getNumFingers() {
    return numfingers;
  }
    

}
