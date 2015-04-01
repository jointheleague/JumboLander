

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Landscape {

	private BufferedImage land;
	private int distance;
	int where;

	public Landscape(int distance) {
		try {
			land = ImageIO
					.read(getClass().getResourceAsStream("/land.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.distance = distance;
	}

	public void update() {
		distance -= 8;
		where += 8;
	}

	public void draw(Graphics g) {
		update();
		
		for(int i = 0; i < distance + 2000; i++){
			g.drawImage(land, where- (800*i), 0,
					land.getWidth(), land.getHeight(), null);
		}
	}
}
