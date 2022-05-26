package dataFiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import databases.Fakulltetet;
import databases.Profesor;
import databases.Student;

public class ProfesorFile {
	public static ArrayList<Profesor> profesor;
	public static ArrayList<String> colums;
	public static void set(String filePath) {
		profesor=new ArrayList<Profesor>();
		colums=new ArrayList<String>();
		try {

			BufferedReader infile = new BufferedReader(new FileReader("database/"+filePath+".txt"));
			
			String col= infile.readLine();
			int i=0;
			while(i!=-1) {
				i=col.indexOf(",");
				if(i!=-1) {
				colums.add(col.substring(0,i));
				
				col=col.substring(i+1);
				}
			}
			while (infile.ready()) {

				String s = infile.readLine();
				Profesor prof=new Profesor(s);
				profesor.add(prof);
				prof.setFakulteti(Fakulltetet.subcelia(filePath, 0));
			}
			
			infile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void nderroFjalkalimin(String fjalkalimiRi,Profesor prof) {
		
		Fakulltetet.setData(fjalkalimiRi,prof.getPerdoruesiID(),1,prof.getFakulteti()+"/"+"profesor");
	}
	public static void arrangeProfesorData(String s, Profesor prof) {
		int count=1;
		while(Fakulltetet.celia(s, count-1)!=null) {
		prof.setVlerat(count, Fakulltetet.celia(s, count-1));
		count++;
		}
		
	
	}
}
