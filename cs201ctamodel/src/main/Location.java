package main;

import eu.jacquet80.minigeo.Point;

public class Location {
	
	private String name;
	private double latitude;
	private double longitude;
	
	/*
	 * Class for GPSLocation on Earth Map
	 */
	public Location(){
		this.name = "Blank Location";
		this.latitude = 0.000;
		this.longitude = 0.000;
	}
	
	public Location(String name, double latitude, double longitude){
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Location(String name, String latitude, String longitude){
		this.name = name;
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);
	}
	
	public Location(String name, String coords){
		this.name = name;
		Double[] pair = Location.pairToCoordinates(coords);
		this.latitude = pair[0];
		this.longitude = pair[1];
	}
	
	public Location(String line, int nameIndex, int coordsIndex){
		String[] data = line.split("\\|");
		this.name = data[nameIndex];
		String coords = data[coordsIndex];
		Double[] pair = Location.pairToCoordinates(coords);
		this.latitude = pair[0];
		this.longitude = pair[1];
	}
	
	public static Double[] pairToCoordinates(String coords){
		coords = coords.replaceAll("[()]", "");
		String[] data = coords.split(",");
		Double[] pair = {Double.parseDouble(data[0]), Double.parseDouble(data[1])};
		return pair;
	}
	
	/*
	 * Moved degreesToRadians() method to Converter class
	 */
	public double calculateDistance(Location loc){
		/*double x = 69.1 * (loc.getLatitude() - this.getLatitude());
		double y = 69.1 * (loc.getLongitude() - this.getLongitude()) * Math.cos(this.getLatitude() / 57.3);
		double distance = Math.sqrt((x * x) + (y * y));
		return distance;*/
		double EARTH_RADIUS = 6371.0;
		double dLat = Converter.degreesToRadians(loc.getLatitude() - this.getLatitude());
		double dLon = Converter.degreesToRadians(loc.getLongitude() - this.getLongitude());
		double a = (Math.sin(dLat / 2) * Math.sin(dLat / 2)) +
					(Math.cos(Converter.degreesToRadians(this.getLatitude())) *
					Math.cos(Converter.degreesToRadians(loc.getLatitude())) * 
					Math.sin(dLon / 2) * Math.sin(dLon / 2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = EARTH_RADIUS * c; //Value in Kilometers
		return distance;
	}
	
	/*
	 * Alternate: double, double calculation method that I don't really want to use
	 */
	public double calculateDistance(double lat, double lon){
		/*double x = 69.1 * (loc.getLatitude() - this.getLatitude());
		double y = 69.1 * (loc.getLongitude() - this.getLongitude()) * Math.cos(this.getLatitude() / 57.3);
		double distance = Math.sqrt((x * x) + (y * y));
		return distance;*/
		double EARTH_RADIUS = 6371.0;
		double dLat = Converter.degreesToRadians(lat - this.getLatitude());
		double dLon = Converter.degreesToRadians(lon - this.getLongitude());
		double a = (Math.sin(dLat / 2) * Math.sin(dLat / 2)) +
					(Math.cos(Converter.degreesToRadians(this.getLatitude())) *
					Math.cos(Converter.degreesToRadians(lat)) * 
					Math.sin(dLon / 2) * Math.sin(dLon / 2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = EARTH_RADIUS * c; //Value in Kilometers
		return distance;
	}	
	public int compareTo(Location loc){
		int comparison = 0;
		return comparison;
	}
	
	@Override
	//Generated by Eclipse
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location: " + name + " at {" + latitude + ", " + longitude + "}";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public Point getPoint(){
		Point point = new Point(this.latitude, this.longitude);
		return point;
	}

}