package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.example.steps.serenity.GeniusEndUserSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/GeniusTestData.csv")
public class GeniusSearchByKeywordDDT {
    @Managed(uniqueSession = true)
    public WebDriver webDriver;

    @Steps
    public GeniusEndUserSteps steps;

    public String keyword;
    public String title;

    @Qualifier
    public String getQualifier() {
        return keyword;
    }

    @Test
    public void searchGeniusByKeywordTestDDT() {
        steps.open();
        steps.looksFor(getKeyword());
        steps.shouldSeeTitle(getTitle());
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
