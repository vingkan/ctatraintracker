package main;

public class VisitorMenu extends Menu {
	
	public VisitorMenu(CTASystem system){
		super(system);
		this.setOptions(VISITOR_OPTIONS);
	}
	
	@Override
	public String toString() {
		return "Chicago Transit Authority: Visitor Menu";
	}
	
	public void selectOption(String choice){
		switch(choice){
			case "Find a Station":
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
