Meta:

Narrative:
As a user
I want to create new account
So that I can achieve a business goal

Scenario: Try to create new user

When Try to register {"username":"name","password":"password"}
Then Check registered user name

Scenario: Try to create existing user
When Try to register {"username":"sJohn","password":"somePassword","email":"demo@demo.com"}
Then Validate UserExists User exists sJohn,demo@demo.com