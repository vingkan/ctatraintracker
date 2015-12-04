package main;

public class VisitorMenu extends Menu {
	
	public VisitorMenu(CTASystem system){
		super(system);
		this.setOptions(VISITOR_OPTIONS);
	}
	
	public void selectOption(String choice){
		switch(choice){
			case "Find a Station":
				findStation();
				break;
			case "Trip Planner":
				findStation();
				break;
			case "Exit Program":
				findStation();
				break;
			default:
		}
	}

}
