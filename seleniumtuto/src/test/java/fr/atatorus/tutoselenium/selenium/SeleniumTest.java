package fr.atatorus.tutoselenium.selenium;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import com.opera.core.systems.OperaDriver;
import com.thoughtworks.selenium.Selenium;

public class SeleniumTest {

    private static final String PAGE_TO_LOAD_TIMEOUT = "100";
    public static final int HTML_UNIT_DRIVER = 0;
    public static final int FIREFOX_DRIVER = 1;
    public static final int OPERA_DRIVER = 2;
    public static final int CHROME_DRIVER = 3;

    private int currentDriver;

    private WebDriver driver;
    private String baseUrl;
    private Selenium selenium;
    private final StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        Properties properties = System.getProperties();
        baseUrl = properties.getProperty("base.url", "http://127.0.0.1:8080/tutoselenium/");
    }

    @Test
    public void firefoxTest() throws Exception {
        driver = new FirefoxDriver();
        currentDriver = FIREFOX_DRIVER;
        selenium = new WebDriverBackedSelenium(driver, baseUrl);
        testSelenium();
    }

    @Test
    public void htmlUnitTest() throws Exception {
        driver = new HtmlUnitDriver(true);
        currentDriver = HTML_UNIT_DRIVER;
        selenium = new WebDriverBackedSelenium(driver, baseUrl);
        testSelenium();
    }

    @Test
    public void operaTest() throws Exception {
        driver = new OperaDriver();
        currentDriver = OPERA_DRIVER;
        selenium = new WebDriverBackedSelenium(driver, baseUrl);
        testSelenium();
    }

    private static final String CHROME_DRIVER_PATH = "/usr/lib64/chromium/chromedriver";

    @Test
    public void chromeTest() throws Exception {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        currentDriver = CHROME_DRIVER;
        selenium = new WebDriverBackedSelenium(driver, baseUrl);
        testSelenium();
    }

    private void testSelenium() throws Exception {
        // Connection
        driver.get(baseUrl + "faces/page1.xhtml");
        selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
        // Vérification
        checkHeader(Locale.FRENCH);
        checkPageUne(false, Locale.FRENCH);
        checkFooter(); // On vient de se connecter, on n'affiche donc pas le numéro de la page
                       // précédente

        // Avant d'aller page 2, on provoque une erreur
        driver.findElement(By.id("contentForm:pageText")).clear();
        driver.findElement(By.id("contentForm:pageText")).sendKeys("4");
        driver.findElement(By.id("contentForm:nextPage")).click();

        checkPageUne(true, Locale.FRENCH);

        // On va page 2
        driver.findElement(By.id("contentForm:pageText")).clear();
        driver.findElement(By.id("contentForm:pageText")).sendKeys("2");
        driver.findElement(By.id("contentForm:nextPage")).click();
        selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
        // vérification
        checkHeader(Locale.FRENCH);
        checkPageDeux(Locale.FRENCH);
        checkFooter("page une", Locale.FRENCH); // On vient de la page 1 en français

        // On va page 3
        driver.findElement(By.id("contentForm:page3Button")).click();
        selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
        // vérification
        checkHeader(Locale.FRENCH);
        checkPageTrois(Locale.FRENCH);
        checkFooter("page deux", Locale.FRENCH);

        // on retourne page 1
        new Select(driver.findElement(By.id("contentForm:pageList_input"))).selectByValue("1");
        driver.findElement(By.id("contentForm:nextPageButton")).click();
        selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);

        checkFooter("page trois", Locale.FRENCH);
        // On passe en anglais
        driver.findElement(By.id("headerForm:english_button")).click();
        selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
        // Vérification
        checkHeader(Locale.ENGLISH);
        checkPageUne(false, Locale.ENGLISH);
        checkFooter("page three", Locale.ENGLISH);

        // On va page 2
        driver.findElement(By.id("contentForm:pageText")).clear();
        driver.findElement(By.id("contentForm:pageText")).sendKeys("2");
        driver.findElement(By.id("contentForm:nextPage")).click();
        selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
        // vérification
        checkHeader(Locale.ENGLISH);
        checkPageDeux(Locale.ENGLISH);
        checkFooter("page one", Locale.ENGLISH);

        // On va page 3
        driver.findElement(By.id("contentForm:page3Button")).click();
        selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
        // vérification
        checkHeader(Locale.ENGLISH);
        checkPageTrois(Locale.ENGLISH);
        checkFooter("page two", Locale.ENGLISH);

        // Retour vers la page 1
        new Select(driver.findElement(By.id("contentForm:pageList_input"))).selectByVisibleText("page1");
        driver.findElement(By.id("contentForm:nextPageButton")).click();
        selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
        checkFooter("page three", Locale.ENGLISH);
    }

    private void checkPageTrois(Locale locale) {
        checkElement("contentForm", "pageTitle", locale == Locale.FRENCH ? "Page trois" : "Page three");
        checkPanelTitle("contentForm",
                        "panel",
                        "ui-panelgrid-header",
                        locale == Locale.FRENCH ? "Choix de la nouvelle page" : "Select the new page");

        checkElement("contentForm", "label", locale == Locale.FRENCH ? "Choisissez la nouvelle page :"
                                                                    : "Select the page :");
        checkElement("contentForm", "nextPageButton", locale == Locale.FRENCH ? "Page suivante" : "Next page");
        Select select = new Select(driver.findElement(By.id("contentForm:pageList_input")));
        List<WebElement> options = select.getOptions();
        assertThat(options.get(0).getText(), is("page1"));
        assertThat(options.get(1).getText(), is("page2"));
        assertThat(options.get(2).getText(), is("page3"));
    }

    private void checkFooter() {
        assertThat(isElementPresent(By.id("previousPage")), is(false));
    }

    private void checkFooter(String fromPage, Locale locale) {
        if (locale == Locale.FRENCH) {
            checkElement("previousPage", "Vous venez de la " + fromPage);
        } else {
            checkElement("previousPage", "You are coming from " + fromPage);
        }
    }

    private void checkElement(String elementId, String expected) {
        assertThat(driver.findElement(By.id(elementId)).getText(), is(expected));
    }

    private void checkElement(String parentId, String elementId, String expected) {
        checkElement(parentId + ":" + elementId, expected);
    }

    private void checkPageDeux(Locale locale) {
        checkElement("contentForm", "pageTitle", locale == Locale.FRENCH ? "Page deux" : "Page two");
        checkPanelTitle("contentForm",
                        "panel",
                        "ui-panel-title",
                        locale == Locale.FRENCH ? "Choix de la prochaine page" : "Select the new page");

        checkElement("contentForm", "page1Button", locale == Locale.FRENCH ? "Page une" : "Page one");
        checkElement("contentForm", "page2Button", locale == Locale.FRENCH ? "Page deux" : "Page two");
        checkElement("contentForm", "page3Button", locale == Locale.FRENCH ? "Page trois" : "Page three");
    }

    private void checkPageUne(boolean errorMessage, Locale locale) {
        checkElement("contentForm", "pageTitle", locale == Locale.FRENCH ? "Page une" : "Page one");
        checkPanelTitle("contentForm",
                        "panel",
                        "ui-panel-title",
                        locale == Locale.FRENCH ? "Choix de la prochaine page" : "Select the new page");

        checkElement("contentForm", "label", locale == Locale.FRENCH ? "Numéro de la prochaine page"
                                                                    : "Number of next page :");
        checkElement("contentForm", "nextPage", locale == Locale.FRENCH ? "Page suivante" : "Next page");

        if (errorMessage) {
            checkElement("contentForm",
                         "pageError",
                         locale == Locale.FRENCH ? "Vous devez entrer une valeur entre un et trois."
                                                : "You must enter a number between one and three.");
            String color = driver.findElement(By.id("contentForm:pageError")).getCssValue("color");
            switch (currentDriver) {
                case CHROME_DRIVER:
                case FIREFOX_DRIVER:
                case OPERA_DRIVER:
                    assertThat(color, is("rgba(255, 0, 0, 1)"));
                    break;
                default:
                    assertThat(color, is("red"));
                    break;
            }
        } else {
            switch (currentDriver) {
                case FIREFOX_DRIVER:
                case OPERA_DRIVER:
                case CHROME_DRIVER:
                    assertThat(driver.findElement(By.id("contentForm:pageError")).isDisplayed(), is(false));
                    break;
                default:
                    checkElement("contentForm", "pageError", "");
            }
        }
    }

    private void checkPanelTitle(String parentId, String panelId, String titleClass, String expectedTitle) {
        WebElement panel = driver.findElement(By.id(parentId + ":" + panelId));
        WebElement panelTitle = panel.findElement(By.className(titleClass));
        assertThat(panelTitle.getText(), is(expectedTitle));
    }

    private void checkHeader(Locale locale) {
        WebElement title = driver.findElement(By.tagName("h1"));
        assertThat(title.getText(), is(locale == Locale.FRENCH ? "En tête" : "Header"));
        String drapeau = driver.findElement(By.id("headerForm:english_button")).getCssValue("background-image");
        assertThat(drapeau, is(buildUrl("drapeau_anglais.png")));
        drapeau = driver.findElement(By.id("headerForm:french_button")).getCssValue("background-image");
        assertThat(drapeau, is(buildUrl("drapeau_francais.png")));
    }

    private String buildUrl(String resource) {
        switch (currentDriver) {
            case CHROME_DRIVER:
                return "url(" + baseUrl + "faces/javax.faces.resource/" + resource + "?ln=images)";
            case FIREFOX_DRIVER:
            case OPERA_DRIVER:
                return "url(\"" + baseUrl + "faces/javax.faces.resource/" + resource + "?ln=images\")";
            default:
                String[] split = baseUrl.split("/");
                String root = "";
                if (split.length == 4) {
                    root = "/" + split[3];
                }
                return "url(" + root + "/faces/javax.faces.resource/" + resource + "?ln=images)";
        }
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

}
