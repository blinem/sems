package databases;

import java.util.ArrayList;

public class Lenda {
	private String emriLendes;
	private int semestri;
	private String niveli;
	private String lloji;

	private String fakullteti;
	private String departamenti;
	private String profesori;
	private ArrayList<String> profesoret;

	private boolean paraqitLenden;
	private boolean paraqitProvimin;
	private boolean refuzuar;

	private int rezultati;
	private int nota;

	private int ECTS;
	private int lendaID;

	public Lenda(String s) {
		profesoret = new ArrayList<String>();
		int i = s.indexOf(",");
		int count = 1;
		while (i != -1) {
			this.setVlerat(count++, s.substring(0, i));
			s = s.substring(i + 1);
			i = s.indexOf(",");
		}
	}

	public String getEmriLendes() {
		return emriLendes;
	}

	public int getSemestri() {
		return semestri;
	}

	public String getFakullteti() {
		return fakullteti;
	}

	public String getDepartamenti() {
		return departamenti;
	}

	public String getNiveli() {
		return niveli;
	}

	public String getLloji() {
		return lloji;
	}

	public String getProfesori() {
		return profesori;
	}

	public ArrayList<String> getProfesoret() {
		return profesoret;
	}

	public int getECTS() {
		return ECTS;
	}

	public int getLendaID() {
		return lendaID;
	}

	public boolean getParaqitLenden() {
		return paraqitLenden;
	}

	public boolean getParaqitProvimin() {
		return paraqitProvimin;
	}

	public boolean getRefuzuar() {
		return refuzuar;
	}

	public int getRezultati() {
		return rezultati;
	}

	public int getNota() {
		return nota;
	}

	public void setEmriLendes(String emriLendes) {
		this.emriLendes = emriLendes;
	}

	public void setSemestri(String semestri) {
		if (!semestri.isEmpty()) {
			this.semestri = new Integer(semestri).intValue();
		}
	}

	public void setFakullteti(String fakullteti) {
		this.fakullteti = fakullteti;
	}

	public void setDepartamenti(String departamenti) {
		this.departamenti = departamenti;
	}

	public void setNiveli(String niveli) {
		this.niveli = niveli;
	}

	public void setLloji(String lloji) {
		this.lloji = lloji;
	}

	public void setProfesori(String profesori) {
		this.profesori = profesori;
	}

	public void setParaqitProvimin(boolean paraqitProvimin) {
		this.paraqitProvimin = paraqitProvimin;
	}

	public void setParaqitLenden(boolean paraqitLenden) {
		this.paraqitLenden = paraqitLenden;
	}

	public void addProfesor(String s) {
		profesoret.add(s);
	}

	public void setECTS(String ECTS) {
		if (!ECTS.isEmpty()) {
			this.ECTS = new Integer(ECTS).intValue();
		}
	}

	public void setLendaID(String lendaID) {
		if (!lendaID.isEmpty()) {
			this.lendaID = new Integer(lendaID).intValue();
		}
	}

	public void setRefuzuar(boolean refuzuar) {
		this.refuzuar = refuzuar;
	}

	public void setRezultati(String rezultati) {
		if (!rezultati.isEmpty()) {
			this.rezultati = new Integer(rezultati).intValue();
		}
	}

	public void setNota(String nota) {
		if (!nota.isEmpty()) {
			this.nota = new Integer(nota).intValue();
		}
	}

	public void setVlerat(int i, String s) {
		switch (i) {
		case 1:
			setLendaID(s);
			break;
		case 2:
			setEmriLendes(s);
			break;
		case 3:
			setSemestri(s);
			break;
		case 4:
			setECTS(s);
			break;
		case 5:
			setNiveli(s);
			break;
		case 6:
			setLloji(s);
			break;
		}
	}

	public String getVlera(int i, int j) {
		switch (i) {
		case 1:
			return getEmriLendes();

		case 2:
			return getProfesoret().get(j);

		case 3:
			return "" + getECTS();

		default:
			return null;
		}

	}
}