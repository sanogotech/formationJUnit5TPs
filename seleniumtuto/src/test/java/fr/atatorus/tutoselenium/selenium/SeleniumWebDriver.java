package fr.atatorus.tutoselenium.selenium;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

@Ignore
public class SeleniumWebDriver {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private final StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/tutoselenium";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSelenium() throws Exception {
        // open | /tutoselenium/ |
        driver.get(baseUrl);
        // type | id=contentForm:pageText | 2
        driver.findElement(By.id("contentForm:pageText")).clear();
        driver.findElement(By.id("contentForm:pageText")).sendKeys("2");
        // click | id=contentForm:nextPage |
        driver.findElement(By.id("contentForm:nextPage")).click();
        // click | id=contentForm:page3Button |
        driver.findElement(By.id("contentForm:page3Button")).click();
        // select | id=contentForm:pageList_input | value=1
        new Select(driver.findElement(By.id("contentForm:pageList_input"))).selectByValue("1");
        // click | id=contentForm:nextPageButton |
        driver.findElement(By.id("contentForm:nextPageButton")).click();
        // click | id=headerForm:english_button |
        driver.findElement(By.id("headerForm:english_button")).click();
        // type | id=contentForm:pageText | 2
        driver.findElement(By.id("contentForm:pageText")).clear();
        driver.findElement(By.id("contentForm:pageText")).sendKeys("2");
        // click | id=contentForm:nextPage |
        driver.findElement(By.id("contentForm:nextPage")).click();
        // click | id=contentForm:page3Button |
        driver.findElement(By.id("contentForm:page3Button")).click();
        // select | id=contentForm:pageList_input | label=page1
        new Select(driver.findElement(By.id("contentForm:pageList_input"))).selectByVisibleText("page1");
        // click | id=contentForm:nextPageButton |
        driver.findElement(By.id("contentForm:nextPageButton")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if ( !"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alert.getText();
        } finally {
            acceptNextAlert = true;
        }
    }
}
