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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import databases.Fakulltetet;
import databases.Lenda;

public class Provimet extends JPanel {
	private static final int GJERESIA = 1200;
	private static final int GJATESIA = GJERESIA * 9 / 16;

	private ImageIcon prapaIcon = new ImageIcon("res/prapa.png");

	private JLabel semestriLb;

	public JButton prapaBt;
	
	private JTable listaTb;
	private JTable zgjedhurTb;


	private JComboBox<String> semestriCb;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private JScrollPane listaSp;
	private JScrollPane zgjedhurSp;
	
	ArrayList<Lenda>  paraqitur;
	ArrayList<Lenda> paparaqitur;
	
	String[] columnName = { "Lënda", "Profesori", "ECTS", "Paraqit"};

	public Provimet(ControllerStudentMenu cSM) {
		setLayout(null);
		setMinimumSize(new Dimension(GJERESIA, GJATESIA));
		setMaximumSize(new Dimension(GJERESIA, GJATESIA));
		setPreferredSize(new Dimension(GJERESIA, GJATESIA));

		semestriLb = new JLabel("Semestri");
		semestriLb.setBounds((GJERESIA - 100) / 2, 50, 100, 30);
		add(semestriLb);

		String[] b = { "Zgjedh", "Semestri I", "Semestri II" };
		semestriCb = new JComboBox<String>(b);
		semestriCb.setBounds((GJERESIA-200) / 2, 80, 200, 35);
		add(semestriCb);
		semestriCb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				paraqitur=new ArrayList<Lenda>();
				paparaqitur= new ArrayList<Lenda>();
				resetTable(model1);
				resetTable(model2);
				if(semestriCb.getSelectedIndex()!=0) {
					for (int i = 0; i < cSM.student.getlendet().size(); i++) {
						if (cSM.student.getlendet().get(i).getParaqitLenden()) { 
						String[] s = new String[columnName.length];
						for (int j = 0; j < columnName.length; j++) {
							s[j] = cSM.student.getlendet().get(i).getVlera(j + 1,cSM.student.getlendet().get(i).getProfesoret().indexOf(cSM.student.getlendet().get(i).getProfesori()));
						}

						
							if(cSM.student.getlendet().get(i).getParaqitProvimin()) {
							s[3]="Fshi";
							model2.addRow(s);
							paraqitur.add(cSM.student.getlendet().get(i));
						} else {
							s[3]="Paraqit";
							model1.addRow(s);
							paparaqitur.add(cSM.student.getlendet().get(i));
						}
					}}
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
						if (zgjedhurTb.getSelectedColumn() == 3) {
					
							model1.addRow(getValue(model2, i));
							model1.setValueAt("Paraqit", model1.getRowCount() - 1, 3);
							paraqitur.get(i).setParaqitProvimin(false);
							
							paparaqitur.add(paraqitur.get(i));
							paraqitur.remove(i);
							model2.removeRow(i);
							Fakulltetet.setData("0", cSM.student.getPerdoruesiID(), cSM.student.getlendet().indexOf(paparaqitur.get(paparaqitur.size()-1))+1,1, cSM.student.getFakulteti()+"/"+ cSM.student.getDepartamenti()+"/"+ cSM.student.getNiveliStudimeve()+"/"+ cSM.student.getViti()+"L" );

						}
				}

			}

		});
		listaTb.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					int i = listaTb.getSelectedRow();
					if (i != -1)
						if (listaTb.getSelectedColumn() == 3) {
							model2.addRow(getValue(model1, i));
							model2.setValueAt("Fshi", model2.getRowCount() - 1, 3);
							paparaqitur.get(i).setParaqitProvimin(true);
							paraqitur.add(paparaqitur.get(i));
							paparaqitur.remove(i);
							model1.removeRow(i);
							Fakulltetet.setData("1", cSM.student.getPerdoruesiID(), cSM.student.getlendet().indexOf(paraqitur.get(paraqitur.size()-1))+1,1, cSM.student.getFakulteti()+"/"+ cSM.student.getDepartamenti()+"/"+ cSM.student.getNiveliStudimeve()+"/"+ cSM.student.getViti()+"L" );
							Fakulltetet.setData("0", cSM.student.getPerdoruesiID(), cSM.student.getlendet().indexOf(paraqitur.get(paraqitur.size()-1))+1,2, cSM.student.getFakulteti()+"/"+ cSM.student.getDepartamenti()+"/"+ cSM.student.getNiveliStudimeve()+"/"+ cSM.student.getViti()+"L" );
							Fakulltetet.setData("0", cSM.student.getPerdoruesiID(), cSM.student.getlendet().indexOf(paraqitur.get(paraqitur.size()-1))+1,3, cSM.student.getFakulteti()+"/"+ cSM.student.getDepartamenti()+"/"+ cSM.student.getNiveliStudimeve()+"/"+ cSM.student.getViti()+"L" );

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
		String[] a = new String[4];
		for (int i = 0; i < 4; i++) {
			a[i] = (String) model.getValueAt(num, i);
		}
		return a;
	}
	public void resetTable(DefaultTableModel model) {
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}}
}
