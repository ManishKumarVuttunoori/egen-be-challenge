package egenChallenge;

import lombok.Data;

@Data
public class Company {

	private String compName;
	private String compWebsite;
	
	public Company(){
		
	}
	public Company(String compName, String compWebsite){
		this.compName = compName;
		this.compWebsite = compWebsite;
	}
	
	public String getCompName(){
		return this.compName;
	}
	public String getCompWebsite(){
		return this.compWebsite;
	}
	
}
