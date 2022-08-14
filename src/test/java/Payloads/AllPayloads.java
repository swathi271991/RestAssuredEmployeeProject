package Payloads;

import java.util.random.RandomGenerator;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AllPayloads {

	public static String addBookPayload(String isbnval,int aisle)
	{
		
		return "{\r\n" + 
				"\r\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
				"\"isbn\":\"bcd"+isbnval+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}\r\n" ;
	}
	
	public static String deletePayLoad(String id)
	{
		return "{\r\n" + 
				" \r\n" + 
				"\"ID\" : \""+id+"\"\r\n" + 
				" \r\n" + 
				"} \r\n" + 
				"";
	}
	
	public static JsonPath rawtoJSON(Response response)
	{
		JsonPath js = new JsonPath(response.body().asString());
		return js;
	}

	public static String addBookPayload(String isbnval, String aisle) {
		// TODO Auto-generated method stub
		return "{\r\n" + 
		"\r\n" + 
		"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
		"\"isbn\":\"bcd"+isbnval+"\",\r\n" + 
		"\"aisle\":\""+aisle+"\",\r\n" + 
		"\"author\":\"John foe\"\r\n" + 
		"}\r\n" ;
	}
	
}
