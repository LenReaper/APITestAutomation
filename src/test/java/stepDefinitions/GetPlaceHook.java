//package stepDefinitions;
//
//import java.io.FileNotFoundException;
//
//import io.cucumber.java.Before;
//
//public class GetPlaceHook {
//	
//	
//	@Before("@GetPlace")
//	public void beforeGetPlace() throws FileNotFoundException {
//		
//		String place_Id;
//		
//		addPlaceCodeImplementation placeCode = new addPlaceCodeImplementation();
//		if(placeCode.placeId==null)
//		{
//			placeCode.user_is_logged_into_the_api();
//			placeCode.user_enters_values_in_required_fields_to_add_a_place_using("Palash", "Meghalaya", "2013654789");
//			placeCode.the_location_should_be_successfully_added();
//			placeCode.the_code_should_be("status", "OK");
//			place_Id = placeCode.placeId;
//		}
//	}
//
//}
