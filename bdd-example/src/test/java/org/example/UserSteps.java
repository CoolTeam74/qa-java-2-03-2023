package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserSteps {
    private User user = new User();

    @Given("^that the user (.*) is given a task to check his/her credit rating$")
    public void userName(String name) {
        user.setName(name);
    }

    @When("^(.*) got (\\d+) marks in rating$")
    public void getRating(String name, int marks) {
        user.setName(name);
        user.setMarks(marks);
    }

    @Then("^(.*) is know as well rating")
    public void ratingOk(String name) {
        assertEquals(name, user.getName());
        assertTrue(user.result());
    }
}
