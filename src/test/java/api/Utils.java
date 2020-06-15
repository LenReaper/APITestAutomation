package api;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ResourceBundle;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification req;
	
	public RequestSpecification requestSpec() throws FileNotFoundException {
		
		ResourceBundle res = ResourceBundle.getBundle("OR");

		if(req==null)
		{
			PrintStream stream = new PrintStream(new FileOutputStream("log.txt"));
			req = new RequestSpecBuilder()
				.setBaseUri(res.getString("url"))
				.addQueryParam("key", res.getString("keyName"))
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.setContentType(ContentType.JSON).build();
		
			return req;
		}
		
			return req;
	}
	
	public ResponseSpecification responseSpec() {
		
		ResponseSpecification response =  new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		return response;
	}
	
	public String parseJsonToString(String response, String variable) {
		JsonPath js = new JsonPath(response);
		return js.getString(variable);
		
	}
	
	public String getPlaceResponse(String placeId) throws FileNotFoundException {
		
		ResourceBundle res = ResourceBundle.getBundle("OR");
		
		String getResponse = given().spec(requestSpec()).queryParam("place_id", placeId)
				.when().get(res.getString("getResource"))
				.then().spec(responseSpec()).extract().asString();
		
		return getResponse;
	}

}
