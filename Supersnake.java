package Snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Supersnake implements GameObject{
	

	protected ArrayList<Bodypart> arr;
	protected ArrayList<Turningpoint> turnedpositions;
	protected double  initialx = Constants.screen_width / 800, initialy = Constants.screen_height / 2;
	protected int mag = 20;
	protected double speed = 1.75;
	protected double dx = 0;
	protected double dy = 0;
	protected Color col = Color.red;
	public boolean isDead = false;
	protected double health = 200;
	protected HealthBar h;
	
	
	public void changeCol(Color c) {
		col = c;
	}
	
	public void doDamage(double x) {
		health-=x;
	}

	public void pauseMovement() {
		for (int i = 1; i < arr.size(); i++) {
			arr.get(i).stop = true;
		}
	}

	public void continueMovement() {
		for (int i = 1; i < arr.size(); i++) {
			arr.get(i).stop = false;
		}
	}
	
	protected void collisionHandling() {
		for (int j = 3; j < arr.size(); j++) {// snake collides with snake
			if (arr.get(0).intersects((arr.get(j)))) {
				health = 0;
				
			}
		}

		for (int j = 0; j < arr.size(); j++) {
			if (arr.get(j).outBounds()) {
				health = 0;
				
			}
		}
	}
	
	protected void shiftSnake() {

		for (int i = 0; i < arr.size(); i++) {

			for (int j = 0; j < turnedpositions.size(); j++) {

				if (Math.abs(arr.get(i).getCenterX() - turnedpositions.get(j).xpos) <= Constants.epsilon) {//error handling- makes sure that it turns

					if (Math.abs(arr.get(i).getCenterY() - turnedpositions.get(j).ypos) <= Constants.epsilon) {
						
						arr.get(i).translate(speed*turnedpositions.get(j).xv,speed*turnedpositions.get(j).yv);
						arr.get(i).gx = speed*speed * turnedpositions.get(j).xv;// multiplying the vector with the scalar-> speeds up
						arr.get(i).gy = speed*speed * turnedpositions.get(j).yv;

						if (i == arr.size() - 1) {
							turnedpositions.remove(j);// removes the point after all body parts have past through it
						}

					}

				}

			}

		}

	}
	
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		win.setColor(col);
		win.fill(arr.get(0));
		if(arr.size()>1) {
		for (int i = 1; i < arr.size(); i++) {
	
			arr.get(i).draw(win);

		}
	
	}
		if(h!=null) {
			h.draw(win);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	
		if(h!=null) {
			h.update(this.health);
		}
		if(health <= 0) {
			this.isDead = true;
		}
	}

}