package payload;

public class UpdatePayload {

	public static String updatePayload(String placeId, String newAddress) {
		
		return "{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"";
	}
}
