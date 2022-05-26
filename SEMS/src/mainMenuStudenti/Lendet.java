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
import databases.Student;


public class Lendet extends JPanel {
	private static final int GJERESIA = 1200;
	private static final int GJATESIA = GJERESIA * 9 / 16;

	private ImageIcon prapaIcon = new ImageIcon("res/prapa.png");

	private JLabel niveliLb;
	private JLabel fakulltetiLb;
	private JLabel departamentiLb;
	private JLabel semestriLb;
	private JLabel llojiLb;

	public JButton prapaBt;

	private JTextField niveliTf;
	private JTextField fakulltetiTf;
	private JTextField departamentiTf;

	private JComboBox<String> semestriCb;
	private JComboBox<String> llojiCb;

	private JScrollPane listaSp;
	private JScrollPane zgjedhurSp;

	private JTable listaTb;
	private JTable zgjedhurTb;
	private DefaultTableModel model1;
	private DefaultTableModel model2;

	String[] columnName = { "Lënda", "Profesori", "ECTS", "Grupi", "Salla", "Koha", "" };
	
	ArrayList<Lenda>  paraqitur;
	ArrayList<Lenda> paparaqitur;

	ControllerStudentMenu cSM;
	public Lendet(ControllerStudentMenu cSM) {
		this.cSM=cSM;
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
		semestriLb = new JLabel("Semestri");
		semestriLb.setBounds((GJERESIA + 350) / 2, 50, 100, 30);
		add(semestriLb);
		llojiLb = new JLabel("Lloji");
		llojiLb.setBounds((GJERESIA + 650) / 2, 50, 100, 30);
		add(llojiLb);

		niveliTf = new JTextField(cSM.student.getNiveliStudimeve());
		niveliTf.setDisabledTextColor(Color.darkGray);
		niveliTf.setBackground(Color.lightGray);
		niveliTf.setEnabled(false);
		niveliTf.setBounds((GJERESIA - 950) / 2, 80, 150, 35);
		add(niveliTf);
		fakulltetiTf = new JTextField(cSM.student.getFakulteti());
		fakulltetiTf.setDisabledTextColor(Color.darkGray);
		fakulltetiTf.setBackground(Color.lightGray);
		fakulltetiTf.setEnabled(false);
		fakulltetiTf.setBounds((GJERESIA - 600) / 2, 80, 250, 35);
		add(fakulltetiTf);
		departamentiTf = new JTextField(cSM.student.getDepartamenti());
		departamentiTf.setBounds((GJERESIA - 50) / 2, 80, 150, 35);
		departamentiTf.setDisabledTextColor(Color.darkGray);
		departamentiTf.setBackground(Color.lightGray);
		departamentiTf.setEnabled(false);
		add(departamentiTf);
		String[] a = { "Zgjedh", "Semestri I", "Semestri II" };
		semestriCb = new JComboBox<String>(a);
		semestriCb.setBounds((GJERESIA + 300) / 2, 80, 150, 35);
		add(semestriCb);
		String[] b = { "Zgjedh", "Obligative", "Zgjedhore" };
		llojiCb = new JComboBox<String>(b);
		llojiCb.setBounds((GJERESIA + 650) / 2, 80, 150, 35);
		add(llojiCb);

		semestriCb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paraqitur=new ArrayList<Lenda>();
				paparaqitur= new ArrayList<Lenda>();
				resetTable(model1);
				resetTable(model2);
				if (llojiCb.getSelectedIndex() != 0 && semestriCb.getSelectedIndex() != 0) {

					paraqitData();
				
				}
			}
		});
		llojiCb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paraqitur=new ArrayList<Lenda>();
				paparaqitur= new ArrayList<Lenda>();
				resetTable(model1);
				resetTable(model2);
				if (llojiCb.getSelectedIndex() != 0 && semestriCb.getSelectedIndex() != 0) {
					paraqitData();
				}
			}
		});

		model1 = new DefaultTableModel();
		model2 = new DefaultTableModel();
		listaTb = new JTable(model1);
		zgjedhurTb = new JTable(model2);

		for (int i = 0; i < columnName.length; i++) {
			model1.addColumn(columnName[i]);
			model2.addColumn(columnName[i]);
		}
		listaTb.setDragEnabled(false);
		listaTb.setDefaultEditor(Object.class, null);
		listaSp = new JScrollPane(listaTb);
		listaSp.setBounds(100, 150, GJERESIA - 200, 200);
		add(listaSp);
		zgjedhurTb.setDragEnabled(false);
		zgjedhurTb.setDefaultEditor(Object.class, null);
		zgjedhurSp = new JScrollPane(zgjedhurTb);
		zgjedhurSp.setBounds(100, 450, GJERESIA - 200, 200);
		add(zgjedhurSp);
		zgjedhurTb.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					int i = zgjedhurTb.getSelectedRow();
					if (i != -1)
						if (zgjedhurTb.getSelectedColumn() == 6) {
							zgjedhurTb.clearSelection();
							paraqitur.get(i).setParaqitLenden(false);
							paraqitur.get(i).setProfesori(null);
							Fakulltetet.setData("0", cSM.student.getPerdoruesiID(), cSM.student.getlendet().indexOf(paraqitur.get(i))+1, cSM.student.getFakulteti()+"/"+ cSM.student.getDepartamenti()+"/"+ cSM.student.getNiveliStudimeve()+"/"+ cSM.student.getViti()+"L" );
							paparaqitur.add(paraqitur.get(i));
							paraqitur.remove(i);
							paraqitData();
						
						}
				}

			}

		});
		listaTb.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					int i = listaTb.getSelectedRow();
					if (i != -1)
						if (listaTb.getSelectedColumn() == 6&&listaTb.getValueAt(i, 6)=="Regjistro") {
							listaTb.clearSelection();
							paparaqitur.get(i).setParaqitLenden(true);
							paparaqitur.get(i).setProfesori((String) model1.getValueAt(i, 1));
							
							Fakulltetet.setData((paparaqitur.get(i).getProfesoret().indexOf(paparaqitur.get(i).getProfesori())+1)+"/0/0/0/0/", cSM.student.getPerdoruesiID(), cSM.student.getlendet().indexOf(paparaqitur.get(i))+1, cSM.student.getFakulteti()+"/"+ cSM.student.getDepartamenti()+"/"+ cSM.student.getNiveliStudimeve()+"/"+ cSM.student.getViti()+"L" );
							paraqitur.add(paparaqitur.get(i));
							paparaqitur.remove(i);
							paraqitData();
							
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

	public String[] getValue(DefaultTableModel model, int num) {
		String[] a = new String[7];
		for (int i = 0; i < 7; i++) {
			a[i] = (String) model.getValueAt(num, i);
		}
		return a;
	}

	public void resetTable(DefaultTableModel model) {
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
	}
	private void paraqitData() {
		paraqitur=new ArrayList<Lenda>();
		paparaqitur= new ArrayList<Lenda>();
		resetTable(model1);
		resetTable(model2);
		for (int i = 0; i < cSM.student.getlendet().size(); i++) {
			if (cSM.student.getlendet().get(i).getSemestri() == semestriCb.getSelectedIndex()
					&& cSM.student.getlendet().get(i).getLloji().equals(llojiCb.getSelectedItem())) {
				for(int j=0;j<cSM.student.getlendet().get(i).getProfesoret().size();j++) {
				String[] s = new String[columnName.length];
				for (int k = 0; k < columnName.length; k++) {
					s[k] = cSM.student.getlendet().get(i).getVlera(k + 1,j);
				}

				if (cSM.student.getlendet().get(i).getProfesori()!=null&&cSM.student.getlendet().get(i).getProfesori().equals(s[1])) {
					s[6] = "Fshi";
					model2.addRow(s);
					paraqitur.add(cSM.student.getlendet().get(i));
				} else {
					if(!cSM.student.getlendet().get(i).getParaqitLenden())
					s[6] = "Regjistro";	
					else {
						s[6] = "";	
					}
					
					model1.addRow(s);
					paparaqitur.add(cSM.student.getlendet().get(i));
				}
				
			}
			}
		}
	}
	
}
