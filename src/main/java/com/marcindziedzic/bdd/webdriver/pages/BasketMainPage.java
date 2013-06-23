package com.marcindziedzic.bdd.webdriver.pages;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class BasketMainPage extends Page {

	public BasketMainPage(WebDriverProvider driverProvider) {
		super(driverProvider, "Koszyk");
	}

	@Then("domain <domain> should be visible in basket")
	public void verifyDomainIsInBasket(@Named("domain") String domain) {
        verifyElementContainsText(By.className("roll-elements"), domain);
	}

}
