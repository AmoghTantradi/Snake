package Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

//sometimes does'nt acknowledge level changing 
public class Screen {

	static int gamestate = 0;// 1 is multiplayer, 2 is single player, 3 is loser screen singleplayer, 4 is split screen
								// for multiplayer
	// 5 is winner screen for single player

	static Body player1;
	static Enemysnake player2;
	static int scorep1 = 0;
	static int scorep2 = 0;
	static int livesp1;
	static int livesp2;
	static int level = 1;
	static BufferedImage apple;
	static BufferedImage rocket;
	static BufferedImage powerup;
	boolean[] levels;
	Spawnfood food;
	Turret[] t;
	ArrayList<Tank> group;
	ArrayList<Powerup> p;
	FrontImage sprite;

	public Screen(ArrayList<BufferedImage> images) {

		sprite = new FrontImage(images.get(0));

		apple = images.get(1);

		rocket = images.get(2);

		powerup = images.get(3);

	}

	public void update() {
		if (gamestate == 0) {// this is the home/menu screen
			player1 = null;
			player2 = null;
			food = null;
			sprite.update();
			playBackgroundsound();
		} 
		else if (gamestate == 1) {// multiplayer
			food.update();
			if (player1 != null)
				player1.update();
			if (player2 != null)
				player2.update();

			updateTurrets();
			updateTanks();
			updatePowerups();
			checkCollision();
			updateGame();
			playBackgroundsound();
			
		}
		else if (gamestate == 2) {// single player
			food.update();
			if (player1 != null) {
				player1.update();
			}

			updateTurrets();
			updateTanks();
			updatePowerups();
			checkCollision();
			updateGame();
			playBackgroundsound();

		}
	}

	public void playBackgroundsound() {
		if ((!Snake.s.isPlaying(3)) && (gamestate == 1 || gamestate == 2 || gamestate == 0)) {
			Snake.s.play(3);
		} else {
			if (gamestate == 3 || gamestate == 4 || gamestate == 5) {
				Snake.s.stop(3);
			}
		}
	}

	public void updatePowerups() {

		for (int i = 0; i < p.size(); i++) {

			if (p.get(i).intersects(player1.arr.get(0))) {

				if (player1.health != Constants.max_health) {
					if (player1.health + Constants.health_boost >= Constants.max_health) {
						player1.health = Constants.max_health;
					} else {
						player1.health += Constants.health_boost;
					}
				}

				p.remove(i);
			} else {

				if (gamestate == 1) {
					if (p.get(i).intersects(player2.arr.get(0))) {

						if (player2.health != Constants.max_health) {
							if (player2.health + Constants.health_boost >= Constants.max_health) {
								player2.health = Constants.max_health;
							} else {
								player2.health += Constants.health_boost;
							}
						}
						p.remove(i);
					}
				}

			}

		}

		if (p.size() == 0) {
			for (int i = 0; i < 3; i += 1)
				p.add(new Powerup(30));
		}

	}

	public void updateTurrets() {
		for (int i = 0; i < t.length; i++) {
			if (t[i] != null) {
				t[i].update();
			}
		}

	}

	public void updateTanks() {
		for (int i = 0; i < group.size(); i++) {
			group.get(i).update();
		}
	}

	public void restart() { // this will reset everything to the original thing and is called whenever we press 1 or 2 to play
		level = 1;
		scorep1 = 0;
		scorep2 = 0;

		t = new Turret[4];

		levels = new boolean[3];// 3 is the max num of levels remember to set all values to false after
								// completion.

		group = new ArrayList<Tank>();

		p = new ArrayList<Powerup>();

		start();
	}

	public void start() {

		player1 = new Body();
		if (gamestate == 1) {// only spawns the second snake if it is a multiplayer
			player2 = new Enemysnake();
		} 
		else {// spawns a tank for level 1 of the single player
			group.add(new Tank());
		}
		food = new Spawnfood();
		p.add(new Powerup(30));
		food.spawn();// creates the apple
		t[0] = new Turret((Constants.screen_width / (double) 8), (Constants.screen_height / (double) 8));
		t[1] = new Turret((6 * Constants.screen_width / (double) 8), (Constants.screen_height / (double) 8));
		t[2] = new Turret((Constants.screen_width / (double) 8), (6 * Constants.screen_height / (double) 8));
		t[3] = new Turret((6 * Constants.screen_width / (double) 8), (6 * Constants.screen_height / (double) 8));
		livesp1 = 2;// sets the number of lives for the snake
		livesp2 = 2;
		for (int i = 0; i < levels.length; i++) {
			levels[i] = false;// the levels have not yet been completed
		}

	}

