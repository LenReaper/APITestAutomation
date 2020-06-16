package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.util.ResourceBundle;

import org.junit.Assert;

import api.POJOPost;
import api.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import payload.UpdatePayload;

public class addPlaceCodeImplementation extends POJOPost{
	
	RequestSpecification partialRequest;
	RequestSpecification request;
	ResponseSpecification response;
	String getResponse;
	String finalResponse;
	String placeId;
	String name;
	String deleteRequest;
	RequestSpecification updRequest;
	String finalUpdResponse;
	Utils util = new Utils();
	ResourceBundle res = ResourceBundle.getBundle("OR");
	
	@Given("User is logged into the api")
	public void user_is_logged_into_the_api() throws FileNotFoundException {
	    
		partialRequest = util.requestSpec();
	}

	@When("User enters values in required fields to add a place using {string} {string} {string}")
	public void user_enters_values_in_required_fields_to_add_a_place_using(String name, String address, String phNumber) {
	    
		request = given().spec(partialRequest).body(postObject(name, address, phNumber));
	    
	}

	@Then("The location should be successfully added")
	public void the_location_should_be_successfully_added() {
		
		response =  util.responseSpec();
		finalResponse = request.when().post(res.getString("postResource"))
		.then().spec(response).extract().asString();	
	    
	}

	@Then("The {string} code should be {string}")
	public void the_code_should_be(String keyValue, String expValue) {
		
		placeId = util.parseJsonToString(finalResponse, "place_id");
		String actualStatus = util.parseJsonToString(finalResponse, keyValue);
		Assert.assertEquals(expValue, actualStatus);
	}
	
	@Then("The name returned after get api is run must me same as {string}")
	public void the_name_returned_after_get_api_is_run_must_me_same_as(String expectedName) throws FileNotFoundException {
		
		getResponse = util.getPlaceResponse(placeId);
		String actualName = util.parseJsonToString(getResponse, "name");
		Assert.assertEquals(expectedName, actualName);
		System.out.println("Expected Name "+expectedName+"    Actual Name "+actualName);
	    
	    
	}
	
	@Then("Address should be updated to {string}")
	public void address_should_be_updated_to(String newAddress) {
	    
		updRequest = given().spec(partialRequest).body(UpdatePayload.updatePayload(placeId, newAddress));
		finalUpdResponse = updRequest
			.when().put(res.getString("putResource"))
			.then().spec(response).extract().asString();
	    
	}

	@Then("The Address returned after get api is run must me same as {string}")
	public void the_Address_returned_after_get_api_is_run_must_me_same_as(String expectedAddress) throws FileNotFoundException {
	    
		getResponse = util.getPlaceResponse(placeId);
		String actualNewAddress = util.parseJsonToString(getResponse, "address");
		Assert.assertEquals(expectedAddress, actualNewAddress);
		System.out.println("Expected new address is "+expectedAddress+" Actual new address is "+actualNewAddress);
	    
	}
	
	@And("^The location should be deleted and status should be \"([^\"]*)\"$")
    public void the_location_should_be_deleted_and_status_should_be_something(String expectedStatus) throws Throwable {
        
		String deleteResponse = given().spec(util.requestSpec()).body("{\r\n" + 
				"    \"place_id\":\""+placeId+"\"          \r\n" + 
				"}\r\n" + 
				"")
		.when().post(res.getString("deleteResource"))
		.then().spec(response).extract().asString();
		
		String actualStatus = util.parseJsonToString(deleteResponse, "status");
		
		Assert.assertEquals(expectedStatus, actualStatus);
	}

}
