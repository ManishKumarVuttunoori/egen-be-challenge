package egenChallenge;

import static spark.Spark.get;

import spark.Request;
import spark.Response;
import spark.Route;

public class UserController {

	public UserController(DataBaseConnect db){
		/* Default page showing possible features*/
		get("/", new Route(){
			@Override
			public Object handle(Request req, Response res){
				return res.body();
			}
		});
		
		/* creates a new user if not already present in the db*/ 
		get("/createUser", new Route(){
			 @Override
			 public Object handle(Request req, Response res)
			 {
				 res.type("application/json");
				 String retVal = "";
				// use the get parameters and get the request body here...
					User user1 = new User("", "Manish12", "Vuttunoori", "mmans", "1","2","3","23","123","asd","hkags","sample_url");
					 if (db.createUser(DataParser.dataToJson(user1))){
						 // user created successfully
						 System.out.println("User created successfully!!");
						 res.status(200);
						 retVal = "User created Successfully";
					 }
					 else{
						 //already exists  or not a valid ..set error response
						 System.out.println("User not created successfully!!");
						 res.status(404);
						retVal = "Either user exists or not a valid user"; 
					 }
					 return retVal;
			 }}, new ResponseJson());  

		/* gets all the users from the db*/
		get("/getUsers", new Route(){
			 @Override
			 public Object handle(Request req, Response res)
			 {
				 res.type("application/json");
				 return db.getAllUsers();
			 }}, new ResponseJson());
		
		/* updates a existing user and returns 404 if user not found*/
		get("/updateUser", new Route(){
			 @Override
			 public Object handle(Request req, Response res)
			 {
				 String retVal="";
				 // use query parameters to get the values from the UI or url
					// convert them to Json
					User user2 = new User("", "Manish1", "Vuttunoori", "mmans", "1efres","efef2","ewfe3","ewfew23","123","asd","","sample_url");
					res.type("application/json");
					if(db.updateUser(DataParser.dataToJson(user2)))
					{
						res.status(200);
						retVal = "Update Succesful";
					}
					else
					{
						res.status(400);
						retVal = "Update unsuccesful";
					}
					return retVal;
				}
			 }, new ResponseJson());
				 
	}
}	