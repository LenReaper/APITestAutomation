package stepDefinitions;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import api.POJOPost;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class addPlaceCodeImplementation extends POJOPost{
	
	RequestSpecification partialRequest;
	RequestSpecification request;
	String finalResponse;
	
	@Given("User is logged into the api")
	public void user_is_logged_into_the_api() {
	    
		//ResqSpecBuilder to use common code
		
		partialRequest = new RequestSpecBuilder()
											.setBaseUri("https://rahulshettyacademy.com")
											.addQueryParam("key", "qaclick123")
											.setContentType(ContentType.JSON).build();
	}

	@When("User enters values in required fields to add a place")
	public void user_enters_values_in_required_fields_to_add_a_place() {
	    
		//Final request 
		
		request = given().spec(partialRequest).body(postObject());
	    
	}

	@Then("The location should be successfully added")
	public void the_location_should_be_successfully_added() {
		ResponseSpecification response =  new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON).log(LogDetail.ALL).build();

		finalResponse = request.when().post("/maps/api/place/add/json")
		.then().spec(response).extract().asString();	
	    
	}

	@Then("The {string} code should be {string}")
	public void the_code_should_be(String keyValue, String expValue) {
		
		JsonPath js = new JsonPath(finalResponse);
		String actualStatus = js.getString(keyValue);
		Assert.assertEquals(expValue, actualStatus);
		
		
	    
	    
	}

}
