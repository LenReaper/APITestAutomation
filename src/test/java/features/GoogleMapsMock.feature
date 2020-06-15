Feature: Google Maps mock api

@AddPlace
Scenario Outline: Add Place in Google Maps mock api

Given User is logged into the api
When User enters values in required fields to add a place using "<name>" "<address>" "<phone>"
Then The location should be successfully added
And The "status" code should be "OK"
And The name returned after get api is run must me same as "<name>"

Examples:
	|name	|address	|phone	  |
	|Nebar	|Sipur		|123654789|
	|Star	|L Ngr		|789654123|
	|B da	|Gbasti		|987546321|



@GetPlace
Scenario: Get Place in Google Maps mock api

Given User is logged into the api
When user enters placeId and sends request
Then Response should be returned as expected
And The status code should be 200
 