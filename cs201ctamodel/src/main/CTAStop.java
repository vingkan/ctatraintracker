package main;

public class CTAStop extends Location{

	private int id;
	private String name;
	
	public CTAStop(){
		super();
		this.id = 0;
		this.name = "Blank Stop";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
