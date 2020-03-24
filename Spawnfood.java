package Snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Spawnfood implements GameObject {

	ArrayList<Apple> spawned;

	public Spawnfood() {
		spawned = new ArrayList<Apple>();
	}

	public void spawn() {// maybe spawn all in a circle/

		spawned.add(new Apple());

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

		for (int i = 0; i < spawned.size(); i++) {

			if (Screen.player1 != null) {

				for (int j = 0; j < Screen.player1.arr.size(); j++) {

					if (spawned.get(i).intersects(Screen.player1.arr.get(j))) {

						Screen.scorep1 += 20;

						spawned.remove(i);
						Snake.s.setVolume(2, 6.0206f);
						Snake.s.play(2);

						Screen.player1.pauseMovement();

						Screen.player1.arr.add(new Bodypart(
								(Screen.player1.arr.get(Screen.player1.arr.size() - 1).x)
										+ (-Screen.player1.arr.get(Screen.player1.arr.size() - 1).gx
												* Screen.player1.arr.get(Screen.player1.arr.size() - 1).mag),
								(Screen.player1.arr.get(Screen.player1.arr.size() - 1).y)
										+ (-Screen.player1.arr.get(Screen.player1.arr.size() - 1).gy
												* Screen.player1.arr.get(Screen.player1.arr.size() - 1).mag),
								Screen.player1.arr.get(0).mag, Color.cyan));

						Screen.player1.arr.get(Screen.player1.arr.size() - 1).gx = Screen.player1.arr
								.get(Screen.player1.arr.size() - 2).gx;
						Screen.player1.arr.get(Screen.player1.arr.size() - 1).gy = Screen.player1.arr
								.get(Screen.player1.arr.size() - 2).gy;

						Screen.player1.shiftSnake();
						Screen.player1.continueMovement();

						// this is spawing apple's too fast that's why
						this.spawn();

					}

				}

			}
			if (Screen.player2 != null) {

				for (int j = 0; j < Screen.player2.arr.size(); j++) {

					if (spawned.get(i).intersects(Screen.player2.arr.get(j))) {

						Screen.scorep2 += 20;

						spawned.remove(i);
						Snake.s.setVolume(2, 6.0206f);
						Snake.s.play(2);


						Screen.player2.pauseMovement();

						Screen.player2.arr.add(new Bodypart(
								(Screen.player2.arr.get(Screen.player2.arr.size() - 1).x)
										+ (-Screen.player2.arr.get(Screen.player2.arr.size() - 1).gx
												* Screen.player2.arr.get(Screen.player2.arr.size() - 1).mag),
								(Screen.player2.arr.get(Screen.player2.arr.size() - 1).y)
										+ (-Screen.player2.arr.get(Screen.player2.arr.size() - 1).gy
												* Screen.player2.arr.get(Screen.player2.arr.size() - 1).mag),
								Screen.player2.arr.get(0).mag, Color.yellow));

						Screen.player2.arr.get(Screen.player2.arr.size() - 1).gx = Screen.player2.arr
								.get(Screen.player2.arr.size() - 2).gx;
						Screen.player2.arr.get(Screen.player2.arr.size() - 1).gy = Screen.player2.arr
								.get(Screen.player2.arr.size() - 2).gy;

						Screen.player2.shiftSnake();
						Screen.player2.continueMovement();

						this.spawn();

					}

				}

			}

		}

	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		for (int i = 0; i < spawned.size(); i++) {
			spawned.get(i).draw(win);
		}

	}

}
