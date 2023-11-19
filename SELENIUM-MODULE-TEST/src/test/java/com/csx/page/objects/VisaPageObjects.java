package com.csx.page.objects;

import com.csx.springConfig.Annotations.LazyAutowired;
import com.csx.springConfig.Annotations.Page;
import com.csx.stepdefinitions.ScenarioContext;
import com.csx.test.util.ScreenshotUtils;
import com.csx.test.util.SeleniumUtil;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Objects;

@Page
public class VisaPageObjects {
    private static final Logger logger = LoggerFactory.getLogger(VisaPageObjects.class);

    @FindBy(id ="first_4")
    public WebElement firstName;

    @FindBy(id ="last_4")
    public WebElement lastName;

    @FindBy(id = "input_46")
    public WebElement fromCountry;

    @FindBy(id = "input_47")
    public WebElement toCountry;

    @FindBy(id = "input_24_month")
    public WebElement month;

    @FindBy(id = "input_24_day")
    public WebElement day;

    @FindBy(id = "input_24_year")
    public WebElement year;

    @FindBy(id = "input_6")
    public WebElement email;

    @FindBy(id = "input_27_phone")
    public WebElement phone;

    @FindBy(id = "input_45")
    public WebElement comments;

    @FindBy(id = "submitBtn")
    public WebElement submit;

    @FindBy(id = "requestnumber")
    public WebElement requestNumber;


}
