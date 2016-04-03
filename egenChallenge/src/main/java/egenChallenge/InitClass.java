package egenChallenge;

import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.*;
import com.mongodb.client.MongoDatabase;
public class InitClass {
	public static void main(String[] args) {
		// database connection
		MongoDatabase db = DataBaseConnect.getConnection();
		port(4579);
		get("/", (req, res) -> "Hello World");
		//Sample data to check if the data connection is working--
		User user1 = new User("", "Manish1", "Vuttunoori", "mmans", "1","2","3","23","123","asd","hkags","sample_url");
		 DataBaseConnect.createUser(db, DataParser.dataToJson(user1));
		 DataBaseConnect.getAllUsers(db);
		// get("/hello", (req, res) -> "Hello World");
		/* get("/hello1", new Route(){
			 @Override
			 public Object handle(Request req, Response res)
			 {
				 return "Hello World1";
			 }});  */

	}
	
	

}
