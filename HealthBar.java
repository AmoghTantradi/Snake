package Snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class HealthBar extends Rectangle2D.Double implements GameObject{

	
	public HealthBar(double d, double e,Supersnake s) {
		super(d,e,s.health,30);
		
	}


	
	public void update(double x) {
		// TODO Auto-generated method stub
		this.setFrame(this.x,this.y,x, 30); 
	}


	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		win.setColor(Color.MAGENTA);
		win.fill(this);
	}



	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	
}
