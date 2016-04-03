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
	private String dateCreated;
	private Company company;
	private String profilePic;
	
	public User(){}
	
	public User(String id, String firstName, String lastName, String email, String street, String city, String zip, String state, String country, String compName, String compWebsite, String profilePic)
	{ 	
		this.id = firstName+lastName+email;
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(this.id.getBytes());
			this.id = new String(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setAddress(new Address(street, city, zip, state, country));
		this.setDateCreated(new Date().toString());
		this.setCompany(new Company(compName, compWebsite));
		this.setProfilePic(profilePic);		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
}