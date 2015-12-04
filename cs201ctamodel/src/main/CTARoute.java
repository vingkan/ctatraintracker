package main;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class CTARoute {

	private String train;
	private RouteType type;
	private List<CTAStop> path;
	
	public CTARoute(){
		this.train = "Not In Service";
		this.type = RouteType.CUSTOM;
		this.path = new LinkedList<CTAStop>();
	}
	
	public CTARoute(RouteType type){
		this.train = type.getName();
		this.type = type;
		this.path = new LinkedList<CTAStop>();
	}
	
	public CTARoute(String train, RouteType type, CTASystem cta, int[] stopIDs){
		this.train = train;
		this.type = type;
		this.path = new LinkedList<CTAStop>();
		for(Integer stopID : stopIDs){
			this.path.add(cta.getStopByID(stopID));
		}
	}
	
	public int containsStop(CTAStop stop){
		int index = -1;
		for(int s = 0; s < this.path.size(); s++){
			if(this.path.get(s).getID() == stop.getID()){
				index = s;
			}
		}
		return index;
	}
	
	public void removeStop(CTAStop stop){
		this.path.remove(stop);
	}

	@Override
	public String toString() {
		return this.getName() + ": " + this.getTrain();
		//Long toString() for Console Viewing:
		/*String response = "CTA Route: " + this.type.getName() + "\n";
		response += "---------------------------------" + "\n";
		for(int s = 0; s < this.path.size(); s++){
			CTAStop stop = this.path.get(s);
			response += s + ". " + stop.toString() + "\n";
		}
		response += "---------------------------------";
		return response;*/
	}

	public String getTrain() {
		return train;
	}

	public void setTrain(String train) {
		this.train = train;
	}

	public RouteType getType() {
		return type;
	}

	public void setType(RouteType type) {
		this.type = type;
	}
	
	public String getName(){
		return this.type.getName();
	}
	
	public Color getColor(){
		return this.type.getColor();
	}

	public List<CTAStop> getPath() {
		return path;
	}

	public void setPath(List<CTAStop> path) {
		this.path = path;
	}
	
}
