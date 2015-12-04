package main;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.jacquet80.minigeo.MapWindow;
import eu.jacquet80.minigeo.POI;
import eu.jacquet80.minigeo.Point;
import eu.jacquet80.minigeo.Segment;

public class CTASystem {
	
	private static final String CHICAGO_BOUNDS = "chicago.poly.txt";
	
	private List<CTAStop> stops;
	private List<CTARoute> routes;
	private MapWindow map;
	
	public CTASystem(){
		this.stops = new ArrayList<CTAStop>();
		this.routes = new ArrayList<CTARoute>();
		this.map = CTASystem.getMapWindowFromFile(CHICAGO_BOUNDS);
	}
	
	public CTASystem(String[] path){
		this.stops = new ArrayList<CTAStop>();
		this.routes = new ArrayList<CTARoute>();
		this.map = CTASystem.getMapWindowFromFile(CHICAGO_BOUNDS);
		//Read from files to populate system
		File stopsFile = new File(path[0]);
		File routesFile = new File(path[1]);
		try{
			Scanner scan = new Scanner(stopsFile);
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				this.addStop(new CTAStop(line));
			}
			scan.close();
			Scanner mapper = new Scanner(routesFile);
			while(mapper.hasNextLine()){
				String line = mapper.nextLine();
				this.addRoute(line);	
			}
			mapper.close();
		}
		catch(Exception e){
			System.out.println("Encountered Exception: " + e);
		}	
	}
	
	public CTAStop getStopByID(int idQuery){
		CTAStop response = new CTAStop();
		for(CTAStop stop : this.stops){
			if(stop.getID() == idQuery){
				response = stop;
			}
		}
		return response;
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

	public List<CTAStop> getStops() {
		return stops;
	}

	public void setStops(List<CTAStop> stops) {
		this.stops = stops;
	}
	
	public void addStop(CTAStop stop, boolean refresh){
		this.stops.add(stop);
		if(refresh){
			this.refreshMap();
		}
	}
	
	public void addStop(CTAStop stop){
		this.addStop(stop, false);
	}
	
	public void removeStop(int stopID, boolean refresh){
		CTAStop target = this.getStopByID(stopID);
		for(CTARoute route : this.routes){
			int index = route.containsStop(target);
			if(index > -1){
				route.removeStop(target);
			}
		}
		this.stops.remove(target);
		System.out.println("Removed: " + target);
		if(refresh){
			this.refreshMap();
		}
	}
	
	public void removeStop(int stopID){
		removeStop(stopID, false);
	}

	public List<CTARoute> getRoutes() {
		return routes;
	}

	public void setRoutes(List<CTARoute> routes) {
		this.routes = routes;
	}
	
	public void addRoute(CTARoute route){
		this.routes.add(route);
	}
	
	public void addRoute(String train, RouteType type, int[] stopIDs){
		this.routes.add(new CTARoute(train, type, this, stopIDs));
	}
	
	public void addRoute(String line){
		String[] data = line.split(",");
		int[] stopIDs = new int[data.length-2];
		for(int d = 0; d < data.length-2; d++){
			stopIDs[d] = Integer.parseInt(data[d+2]);
		}
		this.addRoute(data[0], RouteType.getTypeByName(data[1]), stopIDs);
	}
	
	public MapWindow getMap() {
		return map;
	}

	public void setMap(MapWindow map) {
		this.map = map;
	}
	
	public void toggleMap(boolean visible){
		this.map.setVisible(visible);
	}
	
	public void refreshMap(){
		System.out.println("Refresh");
		toggleMap(false);
		this.map = getMapWindowFromFile(CHICAGO_BOUNDS);
		drawMapPoints();
		drawMapRoutes();
		toggleMap(true);
	}
	
	/*
	 * Creates MapWindow with outline of the City of Chicago from the .poly file
	 * Based on: https://code.google.com/p/minigeo/source/browse/trunk/MiniGeo/src/MinigeoDemo.java
	 * Generated .poly file at http://polygons.openstreetmap.fr/
	 * Relation for .poly file: Chicago (122604)
	 */
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
	
	public void drawMapPoints(){
		//this.map.addPOI(new POI(new Point(41.9869192, -87.9398331), "Chicago"));
		for(CTAStop stop : this.stops){
			this.map.addPOI(new POI(stop.getPoint(), ""));
		}
	}
	
	public void drawMapRoutes(){
		for(CTARoute route : this.routes){
			List<CTAStop> stops = route.getPath();
			Point previous = stops.get(0).getPoint();
			Point cursor = null;
			for(int s = 1; s < stops.size(); s++){
				cursor = stops.get(s).getPoint();
				map.addSegment(new Segment(previous, cursor, route.getColor()));
				previous = cursor;
			}
		}
	}
	
	public void setMenuCallback(Menu menu){
		this.map.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				e.getWindow().dispose();
				menu.displayOptions();
			}
		});
	}
	
	public void spotLocation(Location location, Menu menu){
		refreshMap();
		this.map.addPOI(new POI(location.getPoint(), location.getName()));
		setMenuCallback(menu);
		toggleMap(true);
	}

}