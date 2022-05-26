package mainMenuProfesori;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import databases.Profesor;
import mainMenuProfesori.ControllerProfesorMenu;
import mainMenuProfesori.NdryshoFjalkalimin;

public class Llogaria extends JPanel {
	private static final int GJERESIA = 1200;
	private static final int GJATESIA = GJERESIA * 9 / 16;

	private ImageIcon prapaIcon = new ImageIcon("res/prapa.png");

	public JLabel idLb;
	public JLabel emriLb;
	public JLabel mbiemriLb;
	public JLabel emailLb;
	public JLabel fjalkalimiLb;

	public JTextField idTf;
	public JTextField emriTf;
	public JTextField mbiemriTf;
	public JTextField emailTf;

	public JButton fjalkalimiBt;
	public JButton prapaBt;

	public Llogaria(ControllerProfesorMenu cPM) {
		setLayout(null);
		setMinimumSize(new Dimension(GJERESIA, GJATESIA));
		setMaximumSize(new Dimension(GJERESIA, GJATESIA));
		setPreferredSize(new Dimension(GJERESIA, GJATESIA));

		idLb = new JLabel("ID:");
		idLb.setBounds((GJERESIA - 350) / 2, (GJATESIA - 350) / 2, 100, 50);
		add(idLb);

		emriLb = new JLabel("Emri:");
		emriLb.setBounds((GJERESIA - 350) / 2, (GJATESIA - 200) / 2, 100, 50);
		add(emriLb);

		mbiemriLb = new JLabel("Mbiemri:");
		mbiemriLb.setBounds((GJERESIA - 350) / 2, (GJATESIA - 50) / 2, 100, 50);
		add(mbiemriLb);

		emailLb = new JLabel("Email:");
		emailLb.setBounds((GJERESIA - 350) / 2, (GJATESIA + 100) / 2, 100, 50);
		add(emailLb);

		fjalkalimiLb = new JLabel("Ndrysho Fjalkalimi");
		fjalkalimiLb.setBounds((GJERESIA - 350) / 2, (GJATESIA + 250) / 2, 200, 40);
		add(fjalkalimiLb);

		idTf = new JTextField("" + cPM.prof.getPerdoruesiID());
		idTf.setEditable(false);
		idTf.setBounds((GJERESIA - 50) / 2, (GJATESIA - 350) / 2, 200, 50);
		add(idTf);

		emriTf = new JTextField(cPM.prof.getEmri());
		emriTf.setEditable(false);
		emriTf.setBounds((GJERESIA - 50) / 2, (GJATESIA - 200) / 2, 200, 50);
		add(emriTf);

		mbiemriTf = new JTextField(cPM.prof.getMbiemri());
		mbiemriTf.setEditable(false);
		mbiemriTf.setBounds((GJERESIA - 50) / 2, (GJATESIA - 50) / 2, 200, 50);
		add(mbiemriTf);

		emailTf = new JTextField(cPM.prof.getEmail());
		emailTf.setEditable(false);
		emailTf.setBounds((GJERESIA - 50) / 2, (GJATESIA + 100) / 2, 200, 50);
		add(emailTf);

		fjalkalimiBt = new JButton("Ndrysho");
		fjalkalimiBt.setBounds((GJERESIA + 50) / 2, (GJATESIA + 250) / 2, 100, 40);
		add(fjalkalimiBt);
		fjalkalimiBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NdryshoFjalkalimin(cPM.prof);
			}
		});
		prapaBt = new JButton(prapaIcon);
		prapaBt.setFocusable(false);
		prapaBt.setBounds(30, 30, 50, 35);
		prapaBt.setBackground(new Color(48, 48, 48));
		add(prapaBt);
		prapaBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cPM.shfaqProfesorMenu();
			}
		});
	}

	public void paintComponent(Graphics g) {

		g.setColor(Color.white);
		g.fillRect(0, 0, GJERESIA, GJATESIA);
		g.setColor(new Color(214, 214, 214));
		g.fillRect((GJERESIA - 500) / 2, (GJATESIA - 500) / 2, 500, 500);
		g.setColor(Color.black);
		g.drawRect((GJERESIA - 500) / 2, (GJATESIA - 500) / 2, 500, 500);

	}
}
