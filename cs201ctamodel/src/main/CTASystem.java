package main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.jacquet80.minigeo.MapWindow;
import eu.jacquet80.minigeo.Point;
import eu.jacquet80.minigeo.Segment;

public class CTASystem {
	
	private List<CTAStop> stops;
	
	public CTASystem(){
		this.stops = new ArrayList<CTAStop>();
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
		try{
			FileReader file = new FileReader(path);
			BufferedReader reader = new BufferedReader(file);
			String line;
			Point cursor = null;
			Point previous = null;
			int readCount = 0;
			int errorCount = 0;
			while((line = reader.readLine()) != null){
				readCount++;
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
					errorCount++;
				}
			}
			reader.close();
			System.out.println("Read " + readCount + " with " + errorCount + " errors.");
		}
		catch(Exception e){
			System.out.println("Encountered Exception: " + e);
		}
		return map;
	}
	
	public void displayMap(){
		MapWindow map = CTASystem.getMapWindowFromFile("chicago.poly.txt");
		map.setVisible(true);
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
