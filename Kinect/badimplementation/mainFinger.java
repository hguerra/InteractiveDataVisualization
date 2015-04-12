package badimplementation;

import br.com.inpe.interactivedatavisualization.kinect.model.HandRecognize;
import processing.core.*;
import SimpleOpenNI.*;


public class mainFinger extends PApplet{
		HandRecognize fingers;
		SimpleOpenNI kinect;
		
		int threshold = 2200; //625

		public void setup() {
		  size(640, 480);
		  
		 
		  kinect = new SimpleOpenNI(this);
		  kinect.enableDepth();
		  
		 
		  kinect.setMirror(true);

		  
		  fingers = new HandRecognize(this, 640, 480);
		  
		  fingers.setMeltFactor(100);
		 
		}

		public void draw() {
		  
		  kinect.update();
		
		  
		
		  PImage depthImage = kinect.depthImage();
		  image(depthImage, 0, 0);

		  
		  fingers.setThreshold(threshold);
		  
		  
		  int[] depthMap = kinect.depthMap();
		  
		  fingers.update(depthMap);

		
		  stroke(0,255,0);
		  for (int k = 0; k < fingers.getNumContours(); k++) {
		    fingers.drawContour(k);
		  }
		  
		 /*
		  * Mexer!
		  */
		  noStroke();
		  fill(255,0,0);
		  for (int i = 0; i < fingers.getNumFingers(); i++) {
		    PVector position = fingers.getFinger(i);
		    ellipse(position.x - 5, position.y -5, 10, 10);
		  }
		  fill(255,0,0);
		  text(threshold, 10, 20);
		}
		public void keyPressed(){
		  if(key == '-'){
		    threshold -= 10;
		  }
		  
		  if(key == '='){
		    threshold += 10;
		  }
		}
}

