package mainMenuAdministratori;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import dataFiles.ProfesorFile;
import dataFiles.StudentFile;
import databases.Fakulltetet;
import databases.Profesor;

public class Profesoret extends JPanel {
	private static final int GJERESIA = 1200;
	private static final int GJATESIA = GJERESIA * 9 / 16;

	private ImageIcon prapaIcon = new ImageIcon("res/prapa.png");

	private JTextField emriTf;
	private JTextField mbiemriTf;
	private JTextField emailTf;
	private JTextField perdoruesiIDTf;
	private JTextField fjalkalimiTf;
	private JTextField nrPersonalTf;
	private JTextField titulliTf;
	private JTextField gjiniaTf;
	private JTextField shtetiTf;
	private JTextField komunaTf;
	private JTextField vendlindjaTf;
	private JTextField kombesiaTf;
	private JTextField adresaTf;
	private JTextField nrTelefoniTf;
	private JComboBox<String> fakultetiCb;


	DefaultTableModel model;

	private JLabel emriLb;
	private JLabel mbiemriLb;
	private JLabel emailLb;
	private JLabel perdoruesiIDLb;
	private JLabel fjalkalimiLb;
	private JLabel nrPersonalLb;
	private JLabel titulliLb;
	private JLabel gjiniaLb;
	private JLabel shtetiLb;
	private JLabel komunaLb;
	private JLabel vendlindjaLb;
	private JLabel kombesiaLb;
	private JLabel adresaLb;
	private JLabel nrTelefoniLb;
	private JLabel fakultetiLb;

	private JButton ruaj;
	private JButton fshij;
	private JButton shto;
	private JButton pastro;
	private JButton prapaBt;
	private JButton kerko;

	private JScrollPane kerkoSp;
	private JTable lista;

