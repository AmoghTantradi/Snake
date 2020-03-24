package Snake;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class FrontImage implements GameObject {
	
	Animation anim;
	
	public FrontImage(BufferedImage img) {
		anim = new Animation(img,2,4,0.75d);
		anim.xpos = (Constants.screen_width*0.375);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		anim.update();
		
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		anim.draw(win);
		
	}

}
