package databases;

import main.Controller;
import mainMenuAdministratori.ControllerAdministratorMenu;

public class Administrator extends Perdoruesi {
	private int administratorID;

	public Administrator(String emri, String mbiemri, String email, String fjalkalimi) {
		super( emri, mbiemri, email, fjalkalimi);
	}

	public Administrator() {
		super();

	}
	public  void setVlerat(int i, String s) {}
	@Override
	public void inicializo(Controller controller) {
		new ControllerAdministratorMenu(this,controller);

	}


	public int getAdministratorID() {
		return administratorID;
	}

}
