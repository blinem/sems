package loginInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Controller;

@SuppressWarnings("serial")
public class Login extends JPanel {
	private static final int GJERESIA = 1200;
	private static final int GJATESIA = GJERESIA * 9 / 16;

	private static Image img = new ImageIcon("res/ss2.jpg").getImage();
	private static Image logo = new ImageIcon("res/logosmall.png").getImage();

	private JTextField perdoruesiTf;

	private JPasswordField fjalkalimiPf;

	private JLabel perdoruesiLb;
	private JLabel fjalkalimiLb;

	private JButton hyr;

	public Login(Controller controller) {
		setLayout(null);
		setMinimumSize(new Dimension(GJERESIA, GJATESIA));
		setMaximumSize(new Dimension(GJERESIA, GJATESIA));
		setPreferredSize(new Dimension(GJERESIA, GJATESIA));

		perdoruesiTf = new JTextField();
		perdoruesiTf.setBounds((GJERESIA - 125) / 2, (GJATESIA + 50) / 2, 200, 50);
		add(perdoruesiTf);

		fjalkalimiPf = new JPasswordField();
		fjalkalimiPf.setBounds((GJERESIA - 125) / 2, (GJATESIA + 175) / 2, 200, 50);
		add(fjalkalimiPf);

		perdoruesiLb = new JLabel("Përdoruesi:");
		perdoruesiLb.setBounds((GJERESIA - 325) / 2, (GJATESIA + 50) / 2, 100, 50);
		add(perdoruesiLb);

		fjalkalimiLb = new JLabel("Fjalëkalimi:");
		fjalkalimiLb.setBounds((GJERESIA - 325) / 2, (GJATESIA + 175) / 2, 100, 50);
		add(fjalkalimiLb);

		hyr = new JButton("Hyr");
		hyr.setFocusable(false);
		hyr.setBounds((GJERESIA - 50) / 2, (GJATESIA + 325) / 2, 125, 50);
		hyr.setBackground(new Color(220, 20, 60));
		hyr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.inicializo(perdoruesiTf.getText(), fjalkalimiPf.getText());				
			}
		});
		add(hyr);

	}

	public void paintComponent(Graphics g) {

		g.drawImage(img, 0, 0, GJERESIA + 15, GJATESIA + 30, null);
		g.setColor(Color.white);
		g.fillRect((GJERESIA - 400) / 2, (GJATESIA - 450) / 2, 400, 450);
		g.setColor(Color.black);
		g.drawRect((GJERESIA - 400) / 2, (GJATESIA - 450) / 2, 400, 450);
		g.drawImage(logo, (GJERESIA - 200) / 2, (GJATESIA - 400) / 2, 222, 200, null);

	}

	public void resetPerdoruesiText() {
		perdoruesiTf.setText("");
	}

	public void resetFjalkalimiText() {
		fjalkalimiPf.setText("");
	}
}
