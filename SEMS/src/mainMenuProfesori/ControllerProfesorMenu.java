package mainMenuProfesori;


import databases.Profesor;
import loginInterface.Korniza;
import main.Controller;

public class ControllerProfesorMenu {
	Profesor prof;
	Controller controller;
	Llogaria llogaria;
	Korniza korniza;
	MainMenuProffesor profesorMenu;
	Notat notat;
	public ControllerProfesorMenu(Profesor prof, Controller controller) {
		this.prof = prof;
		this.controller=controller;
		this.llogaria=new Llogaria(this);
		this.notat=new Notat(this);
		this.profesorMenu=new MainMenuProffesor(this);
		this.korniza = controller.getKorniza();
		shfaqProfesorMenu();

	}
	public void shfaqProfesorMenu() {
		korniza.getContentPane().removeAll();
		korniza.getContentPane().add(profesorMenu);
		korniza.pack();
		korniza.repaint();
	}
	public void shfaqLlogaria() {
		korniza.getContentPane().removeAll();
		korniza.getContentPane().add(llogaria);
		korniza.pack();
		korniza.repaint();
	}
	public void shfaqNotat() {
		korniza.getContentPane().removeAll();
		korniza.getContentPane().add(notat);
		korniza.pack();
		korniza.repaint();
	}
	public void shfaqLoginInterFace() {
		controller.shfaqLoginInterface();
	}
}
