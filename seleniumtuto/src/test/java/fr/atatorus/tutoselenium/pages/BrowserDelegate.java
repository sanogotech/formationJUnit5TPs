package fr.atatorus.tutoselenium.pages;

import org.fluentlenium.core.domain.FluentWebElement;

public interface BrowserDelegate {

    String buildUrl(String baseUrl, String resource);

    String getErrorColor();

    boolean isDisplayed(FluentWebElement element);

}
