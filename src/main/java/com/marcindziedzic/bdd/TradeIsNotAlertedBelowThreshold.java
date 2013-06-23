package com.marcindziedzic.bdd;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class TradeIsNotAlertedBelowThreshold extends Story {

    @Given("a stock of symbol $symbol and a threshold of $threshold")
    public void givenStockSymbolAndThreshold(String symbol, String threshold) {
        System.out.println("Given");
    }

    @When("the stock is traded at $threshold")
    public void whenTheStockIsTraded(float threshold) {
        System.out.println("When");
    }

    @Then("the alert status should be $alertStatus")
    public void thenTheAlertStatusShouldBe(String alertStatus) {
        System.out.println("Then");
    }
}
