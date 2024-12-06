package com.fitness.management.steps;

import com.fitness.management.User;
import com.fitness.management.UserService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;


public class UserManagementSteps {
	private UserService userService;
	
	
	@Given("the admin dash board  is loaded")
	public void the_admin_dash_board_is_loaded() {
		userService  = new UserService();
	}

	@When("the admin adds a new user with name {string}, email {string} ,role {string}, and status {string}")
	public void the_admin_adds_a_new_user_with_name_rand_email_role_and_status(String name, String email, String role, String status) {
	   boolean isActive = status.equalsIgnoreCase("active");
	   User user = new User(name , email , role , isActive);
	   boolean result = userService.addUser(user);
	   assertTrue("User should be added successfully", result );
	}

	@Then("the user should be added successfully")
	public void the_user_should_be_added_successfully() {
	   assertNotNull("User should exist", userService.getUser("Rand@email.com"));
	}

	@Then("the user with email {string} should exist in the system")
	public void the_user_with_email_should_exist_in_the_system(String email) {
	    User user = userService.getUser(email);
	    assertNotNull("User should exist in the system", user);
	}

}