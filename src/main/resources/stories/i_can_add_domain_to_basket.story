Narrative:

In order to buy a domain
As a potential client
I want to be able to add domain to basket

Scenario: Adding available domain to the basket
Given logged in client
And domain <domain> in search result
When I order domain <domain>
And skip recommendations
Then domain <domain> should be visible in basket

Examples:
|domain|
|uniquedomainname2.pl|
|uniquedomainname3.pl|