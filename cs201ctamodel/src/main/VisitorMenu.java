package main;

public class VisitorMenu extends Menu {
	
	/*
	 * This class gives visitors access to the basic functionality defined in the Abstract Menu class
	 */
	public VisitorMenu(CTASystem system){
		super(system);
		this.setOptions(VISITOR_OPTIONS);
	}
	
	@Override
	public String toString() {
		return "Chicago Transit Authority: Visitor Menu";
	}
	
	@Override
	public void selectOption(String choice){
		switch(choice){
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

}
