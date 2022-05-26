package dataFiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import databases.Fakulltetet;
import databases.Student;

public class StudentFile {
	public static ArrayList<Student> student;
	public static ArrayList<String> colums;

	public StudentFile() {

	}
	public static void set(String filePath) {
		student=new ArrayList<Student>();
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
				Student stud=new Student(s);
				student.add(stud);
				stud.setFakulteti(Fakulltetet.subcelia(filePath, 0));
				stud.setDepartamenti(Fakulltetet.subcelia(filePath, 1));
				stud.setProgrami(Fakulltetet.subcelia(filePath, 1));
				stud.setNiveliStudimeve(Fakulltetet.subcelia(filePath, 2));
				stud.setViti(filePath.substring(filePath.lastIndexOf('/'),filePath.length()-1));
				
			}
			
			infile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}


	
	public static void nderroFjalkalimin(String fjalkalimiRi,Student stud) {
	
		Fakulltetet.setData(fjalkalimiRi,stud.getPerdoruesiID(),1,stud.getFakulteti()+"/"+stud.getDepartamenti()+"/"+stud.getNiveliStudimeve()+"/"+stud.getViti());
	}
	
	
	public static void arrangeStudentData(String s,Student stud) {
		int count=1;
		while(Fakulltetet.celia(s, count-1)!=null) {
		stud.setVlerat(count, Fakulltetet.celia(s, count-1));
		count++;
		}
		
	}
}
