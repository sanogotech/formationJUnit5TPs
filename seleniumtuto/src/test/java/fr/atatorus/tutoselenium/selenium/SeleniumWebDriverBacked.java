package fr.atatorus.tutoselenium.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

@Ignore
public class SeleniumWebDriverBacked {

    private Selenium selenium;

    @Before
    public void setUp() throws Exception {
        WebDriver driver = new FirefoxDriver();
        String baseUrl = "http://localhost:8080/tutoselenium";
        selenium = new WebDriverBackedSelenium(driver, baseUrl);
    }

    @Test
    public void testSelenium() throws Exception {
        selenium.open("/tutoselenium/");
        selenium.type("id=contentForm:pageText", "2");
        selenium.click("id=contentForm:nextPage");
        selenium.waitForPageToLoad("30000");
        selenium.click("id=contentForm:page3Button");
        selenium.waitForPageToLoad("30000");
        selenium.select("id=contentForm:pageList_input", "value=1");
        selenium.click("id=contentForm:nextPageButton");
        selenium.waitForPageToLoad("30000");
        selenium.click("id=headerForm:english_button");
        selenium.waitForPageToLoad("30000");
        selenium.type("id=contentForm:pageText", "2");
        selenium.click("id=contentForm:nextPage");
        selenium.waitForPageToLoad("30000");
        selenium.click("id=contentForm:page3Button");
        selenium.waitForPageToLoad("30000");
        selenium.select("id=contentForm:pageList_input", "label=page1");
        selenium.click("id=contentForm:nextPageButton");
        selenium.waitForPageToLoad("30000");
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
