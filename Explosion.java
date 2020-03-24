package Snake;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Explosion implements GameObject {
	
	ArrayList <ExplosionParticles> particles;
	
	Random rand;
	
	double speed = 10;
	
	public Explosion(double a, double b) {
		
		particles = new ArrayList <ExplosionParticles>();
		
		rand = new Random();
		
		for(int i = 0; i< 100;i++) {
			particles.add(new ExplosionParticles(a, b));
			particles.get(i).dx = rand.nextGaussian()*speed;
			particles.get(i).dy = rand.nextGaussian()*speed;
		}
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		for(int i = 0; i< particles.size();i++) {
			particles.get(i).update();
			if(particles.get(i).outBounds() || particles.get(i).dx == 0 && particles.get(i).dy == 0) {
				particles.remove(i);
			}
		}
		
		
		
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).draw(win);
		}
		
	}

}
