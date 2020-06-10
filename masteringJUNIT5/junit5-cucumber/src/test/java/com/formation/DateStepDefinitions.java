package com.formation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DateStepDefinitions {

    private String result;
    private DateCalculator calculator;

    @Given("^today is ([0-9]{4}-[0-9]{2}-[0-9]{2})$")
    public void today_is(Date date) {
        calculator = new DateCalculator(date);
    }

    @When("^I ask if ([0-9]{4}-[0-9]{2}-[0-9]{2}) is in the past$")
    public void I_ask_if_date_is_in_the_past(Date date) {
        result = calculator.isDateInThePast(date);
    }

    @Then("^the result should be (yes|no)$")
    public void the_result_should_be(String expectedResult) {
        assertEquals(expectedResult, result);
    }

}
