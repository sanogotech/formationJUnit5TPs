package fr.atatorus.tutoselenium.pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;
import java.util.Properties;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.support.FindBy;

import com.thoughtworks.selenium.Selenium;

public abstract class BasePage extends FluentPage {

    private static final String PAGE_TO_LOAD_TIMEOUT = "250";
    protected static Locale locale = Locale.FRENCH;

    public static void resetLocale() {
        locale = Locale.FRENCH;
    }

    private static final String[] pagesFR = { "", "page une", "page deux", "page trois" };
    private static final String[] pagesEN = { "", "page one", "page two", "page three" };

    protected BrowserDelegate delegate;

    public void setDelegate(BrowserDelegate delegate) {
        this.delegate = delegate;
    }

    // //////////////////////////////////////////////
    // HEADER

    @FindBy(tagName = "h1")
    private FluentWebElement headerTitle;
    @FindBy(id = "headerForm:english_button")
    private FluentWebElement englishFlag;
    @FindBy(id = "headerForm:french_button")
    private FluentWebElement frenchFlag;

    // //////////////////////////////////////////////
    // FOOTER

    @FindBy(id = "previousPage")
    private FluentWebElement footerText;
    private Selenium selenium;

    // ////////////////////////////////////

    @Override
    public String getBaseUrl() {
        Properties properties = System.getProperties();
        return properties.getProperty("base.url", "http://127.0.0.1:8080/tutoselenium/");
    }

    public void clickOnFrenchFlags() {
        locale = Locale.FRENCH;
        frenchFlag.click();
        waitPageToLoad();
    }

    public void clickOnEnglishFlags() {
        locale = Locale.ENGLISH;
        englishFlag.click();
        waitPageToLoad();
    }

    protected void checkHeader() {
        assertThat(headerTitle.getText(), is(locale == Locale.FRENCH ? "En tête" : "Header"));
        String url = delegate.buildUrl(getBaseUrl(), "drapeau_anglais.png");
        assertThat(englishFlag.getElement().getCssValue("background-image"), is(url));
        url = delegate.buildUrl(getBaseUrl(), "drapeau_francais.png");
        assertThat(frenchFlag.getElement().getCssValue("background-image"), is(url));
    }

    protected void checkFooter(int previousPage) {
        String page = "";
        if (locale == Locale.FRENCH) {
            page = pagesFR[previousPage];
        } else {
            page = pagesEN[previousPage];
        }
        assertThat(footerText.getText(), is(locale == Locale.FRENCH ? "Vous venez de la " + page
                                                                   : "You are coming from " + page));
    }

    protected boolean hasPreviousPage() {
        FluentWebElement previousPage = getPreviousPageElement();
        if (previousPage == null) {
            return false;
        }
        return previousPage.isDisplayed();
    }

    private FluentWebElement getPreviousPageElement() {
        FluentList<FluentWebElement> elements = find("#previousPage");
        if ( !elements.isEmpty()) {
            return elements.get(0);
        }
        return null;
    }

    protected void waitPageToLoad() {
        if (selenium == null) {
            selenium = new WebDriverBackedSelenium(getDriver(), getBaseUrl());
        }
        selenium.waitForPageToLoad(PAGE_TO_LOAD_TIMEOUT);
    }

}
