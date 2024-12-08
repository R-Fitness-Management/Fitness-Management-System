package com.fitness.management.steps;

import com.fitness.management.ProgramService;
import com.fitness.management.Program;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ProgramManagementSteps {

    private ProgramService programService;
    private Program program;

    @Given("the instructor dashboard is loaded")
    public void the_instructor_dashboard_is_loaded() {
        programService = new ProgramService();
    }

    @When("the instructor creates a new fitness program with:")
    public void the_instructor_creates_a_new_fitness_program_with(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        List<String> videos = Arrays.asList(data.get("videos").split(","));
        List<String> documents = Arrays.asList(data.get("documents").split(","));
        program = new Program(
            data.get("title"),
            data.get("duration"),
            data.get("difficulty"),
            data.get("goals"),
            Double.parseDouble(data.get("price")),
            data.get("schedule"),
            videos,
            documents
        );
        boolean result = programService.addProgram(program);
        assertTrue("Program should be created successfully", result);
    }

    @Then("the fitness program should be created successfully")
    public void the_fitness_program_should_be_created_successfully() {
        assertNotNull("Program should exist", programService.getProgram(program.getTitle()));
    }

    @Then("the program with title {string} should exist in the system")
    public void the_program_with_title_should_exist_in_the_system(String title) {
        Program existingProgram = programService.getProgram(title);
        assertNotNull("The program with title " + title + " should exist in the system", existingProgram);
    }

    @When("the instructor updates the program titled {string} with:")
    public void the_instructor_updates_the_program_titled_with(String title, DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        List<String> videos = Arrays.asList(data.get("videos").split(","));
        List<String> documents = Arrays.asList(data.get("documents").split(","));
        boolean updated = programService.updateProgram(
            title,
            data.get("duration"),
            data.get("difficulty"),
            data.get("goals"),
            Double.parseDouble(data.get("price")),
            data.get("schedule"),
            videos,
            documents
        );
        assertTrue("Program should be updated successfully", updated);
    }
    @Then("the fitness program should be updated successfully")
    public void the_fitness_program_should_be_updated_successfully() {
        Program updatedProgram = programService.getProgram(program.getTitle());
        assertNotNull("Updated program should exist", updatedProgram);
    }

    @When("the instructor deletes the program titled {string}")
    public void the_instructor_deletes_the_program_titled(String title) {
        boolean deleted = programService.deleteProgram(title);
        assertTrue("Program should be deleted successfully", deleted);
    }

    @Then("the fitness program should no longer exist in the system")
    public void the_fitness_program_should_no_longer_exist_in_the_system() {
        assertNull("Program should no longer exist", programService.getProgram(program.getTitle()));
    }
}
