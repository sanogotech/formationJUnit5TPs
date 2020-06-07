package fr.atatorus.tutoselenium.selenium;

import static fr.atatorus.tutoselenium.selenium.WebDriverFactory.Type.CHROME;
import static fr.atatorus.tutoselenium.selenium.WebDriverFactory.Type.FIREFOX;
import static fr.atatorus.tutoselenium.selenium.WebDriverFactory.Type.HTML_UNIT;
import static fr.atatorus.tutoselenium.selenium.WebDriverFactory.Type.OPERA;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;

import com.thoughtworks.selenium.Selenium;

import fr.atatorus.tutoselenium.pages.BasePage;
import fr.atatorus.tutoselenium.pages.BrowserDelegate;
import fr.atatorus.tutoselenium.pages.ChromeDelegate;
import fr.atatorus.tutoselenium.pages.FirefoxDelegate;
import fr.atatorus.tutoselenium.pages.HtmlUnitDelegate;
import fr.atatorus.tutoselenium.pages.OperaDelegate;
import fr.atatorus.tutoselenium.pages.Page1;
import fr.atatorus.tutoselenium.pages.Page2;
import fr.atatorus.tutoselenium.pages.Page3;

@RunWith(Parameterized.class)
public class TutorielSeleniumTest extends FluentTest {

    private static final String PAGE_TO_LOAD_TIMEOUT = "250";

    @Parameters(name = "{0}")
    public static Collection<Object[]> data() throws IOException {
        return Arrays.asList(new Object[][] { { HTML_UNIT, new HtmlUnitDelegate() },
                                             { FIREFOX, new FirefoxDelegate() },
                                             { OPERA, new OperaDelegate() },
                                             { CHROME, new ChromeDelegate() } });
    }

    private final WebDriver driver;
    private final BrowserDelegate delegate;
    private String baseUrl;
    private Selenium selenium;

    public TutorielSeleniumTest(WebDriverFactory.Type driverType, BrowserDelegate delegate) throws InterruptedException {
        super();
        this.driver = WebDriverFactory.getDriver(driverType);
        this.delegate = delegate;
        createPage(Page1.class);
    }

    @Page
    protected Page1 page1;
    @Page
    protected Page2 page2;
    @Page
    protected Page3 page3;

    @Override
    public WebDriver getDefaultDriver() {
        return driver;
    }

    @Before
    public void setUp() throws Exception {
        Properties properties = System.getProperties();
        baseUrl = properties.getProperty("base.url", "http://127.0.0.1:8080/tutoselenium/");
        selenium = new WebDriverBackedSelenium(driver, baseUrl);
        BasePage.resetLocale();
        page1.setDelegate(delegate);
        page2.setDelegate(delegate);
        page3.setDelegate(delegate);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void page1to1WithErrorFrench() throws Exception {
        connect();

        page1.isAt();
        page1.checkPage(false);

        page1.setNextPage(4);
        page1.buttonClick();

        page1.isAt();
        page1.checkPage(true);
    }

    @Test
    public void page1to2French() throws Exception {
        connect();

        page1.isAt();
        page1.checkPage(false);

        page1.setNextPage(2);
        page1.buttonClick();

        page2.isAt();
        page2.checkPage(1);
    }

    @Test
    public void page2to3French() throws Exception {
        connectAndGo(2);

        page2.isAt();
        page2.checkPage(1);

        page2.clicPage3Button();

        page3.isAt();
        page3.checkPage(2);
    }

    @Test
    public void page3to1French() throws Exception {
        connectAndGo(3);

        page3.isAt();
        page3.checkPage(1);

        page3.selectNextPage("1");
        page3.clickOnNextPageButton();

        page1.isAt();
        page1.checkPage(false, 3);
    }

    @Test
    public void page1to1WithErrorEnglish() throws Exception {
        connect();

        page1.isAt();
        page1.clickOnEnglishFlags();
        page1.checkPage(false);

        page1.setNextPage(4);
        page1.buttonClick();

        page1.isAt();
        page1.checkPage(true);
    }

    @Test
    public void page1to2English() throws Exception {
        connect();

        page1.isAt();
        page1.clickOnEnglishFlags();
        page1.checkPage(false);

        page1.setNextPage(2);
        page1.buttonClick();

        page2.isAt();
        page2.checkPage(1);
    }

    @Test
    public void page2to3English() throws Exception {
        connectAndGo(2);

        page2.isAt();
        page2.clickOnEnglishFlags();
        page2.checkPage(1);

        page2.clicPage3Button();
        page3.isAt();
        page3.checkPage(2);
    }

    @Test
    public void page3to1English() throws Exception {
        connectAndGo(3);

        page3.isAt();
        page3.clickOnEnglishFlags();
        page3.checkPage(1);

        page3.selectNextPage("1");
        page3.clickOnNextPageButton();

        page1.isAt();
        page1.checkPage(false, 3);
    }

    private void connect() {
        goTo(baseUrl + "faces/page1.xhtml");
        selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
    }

    private void connectAndGo(int page) {
        connect();
        if (page != 1) {
            page1.gotoPage(page);
        }
    }

    @Test
    public void seleniumTest() throws Exception {
        // Connection
        driver.get(baseUrl + "faces/page1.xhtml");

        // Vérification
        page1.isAt();
        page1.checkPage(false);

        // Avant d'aller page 2, on provoque une erreur
        page1.setNextPage(4);
        page1.buttonClick();
        page1.checkPage(true);

        // On va page 2
        page1.setNextPage(2);
        page1.buttonClick();

        page2.checkPage(1);
        page2.checkPage(1);

        // On va page 3
        page2.clicPage3Button();

        page3.isAt();
        page3.checkPage(2);

        // on retourne page 1
        page3.selectNextPage("1");
        page3.clickOnNextPageButton();

        page1.isAt();
        page1.checkPage(false, 3);
        // On passe en anglais
        page1.clickOnEnglishFlags();

        page1.isAt();
        page1.checkPage(false, 3);

        // On va page 2
        page1.setNextPage(2);
        page1.buttonClick();

        page2.isAt();
        page2.checkPage(1);

        // On va page 3
        page2.clicPage3Button();

        page3.isAt();
        page3.checkPage(2);

        // Retour vers la page 1
        page3.selectNextPage("1");
        page3.clickOnNextPageButton();

        page1.isAt();
        page1.checkPage(false, 3);
    }

}
