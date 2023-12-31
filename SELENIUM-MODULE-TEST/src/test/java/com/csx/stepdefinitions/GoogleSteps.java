package com.csx.stepdefinitions;

import com.csx.page.actions.GooglePageActions;
import com.csx.springConfig.Annotations.LazyAutowired;
import com.csx.test.util.ScreenshotUtils;
import com.csx.test.util.SeleniumUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.Uninterruptibles;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;

public class GoogleSteps {

    @Autowired
    protected WebDriver driver;
    @Autowired
    protected WebDriverWait wait;
    @Autowired
    public TestUserDetails testUserDetails;
    Scenario scenario;
    @Autowired
    ScenarioContext scenarioContext;
    @Autowired
    ScreenshotUtils screenshotUtils;
    @LazyAutowired
    private GooglePageActions googlePage;
    @Autowired
    public GoogleSteps(TestUserDetails testUserDetails) {
        this.testUserDetails = testUserDetails;
    }
    @Before
    public void settingScenario(Scenario scenario) {
        this.scenario=scenario;
        scenarioContext.setScenario(scenario);
        System.out.println("scenarion object in Google page by : ==>"+ scenario );
    }
    @Given("I am on the google site")
    public void launchSite() throws InterruptedException {
        this.googlePage.goTo();
        screenshotUtils.insertScreenshot1(scenario,"screenshot");
        screenshotUtils.insertScreenshot("screenshot");
        testUserDetails.setUserDetails(new UserDetails("Shaik.Nagoorvali", "password"));
    }

    @When("I enter {string} as a keyword")
    public void enterKeyword(String keyword) throws InterruptedException {
        this.googlePage.search(keyword);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='result-stats']")));
        screenshotUtils.insertScreenshot1(scenario,"screenshot");
        screenshotUtils.insertScreenshot("screenshot");
        screenshotUtils.addLog("searching for String :" + keyword);
    }

    @Then("I should see search results page")
    public void clickSearch() throws IOException, JSONException {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(4));
        Assert.assertTrue(this.googlePage.isAt());

        Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/JsonFiles/JsonFile.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonTree = objectMapper.readTree(reader);
        JSONObject inputJSONOBject = new JSONObject(jsonTree.toPrettyString());
        screenshotUtils.addJsonLog(inputJSONOBject);

        //Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Then("I should see at least {int} results")
    public void verifyResults(int count) throws InterruptedException, IOException {
        Assert.assertTrue(this.googlePage.getCount() >= count);
        SeleniumUtil.clickElementbyXPath(driver,"//a[normalize-space()='Images']");
        Thread.sleep(3000);
        screenshotUtils.insertScreenshot1(scenario,"screenshot");
        screenshotUtils.insertScreenshot("screenshot");
        driver.findElement(By.xpath("//a[normalize-space()='Videos']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='result-stats']")));
        screenshotUtils.insertScreenshot1(scenario,"screenshot");
        screenshotUtils.insertScreenshot("screenshot");
        screenshotUtils.addLog(Arrays.asList("nagoor", "rubia", "nazim", "rayan"));

    }
}
