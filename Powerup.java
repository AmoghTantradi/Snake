package Snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class Powerup extends Rectangle2D.Double implements GameObject {

	double dx = 0, dy = 0;
	Color col = Color.GREEN;

	public Powerup(double size) {

		super((Math.random() * Constants.screen_width / 2) + Constants.screen_width / 4,
				(Math.random() * Constants.screen_height / 2) + Constants.screen_height / 4, size, size);
	}

	
	public boolean outBounds() {
		return(this.getCenterX()<0 || this.getCenterX()>Constants.screen_width ||
				this.getCenterY()<0 || this.getCenterY()>Constants.screen_height);
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		win.setColor(col);
		win.fill(this);
		win.drawImage(Screen.powerup,null,(int)this.x,(int)this.y);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
