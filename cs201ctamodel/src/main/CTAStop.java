package main;

import javax.swing.JOptionPane;

public class CTAStop extends Location {

	private int id; //id of -1 indicates null
	private boolean wheelChairAccessible;
	private boolean opened;
	private int westboundStationID;
	private int southboundStationID;
	
	/*
	 * Class for CTAStop, child of GPS Location
	 */
	public CTAStop(){
		/*this.name = "Blank CTA Station";
		this.latitude = 0.000;
		this.longitude = 0.000;*/
		super();
		this.id = -1;
		this.westboundStationID = -1;
		this.southboundStationID = -1;
		this.wheelChairAccessible = false;
		this.opened = false;
	}
	
	public CTAStop(String[] dataArray){
		/*this.name = dataArray[1];
		this.latitude = Double.parseDouble(dataArray[2]);
		this.longitude = Double.parseDouble(dataArray[3]);*/
		super(dataArray[1], Double.parseDouble(dataArray[2]), Double.parseDouble(dataArray[3]));
		this.id = Integer.parseInt(dataArray[0]);
		this.westboundStationID = Integer.parseInt(dataArray[5]);
		this.southboundStationID = Integer.parseInt(dataArray[6]);
		this.wheelChairAccessible = Boolean.parseBoolean(dataArray[7]);
		this.opened = true;
	}
	
	public CTAStop(String name, double latitude, double longitude, boolean wheelChair, boolean open){
		super(name, latitude, longitude);
		this.wheelChairAccessible = wheelChair;
		this.opened = open;
	}
	
	public static CTAStop createUserCTAStop(){
		String[] dataArray = new String[8];
		dataArray[0] = "-1"; //Hooray for defaults
		dataArray[1] = JOptionPane.showInputDialog("Enter the stop name.");
		dataArray[2] = JOptionPane.showInputDialog("Enter the latitude coordinate.");
		dataArray[3] = JOptionPane.showInputDialog("Enter the longitude coordinate.");
		dataArray[4] = "elevated";
		dataArray[5] = "-1"; //Not updating these values until the next project... if there is one.
		dataArray[6] = "-1"; //I know that data integrity is important...
		dataArray[7] = Converter.getAcceptableStrings("Is the stop wheelchair accessible?", Converter.getBooleanOptions(), false).toLowerCase();
		return new CTAStop(dataArray);
	}

	public int compareTo(CTAStop stop){
		int comparison = 0;
		comparison = this.getId() - stop.getId();
		return comparison;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isWheelChairAccessible() {
		return wheelChairAccessible;
	}

	public void setWheelChairAccessible(boolean wheelChairAccessible) {
		this.wheelChairAccessible = wheelChairAccessible;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public int getWestboundStationID() {
		return westboundStationID;
	}

	public void setWestboundStationID(int westboundStationID) {
		this.westboundStationID = westboundStationID;
	}

	public int getSouthboundStationID() {
		return southboundStationID;
	}

	public void setSouthboundStationID(int southboundStationID) {
		this.southboundStationID = southboundStationID;
	}
	
}