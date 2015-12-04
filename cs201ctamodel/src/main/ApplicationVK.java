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
		
		/*for(CTAStop stop : cta.getStops()){
			System.out.println(stop + "\n");
		}*/
		
		//These are all the mappings I will add for now.
		/*cta.addRoute("Ashland/63rd", RouteType.GREEN_LINE, new int[]{30004, 30263, 30119, 30243, 30054, 30135, 30094, 30005, 30291, 30207, 30265, 30033, 30295, 30221, 30074, 30050, 30039, 30132, 30081, 30382, 30214, 30059, 30246, 30210, 30025, 30100, 30184, 30057});
		cta.addRoute("Cottage Grove", RouteType.GREEN_LINE, new int[]{30004, 30263, 30119, 30243, 30054, 30135, 30094, 30005, 30291, 30207, 30265, 30033, 30295, 30221, 30074, 30050, 30039, 30132, 30081, 30382, 30214, 30059, 30246, 30210, 30025, 30100, 30217, 30139});
		cta.addRoute("95th/Dan Ryan", RouteType.RED_LINE, new int[]{30173, 30228, 30021, 30252, 30148, 30170, 30268, 30067, 30230, 30150, 30106, 30017, 30274, 30256, 30234, 30126, 30122, 30280, 30065, 30290, 30212, 30110, 30286, 30270, 30194, 30037, 30238, 30224, 30178, 30192, 30047, 30276, 30089});
		cta.addRoute("Howard", RouteType.RED_LINE, new int[]{30088, 30275, 30046, 30191, 30177, 30223, 30237, 30036, 30193, 30269, 30285, 30109, 30211, 30289, 30064, 30279, 30121, 30125, 30233, 30255, 30273, 30016, 30105, 30149, 30229, 30066, 30267, 30169, 30147, 30251, 30020, 30227, 30173});
		cta.addRoute("Forest Park", RouteType.BLUE_LINE, new int[]{30171, 30160, 30045, 30146, 30248, 30260, 30108, 30240, 30013, 30198, 30112, 30130, 30116, 30063, 30272, 30096, 30374, 30073, 30154, 30015, 30262, 30085, 30069, 30093, 30158, 30043, 30049, 30180, 30188, 30002, 30035, 30190, 30077});*/
		
		cta.drawMapPoints();
		cta.drawMapRoutes();
		cta.toggleMap(true);
		
		cta.removeStop(30171);
		cta.removeStop(30160);
		cta.removeStop(30045);
		cta.removeStop(30146);
		cta.removeStop(30248, true);
		
	}

}
