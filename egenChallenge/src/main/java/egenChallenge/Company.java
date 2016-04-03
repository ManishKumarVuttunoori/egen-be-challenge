package egenChallenge;

import lombok.Data;

@Data
public class Company {

	private String compName;
	private String compWebsite;
	
	public Company(){
		
	}
	public Company(String compName, String compWebsite){
		this.setCompName(compName);
		this.setCompWebsite(compWebsite);
	}
	public String getCompWebsite() {
		return compWebsite;
	}
	public void setCompWebsite(String compWebsite) {
		this.compWebsite = compWebsite;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	
	
	
}
