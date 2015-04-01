import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame implements KeyListener {

	Random rand = new Random();

	Plane plane;
	Runway runway;
	Scene scene;
	Landscape land;
	long timer;
	int length, paints;
	int fps = 40;

	public Game() {
		this.setTitle("Jumbo Lander");
		this.setSize(800, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
		this.setVisible(true);
		this.setResizable(false);

		length = rand.nextInt(6 * 800) + 3 * 800;
		plane = new Plane(length);
		runway = new Runway(length);
		scene = new Scene(length);
		land = new Landscape(length);
	}

	public void paint(Graphics g) {
		paints++;
		try {
			long sleep = timer + 1000 / fps - System.currentTimeMillis();
			if (sleep > 0)
				Thread.sleep(sleep);
			else if (paints != 1)
				System.out.println("Computer can't keep up with " + fps
						+ " fps!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		g.setColor(Color.CYAN);
		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		land.draw(g);
		runway.draw(g);
		plane.draw(g);
		scene.draw(g);

		timer = System.currentTimeMillis();
		if (plane.isOnRunway(runway))
			drawWin(g);
		else if (plane.isDead())
			drawGameOver(g, "Game Over!");
		else if (plane.tooFar())
			drawGameOver(g, "You Missed the Runway!");
		else if (plane.collidedWith(scene)) {
			plane.drawExploded(g);
			drawGameOver(g, "You Crashed!");
		} else
			repaint();
	}

	public void drawGameOver(Graphics g, String message) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		g.setColor(Color.BLACK);
		g.drawString(message, this.getWidth() / 2 - message.length()*10, 100);
	}

	public void drawWin(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		g.setColor(Color.BLACK);
		g.drawString("You Win!", this.getWidth() / 2, 100);
	}

	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
			plane.setThrottle(true);
		}
	}

	public void keyReleased(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
			plane.setThrottle(false);
		}
	}

	public void keyTyped(KeyEvent arg0) {
		return;
	}

}