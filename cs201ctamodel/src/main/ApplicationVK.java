package main;

public class ApplicationVK {

	private static final String stopsPath = "cta-system-stops-pipes.csv";
	private static final String routesPath = "route-mappings.txt";
	
	public static void main(String[] args) {
		
		/*cta.removeStop(30171);
		cta.removeStop(30160);
		cta.removeStop(30045);
		cta.removeStop(30146);
		cta.removeStop(30248);*/
		Menu menu;
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

}
