package egenChallenge;
import static spark.Spark.*;

public class InitClass {
	public static void main(String[] args) {
		port(4592);
		// initialize a controller
		new UserController(new DataBaseConnect());  
	}
	

}
