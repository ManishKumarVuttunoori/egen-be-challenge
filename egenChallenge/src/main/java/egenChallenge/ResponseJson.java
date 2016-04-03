package egenChallenge;

import spark.ResponseTransformer;

public class ResponseJson implements ResponseTransformer {
	
	@Override
	public String render(Object obj)
	{
		return DataParser.dataToJson(obj);
	}

}
