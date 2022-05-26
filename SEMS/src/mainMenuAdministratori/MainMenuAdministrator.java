
package mainMenuAdministratori;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import databases.Administrator;


public class MainMenuAdministrator extends JPanel {
	private static final int GJERESIA = 1200;
	private static final int GJATESIA = GJERESIA * 9 / 16;

	private ImageIcon largohuniIcon = new ImageIcon("res/largohuni1.png");
	private ImageIcon profesorIcon = new ImageIcon("res/profesor.png");
	private ImageIcon studentIcon = new ImageIcon("res/student.png");
	private ImageIcon lendetIcon = new ImageIcon("res/lendet.jpg");
	private ImageIcon notatIcon = new ImageIcon("res/rezultatet.jpg");

	private JButton studentetBt;
	private JButton profesoretBt;
	private JButton largohuniBt;

	public MainMenuAdministrator(ControllerAdministratorMenu cAM,Administrator admin) {
		setLayout(null);
		setMinimumSize(new Dimension(GJERESIA, GJATESIA));
		setMaximumSize(new Dimension(GJERESIA, GJATESIA));
		setPreferredSize(new Dimension(GJERESIA, GJATESIA));

		largohuniBt = new JButton(largohuniIcon);
		largohuniBt.setFocusable(false);
		largohuniBt.setBounds(GJERESIA - 100, 25, 75, 50);
		largohuniBt.setBackground(new Color(220, 20, 60));
		largohuniBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cAM.shfaqLoginInterFace();
			}
		});
		add(largohuniBt);

		profesoretBt = new JButton("Profesorët");
		profesoretBt.setFocusable(false);
		profesoretBt.setFont(new Font("Helvetica", Font.BOLD, 18));
		profesoretBt.setIcon(profesorIcon);
		profesoretBt.setIconTextGap(30);
		profesoretBt.setBounds((GJERESIA - 300 - 250) / 2, (GJATESIA - 100) / 2, 250, 100);
		profesoretBt.setBackground(new Color(128, 255, 0));
		profesoretBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cAM.shfaqProfesoret();
			}
		});
		add(profesoretBt);

		studentetBt = new JButton("Studentët");
		studentetBt.setFocusable(false);
		studentetBt.setFont(new Font("Helvetica", Font.BOLD, 18));
		studentetBt.setIcon(studentIcon);
		studentetBt.setIconTextGap(30);
		studentetBt.setBounds((GJERESIA + 300 - 250) / 2, (GJATESIA - 100) / 2, 250, 100);
		studentetBt.setBackground(new Color(252, 255, 79));
		studentetBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cAM.shfaqKerko();
			}
		});
		add(studentetBt);


	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, GJERESIA, GJATESIA);
	}

	
}
