package mainMenuStudenti;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;

import databases.Student;

public class MainMenuStudent extends JPanel {
	private static final int GJERESIA = 1200;
	private static final int GJATESIA = GJERESIA * 9 / 16;

	private ImageIcon largohuniIcon = new ImageIcon("res/largohuni1.png");
	private ImageIcon lendetIcon = new ImageIcon("res/lendet.jpg");
	private ImageIcon provimetIcon = new ImageIcon("res/provimet.jpg");
	private ImageIcon transkriptaIcon = new ImageIcon("res/transkripta.png");
	private ImageIcon rezultatetIcon = new ImageIcon("res/rezultatet.jpg");
	private Image backroundIcon = new ImageIcon("res/studbackround.png").getImage();
	private Image logoIcon = new ImageIcon("res/logosmall.png").getImage();

	private JLabel emriLb;
	private JLabel fakultetiLB;
	private JLabel departamentiLB;


	private JButton lendetBt;
	private JButton provimetBt;
	private JButton transkriptaBt;
	private JButton rezultatetBt;
	private JButton largohuniBt;
	private JButton llogariaBt;

	public MainMenuStudent(ControllerStudentMenu cSM, Student stud) {
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
				cSM.shfaqLoginInterFace();
			}
		});
		add(largohuniBt);

		llogariaBt = new JButton("Llogaria");
		llogariaBt.setFocusable(false);
		llogariaBt.setBounds(25, 175, 100, 45);
		llogariaBt.setBackground(new Color(220, 20, 60));
		llogariaBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cSM.shfaqLlogaria();
			}
		});
		add(llogariaBt);

		emriLb = new JLabel(stud.getEmri()+ " "+stud.getMbiemri());
		emriLb.setBounds(30, 110, 100, 100);
		add(emriLb);
		
		fakultetiLB = new JLabel("Fakulteti:  "+stud.getFakulteti());
		fakultetiLB.setBounds(50, 75, 400, 50);
		add(fakultetiLB);
		
		departamentiLB = new JLabel("Departamenti:  "+stud.getDepartamenti());
		departamentiLB.setBounds(GJERESIA-400, 75, 400, 50);
		add(departamentiLB);
		
		lendetBt = new JButton("Lëndët");
		lendetBt.setFocusable(false);
		lendetBt.setFont(new Font("Helvetica", Font.BOLD, 18));
		lendetBt.setIcon(lendetIcon);
		lendetBt.setIconTextGap(30);
		lendetBt.setBounds((GJERESIA - 900 - 250) / 2, (GJATESIA +200) / 2, 250, 100);
		lendetBt.setBackground(new Color(0, 168, 243));
		lendetBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cSM.shfaqLendet();
			}
		});
		add(lendetBt);

		provimetBt = new JButton("Provimet");
		provimetBt.setFocusable(false);
		provimetBt.setFont(new Font("Helvetica", Font.BOLD, 18));
		provimetBt.setIcon(provimetIcon);
		provimetBt.setIconTextGap(30);
		provimetBt.setBounds((GJERESIA - 300 - 250) / 2, (GJATESIA +200) / 2, 250, 100);
		provimetBt.setBackground(new Color(128, 255, 0));
		provimetBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cSM.shfaqProvimet();
			}
		});
		add(provimetBt);
		
		rezultatetBt = new JButton("Rezultatet");
		rezultatetBt.setFocusable(false);
		rezultatetBt.setFont(new Font("Helvetica", Font.BOLD, 18));
		rezultatetBt.setIcon(rezultatetIcon);
		rezultatetBt.setIconTextGap(30);
		rezultatetBt.setBounds((GJERESIA + 300 - 250) / 2, (GJATESIA +200) / 2, 250, 100);
		rezultatetBt.setBackground(new Color(255, 40, 49));
		rezultatetBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cSM.shfaqRezultatet();
			}
		});
		add(rezultatetBt);
		
		transkriptaBt = new JButton("Transkripta");
		transkriptaBt.setFocusable(false);
		transkriptaBt.setFont(new Font("Helvetica", Font.BOLD, 18));
		transkriptaBt.setIcon(transkriptaIcon);
		transkriptaBt.setIconTextGap(30);
		transkriptaBt.setBounds((GJERESIA + 900 - 250) / 2, (GJATESIA +200) / 2, 250, 100);
		transkriptaBt.setBackground(new Color(252, 255, 79));
		transkriptaBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cSM.shfaqTranskripta();
			}
		});
		add(transkriptaBt);
	
	}

	public void paintComponent(Graphics g) {
		g.drawImage(backroundIcon, 0,0,null);
		g.setColor(Color.red);
		g.fillRect(0, 50, GJERESIA, 100);
		g.fillOval((GJERESIA-300)/2, 30, 300, 300);
		g.setColor(Color.white);
		g.fillOval((GJERESIA-270)/2, 45, 270, 270);
		g.drawImage(logoIcon,(GJERESIA-300+15)/2, 45,300,270, null);
		
	}
}
