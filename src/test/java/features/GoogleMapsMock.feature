Feature: Google Maps mock api


Scenario Outline: End to End execution of Google Maps mock api

Given User is logged into the api
When User enters values in required fields to add a place using "<name>" "<address>" "<phone>"
Then The location should be successfully added
And The "status" code should be "OK"
And The name returned after get api is run must me same as "<name>"
And Address should be updated to "<newAddress>"
And The Address returned after get api is run must me same as "<newAddress>"
And The location should be deleted and status should be "OK"

Examples:
	|name	|address	|phone	  |newAddress	|
	|Nebar	|Sipur		|123654789|Cmari		|
	|Star	|L Ngr		|789654123|Ubari		|
	|B da	|Gbasti		|987546321|L ngr		|



#@GetPlace
#Scenario: Get Place in Google Maps mock api
#
#Given User is logged into the api
#When user enters placeId and sends request
#Then Response should be returned as expected
#And The status code should be 200
#
#@DeletePlace
#Scenario: Delete place in Google Maps mock api
#
#Given User is logged into the api
#When user enters placeId and sends delete request
#Then Deletion should be successful and status should be "OK"
#And The status code should be 200
 