Feature: Google Maps mock api

@AddPlce
Scenario: Add Place in Google Maps mock api

Given User is logged into the api
When User enters values in required fields to add a place
Then The location should be successfully added
And The "status" code should be "OK"


@GetPlace
Scenario: Get Place in Google Maps mock api

Given User is logged into the api
When user enters placeId and sends request
Then Response should be returned as expected
And The status code should be 200
 