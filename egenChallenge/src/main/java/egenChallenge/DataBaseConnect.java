package egenChallenge;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
// Model class for user
public class DataBaseConnect {
	
	private  MongoClient client = new MongoClient("localhost");
	
	public DataBaseConnect(){}
	
	public  MongoDatabase getConnection(){
		// creates database with name egendb
		return client.getDatabase("egendb");   
	}
	// creates a unique user in database
	public Boolean createUser(String json){
		MongoDatabase db = getConnection();
		Document docToInsert = Document.parse(json);
		
		if (docToInsert.containsKey("id") && docToInsert.get("id")!=null)
		{
			String hashToCompare = (String) docToInsert.get("id");
			FindIterable<Document> iterable = db.getCollection("users").find(new Document("id", hashToCompare)); 
			
			if (iterable.first()!=null)
			{	// Existing user
				return false;
			}
			else{
				db.getCollection("users").insertOne(docToInsert);
				return true;
			}
		}
		else
		{	// received data is not in required Json format
			return false;
		}
	
	}
	
	public List<User> getAllUsers(){
	MongoDatabase db = getConnection();	
	List<User> userList = new LinkedList<User>();
	FindIterable<Document> iterable = db.getCollection("users").find();
	iterable.forEach(new Block<Document>() {
	    
		@Override
	    public void apply(final Document document){
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
	
	public Boolean updateUser(String json) {
		MongoDatabase db = getConnection();
		
		Document docToUpdate = Document.parse(json);
		
		Set <String> keys = docToUpdate.keySet();
		if (docToUpdate.get("id")!=null)
		{
			String hashToCompare = (String) docToUpdate.get("id");
			FindIterable<Document> iterable = db.getCollection("users").find(new Document("id", hashToCompare)); 
			Iterator<String> iter = keys.iterator();
			String init_key = "";
			
			if (iterable.first()!= null)
			{	// update the user data here
				while(iter.hasNext())
				{
					init_key = (String)iter.next();
					Document doc = new Document("$set", new Document(init_key, docToUpdate.get(init_key)));
					UpdateResult result = db.getCollection("users").updateOne(new Document("id",hashToCompare),doc);	
				}
				return true;
			}
			else{
				// send back 404 response
				return false;
			} 
		}
		else{
			// return as invalid data is sent
			return false;
		}
	}
}
