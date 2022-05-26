package mainMenuProfesori;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import databases.Profesor;


public class MainMenuProffesor extends JPanel {
	private static final int GJERESIA = 1200;
	private static final int GJATESIA = GJERESIA * 9 / 16;
	
	private ImageIcon largohuniIcon = new ImageIcon("res/largohuni1.png");
	private ImageIcon notatIcon = new ImageIcon("res/lendet.jpg");
	private Image logoIcon = new ImageIcon("res/logosmall.png").getImage();


	private JLabel emriLb;
	private JLabel fakultetiLB;

	private JButton notatBt;
	private JButton largohuniBt;
	private JButton llogariaBt;
	
	public MainMenuProffesor(ControllerProfesorMenu cPM) {		
	setLayout(null);
	setMinimumSize(new Dimension(GJERESIA, GJATESIA));
	setMaximumSize(new Dimension(GJERESIA, GJATESIA));
	setPreferredSize(new Dimension(GJERESIA, GJATESIA));

	largohuniBt = new JButton(largohuniIcon);
	largohuniBt.setFocusable(true);
	largohuniBt.setBounds(GJERESIA - 100, 175, 75, 45);
	largohuniBt.setBackground(new Color(220, 20, 60));
	largohuniBt.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			cPM.shfaqLoginInterFace();
		}
	});
	add(largohuniBt);

	llogariaBt = new JButton("Llogaria");
	llogariaBt.setFocusable(false);
	llogariaBt.setBounds(25, 175, 100, 45);
	llogariaBt.setBackground(new Color(220, 20, 60));
	llogariaBt.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		cPM.shfaqLlogaria();
		}
	});
	add(llogariaBt);

	emriLb = new JLabel(cPM.prof.getEmri()+ " "+cPM.prof.getMbiemri());
	emriLb.setBounds(30, 110, 100, 100);
	add(emriLb);
	
	fakultetiLB = new JLabel("Fakulteti:  "+cPM.prof.getFakulteti());
	fakultetiLB.setBounds(50, 75, 400, 50);
	add(fakultetiLB);

	notatBt = new JButton("Notat");
	notatBt.setFocusable(false);
	notatBt.setFont(new Font("Helvetica", Font.BOLD, 18));
	//notatBt.setIcon(notatIcon);
	notatBt.setIconTextGap(30);
	notatBt.setIcon(notatIcon);
	notatBt.setBounds((GJERESIA - 250) / 2, (GJATESIA +200) / 2, 250, 100);
	notatBt.setBackground(new Color(0, 168, 243));
	notatBt.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			cPM.shfaqNotat();
		}
	});
	add(notatBt);

	
}
	

	public void paintComponent(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 50, GJERESIA, 100);
		g.fillOval((GJERESIA-300)/2, 30, 300, 300);
		g.setColor(Color.white);
		g.fillOval((GJERESIA-270)/2, 45, 270, 270);
		g.drawImage(logoIcon,(GJERESIA-300+15)/2, 45,300,270, null);
	}
}
