Narrative:

In order to check if I can buy domain
As a potential client
I want to have ability to check if domain is free for registration

Scenario: Checking domain availability directly from home page
Given opened home page
When I insert uniquedomainname in search field
And press search button
Then domain search results page should open
And domain should be available uniquedomainname.pl

Scenario: Checking domain availability from domain registration page
Given domain registration page
And commercial should be present Kreator Stron WWW
And commercial should be present Profesjonalna soloPOCZTA
And commercial should be present Internetowy dziennik Active.blog
When I search for domain uniquedomainname.pl
Then domain search results page should open
And domain should be available uniquedomainname.pl