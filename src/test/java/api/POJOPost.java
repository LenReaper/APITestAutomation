package api;

import java.util.ArrayList;
import java.util.List;

import pojo.Locations;
import pojo.addPlaceObjects;


public class POJOPost {
	
	public addPlaceObjects postObject(String nameGiven, String addressGiven, String languageGiven) {
		
		addPlaceObjects add = new addPlaceObjects();
		add.setAccuracy(100);
		add.setName(nameGiven);
		add.setPhone_number("(0123)-456-789");
		add.setAddress(addressGiven);
		add.setWebsite("www.google.com/");
		add.setLanguage(languageGiven);
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
