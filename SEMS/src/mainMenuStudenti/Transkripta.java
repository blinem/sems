package mainMenuStudenti;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import databases.Lenda;

public class Transkripta extends JPanel {
	private static final int GJERESIA = 1200;
	private static final int GJATESIA = GJERESIA * 9 / 16;

	private ImageIcon prapaIcon = new ImageIcon("res/prapa.png");

	private JTable listaTb;

	private JScrollPane listaSp;
	private DefaultTableModel model;
	private JButton prapaBt;
	
	private JLabel notaMesatare;
	
	private ArrayList<Lenda> lendet;
	
	String[] columnName = { "Lënda", "Profesori", "Nota", "ECTS", "Data e Provimit" };
	
	ControllerStudentMenu cSM;

	public Transkripta(ControllerStudentMenu cSM) {
		this.cSM=cSM;
		lendet =new ArrayList<Lenda>();
		setLayout(null);
		setMinimumSize(new Dimension(GJERESIA, GJATESIA));
		setMaximumSize(new Dimension(GJERESIA, GJATESIA));
		setPreferredSize(new Dimension(GJERESIA, GJATESIA));

		notaMesatare = new JLabel();
		notaMesatare.setBounds(950, 550, 200, 30);
	
		add(notaMesatare);
	
	
		model = new DefaultTableModel();
		for (int i = 0; i < columnName.length; i++)
			model.addColumn(columnName[i]);
		setData();
	
		listaTb = new JTable(model);
		listaTb.setDragEnabled(false);
		listaTb.setDefaultEditor(Object.class, null);
		listaSp = new JScrollPane(listaTb);
		listaSp.setBounds(100, 150, GJERESIA - 200, 400);
		add(listaSp);
		prapaBt = new JButton(prapaIcon);
		prapaBt.setFocusable(false);
		prapaBt.setBounds(30, 30, 50, 35);
		prapaBt.setBackground(new Color(48, 48, 48));
		add(prapaBt);
		prapaBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cSM.shfaqStudentMenu();
			}
		});
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, GJERESIA, GJATESIA);
	}
	public void getMesatarja() {
		double sum=0;
		for(int i=0;i<lendet.size();i++) {
			sum+=lendet.get(i).getNota();		}
		notaMesatare.setText("Nota mesatare:  "+sum/lendet.size());
	}
	public void setData() {
		for (int i = 0; i < cSM.student.getlendet().size(); i++) {
			if (cSM.student.getlendet().get(i).getNota() != 0) {
				String[] s = new String[columnName.length];
				s[0] = cSM.student.getlendet().get(i).getEmriLendes();
				s[1] = cSM.student.getlendet().get(i).getProfesori();
				s[2] = "" + cSM.student.getlendet().get(i).getNota();
				s[3] = "" + cSM.student.getlendet().get(i).getECTS();
				model.addRow(s);
				lendet.add(cSM.student.getlendet().get(i));
				
			}

		}
		getMesatarja();
	}
	
}
