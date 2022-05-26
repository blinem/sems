package databases;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Fakulltetet {
	private static BufferedReader infile;

	public static String[] getFakulltetet() {
		String s = getData("fakultetet");
		Object[] a = s.lines().toArray();
		String[] b = new String[a.length];
		for (int i = 0; i < a.length; i++)
			b[i] = a[i].toString();
		return b;

	}

	public static String[] getColumnData(int column, String filePath) {
		String t = getData(filePath);
		Object[] a = t.lines().toArray();
		String[] b = new String[a.length];
		for (int i = 0; i < b.length; i++)
			b[i] = celia(a[i].toString(), column);
		return b;
	}

	public static String[] getDepartamentet(String fakulteti) {
		String s = getData(fakulteti + "/departamentet");
		Object[] a = s.lines().toArray();
		String[] b = new String[a.length];
		for (int i = 0; i < a.length; i++)
			b[i] = a[i].toString();
		return b;

	}

	public static String getData(String filePath) {
		String t = "";
		try {
			infile = new BufferedReader(new FileReader("database/" + filePath + ".txt"));
			while (infile.ready())
				t += infile.readLine() + System.lineSeparator();
			infile.close();

		} catch (Exception e) {

		}
		return t;
	}

	public static String getData(String numri, String filePath) {
		String s = getData(filePath);
		if(!s.isEmpty()) {
		Object[] a = s.lines().toArray();
		String[] b = new String[a.length];
		for (int i = 0; i < a.length; i++)
			b[i] = a[i].toString();
		return b[new Integer(numri).intValue()];
		}
		return null;

	}

	public static String getData(String numri, int column, String filePath) {
		return celia(getData(numri, filePath), column);
	}

	public static void setData(String data, String filePath) {
		FileWriter writer = null;
		try {
			writer = new FileWriter("database/" + filePath + ".txt");
			writer.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block

		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block

			}
		}
	}

	public static void setData(String data, int row, String filePath) {
		String t = "";
		String s = getData(filePath);
		Object[] a = s.lines().toArray();
		String[] b = new String[a.length];
		for (int i = 0; i < a.length; i++)
			b[i] = a[i].toString();
		b[row] = data;
		for (int i = 0; i < b.length; i++) {
			t += b[i] + System.lineSeparator();
		}
		setData(t, filePath);
	}

	public static void setData(String data, int row, int column, String filePath) {
		String t = "";

		String s = getData("" + row, filePath);
		for (int i = 0; i < column; i++) {
			t += celia(s, i) + ",";
		}
		t += data + ",";
		int index = column + 1;
		while (celia(s, index) != null)
			t += celia(s, index++) + ",";
		setData(t, row, filePath);
	}

	public static void setData(String data, int row, int column, int semiColumn, String filePath) {
		String t = "";
		String s = getData("" + row, column, filePath);
		for (int i = 0; i < semiColumn; i++) {
			t += subcelia(s, i) + "/";
		}
		t += data + "/";
		int index = semiColumn + 1;
		while (subcelia(s, index) != null)
			t += subcelia(s, index++) + "/";
		setData(t, row, column, filePath);
	}

	public static void addData(String data, String filePath) {
		String t = getData(filePath);
		
		if(!t.isEmpty()) {
		t = t + data + System.lineSeparator();
		setData(t, filePath);
		}else {
			String s="";
			if(filePath.charAt(filePath.length()-1)=='L') {
				String fp="";
				int i=1;
				while(subcelia(filePath, i)!=null)
					fp=subcelia(filePath, i)+"/";
				s=getData(fp+"lendetprofesoret");
			}else {
				s="Id,Password,Emri,Mbiemri,Email,NrPersonal,Emri Prindit,Gjinia,Shteti,Komuna,Vendlindja,Kombesia,Adresa,NrTelefonit,";
			}
			setData(s+System.lineSeparator()+data+System.lineSeparator(),filePath);
		}
	}

	public static void deleteLastRow(String filePath) {
		String t = getData(filePath);
		Object[] a = t.lines().toArray();
		String s = "";
		for (int i = 0; i < a.length - 1; i++)
			s += a[i].toString()+System.lineSeparator();
		setData(s, filePath);
	}

	public static String celia(String s, int i) {
		int before = -1;
		int k = 0;
		for (int j = 0; j < s.length(); j++) {

			if (s.charAt(j) == ',') {
				if (k == i) {
					return s.substring(before + 1, j);
				}
				k++;
				before = j;

			}
		}

		return null;
	}

	public static String subcelia(String s, int i) {
		int before = -1;
		int k = 0;
		for (int j = 0; j < s.length(); j++) {

			if (s.charAt(j) == '/') {
				if (k == i) {
					return s.substring(before + 1, j);
				}
				k++;
				before = j;

			}
		}

		return null;
	}

	public static String getNiveli(String s) {
		int n = new Integer(s).intValue();
		if (n == 1)
			return "Baçelor";
		else if (n == 2)
			return "Master";
		else if (n == 3)
			return "Doktoraturë";
		return null;
	}

	public static String getColumns(String filePath) {
		String t = getData("" + 0, filePath);
		if(t!=null) {
		int index = 1;
		String c = ",";
		while (celia(t, index) != null) {
			index++;
			c += "0,";
		};
		return c;
		}
		return "";

	}

}
