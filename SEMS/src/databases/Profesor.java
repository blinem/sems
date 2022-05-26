package databases;

import main.Controller;
import mainMenuProfesori.ControllerProfesorMenu;

public class Profesor extends Perdoruesi {
	private String nrPersonal;
	private String gjinia;
	private String shteti;
	private String komuna;
	private String vendlindja;
	private String kombesia;
	private String adresa;
	private String nrTelefoni;
	
	private String fakulteti;
	private String titulli;

	private int profesorID;

	public Profesor(String s) {
		int i=s.indexOf(",");
		int count=1;
		while(i!=-1) {
		this.setVlerat(count++, s.substring(0, i));
		s=s.substring(i+1);
		i=s.indexOf(",");
		}
	}

	@Override
	public void inicializo(Controller controller) {
		new ControllerProfesorMenu(this, controller);
	}
	
	public String getTitulli() {
		return titulli;
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

	public int getProfesorID() {
		return profesorID;
	}

	public String getNrPersonal() {
		return nrPersonal;
	}
	
	private void setTitulli(String s) {
		this.titulli=s;
		
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

	public void setNrPersonal(String nrPersonal) {
		this.nrPersonal = nrPersonal;
	}
	

	public  void setVlerat(int i, String s) {
		switch (i) {
		case 1: setPerdoruesiID(s);
				break;
		case 2:	setFjalkalimi(s);
				break;
		case 3:	setEmri(s);
				break;
		case 4:	setMbiemri(s);
				break;
		case 5:	setEmail(s);
				break;
		case 6: setNrPersonal(s);
				break;
		case 7: setTitulli(s);
				break;
		case 8: setGjinia(s);
				break;
		case 9: setShteti(s);
				break;
		case 10: setKomuna(s);
				break;
		case 11: setVendlindja(s);
				break;
		case 12: setKombesia(s);
				break;
		case 13: setAdresa(s);
				break;
		case 14: setNrTelefoni(s);
				break;
		}
	}

		public  String getVlerat(int i) {
			switch (i) {
			case 1: return ""+getPerdoruesiID();
			case 2:	return getFjalkalimi();
			case 3:	return getEmri();
			case 4:	return getMbiemri();
			case 5:	return getEmail();
			case 6: return getNrPersonal();
			case 7: return getTitulli();
			case 8: return getGjinia();
			case 9: return getShteti();
			case 10: return getKomuna();
			case 11: return getVendlindja();
			case 12: return getKombesia();
			case 13: return getAdresa();
			case 14: return getNrTelefoni();
			default: return null;
			}
	}

		
}
