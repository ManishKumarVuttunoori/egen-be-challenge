package egenChallenge;

public class Address {
	private String street;
	private String city;
	private String zip;
	private String state;
	private String country;

	public Address(){
		
	}
	public Address(String street, String city, String zip,String state, String country){
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.country = country;
	}
	public String getStreet(){
		return this.street;
	}
	public String getCity(){
		return this.city;
	}
	public String getState(){
		return this.state;
	}
	public String getZip(){
		return this.zip;
	}
	public String getCountry(){
		return this.country;
	}
}
