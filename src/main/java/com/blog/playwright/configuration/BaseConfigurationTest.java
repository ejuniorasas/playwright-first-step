package com.blog.playwright.configuration;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BaseConfigurationTest {

    protected static Playwright playwright;
    protected static Browser browser;

    @BeforeAll
    static void beforeAll() {
        createPlaywright();
        createBrowser();
    }

    @AfterAll
    static void afterAll() {
        closeBrowser();
        closePlaywright();
    }

    private static void closePlaywright() {
        if (playwright != null) {
            playwright.close();
        }
    }

    private static void closeBrowser() {
        if (browser != null) {
            browser.close();
        }
    }

    private static void createBrowser() {
        browser = playwright.chromium().launch();
    }

    private static void createPlaywright() {
        playwright = Playwright.create();
    }
}
