package main;

import java.util.ArrayList;
import java.util.List;

public class CTAStop extends Location{

	private int id;
	private int stationID;
	private List<String> routes;
	
	
	public CTAStop(){
		super();
		this.id = -1;
		this.stationID = -1;
		this.routes = new ArrayList<String>();
	}
	
	public CTAStop(String line){
		super(line, 1, 16);
		String[] data = line.split("\\|");
		this.id = Integer.parseInt(data[0]);
		this.stationID = Integer.parseInt(data[5]);
		this.routes = new ArrayList<String>();
		this.getRoutes().addAll(CTAStop.pipesToLines(line));
	}
	
	public static List<String> pipesToLines(String line){
		List<String> lines = new ArrayList<String>();
		String[] data = line.split("\\|");
		String[] key = {"Red Line", "Blue Line", "Green Line", "Brown Line", "Purple Line", "Purple Express", "Yellow Line", "Pink Line", "Orange Line"};
		for(int i = 0; i < (16-8); i++){
			if(data[i+7].equals("TRUE")){
				lines.add(key[i]);
			}
		}
		return lines;
	}

	@Override
	public String toString() {
		String response = "CTA Stop " + this.id + "\n";
		response += super.toString() + "\n";
		response += "Station " + this.stationID + " Serves Lines:";
		for(String line : this.routes){
			response += "\n" + line;
		}
		return response;
				
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public List<String> getRoutes() {
		return routes;
	}

	public void setRoutes(List<String> routes) {
		this.routes = routes;
	}
	
}
