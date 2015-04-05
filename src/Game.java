import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
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

	public Game(boolean allowSound) {
		this.setTitle("Jumbo Lander");
		this.setSize(800, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
		this.setVisible(true);
		this.setResizable(false);
		
		try {
	        this.setIconImage(ImageIO.read(new File("res/icon.png")));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

		length = rand.nextInt(6 * 800) + 3 * 800;
		plane = new Plane(length);
		runway = new Runway(length);
		scene = new Scene(length);
		land = new Landscape();
		s = new Sound(allowSound);

		s.playOnLoop(Sound.radioChatter);
		s.playOnLoop(Sound.engine);
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

		if (rand.nextInt(fps*3) == 1)
			s.play(Sound.bingBong);

		land.draw(g);
		runway.draw(g);
		plane.draw(g);
		scene.draw(g);

		timer = System.currentTimeMillis();
		if (plane.isOnRunway(runway) && isInControl) {
			drawWin(g);
			s.stopAmbient();
			s.playOnLoop(Sound.victory);
		} else if (plane.tooFar() && isInControl) {
			drawGameOver(g, "You Missed the Runway!");
			s.stopAmbient();
			s.playOnLoop(Sound.boo);
		} else if ((plane.isDead() || plane.collidedWith(scene) && isInControl)
				|| !isInControl) {
			if (isInControl) {
				s.play(Sound.explosion);
				s.stopAmbient();
			}
			plane.drawExploded(g);
			drawGameOver(g, "You Crashed!");
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
	
	public void reset() {
		length = rand.nextInt(6 * 800) + 3 * 800;
		plane.init(length);
		runway.init(length);
		scene.init(length);
		land.init();
		s.init();

		s.playOnLoop(Sound.radioChatter);
		s.playOnLoop(Sound.engine);
		
		isInControl = true;
		repaint();
	}

	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
			if (isInControl)
				plane.setThrottle(true);
		} else if (ke.getKeyCode() == KeyEvent.VK_R) {
			reset();
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
