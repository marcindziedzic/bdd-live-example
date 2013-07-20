package com.marcindziedzic.bdd;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.i18n.LocalizedKeywords;

import java.util.Locale;

public class HistoryjkaPoPolsku extends Story {

    @Given("otwarta strone logowania")
    public void givenOpenHomePage() {
        System.out.println("givenOpenHomePage() called");
    }

    @When("wprowadze poprawnie dane uzytkownika")
    public void whenLoginDataAreInserted() {
        System.out.println("whenLoginDataAreInserted() called");
    }

    @Then("zostac zalogowany do systemu")
    public void thenUserIsLoggedIn() {
        System.out.println("thenUserIsLoggedIn() called");
    }

    @Override
    public LocalizedKeywords getKeywords() {
        return new LocalizedKeywords(new Locale("pl"));
    }

    @Override
    public String getStoryExtension() {
        return ".historyjka";
    }
}
