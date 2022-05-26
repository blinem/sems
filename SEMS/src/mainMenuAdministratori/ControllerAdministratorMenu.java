package mainMenuAdministratori;

import databases.Administrator;
import loginInterface.Korniza;
import main.Controller;

public class ControllerAdministratorMenu {
	Administrator admin;
	
	Kerko kerko;
	
	MainMenuAdministrator mMA;
	
	Profesoret profesoret;
	
	Korniza korniza;
	
	Controller controller;
	public ControllerAdministratorMenu(Administrator admin,Controller controller) {
		this.admin=admin;
		this.controller=controller;
		kerko=new Kerko(this);
		mMA=new MainMenuAdministrator(this,admin);
		profesoret=new Profesoret(this);
		korniza=controller.getKorniza();
		shfaqMainMenuAdministrator();
		
	}
	public void shfaqLoginInterFace() {
		controller.shfaqLoginInterface();
	}
	public void shfaqKerko() {
		korniza.getContentPane().removeAll();
		korniza.getContentPane().add(kerko);
		korniza.pack();
		korniza.repaint();

	}
	public void shfaqProfesoret() {
		korniza.getContentPane().removeAll();
		korniza.getContentPane().add(profesoret);
		korniza.pack();
		korniza.repaint();

	}
	public void shfaqMainMenuAdministrator() {
		korniza.getContentPane().removeAll();
		korniza.getContentPane().add(mMA);
		korniza.pack();
		korniza.repaint();

	}
}
