package com.csx.page.actions;

import com.csx.page.objects.GooglePageObjects;
import com.csx.springConfig.Annotations.LazyAutowired;
import com.csx.springConfig.Annotations.Page;
import com.csx.stepdefinitions.ScenarioContext;
import com.csx.test.util.ScreenshotUtils;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
@Page
public class GooglePageActions {
    @Autowired
    WebDriver driver;
    @Autowired
    WebDriverWait wait;
    @LazyAutowired
    ScreenshotUtils screenshotUtils;
    @Autowired
    ScenarioContext scenarioContext;
    @LazyAutowired
    GooglePageObjects pageObjects;

    @Value("${application.url}")
    private String url;

    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver, this.pageObjects);
    }
    public void goTo(){
        this.driver.navigate().to(url);
    }
    public void search(final String keyword){
        pageObjects.searchBox.sendKeys(keyword);
        screenshotUtils.insertScreenshot1(scenarioContext.getScenario(),"screenshot");
        pageObjects.searchBox.sendKeys(Keys.TAB);
        pageObjects.searchBtns
                .stream()
                .filter(e -> e.isDisplayed() && e.isEnabled())
                .findFirst()
                .ifPresent(WebElement::click);
    }
    public int getCount(){

        return pageObjects.results.size();


    }
    public boolean isAt() {
        return this.wait.until((d) -> pageObjects.searchBox.isDisplayed());
    }
}
