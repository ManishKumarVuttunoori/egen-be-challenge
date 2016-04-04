package egenChallenge;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spark.Spark;
import spark.utils.IOUtils;

public class TestApplication {

	@BeforeClass
	public static void beforeClass(){
		//Main.main(null);
	}
	@AfterClass
	public static void afterClass()
	{
		Spark.stop();
	}
	@Test
	public void newUserCreated(){
		ResponseClass res = request("GET", "/createUser?firstName=johnny23&lastName=Vuttunoori&email=john@foobar.com&street=test&city=Bloomington&state=IN&country=USA&compName=Egen&compSite=egen.com&profilePic=www.google.com&zip=47408");
		assertEquals(200, res.status);
		assertEquals("\"User created Successfully\"", res.data);
		
	}
	@Test
	public void newUserCreatedwithFewParam(){
		ResponseClass res = request("GET", "/createUser?firstName=johnny24&lastName=Vuttunoori&email=john@foobar.com&street=test&city=Bloomington&state=IN&country=USA&compName=Egen&profilePic=www.google.com");
		assertEquals(200, res.status);
		assertEquals("\"User created Successfully\"", res.data);
	}	
	@Test
	//TODO gives proper response when tried manually from a browser??
	public void duplicateUserCreated(){
		ResponseClass res = request("GET", "/createUser?firstName=johnny24&lastName=Vuttunoori&email=john@foobar.com&street=test&city=Bloomington&state=IN&country=USA&compName=Egen&profilePic=www.google.com");
		assertEquals(404, res.status);
		assertEquals("\"Either user exists or not a valid user\"", res.data);
	}
	@Test
	public void updateExistingUser(){
		ResponseClass res = request("GET", "/updateUser?firstName=johnny24&lastName=Vuttunoori&email=john@foobar.com&street=test&city=Bloomington1&state=IN&country=USA&compName=Egen&profilePic=www.google.com");
		assertEquals(200, res.status);
		assertEquals( "\"Update Succesful\"",res.data);
	}
	@Test
	public void updateNonExistingUser(){
		ResponseClass res = request("GET", "/updateUser?firstName=Manish&lastName=Vuttunoori&email=john@foobar.com&street=test&city=Bloomington1&state=IN&country=USA&compName=Egen&profilePic=www.google.com");
		assertEquals(404, res.status);
		assertEquals( "\"Update unsuccesful\"",res.data);
	}
	@Test
	public void createInvalidUser(){
		ResponseClass res = request("GET", "/createUser?street=test&city=Bloomington&state=IN&country=USA&compName=Egen&profilePic=www.google.com");
		assertEquals(404, res.status);
		assertEquals("\"Either user exists or not a valid user\"", res.data);
	}
	
	@Test
	public void getCreatedUsers(){
		ResponseClass res = request("GET", "/getUsers");
		assertEquals(200, res.status);
		User[] data;
		try {
			data = res.output();
			//atleast one user must exist after performing above test cases
			assertNotNull(data[0].getId());
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	private ResponseClass request(String method, String path) {
		try {
			URL url = new URL("http://localhost:4592" + path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setDoOutput(true);
			connection.connect();
			String body = IOUtils.toString(connection.getInputStream());
			return new ResponseClass(connection.getResponseCode(), body);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Sending Request failed due to an error: " + e.getMessage());
			return null;
		}
	}
	private static class ResponseClass{
		public final String data;
		public final int status;
		
		public ResponseClass(int status, String data)
		{
			this.status = status;
			this.data = data;
		}
		public User[] output() throws ClassNotFoundException{
			return (User[])DataParser.jsonToDataArray(data,User[].class);
		}
	}
}
