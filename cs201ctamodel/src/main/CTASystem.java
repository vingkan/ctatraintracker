package main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.jacquet80.minigeo.MapWindow;
import eu.jacquet80.minigeo.POI;
import eu.jacquet80.minigeo.Point;
import eu.jacquet80.minigeo.Segment;

public class CTASystem {
	
	private List<CTAStop> stops;
	private MapWindow map;
	
	public CTASystem(){
		this.stops = new ArrayList<CTAStop>();
		this.map = CTASystem.getMapWindowFromFile("chicago.poly.txt");
	}
	
	public CTAStop searchStopsByName(String query){
		CTAStop response = new CTAStop();
		for(CTAStop stop : this.stops){
			if(stop.getName().equals(query)){
				response = stop;
			}
		}
		return response;
	}
	
	private static String pattern = "^.*?(-?\\d+\\.\\d+)\\s+(-?\\d+\\.\\d+)$";
	private static Pattern POINT = Pattern.compile(pattern);
	
	public static MapWindow getMapWindowFromFile(String path){
		MapWindow map = new MapWindow();
		map.setTitle("Chicago Transit Authority: L-Trains Map");
		try{
			FileReader file = new FileReader(path);
			BufferedReader reader = new BufferedReader(file);
			String line;
			Point cursor = null;
			Point previous = null;
			while((line = reader.readLine()) != null){
				Matcher matcher = POINT.matcher(line);
				if(matcher.matches()){
					double longitude = Double.parseDouble(matcher.group(1));
					double latitude = Double.parseDouble(matcher.group(2));
					cursor = new Point(latitude, longitude);
					if(previous != null){
						map.addSegment(new Segment(previous, cursor, Color.BLACK));
					}
					previous = cursor;
				}
				else{
					System.out.println("ERROR: Could not draw map point.");
				}
			}
			reader.close();
		}
		catch(Exception e){
			System.out.println("Encountered Exception: " + e);
		}
		return map;
	}
	
	public void displayMap(){
		this.map.setVisible(true);
	}

	public void updateMapPoints(){
		//this.map.addPOI(new POI(new Point(41.9869192, -87.9398331), "Chicago"));
		for(CTAStop stop : this.stops){
			System.out.println(stop.getLatitude());
			System.out.println(stop.getLongitude());
			this.map.addPOI(new POI(stop.getPoint(), ""));
		}
	}
	
	public MapWindow getMap() {
		return map;
	}

	public void setMap(MapWindow map) {
		this.map = map;
	}

	public List<CTAStop> getStops() {
		return stops;
	}

	public void setStops(List<CTAStop> stops) {
		this.stops = stops;
	}
	
	public void addStop(CTAStop stop){
		this.stops.add(stop);
	}

}