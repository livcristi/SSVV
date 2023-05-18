package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;

import org.example.steps.serenity.GeniusEndUserSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class GeniusSearchByKeyword {
    @Managed(uniqueSession = true)
    public WebDriver webDriver;

    @Steps
    public GeniusEndUserSteps steps;

    @Test
    public void searchingByKeywordMrPostmanShouldReturnTitle(){
        steps.open();
        steps.looksFor("mr postman");
        steps.shouldSeeTitle("Ova da Wudz");
    }

    @Test
    public void searchingByKeywordMrLonelyShouldReturnTitle(){
        steps.open();
        steps.looksFor("mr lonely");
        steps.shouldSeeTitle("911 / Mr. Lonely");
    }
}
