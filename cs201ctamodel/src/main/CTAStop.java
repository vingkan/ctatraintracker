package main;

public class CTAStop extends Location{

	private int id;
	private String name;
	private int stationID;
	private int routeID;
	
	
	public CTAStop(){
		super();
		this.id = -1;
		this.name = "Blank Stop";
		this.stationID = -1;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public int getRouteID() {
		return routeID;
	}

	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}
	
}
