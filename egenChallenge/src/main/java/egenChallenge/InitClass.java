package egenChallenge;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.*;
import org.bson.Document;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.client.MongoDatabase;

public class InitClass {
	public static void main(String[] args) {
		// database connection
		MongoDatabase db = DataBaseConnect.getConnection();
		//port(4577);
		get("/", (req, res) -> "Hello World");
		//Sample data to check if the data connection is working--
		User user1 = new User("", "Manish1", "Vuttunoori", "mmans", "1","2","3","23","123","asd","hkags","sample_url");
		 DataBaseConnect.createUser(db, dataToJson(user1));
		 DataBaseConnect.getAllUsers(db);
		// get("/hello", (req, res) -> "Hello World");
		/* get("/hello1", new Route(){
			 @Override
			 public Object handle(Request req, Response res)
			 {
				 return "Hello World1";
			 }});  */

	}
	
	public static String dataToJson(Object data)
	{
		System.out.println("1"+data.toString());
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		System.out.println("2"+ow.toString());
		 try {
			 String temp = new ObjectMapper().writeValueAsString(data);
			 System.out.println("2.5"+temp);
			String json = ow.writeValueAsString(data);
			System.out.println("3"+json);
			//System.out.println(json+"hello");
			//return json;
			return temp;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}

}
