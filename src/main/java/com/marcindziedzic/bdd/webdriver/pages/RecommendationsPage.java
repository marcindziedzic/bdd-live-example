package com.marcindziedzic.bdd.webdriver.pages;

import org.jbehave.core.annotations.When;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class RecommendationsPage extends Page {
    public RecommendationsPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    @When("skip recommendations")
    public void skipRecommendations() {
        findElement(By.className("continue-button")).click();
    }
}
