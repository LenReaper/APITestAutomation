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
	
	static PrintStream print;
	public static RequestSpecification req;
	RequestSpecification request;
	RequestSpecification getAccessRequest;
	RequestSpecification allCourseRequest;
	ResourceBundle res = ResourceBundle.getBundle("OR"); 
	
	public RequestSpecification requestSpec() throws FileNotFoundException {

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
	
	
	public RequestSpecification requestOAuth() throws FileNotFoundException {
		
		if(request==null)
		{
		print = new PrintStream(new FileOutputStream("oauth.txt"));
		
		request = new RequestSpecBuilder()
				.setBaseUri(res.getString("auth_url"))
				.addQueryParam("scope", res.getString("scope"))
				.addQueryParam("auth_url", res.getString("auth_url"))
				.addQueryParam("client_id", res.getString("client_id"))
				.addQueryParam("response_type", res.getString("response_type"))
				.addQueryParam("redirect_uri", res.getString("redirect_uri"))
				.addFilter(RequestLoggingFilter.logRequestTo(print))
				.addFilter(ResponseLoggingFilter.logResponseTo(print))
				.build();
		
		return request;
		}
		return request;
	}
	
	
	public RequestSpecification getAccessTokenReq(String code) {
		 
		if(getAccessRequest==null)
		{
			getAccessRequest = new RequestSpecBuilder()
					.setBaseUri(res.getString("accessTokenURL"))
					.addQueryParam("code", code)
					.addQueryParam("client_id", res.getString("client_id"))
					.addQueryParam("client_secret",res.getString("client_secret"))
					.addQueryParam("redirect_uri", res.getString("redirect_uri"))
					.addQueryParam("grant_type", res.getString("grant_type"))
					.addFilter(RequestLoggingFilter.logRequestTo(print))
					.addFilter(ResponseLoggingFilter.logResponseTo(print))
					.setContentType(ContentType.JSON)
					.build();
			
			return getAccessRequest;
	}
		
		return getAccessRequest;

	}
	
	
	public RequestSpecification allCourseDetailsRequest(String accessCode) {
		
		if(allCourseRequest==null)
		{
			allCourseRequest = new RequestSpecBuilder()
					.setBaseUri(res.getString("redirect_uri"))
					.addQueryParam("access_token", accessCode)
					.addFilter(RequestLoggingFilter.logRequestTo(print))
					.addFilter(ResponseLoggingFilter.logResponseTo(print))
					.build();
			
			return allCourseRequest;
		}
		
		return allCourseRequest;
	}
	
}
