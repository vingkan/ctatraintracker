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
		return "Chicago Transit Authority: Administratie Menu";
	}
	
	public void selectOption(String choice){
		switch(choice){
			case "Find a Station":
				findStation();
				break;
			default:
		}
	}
	
}
