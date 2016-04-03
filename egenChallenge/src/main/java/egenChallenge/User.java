package egenChallenge;
import java.util.Date;
import lombok.Data;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Data
public class User {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private Address address;
	String dateCreated;
	Company company;
	String profilePic;
	
	public User(){}
	
	public User(String id, String firstName, String lastName, String email, String street, String city, String zip, String state, String country, String compName, String compWebsite, String profilePic)
	{ 	
		this.id = id;
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(this.id.getBytes());
			this.id = new String(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = new Address(street, city, zip, state, country);
		this.dateCreated =new Date().toString();
		this.company = new Company(compName, compWebsite);
		this.profilePic = profilePic;
		
		
	}
	
	public String getId(){
		return this.id;
	}
	public String getfirstName(){
		return this.firstName;
	}
	public String getlastName(){
		return this.lastName;
	}
	public String getEmail(){
		return this.email;
	}
	public Address getAddress(){
		return this.address;
	}
	public Company getCompany(){
		return this.company;
	}
	public String getProfilePic(){
		return this.profilePic;
	}
}
