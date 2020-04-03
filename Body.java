package Snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import Utilities.GDV5;


public class Body extends Supersnake {


	public Body() {
	
		this(5,Constants.screen_width / 800, Constants.screen_height / 3);
	}
	public Body(int size, double x, double  y) {
		this.arr = new ArrayList<Bodypart>();
		this.turnedpositions = new ArrayList<Turningpoint>();

		for (int i = size; i > 0; i--) {
			arr.add(new Bodypart(x + (mag) * i, y, mag,Color.cyan));
		}
		super.changeCol(Color.blue);
		h = new HealthBar(0,Constants.screen_height*0.97,this);
	}

	@Override
	public  void update() {
		// TODO Auto-generated method stub
		dx = arr.get(0).gx;
		dy = arr.get(0).gy;
		// now you have to do movement
		if (GDV5.KeysTyped[KeyEvent.VK_W] ) {//this fixes the error of adding the new blocks
			if(dy <=0) {
			turnedpositions.add(new Turningpoint((arr.get(0).getCenterX()), arr.get(0).getCenterY(), 0, -1)); //creates a unit vector with argument = k*pi/2
			}
			GDV5.KeysTyped[KeyEvent.VK_W] = false;
		}
		if (GDV5.KeysTyped[KeyEvent.VK_S]   ) {
			if(dy >=0) {
			turnedpositions.add(new Turningpoint(arr.get(0).getCenterX(), arr.get(0).getCenterY(), 0, 1));
			}
			GDV5.KeysTyped[KeyEvent.VK_S] = false;
		}
		if (GDV5.KeysTyped[KeyEvent.VK_A] ) {
			if(dx<=0) {
			turnedpositions.add(new Turningpoint(arr.get(0).getCenterX(), arr.get(0).getCenterY(), -1, 0));
			}
			GDV5.KeysTyped[KeyEvent.VK_A] = false;
		}
		if (GDV5.KeysTyped[KeyEvent.VK_D]  ) {
			if(dx >=0) {
			turnedpositions.add(new Turningpoint(arr.get(0).getCenterX(), arr.get(0).getCenterY(), 1, 0));
			}
			GDV5.KeysTyped[KeyEvent.VK_D] = false;
		}
		
			super.shiftSnake();
		
		
		

		for (int i = 0; i < arr.size(); i++) {
			arr.get(i).update();
		}

		collisionHandling();

		// System.out.println("health "+ health );
		
		
		super.update();
		
	
	}
	
	
	public void collisionHandling() {
		 
		super.collisionHandling();
		if(Screen.player2 != null) {
		for(int i = 0; i < Screen.player2.arr.size();i++) {
			if(arr.get(0).intersects(Screen.player2.arr.get(i))) {
				
				health = 0;
				
			}
			
		}
	}
	}

	
	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
	
		super.draw(win);

	}

}