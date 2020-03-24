package Snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class ExplosionParticles extends Rectangle2D.Double implements GameObject {
	
	double dx,dy;
	
	Color col = Color.ORANGE;
	
	public ExplosionParticles(double a, double b) {
		super(a,b,5,5);
		// TODO Auto-generated constructor stub
	}
	
	public void setColor(Color c) {
		this.col = c;
	}
	
	public boolean outBounds() {
		return(this.getCenterX()<0 || this.getCenterX()>Constants.screen_width || this.getCenterY()<0 
				|| this.getCenterY()>Constants.screen_height);
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.setRect(this.x+this.dx,this.y+this.dy, 5, 5);
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		win.setColor(col);
		win.fill(this);
		
		
	}
	
	

}
