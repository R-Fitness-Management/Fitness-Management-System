package com.fitness.management.steps;

import com.fitness.management.User;
import com.fitness.management.UserService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class UserManagementSteps {
	
	
	@Given("the admin dash board  is loaded")
	public void the_admin_dash_board_is_loaded() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the admin adds a new user with name\"Rand\", email {string} ,role {string}, and status {string}")
	public void the_admin_adds_a_new_user_with_name_rand_email_role_and_status(String string, String string2, String string3) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the user should be added successfully")
	public void the_user_should_be_added_successfully() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the user with email {string} should exist in the system")
	public void the_user_with_email_should_exist_in_the_system(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}