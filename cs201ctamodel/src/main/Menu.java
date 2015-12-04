package main;

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
		String choice = (String)JOptionPane.showInputDialog(
			null, this.toString(),
			null, JOptionPane.PLAIN_MESSAGE,
			null, this.getOptions(), this.getOptions()[0]);
		try{
			selectOption(choice);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Thank you for using this app. Have a nice day!");
		}
	}
	
	@Override
	public String toString() {
		return "Chicago Transit Authority: Menu";
	}

	public void selectOption(String choice){
		//To be implemented by child class.
	}
	
	public void findStation(){
		
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
