package egenChallenge;
import static spark.Spark.*;
// init contoller
public class InitClass {
	public static void main(String[] args) {
		port(4592);
		// initialize a controller
		new UserController(new DataBaseConnect());  
	}
}
