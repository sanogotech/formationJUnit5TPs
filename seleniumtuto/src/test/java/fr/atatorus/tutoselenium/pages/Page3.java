package fr.atatorus.tutoselenium.pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Page3 extends BasePage {

    // //////////////////////////////////////////////
    // BODY

    @FindBy(id = "contentForm:pageTitle")
    private FluentWebElement pageTitle;
    @FindBy(className = "ui-panelgrid-header")
    private FluentWebElement panelTitle;
    @FindBy(id = "contentForm:label")
    private FluentWebElement label;
    @FindBy(id = "contentForm:nextPageButton")
    private FluentWebElement button;
    @FindBy(id = "contentForm:pageList_input")
    private FluentWebElement list;

    public void selectNextPage(String page) {
        getList().selectByValue(page);
    }

    public void clickOnNextPageButton() {
        button.click();
        waitPageToLoad();
    }

    private WebElement getOption(int index) {
        return getList().getOptions().get(index);
    }

    private Select getList() {
        Select select = new Select(list.getElement());
        return select;
    }

    @Override
    public void isAt() {
        assertThat(pageTitle.getText(), is(locale == Locale.FRENCH ? "Page trois" : "Page three"));
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
        assertThat(panelTitle.getText(), is(locale == Locale.FRENCH ? "Choix de la nouvelle page"
                                                                   : "Select the new page"));
        assertThat(label.getText(), is(locale == Locale.FRENCH ? "Choisissez la nouvelle page :"
                                                              : "Select the page :"));
        assertThat(button.getText(), is(locale == Locale.FRENCH ? "Page suivante" : "Next page"));
        assertThat(getOption(0).getText(), is("page1"));
        assertThat(getOption(1).getText(), is("page2"));
        assertThat(getOption(2).getText(), is("page3"));
    }

}
