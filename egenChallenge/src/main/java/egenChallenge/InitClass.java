package egenChallenge;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;
public class InitClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 get("/hello", (req, res) -> "Hello World");
		 get("/hello1", new Route(){
			 @Override
			 public Object handle(Request req, Response res)
			 {
				 return "Hello World1";
			 }});

	}
}
