package egenChallenge;

import spark.ResponseTransformer;
// Response Transformer implementation for Spark routes
public class ResponseJson implements ResponseTransformer {
	
	@Override
	public String render(Object obj)
	{
		return DataParser.dataToJson(obj);
	}

}
