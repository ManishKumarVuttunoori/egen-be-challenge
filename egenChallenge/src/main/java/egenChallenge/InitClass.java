package egenChallenge;
import static spark.Spark.*;

public class InitClass {
	public static void main(String[] args) {
		port(4581);
		// initialize a controller
		new UserController(new DataBaseConnect());  
	}
	

}
