package Snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class Bodypart extends Rectangle2D.Double implements GameObject {
	
	//double theta = 0;
	double gy=0;
	double gx=1;
	double mag;
	boolean stop = false;
	Color c = Color.BLUE;
	double health = 100;
	public Bodypart(double x, double y, double mag, Color c) {
		super(x, y, mag, mag);
		this.mag = mag;
		this.c = c;
	}
	
	public void doDamage(double c) {
		health -= c;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	
		if(!stop) {
		this.translate(gx, gy);	
	}
	}
	
	public void translate(double x, double y) {
		this.setFrame(this.getX()+x, this.getY()+y,mag , mag);
	}
	
	public boolean outBounds() {
		return(this.x+this.mag>Constants.screen_width || this.x<0 || this.y < 0 || this.y+this.mag>Constants.screen_height);
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		win.setColor(c);
		//AffineTransform old = win.getTransform();
		
	//	win.rotate(Math.toRadians(theta),this.getCenterX(), this.getCenterY());
		
		win.fill(this);
		
		win.setColor(Color.blue);
		
		win.draw(this);
		
		//win.setTransform(old);
		
	}

	
}
