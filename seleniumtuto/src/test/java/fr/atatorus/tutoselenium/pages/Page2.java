package fr.atatorus.tutoselenium.pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

public class Page2 extends BasePage {

    // //////////////////////////////////////////////
    // BODY

    @FindBy(id = "contentForm:pageTitle")
    private FluentWebElement pageTitle;
    @FindBy(className = "ui-panel-title")
    private FluentWebElement panelTitle;
    @FindBy(id = "contentForm:page1Button")
    private FluentWebElement page1Button;
    @FindBy(id = "contentForm:page2Button")
    private FluentWebElement page2Button;
    @FindBy(id = "contentForm:page3Button")
    private FluentWebElement page3Button;

    public void clicPage1Button() {
        page1Button.click();
        waitPageToLoad();
    }

    public void clicPage2Button() {
        page2Button.click();
        waitPageToLoad();
    }

    public void clicPage3Button() {
        page3Button.click();
        waitPageToLoad();
    }

    @Override
    public void isAt() {
        assertThat(pageTitle.getText(), is(locale == Locale.FRENCH ? "Page deux" : "Page two"));
    }

    public void checkPage(int previousPage) {
        checkHeader();
        checkBody();
        checkFooter(previousPage);
    }

    public void checkPage() {
        checkHeader();
        checkBody();
        assertThat(hasPreviousPage(), is(false));
    }

    private void checkBody() {
        assertThat(panelTitle.getText(), is(locale == Locale.FRENCH ? "Choix de la prochaine page"
                                                                   : "Select the new page"));
        assertThat(page1Button.getText(), is(locale == Locale.FRENCH ? "Page une" : "Page one"));
        assertThat(page2Button.getText(), is(locale == Locale.FRENCH ? "Page deux" : "Page two"));
        assertThat(page3Button.getText(), is(locale == Locale.FRENCH ? "Page trois" : "Page three"));
    }

}
