package main;

import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public abstract class Menu {

	protected static final String[] VISITOR_OPTIONS = {
		"Find Stop",
		"Trip Planner",
		"Exit Program"
	};
	
	protected static final String[] ADMIN_OPTIONS = {
		"Add Stop",
		"Edit Stop",
		"Remove Stop"
	};
	
	private CTASystem system;
	private Location userLocation;
	private String[] options;
	
	public Menu(CTASystem system){
		this.system = system;
		//this.userLocation = Menu.promptUserLocation();
		this.options = new String[0];
	}
	
	public static Location promptUserLocation(){
		double customLat = Converter.getUserDouble("Please enter your current latitude coordinate."); //41.830770
		double customLon = Converter.getUserDouble("Please enter your current longitude coordinate."); //-87.630035
		Location user = new Location("User", customLat, customLon);
		return user;
	}
	
	public void displayOptions(){
		System.out.println("opening menu");
		String choice = (String)JOptionPane.showInputDialog(
			null, this.toString(),
			null, JOptionPane.PLAIN_MESSAGE,
			null, this.getOptions(), this.getOptions()[0]);
		try{
			selectOption(choice);
		}
		catch(Exception e){
			exitProgram();
		}
	}
	
	@Override
	public String toString() {
		return "Chicago Transit Authority: Menu";
	}

	public void selectOption(String choice){
		//To be implemented by child class.
	}
	
	public CTAStop searchForStop(){
		int size = system.getStops().size();
		CTAStop[] stopsList = new CTAStop[size];
		for(int i = 0; i < size; i++){
			stopsList[i] = system.getStops().get(i);
		}
		CTAStop choice = (CTAStop)JOptionPane.showInputDialog(
			null, "CTA Stops",
			null, JOptionPane.PLAIN_MESSAGE,
			null, stopsList, stopsList[0]);
		return choice;
	}
	
	public CTAStop searchForStop(List<CTAStop> list){
		int size = list.size();
		CTAStop[] stopsList = new CTAStop[size];
		for(int i = 0; i < size; i++){
			stopsList[i] = list.get(i);
		}
		CTAStop choice = (CTAStop)JOptionPane.showInputDialog(
			null, "CTA Stops",
			null, JOptionPane.PLAIN_MESSAGE,
			null, stopsList, stopsList[0]);
		return choice;
	}
	
	public CTARoute searchForRoute(){
		int size = system.getRoutes().size();
		CTARoute[] routesList = new CTARoute[size];
		for(int i = 0; i < size; i++){
			routesList[i] = system.getRoutes().get(i);
		}
		CTARoute choice = (CTARoute)JOptionPane.showInputDialog(
			null, "CTA Routes",
			null, JOptionPane.PLAIN_MESSAGE,
			null, routesList, routesList[0]);
		return choice;
	}
	
	public void findStation(){
		CTAStop choice = searchForStop();
		system.spotLocation(choice, "Found: " + choice.getName(), this);
	}
	
	public void tripPlanner(){
		CTAStop end = searchForStop();
		LocationComparator locationComparator = new LocationComparator(end);
		Collections.sort(system.getStops(), locationComparator);
		CTAStop start = searchForStop();
		system.spotLocation(start, "Start: " + start.getName(), this);
		system.spotConcurrentLocation(end, "End: " + end.getName(), this);
	}
	
	public void exitProgram(){
		JOptionPane.showMessageDialog(null, "Thank you for using this app. Have a nice day!");
	}

	public CTASystem getSystem() {
		return system;
	}

	public void setSystem(CTASystem system) {
		this.system = system;
	}

	public Location getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(Location userLocation) {
		this.userLocation = userLocation;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}
	
}
