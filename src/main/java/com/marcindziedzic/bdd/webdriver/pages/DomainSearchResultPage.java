package com.marcindziedzic.bdd.webdriver.pages;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class DomainSearchResultPage extends Page {

    public DomainSearchResultPage(WebDriverProvider driverProvider) {
        super(driverProvider, "Wyniki wyszukiwania domen");
    }

    @Then("domain search results page should open")
    public void isOpen() {
        verifyTitle(title);
    }

    @Given("is available $domain")
    @Then("domain should be available $domain")
    public void verifyDomainIsAvailable(String domain) {
        String status = findElement(By.id("domainStatusDesc_" + domain)).getText();
        assertThat(status, equalTo("dostępna"));
    }

    @When("I order domain <domain>")
    public void orderDomain(@Named("domain") String domain) {
        verifyDomainIsAvailable(domain);
        addDomainToBasket(domain);
        pressContinueButton();
    }

    private void addDomainToBasket(String domain) {
        WebElement selectElement = findElement(By.id("domainAction_" + domain));
        selectElement.findElement(By.tagName("a")).click();

        awaitDomainAddedToBasket(domain);
    }

    private void awaitDomainAddedToBasket(final String domain) {
        waitUntil(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                findElement(By.className("mini-cart")).click();

                WebElement e = findElement(By.className("mini-cart-prods-container"));
                if (e.getText().contains(domain)) {
                    return e;
                }

                return null;
            }
        });
    }

    private void pressContinueButton() {
        By continueButton = By.xpath("//body/div/div/section/div/div[3]/div[2]/div/a");
        waitUntil(elementToBeClickable(continueButton));
        findElement(continueButton).click();
    }

}


