package com.csx.page.objects;


import com.csx.springConfig.Annotations.Page;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Page
public class HomePageObjects {

    @FindBy(name = "q")
    public WebElement searchBox;
    @FindBy(name = "btnK")
    public List<WebElement> searchBtns;

}
