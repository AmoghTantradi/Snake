package Snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class Laser extends Rectangle implements GameObject {
	

	double theta = 0;
	double dx,dy;
	double speed = 10.0f;
	public Laser(Turret t) {

		super((int)t.getCenterX(),(int)t.getCenterY(),5,20);
		theta = t.reqTheta;
		dx = speed*Math.sin(Math.toRadians(theta));
		dy = speed*-Math.cos(Math.toRadians(theta));
		
		
	}

	public void update(Turret t) {
	
		
		super.translate((int)dx,(int)dy);
		
	}
	
	public void update() {
		
	}
	
	public boolean outBounds() {
		return (this.getCenterX()<0 || this.getCenterX()>Constants.screen_width || this.getCenterY()>Constants.screen_height
				|| this.getCenterY()<0);
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		AffineTransform old = win.getTransform();
		win.setColor(Color.red);
		
		win.rotate(Math.toRadians(theta),this.getCenterX(), this.getCenterY());
		
		win.drawImage(Screen.rocket, null, this.x, this.y);
	
	
		//win.fill(this);
		win.setTransform(old);
		

	}
	
	

}
