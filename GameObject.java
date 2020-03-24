package Snake;

import java.awt.Graphics2D;

public interface GameObject {
	
	static final int width = 800;
	static final int height = 800;
	static final int FPS = 30;

	void update();
	void draw(Graphics2D win);
}