	public Profesoret(ControllerAdministratorMenu cAM) {
		setLayout(null);
		setMinimumSize(new Dimension(GJERESIA, GJATESIA));
		setMaximumSize(new Dimension(GJERESIA, GJATESIA));
		setPreferredSize(new Dimension(GJERESIA, GJATESIA));

		setLabels();
		setJTextFields();
		setJButtons();
		setJList();
		setJComboBox();
		prapaBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cAM.shfaqMainMenuAdministrator();
			}
		});
	}

	private void setJButtons() {
		ruaj = new JButton("Ruaj");
		fshij = new JButton("Fshije");
		shto = new JButton("Shto");
		pastro = new JButton("Pastro");
		kerko = new JButton("Kerko");
		ruaj.setBounds(370, 200, 100, 50);
		fshij.setBounds(370, 270, 100, 50);
		shto.setBounds(370, 340, 100, 50);
		pastro.setBounds(370, 410, 100, 50);
		kerko.setBounds(370, 480, 100, 50);
		add(ruaj);
		add(fshij);
		add(shto);
		add(pastro);
		add(kerko);
		pastro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				perdoruesiIDTf.setText("");
				fjalkalimiTf.setText("");
				emriTf.setText("");
				mbiemriTf.setText("");
				emailTf.setText("");
				nrPersonalTf.setText("");
				titulliTf.setText("");
				gjiniaTf.setText("");
				shtetiTf.setText("");
				komunaTf.setText("");
				vendlindjaTf.setText("");
				kombesiaTf.setText("");
				adresaTf.setText("");
				nrTelefoniTf.setText("");
				lista.clearSelection();
			}
		});
		shto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (lista.getRowCount() + 1) + "," + fjalkalimiTf.getText() + "," + emriTf.getText() + ","
						+ mbiemriTf.getText() + "," + emailTf.getText() + "," + nrPersonalTf.getText() + ","
						+ titulliTf.getText() + "," + gjiniaTf.getText() + "," + shtetiTf.getText() + ","
						+ komunaTf.getText() + "," + vendlindjaTf.getText() + "," + kombesiaTf.getText() + ","
						+ adresaTf.getText() + "," + nrTelefoniTf.getText() + ",";
				Fakulltetet.addData(s, fakultetiCb.getSelectedItem() + "/profesor");
				ProfesorFile.profesor.add(new Profesor(s));
				String st[] = new String[ProfesorFile.colums.size()];
				for (int j = 0; j < ProfesorFile.colums.size(); j++) {
					st[j] = ProfesorFile.profesor.get(ProfesorFile.profesor.size() - 1).getVlerat(j + 1);
				}
				model.addRow(st);

				lista.repaint();
			}

		});
		ruaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = lista.getSelectedRow();
				if (i != -1) {
					String s = perdoruesiIDTf.getText() + "," + fjalkalimiTf.getText() + "," + emriTf.getText() + ","
							+ mbiemriTf.getText() + "," + emailTf.getText() + "," + nrPersonalTf.getText() + ","
							+ titulliTf.getText() + "," + gjiniaTf.getText() + "," + shtetiTf.getText() + ","
							+ komunaTf.getText() + "," + vendlindjaTf.getText() + "," + kombesiaTf.getText() + ","
							+ adresaTf.getText() + "," + nrTelefoniTf.getText() + ",";
					Fakulltetet.setData(s, i + 1, fakultetiCb.getSelectedItem() + "/profesor");
					ProfesorFile.arrangeProfesorData(s, ProfesorFile.profesor.get(i));
					for (int j = 0; j < ProfesorFile.colums.size(); j++) {
						lista.setValueAt(Fakulltetet.celia(s, j), i, j);
					}

				}
			}

		});
		fshij.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = lista.getSelectedRow();
				if (i != -1) {
					if (i != lista.getRowCount() - 1) {
						String s = "Profesori i fshirë" + "," + "Profesori i fshirë" + "," + "Profesori i fshirë" + ","
								+ "Profesori i fshirë" + "," + "Profesori i fshirë" + "," + "Profesori i fshirë" + ","
								+ "Profesori i fshirë" + "," + "Profesori i fshirë" + "," + "Profesori i fshirë" + ","
								+ "Profesori i fshirë" + "," + "Profesori i fshirë" + "," + "Profesori i fshirë" + ","
								+ "Profesori i fshirë" + "," + "Profesori i fshirë" + ",";
						Fakulltetet.setData(s, i + 1, fakultetiCb.getSelectedItem() + "/profesor");
						ProfesorFile.arrangeProfesorData(s, ProfesorFile.profesor.get(i));
						for (int j = 0; j < ProfesorFile.colums.size(); j++) 
							lista.setValueAt(Fakulltetet.celia(s, j), i, j);
						

					}else {
						Fakulltetet.deleteLastRow(fakultetiCb.getSelectedItem() + "/profesor");
						ProfesorFile.profesor.remove(ProfesorFile.profesor.size()-1);
						model.removeRow(lista.getRowCount()-1);
						perdoruesiIDTf.setText("");
						fjalkalimiTf.setText("");
						emriTf.setText("");
						mbiemriTf.setText("");
						emailTf.setText("");
						nrPersonalTf.setText("");
						titulliTf.setText("");
						gjiniaTf.setText("");
						shtetiTf.setText("");
						komunaTf.setText("");
						vendlindjaTf.setText("");
						kombesiaTf.setText("");
						adresaTf.setText("");
						nrTelefoniTf.setText("");
						lista.clearSelection();
				
					}
				}
			}

		});
		kerko.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				resetRow(model);
				if (fakultetiCb.getSelectedIndex() != 0) {
					ProfesorFile.set(fakultetiCb.getSelectedItem() + "/profesor");
					if (model.getColumnCount() == 0)
						for (int i = 0; i < ProfesorFile.colums.size(); i++)
							model.addColumn(ProfesorFile.colums.get(i));
					String s[];
					for (int i = 0; i < ProfesorFile.profesor.size(); i++) {
						s = new String[ProfesorFile.colums.size()];
						for (int j = 0; j < ProfesorFile.colums.size(); j++) {
							s[j] = ProfesorFile.profesor.get(i).getVlerat(j + 1);
						}
						model.addRow(s);
					}
				}
			}

		});
		prapaBt = new JButton(prapaIcon);
		prapaBt.setFocusable(false);
		prapaBt.setBounds(30, 30, 50, 35);
		prapaBt.setBackground(new Color(48, 48, 48));
		add(prapaBt);

	}

	private void setJList() {

		model = new DefaultTableModel();
		lista = new JTable(model);

		lista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				if (lista.getSelectedRow() != -1) {
					perdoruesiIDTf.setText("" + ProfesorFile.profesor.get(lista.getSelectedRow()).getPerdoruesiID());
					fjalkalimiTf.setText(ProfesorFile.profesor.get(lista.getSelectedRow()).getFjalkalimi());
					emriTf.setText(ProfesorFile.profesor.get(lista.getSelectedRow()).getEmri());
					mbiemriTf.setText(ProfesorFile.profesor.get(lista.getSelectedRow()).getMbiemri());
					emailTf.setText(ProfesorFile.profesor.get(lista.getSelectedRow()).getEmail());
					nrPersonalTf.setText(ProfesorFile.profesor.get(lista.getSelectedRow()).getNrPersonal());
					titulliTf.setText(ProfesorFile.profesor.get(lista.getSelectedRow()).getTitulli());
					gjiniaTf.setText(ProfesorFile.profesor.get(lista.getSelectedRow()).getGjinia());
					shtetiTf.setText(ProfesorFile.profesor.get(lista.getSelectedRow()).getShteti());
					komunaTf.setText(ProfesorFile.profesor.get(lista.getSelectedRow()).getKomuna());
					vendlindjaTf.setText(ProfesorFile.profesor.get(lista.getSelectedRow()).getVendlindja());
					kombesiaTf.setText(ProfesorFile.profesor.get(lista.getSelectedRow()).getKombesia());
					adresaTf.setText(ProfesorFile.profesor.get(lista.getSelectedRow()).getAdresa());
					nrTelefoniTf.setText(ProfesorFile.profesor.get(lista.getSelectedRow()).getNrTelefoni());
				}
			}
		});
		kerkoSp = new JScrollPane(lista, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		kerkoSp.setBounds((GJERESIA + 100) / 2, (GJATESIA - 400) / 2, 500, 400);
		add(kerkoSp);
	}

	private void setLabels() {
		perdoruesiIDLb = new JLabel("ID Perdoruesi:");
		fjalkalimiLb = new JLabel("Fjalkalimi");
		nrPersonalLb = new JLabel("Numri Personal");
		emriLb = new JLabel("Emri:");
		titulliLb = new JLabel("Emri i Prindit:");
		mbiemriLb = new JLabel("Mbiemri:");
		gjiniaLb = new JLabel("Gjinia:");
		shtetiLb = new JLabel("Shteti:");
		komunaLb = new JLabel("Komuna:");
		vendlindjaLb = new JLabel("Vendlindja:");
		kombesiaLb = new JLabel("Kombesia:");
		adresaLb = new JLabel("Adresa:");
		nrTelefoniLb = new JLabel("Tel:");
		emailLb = new JLabel("Email");
		fakultetiLb = new JLabel("Fakullteti:");
	

		perdoruesiIDLb.setBounds(350, 50, 100, 30);
		fjalkalimiLb.setBounds(350, 80, 100, 30);
		nrPersonalLb.setBounds(50, 50, 100, 30);
		emriLb.setBounds(50, 80, 100, 30);
		titulliLb.setBounds(50, 110, 100, 30);
		mbiemriLb.setBounds(50, 140, 100, 30);
		gjiniaLb.setBounds(50, 170, 100, 30);
		shtetiLb.setBounds(50, 200, 100, 30);
		komunaLb.setBounds(50, 230, 100, 30);
		vendlindjaLb.setBounds(50, 260, 100, 30);
		kombesiaLb.setBounds(50, 290, 100, 30);
		adresaLb.setBounds(50, 320, 100, 30);
		nrTelefoniLb.setBounds(50, 350, 100, 30);
		emailLb.setBounds(50, 380, 100, 30);
		fakultetiLb.setBounds(50, 510, 100, 30);


		add(perdoruesiIDLb);
		add(fjalkalimiLb);
		add(nrPersonalLb);
		add(emriLb);
		add(titulliLb);
		add(mbiemriLb);
		add(gjiniaLb);
		add(shtetiLb);
		add(komunaLb);
		add(vendlindjaLb);
		add(kombesiaLb);
		add(adresaLb);
		add(nrTelefoniLb);
		add(emailLb);
		add(fakultetiLb);

	}

	private void setJTextFields() {
		perdoruesiIDTf = new JTextField();
		fjalkalimiTf = new JTextField();
		nrPersonalTf = new JTextField();
		emriTf = new JTextField();
		titulliTf = new JTextField();
		mbiemriTf = new JTextField();
		gjiniaTf = new JTextField();
		shtetiTf = new JTextField();
		komunaTf = new JTextField();
		vendlindjaTf = new JTextField();
		kombesiaTf = new JTextField();
		adresaTf = new JTextField();
		nrTelefoniTf = new JTextField();
		emailTf = new JTextField();

		perdoruesiIDTf.setBounds(450, 50, 150, 30);
		fjalkalimiTf.setBounds(450, 80, 150, 30);
		nrPersonalTf.setBounds(150, 50, 150, 30);
		emriTf.setBounds(150, 80, 150, 30);
		titulliTf.setBounds(150, 110, 150, 30);
		mbiemriTf.setBounds(150, 140, 150, 30);
		gjiniaTf.setBounds(150, 170, 150, 30);
		shtetiTf.setBounds(150, 200, 150, 30);
		komunaTf.setBounds(150, 230, 150, 30);
		vendlindjaTf.setBounds(150, 260, 150, 30);
		kombesiaTf.setBounds(150, 290, 150, 30);
		adresaTf.setBounds(150, 320, 150, 30);
		nrTelefoniTf.setBounds(150, 350, 150, 30);
		emailTf.setBounds(150, 380, 150, 30);

		add(perdoruesiIDTf);
		add(fjalkalimiTf);
		add(nrPersonalTf);
		add(emriTf);
		add(titulliTf);
		add(mbiemriTf);
		add(gjiniaTf);
		add(shtetiTf);
		add(komunaTf);
		add(vendlindjaTf);
		add(kombesiaTf);
		add(adresaTf);
		add(nrTelefoniTf);
		add(emailTf);

		perdoruesiIDTf.setEditable(false);

	}

	public void setJComboBox() {
		fakultetiCb = new JComboBox<String>();
		fakultetiCb.setBounds(150, 510, 150, 30);
		

		add(fakultetiCb);

		String a[] = Fakulltetet.getFakulltetet();
		for (int i = 0; i < a.length; i++)
			if (Fakulltetet.celia(a[i], 1) != null)
				fakultetiCb.addItem(Fakulltetet.celia(a[i], 1));

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.green);
		g.fillRect(0, 0, GJERESIA, GJATESIA);

	}

	public void resetRow(DefaultTableModel model) {
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
	}
}
