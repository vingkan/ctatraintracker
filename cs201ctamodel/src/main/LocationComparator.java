package main;

import java.util.Comparator;

/*
 * Comparator to sort a list of GPS Locations based on their proximity to a given destination
 * Destination should be provided on instantiation
 */
public class LocationComparator implements Comparator<Location>{
	
	private Location destination;
	
	public LocationComparator(){
		this.destination = new Location();
	}
	
	public LocationComparator(Location destination){
		this.destination = destination;
	}
	
	public int compare(Location loc1, Location loc2){
		int comparison = 0;
		double distance1 = this.destination.calculateDistance(loc1);
		double distance2 = this.destination.calculateDistance(loc2);
		double difference = (distance1 - distance2);
		if(difference < 0.0){
			comparison = -1;
		}
		else if(difference > 0.0){
			comparison = 1;
		}
		return comparison;
	}
	
}
