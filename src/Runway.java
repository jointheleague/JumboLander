

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Runway {

	private BufferedImage runway;
	private int distance;

	public Runway(int distance) {
		try {
			runway = ImageIO
					.read(getClass().getResourceAsStream("/runway.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		init(distance);
	}
	
	public void init(int distance) {
		this.distance = distance;
	}

	public void update() {
		distance -= 8;
	}

	public void draw(Graphics g) {
		update();
		g.drawImage(runway, -distance, 400 - runway.getHeight(),
				runway.getWidth(), runway.getHeight(), null);
	}

	public CollisionRect getCollisionRect() {
		return new CollisionRect(-distance, 400 - runway.getHeight(),
				runway.getWidth(), runway.getHeight());
	}
}
