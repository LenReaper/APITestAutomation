package api;

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
	
	public RequestSpecification requestSpec() throws FileNotFoundException {
		
		ResourceBundle res = ResourceBundle.getBundle("OR");
		
		PrintStream stream = new PrintStream(new FileOutputStream("log.txt"));
		
		RequestSpecification req = new RequestSpecBuilder()
				.setBaseUri(res.getString("url"))
				.addQueryParam("key", res.getString("keyName"))
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.setContentType(ContentType.JSON).build();
		
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

}
