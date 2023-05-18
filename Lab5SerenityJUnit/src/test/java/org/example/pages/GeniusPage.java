package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

@DefaultUrl("https://genius.com/")
public class GeniusPage extends PageObject {

    @FindBy(name="q")
    private WebElementFacade searchBar;

    @FindBy(xpath = "/html/body/div[1]/div/div[1]/div[1]/form/div")
    private WebElementFacade searchButton;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElementFacade acceptCookies;

    public void enter_keyword(String keyword){
        searchBar.type(keyword);
    }

    public void go(){
        //searchButton.click();
        searchBar.sendKeys(Keys.ENTER);
    }

    public String getHitResult(){
        WebElementFacade result = find(By.className("mini_card-title"));
        return result.getText();
    }

    public void acceptCookies(){
        acceptCookies.click();
    }
}
