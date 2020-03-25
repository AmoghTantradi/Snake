package Snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Turret extends Rectangle2D.Double implements GameObject {
	
	double theta = 0;
	double dTheta = 2.0f;
	double reqTheta;
	boolean fire = false;
	private double sizex = 30.0f,sizey = 30.0f;
	ArrayList<Laser> l;
	ArrayList<Explosion> e;
	public Turret( double x, double y) {
	super(x, y,30,30);

	l = new ArrayList<Laser>();
	
	e = new ArrayList<Explosion>();
	

	
	
	}
	
	public void setSize(double x, double y) {
		sizex = x;
		sizey = y;
		this.setFrame(this.x, this.y,x, y);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		//calculate angle between itself and the snake
		//then shoots only if it is aimed at the snake 
		
		if(l.size()==0) {
			if(fire) {
				l.add(new Laser(this));
				Snake.s.setVolume(0, 0.35f);
				Snake.s.play(0);
			}
		}
		
		for(int i = 0; i< l.size();i++) {
			l.get(i).update(this);
		}
		removeLaser();
		//check if any lasers are still active
	
		weaponSystems();	//aims the turret at the snake and does collision handling 
		
		updateExplosions();
		
	}
	
	public void shiftTurret(double dx, double dy) {
		this.setRect(this.x+dx, this.y+dy,sizex, sizey);
	}
	
	private  void updateExplosions() {
		for(int i = 0; i < e.size();i++) {
			e.get(i).update();
			if(e.get(i).particles.size() == 0) {
				e.remove(i);
			}
		}
	}
	
	
	private void removeLaser() {
		for(int i = 0; i< l.size(); i++) {
			
			if(l.get(i).outBounds()) {
				l.remove(i);
			}
		}
	}
	
	private void weaponSystems() {
		if(Screen.gamestate == 1) {
			if(Screen.player2!=null) {
				if(!Screen.player2.isDead) {
					if(this.getCenterY()>=Constants.screen_height/2) {
						changeOrientation(Screen.player2);
						
					}
					else {
						changeOrientation(Screen.player1);
					}
				
			}
				
				
				hitBox(Screen.player2);
				
		}
			else {
				changeOrientation(Screen.player1);
			}
			
		}
		else {
			changeOrientation(Screen.player1);
		}
		hitBox(Screen.player1);
	}
		
		
	
	
	private void changeOrientation(Supersnake s) {
		
			
			reqTheta = 0;
			
			double delX = s.arr.get(0).getCenterX() - this.getCenterX();
			
			double delY = s.arr.get(0).getCenterY() - this.getCenterY();
			
			reqTheta = Math.toDegrees(Math.atan2(delY, delX)) +90.0f;//this is in degrees 
			reqTheta+=360;
			reqTheta%=360;
			
			double angDist1 = reqTheta - this.theta;
			
			double angDist2 = 360.0 - angDist1;
			
			angDist1+=360;
			angDist1%=360;
			
			angDist2+=360;
			angDist2%=360;
			
			
			if (!(theta - reqTheta < Constants.epsilon && theta - reqTheta > -Constants.epsilon)) {
			
			
			//	System.out.println("dist1: "+ angDist1+" dist2: "+ angDist2);
				
				if(Math.abs(angDist1)>=Math.abs(angDist2)) {
					theta -= dTheta;
					theta+=360;
					theta%=360;
				}
				else {
					theta+=dTheta;
					theta+=360;
					theta%=360;
				}

			}else {
			//	System.out.println("theta : "+theta +" ReqTheta: "+ reqTheta);
				fire = true;//if the turret has aimed at the snake, shoot.
			}
			
		
			
			
			
		}
		
		
		
		
		
//	}
	
	private void hitBox(Supersnake s) {//this will damage the snake 
		for(int i = 0; i< s.arr.size();i++) {
			for(int j = 0; j < l.size();j++) {
				if(l.get(j).intersects(s.arr.get(i))) {
					if(i!=0) {
				
						s.arr.get(i).doDamage(10*Screen.level);
						if(s.arr.get(i).health<=0) {
							e.add(new Explosion(s.arr.get(i).getCenterX(),s.arr.get(i).getCenterY()));
							s.arr.remove(i);
							Snake.s.play(1);
							s.doDamage(2*Screen.level);
							
						}
					
						l.remove(j);
					}
					else  {
						l.remove(j);
						
						s.doDamage(5*Screen.level);//increases damage every level
					}
				}
			}
		}
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub

		AffineTransform a = win.getTransform();
		win.rotate(Math.toRadians(theta),this.getCenterX(), this.getCenterY());
		win.setColor(Color.WHITE);
		win.fill(this);
		win.setTransform(a);
		
		for(int i = 0; i< l.size();i++) {
			l.get(i).draw(win);
		}
		for(int i = 0;i< e.size();i++) {
			e.get(i).draw(win);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
