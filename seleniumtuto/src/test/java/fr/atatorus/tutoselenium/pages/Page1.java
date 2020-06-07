package fr.atatorus.tutoselenium.pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

public class Page1 extends BasePage {

    // //////////////////////////////////////////////
    // BODY

    @FindBy(id = "contentForm:pageTitle")
    private FluentWebElement pageTitle;
    @FindBy(className = "ui-panel-title")
    private FluentWebElement panelTitle;
    @FindBy(id = "contentForm:label")
    private FluentWebElement label;
    @FindBy(id = "contentForm:pageText")
    private FluentWebElement textField;
    @FindBy(id = "contentForm:pageError")
    private FluentWebElement errorMessage;
    @FindBy(id = "contentForm:nextPage")
    private FluentWebElement button;

    public void setNextPage(int page) {
        textField.clear();
        textField.text("" + page);
    }

    public void buttonClick() {
        button.click();
        waitPageToLoad();
    }

    @Override
    public void isAt() {
        assertThat(pageTitle.getText(), is(locale == Locale.FRENCH ? "Page une" : "Page one"));
    }

    public void checkPage(boolean errorExpected, int previousPage) {
        checkHeader();
        checkBody(errorExpected);
        assertThat(hasPreviousPage(), is(true));
        checkFooter(previousPage);
    }

    public void checkPage(boolean errorExpected) {
        checkHeader();
        checkBody(errorExpected);
        assertThat(hasPreviousPage(), is(false));
    }

    public void gotoPage(int page) {
        setNextPage(page);
        buttonClick();
    }

    private void checkBody(boolean errorExpected) {
        assertThat(panelTitle.getText(), is(locale == Locale.FRENCH ? "Choix de la prochaine page"
                                                                   : "Select the new page"));
        assertThat(label.getText(), is(locale == Locale.FRENCH ? "Numéro de la prochaine page"
                                                              : "Number of next page :"));
        assertThat(button.getText(), is(locale == Locale.FRENCH ? "Page suivante" : "Next page"));
        String errorText = errorMessage.getText();
        assertThat(delegate.isDisplayed(errorMessage), is(errorExpected));
        if (errorExpected) {
            assertThat(errorText,
                       is(locale == Locale.FRENCH ? "Vous devez entrer une valeur entre un et trois."
                                                 : "You must enter a value between one and three"));
            assertThat(errorMessage.getElement().getCssValue("color"), is(delegate.getErrorColor()));
        }
    }

}
