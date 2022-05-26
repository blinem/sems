package mainMenuProfesori;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import dataFiles.ProfesorFile;
import dataFiles.StudentFile;
import databases.Profesor;

public class NdryshoFjalkalimin extends JFrame {

	final int GJERESIA = 500;
	final int GJATESIA = GJERESIA * 3 / 4;

	JPanel paneli;

	JLabel fjalkalimiVjeterLb;
	JLabel fjalkalimiRiLb;
	JLabel konfirmimiFjalkalimiLb;

	JPasswordField fjalkalimiVjeterPf;
	JPasswordField fjalkalimiRiPf;
	JPasswordField konfirmimiFjalkalimiPf;

	JButton konfirmoBt;

	public NdryshoFjalkalimin(Profesor prof) {

		paneli = new JPanel();
		paneli.setMinimumSize(new Dimension(GJERESIA, GJATESIA));
		paneli.setMaximumSize(new Dimension(GJERESIA, GJATESIA));
		paneli.setPreferredSize(new Dimension(GJERESIA, GJATESIA));

		fjalkalimiVjeterLb = new JLabel("Fjalëkalimi aktual:");
		fjalkalimiVjeterLb.setBounds((GJERESIA - 350) / 2, (GJATESIA - 200) / 2, 150, 50);
		add(fjalkalimiVjeterLb);

		fjalkalimiRiLb = new JLabel("Fjalëkalimi i ri:");
		fjalkalimiRiLb.setBounds((GJERESIA - 350) / 2, (GJATESIA - 50) / 2, 150, 50);
		add(fjalkalimiRiLb);

		konfirmimiFjalkalimiLb = new JLabel("Konfirmoni fjalëkalimin:");
		konfirmimiFjalkalimiLb.setBounds((GJERESIA - 350) / 2, (GJATESIA + 100) / 2, 150, 50);
		add(konfirmimiFjalkalimiLb);

		fjalkalimiVjeterPf = new JPasswordField();
		fjalkalimiVjeterPf.setBounds((GJERESIA - 50) / 2, (GJATESIA - 200) / 2, 200, 50);
		add(fjalkalimiVjeterPf);

		fjalkalimiRiPf = new JPasswordField();
		fjalkalimiRiPf.setBounds((GJERESIA - 50) / 2, (GJATESIA - 50) / 2, 200, 50);
		add(fjalkalimiRiPf);

		konfirmimiFjalkalimiPf = new JPasswordField();
		konfirmimiFjalkalimiPf.setBounds((GJERESIA - 50) / 2, (GJATESIA + 100) / 2, 200, 50);
		add(konfirmimiFjalkalimiPf);

		konfirmoBt = new JButton("Konfirmo");
		konfirmoBt.setBounds((GJERESIA + 50) / 2, (GJATESIA + 250) / 2, 100, 40);
		add(konfirmoBt);
		konfirmoBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!fjalkalimiVjeterPf.getText().equals(prof.getFjalkalimi())) {
					JOptionPane.showMessageDialog(null, "Fjalkalimi i Vjeter eshte i futur gabim");
				}else if(fjalkalimiRiPf.getText().length()<6){
					JOptionPane.showMessageDialog(null, "Fjalkalimi duhet të jetë më i gjatë se 6 shkronja");
				}else if(!fjalkalimiRiPf.getText().equals(konfirmimiFjalkalimiPf.getText())) {
					JOptionPane.showMessageDialog(null, "Fjalkalimi i ri dhe Konfirmimi fjalkalimit te ri nuk përputhen");
				}else if(fjalkalimiRiPf.getText().equals(fjalkalimiVjeterPf.getText())) {
					JOptionPane.showMessageDialog(null, "Fjalkalimi i ri nuk mund te jete sikur fjalkalimi i vjeter");
				} 
				else{
				ProfesorFile.nderroFjalkalimin(fjalkalimiRiPf.getText(), prof);
				dispose();
				}
			}
		});

		getContentPane().add(paneli);
		setResizable(false);

		pack();
		setSize(GJERESIA + 15, GJATESIA + 38);
		setTitle("SEMS");
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
