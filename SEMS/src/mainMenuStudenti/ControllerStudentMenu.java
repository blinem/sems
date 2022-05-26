package mainMenuStudenti;

import databases.Student;
import loginInterface.Korniza;
import main.Controller;

public class ControllerStudentMenu {

	Student student;

	Llogaria llogaria;

	MainMenuStudent studentMenu;

	Korniza korniza;

	Provimet provimet;

	Rezultatet rezultatet;

	Lendet lendet;
	
	Controller controller;
	
	Transkripta transkripta;
	
	

	public ControllerStudentMenu(Student student, Controller controller) {
		this.student = student;
		this.controller=controller;
		llogaria = new Llogaria(this, student);
		studentMenu = new MainMenuStudent(this, student);
		provimet = new Provimet(this);
		rezultatet = new Rezultatet(this);
		lendet = new Lendet(this);
		transkripta = new Transkripta(this);
		this.korniza = controller.getKorniza();
		shfaqStudentMenu();

	}

	public void shfaqStudentMenu() {
		korniza.getContentPane().removeAll();
		korniza.getContentPane().add(studentMenu);
		korniza.pack();
		korniza.repaint();
	}

	public void shfaqLlogaria() {
		korniza.getContentPane().removeAll();
		korniza.getContentPane().add(llogaria);
		korniza.pack();
		korniza.repaint();

	}

	public void shfaqLendet() {
		korniza.getContentPane().removeAll();
		korniza.getContentPane().add(lendet);
		korniza.pack();
		korniza.repaint();

	}

	public void shfaqProvimet() {
		korniza.getContentPane().removeAll();
		korniza.getContentPane().add(provimet);
		korniza.pack();
		korniza.repaint();

	}

	public void shfaqRezultatet() {
		korniza.getContentPane().removeAll();
		korniza.getContentPane().add(rezultatet);
		korniza.pack();
		korniza.repaint();

	}
	
	public void shfaqTranskripta() {
		korniza.getContentPane().removeAll();
		korniza.getContentPane().add(transkripta);
		korniza.pack();
		korniza.repaint();

	}

	public void shfaqLoginInterFace() {
		controller.shfaqLoginInterface();
	}

}
