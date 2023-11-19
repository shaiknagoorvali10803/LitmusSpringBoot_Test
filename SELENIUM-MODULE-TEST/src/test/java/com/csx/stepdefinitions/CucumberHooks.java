package com.csx.stepdefinitions;


import com.csx.springConfig.Annotations.LazyAutowired;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class CucumberHooks {
    @LazyAutowired
    private ApplicationContext applicationContext;
    @Autowired
    protected WebDriver driver;

    @AfterStep
    public void afterStep(Scenario scenario) throws IOException, InterruptedException {
        if(scenario.isFailed()){
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", scenario.getName());
        }
    }
    @After
    public void afterScenario(){
        this.applicationContext.getBean(WebDriver.class).quit();
        }
   }


