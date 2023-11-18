package com.honzikd;

import org.openqa.selenium.firefox.FirefoxDriver;

import static java.lang.System.exit;


public class WikipediaCrawler {

    public static void main(String[] args) {

        FirefoxDriver driver = new FirefoxDriver();

        long limit = 10000;
        long counter = 0;

        driver.get("https://en.wikipedia.org/wiki/Special:Random/Article");

        while (!driver.getCurrentUrl().equals("https://en.wikipedia.org/wiki/Philosophy") && counter < limit) {
            WikipediaPage wikiPage = new WikipediaPage(driver);
            wikiPage.clickFirstLinkInArticleText();
            counter++;
        }

        System.out.println("Got to 'Philosophy' article in: " + counter + " transitions.");

        driver.close();
        exit(0);
    }
}
