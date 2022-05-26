package databases;

import java.util.ArrayList;

import main.Controller;
import mainMenuStudenti.ControllerStudentMenu;

public class Student extends Perdoruesi {

	private String nrPersonal;
	private String emriPrindit;
	private String gjinia;
	private String shteti;
	private String komuna;
	private String vendlindja;
	private String kombesia;
	private String adresa;
	private String nrTelefoni;

	private String fakulteti;
	private String departamenti;
	private String programi;
	private String niveliStudimeve;
	private String vitiRegjistrimit;

	private ArrayList<Lenda> lendet;

	private String filePath;

	public Student(String s) {
		lendet = new ArrayList<Lenda>();
		int count = 1;
		while (Fakulltetet.celia(s, count - 1) != null) {
			this.setVlerat(count, Fakulltetet.celia(s, count - 1));
			count++;
		}
	}

	public void inicializo(Controller controller) {
		new ControllerStudentMenu(this, controller);
	}

	public String getEmriPrindit() {
		return emriPrindit;
	}

	public String getGjinia() {
		return gjinia;
	}

	public String getShteti() {
		return shteti;
	}

	public String getKomuna() {
		return komuna;
	}

	public String getVendlindja() {
		return vendlindja;
	}

	public String getKombesia() {
		return kombesia;
	}

	public String getAdresa() {
		return adresa;
	}

	public String getNrTelefoni() {
		return nrTelefoni;
	}

	public String getFakulteti() {
		return fakulteti;
	}

	public String getDepartamenti() {
		return departamenti;
	}

	public String getProgrami() {
		return programi;
	}

	public String getNiveliStudimeve() {
		return niveliStudimeve;
	}

	public String getNrPersonal() {
		return nrPersonal;
	}

	public String getViti() {
		return vitiRegjistrimit;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setEmriPrindit(String emriPrindit) {
		this.emriPrindit = emriPrindit;
	}

	public void setGjinia(String gjinia) {
		this.gjinia = gjinia;
	}

	public void setShteti(String shteti) {
		this.shteti = shteti;
	}

	public void setKomuna(String komuna) {
		this.komuna = komuna;
	}

	public void setVendlindja(String vendlindja) {
		this.vendlindja = vendlindja;
	}

	public void setKombesia(String kombesia) {
		this.kombesia = kombesia;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public void setNrTelefoni(String nrTelefoni) {
		this.nrTelefoni = nrTelefoni;
	}

	public void setFakulteti(String fakulteti) {
		this.fakulteti = fakulteti;
	}

	public void setDepartamenti(String departamenti) {
		this.departamenti = departamenti;
	}

	public void setProgrami(String programi) {
		this.programi = programi;
	}

	public void setNiveliStudimeve(String niveliStudimeve) {
		this.niveliStudimeve = niveliStudimeve;
	}

	public void setNrPersonal(String nrPersonal) {
		this.nrPersonal = nrPersonal;
	}

	public void setViti(String viti) {
		vitiRegjistrimit = viti;

	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setVlerat(int i, String s) {
		switch (i) {
		case 1:
			setPerdoruesiID(s);
			break;
		case 2:
			setFjalkalimi(s);
			break;
		case 3:
			setEmri(s);
			break;
		case 4:
			setMbiemri(s);
			break;
		case 5:
			setEmail(s);
			break;
		case 6:
			setNrPersonal(s);
			break;
		case 7:
			setEmriPrindit(s);
			break;
		case 8:
			setGjinia(s);
			break;
		case 9:
			setShteti(s);
			break;
		case 10:
			setKomuna(s);
			break;
		case 11:
			setVendlindja(s);
			break;
		case 12:
			setKombesia(s);
			break;
		case 13:
			setAdresa(s);
			break;
		case 14:
			setNrTelefoni(s);
			break;
		}
	}

	public String getVlerat(int i) {
		switch (i) {
		case 1:
			return "" + getPerdoruesiID();
		case 2:
			return getFjalkalimi();
		case 3:
			return getEmri();
		case 4:
			return getMbiemri();
		case 5:
			return getEmail();
		case 6:
			return getNrPersonal();
		case 7:
			return getEmriPrindit();
		case 8:
			return getGjinia();
		case 9:
			return getShteti();
		case 10:
			return getKomuna();
		case 11:
			return getVendlindja();
		case 12:
			return getKombesia();
		case 13:
			return getAdresa();
		case 14:
			return getNrTelefoni();
		default:
			return null;
		}
	}

	public void addLenda(String s) {
		Lenda a = new Lenda(Fakulltetet.getData(Fakulltetet.subcelia(s, 0).substring(6, 9), filePath + "lendet"));
		lendet.add(a);
		int i = 1;
		while (Fakulltetet.subcelia(s, i) != null) {
			a.addProfesor(Fakulltetet.celia(
					Fakulltetet.getData(Fakulltetet.subcelia(s, i).substring(2, 5), getFakulteti() + "/profesor"), 2)
					+ " " + Fakulltetet.celia(Fakulltetet.getData(Fakulltetet.subcelia(s, i).substring(2, 5),
							getFakulteti() + "/profesor"), 3));
			i++;
		}
	}

	public ArrayList<Lenda> getlendet() {
		return lendet;

	}
}
