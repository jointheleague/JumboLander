
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Plane {

	private BufferedImage plane, exploded;
	private int altitude, distance;
	private boolean throttle;

	public Plane(int distance) {
		try {
			plane = ImageIO.read(getClass().getResourceAsStream("/plane.png"));
			exploded = ImageIO.read(getClass().getResourceAsStream(
					"/exploded.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.distance = distance;
	}

	public boolean isDead() {
		if (altitude > -20F && altitude < 400)
			return false;
		return true;
	}

	public boolean isOnRunway(Runway r) {
		return this.getCollisionRect().isIn(r.getCollisionRect());
	}

	private void update() {
		if (throttle)
			altitude -= 5;
		else
			altitude += 5;
		
		distance -= 8;
	}

	public void draw(Graphics g) {
		update();
		g.drawImage(plane, 800 - plane.getWidth(), altitude, plane.getWidth(),
				plane.getHeight(), null);
	}

	public void drawExploded(Graphics g) {
		g.drawImage(exploded, 800 - plane.getWidth(), altitude,
				exploded.getWidth(), exploded.getHeight(), null);
	}

	public void setThrottle(boolean throttle) {
		this.throttle = throttle;
	}

	private CollisionRect getCollisionRect() {
		return new CollisionRect(615, altitude, plane.getWidth(),
				plane.getHeight());
	}

	public boolean collidedWith(Scene scene) {
		for (CollisionRect cr : scene.getCollisionRects())
			if (cr.isIn(this.getCollisionRect()))
				return true;
		return false;
	}

	public boolean tooFar() {
		if (distance < -800)
			return true;
		return false;
	}
}
