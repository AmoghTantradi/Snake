package Snake;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Utilities.Timer;

public class Animation implements GameObject {
	
	BufferedImage [] slides;
	int position = 0;
	Timer time;
	double  xpos = Constants.screen_width/2, ypos = Constants.screen_height/2;
	
	
	public Animation(BufferedImage image, int rows, int cols, double delay) {
		time = new Timer(delay);
		time.start();
		
		int width = image.getWidth()/cols;
		int height = image.getHeight()/rows;
		
		slides = new BufferedImage[rows * cols];
		
		int count =0;
		
		for (int i=0;i<rows;i++) {
			for(int j=0; j<cols;j++) {
				
				BufferedImage subImage = image.getSubimage(width*j, height*i, width, height);
				slides[count] = subImage;
				count++;
			}
		}
		
	}
	
	public void setPosition(double d, double e) {
		this.xpos = d;
		this.ypos = e;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(time.update()) {
			this.position++;//increments the picture
			this.position %= this.slides.length;
		}
		
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		win.drawImage(this.slides[this.position],null,(int)xpos,(int)ypos);
		
	}

}
