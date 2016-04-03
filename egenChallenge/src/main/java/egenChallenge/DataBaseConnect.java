package egenChallenge;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class DataBaseConnect {
	
	private  static MongoClient client = new MongoClient("localhost");
	
	private DataBaseConnect(){}
	
	public static MongoDatabase getConnection(){
		// creates database with name egendb
		return client.getDatabase("egendb");   
	}
	
	public static Boolean createUser(MongoDatabase db,String data){
		System.out.println(db+data);
		db.getCollection("test1").insertOne(Document.parse(data));
		//  calling getAllUsers---
		getAllUsers(db);
			return true;
	}
	
	public static List<User> getAllUsers(MongoDatabase db){
	List<User> userList = new LinkedList<User>();
	ObjectMapper mapper = new ObjectMapper();
	FindIterable<Document> iterable = db.getCollection("test1").find();
	iterable.forEach(new Block<Document>() {
	    
		@Override
	    public void apply(final Document document) {
	        try {
	        	System.out.println(document.toJson());
	        	System.out.println("{"+document.toJson().substring(50));
				userList.add(mapper.readValue("{"+document.toJson().substring(50),User.class));
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    });
		System.out.println(userList);
		return userList;
	}
	

}
