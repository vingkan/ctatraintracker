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
		
		int[] greenStops = {30004, 30263, 30119, 30243, 30054, 30135, 30094, 30005, 30291, 30207, 30265, 30033, 30295, 30221, 30074, 30050, 30039, 30132, 30081, 30382, 30214, 30059, 30246, 30210, 30025, 30100, 30184, 30057};
		CTARoute greenLineTo63rd = new CTARoute(RouteType.GREEN_LINE, cta, greenStops);
		cta.addRoute(RouteType.GREEN_LINE, new int[]{30004, 30263, 30119, 30243, 30054, 30135, 30094, 30005, 30291, 30207, 30265, 30033, 30295, 30221, 30074, 30050, 30039, 30132, 30081, 30382, 30214, 30059, 30246, 30210, 30025, 30100, 30217, 30139});
		
		System.out.println(greenIIT);
		
		cta.addRoute(greenLineTo63rd);
		
		cta.drawMapPoints();
		cta.drawMapRoutes();
		cta.displayMap();
		
	}

}
