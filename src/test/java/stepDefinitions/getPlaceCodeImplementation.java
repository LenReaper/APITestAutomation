package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.util.ResourceBundle;

import org.junit.Assert;

import api.Utils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
import pojoSerialization.addPlaceObjects;

public class getPlaceCodeImplementation {
	String response;
	String placeId;
	addPlaceCodeImplementation add = new addPlaceCodeImplementation();
	Utils util = new Utils();
	addPlaceObjects addObj = new addPlaceObjects();
	ResourceBundle res = ResourceBundle.getBundle("OR");
	
	@When("user enters placeId and sends request")
	public void user_enters_placeId_and_sends_request() throws FileNotFoundException {
	   
		placeId = addPlaceCodeImplementation.placeId;
		
		response = given().spec(util.requestSpec()).queryParam("place_id", placeId)
		.when().get(res.getString("getResource"))
		.then().spec(util.responseSpec()).extract().asString();
		
	}

	@Then("Response should be returned as expected")
	public void response_should_be_returned_as_expected() {
	    
		String actualName = util.parseJsonToString(response, "name");
		String expectedName = addObj.getName();
		System.out.println(expectedName);
		Assert.assertEquals(expectedName, actualName);
		System.out.println("Actual Name found is "+actualName);
		System.out.println("Expected Name was "+expectedName);
		
	}
	
	@Then("The status code should be 200")
	public void the_status_code_should_be_200() throws FileNotFoundException {
		
		given().spec(util.requestSpec()).queryParam("place_id", placeId)
		.when().get(res.getString("getResource"))
		.then().spec(util.responseSpec()).assertThat().statusCode(200);
		
	}


}
