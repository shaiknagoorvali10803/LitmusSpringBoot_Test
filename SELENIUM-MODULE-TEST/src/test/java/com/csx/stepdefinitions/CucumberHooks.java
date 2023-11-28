package com.csx.stepdefinitions;


import com.csx.springConfig.Annotations.LazyAutowired;
import com.csx.test.util.FileHandlingUtil;
import com.csx.test.util.LoggingException;
import com.csx.test.util.VideoRecorder;
import io.cucumber.java.*;
import org.apache.commons.lang3.BooleanUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.IOException;


public class CucumberHooks {
    public static final String LOCAL_VIDEO_RECORD_FLAG = "localVideoRecord";
    public static final String downloadPath = System.getProperty("user.dir");
    public static final String videoFileType = "avi";
    @LazyAutowired
    private ApplicationContext applicationContext;
    @Autowired
    protected WebDriver driver;

    @Before
    public void settingScenario(Scenario scenario) throws Exception {
        System.out.println("video recording started");
        localVideoRecord();
    }

    @After
    public void afterScenario(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            try {
                if (!BooleanUtils.toBoolean(System.getProperty("buildToolRun"))) {
                    VideoRecorder.stopRecording();
                }
            } catch (ClassCastException | IOException |NullPointerException e) {
                throw new LoggingException(e);
            }
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", scenario.getName());
        }
        else {
            if (!BooleanUtils.toBoolean(System.getProperty("buildToolRun"))) {
                VideoRecorder.stopRecording();
                System.out.println("video recording ended");
                String fileName = FileHandlingUtil.getTheNewestFile(downloadPath, videoFileType);
                //FileHandlingUtil.deleteExistingFile(fileName);
            }
        }
        this.applicationContext.getBean(WebDriver.class).quit();
    }

    private void localVideoRecord() throws Exception {
        if (!BooleanUtils.toBoolean(System.getProperty("buildToolRun"))) {
            VideoRecorder.startRecording();
        }
    }

    @After
    public void afterScenario(){
        this.applicationContext.getBean(WebDriver.class).quit();
        }
   }


