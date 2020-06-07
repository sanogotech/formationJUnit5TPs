package fr.atatorus.tutoselenium.pages;

import org.fluentlenium.core.domain.FluentWebElement;

public class FirefoxDelegate implements BrowserDelegate {

    @Override
    public String buildUrl(String baseUrl, String resource) {
        return "url(\"" + baseUrl + "faces/javax.faces.resource/" + resource + "?ln=images\")";
    }

    @Override
    public String getErrorColor() {
        return "rgba(255, 0, 0, 1)";
    }

    @Override
    public boolean isDisplayed(FluentWebElement element) {
        return element.isDisplayed();
    }

}
