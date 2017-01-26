package cta; 
import cta.Train;
import java.util.*;
import java.io.*;

public class TrainClient {
	Train[] train;

// MAIN METHOD 	
	public static void main(String[] args) {
		TrainClient obj = new TrainClient();
		Vector v = obj.vectorData("cta_train.csv");
		String in = "c";
	while (in.equals("q")==false) {
		// create a vector with points from a specific year
		Vector ve = new Vector();
		while (ve.size() == 0) {
			System.out.println();
			System.out.print("Which year do you want data from? ");
			Scanner scanner = new Scanner(System.in);
			String dd = scanner.nextLine();
			ve = obj.searchDVector(v, dd);
			if (ve.size()==0) {
				System.out.println("No such year exists. Try another name. (To quit, press 'q'. To list possible years, enter 'ls'.) \n");
			}
			else System.out.println("There are " + ve.size() + " points in " + dd +"\n");
			
			if (dd.equals("q")) {
				System.out.println("Quitting");
				System.exit(0);
			}
			if (dd.equals("ls")) {
				int k;
				Train f = (Train) v.elementAt(0);
				System.out.println(f.getYear());
				for (k=1; k<v.size(); k++) {
					Train t = (Train) v.elementAt(k);
					Train tt = (Train) v.elementAt(k-1);

					if(tt.getYear().equals(t.getYear())==false) {
						System.out.println(t.getYear());
					}
				}
				System.out.println();
			}
		}

		// create a vector with points from a specific station the above year
		Vector ve1 = new Vector();
		while (ve1.size() == 0) {
			System.out.print("Which station? ");
		 	Scanner scanner1 = new Scanner(System.in);
		 	String sdd = scanner1.nextLine();
		 	ve1 = obj.searchSDVector(ve, sdd);
		 	if (ve1.size()==0) {
				System.out.println("No such station exists. Try another name. (To quit, enter 'q'. To list subdistricts, enter 'ls'.) \n");
			}
			else System.out.println();
			
			if (sdd.equals("q")) {
				System.out.println("Quitting\n");
				System.exit(0);
			}
			if (sdd.equals("ls")) {
				int k;
				Train f = (Train) ve.elementAt(0);
				System.out.println(f.getStationName());
				for (k=1; k<ve.size(); k++) {
					Train t = (Train) ve.elementAt(k);
					Train tt = (Train) ve.elementAt(k-1);
					if(tt.getStationName().equals(t.getStationName())==false){
						System.out.println(t.getStationName());
					}
				}
				System.out.println();
			}
			if (sdd.equals("all")) {
				Train f = (Train) ve.elementAt(0);
				ve1 = obj.searchSDVector(ve, f.getStationName());
				int x = obj.score(ve1);
				System.out.println(x);
				for (int k = 1; k<ve.size(); k++) {
					
					f = (Train) ve.elementAt(k);
					Train ff = (Train) ve.elementAt(k-1);
					ve1 = obj.searchSDVector(ve, f.getStationName());
					x = obj.score(ve1);
					if (f.getStationName().equals(ff.getStationName()) == false) { 
						System.out.println( x);
					}
				}
				System.out.println("DONE");
			}
		}
		int x = obj.score(ve1);
		System.out.println("Total number of rides = " + x);
		

		System.out.println();
		
		System.out.print("Press 'c' to continue or 'q' to quit: ");
		Scanner input = new Scanner(System.in);
		in = input.nextLine();
	}
	
		if (in.equals("q")) {
				System.out.println("Quitting\n");
				System.exit(0);
			}
	}
	
// Method to create an array of points from the data file.	
	public Vector vectorData(String filename) {
		Vector v;
		int count = 0;
		try{
			File openFile = new File(filename);
			Scanner in = new Scanner(openFile);
			
			while (in.hasNextLine()) {
				count++;
				in.nextLine();
			}
					
			train = new Train[count];
					
			Scanner input = new Scanner(openFile);
			int i=0;
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String[] details = line.split(",");
				int stationID = Integer.parseInt(details[0]);
				String stationName = details[1];
				String date = details[2];
				String dayType = details[3];
				int rides = Integer.parseInt(details[4]);
				
				String[] datee = date.split("/");
				int month = Integer.parseInt(datee[0]);
				int day = Integer.parseInt(datee[1]);
				String year = datee[2];
				
				train[i] = new Train(stationID, stationName, month, day, year, dayType, rides);
				i++;
			}	
			
			v = new Vector();
			for (int j=0; j<train.length; j++) {
				v.addElement(train[j]);
			}
			
			return v;
		}
		catch (IOException ioe) {
			System.out.println("Not possible to execute");
			Vector v2 = new Vector(0);
			return v2;
		}
	}

// Method to search an array of points for only those of the desired year and return a vector of points from only that year
	public Vector searchDVector(Vector sv, String key) {
		int start=0, end = sv.size();
		Vector v3 = new Vector();
		while (start<end) {
			Train pnt = (Train) sv.elementAt(start);
			if (pnt.getYear().equals(key)) {
				v3.addElement(pnt);
			}
			start++;		
		}
		return v3;
	}

// Method to search an array of points for only those of the desired station and return a vector of points from only that station
	public Vector searchSDVector(Vector sv, String key) {
		int start=0, end = sv.size();
		Vector v3 = new Vector();
		while (start<end) {
			Train pnt = (Train) sv.elementAt(start);
			if (pnt.getStationName().equals(key)) {
				v3.addElement(pnt);
			}
			start++;		
		}
		return v3;
	}

// Method to get number of rides
	public int score(Vector v3) {
		int length = v3.size();
		Train[] pt = new Train[length];
		for (int i=0; i<length; i++) {
			pt[i] = (Train) v3.elementAt(i);		
		}
		int score = 0;
		int n=pt.length;
		for(int j=0; j<n-1;j++) {
			score = score + pt[j].getRides();
		}
	
		return score;
	}
}