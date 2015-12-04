package main;

import java.awt.Color;

public enum RouteType {
	
	CUSTOM ("Custom Route", Color.GRAY),
	GREEN_LINE ("Green Line", new Color(0x00A950)),
	RED_LINE ("Red Line", Color.RED),
	BLUE_LINE ("Blue Line", new Color(0x00A0E0)),
	ORANGE_LINE ("Orange Line", Color.ORANGE),
	YELLOW_LINE ("Yellow Line", Color.YELLOW),
	BROWN_LINE ("Brown Line", new Color(0x754200)),
	PURPLE_LINE ("Purple Line", new Color(0x3B2D83)),
	PURPLE_EXPRESS ("Purple Express", Color.MAGENTA),
	PINK_LINE ("Pink Line", Color.PINK);

	private String name;
	private Color color;
	
	RouteType(String name, Color color){
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}
	
	public static RouteType getTypeByName(String name){
		RouteType response = RouteType.CUSTOM;
		for(RouteType type : RouteType.values()){
			if(type.getName().equals(name)){
				response = type;
			}
		}
		return response;
	}
	
}
