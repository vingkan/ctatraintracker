package main;

import javax.swing.JOptionPane;

public class AdminMenu extends Menu {
	
	public AdminMenu(CTASystem system){
		super(system);
		String[] ALL_OPTIONS = new String[VISITOR_OPTIONS.length + ADMIN_OPTIONS.length];
		System.arraycopy(ADMIN_OPTIONS, 0, ALL_OPTIONS, 0, ADMIN_OPTIONS.length);
		System.arraycopy(VISITOR_OPTIONS, 0, ALL_OPTIONS, ADMIN_OPTIONS.length, VISITOR_OPTIONS.length);
		this.setOptions(ALL_OPTIONS);
	}
	
	@Override
	public String toString() {
		return "Chicago Transit Authority: Administrative Menu";
	}
	
	@Override
	public void selectOption(String choice){
		switch(choice){
			case "Add Stop":
				addStation();
				break;
			case "Edit Stop":
				editStation();
				break;
			case "Remove Stop":
				removeStation();
				break;
			case "Find Stop":
				findStation();
				break;
			case "Trip Planner":
				tripPlanner();
				break;
			case "Exit Program":
				exitProgram();
				break;
			default:
		}
	}
	
	public void addStation(){
		JOptionPane.showMessageDialog(null, "Choose the route to edit.");
		CTARoute editRoute = searchForRoute();
		String name = JOptionPane.showInputDialog("What will the new station be called?");

		double lat = Converter.getUserDouble("Enter stop latitude.");
		double lon = Converter.getUserDouble("Enter stop longitude.");
		CTAStop newStop = new CTAStop(name, lat, lon, editRoute.getType());
		newStop.setID(getSystem().getStops().size() + 100);
		getSystem().addStop(newStop);
		
		String[] decisionOptions = {"Before", "After"};
		String decision = Converter.getAcceptableStrings("Would you like the new stop to go before or after an existing stop?", decisionOptions, false);
		JOptionPane.showMessageDialog(null, "Next you will pick the station the new stop should go " + decision + ".");
		
		CTAStop referenceStation = searchForStop(editRoute.getPath());
		if(decision.toLowerCase().equals("after")){
			editRoute.getPath().add(editRoute.getPath().indexOf(referenceStation), newStop);
			//JOptionPane.showMessageDialog(null, "New Stop Added.");
		}
		else if(decision.toLowerCase().equals("before")){
			editRoute.getPath().add(editRoute.getPath().indexOf(referenceStation)+1, newStop);
			//JOptionPane.showMessageDialog(null, "New Stop Added.");
		}
		getSystem().spotLocation(newStop, "Added: " + newStop.getName(), this);
	}
	
	public void editStation(){
		CTAStop choice = searchForStop();
		String name = JOptionPane.showInputDialog(null, "Change Stop Name:", choice.getName());
		choice.setName(name);
		double lat = Converter.getUserDouble("New Latitude for Stop: (enter 0 to keep current coordinates)");
		double lon = Converter.getUserDouble("New Longitude for Stop: (enter 0 to keep current coordinates)");
		if(lat != 0 && lon != 0){
			choice.setLatitude(lat);
			choice.setLongitude(lon);
		}
		getSystem().spotLocation(choice, "Edited: " + choice.getName(), this);
	}
	
	public void removeStation(){
		CTAStop target = searchForStop();
		getSystem().removeStop(target.getID());
		getSystem().spotLocation(target, "Removed: " + target.getName(), this);
		System.out.println("finish remove station call");
	}
	
}
