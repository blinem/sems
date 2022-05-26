package loginInterface;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Korniza extends JFrame {
	private static Image logo = new ImageIcon("res/logosmall.png").getImage();
	private static final int GJERESIA = 1200;
	private static final int GJATESIA = GJERESIA * 9 / 16;

	public Korniza() {
		setIconImage(logo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setSize(GJERESIA + 15, GJATESIA + 38);
		setTitle("SEMS");
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
