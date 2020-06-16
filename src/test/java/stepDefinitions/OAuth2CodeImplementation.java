package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;

public class OAuth2CodeImplementation {
	
	Utils util = new Utils();
	String code;
	RequestSpecification request;
	String accessCodeResponse;
	String accessCode;
	String finalResponse;
	ResourceBundle res = ResourceBundle.getBundle("OR");
	
	
	@Given("^User has hit the authorization server url with get command$")
    public void user_has_hit_the_authorization_server_url_with_get_command() throws Throwable {
		
		 request = given().spec(util.requestOAuth());
		
    }
	
    @When("^User gets the Access Token request$")
    public void user_gets_the_access_token_request() throws Throwable {
        
    	String urlWithCode = res.getString("urlToGetCode");
    	String partialCode = urlWithCode.split("&code=")[1];
    	code = partialCode.split("&scope=")[0];
    	
    	accessCodeResponse = given().spec(util.getAccessTokenReq(code))
    	.urlEncodingEnabled(false)
    	.when().post()
    	.then().assertThat().statusCode(200)
    	.extract().response().asString();
    	
    	accessCode = util.parseJsonToString(accessCodeResponse, "access_token");
    	
    }
    
    @Then("^User should be able to see all couse details$")
    public void user_should_be_able_to_see_all_couse_details() throws Throwable {
        
    	finalResponse = given().spec(util.allCourseDetailsRequest(accessCode))
        .when().get()
        .then().assertThat().statusCode(200)
        .extract().response().asString();
    	
    	System.out.println(finalResponse);

    	
    }

    @And("^User extracts code from resulting URL$")
    public void user_extracts_code_from_resulting_url() throws Throwable {
    	
    	System.out.println(finalResponse);
    	
    }

}
