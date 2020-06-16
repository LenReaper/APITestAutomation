Feature: OAuth validation of RS Academy

@OAuth
Scenario: Validation of OAuth
Given User has hit the authorization server url with get command
And User extracts code from resulting URL
When User gets the Access Token request
Then User should be able to see all couse details