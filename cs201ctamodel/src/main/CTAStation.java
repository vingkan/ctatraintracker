package main;

import java.util.ArrayList;
import java.util.List;

/*
 * Station that holds stops in the CTA
 * Not actually used in this program kek
 */
public class CTAStation {

	private int id;
	private String name;
	private List<CTAStop> stops;
	
	public CTAStation(int id, String name){
		this.id = id;
		this.name = name;
		this.stops = new ArrayList<CTAStop>();
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

	public List<CTAStop> getStops() {
		return stops;
	}

	public void setStops(List<CTAStop> stops) {
		this.stops = stops;
	}
	
}
