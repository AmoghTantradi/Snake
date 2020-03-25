package Snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Apple extends Rectangle2D.Double implements GameObject {

	BufferedImage img;
	double gx, gy;

	public Apple() {
		// super((int)((Constants.screen_width/4)+(Math.abs(Constants.rand.nextGaussian())*Constants.screen_width/2)),(int)((Constants.screen_height/4)+(Math.abs(Constants.rand.nextGaussian())*Constants.screen_height/2)),50,50);
		super((Math.random() * Constants.screen_width / 2) + Constants.screen_width / 3,
				(Math.random() * Constants.screen_height / 2) + Constants.screen_height / 3, 25, 25);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		// win.drawImage(img,null,(int)gx,(int)gy);
		win.setColor(Color.yellow);
		win.fill(this);
		win.drawImage(Screen.apple, null, (int) this.x, (int) this.y);

	}

}
