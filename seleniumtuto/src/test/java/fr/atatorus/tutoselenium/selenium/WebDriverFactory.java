package fr.atatorus.tutoselenium.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.opera.core.systems.OperaDriver;

public class WebDriverFactory {

    public static enum Type {
        FIREFOX, CHROME, OPERA, HTML_UNIT, IE
    }

    private static final String CHROME_DRIVER_PATH = "/usr/lib64/chromium/chromedriver";
    static {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
    }

    public static WebDriver getDriver(Type driverType) {
        switch (driverType) {
            case FIREFOX:
                return new FirefoxDriver();
            case CHROME:
                return new ChromeDriver();
            case OPERA:
                return new OperaDriver();
            case IE:
                throw new IllegalArgumentException();
            default:
                return new HtmlUnitDriver(true);
        }
    }
}
