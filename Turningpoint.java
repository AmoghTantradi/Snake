package Snake;


public class Turningpoint {
//this thing has x, y methods and also stored whether it went left, right, up, or down by storing a unit vector
	
	double xpos,ypos,xv,yv;

	
	public Turningpoint(double x, double y, double xv, double yv) {
		this.xpos = x;
		this.ypos = y;
		this.xv = xv;
		this.yv = yv;
	}
	
}
