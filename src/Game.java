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
	Sound s;

	long timer = System.currentTimeMillis();
	int length;
	int fps = 40;
	boolean isInControl = true;

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
		s = new Sound();
		s.playOnLoop(s.radioChatter);
		s.playOnLoop(s.engine);
	}

	public void paint(Graphics g) {
		try {
			long sleep = timer + 1000 / fps - System.currentTimeMillis();
			if (sleep > 0)
				Thread.sleep(sleep);
			else
				System.out.println("Computer can't keep up with " + fps
						+ " fps!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		if (rand.nextInt(fps * 2) == 1)
			s.play(s.bingBong);

		land.draw(g);
		runway.draw(g);
		plane.draw(g);
		scene.draw(g);

		timer = System.currentTimeMillis();
		if (plane.isOnRunway(runway) && isInControl) {
			drawWin(g);
			s.stopAmbient();
			s.play(s.victory);
		} else if (plane.isDead() && isInControl) {
			drawGameOver(g, "Game Over!");
			s.stopAmbient();
		} else if (plane.tooFar() && isInControl) {
			drawGameOver(g, "You Missed the Runway!");
			s.stopAmbient();
			s.play(s.boo);
		} else if ((plane.collidedWith(scene) && isInControl) || !isInControl) {
			if (isInControl)
				s.play(s.explosion);
			plane.drawExploded(g);
			drawGameOver(g, "You Crashed!");
			s.stopAmbient();
			isInControl = false;
			plane.setThrottle(false);
			if (plane.getAltitude() <= 400)
				repaint();
		} else
			repaint();
	}

	public void drawGameOver(Graphics g, String message) {
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 40));
		g.setColor(Color.BLACK);
		g.drawString(message, this.getWidth() / 2 - message.length() * 10, 100);
	}

	public void drawWin(Graphics g) {
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 40));
		g.setColor(Color.BLACK);
		g.drawString("You Win!", this.getWidth() / 2, 100);
	}

	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
			if (isInControl)
				plane.setThrottle(true);
		} else if (ke.getKeyCode() == KeyEvent.VK_M) {
			this.dispose();
			new JumboLanderMain();
		}
	}

	public void keyReleased(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
			if (isInControl)
				plane.setThrottle(false);
		}
	}

	public void keyTyped(KeyEvent arg0) {
		return;
	}

}
