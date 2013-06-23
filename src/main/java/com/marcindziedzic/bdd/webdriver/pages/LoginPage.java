package com.marcindziedzic.bdd.webdriver.pages;

import com.marcindziedzic.bdd.webdriver.Credentials;
import org.jbehave.core.annotations.Given;
import org.jbehave.web.selenium.WebDriverProvider;

public class LoginPage extends Page {

    public LoginPage(WebDriverProvider driverProvider) {
        super(driverProvider, "Panel Klienta NetArt");
    }

    @Given("logged in client")
    public void loggedInClient() {
        if (isLoggedIn()) {
            return;
        }

        open("zaloguj-sie/");

        insertText("clientLogin", Credentials.CORRECT_LOGIN);
        insertText("clientPass", Credentials.CORRECT_PASSWORD);
        click("__submit_PK_M0118");
    }

    private boolean isLoggedIn() {
        return getPageSource().contains("Wyloguj się");
    }

}
