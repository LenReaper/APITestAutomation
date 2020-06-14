package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.util.ResourceBundle;

import org.junit.Assert;

import api.POJOPost;
import api.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class addPlaceCodeImplementation extends POJOPost{
	
	RequestSpecification partialRequest;
	RequestSpecification request;
	String finalResponse;
	static String placeId;
	Utils util = new Utils();
	ResourceBundle res = ResourceBundle.getBundle("OR");
	
	@Given("User is logged into the api")
	public void user_is_logged_into_the_api() throws FileNotFoundException {
	    
		partialRequest = util.requestSpec();
	}

	@When("User enters values in required fields to add a place")
	public void user_enters_values_in_required_fields_to_add_a_place() {
	    
		request = given().spec(partialRequest).body(postObject());
	    
	}

	@Then("The location should be successfully added")
	public void the_location_should_be_successfully_added() {
		
		ResponseSpecification response =  util.responseSpec();
		finalResponse = request.when().post(res.getString("postResource"))
		.then().spec(response).extract().asString();	
	    
	}

	@Then("The {string} code should be {string}")
	public void the_code_should_be(String keyValue, String expValue) {
		
		placeId = util.parseJsonToString(finalResponse, "place_id");
		String actualStatus = util.parseJsonToString(finalResponse, keyValue);
		Assert.assertEquals(expValue, actualStatus);
	}

}
