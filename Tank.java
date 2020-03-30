package Snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class Tank extends Rectangle2D.Double implements GameObject {
	//tank number 2-3-7
	double theta= 0;
	double dTheta = 2.0f;
	double reqTheta;
	double speed = 1.75d;
	double dx = 0,dy = 0;
	static double  width = 20.0d;
	static double length = 40.0d;
	boolean moving = false;
	double ptx,pty;
	Turret t;
	
	public Tank() {
		super(Constants.screen_width/2,Constants.screen_height/2,width,length);
		t = new Turret(this.x,this.getCenterY()-width/2);
		t.setSize(width, width);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub;
		

		synchronizedMovement();//emulates tank movement better

		
		t.shiftTurret(dx, dy);
		this.setRect(this.x+this.dx,this.y+this.dy,width,length);
		t.update();
	}
	
	@SuppressWarnings("unused")
	private void randomMovement() {//emulates car movement better-not tank movement
	
		 ptx = (Math.random()*Constants.screen_width/2)+Constants.screen_width/4;
		 pty = (Math.random()*Constants.screen_height/2)+Constants.screen_height/4;
		
		
		movetoPoint(ptx,pty);
		
		dx = speed*Math.sin(Math.toRadians(theta));
		dy = -speed*Math.cos(Math.toRadians(theta));
	
	}
	
	private void stop() {
		dx = 0;
		dy = 0;
	}
	
	private void synchronizedMovement() {//emultated tank movement perfectly
		
		if(!moving) {
		 ptx = (Math.random()*Constants.screen_width/2)+Constants.screen_width/4;
		 pty = (Math.random()*Constants.screen_height/2)+Constants.screen_height/4;
		}
		 if(!this.contains(ptx, pty)) {
			 moving = true;
			 movetoPoint(ptx,pty);//shifts the tank to the required position
			 
			 if(Math.abs(theta - reqTheta)<=dTheta) {

					dx = speed*Math.sin(Math.toRadians(theta));
					dy = -speed*Math.cos(Math.toRadians(theta));
			 
		 }
		
	}
		 else {
			 stop();
			 moving = false;
			
		 }
		 
		
	}
	
	private void  movetoPoint(double x, double y) {
		
		double delX = x - this.getCenterX();
		
		double delY = y - this.getCenterY();
		
		reqTheta = Math.toDegrees(Math.atan2(delY, delX))+90;
		
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

		}

		

		
		
	
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		AffineTransform old = win.getTransform();
		win.rotate(Math.toRadians(theta),this.getCenterX(),this.getCenterY());
		win.setColor(Color.GRAY);
		win.fill(this);
		win.draw(this);
		win.setTransform(old);
		t.draw(win);
	}

}
