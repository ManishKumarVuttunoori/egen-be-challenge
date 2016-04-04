package egenChallenge;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class DataParser {
	public DataParser(){}
	// converts given object to Json
	public static String dataToJson(Object data)
	{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		 try {
			 String json = ow.writeValueAsString(data);
			 return json;
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return null;
		
	}
	// helper method to return Object from Json format when object is given as input
	public static Object jsonToData(String json, Object obj){
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			return mapper.readValue(json, obj.getClass());
		} catch (JsonParseException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	// Second helper method to return Objects from Json format when class given as string 
	public static Object jsonToDataArray(String json, Class obj) throws ClassNotFoundException{
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, obj);
		} catch (JsonParseException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
}
