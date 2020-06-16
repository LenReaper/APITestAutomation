//package stepDefinitions;
//
//import static io.restassured.RestAssured.given;
//
//import java.util.ResourceBundle;
//
//import org.junit.Assert;
//
//import api.Utils;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//
//public class deletePlaceCodeImplementation{
//	
//	Utils util = new Utils();
//	ResourceBundle res = ResourceBundle.getBundle("OR");
//	String placeId;
//	String response;
//	
//    @When("^user enters placeId and sends delete request$")
//    public void user_enters_placeid_and_sends_delete_request() throws Throwable {
//    	
//    	response = given().spec(util.requestSpec()).body("{\r\n" + 
//    			"    \"place_id\":\""+placeId+"\"          \r\n" + 
//    			"}\r\n" + 
//    			"")
//    	.when().post(res.getString("deleteResource"))
//    	.then().spec(util.responseSpec()).extract().asString();
//    }
//	
//	@Then("Deletion should be successful and status should be {string}")
//	public void deletion_should_be_successful(String expectedStatus) {
//	    String actualStatus = util.parseJsonToString(response, "status");
//	    Assert.assertEquals(expectedStatus, actualStatus);
//	}
//
//}
