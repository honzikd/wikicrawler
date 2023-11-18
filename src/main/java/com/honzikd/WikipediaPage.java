package com.honzikd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;


final class WikipediaPage {

    private final WebDriver driver;
    private final By mainTextContent = By.id("mw-content-text");
    private final By textParagraph = By.tagName("p");
    private final By wikiLink = By.xpath("a[contains(@href,'/wiki/')]");

    public WikipediaPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getMainContent() {
        return driver.findElement(mainTextContent);
    }

    private List<WebElement> getParagraphs() {
        return getMainContent().findElements(textParagraph);
    }

    private WebElement getFirstHyperlinkInParagraphs() {
        for (WebElement paragraph : getParagraphs()) {
            List<WebElement> links = paragraph.findElements(wikiLink);
            if (!links.isEmpty()) {
                return links.get(0);
            }
        }
        // Dead-end page. This should (almost) never happen, see: https://en.wikipedia.org/wiki/Category:Dead-end_pages
        throw new NoSuchElementException("No wiki link found in the main article text at: " + driver.getCurrentUrl());
    }

    public void clickFirstLinkInArticleText() {
        getFirstHyperlinkInParagraphs().click();
    }
}