	public void checkCollision() {
		if (player1 != null) {
			if (player1.isDead) {
				player1 = null;
			}
		}
		if (player2 != null) {
			if (player2.isDead) {
				player2 = null;
			}
		}
	}

	public void kill() {// this will set everything to null

		player1 = null;
		player2 = null;
		level = 0;
		food = null;
		t = null;
		levels = null;
		group = null;
		p = null;

	}

	public void updateGame() {

		/*
		 * if (gamestate == 2) {// single player
		 * 
		 * if (player1 == null) {
		 * 
		 * if (livesp1 > 0) {
		 * 
		 * player1 = new Body(); livesp1 -= 1;
		 * 
		 * } else { gamestate = 3; kill(); }
		 * 
		 * if (scorep1 == 100 && !levels[0]|| scorep1 == 200 && !levels[1]) {// put
		 * twice just in case it dies while reaching a new
		 * food.spawned.removeAll(food.spawned); // level player1 = new Body(); if(level
		 * == 1) { levels[0] =true; } else levels[1] = true; level += 1; livesp1 = 2;
		 * group.removeAll(group); for (int i = 0; i < scorep1/100; i++) { group.add(new
		 * Tank()); } food.spawn(); } else if (scorep1 == 300 && !levels[2]) {
		 * food.spawned.removeAll(food.spawned); gamestate = 5; kill(); }
		 * 
		 * } else {//it is alive so this does the level changing
		 * 
		 * if (scorep1 == 100 && !levels[0]|| scorep1 == 200 && !levels[1]) {// put
		 * twice just in case it dies while reaching a new
		 * food.spawned.removeAll(food.spawned); // level player1 = new Body(); if(level
		 * == 1) { levels[0] =true; } else levels[1] = true; level += 1; livesp1 = 2;
		 * group.removeAll(group); for (int i = 0; i < scorep1/100; i++) { group.add(new
		 * Tank()); } food.spawn(); } else if (scorep1 == 300 && !levels[2]) {
		 * food.spawned.removeAll(food.spawned); gamestate = 5; kill(); } } }
		 * 
		 * else { // gamestate ==1 (multiplayer)
		 * 
		 * if (player1 == null) {
		 * 
		 * if (livesp1 > 0) {// has enough lives player1 = new Body(); livesp1--; } else
		 * {// runs out of lives
		 * 
		 * if (player2 != null) {
		 * 
		 * if (level != 3) { level += 1; player1 = new Body(); livesp1 = 2;
		 * group.removeAll(group); for (int i = 0; i < level; i++) group.add(new
		 * Tank()); } else {// if it is level 3, end the game gamestate = 4; kill(); }
		 * 
		 * } else {// player2 is dead so 2nd if statement comes into play if (level !=
		 * 3) {
		 * 
		 * player1 = new Body(); livesp1 = 2;
		 * 
		 * } else {// if it is level 3, end the game gamestate = 4; kill(); }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * if (player2 == null) { if (livesp2 > 0) {// has enough lives player2 = new
		 * Enemysnake(); livesp2--; } else {// runs out of lives
		 * 
		 * if (level != 3) { level += 1; player2 = new Enemysnake(); livesp2 = 2;
		 * group.removeAll(group); for (int i = 0; i < level; i++) group.add(new
		 * Tank()); } else {// if it is level 3, end the game gamestate = 4; kill(); }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 */
		
		
		//if it is singlep player
		
		//if player 1 is dead 
		
		//if it has enough lives, restart player 1
		
		//otherwise, if it has progressed to level 1 or level 2 or level 3, promote it 
		
		//otherwise, kill it and reset it to end screen
		
		//if player 1 is alive
		
		//if it has cleared level 1 or level 2 or level 3, do the updating thing
		
		if(gamestate == 2) { //singleplayer
			
			if(player1 == null) {
				
				if(livesp1 > 0) {
					player1 = new Body();
					livesp1-=1;
					group.removeAll(group);
					for(int i = 0; i< level; i++) {
						group.add(new Tank());
					}
					
				}
				else {//it doesnt have enough lives
					
					//needs to be killed
					
					kill();
					
					gamestate = 3;//single player loser screen
					
				}
					
				}//end if
			
			else {//player 1 is not null
				
				//now check if it has progressed through a level 
				
				if(!levels[0]) {
					
					if(scorep1 == 100) {//update shit
						
						kill();
						
						restart();
						
						scorep1 = 100;
						
						level = 2;
						
						group.add(new Tank());
						
						levels[0] = true;
						
					}
					//otherwise do nothing and wait
					
				}
				 if (!levels[1]) {
					
					if(scorep1 == 200) {//update shit
						
							kill();
							
							restart();
							
							scorep1 = 200;
							
							level = 3;
							
							group.add(new Tank());
							group.add(new Tank());//3 tanks at this level
							
							levels[1] = true;
						
					}
					
					
					
				}
				 if (!levels[2]) {
					
					if(scorep1 == 300) {//update shit-player wins the game
						
							kill();
							
							gamestate = 5;
						
					}
					
				}
				
				
				
				
			}
			
			
			
				
				
				
				
			}
		else {
			if (player1 == null) {
	
				if (livesp1 > 0) {// has enough lives
					player1 = new Body();
					livesp1--;
				} else {// runs out of lives
	
					if (player2 != null) {
	
						if (level != 3) {
							level += 1;
							player1 = new Body();
							livesp1 = 2;
							group.removeAll(group);
							for (int i = 0; i < level; i++)
								group.add(new Tank());
						} else {// if it is level 3, end the game
							kill();
							gamestate = 4;
							
						}
	
					} else {// player2 is dead so 2nd if statement comes into play
						if (level != 3) {
	
							player1 = new Body();
							livesp1 = 2;
	
						} else {// if it is level 3, end the game
							kill();
							gamestate = 4;
							
						}
	
					}
	
				}
	
			}
	
			if (player2 == null) {
				if (livesp2 > 0) {// has enough lives
					player2 = new Enemysnake();
					livesp2--;
				} else {// runs out of lives
	
					if (level != 3) {
						level += 1;
						player2 = new Enemysnake();
						livesp2 = 2;
						group.removeAll(group);
						for (int i = 0; i < level; i++)
							group.add(new Tank());
					} else {// if it is level 3, end the game
						kill();
						gamestate = 4;
						
					}
	
				}
	
			}

	}
		
			
			
			
			
			
			
		
}
			
			
			
		

	

