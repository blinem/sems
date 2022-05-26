package main;

import javax.swing.JOptionPane;

import databases.Administrator;
import databases.Fakulltetet;
import databases.Profesor;
import databases.Student;
import loginInterface.Korniza;
import loginInterface.Login;

public class Controller {

	Login login;
	Korniza korniza;

	public Controller() {
		korniza = new Korniza();
		login = new Login(this);
		shfaqLoginInterface();

	}

	public void shfaqLoginInterface() {
		korniza.getContentPane().removeAll();
		korniza.getContentPane().add(login);
		korniza.pack();
		korniza.repaint();
	}

	public void inicializo(String perdoruesi, String fjalkalimi) {
		boolean p = true;
		if (perdoruesi.length() == 12) {
			String fakullteti = Fakulltetet.getData(perdoruesi.substring(2, 4), 1, "fakultetet");
			if (!fakullteti.isEmpty()) {
				String departamenti = Fakulltetet.getData(perdoruesi.substring(4, 6), 1, fakullteti + "/departamentet");
				if (!departamenti.isEmpty()) {

					String data1 = Fakulltetet.getData(perdoruesi.substring(7, 12),
							fakullteti + "/" + departamenti + "/" + Fakulltetet.getNiveli(perdoruesi.substring(6, 7))
									+ "/" + perdoruesi.substring(0, 2));
					String data2 = Fakulltetet.getData(perdoruesi.substring(7, 12),
							fakullteti + "/" + departamenti + "/" + Fakulltetet.getNiveli(perdoruesi.substring(6, 7))
									+ "/" + perdoruesi.substring(0, 2) + "L");
					String lendet = Fakulltetet.getData(fakullteti + "/" + departamenti + "/"
							+ Fakulltetet.getNiveli(perdoruesi.substring(6, 7)) + "/lendetprofesoret");
					if (!data1.isEmpty()) {
						if (fjalkalimi.equals(Fakulltetet.celia(data1, 1))) {
							Student stud = new Student(data1);
							stud.setFakulteti(fakullteti);
							stud.setDepartamenti(departamenti);
							stud.setViti(perdoruesi.substring(0, 2));
							stud.setNiveliStudimeve(Fakulltetet.getNiveli(perdoruesi.substring(6, 7)));
							stud.setFilePath(fakullteti + "/" + departamenti + "/" + stud.getNiveliStudimeve() + "/");
							int i = 1;
							while (Fakulltetet.celia(lendet, i) != null) {
								if ((new Integer(Fakulltetet.celia(lendet, i).substring(4, 6))).intValue() <= 1) {

									stud.addLenda(Fakulltetet.celia(lendet, i));

								}

								if (Fakulltetet.subcelia(Fakulltetet.celia(data2, i), 0) != null
										&& new Integer(Fakulltetet.subcelia(Fakulltetet.celia(data2, i), 0))
												.intValue() != 0) {
									stud.getlendet().get(i - 1)
											.setProfesori(stud.getlendet().get(i - 1).getProfesoret().get(
													new Integer(Fakulltetet.celia(data2, i).substring(0, 1)).intValue()
															- 1));
									stud.getlendet().get(i - 1).setParaqitLenden(true);
									if (new Integer(Fakulltetet.subcelia(Fakulltetet.celia(data2, i), 1))
											.intValue() == 1) {
										stud.getlendet().get(i - 1).setParaqitProvimin(true);
										stud.getlendet().get(i - 1)
												.setRezultati(Fakulltetet.subcelia(Fakulltetet.celia(data2, i), 2));
										stud.getlendet().get(i - 1)
												.setNota(Fakulltetet.subcelia(Fakulltetet.celia(data2, i), 4));
										if (new Integer(Fakulltetet.subcelia(Fakulltetet.celia(data2, i), 3))
												.intValue() == 1) {
											stud.getlendet().get(i - 1).setRefuzuar(true);

										}
									}
								}

								i++;
							}
							stud.inicializo(this);
							p = false;
						}
					}
				}
			}
		} else if (perdoruesi.equals("admin") && fjalkalimi.equals("password")) {
			new Administrator().inicializo(this);
			p = false;
		} else if (perdoruesi.length() == 5) {
			String fakullteti = Fakulltetet.getData(perdoruesi.substring(0, 2), 1, "fakultetet");
			if (fakullteti != null && !fakullteti.isEmpty()) {
				String data1 = Fakulltetet.getData(perdoruesi.substring(2, 5), fakullteti + "/profesor");
				if (data1 != null && !data1.isEmpty()) {
					if (fjalkalimi.equals(Fakulltetet.celia(data1, 1))) {
						Profesor prof = new Profesor(data1);
						prof.setFakulteti(fakullteti);
						prof.inicializo(this);
						p = false;
					}
				}
			}
		}
		if (p) {
			JOptionPane.showMessageDialog(null, "Perdoruesi ose fjalkalimi janë futur gabim ");
		}
		login.resetPerdoruesiText();
		login.resetFjalkalimiText();
	}

	public Korniza getKorniza() {
		return korniza;
	}
}
