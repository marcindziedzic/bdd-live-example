package com.marcindziedzic.bdd.webdriver.pages;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class Page extends WebDriverPage {

	protected final WebDriverProvider driverProvider;

	protected String title;

	public Page(WebDriverProvider driverProvider) {
		super(driverProvider);
		this.driverProvider = driverProvider;
	}

	protected Page(WebDriverProvider driverProvider, String title) {
		this(driverProvider);
		this.title = title;
	}

	protected void open(String relativeUrl) {
		get("https://www.nazwa.pl/" + relativeUrl);
        verifyTitleIfSet();
    }

    @Given("open page $url")
    public void openPage(String url) {
        open(url);
        verifyTitleIfSet();
    }

	@Then("title should be $expected")
	public void verifyTitle(String expected) {
        assertThat(getTitle(), containsString(expected));
	}

    @Given("commercial should be present $expected")
    @Then("text should be present $expected")
    public void verifyTextPresent(String expected) {
        assertThat(getPageSource(), containsString(expected));
    }

	protected void forceWait() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {e.printStackTrace();
		}
	}

    protected <T> void waitUntil(ExpectedCondition<T> condition) {
        new WebDriverWait(this, 10).until(condition);
    }

    private void verifyTitleIfSet() {
        if (title != null) {
            verifyTitle(title);
        }
    }

	protected void insertText(String elementId, String name) {
		WebElement element = findById(elementId);
		element.sendKeys(name);
	}

    protected void click(String elementId) {
		WebElement element = findById(elementId);
		element.click();
	}

    protected WebElement findById(String elementId) {
		return findElement(By.id(elementId));
	}

    protected void verifyElementContainsText(By by, String expected) {
        WebElement el = findElement(by);
        assertTrue(el.getText().contains(expected));
    }
}
