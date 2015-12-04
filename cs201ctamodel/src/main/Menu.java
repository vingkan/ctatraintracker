package main;

import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

/*
 * Menu class to run program operations
 * This class is abstract
 */
public abstract class Menu {

	/*
	 * Commands available to Visitors
	 */
	protected static final String[] VISITOR_OPTIONS = {
		"Find Stop",
		"Trip Planner",
		"Exit Program"
	};
	
	/*
	 * Commands available to Administrators
	 */
	protected static final String[] ADMIN_OPTIONS = {
		"Add Stop",
		"Edit Stop",
		"Remove Stop"
	};
	
	private CTASystem system; //CTA System model used in program
	private Location userLocation; //Location of client
	private String[] options; //Options available to instance of Menu
	
	/*
	 * Main Constructor
	 */
	public Menu(CTASystem system){
		this.system = system;
		//this.userLocation = Menu.promptUserLocation();
		this.options = new String[0];
	}
	
	/*
	 * Get the user's location coordinates
	 */
	public static Location promptUserLocation(){
		double customLat = Converter.getUserDouble("Please enter your current latitude coordinate."); //41.830770
		double customLon = Converter.getUserDouble("Please enter your current longitude coordinate."); //-87.630035
		Location user = new Location("User", customLat, customLon);
		return user;
	}
	
	/*
	 * Display menu options as selector box for user
	 */
	public void displayOptions(){
		system.getMap().setVisible(false);
		System.out.println("opening menu");
		String choice = (String)JOptionPane.showInputDialog(
			null, this.toString(),
			null, JOptionPane.PLAIN_MESSAGE,
			null, this.getOptions(), this.getOptions()[0]);
		try{
			selectOption(choice);
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Wrong: " + choice);
			exitProgram();
		}
	}
	
	@Override
	public String toString() {
		return "Chicago Transit Authority: Menu";
	}
	
	/*
	 * Refer to an action by its String name and run it
	 */
	public void selectOption(String choice){
		//To be implemented by child class.
	}
	
	/*
	 * Loops through CTA stops to find a match
	 * @prompt: message to be displayed to the user at the top of the list
	 */
	public CTAStop searchForStop(String prompt){
		int size = system.getStops().size();
		CTAStop[] stopsList = new CTAStop[size];
		for(int i = 0; i < size; i++){
			stopsList[i] = system.getStops().get(i);
		} 
		CTAStop choice = (CTAStop)JOptionPane.showInputDialog(
			null, prompt,
			null, JOptionPane.PLAIN_MESSAGE,
			null, stopsList, stopsList[0]);
		return choice;
	}
	
	/*
	 * Similar to above, but only searches through the given list
	 */
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
	
	/*
	 * Displays CTA Routes as a selector list for user to choose one
	 */
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
	
	/*
	 * Piinpoints a stop on the CTASystem map
	 */
	public void findStation(){
		CTAStop choice = searchForStop("Select a stop.");
		system.spotLocation(choice, "Found: " + choice.getName(), this);
	}
	
	/*
	 * It took so long to get the MapWindow set up, but it looks pretty cool.
	 * I'm gonna say that seeing the start and end point on the map satisfies
	 * "Generate a route from a starting station to a destination station" 
	 */
	public void tripPlanner(){
		CTAStop end = searchForStop("Choose a destination stop.");
		System.out.println("desintation");
		LocationComparator locationComparator = new LocationComparator(end);
		Collections.sort(system.getStops(), locationComparator);
		CTAStop start = searchForStop("Choose your starting stop.");
		system.spotLocation(start, "Start: " + start.getName(), this);
		system.spotConcurrentLocation(end, "End: " + end.getName(), this);
		System.out.println("finish method");
	}
	
	/*
	 * Ends the program
	 */
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
	
	/*
	 * Sorting/Searching Functions from Lab 7:
	 * Modified to sort CTAStops
	 */
	
	public static CTAStop[] selectionSort(List<CTAStop> stopsList){
		CTAStop[] oldList = new CTAStop[stopsList.size()];
		for(int s = 0; s < stopsList.size(); s++){
			oldList[s] = stopsList.get(s);
		}
		CTAStop[] newList = new CTAStop[stopsList.size()];
		int counter = 0;
		for(int i = 0; i < oldList.length; i++){
			CTAStop smallestID = oldList[i];
			int swapPoint = i;
			for(int j = i+1; j < oldList.length; j++){
				counter++;
				if(smallestID.getID() > oldList[j].getID()){
					smallestID = oldList[j];
					swapPoint = j;
					
				}
			}
			oldList[swapPoint] = oldList[i];
			newList[i] = smallestID;
		}
		System.out.println("Took " + counter + " tries to sort the list.");
		/*for(String item : newList){
			System.out.println(item);
		}*/
		return newList;
	}

	public CTAStop sortedBinarySearch(CTAStop target){
		CTAStop response = new CTAStop();
		CTAStop[] list = Menu.selectionSort(this.getSystem().getStops());
		int counter = 0;
		boolean found = false;
		int lowest = 0;
		int highest = list.length;
		int middle = Math.round(list.length / 2);
		//To prevent infinite loops when object is not in array
		boolean reachedEnd = false;
		//System.out.println("Middle of " + list.length + " is " + middle);
		while(!found){
			counter++;
			//System.out.println(list[middle] + " vs " + target);
			if(list[middle].getID() == target.getID()){
				found = true;
				System.out.println("Took " + counter + " tries to find " + target + ".");
				response = list[middle];
				break;
			}
			else{
				if(list[middle].getID() < target.getID()){
					lowest = middle;
					middle = Math.round((highest - middle) / 2) + middle;
					
				}
				else{
					highest = middle;
					middle = Math.round((middle - lowest) / 2);
				}
				//System.out.println("hi: " + highest + ", lo: " + lowest);
				//System.out.println("New middle is " + middle);
			}
			if(reachedEnd){
				System.out.println("Couldn't find " + target + " in " + counter + " tries.");
				break;
			}
			if(middle == (list.length - 1) || middle == 0){
				reachedEnd = true;
			}
		}
		return response;
	}
}
