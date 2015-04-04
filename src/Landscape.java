
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

public class Landscape {

	private BufferedImage land;
	int where, hour;

	public Landscape() {
		init();
	}

	public void init() {
		hour = new Date().getHours();
		try {
			if (18 > hour && hour > 7) {
				land = ImageIO
						.read(getClass().getResourceAsStream("/land.png"));
			} else if (20 >= hour && hour >= 18) {
				land = ImageIO.read(getClass().getResourceAsStream(
						"/landAfternoon.png"));
			} else if (7 >= hour && hour >= 5) {
				land = ImageIO.read(getClass().getResourceAsStream(
						"/landMorning.png"));
			} else if (4 >= hour || hour > 20) {
				land = ImageIO.read(getClass().getResourceAsStream(
						"/landNight.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		where += 8;
	}

	public void draw(Graphics g) {
		update();

		for (int i = 0; i < 20; i++) {
			g.drawImage(land, where - (800 * i), 0, land.getWidth(),
					land.getHeight(), null);
		}
	}
}
