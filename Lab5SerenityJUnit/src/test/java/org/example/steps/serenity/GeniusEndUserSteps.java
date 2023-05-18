package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.GeniusPage;

import static org.junit.Assert.assertEquals;

public class GeniusEndUserSteps {
    GeniusPage geniusPage;

    @Step
    public void enters(String keyword) {
        geniusPage.enter_keyword(keyword);
    }

    @Step
    public void searches() {
        geniusPage.go();
    }

    @Step
    public void shouldSeeTitle(String title) {
        assertEquals(geniusPage.getHitResult(), title);
    }

    @Step
    public void open(){
        geniusPage.open();
        acceptCookie();
    }

    @Step
    public void looksFor(String keyword){
        enters(keyword);
        searches();
    }

    @Step
    public void acceptCookie(){
        geniusPage.acceptCookies();
    }
}
