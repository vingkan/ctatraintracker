package main;

import java.io.File;
import java.util.Scanner;

public class ApplicationVK {

	public static void main(String[] args) {
		
		CTASystem cta = new CTASystem();

		File file = new File("cta-system-stops-pipes.csv");
		try{
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				//System.out.println(line);
				cta.addStop(new CTAStop(line));
			}
			scan.close();
		}
		catch(Exception e){
			System.out.println("Encountered Exception: " + e);
		}
		
		/*for(CTAStop stop : cta.getStops()){
			System.out.println(stop + "\n");
		}*/
		
		CTARoute greenIIT = new CTARoute(RouteType.GREEN_LINE);
		greenIIT.getPath().add(cta.getStopByID(30213));
		greenIIT.getPath().add(cta.getStopByID(30381));
		greenIIT.getPath().add(cta.getStopByID(30080));
		
		System.out.println(greenIIT);
		
		cta.addRoute(greenIIT);
		
		cta.drawMapPoints();
		cta.drawMapRoutes();
		cta.displayMap();
		
	}

}
