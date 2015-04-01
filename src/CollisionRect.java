

public class CollisionRect {

	private int x, y, width, height;

	public CollisionRect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean isIn(CollisionRect cr) {
		return (x > cr.x && x < cr.x + cr.width)
				&& (x + width < cr.x + cr.width)
				&& (y > cr.y && y < cr.y + cr.height)
				&& (y + height < cr.y + cr.height);
	}
}
