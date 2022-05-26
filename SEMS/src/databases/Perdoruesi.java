package databases;


import main.Controller;

public abstract class Perdoruesi {

	private String emri;
	private String mbiemri;
	private String email;
	private int perdoruesiID;
	private String fjalkalimi;


	public Perdoruesi( String emri, String mbiemri, String email, String fjalkalimi) {

		this.emri = emri;
		this.mbiemri = mbiemri;
		this.email = email;
		this.fjalkalimi = fjalkalimi;
	}

	public Perdoruesi() {
		this.emri = "ERROR";
		this.mbiemri = "ERROR";
		this.email = "ERROR";
		this.fjalkalimi = "ERROR";
	}

	public abstract void inicializo(Controller controller); 
	
	public abstract void setVlerat(int i, String s);

	public String getEmri() {
		return emri;
	}

	public String getMbiemri() {
		return mbiemri;
	}

	public String getEmail() {
		return email;
	}

	public int getPerdoruesiID() {
		return perdoruesiID;
	}

	public String getFjalkalimi() {
		return fjalkalimi;
	}

	public void setEmri(String emri) {
		this.emri = emri;
	}

	public void setMbiemri(String mbiemri) {
		this.mbiemri = mbiemri;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFjalkalimi(String fjalkalimi) {
		this.fjalkalimi = fjalkalimi;
	}
	public void setPerdoruesiID(String id) {
		if(!id.isEmpty()) {
			try {
			this.perdoruesiID=new Integer(id).intValue();
			}catch(Exception e) {}
		}
		
	}	
}
