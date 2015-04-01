

import java.awt.image.BufferedImage;

public class SceneObject {

	public int distance;
	public int altitude;
	public int width;
	public BufferedImage image;
	public int height;
	public boolean isHazard;

	public SceneObject(BufferedImage image, int distance, int altitude,
			boolean isHazard) {
		this.image = image;
		this.distance = distance;
		this.altitude = altitude;
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.isHazard = isHazard;
	}
}
