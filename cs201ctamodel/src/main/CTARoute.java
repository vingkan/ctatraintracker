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
