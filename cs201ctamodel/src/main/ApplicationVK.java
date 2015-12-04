package main;

import javax.swing.JOptionPane;

public class ApplicationVK {

	private static final String stopsPath = "cta-system-stops-pipes.csv";
	private static final String routesPath = "route-mappings.txt";
	
	public static void main(String[] args) {
		
		runApplication();
		//System.out.println("---------------------------");
		//testPlan();
		
	}

	public static void runApplication(){
		Menu menu;
		JOptionPane.showMessageDialog(null, "Welcome to the Chicago Transit Authority Java Platform!\nThe system data will be loaded shortly...");
		CTASystem cta = new CTASystem(new String[]{stopsPath, routesPath});
		int password = Converter.getUserInteger("Enter the 4 Digit Pin for Administrative Access");
		if(password == 1871){
			menu = new AdminMenu(cta);
		}
		else{
			menu = new VisitorMenu(cta);
		}
		menu.displayOptions();
	}
	
	public static void testPlan(){
		CTASystem cta = new CTASystem(new String[]{stopsPath, routesPath});
		Menu menu = new AdminMenu(cta);
		System.out.println(menu);
		int target1 = 30171;
		int target2 = 30146;
		CTAStop result1 = menu.sortedBinarySearch(new CTAStop(target1));
		CTAStop result2 = menu.sortedBinarySearch(new CTAStop(target2));
		System.out.println(result1);
		System.out.println(result2);
		Menu visMenu = new VisitorMenu(cta);
		visMenu.selectOption("Add Stop");
	}
	
}
