package api;

import java.util.ArrayList;
import java.util.List;

import pojoSerialization.Locations;
import pojoSerialization.addPlaceObjects;

public class POJOPost {
	
	public addPlaceObjects postObject() {
		
		addPlaceObjects add = new addPlaceObjects();
		add.setAccuracy(100);
		add.setName("Third Nameless dudine");
		add.setPhone_number("(0123)-456-789");
		add.setAddress("Shaitan gali, Khatra mehal, Shamshan ke saamne");
		add.setWebsite("www.google.com/");
		add.setLanguage("Spanish");
		Locations loc = new Locations();
		loc.setLat(-366.66);
		loc.setLng(-500.20);
		add.setLocation(loc);
		List<String> types = new ArrayList<String>();
		types.add("shoe rack");
		types.add("chappal");
		add.setTypes(types);
		return add;
	}

}
