package mainMenuStudenti;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import databases.Fakulltetet;
import databases.Lenda;

public class Rezultatet extends JPanel {
	private static final int GJERESIA = 1200;
	private static final int GJATESIA = GJERESIA * 9 / 16;

	private ImageIcon prapaIcon = new ImageIcon("res/prapa.png");

	private JLabel niveliLb;
	private JLabel fakulltetiLb;
	private JLabel departamentiLb;

	public JButton prapaBt;

	private JTextField niveliTf;
	private JTextField fakulltetiTf;
	private JTextField departamentiTf;

	private JScrollPane listaSp;

	private JTable listaTb;

	ArrayList<Lenda> lendet;

	String[] columnName = { "Lënda", "Profesori", "Data e paraqitjes", "Data e vendosjes se notes", "Nota", "", "" };
	private DefaultTableModel model;
	ControllerStudentMenu cSM;
	public Rezultatet(ControllerStudentMenu cSM) {
		this.cSM=cSM;
		lendet=new ArrayList<Lenda>();
		setLayout(null);
		setMinimumSize(new Dimension(GJERESIA, GJATESIA));
		setMaximumSize(new Dimension(GJERESIA, GJATESIA));
		setPreferredSize(new Dimension(GJERESIA, GJATESIA));

		niveliLb = new JLabel("Niveli");
		niveliLb.setBounds((GJERESIA - 950) / 2, 50, 100, 30);
		add(niveliLb);
		fakulltetiLb = new JLabel("Fakulteti");
		fakulltetiLb.setBounds((GJERESIA - 600) / 2, 50, 100, 30);
		add(fakulltetiLb);
		departamentiLb = new JLabel("Departamenti");
		departamentiLb.setBounds((GJERESIA - 50) / 2, 50, 100, 30);
		add(departamentiLb);

		niveliTf = new JTextField("Bacelor");
		niveliTf.setDisabledTextColor(Color.darkGray);
		niveliTf.setBackground(Color.lightGray);
		niveliTf.setEnabled(false);
		niveliTf.setBounds((GJERESIA - 950) / 2, 80, 150, 35);
		add(niveliTf);
		fakulltetiTf = new JTextField("Fakulteti i Shkencave Matematike Natyrore");
		fakulltetiTf.setDisabledTextColor(Color.darkGray);
		fakulltetiTf.setBackground(Color.lightGray);
		fakulltetiTf.setEnabled(false);
		fakulltetiTf.setBounds((GJERESIA - 600) / 2, 80, 250, 35);
		add(fakulltetiTf);
		departamentiTf = new JTextField("Matematike");
		departamentiTf.setBounds((GJERESIA - 50) / 2, 80, 150, 35);
		departamentiTf.setDisabledTextColor(Color.darkGray);
		departamentiTf.setBackground(Color.lightGray);
		departamentiTf.setEnabled(false);
		add(departamentiTf);
		model = new DefaultTableModel();
		for (int i = 0; i < columnName.length; i++)
			model.addColumn(columnName[i]);

		paraqitData();

		listaTb = new JTable(model);
		listaTb.setDragEnabled(false);
		listaTb.setDefaultEditor(Object.class, null);
		listaSp = new JScrollPane(listaTb);
		listaSp.setBounds(100, 150, GJERESIA - 200, 200);
		add(listaSp);
		listaTb.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					int i = listaTb.getSelectedRow();
					if (i != -1)
						if (!listaTb.getValueAt(i, 5).equals("Nota është refuzuar")&& !listaTb.getValueAt(i, 5).equals("Nota ka kaluar në transkriptë")) {
							
							if(listaTb.getSelectedColumn()==5) {
								listaTb.clearSelection();
								resetTable(model);
								listaTb.clearSelection();
								lendet.get(i).setNota(""+lendet.get(i).getRezultati());
								Fakulltetet.setData(""+lendet.get(i).getRezultati(), cSM.student.getPerdoruesiID(), cSM.student.getlendet().indexOf(lendet.get(i))+1,4, cSM.student.getFakulteti()+"/"+ cSM.student.getDepartamenti()+"/"+ cSM.student.getNiveliStudimeve()+"/"+ cSM.student.getViti()+"L" );
								paraqitData();
								cSM.transkripta.setData();
							}
							else if(listaTb.getSelectedColumn()==6) {
								listaTb.clearSelection();
								resetTable(model);
								listaTb.clearSelection();
								cSM.student.getlendet().get(cSM.student.getlendet().indexOf(lendet.get(i))).setRefuzuar(true);
								cSM.student.getlendet().get(cSM.student.getlendet().indexOf(lendet.get(i))).setParaqitProvimin(false);
								Fakulltetet.setData("1", cSM.student.getPerdoruesiID(), cSM.student.getlendet().indexOf(lendet.get(i))+1,3, cSM.student.getFakulteti()+"/"+ cSM.student.getDepartamenti()+"/"+ cSM.student.getNiveliStudimeve()+"/"+ cSM.student.getViti()+"L" );
								Fakulltetet.setData("0", cSM.student.getPerdoruesiID(), cSM.student.getlendet().indexOf(lendet.get(i))+1,1, cSM.student.getFakulteti()+"/"+ cSM.student.getDepartamenti()+"/"+ cSM.student.getNiveliStudimeve()+"/"+ cSM.student.getViti()+"L" );

								paraqitData();
							}
							
						}
				}

			}

		});
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
	public void resetTable(DefaultTableModel model) {
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
	}
	public void paraqitData() {
		for (int i = 0; i < cSM.student.getlendet().size(); i++) {
			if (cSM.student.getlendet().get(i).getRezultati() != 0) {
				String[] s = new String[columnName.length];
				
				s[0]=cSM.student.getlendet().get(i).getEmriLendes();
				s[1]=cSM.student.getlendet().get(i).getProfesori();
				if (cSM.student.getlendet().get(i).getRezultati() == 4)
					s[4] = "Abstenim";
				else
					s[4] = "" + cSM.student.getlendet().get(i).getRezultati();
				if (cSM.student.getlendet().get(i).getRefuzuar()||cSM.student.getlendet().get(i).getRezultati()==4||cSM.student.getlendet().get(i).getRezultati()==5) {
					s[5] = "Nota është refuzuar";
					s[6] = "Nota është refuzuar";
				} else if (cSM.student.getlendet().get(i).getNota() != 0||cSM.student.getlendet().get(i).getRezultati()==10) {
					s[5] = "Nota ka kaluar në transkriptë";
					s[6] = "Nota ka kaluar në transkriptë";
				}
					else {
					s[5] = "Prano noten";
					s[6] = "Refuzo noten";
				}
				model.addRow(s);
				lendet.add(cSM.student.getlendet().get(i));
			}
		}
	}
}
