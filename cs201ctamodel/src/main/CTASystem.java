package main;

import java.util.ArrayList;
import java.util.List;

public class CTASystem {
	
	private List<CTAStop> stops;
	
	public CTASystem(){
		this.stops = new ArrayList<CTAStop>();
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
