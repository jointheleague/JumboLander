
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Scene {

	private ArrayList<SceneObject> scene;
	Random rand = new Random();
	BufferedImage bird, cloud, sun, tree;

	public Scene(int length) {
		scene = new ArrayList<SceneObject>();
		bird = cloud = sun = tree = null;
		try {
			bird = ImageIO.read(getClass().getResourceAsStream("/bird.png"));
			cloud = ImageIO.read(getClass().getResourceAsStream("/cloud.png"));
			sun = ImageIO.read(getClass().getResourceAsStream("/june.png"));
			tree = ImageIO.read(getClass().getResourceAsStream("/tree.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < rand.nextInt(length / 500) + 10; i++) {
			scene.add(new SceneObject(bird, rand.nextInt(length + 800) - 800,
					rand.nextInt(400), true));
		}
		for (int i = 0; i < rand.nextInt(length / 500) + 10; i++) {
			scene.add(new SceneObject(cloud, rand.nextInt(length + 800) - 800,
					rand.nextInt(150), false));
		}
		for (int i = 0; i < rand.nextInt(length / 500) + 10; i++) {
			scene.add(new SceneObject(tree, rand.nextInt(length + 800) - 800,
					400 - tree.getHeight(), true));
		}
		scene.add(new SceneObject(sun, -500, 0, false));
	}

	public void update() {
		for (SceneObject so : scene) {
			so.distance -= 8;

		}
	}

	public void draw(Graphics g) {
		update();

		for (SceneObject so : scene) {
			if (!so.isHazard)
				g.drawImage(so.image, -so.distance, so.altitude, so.width,
						so.height, null);
		}
		for (SceneObject so : scene) {
			if (so.isHazard)
				g.drawImage(so.image, -so.distance, so.altitude, so.width,
						so.height, null);
		}
	}

	public ArrayList<CollisionRect> getCollisionRects() {
		ArrayList<CollisionRect> crs = new ArrayList<CollisionRect>();
		for (SceneObject so : scene)
			if (so.isHazard)
				crs.add(new CollisionRect(-so.distance, so.altitude, so.width,
						so.height));
		return crs;
	}
}
