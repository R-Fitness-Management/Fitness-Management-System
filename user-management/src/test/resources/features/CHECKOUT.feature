Feature: Admin User Management 

Scenario: Add a new user 
Given the admin dash board  is loaded 
When the admin adds a new user with name"Rand", email "Rand@email.com" ,role "Instructor", and status "active"
Then the user should be added successfully 
And the user with email "Rand@email.com" should exist in the system


