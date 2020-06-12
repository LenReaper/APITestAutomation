Feature: Google Maps mock api

Scenario: Add Place in Google Maps mock api

Given User is logged into the api
When User enters values in required fields to add a place
Then The location should be successfully added
And The "status" code should be "OK"