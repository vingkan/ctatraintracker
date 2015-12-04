package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApplicationVK {

	public static void main(String[] args) {
		
		List<CTAStop> stops = new ArrayList<CTAStop>();

		File file = new File("cta-sample-stops-pipes.csv");
		try{
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				//System.out.println(line);
				stops.add(new CTAStop(line));
			}
			scan.close();
		}
		catch(Exception e){
			System.out.println("Encountered Exception: " + e);
		}
		
		for(CTAStop stop : stops){
			System.out.println(stop + "\n");
		}

	}

}
