package com.blog.playwright.github.happypath;

import com.blog.playwright.configuration.BaseConfigurationTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

class PlaywrightGitHubRegressionTest extends BaseConfigurationTest {

    private static final String GITHUB_URL = "https://github.com";

    @Test
    void should_containsTitle_when_accessMainPage() {
        //given
        var page = browser.newPage();

        //when
        page.navigate(GITHUB_URL);

        //then
        PlaywrightAssertions.assertThat(page)
                .hasTitle("GitHub: Let’s build from here · GitHub");
    }

    @Test
    void should_findPlaywright_when_searchIt() {
        //given
        var page = browser.newPage();
        page.navigate(GITHUB_URL);

        //when - it could be more clear create a method to find a project and receive the value as a parameter
        // but for test purpose I will let this code here
        page.getByLabel("Search or jump to…").click();
        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Search")).fill("playwright");
        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Search")).press("Enter");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("microsoft/playwright-java")).click();

        //then
        PlaywrightAssertions.assertThat(page).hasURL("https://github.com/microsoft/playwright-java");
    }
}