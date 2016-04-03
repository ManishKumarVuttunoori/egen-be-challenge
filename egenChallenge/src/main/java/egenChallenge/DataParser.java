package egenChallenge;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class DataParser {
	public DataParser(){}
	public static String dataToJson(Object data)
	{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		 try {
			 String json = ow.writeValueAsString(data);
			 return json;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static Object jsonToData(String json, Object obj){
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			return mapper.readValue(json, obj.getClass());
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
		return null;
	}
}
