package model;

import processing.core.PApplet;
import processing.core.PConstants;

public class Gem {
	  float xpos;
	  float ypos;
	  int value;
	  int myColor;
	  PApplet window;
	  
	  /**
	  * ctor
	  * @param xpos position on window
	  * @param ypos position on window
	  * @param value value of the gem
	  * @param myColor color of the star
	  */
	  public Gem(PApplet window, float xpos, float ypos, int value, int myColor){
	    this.xpos=xpos; 
	    this.ypos=ypos; 
	    this.value=value;
	    this.window = window;
	    this.myColor = myColor;
	  }
	  /**
	  * öffentliche draw funktion, kapselt private draw-funktion
	  */
	  public void drawStar(){
	      star(this.xpos,this.ypos, 10,20, 5); 
	  }
	 
	  /**
	  * draws a star; copied from Star.pde sketch library
	  */
	  private void star(float x, float y, float radius1, float radius2, int npoints) {
	    float angle = PConstants.TWO_PI / npoints;
	    float halfAngle = angle/(float)2.0;
	    window.fill(myColor);
	    window.stroke(myColor);
	    // shapes in processing: 
	    // https://processing.org/reference/beginShape_.html
	    window.beginShape();
	    for (float a = 0; a < PConstants.TWO_PI; a += angle) {
	      float sx = x + PApplet.cos(a) * radius2;
	      float sy = y + PApplet.sin(a) * radius2;
	      window.vertex(sx, sy);
	      sx = x + PApplet.cos(a+halfAngle) * radius1;
	      sy = y + PApplet.sin(a+halfAngle) * radius1;
	      window.vertex(sx, sy);
	    }
	    window.endShape(PConstants.CLOSE);
	  }

	  public float getX() {
			return xpos;
		}
		
		public float getY() {
			return ypos;
		}
		public int getValue() {
			return value;
		}
}
