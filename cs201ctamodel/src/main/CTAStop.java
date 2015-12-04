package main;

import java.util.ArrayList;
import java.util.List;

import eu.jacquet80.minigeo.Point;

/*
 * Model of a stop in the CTA
 */
public class CTAStop extends Location{

	private int id;
	private int stationID;
	private List<RouteType> routes;
	
	/*
	 * Default constructor
	 */
	public CTAStop(){
		super();
		this.id = -1;
		this.stationID = -1;
		this.routes = new ArrayList<RouteType>();
	}
	
	/*
	 * For the purpose of wrapping an ID in a CTAStop object for searching.
	 */
	public CTAStop(int stopID){
		super();
		this.id = stopID;
		this.stationID = -1;
		this.routes = new ArrayList<RouteType>();
	}
	
	/*
	 * Main constructor
	 */
	public CTAStop(String name, Double lat, Double lon, RouteType type){
		super(name, lat, lon);
		this.routes = new ArrayList<RouteType>();
		this.routes.add(type);
	}
	
	/*
	 * nextLine constructor
	 */
	public CTAStop(String line){
		super(line, 2, 16);
		String[] data = line.split("\\|");
		this.id = Integer.parseInt(data[0]);
		this.stationID = Integer.parseInt(data[5]);
		this.routes = new ArrayList<RouteType>();
		this.getRoutes().addAll(CTAStop.pipesToLines(line));
	}
	
	/*
	 * Retrieves the routes the stop belongs to from a pipe-separated values file
	 */
	public static List<RouteType> pipesToLines(String line){
		List<RouteType> lines = new ArrayList<RouteType>();
		String[] data = line.split("\\|");
		RouteType[] key = {
			RouteType.RED_LINE,
			RouteType.BLUE_LINE,
			RouteType.GREEN_LINE,
			RouteType.BROWN_LINE,
			RouteType.PURPLE_LINE,
			RouteType.PURPLE_EXPRESS,
			RouteType.YELLOW_LINE,
			RouteType.PINK_LINE,
			RouteType.ORANGE_LINE
		};
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
		for(RouteType line : this.routes){
			response += "\n - " + line.getName();
		}
		return response;	
	}

	/*
	 * Returns a point object for use on the map window
	 * (non-Javadoc)
	 * @see main.Location#getPoint()
	 */
	@Override
	public Point getPoint(){
		//Color color = this.routes.get(0).getColor();
		Point point = new Point(this.getLatitude(), this.getLongitude());
		return point;
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

	public List<RouteType> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteType> routes) {
		this.routes = routes;
	}
	
}
