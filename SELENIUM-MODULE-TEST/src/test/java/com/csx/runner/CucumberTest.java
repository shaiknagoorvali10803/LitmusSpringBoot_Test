package com.csx.runner;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("featurefiles")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.csx.stepdefinitions")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "classpath:featurefiles")
@ConfigurationParameter(key= FILTER_TAGS_PROPERTY_NAME,value = "@extent")
@ConfigurationParameter(key= EXECUTION_DRY_RUN_PROPERTY_NAME,value = "false")
@ConfigurationParameter(key= PARALLEL_EXECUTION_ENABLED_PROPERTY_NAME,value = "false")
public class CucumberTest {
}

