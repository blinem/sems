package mainMenuProfesori;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

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
import databases.Student;

public class Notat extends JPanel {
	private static final int GJERESIA = 1200;
	private static final int GJATESIA = GJERESIA * 9 / 16;

	private ImageIcon prapaIcon = new ImageIcon("res/prapa.png");

	private JLabel lendaLb;
	private JLabel departamentiLb;
	private JLabel niveliLb;

	public JButton prapaBt;
	public JButton ruaj;
	
	private JTextField idTf;
	private JTextField vitiTf;
	private JTextField emriTf;
	private JTextField mbiemriTf;
	
	private JLabel idLb;
	private JLabel vitiLb;
	private JLabel emriLb;
	private JLabel mbiemriLb;
	private JLabel notaLb;

	private JTable listaTb;
	private JTable zgjedhurTb;

	private JComboBox<String> lendaCb;
	private JComboBox<String> departamentiCb;
	private JComboBox<String> niveliCb;
	private JComboBox<String> vendosNoten;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private JScrollPane listaSp;
	private JScrollPane zgjedhurSp;

	ArrayList<Integer> lendaID;

	ArrayList<Student> paraqitur;
	ArrayList<Student> paparaqitur;

	ControllerProfesorMenu cPM;
	
	private int profIndex;

	public Notat(ControllerProfesorMenu cPM) {
		lendaID = new ArrayList<Integer>();
		this.cPM = cPM;
		setLayout(null);
		setMinimumSize(new Dimension(GJERESIA, GJATESIA));
		setMaximumSize(new Dimension(GJERESIA, GJATESIA));
		setPreferredSize(new Dimension(GJERESIA, GJATESIA));
		
		idLb = new JLabel("Id");
		idLb.setBounds((GJERESIA - 950+40) / 2,(GJATESIA+50)/2, 50, 35);
		add(idLb);
		vitiLb = new JLabel("Viti");
		vitiLb.setBounds((GJERESIA - 800+15) / 2,(GJATESIA+50)/2, 50, 35);
		add(vitiLb);
		emriLb = new JLabel("Emri");
		emriLb.setBounds((GJERESIA - 650+125) / 2,(GJATESIA+50)/2, 150, 35);
		add(emriLb);
		mbiemriLb = new JLabel("Mbiemri");
		mbiemriLb.setBounds((GJERESIA - 250+115) / 2,(GJATESIA+50)/2, 150, 35);
		add(mbiemriLb);
		notaLb = new JLabel("Nota");
		notaLb.setBounds((GJERESIA + 150+100) / 2, (GJATESIA+50)/2, 150, 35);
		add(notaLb);
		idTf = new JTextField();
		idTf.setDisabledTextColor(Color.darkGray);
		idTf.setBackground(Color.lightGray);
		idTf.setEnabled(false);
		idTf.setBounds((GJERESIA - 950) / 2,(GJATESIA+100)/2, 50, 35);
		add(idTf);
		vitiTf = new JTextField();
		vitiTf.setDisabledTextColor(Color.darkGray);
		vitiTf.setBackground(Color.lightGray);
		vitiTf.setEnabled(false);
		vitiTf.setBounds((GJERESIA - 800) / 2,(GJATESIA+100)/2, 35, 35);
		add(vitiTf);
		emriTf = new JTextField();
		emriTf.setDisabledTextColor(Color.darkGray);
		emriTf.setBackground(Color.lightGray);
		emriTf.setEnabled(false);
		emriTf.setBounds((GJERESIA - 650) / 2,(GJATESIA+100)/2, 150, 35);
		add(emriTf);
		mbiemriTf = new JTextField();
		mbiemriTf.setDisabledTextColor(Color.darkGray);
		mbiemriTf.setBackground(Color.lightGray);
		mbiemriTf.setEnabled(false);
		mbiemriTf.setBounds((GJERESIA - 250) / 2,(GJATESIA+100)/2, 150, 35);
		add(mbiemriTf);
		
		vendosNoten = new JComboBox<String>();
		vendosNoten.addItem("Nota");
		vendosNoten.addItem("10");
		vendosNoten.addItem("9");
		vendosNoten.addItem("8");
		vendosNoten.addItem("7");
		vendosNoten.addItem("6");
		vendosNoten.addItem("5");
		vendosNoten.addItem("Abstenim");
		vendosNoten.setBounds((GJERESIA + 150) / 2, (GJATESIA+100)/2, 150, 35);
		add(vendosNoten);

		departamentiLb = new JLabel("Departamenti");
		departamentiLb.setBounds((GJERESIA - 600) / 2, 50, 100, 30);
		add(departamentiLb);

		departamentiCb = new JComboBox<String>();
		departamentiCb.setBounds((GJERESIA - 700) / 2, 80, 200, 35);
		String[] s = Fakulltetet.getDepartamentet(cPM.prof.getFakulteti());
		for (int i = 0; i < s.length; i++)
			if (Fakulltetet.celia(s[i], 1) != null) {
				departamentiCb.addItem((Fakulltetet.celia(s[i], 1)));
			}
		add(departamentiCb);
		departamentiCb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (niveliCb.getSelectedIndex() != 0 && departamentiCb.getSelectedIndex() != 0) {
					String data = Fakulltetet.getData(cPM.prof.getFakulteti() + "/" + departamentiCb.getSelectedItem()
							+ "/" + niveliCb.getSelectedItem() + "/lendetprofesoret");
					int i = 1;
					while (Fakulltetet.celia(data, i) != null) {
						int y = 1;
						String celia = Fakulltetet.celia(data, i);
						while (Fakulltetet.subcelia(celia, y) != null) {
							if (new Integer(Fakulltetet.subcelia(celia, y).substring(2, 5)).intValue() == cPM.prof
									.getPerdoruesiID()) {
								lendaCb.addItem(Fakulltetet.getData(Fakulltetet.subcelia(celia, 0).substring(6, 9), 1,
										cPM.prof.getFakulteti() + "/" + departamentiCb.getSelectedItem() + "/"
												+ niveliCb.getSelectedItem() + "/lendet"));
								lendaID.add(new Integer(Fakulltetet.subcelia(celia, 0).substring(6, 9)).intValue());
								break;
							}
							y++;
						}
						i++;
					}
				}

			}

		});
		ruaj = new JButton("Ruaj");
		ruaj.setFocusable(true);
		ruaj.setBounds((GJERESIA + 550) / 2,(GJATESIA+100)/2, 150, 35);
		ruaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(vendosNoten.getSelectedIndex()!=0) {
					Fakulltetet.setData(""+(11-vendosNoten.getSelectedIndex()),new Integer(idTf.getText()).intValue(),lendaID.get(lendaCb.getSelectedIndex()-1),2,cPM.prof.getFakulteti()+"/"+departamentiCb.getSelectedItem()+"/"+niveliCb.getSelectedItem()+"/"+vitiTf.getText()+"L");
				if(11-vendosNoten.getSelectedIndex()==10) 
					Fakulltetet.setData(""+(11-vendosNoten.getSelectedIndex()),new Integer(idTf.getText()).intValue(),lendaID.get(lendaCb.getSelectedIndex()-1),4,cPM.prof.getFakulteti()+"/"+departamentiCb.getSelectedItem()+"/"+niveliCb.getSelectedItem()+"/"+vitiTf.getText()+"L");
				if(11-vendosNoten.getSelectedIndex()==4||11-vendosNoten.getSelectedIndex()==5)
					Fakulltetet.setData(""+1,new Integer(idTf.getText()).intValue(),lendaID.get(lendaCb.getSelectedIndex()-1),3,cPM.prof.getFakulteti()+"/"+departamentiCb.getSelectedItem()+"/"+niveliCb.getSelectedItem()+"/"+vitiTf.getText()+"L");

					String s[] = new String[4];
					s[0] = (String) model1.getValueAt(listaTb.getSelectedRow(), 0);
					s[1] = (String) model1.getValueAt(listaTb.getSelectedRow(), 1);
					s[2] = (String) model1.getValueAt(listaTb.getSelectedRow(), 2);
					s[3] = (String) model1.getValueAt(listaTb.getSelectedRow(), 3);
					model2.addRow(s);
					model1.removeRow(listaTb.getSelectedRow());
				
					
				}
			}
		});
		add(ruaj);
		
		niveliLb = new JLabel("Niveli");
		niveliLb.setBounds((GJERESIA - 100) / 2, 50, 100, 30);
		add(niveliLb);

		niveliCb = new JComboBox<String>();
		niveliCb.setBounds((GJERESIA - 200) / 2, 80, 200, 35);
		niveliCb.addItem("Niveli");
		niveliCb.addItem("Baçelor");
		niveliCb.addItem("Master");
		niveliCb.addItem("Doktoraturë");
		add(niveliCb);
		niveliCb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (niveliCb.getSelectedIndex() != 0 && departamentiCb.getSelectedIndex() != 0) {
					String data = Fakulltetet.getData(cPM.prof.getFakulteti() + "/" + departamentiCb.getSelectedItem()
							+ "/" + niveliCb.getSelectedItem() + "/lendetprofesoret");
					int i = 1;
					while (Fakulltetet.celia(data, i) != null) {
						int y = 1;
						String celia = Fakulltetet.celia(data, i);
						while (Fakulltetet.subcelia(celia, y) != null) {
							if (new Integer(Fakulltetet.subcelia(celia, y).substring(2, 5)).intValue() == cPM.prof
									.getPerdoruesiID()) {
								lendaCb.addItem(Fakulltetet.getData(Fakulltetet.subcelia(celia, 0).substring(6, 9), 1,
										cPM.prof.getFakulteti() + "/" + departamentiCb.getSelectedItem() + "/"
												+ niveliCb.getSelectedItem() + "/lendet"));
								lendaID.add(new Integer(Fakulltetet.subcelia(celia, 0).substring(6, 9)).intValue());
								break;
							}
							y++;
						}
						i++;
					}
				}
			}

		});
		lendaLb = new JLabel("Lenda");
		lendaLb.setBounds((GJERESIA + 400) / 2, 50, 100, 30);
		add(lendaLb);

		lendaCb = new JComboBox<String>();
		lendaCb.setBounds((GJERESIA + 300) / 2, 80, 200, 35);
		add(lendaCb);
		lendaCb.addItem("Lenda");
		lendaCb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (lendaCb.getSelectedIndex() != 0) {
					for (int i = new GregorianCalendar().getTime().getYear() - 100; i > 0; i--) {
						String viti = "";
						if (i < 10)
							viti = "0" + i;
						else
							viti = "" + i;
						String[] data = Fakulltetet.getColumnData(lendaID.get(lendaCb.getSelectedIndex() - 1),
								cPM.prof.getFakulteti() + "/" + departamentiCb.getSelectedItem() + "/"
										+ niveliCb.getSelectedItem() + "/" + viti + "L");
					
						if (data.length != 0) {
							int k=1;
							while(Fakulltetet.subcelia(data[0], k) != null) {
								if(new Integer(Fakulltetet.subcelia(data[0], k).substring(2, 5)).intValue()==cPM.prof.getPerdoruesiID())
									profIndex=k;
								k++;
							}
							for (int j = 1; j < data.length; j++) {
								if (Fakulltetet.subcelia(data[j], 0) != null
										&& Fakulltetet.subcelia(data[j], 0).equals("" + profIndex)) {
									String info = Fakulltetet.getData("" + j,
											cPM.prof.getFakulteti() + "/" + departamentiCb.getSelectedItem() + "/"
													+ niveliCb.getSelectedItem() + "/" + viti);
									String s[] = new String[4];
									s[0] = Fakulltetet.celia(info, 0);
									s[1] = viti;
									s[2] = Fakulltetet.celia(info, 2);
									s[3] = Fakulltetet.celia(info, 3);
									if (Fakulltetet.subcelia(data[j], 2).equals("0") )
										model1.addRow(s);
									else
										model2.addRow(s);
								}
							}
						}
					}
				}
			}

		});
		model1 = new DefaultTableModel();
		model2 = new DefaultTableModel();
		model1.addColumn("ID");
		model1.addColumn("Viti");
		model1.addColumn("Emri");
		model1.addColumn("Mbiemri");
		model2.addColumn("ID");
		model2.addColumn("Viti");
		model2.addColumn("Emri");
		model2.addColumn("Mbiemri");

		listaTb = new JTable(model1);
		zgjedhurTb = new JTable(model2);

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
					if (i != -1) {
						idTf.setText((String) zgjedhurTb.getValueAt(i, 0));
						vitiTf.setText((String) zgjedhurTb.getValueAt(i, 1));
						emriTf.setText((String) zgjedhurTb.getValueAt(i, 2));
						mbiemriTf.setText((String) zgjedhurTb.getValueAt(i, 3));
						vendosNoten.setSelectedIndex(11-new Integer(Fakulltetet.subcelia(Fakulltetet.getData(""+zgjedhurTb.getValueAt(i, 0),cPM.prof.getPerdoruesiID(),cPM.prof.getFakulteti() + "/" + departamentiCb.getSelectedItem() + "/"
													+ niveliCb.getSelectedItem() + "/" + zgjedhurTb.getValueAt(i, 1)+"L"),2)).intValue());
					}

				}

			}
		});
		listaTb.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					int i = listaTb.getSelectedRow();
					if (i != -1) {
						idTf.setText((String) listaTb.getValueAt(i, 0));
						vitiTf.setText((String) listaTb.getValueAt(i, 1));
						emriTf.setText((String) listaTb.getValueAt(i, 2));
						mbiemriTf.setText((String) listaTb.getValueAt(i, 3));
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
				cPM.shfaqProfesorMenu();
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
		}
	}

}
