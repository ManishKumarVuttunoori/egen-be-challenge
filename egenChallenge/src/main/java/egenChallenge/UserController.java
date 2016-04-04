package egenChallenge;

import static spark.Spark.*;

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
		post("/createUser", new Route(){
			 @Override
			 public Object handle(Request req, Response res)
			 {
				 
				 String firstName = req.queryParams("firstName");
				 String lastName = req.queryParams("lastName");
				 String email = req.queryParams("email");
				 String city = req.queryParams("city");
				 String state = req.queryParams("state");
				 String zip = req.queryParams("zip");
				 String country = req.queryParams("country");
				 String profilePic = req.queryParams("profilePic");
				 String street = req.queryParams("street");
				 String compName = req.queryParams("compName");
				 String compWebsite = req.queryParams("compWebsite");
				 String retVal = "";
				// use the get parameters and get the request body here...
					User user = new User("",firstName,lastName,email,street,city,zip,state,country,compName,compWebsite,profilePic);
					 if (db.createUser(DataParser.dataToJson(user))){
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
					 res.type("application/json");
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
		put("/updateUser", new Route(){
			 @Override
			 public Object handle(Request req, Response res)
			 {	
				 String firstName = req.queryParams("firstName");
				 String lastName = req.queryParams("lastName");
				 String email = req.queryParams("email");
				 String city = req.queryParams("city");
				 String state = req.queryParams("state");
				 String zip = req.queryParams("zip");
				 String country = req.queryParams("country");
				 String profilePic = req.queryParams("profilePic");
				 String street = req.queryParams("street");
				 String compName = req.queryParams("compName");
				 String compWebsite = req.queryParams("compWebsite");
				 String retVal = "";
				// use the get parameters and get the request body here...
					User user = new User("",firstName,lastName,email,street,city,zip,state,country,compName,compWebsite,profilePic);
				 // use query parameters to get the values from the UI or url
					// convert them to Json
				
					res.type("application/json");
					if(db.updateUser(DataParser.dataToJson(user)))
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