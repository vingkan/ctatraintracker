package main;

import java.util.LinkedList;
import java.util.List;

public class CTARoute {

	private RouteType type;
	private List<CTAStop> path;
	
	public CTARoute(){
		this.type = RouteType.CUSTOM;
		this.path = new LinkedList<CTAStop>();
	}
	
	public CTARoute(RouteType type){
		this.type = type;
		this.path = new LinkedList<CTAStop>();
	}
	
	

	@Override
	public String toString() {
		String response = "CTA Route: " + this.type.getName() + "\n";
		response += "---------------------------------" + "\n";
		for(int s = 0; s < this.path.size(); s++){
			CTAStop stop = this.path.get(s);
			response += s + ". " + stop.toString();
		}
		response += "\n" + "---------------------------------";
		return response;
	}

	public RouteType getType() {
		return type;
	}

	public void setType(RouteType type) {
		this.type = type;
	}

	public List<CTAStop> getPath() {
		return path;
	}

	public void setPath(List<CTAStop> path) {
		this.path = path;
	}
	
}
