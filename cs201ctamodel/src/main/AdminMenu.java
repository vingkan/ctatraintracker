package main;

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
		
	}
	
	public void editStation(){
		
	}
	
	public void removeStation(){
		
	}
	
}
