package fr.atatorus.tutoselenium.pages;

import org.fluentlenium.core.domain.FluentWebElement;

public class HtmlUnitDelegate implements BrowserDelegate {

    @Override
    public String buildUrl(String baseUrl, String resource) {
        String[] split = baseUrl.split("/");
        String root = "";
        if (split.length == 4) {
            root = "/" + split[3];
        }
        return "url(" + root + "/faces/javax.faces.resource/" + resource + "?ln=images)";
    }

    @Override
    public String getErrorColor() {
        return "red";
    }

    @Override
    public boolean isDisplayed(FluentWebElement element) {
        return !element.getText().equals("");
    }

}
