import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JumboLanderMain extends JFrame implements ActionListener {

	JButton start, help, tSound;
	JPanel welcomepanel;
	boolean sound = true;

	public static void main(String[] args) {
		new JumboLanderMain();
	}

	public JumboLanderMain() {
		init();
		this.setTitle("Jumbo Lander");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(true);
	}

	public void init() {
		welcomepanel = new JPanel(new GridLayout(3, 1));
		start = new JButton("Play Jumbo Lander");
		help = new JButton("Instructions / Credits");
		tSound = new JButton("Disable Sound");

		start.addActionListener(this);
		help.addActionListener(this);
		tSound.addActionListener(this);

		welcomepanel.add(start);
		welcomepanel.add(help);
		welcomepanel.add(tSound);

		this.add(welcomepanel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(start)) {
			new Game(sound);
		} else if (e.getSource().equals(help)) {
			JOptionPane
					.showMessageDialog(
							null,
							"To go up, press space. To go down, release space. The main objective of Jumbo Lander is to land on the runway without hitting any trees or birds.");
		} else if (e.getSource().equals(tSound)) {
			if(sound == true) {
				sound = false;
				tSound.setText("Enable Sound");
			}
			else{
				sound = true;
				tSound.setText("Disable Sound");
			}
		}
	}
}