	public void draw(Graphics2D win) {
		if (gamestate == 0) {// home screen
			Font font = new Font("TimesNewRoman", Font.BOLD, 300);
			win.setFont(font);
			win.setColor(Color.white);
			win.drawString("SNAKE", (int) (Constants.screen_width * 0.10), (int) (Constants.screen_height * 0.35));
			sprite.draw(win);
			Font font2 = new Font("TimesNewRoman", Font.BOLD, 40);
			win.setFont(font2);
			win.drawString("Use WASD to control player 1 and Arrow keys to control player 2",
					(int) (Constants.screen_width * 0.015), (int) (Constants.screen_height * 0.95));
			;
			win.drawString("Press 1 for singleplayer and 2 for multiplayer", (int) (Constants.screen_width * 0.175),
					(int) (Constants.screen_height * 0.45));
			win.setColor(Color.gray);
			win.drawString("By Amogh", 0, (int) (Constants.screen_height * 0.075));
		}
		// animation.draw(win);
		else if (gamestate == 1) {// multiplayer
			if (player1 != null)
				player1.draw(win);
			food.draw(win);
			if (player2 != null)
				player2.draw(win);

			for (int i = 0; i < t.length; i++) {
				if (t[i] != null) {
					t[i].draw(win);
				}
			}
			for (int i = 0; i < group.size(); i++) {
				group.get(i).draw(win);
			}
			for (int i = 0; i < p.size(); i++) {
				p.get(i).draw(win);
			}
			Font font = new Font("TimesNewRoman", Font.BOLD, 50);
			win.setFont(font);
			win.setColor(Color.white);
			win.drawString("Player 1 : " + scorep1, 10, (int) (Constants.screen_height * 0.95));
			win.drawString("Level : " + level, (int) (Constants.screen_width * 0.4),
					(int) (Constants.screen_height * 0.95));
			win.drawString("Player 2 : " + scorep2, (int) (Constants.screen_width * 0.70),
					(int) (Constants.screen_height * 0.95));
			win.drawString("Lives : " + (livesp1 + 1), 10, (int) (Constants.screen_height * 0.85));
			win.drawString("Lives : " + (livesp2 + 1), (int) (Constants.screen_width * 0.70),
					(int) (Constants.screen_height * 0.85));

		} else if (gamestate == 2) {// single player
			if (player1 != null)
				player1.draw(win);
			food.draw(win);
			for (int i = 0; i < t.length; i++) {
				if (t[i] != null) {
					t[i].draw(win);
				}
			}
			for (int i = 0; i < group.size(); i++) {
				group.get(i).draw(win);
			}
			for (int i = 0; i < p.size(); i++) {
				p.get(i).draw(win);
			}
			Font font = new Font("TimesNewRoman", Font.BOLD, 50);
			win.setFont(font);
			win.setColor(Color.white);
			win.drawString("Player 1 : " + scorep1, 10, (int) (Constants.screen_height * 0.95));
			win.drawString("Level : " + level, (int) (Constants.screen_width * 0.4),
					(int) (Constants.screen_height * 0.95));
			win.drawString("Lives : " + (livesp1 + 1), (int) (Constants.screen_width * 0.75),
					(int) (Constants.screen_height * 0.95));
		} else if (gamestate == 3) {// loser screen for single player
			// animation.draw(win);
			Font font = new Font("TimesNewRoman", Font.BOLD, 275);
			win.setFont(font);
			win.setColor(Color.white);
			win.drawString("You Lose ", 0, (int) (Constants.screen_height * 0.35));
			Font font2 = new Font("TimesNewRoman", Font.BOLD, 40);
			win.setFont(font2);
			win.drawString("Press 1 for singleplayer and 2 for multiplayer", (int) (Constants.screen_width * 0.175),
					(int) (Constants.screen_height * 0.45));
		} else if (gamestate == 4) {// split loser/winner screen for multiplayer
			Font font = new Font("TimesNewRoman", Font.BOLD, 100);
			win.setFont(font);
			win.setColor(Color.white);
			if (scorep1 > scorep2) {
				win.drawString("You Win!", 0, (int) (Constants.screen_height * 0.25));
				win.drawString("You Lose!", (int) (Constants.screen_width * 0.6),
						(int) (Constants.screen_height * 0.25));
			} else if (scorep1 < scorep2) {
				win.drawString("You Lose!", 0, (int) (Constants.screen_height * 0.25));
				win.drawString("You Win!", (int) (Constants.screen_width * 0.60),
						(int) (Constants.screen_height * 0.25));
			} else {
				Font font2 = new Font("TimesNewRoman", Font.BOLD, 300);
				win.setFont(font2);
				win.setColor(Color.white);
				win.drawString("Draw", (int) (Constants.screen_width * 0.25), (int) (Constants.screen_height * 0.35));
			}

			Font font2 = new Font("TimesNewRoman", Font.BOLD, 40);
			win.setFont(font2);

			win.drawString("Press 1 for singleplayer and 2 for multiplayer", (int) (Constants.screen_width * 0.175),
					(int) (Constants.screen_height * 0.45));

		} else {// gamestate == 5, single player wins
			Font font2 = new Font("TimesNewRoman", Font.BOLD, 275);
			win.setFont(font2);
			win.setColor(Color.white);
			win.drawString("You Win!", 0, (int) (Constants.screen_height * 0.35));
			Font font3 = new Font("TimesNewRoman", Font.BOLD, 40);
			win.setFont(font3);
			win.drawString("Press 1 for singleplayer and 2 for multiplayer", (int) (Constants.screen_width * 0.175),
					(int) (Constants.screen_height * 0.45));

		}
	}

}
