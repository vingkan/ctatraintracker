package main;

public class GPSLocation {
	
	private String name;
	private double latitude;
	private double longitude;
	
	/*
	 * Class for GPSLocation on Earth Map
	 */
	public GPSLocation(){
		this.name = "Blank Location";
		this.latitude = 0.000;
		this.longitude = 0.000;
	}
	
	public GPSLocation(String name, double latitude, double longitude){
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public GPSLocation(String name, String latitude, String longitude){
		this.name = name;
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);
	}
	
	/*
	 * Moved degreesToRadians() method to Converter class
	 */
	public double calculateDistance(GPSLocation loc){
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
	public int compareTo(GPSLocation loc){
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
		GPSLocation other = (GPSLocation) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GPSLocation: " + name + " at {" + latitude + ", " + longitude + "}";
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

}