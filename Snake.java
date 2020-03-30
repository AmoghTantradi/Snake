package Snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Utilities.GDV5;
import Utilities.SoundDriver;

@SuppressWarnings("serial")
public class Snake extends GDV5 {
//review generation , especially healthpack generation. Also, review tank generation for single player mode
	//and also review level progression for single player
	//furthermore, try to clean up the code into methods if not done already 
	Screen screen;
	ArrayList<BufferedImage>images;
	
	static SoundDriver s;
	String [] sounds;

	public Snake() {
		images = new ArrayList<BufferedImage>();
		images.add( this.addImage("Images/spriteimag.png"));
		images.add(this.addImage("Images/apple.png"));
		images.add(this.addImage("Images/rocket.png"));
		images.add(this.addImage("Images/powerup.png"));
		this.setBackground(Color.BLACK);
		this.setSize((int) Constants.screen_width, (int) Constants.screen_height);
		this.setTitle("SNAKE");
		screen = new Screen(images);
		sounds = new String[4];
		sounds[0] = "Sounds/MissileSound.wav";
		sounds[1] = "Sounds/ExplosionSound.wav";
		sounds[2] = "Sounds/CrunchSound.wav";
		sounds[3] = "Sounds/RainSound.wav";
		s = new SoundDriver(sounds,this);
	}

	public static void main(String[] args) {
		Snake s = new Snake();
		s.start();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (GDV5.KeysTyped[KeyEvent.VK_1]
				&& (Screen.gamestate == 0 || Screen.gamestate == 3 || Screen.gamestate == 4 || Screen.gamestate == 5)) {
			Screen.gamestate = 2;// makes it a single player
			screen.restart();
			GDV5.KeysTyped[KeyEvent.VK_1] = false;
		}
		if (GDV5.KeysTyped[KeyEvent.VK_2]
				&& (Screen.gamestate == 0 || Screen.gamestate == 3 || Screen.gamestate == 4 || Screen.gamestate == 5)) {
			Screen.gamestate = 1;
			screen.restart();
			GDV5.KeysTyped[KeyEvent.VK_2] = false;
		}
		screen.update();
	}

	@Override
	public void draw(Graphics2D win) {
		screen.draw(win);
	}

}
