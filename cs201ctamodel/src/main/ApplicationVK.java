package main;

import java.io.File;
import java.util.Scanner;

public class ApplicationVK {

	public static void main(String[] args) {
		
		CTASystem cta = new CTASystem();

		File stopsFile = new File("cta-system-stops-pipes.csv");
		File routesFile = new File("route-mappings.txt");
		try{
			Scanner scan = new Scanner(stopsFile);
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				cta.addStop(new CTAStop(line));
			}
			scan.close();
			Scanner mapper = new Scanner(routesFile);
			while(mapper.hasNextLine()){
				String line = mapper.nextLine();
				cta.addRoute(line);	
			}
			mapper.close();
		}
		catch(Exception e){
			System.out.println("Encountered Exception: " + e);
		}
		
		cta.removeStop(30171);
		cta.removeStop(30160);
		cta.removeStop(30045);
		cta.removeStop(30146);
		cta.removeStop(30248);
		
		Menu menu = new VisitorMenu(cta);
		menu.displayOptions();
		
	}

}
