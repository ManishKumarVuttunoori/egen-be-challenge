package egenChallenge;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
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
		
		db.getCollection("users").insertOne(Document.parse(data));
		//  calling getAllUsers---
		getAllUsers(db);
			return true;
	}
	
	public static List<User> getAllUsers(MongoDatabase db){
	List<User> userList = new LinkedList<User>();
	FindIterable<Document> iterable = db.getCollection("users").find();
	iterable.forEach(new Block<Document>() {
	    
		@Override
	    public void apply(final Document document) {
	        try {
				userList.add((User)DataParser.jsonToData("{"+document.toJson().substring(50),new User()));
			}        
	        catch(Exception e){	
	        	e.printStackTrace();
	        }
	    }
	    
	    });
		return userList;
	}	
}
