package Snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import Utilities.GDV5;

public class Enemysnake extends Supersnake{
	private Random rand;
	double x,y;
	
	public Enemysnake() {

		this(5,Constants.screen_width / 800, 2*Constants.screen_height / 3);
		x = Constants.screen_width/2;
		y = Constants.screen_height/3;
	
		
	}

	public Enemysnake(int size,double x, double y) {
		this.arr = new ArrayList<Bodypart>();
		this.turnedpositions = new ArrayList<Turningpoint>();
		rand = new Random();
		for (int i = size; i > 0; i--) {
			arr.add(new Bodypart(x + (mag) * i, y, mag,Color.yellow));
			
		}
		super.changeCol(Color.orange);
		h = new HealthBar(Constants.screen_width*0.75, Constants.screen_height*0.97,this);
	
	
	}
	
	public void update() {
		
		dx = arr.get(0).gx;
		dy = arr.get(0).gy;
		// now you have to do movement
		if (GDV5.KeysTyped[KeyEvent.VK_UP] ) {//this fixes the error of adding the new blocks
			if(dy <=0) {
			turnedpositions.add(new Turningpoint((arr.get(0).getCenterX()), arr.get(0).getCenterY(), 0, -1)); //creates a unit vector with argument = k*pi/2
			}
			GDV5.KeysTyped[KeyEvent.VK_UP] = false;
		}
		if (GDV5.KeysTyped[KeyEvent.VK_DOWN]   ) {
			if(dy >=0) {
			turnedpositions.add(new Turningpoint(arr.get(0).getCenterX(), arr.get(0).getCenterY(), 0, 1));
			}
			GDV5.KeysTyped[KeyEvent.VK_DOWN] = false;
		}
		if (GDV5.KeysTyped[KeyEvent.VK_LEFT] ) {
			if(dx<=0) {
			turnedpositions.add(new Turningpoint(arr.get(0).getCenterX(), arr.get(0).getCenterY(), -1, 0));
			}
			GDV5.KeysTyped[KeyEvent.VK_LEFT] = false;
		}
		if (GDV5.KeysTyped[KeyEvent.VK_RIGHT]  ) {
			if(dx >=0) {
			turnedpositions.add(new Turningpoint(arr.get(0).getCenterX(), arr.get(0).getCenterY(), 1, 0));
			}
			GDV5.KeysTyped[KeyEvent.VK_RIGHT] = false;
		}

			super.shiftSnake();
		
		
		


		for (int i = 0; i < arr.size(); i++) {
			arr.get(i).update();
		}
		
		collisionHandling();


		super.update();
	
		
		
		
		
	}
	
	
	

	public void collisionHandling() {
		super.collisionHandling();
		if(Screen.player1 != null) {
		for(int i = 0; i < Screen.player1.arr.size();i++) {
			if(arr.get(0).intersects(Screen.player1.arr.get(i))) {
			health = 0;
			}
		}
	}
	}
	

	
	
	public void draw(Graphics2D win) {
		super.draw(win);
		
	}
	
	
}
